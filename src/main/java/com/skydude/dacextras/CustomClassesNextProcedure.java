package com.skydude.dacextras;

import io.netty.buffer.Unpooled;
import net.mcreator.dungeonsandcombat.world.inventory.BountyHunterClassGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;


public class CustomClassesNextProcedure {

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String class_id) {
        if (entity != null) {
            System.out.println(entity);
            System.out.println(class_id);
            if (entity instanceof ServerPlayer) {
                ServerPlayer _ent = (ServerPlayer) entity;
                final BlockPos _bpos = BlockPos.containing(x, y, z);

                NetworkHooks.openScreen(_ent, new MenuProvider() {
                    public Component getDisplayName() {
                        System.out.println(" 1 got displayname.");
                        return Component.literal("BountyHunterClassGUI");

                    }



                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
                        // Forge will provide the buf on the client automatically
                        return new CustomClassGuiMenu(windowId, inv, null);
                    }
                }, buf -> {
                    // ✅ This is the crucial part: write extra data to sync
                    buf.writeBlockPos(_bpos);
                    buf.writeUtf(class_id);
                    System.out.println(" next classid is " + class_id);
                });
            } else {
                System.out.println(entity == null ? " -1 entity is null." : " -2, entity is not instanceof serverplayer");
            }
        }
    }
}