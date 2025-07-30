//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.skydude.dacextras;

import java.util.HashMap;
import java.util.function.Supplier;
import net.mcreator.dungeonsandcombat.DungeonsAndCombatMod;
import net.mcreator.dungeonsandcombat.procedures.BountyHunterChoosedProcedure;
import net.mcreator.dungeonsandcombat.procedures.ExliedNextProcedure;
import net.mcreator.dungeonsandcombat.procedures.OniSlayerNextProcedure;
import net.mcreator.dungeonsandcombat.world.inventory.BountyHunterClassGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@EventBusSubscriber(
        bus = Bus.MOD
)
public class CustomClassButtonMessage {
    private final int buttonID;
    private final int x;
    private final int y;
    private final int z;

    public CustomClassButtonMessage(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public CustomClassButtonMessage(int buttonID, int x, int y, int z) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void buffer(CustomClassButtonMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
    }

    public static void handler(CustomClassButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            int buttonID = message.buttonID;
            int x = message.x;
            int y = message.y;
            int z = message.z;
            handleButtonAction(entity, buttonID, x, y, z);
        });
        context.setPacketHandled(true);
    }

    public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
        Level world = entity.level();
        HashMap guistate = BountyHunterClassGUIMenu.guistate;
        if (world.hasChunkAt(new BlockPos(x, y, z))) {
            String class_id = dacextras.CLASS_IDS.get(dacextras.NUMBER);

           // String class_id = "jim_my";
            if (buttonID == 0) {
                dacextras.NUMBER += 1;
                 class_id = dacextras.CLASS_IDS.get(dacextras.NUMBER);
                ExliedNextProcedure.execute(world, (double)x, (double)y, (double)z, entity);
            }

            if (buttonID == 1) {
                OniSlayerNextProcedure.execute(world, (double)x, (double)y, (double)z, entity);
            }

            if (buttonID == 2) {

                CustomClasses.execute(class_id, entity);
            }

        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        DungeonsAndCombatMod.addNetworkMessage(CustomClassButtonMessage.class, CustomClassButtonMessage::buffer, CustomClassButtonMessage::new, CustomClassButtonMessage::handler);
    }
}
