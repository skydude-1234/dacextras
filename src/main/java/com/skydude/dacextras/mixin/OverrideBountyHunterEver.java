package com.skydude.dacextras.mixin;


import net.mcreator.dungeonsandcombat.procedures.BountyHunterClassEverProcedure;
import net.mcreator.dungeonsandcombat.procedures.RogueClassEverProcedure;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BountyHunterClassEverProcedure.class, remap = false)

public abstract class OverrideBountyHunterEver {
    @Inject(method = "onPlayerTick", at = @At("HEAD"), cancellable = true)
    private static void overrideonPlayerTick(TickEvent.PlayerTickEvent event, CallbackInfo ci) {
        ci.cancel(); // cancel original method

    }

    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private static void execute(Entity entity, CallbackInfo ci) {
        ci.cancel(); // cancel original method

    }
}