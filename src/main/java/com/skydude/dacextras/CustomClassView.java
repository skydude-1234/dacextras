//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.skydude.dacextras;

import java.util.Comparator;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class CustomClassView {
    public static Entity execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      {
            if (entity instanceof Player) {
                Player _player = (Player)entity;
                _player.getInventory().armor.set(3, new ItemStack((ItemLike)DungeonsAndCombatModItems.BOUNTY_HUNTER_HELMET.get()));
                _player.getInventory().setChanged();
            } else if (entity instanceof LivingEntity) {
                LivingEntity _living = (LivingEntity)entity;
                _living.setItemSlot(EquipmentSlot.HEAD, new ItemStack((ItemLike)DungeonsAndCombatModItems.BOUNTY_HUNTER_HELMET.get()));
            }

            if (entity instanceof Player) {
                Player _player = (Player)entity;
                _player.getInventory().armor.set(2, new ItemStack((ItemLike)DungeonsAndCombatModItems.BOUNTY_HUNTER_CHESTPLATE.get()));
                _player.getInventory().setChanged();
            } else if (entity instanceof LivingEntity) {
                LivingEntity _living = (LivingEntity)entity;
                _living.setItemSlot(EquipmentSlot.CHEST, new ItemStack((ItemLike)DungeonsAndCombatModItems.BOUNTY_HUNTER_CHESTPLATE.get()));
            }

            if (entity instanceof Player) {
                Player _player = (Player)entity;
                _player.getInventory().armor.set(1, new ItemStack((ItemLike)DungeonsAndCombatModItems.BOUNTY_HUNTER_LEGGINGS.get()));
                _player.getInventory().setChanged();
            } else if (entity instanceof LivingEntity) {
                LivingEntity _living = (LivingEntity)entity;
                _living.setItemSlot(EquipmentSlot.LEGS, new ItemStack((ItemLike)DungeonsAndCombatModItems.BOUNTY_HUNTER_LEGGINGS.get()));
            }

            if (entity instanceof Player) {
                Player _player = (Player)entity;
                _player.getInventory().armor.set(0, new ItemStack((ItemLike)DungeonsAndCombatModItems.BOUNTY_HUNTER_BOOTS.get()));
                _player.getInventory().setChanged();
            } else if (entity instanceof LivingEntity) {
                LivingEntity _living = (LivingEntity)entity;
                _living.setItemSlot(EquipmentSlot.FEET, new ItemStack((ItemLike)DungeonsAndCombatModItems.BOUNTY_HUNTER_BOOTS.get()));
            }

            if (entity instanceof LivingEntity) {
                LivingEntity _entity = (LivingEntity)entity;
                ItemStack _setstack = (new ItemStack((ItemLike)DungeonsAndCombatModItems.EXECUTIONER_AXE.get())).copy();
                _setstack.setCount(1);
                _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                if (_entity instanceof Player) {
                    Player _player = (Player)_entity;
                    _player.getInventory().setChanged();
                }
            }

            if (entity instanceof LivingEntity) {
                LivingEntity _entity = (LivingEntity)entity;
                ItemStack _setstack = (new ItemStack((ItemLike)DungeonsAndCombatModItems.OLD_SHIELD.get())).copy();
                _setstack.setCount(1);
                _entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
                if (_entity instanceof Player) {
                    Player _player = (Player)_entity;
                    _player.getInventory().setChanged();
                }
            }

            return entity;
        }
    }
}
