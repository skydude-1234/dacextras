package com.skydude.dacextras;



import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static final ForgeConfigSpec COMMON_CONFIG;
    public static final ForgeConfigSpec.BooleanValue OVERRIDE_ROGUE;

            ;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Common Config (: ").comment("Cool stuff here.").push("Common Config (:");



        OVERRIDE_ROGUE = builder
                .comment("True Invisibility for Rogue")
                .define("enableTrueInvis", true);
        builder.pop();




        COMMON_CONFIG = builder.build();
    }
}

