package com.skydude.dacextras.mixin;

import com.google.gson.JsonObject;
import com.skydude.dacextras.*;
import net.mcreator.dungeonsandcombat.network.RogueClassGUIButtonMessage;
import net.mcreator.dungeonsandcombat.procedures.*;
import net.mcreator.dungeonsandcombat.world.inventory.RogueClassGUIMenu;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.core.jmx.Server;
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

@Mixin(value = RogueClassGUIButtonMessage.class, remap = false)
public class OverrideRogueGuiMessage {

    @Inject(method = "handleButtonAction", at = @At("HEAD"), cancellable = true)
    private static void overrideHandleButtonAction(Player entity, int buttonID, int x, int y, int z, CallbackInfo ci) {
        ci.cancel();

            Level world = entity.level();
            HashMap guistate = RogueClassGUIMenu.guistate;
            if (world.hasChunkAt(new BlockPos(x, y, z))) {
                if (buttonID == 0) {
                    if (customclass) {
                        if (entity instanceof ServerPlayer) {
                            if (dacextras.NUMBER < -1) {
                                dacextras.NUMBER = 0;

                                String class_id = dacextras.CLASS_IDS.get(0);
                                CustomClassesNextProcedure.execute(world, (double) x, (double) y, (double) z, entity, class_id);

                            }
                        }

                        if (dacextras.NUMBER >= dacextras.CLASS_IDS.size()) {
                            ForgottenKnightNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
                            dacextras.NUMBER = -1;


                        } else {

                            if (entity instanceof ServerPlayer) {


                                dacextras.NUMBER = dacextras.NUMBER + 1;
                                if (dacextras.NUMBER <= -1) {
                                    ForgottenKnightNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
                                    dacextras.NUMBER = 0;
                                    return;
                                } else if ((dacextras.NUMBER >= dacextras.CLASS_IDS.size())) {
                                    ForgottenKnightNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
                                    dacextras.NUMBER = -1;
                                }


                                System.out.println(dacextras.NUMBER);
                                String class_id = dacextras.CLASS_IDS.get((int) dacextras.NUMBER);
                                CustomClassesNextProcedure.execute(world, (double) x, (double) y, (double) z, entity, class_id);


                            }
                        }
                    } else {

                        ForgottenKnightNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
                    }

                }

                if (buttonID == 1) {
                    if (customclass) {
                        if (dacextras.NUMBER < 0) {

                            if (entity instanceof ServerPlayer) {
                                System.out.println(dacextras.NUMBER);
                                ExliedNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
                                dacextras.NUMBER = -1;
                            }


                        } else {
                            if (entity instanceof ServerPlayer) {
                                if (dacextras.NUMBER == 0) {
                                    RogueNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
                                }
                                dacextras.NUMBER = dacextras.NUMBER - 1;
                                System.out.println(dacextras.NUMBER);
                                String class_id = dacextras.CLASS_IDS.get((int) dacextras.NUMBER);
                                System.out.println("Classid is" + class_id);
                                CustomClassesNextProcedure.execute(world, (double) x, (double) y, (double) z, entity, class_id);


                                if (dacextras.NUMBER == -1) {
                                    RogueNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);
                                }

                            }
                        }
                    } else{
                        ExliedNextProcedure.execute(world, (double) x, (double) y, (double) z, entity);

                    }
                }
                if (buttonID == 2) {
                    if (customclass) {
                        if (dacextras.NUMBER <= -1) {
                            RogueChoosedProcedure.execute(entity);
                        } else {
                            String class_id = dacextras.CLASS_IDS.get((int) dacextras.NUMBER);
                            CustomClasses.execute(class_id, entity);
                        }
                        dacextras.NUMBER = -1;
                    } else{
                        RogueChoosedProcedure.execute(entity);

                    }
                }
            }



    }
}
