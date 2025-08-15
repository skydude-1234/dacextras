package com.skydude.dacextras.mixin;

import com.google.gson.JsonObject;
import com.skydude.dacextras.*;
import net.mcreator.dungeonsandcombat.network.ForgottenKnightClassGUIButtonMessage;
import net.mcreator.dungeonsandcombat.network.RogueClassGUIButtonMessage;
import net.mcreator.dungeonsandcombat.procedures.*;
import net.mcreator.dungeonsandcombat.world.inventory.RogueClassGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.FMLPaths;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import static com.skydude.dacextras.dacextras.*;
import static net.minecraft.util.datafix.fixes.BlockEntitySignTextStrictJsonFix.GSON;
import com.skydude.dacextras.dacextras;

@Mixin(value = ForgottenKnightClassGUIButtonMessage.class, remap = false)
public class OverrideForgottenKnightMessage {

    @Inject(method = "handleButtonAction", at = @At("HEAD"), cancellable = true)
    private static void overrideHandleButtonAction(Player entity, int buttonID, int x, int y, int z, CallbackInfo ci){

        ci.cancel();
        Level world = entity.level();
        HashMap guistate = RogueClassGUIMenu.guistate;
        if (world.hasChunkAt(new BlockPos(x, y, z))) {
            if (buttonID == 0) {
                VagabondNextProcedure.execute(world, (double)x, (double)y, (double)z, entity);
                dacextras.NUMBER = -1;
            }

            if (buttonID == 1) {
                if (customclass) {
                    dacextras.NUMBER = (dacextras.CLASS_IDS.size() - 1);
                    System.out.println("fgknight triggered" + dacextras.NUMBER);
                    String class_id = dacextras.CLASS_IDS.get((int) dacextras.NUMBER);
                    CustomClassesNextProcedure.execute(world, (double) x, (double) y, (double) z, entity, class_id);
                } else {
                    RogueNextProcedure.execute(world, (double)x, (double)y, (double)z, entity);
                }
            }

            if (buttonID == 2) {
                ForgottenKnightChoosedProcedure.execute(entity);
                dacextras.NUMBER = -1;
            }

        }


    }
}