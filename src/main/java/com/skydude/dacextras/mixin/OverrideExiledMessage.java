package com.skydude.dacextras.mixin;


import com.skydude.dacextras.dacextras;
import net.mcreator.dungeonsandcombat.network.ExliedClassGUIButtonMessage;
import net.mcreator.dungeonsandcombat.network.ForgottenKnightClassGUIButtonMessage;
import net.mcreator.dungeonsandcombat.procedures.*;
import net.mcreator.dungeonsandcombat.world.inventory.ExliedClassGUIMenu;
import net.mcreator.dungeonsandcombat.world.inventory.ForgottenKnightClassGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.security.cert.LDAPCertStoreParameters;
import java.util.HashMap;

@Mixin(value = ExliedClassGUIButtonMessage.class, remap = false)
public class OverrideExiledMessage {

    @Inject(method = "handleButtonAction", at = @At("HEAD"), cancellable = true)
    private static void overrideHandleButtonAction(Player entity, int buttonID, int x, int y, int z, CallbackInfo ci) {
        ci.cancel();

        Level world = entity.level();
        HashMap guistate = ExliedClassGUIMenu.guistate;
        if (world.hasChunkAt(new BlockPos(x, y, z))) {
            if (buttonID == 0) {
                dacextras.NUMBER = -1;
                RogueNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
                dacextras.NUMBER = -1;
            }

            if (buttonID == 1) {
                BountyHunterNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
            }

            if (buttonID == 2) {
                ExiledChoosedProcedure.execute(entity);
            }

        }
    }
}