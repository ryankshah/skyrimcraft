package com.ryankshah.skyrimcraft.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.event.KeyEvents;
import com.ryankshah.skyrimcraft.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SkyrimMenuScreen extends Screen
{
    protected static final ResourceLocation MENU_ICONS = new ResourceLocation(Skyrimcraft.MODID, "textures/gui/cross.png");

    private Direction currentDirection;

    Component SKILLS = Component.translatable("skyrimcraft.menu.skills");
    Component MAP = Component.translatable("skyrimcraft.menu.map");
    Component QUESTS = Component.translatable("skyrimcraft.menu.quests");
    Component MAGIC = Component.translatable("skyrimcraft.menu.magic");

    public SkyrimMenuScreen() {
        super(Component.translatable(Skyrimcraft.MODID + ".menu.title"));

        this.currentDirection = Direction.NONE;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        PoseStack poseStack = graphics.pose();
        Minecraft mc = this.minecraft;
        Window window = mc.getWindow();
        int scaledWidth = window.getGuiScaledWidth();
        int scaledHeight = window.getGuiScaledHeight();

        Character character = mc.player.getData(PlayerAttachments.CHARACTER);

        poseStack.pushPose();
        RenderUtil.bind(MENU_ICONS);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        RenderUtil.blitWithBlend(poseStack, scaledWidth / 2 - 103, scaledHeight / 2 - 50, 24, 81, 207, 100, 256, 256, 1, 1.0f);

        if(currentDirection == Direction.NORTH) {
            RenderUtil.blitWithBlend(poseStack, scaledWidth / 2 - 9, scaledHeight / 2 - 85 - 9, 0, 0, 18, 17, 256, 256, 2, 1.0f);
            graphics.drawCenteredString(font, SKILLS, this.width / 2, this.height / 2 - 65, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAP, this.width / 2, this.height / 2 + 55, 0x00FFFFFF);
            graphics.drawCenteredString(font, QUESTS, this.width / 2 + 99 + font.width(QUESTS), this.height / 2 - 4, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAGIC, this.width / 2 - 103 - font.width(MAGIC), this.height / 2 - 4, 0x00FFFFFF);
        } else if(currentDirection == Direction.SOUTH) {
            RenderUtil.blitWithBlend(poseStack, scaledWidth / 2 - 9, scaledHeight / 2 + 65 + 9, 18, 0, 18, 17, 256, 256, 2, 1.0f);
            graphics.drawCenteredString(font, SKILLS, this.width / 2, this.height / 2 - 65, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAP, this.width / 2, this.height / 2 + 55, 0x00FFFFFF);
            graphics.drawCenteredString(font, QUESTS, this.width / 2 + 99 + font.width(QUESTS), this.height / 2 - 4, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAGIC, this.width / 2 - 103 - font.width(MAGIC), this.height / 2 - 4, 0x00FFFFFF);
        } else if(currentDirection == Direction.EAST) {
            RenderUtil.blitWithBlend(poseStack, scaledWidth / 2 - 160 - 9, scaledHeight / 2 - 9, 53, 0, 18, 17, 256, 256, 2, 1.0f);
            graphics.drawCenteredString(font, SKILLS, this.width / 2, this.height / 2 - 65, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAP, this.width / 2, this.height / 2 + 55, 0x00FFFFFF);
            graphics.drawCenteredString(font, QUESTS, this.width / 2 + 99 + font.width(QUESTS), this.height / 2 - 4, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAGIC, this.width / 2 - 103 - font.width(MAGIC), this.height / 2 - 4, 0x00FFFFFF);
        } else if(currentDirection == Direction.WEST) {
            RenderUtil.blitWithBlend(poseStack, scaledWidth / 2 + 150 + 9, scaledHeight / 2 - 9, 36, 0, 18, 17, 256, 256, 2, 1.0f);
            graphics.drawCenteredString(font, SKILLS, this.width / 2, this.height / 2 - 65, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAP, this.width / 2, this.height / 2 + 55, 0x00FFFFFF);
            graphics.drawCenteredString(font, QUESTS, this.width / 2 + 99 + font.width(QUESTS), this.height / 2 - 4, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAGIC, this.width / 2 - 103 - font.width(MAGIC), this.height / 2 - 4, 0x00FFFFFF);
        } else {
            graphics.drawCenteredString(font, SKILLS, this.width / 2, this.height / 2 - 65, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAP, this.width / 2, this.height / 2 + 55, 0x00FFFFFF);
            graphics.drawCenteredString(font, QUESTS, this.width / 2 + 99 + font.width(QUESTS), this.height / 2 - 4, 0x00FFFFFF);
            graphics.drawCenteredString(font, MAGIC, this.width / 2 - 103 - font.width(MAGIC), this.height / 2 - 4, 0x00FFFFFF);
        }
        poseStack.popPose();

//        minecraft.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(KeyEvents.SKYRIM_MENU_WEST.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode)))
            currentDirection = Direction.WEST;
        else if(KeyEvents.SKYRIM_MENU_EAST.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode)))
            currentDirection = Direction.EAST;
        else if(KeyEvents.SKYRIM_MENU_NORTH.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode)))
            currentDirection = Direction.NORTH;
        else if(KeyEvents.SKYRIM_MENU_SOUTH.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode)))
            currentDirection = Direction.SOUTH;
        else if(KeyEvents.SKYRIM_MENU_ENTER.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            if(currentDirection == Direction.NORTH) {
                minecraft.setScreen(null);
                minecraft.setScreen(new SkillScreen());
                // minecraft.setScreen(null);
//                 minecraft.player.displayClientMessage(Component.translatable("skyrimcraft.menu.option.unavailable"), false);
            } else if(currentDirection == Direction.SOUTH) {
                 minecraft.setScreen(null);
                 minecraft.player.displayClientMessage(Component.translatable("skyrimcraft.menu.option.unavailable"), false);
            } else if(currentDirection == Direction.WEST) {
                minecraft.setScreen(null);
                minecraft.player.displayClientMessage(Component.translatable("skyrimcraft.menu.option.unavailable"), false);
//                minecraft.setScreen(new QuestScreen());
//                minecraft.setScreen(new InventoryScreen(minecraft.player));
            } else if(currentDirection == Direction.EAST) {
                AtomicReference<List<Spell>> knownSpells;
                if(minecraft.player != null)
                    knownSpells = new AtomicReference<>(Minecraft.getInstance().player.getData(PlayerAttachments.CHARACTER).getKnownSpells());
                else
                    knownSpells = new AtomicReference<>(new ArrayList<>());

                if(knownSpells.get().isEmpty()) {
                    minecraft.setScreen(null);
                    minecraft.player.displayClientMessage(Component.translatable("skyrimcraft.menu.option.magic.none"), false);
                } else {
                    minecraft.setScreen(null);
                    minecraft.setScreen(new SkyrimMagicScreen(knownSpells.get()));
                }
            }  else
                minecraft.player.displayClientMessage(Component.translatable("skyrimcraft.menu.option.invalid"), false);
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    enum Direction {
        NONE(),
        NORTH(),
        SOUTH(),
        EAST(),
        WEST();

        Direction() {
        }
    }
}
