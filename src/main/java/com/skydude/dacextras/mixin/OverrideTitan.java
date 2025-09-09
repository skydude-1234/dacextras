package com.skydude.dacextras.mixin;

import com.skydude.dacextras.Config;
import com.skydude.dacextras.CustomClasses;
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



          LivingEntity living = ((LivingEntity) entity);
          player.getPersistentData().putString("dacextras.class_id", "Titan");
          player.getPersistentData().putDouble("dacextras.maxhealth",Config.TITAN_MAX_HEALTH.get());
          player.getPersistentData().putDouble("dacextras.luck", Config.TITAN_LUCK.get());
          player.getPersistentData().putDouble("dacextras.strength", Config.TITAN_DAMAGE.get());
          player.getPersistentData().putDouble("dacextras.speed", Config.TITAN_SPEED.get());
          player.getPersistentData().putDouble("dacextras.attackspeed", Config.TITAN_ATTACK_SPEED.get());
          player.getPersistentData().putDouble("dacextras.toughness", Config.TITAN_ARMOR_TOUGHNESS.get());
          player.getPersistentData().putDouble("dacextras.armor", Config.TITAN_ARMOR.get());

          CustomClasses.class_attributes((Player) living);

          // set the health to amx health so no glitches happen
          living.setHealth(living.getMaxHealth());



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
