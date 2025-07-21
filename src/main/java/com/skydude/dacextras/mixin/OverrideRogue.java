package com.skydude.dacextras.mixin;

import com.mojang.logging.LogUtils;
import com.skydude.dacextras.AttributeModifiers;
import com.skydude.dacextras.Config;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.mcreator.dungeonsandcombat.procedures.RogueChoosedProcedure;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.UUID;

@Mixin(value = RogueChoosedProcedure.class, remap = false)

public abstract class OverrideRogue {




    //  @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
      @Inject(method = "execute(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"), cancellable = true)

  private static void overrideExecute(Entity entity, CallbackInfo ci) {
          System.out.println("Mixin OverrideRogue.execute() triggered with entity: " + entity);

          if(entity == null){
            LogUtils.getLogger().info("entityIS NULLLnull");
        }
        if (entity != null) {
            LogUtils.getLogger().info("entity!= null");
            if (entity instanceof Player) {
                Player _player = (Player)entity;
                _player.closeContainer();
            }

            if (entity instanceof ServerPlayer) {
                ServerPlayer _player = (ServerPlayer)entity;
                Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("dungeons_and_combat:origin"));
                AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                if (!_ap.isDone()) {
                    for(String criteria : _ap.getRemainingCriteria()) {
                        _player.getAdvancements().award(_adv, criteria);
                    }
                }
            }

            if (entity instanceof ServerPlayer) {
                ServerPlayer _player = (ServerPlayer)entity;
                Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("dungeons_and_combat:the_rogue"));
                assert _adv != null;

                AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                LogUtils.getLogger().info("RANDOM ADV VER THING SHULD WORK LOLZ NULLLnull");
                if (!_ap.isDone()) {
                    for(String criteria : _ap.getRemainingCriteria()) {
                        _player.getAdvancements().award(_adv, criteria);
                    }
                }
            }
            LivingEntity living = (LivingEntity) entity;







            Objects.requireNonNull(living.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(Config.ROGUE_MAX_HEALTH.get());
            // set the health to amx health so no glitches happen
            living.setHealth(living.getMaxHealth());
            LogUtils.getLogger().info("HEALTH HAS HEALTHTED???? NULLLnull");
            Objects.requireNonNull(living.getAttribute(Attributes.LUCK)).setBaseValue(1.0F);
            Objects.requireNonNull(living.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue((double)2.0F);
            Objects.requireNonNull(living.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.12);
            Objects.requireNonNull(living.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(4.2);
            if (entity instanceof Player) {
                Player _player = (Player)entity;
                _player.getInventory().armor.set(3, new ItemStack((ItemLike) DungeonsAndCombatModItems.ROGUE_HELMET.get()));
                _player.getInventory().setChanged();
            } else if (entity instanceof LivingEntity) {
                LivingEntity _living = (LivingEntity)entity;
                _living.setItemSlot(EquipmentSlot.HEAD, new ItemStack((ItemLike)DungeonsAndCombatModItems.ROGUE_HELMET.get()));
            }

            if (entity instanceof Player) {
                Player _player = (Player)entity;
                if(_player.getItemBySlot(EquipmentSlot.CHEST) == ItemStack.EMPTY) {
                    _player.getInventory().armor.set(2, new ItemStack((ItemLike) DungeonsAndCombatModItems.EXILED_CHESTPLATE.get()));
                    _player.getInventory().setChanged();
                    LogUtils.getLogger().info("exiled is exiled");
                }
            } else if (entity instanceof LivingEntity) {
                LivingEntity _living = (LivingEntity)entity;
                _living.setItemSlot(EquipmentSlot.CHEST, new ItemStack((ItemLike)DungeonsAndCombatModItems.ROGUE_CHESTPLATE.get()));
                LogUtils.getLogger().info("exiled is rogue");
            }

            if (entity instanceof Player) {
                Player _player = (Player)entity;
                _player.getInventory().armor.set(1, new ItemStack((ItemLike)DungeonsAndCombatModItems.ROGUE_LEGGINGS.get()));
                _player.getInventory().setChanged();
            } else if (entity instanceof LivingEntity) {
                LivingEntity _living = (LivingEntity)entity;
                _living.setItemSlot(EquipmentSlot.LEGS, new ItemStack((ItemLike)DungeonsAndCombatModItems.ROGUE_LEGGINGS.get()));
            }

            if (entity instanceof Player) {
                Player _player = (Player)entity;
                _player.getInventory().armor.set(0, new ItemStack((ItemLike)DungeonsAndCombatModItems.ROGUE_BOOTS.get()));
                _player.getInventory().setChanged();
            } else if (entity instanceof LivingEntity) {
                LivingEntity _living = (LivingEntity)entity;
                _living.setItemSlot(EquipmentSlot.FEET, new ItemStack((ItemLike)DungeonsAndCombatModItems.ROGUE_BOOTS.get()));
            }

            if (entity instanceof LivingEntity) {
                LivingEntity _entity = (LivingEntity)entity;
                ItemStack _setstack = (new ItemStack((ItemLike)DungeonsAndCombatModItems.DAGGER.get())).copy();
                _setstack.setCount(1);
                _entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                if (_entity instanceof Player) {
                    Player _player = (Player)_entity;
                    _player.getInventory().setChanged();
                }
            }

            if (entity instanceof LivingEntity) {
                LivingEntity _entity = (LivingEntity)entity;
                ItemStack _setstack = (new ItemStack((ItemLike)DungeonsAndCombatModItems.DAGGER.get())).copy();
                _setstack.setCount(1);
                _entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
                if (_entity instanceof Player) {
                    Player _player = (Player)_entity;
                    _player.getInventory().setChanged();
                }
            }

        }
          ci.cancel(); // cancel original method
    }





}
