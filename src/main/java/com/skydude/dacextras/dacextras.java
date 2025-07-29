package com.skydude.dacextras;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.skydude.dacextras.ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS;
import static net.minecraft.util.datafix.fixes.BlockEntitySignTextStrictJsonFix.GSON;

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
    public static final List<String> CLASS_IDS = new ArrayList<>();
    private static final Gson GSON = new Gson();

    public dacextras() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        Mixins.addConfigurations("dacextras.mixins.json");
//        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        ModBiomeModifiers.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            System.out.println("[dacextras] commonSetup complete.");
        });
        Path jsonPath = FMLPaths.CONFIGDIR.get()
                .resolve("dacextras_custom_classes")
                .resolve("custom_main.json");

        if (!Files.exists(jsonPath)) {
            LOGGER.warn("⚠ JSON custom class config not found at {}. No custom classes will be loaded.", jsonPath);
            return;
        } else {

            try {
                String jsonContent = Files.readString(jsonPath);
                JsonObject config = GSON.fromJson(jsonContent, JsonObject.class);

                if (config != null && config.has("custom_classes") && config.get("custom_classes").isJsonArray()) {
                    config.getAsJsonArray("custom_classes").forEach(e -> {
                        String id = e.getAsString();
                        CLASS_IDS.add(id);
                        LOGGER.info("📦 Loaded custom class ID from JSON: {}", id);
                    });
                } else {
                    LOGGER.warn("⚠ No 'custom_classes' array found in {}", jsonPath);
                }
            } catch (IOException ex) {
                LOGGER.error("❌ Failed to read JSON config {}: {}", jsonPath, ex.getMessage());
            }
        }
    }


}
