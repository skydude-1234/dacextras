package com.skydude.dacextras.mixin;

import com.google.gson.JsonObject;
import com.skydude.dacextras.*;
import net.mcreator.dungeonsandcombat.network.RogueClassGUIButtonMessage;
import net.mcreator.dungeonsandcombat.procedures.ExliedNextProcedure;
import net.mcreator.dungeonsandcombat.procedures.ForgottenKnightNextProcedure;
import net.mcreator.dungeonsandcombat.procedures.RogueChoosedProcedure;
import net.mcreator.dungeonsandcombat.procedures.RogueClassEverProcedure;
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

import static com.skydude.dacextras.dacextras.doesitexist;
import static com.skydude.dacextras.dacextras.jsonPath;
import static net.minecraft.util.datafix.fixes.BlockEntitySignTextStrictJsonFix.GSON;
import com.skydude.dacextras.dacextras;

@Mixin(value = RogueClassGUIButtonMessage.class, remap = false)
public class OverrideRogueGuiMessage {

    @Inject(method = "handleButtonAction", at = @At("HEAD"), cancellable = true)
    private static void overrideHandleButtonAction(Player entity, int buttonID, int x, int y, int z, CallbackInfo ci){
        ci.cancel();
        Level world = entity.level();
        HashMap guistate = RogueClassGUIMenu.guistate;
        if (world.hasChunkAt(new BlockPos(x, y, z))) {
            if (buttonID == 0) {
                dacextras.NUMBER = 0;
                String class_id = dacextras.CLASS_IDS.get(dacextras.NUMBER);
               // String class_id = "jim_my";
              //  System.out.println(" the thigny mathingy is " + dacextras.CLASS_IDS.get(dacextras.NUMBER));

                CustomClassesNextProcedure.execute(world, (double)x, (double)y, (double)z, entity, class_id);

                System.out.println(" 0 started executing next.");
//                if (doesitexist()){
//                   // ForgottenKnightNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
//                } else{
//
//                    // this means we need to override the method before rogue to set dacextras.number to 0
//                    CustomClassesNextProcedure.execute(world, (double)x, (double)y, (double)z, entity, firstId);
//                }
            }

            if (buttonID == 1) {
                ExliedNextProcedure.execute(world, (double)x, (double)y, (double)z, entity);
            }

            if (buttonID == 2) {
                RogueChoosedProcedure.execute(entity);
            }

        }


    }
}
