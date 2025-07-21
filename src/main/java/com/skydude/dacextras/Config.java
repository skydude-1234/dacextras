package com.skydude.dacextras;



import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static final ForgeConfigSpec COMMON_CONFIG;
    public static  ForgeConfigSpec.BooleanValue OVERRIDE_ROGUE;
    public static ForgeConfigSpec.DoubleValue ROGUE_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue ROGUE_LUCK;
    public static ForgeConfigSpec.DoubleValue ROGUE_DAMAGE;
    public static ForgeConfigSpec.DoubleValue ROGUE_SPEED;
    public static ForgeConfigSpec.DoubleValue ROGUE_ATTACK_SPEED;

            ;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Common Config (: ").comment("Cool stuff here.").push("Common Config (:");

        builder.push(" ROGUE CONFIG");

        OVERRIDE_ROGUE = builder
                .comment("Override Rogue Class with custom values & bug fixes HIGHLY RECOMMENDED")
                .define("enableTrueInvis", true);


        ROGUE_MAX_HEALTH = builder
                .comment("Max Health for Rogue")
                .defineInRange("rogue_max_health", 16.0F, 1.0F, 100.0F);

        ROGUE_LUCK = builder
                .comment("Luck for Rogue")
                .defineInRange("rogue_luck", 1.0F, 0.0F, 100.0F);

        ROGUE_DAMAGE = builder
                .comment("Attack Damage for Rogue")
                .defineInRange("rogue_damage", 2.0F, 0.0F, 100.0F);

        ROGUE_SPEED = builder
                .comment("Speed for Rogue")
                .defineInRange("rogue_speed", 0.12F, 0.0F, 100.0F);

        ROGUE_ATTACK_SPEED = builder
                .comment("Speed for Rogue")
                .defineInRange("rogue_speed", 4.2, 0.0F, 100.0F);



        COMMON_CONFIG = builder.build();
    }
}

