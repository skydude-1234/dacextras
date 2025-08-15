package com.skydude.dacextras;



import com.mojang.blaze3d.systems.RenderSystem;

import java.io.IOException;
import java.util.HashMap;

import net.mcreator.dungeonsandcombat.DungeonsAndCombatMod;
import net.mcreator.dungeonsandcombat.network.RogueClassGUIButtonMessage;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CustomClassGUIScreen extends AbstractContainerScreen<CustomClassGuiMenu> {
    private static final HashMap<String, Object> guistate;
    private final Level world;
    private final int x;
    private final int y;
    private final int z;
    private final Player entity;
    Button button_empty;
    Button button_empty1;
    Button button_choose;
    public String class_id;

    public CustomClassGUIScreen(CustomClassGuiMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 166;




    }

    public boolean isPauseScreen() {
        return true;
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
       // CustomClasses.execute(dacextras.TEMPCLASS_ID, this.entity);


        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(new ResourceLocation("dungeons_and_combat:textures/screens/class_select_screen.png"), this.leftPos + -112, this.topPos + -47, 0.0F, 0.0F, 300, 250, 300, 250);
        RenderSystem.disableBlend();
    }

    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        } else {
            return super.keyPressed(key, b, c);
        }
    }

    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, Component.translatable(dacextras.TEMPCLASS_ID), -81, 3, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("skydude.health"), -80, 25, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("skydude.strength"), -81, 41, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("skydude.speed"), -82, 58, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("skydude.swing"), -82, 75, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("skydude.armor"), -81, 92, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("skydude.toughness"), -81, 109, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("skydude.looting"), -82, 126, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.dungeons_and_combat.bounty_hunter_class_gui.label_passive"), 110, 95, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable(String.valueOf((CustomClasses.getmaxhealth(dacextras.TEMPCLASS_ID)))), -25, 24, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable(String.valueOf(CustomClasses.getstrength(dacextras.TEMPCLASS_ID))), -25, 41, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable(String.valueOf(CustomClasses.getspeed(dacextras.TEMPCLASS_ID))), -25, 58, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable(String.valueOf(CustomClasses.getswing(dacextras.TEMPCLASS_ID))), -25, 75, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable(String.valueOf(CustomClasses.getarmor(dacextras.TEMPCLASS_ID))), -25, 92, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable(String.valueOf(CustomClasses.gettougness(dacextras.TEMPCLASS_ID))), -25, 109, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable(String.valueOf(CustomClasses.getluck(dacextras.TEMPCLASS_ID))), -25, 126, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.dungeons_and_combat.bounty_hunter_class_gui.label_wield"), 116, 3, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.dungeons_and_combat.bounty_hunter_class_gui.label_heavy"), 95, 23, -3355444, false);
        guiGraphics.drawString(this.font, Component.literal(CustomClasses.getdesc1(dacextras.TEMPCLASS_ID)), 84, 115, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.dungeons_and_combat.bounty_hunter_class_gui.label_light"), 95, 51, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.dungeons_and_combat.bounty_hunter_class_gui.label_medium"), 95, 37, -3355444, false);
        guiGraphics.drawString(this.font, Component.literal(CustomClasses.getdesc2(dacextras.TEMPCLASS_ID)), 84, 129, -3355444, false);
        guiGraphics.drawString(this.font, Component.translatable(CustomClasses.getdesc3(dacextras.TEMPCLASS_ID)), 84, 143, -3355444, false);
    }

    public void init() {
        super.init();
        this.button_empty = new PlainTextButton(this.leftPos + 184, this.topPos + 76, 30, 20, Component.translatable("gui.dungeons_and_combat.bounty_hunter_class_gui.button_empty"), (e) -> {
            DungeonsAndCombatMod.PACKET_HANDLER.sendToServer(new RogueClassGUIButtonMessage(0, this.x, this.y, this.z));
            RogueClassGUIButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
        }, this.font);
        guistate.put("button:button_empty", this.button_empty);
        this.addRenderableWidget(this.button_empty);
        this.button_empty1 = new PlainTextButton(this.leftPos + -112, this.topPos + 77, 30, 20, Component.translatable("gui.dungeons_and_combat.bounty_hunter_class_gui.button_empty1"), (e) -> {
            DungeonsAndCombatMod.PACKET_HANDLER.sendToServer(new RogueClassGUIButtonMessage(1, this.x, this.y, this.z));
            RogueClassGUIButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z);
        }, this.font);
        guistate.put("button:button_empty1", this.button_empty1);
        this.addRenderableWidget(this.button_empty1);
        this.button_choose = new PlainTextButton(this.leftPos + -44, this.topPos + 172, 56, 20, Component.translatable("gui.dungeons_and_combat.bounty_hunter_class_gui.button_choose"), (e) -> {
            DungeonsAndCombatMod.PACKET_HANDLER.sendToServer(new RogueClassGUIButtonMessage(2, this.x, this.y, this.z));
            RogueClassGUIButtonMessage.handleButtonAction(this.entity, 2, this.x, this.y, this.z);
        }, this.font);
        guistate.put("button:button_choose", this.button_choose);
        this.addRenderableWidget(this.button_choose);
    }

    static {
        guistate = CustomClassGuiMenu.guistate;
    }
}
