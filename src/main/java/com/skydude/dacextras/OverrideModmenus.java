package com.skydude.dacextras;

import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMenus;
import net.mcreator.dungeonsandcombat.world.inventory.RogueClassGUIMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMenus.REGISTRY;


import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OverrideModmenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, dacextras.MODID);

    public static final RegistryObject<MenuType<CustomClassGuiMenu>> CUSTOM_CLASS_GUI =
            MENUS.register("custom_class_gui",
                    () -> IForgeMenuType.create(CustomClassGuiMenu::new));
}


