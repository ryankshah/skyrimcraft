package com.ryankshah.skyrimcraft.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.skill.Skill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.event.KeyEvents;
import com.ryankshah.skyrimcraft.screen.components.SkillWidget;
import com.ryankshah.skyrimcraft.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.CreditsAndAttributionScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class SkillScreen extends Screen
{
    // Skill Nebula Cube Map
    public static final CubeMap SKILL_NEBULA_CUBE_MAP = new CubeMap(new ResourceLocation(Skyrimcraft.MODID, "textures/gui/panorama/magic_end"));
    protected static final ResourceLocation SKILL_ICONS = new ResourceLocation(Skyrimcraft.MODID, "textures/gui/skill_icons.png");

    private final int PLAYER_BAR_MAX_WIDTH = 78,
            SKILL_BAR_CONTAINER_U = 1,
            SKILL_BAR_CONTAINER_V = 194,
            SKILL_BAR_CONTAINER_WIDTH = 80,
            SKILL_BAR_CONTAINER_HEIGHT = 8,
            SKILL_BAR_U = 7,
            SKILL_BAR_V = 204,
            SKILL_BAR_WIDTH = 67,
            SKILL_BAR_HEIGHT = 2;

    private List<Skill> skillsList;
    private Skill selectedSkillObject;
    private Minecraft minecraft;
    private LocalPlayer player;
    private Character character;
    private float cubeMapPosition = 0.0f;
    private int currentSkill = 0;
    private int currentUpdateSelection = 0; // 0 = magicka, 1 = health, 2 = stamina
    private boolean skillSelected;
    private int levelUpdates;
    private boolean shouldFocusLevelUpdates = false;

    public SkillScreen() {
        super(Component.translatable(Skyrimcraft.MODID + ".skills.title"));

        this.minecraft = Minecraft.getInstance();
        this.player = Minecraft.getInstance().player;
        this.character = Character.get(player);
        this.skillsList = character.getSkills();
        this.levelUpdates = SkyrimGuiOverlay.SkyrimLevelUpdates.LEVEL_UPDATES.size();
        this.selectedSkillObject = null;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        PoseStack poseStack = graphics.pose();
        Minecraft mc = this.minecraft;
        Window window = mc.getWindow();
        int scaledWidth = window.getGuiScaledWidth();
        int scaledHeight = window.getGuiScaledHeight();

        poseStack.pushPose();
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);

        SKILL_NEBULA_CUBE_MAP.render(this.minecraft, Mth.sin(cubeMapPosition * 0.001F) * 5.0F + 25.0F, -cubeMapPosition * 0.1F, 1.0f);
        poseStack.popPose();

        poseStack.pushPose();
        RenderUtil.bind(SKILL_ICONS);

        RenderUtil.blitWithBlend(poseStack, width / 2 - 110, 10, 0, 209, 221, 14, 512, 512, 1, 1);
        RenderUtil.blitWithBlend(poseStack, width / 2 - 47, 32, 0, 264, 94, 11, 512, 512, 1, 1);
        poseStack.popPose();


        // Render bottom bars
        graphics.fillGradient(0, this.height * 3 / 4 + 20, this.width, this.height, 0xAA000000, 0xAA000000);
        graphics.fillGradient(0, this.height * 3 / 4 + 22, this.width, this.height * 3 / 4 + 23, 0xFF6E6B64, 0xFF6E6B64);

        poseStack.pushPose();
        RenderUtil.bind(SKILL_ICONS);
        renderHealth(poseStack, width, height);
        renderStamina(poseStack, width, height);
        renderMagicka(poseStack, width, height);

        poseStack.popPose();

//        RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
        // If no skill is selected, show skill icons etc.
        if(!skillSelected) {
            for (Skill skillEntry : skillsList) {
                int x = this.width / 2 + 128 * (skillEntry.getID() + 1) - (this.currentSkill + 1) * 128; //(width / 2) - ((SKILL_BAR_CONTAINER_WIDTH / 2) + (24 * currentSkill+1))
//                this.addRenderableWidget(new SkillWidget(
//                        font, x, height / 2 - 32, 64, 128, Component.empty(), skillsList, skillEntry, currentSkill, onPress -> this.skillSelected = true
//                ));
                drawSkill(skillEntry, graphics, poseStack, x, height / 2 - 20);
            }
        } else {
            // If skill selected, show perks for selection etc.
        }

        // If there is a level update, draw this shit on top
        if(shouldFocusLevelUpdates) {

        }

        drawScaledCenteredString(graphics, "NAME", width / 2 - 110 + 22, 17, 0xFF00CC00, 0.5f);
        graphics.drawCenteredString(font, player.getDisplayName().getString(), width / 2 - 110 + 22 + 4 + font.width("NAME"), 17, 0xFF00CC00);

        drawScaledCenteredString(graphics, "Perks to increase: " + SkyrimGuiOverlay.SkyrimLevelUpdates.LEVEL_UPDATES.size(), width / 2, 37, 0x00FFFFFF, 0.5f);

        poseStack.popPose();
    }
    
    public void drawScaledCenteredString(GuiGraphics graphics, String str, int x, int y, int color, float scale) {
        graphics.pose().pushPose();
        graphics.pose().scale(scale, scale, scale);
        graphics.drawCenteredString(font, str, x * (int)(1 / scale), y * (int)(1 / scale), color);
        graphics.pose().popPose();
    }
    public void drawScaledCenteredStringWithSplit(GuiGraphics graphics, String str, int x, int y, int color, float scale, int split) {
        graphics.pose().pushPose();
        graphics.pose().scale(scale, scale, scale);
        if (str.length() >= split) {
            String[] parts = str.split("\\.");
//            String[] parts = {str.substring(0, split),str.substring(split)};
            for(int i = 0; i < parts.length; i++)
                graphics.drawCenteredString(font, parts[i], x * (int)(1 / scale), (y + (i * 6)) * (int)(1 / scale), color);
//            graphics.drawCenteredString(font, parts[1], x * (int)(1 / scale), (y+6) * (int)(1 / scale), color);
        } else {
            graphics.drawCenteredString(font, str, x * (int) (1 / scale), y * (int) (1 / scale), color);
        }
        graphics.pose().popPose();
    }

    @Override
    public void tick() {
        super.tick();
        cubeMapPosition += 1.0f;

        if(this.levelUpdates > 0 && !shouldFocusLevelUpdates)
            shouldFocusLevelUpdates = true;
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(KeyEvents.SKYRIM_MENU_EAST.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            if (shouldFocusLevelUpdates && currentUpdateSelection > 0) {
                currentUpdateSelection--;
            } else if(!skillSelected) {
                if (currentSkill > 0) {
                    currentSkill--;
                } else {
                    currentSkill = skillsList.size() - 1;
                }
            }
        } else if(KeyEvents.SKYRIM_MENU_WEST.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            if(shouldFocusLevelUpdates && currentUpdateSelection < 2) {
                currentUpdateSelection++;
            } else if(!skillSelected) {
                if (currentSkill < skillsList.size() - 1) {
                    currentSkill++;
                } else {
                    currentSkill = 0;
                }
            }
        } else if(KeyEvents.SKYRIM_MENU_ENTER.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            if(shouldFocusLevelUpdates) {
                // Send packet based on the update selected
//                Networking.sendToServer(new PacketUpdateExtraStatOnServer(currentUpdateSelection));
            } else if(!skillSelected) {
                skillSelected = true;
                selectedSkillObject = skillsList.get(currentSkill);
            } else {
                // Skill panel is open, interact with it.
            }
        } else if (keyCode == 256 && this.shouldCloseOnEsc()) {
            if(!skillSelected) {
                this.onClose();
                return true;
            } else {
                // If skill is selected, close the skill's panel and go back to the main skills screen
                selectedSkillObject = null;
                skillSelected = false;
            }
        }
//        else if (keyCode == 258) {
//            boolean flag = !hasShiftDown();
//            if (!this.changeFocus(flag)) {
//                this.changeFocus(flag);
//            }
//
//            return false;
//        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public static AbstractMap.SimpleEntry<Integer, Integer> getIconUV(ResourceKey<Skill> skill) {
        return SkillRegistry.SKILLS_REGISTRY.get(skill).getIconUV();
    }

    private void drawSkill(Skill skill, GuiGraphics graphics, PoseStack poseStack, int x, int y) {
        float skillProgress = skill.getXpProgress();
        //float skillBarWidth = SKILL_BAR_WIDTH * skillProgress;
        poseStack.pushPose();
        RenderUtil.bind(SKILL_ICONS);
        RenderUtil.blitWithBlend(poseStack, x - (SKILL_BAR_CONTAINER_WIDTH / 2), y + 48 + (SKILL_BAR_CONTAINER_HEIGHT / 2), SKILL_BAR_CONTAINER_U, SKILL_BAR_CONTAINER_V, SKILL_BAR_CONTAINER_WIDTH, SKILL_BAR_CONTAINER_HEIGHT, 512, 512, 2, 1);
        RenderUtil.blitWithBlend(poseStack, x - (SKILL_BAR_CONTAINER_WIDTH / 2) + 7, y + 49 + (SKILL_BAR_CONTAINER_HEIGHT / 2) + SKILL_BAR_HEIGHT, SKILL_BAR_U, SKILL_BAR_V, (int)(SKILL_BAR_WIDTH * skillProgress), SKILL_BAR_HEIGHT, 512, 512, 2, 1);

        AbstractMap.SimpleEntry<Integer, Integer> iconUV = getIconUV(SkillRegistry.SKILLS_REGISTRY.getResourceKey(skill).get());
        RenderUtil.blitWithBlend(poseStack, x - 32, y + 18 - 64, iconUV.getKey(), iconUV.getValue(), 64, 64, 512, 512, 2, 1);
        poseStack.popPose();
        graphics.drawCenteredString(font, skill.getName() + " " + skill.getLevel(), x, y + 38, 0x00FFFFFF);

        if(skillsList.get(currentSkill).getID() == skill.getID()) {
            drawScaledCenteredStringWithSplit(graphics, skill.getDescription(), x, y + 85, 0x00FFFFFF, 0.75f, 108);
//            poseStack.pushPose();
//            poseStack.scale(0.5f, 0.5f, 0.5f);
//            graphics.drawCenteredString(font, skill.getDescription(), x, y + 65, 0x00FFFFFF);
//            poseStack.popPose();
        }
    }

    private void renderHealth(PoseStack poseStack, int width, int height) {
        float healthPercentage = player.getHealth() / player.getMaxHealth();
        float healthBarWidth = PLAYER_BAR_MAX_WIDTH * healthPercentage;
        float healthBarStartX;

        if(player.level().getDifficulty() == Difficulty.HARD) {
            healthBarStartX = (width / 2 - 40) + (PLAYER_BAR_MAX_WIDTH - healthBarWidth) / 2.0f;
            RenderUtil.blitWithBlend(poseStack, width / 2 - 51, height - 36, 96, 246, 100, 16, 512, 512, 3, 1);
        } else {
            healthBarStartX = (width / 2 - 39) + (PLAYER_BAR_MAX_WIDTH - healthBarWidth) / 2.0f;
            RenderUtil.blitWithBlend(poseStack, width / 2 - 51, height - 30, 0, 226, 102, 10, 512, 512, 3, 1);
        }
        RenderUtil.blitWithBlend(poseStack, (int)healthBarStartX, height - 28, 12 + ((PLAYER_BAR_MAX_WIDTH - healthBarWidth) / 2.0f), 247, healthBarWidth, 6, 512, 512, 3, 1);
    }

    private void renderStamina(PoseStack poseStack, int width, int height) {
        float staminaPercentage = player.getFoodData().getFoodLevel() / 20.0f; // 20.0f is the max food value (this is apparently hardcoded...)
        float staminaBarWidth = PLAYER_BAR_MAX_WIDTH * staminaPercentage;
        float staminaBarStartX = (float)(width - 108) + (PLAYER_BAR_MAX_WIDTH - staminaBarWidth);

        RenderUtil.blitWithBlend(poseStack, width - 120, height - 30, 0, 226, 102, 10, 512, 512, 3, 1);
        RenderUtil.blitWithBlend(poseStack, (int)staminaBarStartX, height - 28, 12 + ((PLAYER_BAR_MAX_WIDTH - staminaBarWidth) / 2.0f), 255, staminaBarWidth, 6, 512, 512, 3, 1);
    }

    private void renderMagicka(PoseStack poseStack, int width, int height) {
        float magickaPercentage = character.getMagicka() / character.getMaxMagicka();
        float magickaBarWidth = PLAYER_BAR_MAX_WIDTH * magickaPercentage;
        RenderUtil.blitWithBlend(poseStack, 20, height - 30, 0, 226, 102, 10, 512, 512, 3, 1);
        RenderUtil.blitWithBlend(poseStack, 32, height - 28, 12 + ((PLAYER_BAR_MAX_WIDTH - magickaBarWidth) / 2.0f), 239, (int)(78 * magickaPercentage), 6, 512, 512, 3, 1);
    }
}
