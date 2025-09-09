package com.skydude.dacextras;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static com.skydude.dacextras.CustomClassesRegistry.CLASSES;
import static com.skydude.dacextras.CustomClassesRegistry.CLASSES_id;
import static com.skydude.dacextras.dacextras.*;

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
    public static void class_attributes(Player player){
        Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(player.getPersistentData().getDouble("dacextras.maxhealth"));
        // set the health to amx health so no glitches happen
        player.setHealth(player.getMaxHealth());

        Objects.requireNonNull(player.getAttribute(Attributes.LUCK)).setBaseValue(player.getPersistentData().getDouble("dacextras.luck"));
        Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(player.getPersistentData().getDouble("dacextras.strength"));
        Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(player.getPersistentData().getDouble("dacextras.speed"));
        Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(player.getPersistentData().getDouble("dacextras.attackspeed"));
        Objects.requireNonNull(player.getAttribute(Attributes.ARMOR_TOUGHNESS)).setBaseValue(player.getPersistentData().getDouble("dacextras.toughness"));
        Objects.requireNonNull(player.getAttribute(Attributes.ARMOR)).setBaseValue(player.getPersistentData().getDouble("dacextras.armor"));

    }
public static void execute(String class_id, Entity entity) {
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

        Advancement _adv = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("dacextras:the_custom"));
        assert _adv != null;

        AdvancementProgress _ap = serverPlayer.getAdvancements().getOrStartProgress(_adv);
        if (!_ap.isDone()) {
            for(String criteria : _ap.getRemainingCriteria()) {
                serverPlayer.getAdvancements().award(_adv, criteria);
            }
        }
    }
    player.getPersistentData().putString("dacextras.class_id", class_id);
    player.getPersistentData().putDouble("dacextras.maxhealth", getmaxhealth(class_id));
    player.getPersistentData().putDouble("dacextras.luck", getluck(class_id));
    player.getPersistentData().putDouble("dacextras.strength", getstrength(class_id));
    player.getPersistentData().putDouble("dacextras.speed", getspeed(class_id));
    player.getPersistentData().putDouble("dacextras.attackspeed", getswing(class_id));
    player.getPersistentData().putDouble("dacextras.toughness", gettougness(class_id));
    player.getPersistentData().putDouble("dacextras.armor", getarmor(class_id));

    class_attributes(player);





        player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(gethelm(class_id))))));



        player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(getchest(class_id))))));




        player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(getlegs(class_id))))));



        player.setItemSlot(EquipmentSlot.FEET, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(getboot(class_id))))));




        player.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(getmain(class_id))))));



        player.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(getoffhand(class_id))))));



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
public static double getmaxhealth(String class_id) {

    classConfig = getclassconfig(class_id);
    double maxhealth = 0;
    if (classConfig.has("max_health")) {
        maxhealth = classConfig.get("max_health").getAsDouble();

        //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
    }
    return maxhealth;
}
public static double getstrength(String class_id){

    classConfig = getclassconfig(class_id);
    double strength = 0;
    if (classConfig.has("damage")) {
        strength = classConfig.get("damage").getAsDouble();

        //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
    }
        return strength;
}
public static double getspeed(String class_id){

        classConfig = getclassconfig(class_id);
    double speed = 0;
        if (classConfig.has("speed")) {
            speed = classConfig.get("speed").getAsDouble();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return speed;
    }
    public static double getswing(String class_id){

        classConfig = getclassconfig(class_id);
        double swing = 0;
        if (classConfig.has("swing")) {
            swing = classConfig.get("swing").getAsDouble();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return swing;
    }
    public static double getarmor(String class_id){

        classConfig = getclassconfig(class_id);
        double armor = 0;
        if (classConfig.has("armor")) {
            armor = classConfig.get("armor").getAsDouble();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return armor;
    }
    public static double gettougness(String class_id){

        classConfig = getclassconfig(class_id);
        double toughness = 0;
        if (classConfig.has("toughness")) {
            toughness = classConfig.get("toughness").getAsDouble();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return toughness;
    }
    public static double getluck(String class_id){

        classConfig = getclassconfig(class_id);
        double luck = 0;
        if (classConfig.has("luck")) {
            luck = classConfig.get("luck").getAsDouble();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return luck;
    }


    public static String getdesc1(String class_id){

        classConfig = getclassconfig(class_id);
        String description1 = "";
        if (classConfig.has("description1")) {
            description1 = classConfig.get("description1").getAsString();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return description1;
    }
    public static String getdesc2(String class_id){

        classConfig = getclassconfig(class_id);
        String description2 = "";
        if (classConfig.has("description2")) {
            description2 = classConfig.get("description2").getAsString();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return description2;
    }
    public static String getdesc3(String class_id){

        classConfig = getclassconfig(class_id);
        String description3 = "";
        if (classConfig.has("description3")) {
            description3 = classConfig.get("description3").getAsString();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return description3;
    }
    public static String gethelm(String class_id){

        classConfig = getclassconfig(class_id);
        String helmet = "";
        if (classConfig.has("helmet")) {
            helmet = classConfig.get("helmet").getAsString();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return helmet;
    }
    public static String getchest(String class_id){

        classConfig = getclassconfig(class_id);
        String chestplate = "";
        if (classConfig.has("chestplate")) {
            chestplate = classConfig.get("chestplate").getAsString();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return chestplate;
    }
    public static String getlegs(String class_id){

        classConfig = getclassconfig(class_id);
        String leggings = "";
        if (classConfig.has("leggings")) {
            leggings = classConfig.get("leggings").getAsString();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return leggings;
    }
    public static String getboot(String class_id){

        classConfig = getclassconfig(class_id);
        String boots = "";
        if (classConfig.has("boots")) {
            boots = classConfig.get("boots").getAsString();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return boots;
    }
    public static String getmain(String class_id){

        classConfig = getclassconfig(class_id);
        String main_hand = "";
        if (classConfig.has("main_hand")) {
            main_hand = classConfig.get("main_hand").getAsString();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return main_hand;
    }
    public static String getoffhand(String class_id){

        classConfig = getclassconfig(class_id);
        String offhand = "";
        if (classConfig.has("off_hand")) {
            offhand = classConfig.get("off_hand").getAsString();

            //   LOGGER.info("Power level for {} = {}", class_id, maxhealth);
        }
        return offhand;
    }

    public JsonObject getClassConfig() {
        return classConfig;
    }

}

