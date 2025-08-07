package com.skydude.dacextras;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.mcreator.dungeonsandcombat.DungeonsAndCombatMod.PACKET_HANDLER;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(dacextras.MODID)
public class dacextras {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "dacextras";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "dacextras" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "dacextras" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static double NUMBER = -1.0;
    public static final List<String> CLASS_IDS = new ArrayList<>();
    public static String TEMPCLASS_ID = "";
    private static final Gson GSON = new Gson();
    public static final Path jsonPath = FMLPaths.CONFIGDIR.get()
            .resolve("dacextras_custom_classes")
            .resolve("custom_main.json");
    public static double hhealth;
    public static double lluck;
    public static double sstrength;
    public static double sspeed;
    public static double aattack_speed;
    public static double ttoughness;
    public static double aarmor;

    private static int messageID = 0;

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        ++messageID;
    }


    public dacextras() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        Mixins.addConfigurations("dacextras.mixins.json");
//        // Register the commonSetup method for modloading
        OverrideModmenus.MENUS.register(modEventBus); //
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        ModBiomeModifiers.register(modEventBus);

        // OverrideModmenus.CUSTOM_CLASS_GUI.getId(); // force class load


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(modEventBus);


    }


    public static boolean doesitexist() {
        boolean exists = Files.exists(jsonPath);
        if (!exists) {
            LOGGER.warn("⚠ JSON custom class config not found at {}. No custom classes will be loaded.", jsonPath);
        }
        return exists;
    }



    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            System.out.println("[dacextras] commonSetup complete.");
        });


        if (!doesitexist()) {
            return;
        } else {

            try {
                String jsonContent = Files.readString(jsonPath);
                JsonObject jconfig = GSON.fromJson(jsonContent, JsonObject.class);

                if (jconfig != null && jconfig.has("custom_classes") && jconfig.get("custom_classes").isJsonArray()) {
                    CLASS_IDS.clear(); // prevent duplicates
                    jconfig.getAsJsonArray("custom_classes").forEach(e -> {
                        String class_id = e.getAsString();
                        CLASS_IDS.add(class_id);
                        LOGGER.info("📦 Loaded custom class ID from JSON: {}", class_id);
                    });

                    // Ensure server config is applied globally
                    for (String class_id : dacextras.CLASS_IDS) {
                        CustomClasses custom = new CustomClasses(class_id);
                        CustomClassesRegistry.register(custom);

                    }
                } else {
                    LOGGER.warn("⚠ No 'custom_classes' array found in {}", jsonPath);
                }
            } catch (IOException ex) {
                LOGGER.error("❌ Failed to read JSON config {}: {}", jsonPath, ex.getMessage());
            }
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(OverrideModmenus.CUSTOM_CLASS_GUI.get(), CustomClassGUIScreen::new);

            LOGGER.info("[dacextras] Registered CustomClassGUIScreen for {}",
                    OverrideModmenus.CUSTOM_CLASS_GUI.getId());
        });
    }
}
    // to apply:

