package com.skydude.dacextras;



import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
    public static ForgeConfigSpec.ConfigValue<String> ROGUE_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> ROGUE_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> ROGUE_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> ROGUE_BOOTS;

    public static ForgeConfigSpec.DoubleValue BOUNTY_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue BOUNTY_LUCK;
    public static ForgeConfigSpec.DoubleValue BOUNTY_DAMAGE;
    public static ForgeConfigSpec.DoubleValue BOUNTY_SPEED;
    public static ForgeConfigSpec.DoubleValue BOUNTY_ATTACK_SPEED;
    public static ForgeConfigSpec.ConfigValue<String> BOUNTY_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> BOUNTY_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> BOUNTY_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> BOUNTY_BOOTS;

    public static ForgeConfigSpec.DoubleValue ONI_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue ONI_LUCK;
    public static ForgeConfigSpec.DoubleValue ONI_DAMAGE;
    public static ForgeConfigSpec.DoubleValue ONI_SPEED;
    public static ForgeConfigSpec.DoubleValue ONI_ATTACK_SPEED;
    public static ForgeConfigSpec.ConfigValue<String> ONI_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> ONI_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> ONI_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> ONI_BOOTS;

    public static ForgeConfigSpec.DoubleValue KAMATH_MOVEMENT_SPEED;
    public static ForgeConfigSpec.DoubleValue KAMATH_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue KAMATH_ARMOR;
    public static ForgeConfigSpec.DoubleValue KAMATH_ATTACK_DAMAGE;
    public static ForgeConfigSpec.DoubleValue KAMATH_FOLLOW_RANGE;
    public static ForgeConfigSpec.DoubleValue KAMATH_KNOCKBACK_RESISTANCE;
    public static ForgeConfigSpec.DoubleValue KAMATH_ATtACK_KNOCKBACK;

    public static ForgeConfigSpec.IntValue FRACTURED_VEX_SPAWN_WEIGHT;
    public static ForgeConfigSpec.IntValue  FRACTURED_VEX_MIN_COUNT;
    public static ForgeConfigSpec.IntValue  FRACTURED_VEX_MAX_COUNT;


    ;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Common Config (: ").comment("Cool stuff here.");

        builder.push(" ROGUE CONFIG");

        OVERRIDE_ROGUE = builder
                .comment("Override Rogue Class with custom values & bug fixes HIGHLY RECOMMENDED")
                .define("enableTrueInvis", true);


        ROGUE_MAX_HEALTH = builder
                .comment("Max Health for Rogue")
                .defineInRange("rogue_max_health", 106.0F, 1.0F, 1000.0F);

        ROGUE_LUCK = builder
                .comment("Luck for Rogue")
                .defineInRange("rogue_luck", 1.0F, 0.0F, 100.0F);

        ROGUE_DAMAGE = builder
                .comment("Attack Damage for Rogue")
                .defineInRange("rogue_damage", 2.0F, 0.0F, 100.0F);

        ROGUE_SPEED = builder
                .comment("Speed for Rogue")
                .defineInRange("rogue_speed", 0.12, 0.0F, 100.0F);

        ROGUE_ATTACK_SPEED = builder
                .comment("Attack Speed for Rogue")
                .defineInRange("rogue_attack_speed", 4.2, 0, 100);

        ROGUE_HELMET = builder
                .comment("Starting Helmet for Rogue")
                .define("rogue_helmet", "dungeons_and_combat:rogue_helmet");
        ROGUE_CHESTPLATE= builder
                .comment("Starting Chestplate for Rogue")
                .define("rogue_chestplate", "dungeons_and_combat:rogue_chestplate");

        ROGUE_LEGGINGS= builder
                .comment("Starting Leggings for Rogue")
                .define("rogue_leggings", "dungeons_and_combat:rogue_leggings");
        ROGUE_BOOTS= builder
                .comment("Starting Boots for Rogue")
                .define("rogue_boots","dungeons_and_combat:rogue_boots");
        builder.pop();
        builder.push(" Bounty Hunter CONFIG");
       BOUNTY_MAX_HEALTH = builder
                .comment("Max Health for bounty hunter")
                .defineInRange("bounty_max_health", 18.0F, 1.0F, 1000.0F);

        BOUNTY_LUCK = builder
                .comment("Luck for Rogue")
                .defineInRange("bounty_luck", 1.0F, 0.0F, 100.0F);

        BOUNTY_DAMAGE = builder
                .comment("Attack Damage for bounty hunter")
                .defineInRange("bounty_damage", 1.6F, 0.0F, 100.0F);

        BOUNTY_SPEED = builder
                .comment("Speed for bounty hunter")
                .defineInRange("bounty_speed", 0.12, 0.0F, 100.0F);

        BOUNTY_ATTACK_SPEED = builder
                .comment("Attack Speed for bounty hunter")
                .defineInRange("bounty_attack_speed", 3.95, 0, 100);

        BOUNTY_HELMET = builder
                .comment("Starting Helmet for bounty hunter")
                .define("bounty_helmet", "dungeons_and_combat:bounty_hunter_helmet");
        BOUNTY_CHESTPLATE= builder
                .comment("Starting Chestplate for bounty hunter")
                .define("bounty_chestplate", "dungeons_and_combat:bounty_hunter_chestplate");
        //Item.byId(Item.getId(DungeonsAndCombatModItems.ROGUE_CHESTPLATE.get())));
        BOUNTY_LEGGINGS= builder
                .comment("Starting Leggings for bounty hunter")
                .define("bounty_leggings", "dungeons_and_combat:bounty_hunter_leggings");
        BOUNTY_BOOTS= builder
                .comment("Starting Boots for bounty hunter")
                .define("bounty_boots","dungeons_and_combat:bounty_hunter_boots");
        builder.pop();
        builder.push(" Oni Slayer CONFIG");
        ONI_MAX_HEALTH = builder
                .comment("Max Health for bounty hunter")
                .defineInRange("oni_health", 18.0F, 1.0F, 1000.0F);

        ONI_LUCK = builder
                .comment("Luck for Rogue")
                .defineInRange("oni_luck", 1.0F, 0.0F, 100.0F);

        ONI_DAMAGE = builder
                .comment("Attack Damage for bounty hunter")
                .defineInRange("oni_damage", 1.2F, 0.0F, 100.0F);

        ONI_SPEED = builder
                .comment("Speed for bounty hunter")
                .defineInRange("oni_speed", 0.095, 0.0F, 100.0F);

        ONI_ATTACK_SPEED = builder
                .comment("Attack Speed for bounty hunter")
                .defineInRange("oni_attack_speed", 4.2, 0, 100);

        ONI_HELMET = builder
                .comment("Starting Helmet for bounty hunter")
                .define("oni_helmet", "dungeons_and_combat:oni_slayer_helmet");
        ONI_CHESTPLATE= builder
                .comment("Starting Chestplate for bounty hunter")
                .define("oni_chestplate", "dungeons_and_combat:oni_slayer_chestplate");
        ONI_LEGGINGS= builder
                .comment("Starting Leggings for bounty hunter")
                .define("oni_leggings", "dungeons_and_combat:oni_slayer_leggings");
        ONI_BOOTS= builder
                .comment("Starting Boots for bounty hunter")
                .define("oni_boots","dungeons_and_combat:oni_slayer_boots");

        builder.pop();
        builder.push(" KAMATH BOSS CONFIG");
        KAMATH_MOVEMENT_SPEED = builder
                .comment("Kamath's movement speed")
                .defineInRange("kamath_movement_speed", 0.30, 0, 100);
        KAMATH_MAX_HEALTH = builder
                .comment("Kamath's Health")
                .defineInRange("kamath_max_health", 500.0, 0, 100000);
        KAMATH_ARMOR = builder
                .comment("Kamath's armor")
                .defineInRange("kamath_armor", 20.0, 0, 1000);
        KAMATH_ATTACK_DAMAGE = builder
                .comment("Kamath's damage ")
                .defineInRange("kamath_attack_damage", 24.0, 0, 1000);
        KAMATH_FOLLOW_RANGE = builder
                .comment("Kamath's Follow Range")
                .defineInRange("kamath_follow_range", 64.0, 0, 1000);
        KAMATH_KNOCKBACK_RESISTANCE = builder
                .comment("Kamath's knockback resistance")
                .defineInRange("kamath_knockback_resistance", 1.0, 0, 1000);
        KAMATH_ATtACK_KNOCKBACK = builder
                .comment("Kamath's attack knockback")
                .defineInRange("kamath_attack_knockback", 1.0, 0, 1000);
        builder.pop();
        builder.push("FRACTURED VEX");
        FRACTURED_VEX_SPAWN_WEIGHT = builder
                .comment("Fractured vex spawn weight")
                .defineInRange("fractured_vex-spawn_weight", 13, 0, 1000);
        FRACTURED_VEX_MIN_COUNT=  builder
                .comment("Fractured vex spawn minimum amount")
                .defineInRange("fractured_vex-spawn_min", 1, 0, 100);
        FRACTURED_VEX_MAX_COUNT=  builder
                .comment("Fractured vex spawn maximum amount")
                .defineInRange("fractured_vex-spawn_max", 3, 0, 100);

        COMMON_CONFIG = builder.build();
    }
}

