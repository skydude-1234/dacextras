package com.skydude.dacextras;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.skydude.dacextras.dacextras.*;

@Mod.EventBusSubscriber(modid = "dacextras")
public class ClassPersist {

    private static final ResourceLocation REQUIRED_ADVANCEMENT =
            new ResourceLocation("dungeons_and_combat", "origin");

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        var player = event.getEntity();



            CustomClasses.class_attributes(player);

    }

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        var player = event.getEntity();

        if (hasRequiredAdvancement((ServerPlayer) player)) {
            CustomClasses.class_attributes(player);
        }
    }

    private static boolean hasRequiredAdvancement(net.minecraft.server.level.ServerPlayer player) {
        var adv = player.server.getAdvancements().getAdvancement(REQUIRED_ADVANCEMENT);
        if (adv == null) return false;
        var progress = player.getAdvancements().getOrStartProgress(adv);
        return progress.isDone();
    }
}
