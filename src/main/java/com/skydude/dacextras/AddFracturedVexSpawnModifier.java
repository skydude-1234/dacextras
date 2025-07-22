package com.skydude.dacextras;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class AddFracturedVexSpawnModifier implements BiomeModifier {

    public static final Codec<AddFracturedVexSpawnModifier> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(mod -> mod.biomes),
            ForgeRegistries.ENTITY_TYPES.getCodec().fieldOf("entity").forGetter(mod -> mod.entityType),
            Codec.INT.fieldOf("weight").forGetter(mod -> mod.weight),
            Codec.INT.fieldOf("minCount").forGetter(mod -> mod.minCount),
            Codec.INT.fieldOf("maxCount").forGetter(mod -> mod.maxCount)
    ).apply(instance, AddFracturedVexSpawnModifier::new));

    private final HolderSet<Biome> biomes;
    private final EntityType<?> entityType;
    private final int weight;
    private final int minCount;
    private final int maxCount;

    public AddFracturedVexSpawnModifier(HolderSet<Biome> biomes, EntityType<?> entityType, int weight, int minCount, int maxCount) {
        this.biomes = biomes;
        this.entityType = entityType;
        this.weight = Config.FRACTURED_VEX_SPAWN_WEIGHT.get();// SpawnConfig.WEIGHT.get();
        this.minCount = Config.FRACTURED_VEX_MIN_COUNT.get();//SpawnConfig.MIN_COUNT.get();
        this.maxCount = Config.FRACTURED_VEX_MAX_COUNT.get(); //SpawnConfig.MAX_COUNT.get();
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && biomes.contains(biome)) {
            builder.getMobSpawnSettings().addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(entityType, weight, minCount, maxCount)
            );
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return CODEC;
    }

}
