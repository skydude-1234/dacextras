package com.skydude.dacextras.mixin;


import net.mcreator.dungeonsandcombat.procedures.BountyHunterClassEverProcedure;
import net.mcreator.dungeonsandcombat.procedures.ForgottenKnightClassEverProcedure;
import net.mcreator.dungeonsandcombat.procedures.OniSlayerClassEverProcedure;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ForgottenKnightClassEverProcedure.class, remap = false)

public abstract class OverrideForgottenKnightever {
    @Inject(method = "onPlayerTick", at = @At("HEAD"), cancellable = true)
    private static void overrideonPlayerTick(TickEvent.PlayerTickEvent event, CallbackInfo ci) {
        ci.cancel(); // cancel original method

    }

    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private static void execute(Entity entity, CallbackInfo ci) {
        ci.cancel(); // cancel original method
        LivingEntity living = (LivingEntity)entity;
        var health = living.getHealth();
        if (health <= (living.getMaxHealth() / 2 ) && entity instanceof LivingEntity _entity) {
            if (!_entity.level().isClientSide()) {
                _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 40, 1));
            }
        }
        ci.cancel();
    }
}