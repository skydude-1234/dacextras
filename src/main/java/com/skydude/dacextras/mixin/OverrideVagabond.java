package com.skydude.dacextras.mixin;

import com.mojang.logging.LogUtils;
import com.skydude.dacextras.Config;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.mcreator.dungeonsandcombat.procedures.RogueChoosedProcedure;
import net.mcreator.dungeonsandcombat.procedures.VagabondChoosedProcedure;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(value = VagabondChoosedProcedure.class, remap = false)
public abstract class OverrideVagabond {
    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    //  @Inject(method = "execute(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"), cancellable = true)

    private static void overrideExecute(Entity entity, CallbackInfo ci ) {
        ci.cancel();
        System.out.println("Mixin OverrideRogue.execute() triggered with entity: " + entity);
        if (!(entity instanceof Player player)) {
            System.out.println("entity is not instanceof Player player");
            return;
        } else{
            player.closeContainer();
        }

        if (entity instanceof ServerPlayer serverPlayer) {
            Advancement adv = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("dungeons_and_combat:origin"));
            AdvancementProgress _ap = serverPlayer.getAdvancements().getOrStartProgress(adv);

            if (!_ap.isDone()) {
                for(String criteria : _ap.getRemainingCriteria()) {
                    serverPlayer.getAdvancements().award(adv, criteria);
                }
            }
        }
        if (entity instanceof ServerPlayer serverPlayer) {

            Advancement _adv = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("dungeons_and_combat:the_vagabond"));
            assert _adv != null;

            AdvancementProgress _ap = serverPlayer.getAdvancements().getOrStartProgress(_adv);
            if (!_ap.isDone()) {
                for(String criteria : _ap.getRemainingCriteria()) {
                    serverPlayer.getAdvancements().award(_adv, criteria);
                }
            }
        }

        Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(Config.VAGABOND_MAX_HEALTH.get());
        // set the health to amx health so no glitches happen
        player.setHealth(player.getMaxHealth());

        Objects.requireNonNull(player.getAttribute(Attributes.LUCK)).setBaseValue(Config.VAGABOND_LUCK.get());
        Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(Config.VAGABOND_DAMAGE.get());
        Objects.requireNonNull(player.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(Config.VAGABOND_SPEED.get());
        Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(Config.VAGABOND_ATTACK_SPEED.get());


        if(player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.VAGABOND_HELMET.get())))));
        }

        if(player.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
            player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.VAGABOND_CHESTPLATE.get())))));
        }


        if(player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
            player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.VAGABOND_LEGGINGS.get())))));
        }

        if(player.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
            player.setItemSlot(EquipmentSlot.FEET, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.VAGABOND_BOOTS.get())))));
        }

//        if(player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()){
//
//            player.setItemInHand(InteractionHand.MAIN_HAND, (new ItemStack((ItemLike)DungeonsAndCombatModItems.DAGGER.get())).copy());
//        }
//        if(player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
//            player.setItemInHand(InteractionHand.OFF_HAND, (new ItemStack((ItemLike) DungeonsAndCombatModItems.DAGGER.get())).copy());
//        }

        ci.cancel(); // cancel original method
    }





}
