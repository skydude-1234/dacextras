//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.skydude.dacextras;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.mcreator.dungeonsandcombat.init.DungeonsAndCombatModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class CustomClassGuiMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public static final HashMap<String, Object> guistate = new HashMap<>();
    public final Level world;
    public final Player entity;
    public int x;
    public int y;
    public int z;
    private ContainerLevelAccess access;
    private IItemHandler internal;
    private final Map<Integer, Slot> customSlots;
    private boolean bound;
    private Supplier<Boolean> boundItemMatcher;
    private Entity boundEntity;
    private BlockEntity boundBlockEntity;
    private String classId;

    public CustomClassGuiMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super((MenuType)OverrideModmenus.CUSTOM_CLASS_GUI.get(), id);
        this.access = ContainerLevelAccess.NULL;
        this.customSlots = new HashMap<>();
        this.bound = false;
        this.boundItemMatcher = null;
        this.boundEntity = null;
        this.boundBlockEntity = null;
        this.entity = inv.player;
        this.world = inv.player.level();
        this.internal = new ItemStackHandler(0);
        BlockPos pos = null;
        if (extraData != null) {
            pos = extraData.readBlockPos();

            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            this.classId = extraData.readUtf();
            System.out.println(" classId = extraData.readUtf(); is" + classId);
            this.access = ContainerLevelAccess.create(this.world, pos);
        } else { System.out.println(" -3 extradata might be null");}
         System.out.println("  3 CustomClassGui worked??");
    }
    public String getClassId() {
        return classId;
    }
    public boolean stillValid(@NotNull Player player) {
        if (this.bound) {
            if (this.boundItemMatcher != null) {
                return (Boolean)this.boundItemMatcher.get();
            }

            if (this.boundBlockEntity != null) {
                return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
            }

            if (this.boundEntity != null) {
                return this.boundEntity.isAlive();
            }
        }

        return true;
    }

    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }

    public Map<Integer, Slot> get() {
        return this.customSlots;
    }
}