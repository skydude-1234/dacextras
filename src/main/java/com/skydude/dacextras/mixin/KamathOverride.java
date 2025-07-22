package com.skydude.dacextras.mixin;

import net.mcreator.dungeonsandcombat.entity.KamathEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = KamathEntity.class, remap = false)
public class KamathOverride {
    @Inject(method = "createAttributes", at = @At("HEAD"), cancellable = true)

    private static void overridecreateAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        AttributeSupplier.Builder builder = Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.30)
                .add(Attributes.MAX_HEALTH, 500.0)
                .add(Attributes.ARMOR, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 24.0)
                .add(Attributes.FOLLOW_RANGE, 64.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0);

        cir.setReturnValue(builder);
    }

}
