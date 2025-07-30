package com.skydude.dacextras;

import com.skydude.dacextras.CustomClasses;
import com.skydude.dacextras.CustomClassesRegistry;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CustomClassesHandler {
    private static final org.apache.logging.log4j.Logger LOGGER =
            org.apache.logging.log4j.LogManager.getLogger();




    public static void onItemRightCLick(PlayerInteractEvent.RightClickItem event) {
        var player = event.getEntity();

        // Ensure server config is applied globally
        for (String id : dacextras.CLASS_IDS) {
            CustomClasses custom = new CustomClasses(id);
            CustomClassesRegistry.register(custom);

            LOGGER.info("✅ Applied CustomClasses({}, player={}) from server config",
                    id, player.getName().getString());
        }


    }
    }

