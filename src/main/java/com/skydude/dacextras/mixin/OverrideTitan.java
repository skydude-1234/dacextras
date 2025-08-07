package com.skydude.dacextras.mixin;

import com.skydude.dacextras.Config;
import com.skydude.dacextras.dacextras;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModItems;
import net.mcreator.dungeonsandcombat.procedures.ExiledChoosedProcedure;
import net.mcreator.dungeonsandcombat.procedures.TitanChoosedProcedure;
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

@Mixin(value = TitanChoosedProcedure.class, remap = false)
public abstract class OverrideTitan {
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

              Advancement _adv = serverPlayer.server.getAdvancements().getAdvancement(new ResourceLocation("dungeons_and_combat:the_titan"));
              assert _adv != null;

              AdvancementProgress _ap = serverPlayer.getAdvancements().getOrStartProgress(_adv);
              if (!_ap.isDone()) {
                  for(String criteria : _ap.getRemainingCriteria()) {
                      serverPlayer.getAdvancements().award(_adv, criteria);
                  }
              }
          }

          dacextras.hhealth = Config.TITAN_MAX_HEALTH.get();
          dacextras.lluck = Config.TITAN_LUCK.get();
          dacextras.sstrength = Config.TITAN_DAMAGE.get();
          dacextras.sspeed = Config.TITAN_SPEED.get();
          dacextras.aattack_speed = Config.TITAN_ATTACK_SPEED.get();
          dacextras.aarmor = Config.TITAN_ARMOR.get();
          dacextras.ttoughness = Config.TITAN_ARMOR_TOUGHNESS.get();

          LivingEntity living = ((LivingEntity) entity);
          Objects.requireNonNull(living.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(Config.TITAN_MAX_HEALTH.get());
          // set the health to amx health so no glitches happen
          living.setHealth(living.getMaxHealth());

          Objects.requireNonNull(living.getAttribute(Attributes.LUCK)).setBaseValue(Config.TITAN_LUCK.get());
          Objects.requireNonNull(living.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(Config.TITAN_DAMAGE.get());
          Objects.requireNonNull(living.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(Config.TITAN_SPEED.get());
          Objects.requireNonNull(living.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(Config.TITAN_ATTACK_SPEED.get());
          Objects.requireNonNull(living.getAttribute(Attributes.ARMOR)).setBaseValue(Config.TITAN_ARMOR.get());
          Objects.requireNonNull(living.getAttribute(Attributes.ARMOR_TOUGHNESS)).setBaseValue(Config.TITAN_ARMOR_TOUGHNESS.get());


          if(player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
              player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.EXILED_HELMET.get())))));
    }

          if(player.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
              player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.EXILED_CHESTPLATE.get())))));
          }


          if(player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
              player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.EXILED_LEGGINGS.get())))));
}

          if(player.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
              player.setItemSlot(EquipmentSlot.FEET, new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Config.EXILED_BOOTS.get())))));
}

        if(player.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()){

            player.setItemInHand(InteractionHand.MAIN_HAND, (new ItemStack((ItemLike)DungeonsAndCombatModItems.HEAVY_HAMMER.get())).copy());
          }
        if(player.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()) {
            player.setItemInHand(InteractionHand.OFF_HAND, (new ItemStack((ItemLike) DungeonsAndCombatModItems.HEAVY_SHIELD.get())).copy());
        }

          ci.cancel(); // cancel original method
    }





}
