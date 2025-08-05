package com.skydude.dacextras;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class CustomClasses {


    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new Gson();

    private final String class_id;
    public static JsonObject classConfig; // holds this class's custom data






    public CustomClasses( String class_id) {
        this.class_id = class_id;

        //  this.player = player;

        // Load JSON for this specific class_id
     //   loadClassConfig();
        var the_id = class_id;
    }
public static void execute(String class_id, Entity entity) throws IOException {
    if (!(entity instanceof Player player)) {
        System.out.println("entity is not instanceof Player player");
        return;
    } else{
        player.closeContainer();
    }

    if (entity instanceof ServerPlayer serverPlayer) {
        Advancement adv = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("dungeons_and_combat:origin"));
        AdvancementProgress _ap = serverPlayer.getAdvancements().getOrStartProgress(adv);

        if (!_ap.isDone()) {
            for(String criteria : _ap.getRemainingCriteria()) {
                serverPlayer.getAdvancements().award(adv, criteria);
            }
        }
    }
    if (entity instanceof ServerPlayer serverPlayer) {

        Advancement _adv = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("dungeons_and_combat:the_rogue"));
        assert _adv != null;

        AdvancementProgress _ap = serverPlayer.getAdvancements().getOrStartProgress(_adv);
        if (!_ap.isDone()) {
            for(String criteria : _ap.getRemainingCriteria()) {
                serverPlayer.getAdvancements().award(_adv, criteria);
            }
        }
    }

    Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(getmaxhealth(class_id));
    // set the health to amx health so no glitches happen
    player.setHealth(player.getMaxHealth());

    Objects.requireNonNull(player.getAttribute(Attributes.LUCK)).setBaseValue(Config.ROGUE_LUCK.get());
    Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(Config.ROGUE_DAMAGE.get());
    Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(Config.ROGUE_SPEED.get());
    Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(Config.ROGUE_ATTACK_SPEED.get());
    Objects.requireNonNull(player.getAttribute(Attributes.ARMOR_TOUGHNESS)).setBaseValue(0);
    Objects.requireNonNull(player.getAttribute(Attributes.ARMOR)).setBaseValue(Config.ROGUE_ARMOR.get());


    if(player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
        player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.ROGUE_HELMET.get())))));
    }

    if(player.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
        player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.ROGUE_CHESTPLATE.get())))));
    }


    if(player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
        player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.ROGUE_LEGGINGS.get())))));
    }

    if(player.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
        player.setItemSlot(EquipmentSlot.FEET, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.ROGUE_BOOTS.get())))));
    }

    if(player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()){

        player.setItemInHand(InteractionHand.MAIN_HAND, (new ItemStack((ItemLike) DungeonsAndCombatModItems.DAGGER.get())).copy());
    }
    if(player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
        player.setItemInHand(InteractionHand.OFF_HAND, (new ItemStack((ItemLike) DungeonsAndCombatModItems.DAGGER.get())).copy());
    }

}

//    private void loadClassConfig() {
//        Path jsonPath = FMLPaths.CONFIGDIR.get()
//                .resolve("dacextras_custom_classes")
//                .resolve(class_id + ".json"); // filename matches class_id
//
//        if (!Files.exists(jsonPath)) {
//            LOGGER.warn("⚠ No config found for custom class '{}'. Expected: {}", class_id, jsonPath);
//            return;
//        }
//
//        try {
//            String jsonContent = Files.readString(jsonPath);
//            classConfig = GSON.fromJson(jsonContent, JsonObject.class);
//
//            if (classConfig == null) {
//                LOGGER.error("❌ Failed to parse JSON for class_id '{}'", class_id);
//                return;
//            }
//
//            LOGGER.info("✅ Loaded config for class_id '{}': {}", class_id, jsonPath);
//
//            // Example: access custom values
//            if (classConfig.has("max_health")) {
//                int maxhealth = classConfig.get("max_health").getAsInt();
//             //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
//            }
//
//        } catch (IOException | JsonSyntaxException e) {
//            LOGGER.error("❌ Error reading config for '{}': {}", class_id, e.getMessage());
//        }
//    }

    public String getClassId() {
        return class_id;
    }
public static JsonObject getclassconfig(String class_id) {
        Path jsonPath = FMLPaths.CONFIGDIR.get()
                .resolve("dacextras_custom_classes")
                .resolve(class_id + ".json"); // filename matches class_id

        if (!Files.exists(jsonPath)) {
            LOGGER.warn("⚠ No config found for custom class '{}'. Expected: {}", class_id, jsonPath);
        }


        String jsonContent = null;
        try {
            jsonContent = Files.readString(jsonPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        classConfig = GSON.fromJson(jsonContent, JsonObject.class);

        if (classConfig == null) {
            LOGGER.error("❌ Failed to parse JSON for class_id '{}'", class_id);
        }
        return classConfig;
    }
public static int getmaxhealth(String class_id) throws IOException {

    classConfig = getclassconfig(class_id);
    int maxhealth = 0;
    if (classConfig.has("max_health")) {
        maxhealth = classConfig.get("max_health").getAsInt();

        //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
    }
    return maxhealth;
}
public static int getstrength(String class_id){

    classConfig = getclassconfig(class_id);
    int strength = 0;
    if (classConfig.has("damage")) {
        strength = classConfig.get("damage").getAsInt();

        //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
    }
        return strength;
}
public static int getspeed(String class_id){

        classConfig = getclassconfig(class_id);
        int speed = 0;
        if (classConfig.has("speed")) {
            speed = classConfig.get("speed").getAsInt();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return speed;
    }
    public static int getswing(String class_id){

        classConfig = getclassconfig(class_id);
        int swing = 0;
        if (classConfig.has("swing")) {
            swing = classConfig.get("swing").getAsInt();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return swing;
    }
    public static int getarmor(String class_id){

        classConfig = getclassconfig(class_id);
        int armor = 0;
        if (classConfig.has("armor")) {
            armor = classConfig.get("armor").getAsInt();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return armor;
    }
    public static int gettougness(String class_id){

        classConfig = getclassconfig(class_id);
        int toughness = 0;
        if (classConfig.has("toughness")) {
            toughness = classConfig.get("toughness").getAsInt();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return toughness;
    }

    public JsonObject getClassConfig() {
        return classConfig;
    }

}

