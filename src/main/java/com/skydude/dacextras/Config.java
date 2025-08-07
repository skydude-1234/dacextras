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
    public static ForgeConfigSpec.DoubleValue ROGUE_ARMOR;
    public static ForgeConfigSpec.ConfigValue<String> ROGUE_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> ROGUE_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> ROGUE_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> ROGUE_BOOTS;

    public static ForgeConfigSpec.DoubleValue BOUNTY_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue BOUNTY_LUCK;
    public static ForgeConfigSpec.DoubleValue BOUNTY_DAMAGE;
    public static ForgeConfigSpec.DoubleValue BOUNTY_SPEED;
    public static ForgeConfigSpec.DoubleValue BOUNTY_ATTACK_SPEED;
    public static ForgeConfigSpec.DoubleValue BOUNTY_ARMOR;
    public static ForgeConfigSpec.ConfigValue<String> BOUNTY_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> BOUNTY_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> BOUNTY_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> BOUNTY_BOOTS;

    public static ForgeConfigSpec.DoubleValue ONI_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue ONI_LUCK;
    public static ForgeConfigSpec.DoubleValue ONI_DAMAGE;
    public static ForgeConfigSpec.DoubleValue ONI_SPEED;
    public static ForgeConfigSpec.DoubleValue ONI_ATTACK_SPEED;
    public static ForgeConfigSpec.DoubleValue ONI_ARMOR;
    public static ForgeConfigSpec.ConfigValue<String> ONI_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> ONI_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> ONI_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> ONI_BOOTS;

    public static ForgeConfigSpec.DoubleValue FORGOTTEN_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue FORGOTTEN_LUCK;
    public static ForgeConfigSpec.DoubleValue FORGOTTEN_DAMAGE;
    public static ForgeConfigSpec.DoubleValue FORGOTTEN_SPEED;
    public static ForgeConfigSpec.DoubleValue FORGOTTEN_ATTACK_SPEED;
    public static ForgeConfigSpec.DoubleValue FORGOTTEN_ARMOR;


    public static ForgeConfigSpec.ConfigValue<String> FORGOTTEN_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> FORGOTTEN_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> FORGOTTEN_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> FORGOTTEN_BOOTS;

    public static ForgeConfigSpec.DoubleValue EXILED_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue EXILED_LUCK;
    public static ForgeConfigSpec.DoubleValue EXILED_DAMAGE;
    public static ForgeConfigSpec.DoubleValue EXILED_SPEED;
    public static ForgeConfigSpec.DoubleValue EXILED_ATTACK_SPEED;
    public static ForgeConfigSpec.DoubleValue EXILED_ARMOR;

    public static ForgeConfigSpec.ConfigValue<String> EXILED_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> EXILED_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> EXILED_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> EXILED_BOOTS;

    public static ForgeConfigSpec.DoubleValue TITAN_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue TITAN_LUCK;
    public static ForgeConfigSpec.DoubleValue TITAN_DAMAGE;
    public static ForgeConfigSpec.DoubleValue TITAN_SPEED;
    public static ForgeConfigSpec.DoubleValue TITAN_ATTACK_SPEED;
    public static ForgeConfigSpec.DoubleValue TITAN_ARMOR;
    public static ForgeConfigSpec.DoubleValue TITAN_ARMOR_TOUGHNESS;

    public static ForgeConfigSpec.ConfigValue<String> TITAN_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> TITAN_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> TITAN_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> TITAN_BOOTS;

    public static ForgeConfigSpec.DoubleValue VAGABOND_MAX_HEALTH;
    public static ForgeConfigSpec.DoubleValue VAGABOND_LUCK;
    public static ForgeConfigSpec.DoubleValue VAGABOND_DAMAGE;
    public static ForgeConfigSpec.DoubleValue VAGABOND_SPEED;
    public static ForgeConfigSpec.DoubleValue VAGABOND_ATTACK_SPEED;
    public static ForgeConfigSpec.DoubleValue VAGABOND_ARMOR;

    public static ForgeConfigSpec.ConfigValue<String> VAGABOND_HELMET;
    public static ForgeConfigSpec.ConfigValue<String> VAGABOND_CHESTPLATE;
    public static ForgeConfigSpec.ConfigValue<String> VAGABOND_LEGGINGS;
    public static ForgeConfigSpec.ConfigValue<String> VAGABOND_BOOTS;

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
                .defineInRange("rogue_max_health", 16.0F, 1.0F, 1000.0F);

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
        ROGUE_ARMOR = builder
                .comment("Armorfor Rogue")
                .defineInRange("rogue_armor", 0.0F, 0.0, 100);

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
        BOUNTY_ARMOR = builder
                .comment("Armor for bounty hunter")
                .defineInRange("bounty_armor", 0.0F, 0.0, 100);


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
                .comment("Max Health for oni slayer")
                .defineInRange("oni_health", 18.0F, 1.0F, 1000.0F);

        ONI_LUCK = builder
                .comment("Luck for oni slayer")
                .defineInRange("oni_luck", 1.0F, 0.0F, 100.0F);

        ONI_DAMAGE = builder
                .comment("Attack Damage for oni slayer")
                .defineInRange("oni_damage", 1.2F, 0.0F, 100.0F);

        ONI_SPEED = builder
                .comment("Speed for oni slayer")
                .defineInRange("oni_speed", 0.095, 0.0F, 100.0F);

        ONI_ATTACK_SPEED = builder
                .comment("Attack Speed for oni slayer")
                .defineInRange("oni_attack_speed", 4.2, 0, 100);
        ONI_ARMOR = builder
                .comment("Armor for oni slayer")
                .defineInRange("oni_armor", 0.0F, 0, 100);


        ONI_HELMET = builder
                .comment("Starting Helmet for oni slayer")
                .define("oni_helmet", "dungeons_and_combat:oni_slayer_helmet");
        ONI_CHESTPLATE= builder
                .comment("Starting Chestplate for oni slayer")
                .define("oni_chestplate", "dungeons_and_combat:oni_slayer_chestplate");
        ONI_LEGGINGS= builder
                .comment("Starting Leggings for oni slayer")
                .define("oni_leggings", "dungeons_and_combat:oni_slayer_leggings");
        ONI_BOOTS= builder
                .comment("Starting Boots for oni slayer")
                .define("oni_boots","dungeons_and_combat:oni_slayer_boots");

        builder.pop();
        builder.push(" Forgotten Knight CONFIG");
        FORGOTTEN_MAX_HEALTH = builder
                .comment("Max Health for Forgotten Knight")
                .defineInRange("FORGOTTEN_health", 20.0F, 1.0F, 1000.0F);

        FORGOTTEN_LUCK = builder
                .comment("Luck for Forgotten Knight")
                .defineInRange("FORGOTTEN_luck", 0.0F, 0.0F, 100.0F);

        FORGOTTEN_DAMAGE = builder
                .comment("Attack Damage for Forgotten Knight")
                .defineInRange("FORGOTTEN_damage", 1.4F, 0.0F, 100.0F);
        FORGOTTEN_SPEED = builder
                .comment("Speed for Forgotten Knight")
                .defineInRange("FORGOTTEN_speed", 0.095, 0.0F, 100.0F);

        FORGOTTEN_ATTACK_SPEED = builder
                .comment("Attack Speed for Forgotten Knight")
                .defineInRange("FORGOTTEN_attack_speed", 3.95, 0, 100);
        FORGOTTEN_ARMOR = builder
                .comment("Armor for Forgotten Knight")
                .defineInRange("FORGOTTEN_armor", 2.0F, 0.0, 100.0F);

        FORGOTTEN_HELMET = builder
                .comment("Starting Helmet for Forgotten Knight")
                .define("FORGOTTEN_helmet", "dungeons_and_combat:forgotten_knight_helmet");
        FORGOTTEN_CHESTPLATE= builder
                .comment("Starting Chestplate for Forgotten Knight")
                .define("FORGOTTEN_chestplate", "dungeons_and_combat:forgotten_knight_chestplate");
        FORGOTTEN_LEGGINGS= builder
                .comment("Starting Leggings for Forgotten Knight")
                .define("FORGOTTEN_leggings", "dungeons_and_combat:forgotten_knight_leggings");
        FORGOTTEN_BOOTS= builder
                .comment("Starting Boots for Forgotten Knight")
                .define("FORGOTTEN_boots","dungeons_and_combat:forgotten_knight_boots");

        builder.pop();
        builder.push("  EXILED CONFIG");
        EXILED_MAX_HEALTH = builder
                .comment("Max Health for Exiled")
                .defineInRange("EXILED_health", 18.0F, 1.0F, 1000.0F);

        EXILED_LUCK = builder
                .comment("Luck for Exiled")
                .defineInRange("EXILED_luck", 1.0F, 0.0F, 100.0F);

        EXILED_DAMAGE = builder
                .comment("Attack Damage for Exiled")
                .defineInRange("EXILED_damage", 1.0F, 0.0F, 100.0F);
        EXILED_SPEED = builder
                .comment("Speed for Exiled")
                .defineInRange("EXILED_speed", 0.11, 0.0F, 100.0F);

        EXILED_ATTACK_SPEED = builder
                .comment("Attack Speed for Exiled")
                .defineInRange("EXILED_attack_speed", 3.95, 0, 100);
        EXILED_ARMOR = builder
                .comment("Armor for Exiled")
                .defineInRange("EXILED_armor", 0.0F, 0.0, 100.0F);

        EXILED_HELMET = builder
                .comment("Starting Helmet for Exiled")
                .define("EXILED_helmet", "dungeons_and_combat:exiled_helmet");
        EXILED_CHESTPLATE= builder
                .comment("Starting Chestplate for Exiled")
                .define("EXILED_chestplate", "dungeons_and_combat:exiled_chestplate");
        EXILED_LEGGINGS= builder
                .comment("Starting Leggings for Exiled")
                .define("EXILED_leggings", "minecraft:air");
        EXILED_BOOTS= builder
                .comment("Starting Boots for Exiled")
                .define("EXILED_BOOTS","minecraft:air");
        builder.pop();
        builder.push("  TITAN CONFIG");
        TITAN_MAX_HEALTH = builder
                .comment("Max Health for Titan")
                .defineInRange("TITAN_health", 28.0F, 1.0F, 1000.0F);

        TITAN_LUCK = builder
                .comment("Luck for Titan")
                .defineInRange("TITAN_luck", 0.0F, 0.0F, 100.0F);

        TITAN_DAMAGE = builder
                .comment("Attack Damage for Titan")
                .defineInRange("TITAN_damage", 1.0F, 0.0F, 100.0F);
        TITAN_SPEED = builder
                .comment("Speed for Titan")
                .defineInRange("TITAN_speed", 0.085, 0.0F, 100.0F);

        TITAN_ATTACK_SPEED = builder
                .comment("Attack Speed for Titan")
                .defineInRange("TITAN_attack_speed", 3.85, 0, 100);
        TITAN_ARMOR = builder
                .comment("Armor for Titan")
                .defineInRange("TITAN_armor", 2.0F, 0.0, 100.0F);
        TITAN_ARMOR_TOUGHNESS = builder
                .comment("Armor for Titan")
                .defineInRange("TITAN_armor", 0.1, 0.0, 100.0F);

        TITAN_HELMET = builder
                .comment("Starting Helmet for Titan")
                .define("TITAN_helmet", "dungeons_and_combat:titan_helmet");
        TITAN_CHESTPLATE= builder
                .comment("Starting Chestplate for Titan")
                .define("TITAN_chestplate", "dungeons_and_combat:titan_chestplate");
        TITAN_LEGGINGS= builder
                .comment("Starting Leggings for Titan")
                .define("TITAN_leggings", "dungeons_and_combat:titan_leggings");
        TITAN_BOOTS= builder
                .comment("Starting Boots for Titan")
                .define("TITAN_boots","dungeons_and_combat:titan_boots");
        builder.pop();
        builder.push("VAGABOND CONFIG");

        VAGABOND_MAX_HEALTH = builder
                .comment("Max Health for vagabond")
                .defineInRange("vagabond_health", 20.0F, 1.0F, 1000.0F);

        VAGABOND_LUCK = builder
                .comment("Luck for vagabond")
                .defineInRange("vagabond_luck", 0.0F, 0.0F, 100.0F);

        VAGABOND_DAMAGE = builder
                .comment("Attack Damage for vagabond")
                .defineInRange("vagabond_damage", 1.0F, 0.0F, 100.0F);

        VAGABOND_SPEED = builder
                .comment("Speed for vagabond ")
                .defineInRange("vagabond_speed", 0.1, 0.0F, 100.0F);

        VAGABOND_ATTACK_SPEED = builder
                .comment("Attack Speed for vagabond")
                .defineInRange("vagabond_attack_speed", 4.0, 0, 100);
        VAGABOND_ATTACK_SPEED = builder
                .comment("Attack Speed for vagabond ")
                .defineInRange("vagabond_attack_speed", 4.0, 0, 100);
        VAGABOND_ARMOR = builder
                .comment("Attack Speed for vagabond ")
                .defineInRange("vagabond_attack_speed", 0.0F, 0.0, 100);

        VAGABOND_HELMET = builder
                .comment("Starting Helmet for vagabond")
                .define("vagabond_helmet", "dungeons_and_combat:vagabond_helmet");
        VAGABOND_CHESTPLATE= builder
                .comment("Starting Chestplate for vagabond")
                .define("vagabond_chestplate", "dungeons_and_combat:vagabond_chestplate");
        VAGABOND_LEGGINGS= builder
                .comment("Starting Leggings for vagabond")
                .define("vagabond_leggings", "dungeons_and_combat:vagabond_leggings");
        VAGABOND_BOOTS= builder
                .comment("Starting Boots for vagabond")
                .define("vagabond_boots","dungeons_and_combat:vagabond_boots");

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

