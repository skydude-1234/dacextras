package com.skydude.dacextras.mixin;


import net.mcreator.dungeonsandcombat.procedures.RogueClassEverProcedure;
import net.mcreator.dungeonsandcombat.procedures.TitanClassEverProcedure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TitanClassEverProcedure.class, remap = false)

public abstract class OverrideTitanEver {
    @Inject(method = "onPlayerTick", at = @At("HEAD"), cancellable = true)
    private static void overrideonPlayerTick(TickEvent.PlayerTickEvent event, CallbackInfo ci) {
        ci.cancel(); // cancel original method

    }

    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private static void execute(Entity entity, CallbackInfo ci) {
        ci.cancel(); // cancel original method
        if (entity != null) {
            if (entity instanceof ServerPlayer) {
                ServerPlayer _plr0 = (ServerPlayer)entity;
                if (_plr0.level() instanceof ServerLevel && _plr0.getAdvancements().getOrStartProgress(_plr0.server.getAdvancements().getAdvancement(new ResourceLocation("dungeons_and_combat:the_titan"))).isDone()) {
                    {
                        LivingEntity _entity = (LivingEntity)entity;
                        _entity.removeEffect(MobEffects.DIG_SLOWDOWN);
                    }

                    {
                        LivingEntity _entity = (LivingEntity)entity;
                        _entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                    }

                    LivingEntity _entity = (LivingEntity) entity;
                    _entity.removeEffect(MobEffects.WEAKNESS);
                }
            }

        }

    }
}