package com.skydude.dacextras.mixin;

import com.skydude.dacextras.Config;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.mcreator.dungeonsandcombat.procedures.BountyHunterChoosedProcedure;
import net.mcreator.dungeonsandcombat.procedures.OniSlayerChoosedProcedure;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;


@Mixin(value = OniSlayerChoosedProcedure.class, remap = false)
public class OverrideOniSlayer {


    @Inject(method = "execute(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    private static void overrideExecute(Entity entity, CallbackInfo ci) {
        {
            if (entity != null) {
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
                    Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("dungeons_and_combat:the_bounty_hunter"));
                    AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                    if (!_ap.isDone()) {
                        for(String criteria : _ap.getRemainingCriteria()) {
                            _player.getAdvancements().award(_adv, criteria);
                        }
                    }
                }
                LivingEntity living = (LivingEntity) entity;
                living.getAttribute(Attributes.LUCK).setBaseValue((double)1.0F);
                living.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)18.0F);
                living.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(1.6);
                living.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.12);
                living.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(3.95);
                // SET HEALTH TO MAX TO AVOID BUGS
                living.setHealth(living.getMaxHealth());
                if (entity instanceof Player) {
                    Player _player = (Player)entity;
                    _player.getInventory().armor.set(3, new ItemStack((ItemLike)DungeonsAndCombatModItems.ONI_SLAYER_HELMET.get()));
                    _player.getInventory().setChanged();
                }

                if (entity instanceof Player) {
                    Player _player = (Player)entity;
                    _player.getInventory().armor.set(2, new ItemStack((ItemLike)DungeonsAndCombatModItems.ONI_SLAYER_CHESTPLATE.get()));
                    _player.getInventory().setChanged();
                }

                if (entity instanceof Player) {
                    Player _player = (Player)entity;
                    _player.getInventory().armor.set(1, new ItemStack((ItemLike)DungeonsAndCombatModItems.ONI_SLAYER_LEGGINGS.get()));
                    _player.getInventory().setChanged();
                }

                if (entity instanceof Player) {
                    Player _player = (Player)entity;
                    _player.getInventory().armor.set(0, new ItemStack((ItemLike)DungeonsAndCombatModItems.ONI_SLAYER_BOOTS.get()));
                    _player.getInventory().setChanged();
                }

                if (entity instanceof LivingEntity) {
                    LivingEntity _entity = (LivingEntity)entity;
                    ItemStack _setstack = (new ItemStack((ItemLike)DungeonsAndCombatModItems.LONG_KATANA.get())).copy();
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

            }
        }
    }
}
