package com.ryankshah.skyrimcraft.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.skill.ISkill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.event.KeyEvents;
import com.ryankshah.skyrimcraft.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;

import java.util.AbstractMap;
import java.util.Map;

public class SkillScreen extends Screen
{
    // Skill Nebula Cube Map
    public static final CubeMap SKILL_NEBULA_CUBE_MAP = new CubeMap(new ResourceLocation(Skyrimcraft.MODID, "textures/gui/panorama/nebula"));
    protected static final ResourceLocation SKILL_ICONS = new ResourceLocation(Skyrimcraft.MODID, "textures/gui/skill_icons.png");
    protected static final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID + ":textures/gui/overlay_icons.png");

    private final int PLAYER_BAR_MAX_WIDTH = 78,
            SKILL_BAR_CONTAINER_U = 1,
            SKILL_BAR_CONTAINER_V = 194,
            SKILL_BAR_CONTAINER_WIDTH = 80,
            SKILL_BAR_CONTAINER_HEIGHT = 8,
            SKILL_BAR_U = 7,
            SKILL_BAR_V = 204,
            SKILL_BAR_WIDTH = 67,
            SKILL_BAR_HEIGHT = 2;

    private Map<Integer, ISkill> skillsList;
    private ISkill selectedSkillObject;
    private Minecraft minecraft;
    private LocalPlayer player;
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
        this.skillsList = player.getData(PlayerAttachments.SKILLS).getSkills();
        this.levelUpdates = SkyrimGuiOverlay.SkyrimLevelUpdates.LEVEL_UPDATES.size(); // todo: check this and update, perhaps add to attachment
        this.selectedSkillObject = null;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        PoseStack matrixStack = graphics.pose();
        Minecraft mc = this.minecraft;
        Window window = mc.getWindow();
        int scaledWidth = window.getGuiScaledWidth();
        int scaledHeight = window.getGuiScaledHeight();

        matrixStack.pushPose();
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);

        SKILL_NEBULA_CUBE_MAP.render(this.minecraft, Mth.sin(cubeMapPosition * 0.001F) * 5.0F + 25.0F, -cubeMapPosition * 0.1F, 1.0f);

        RenderSystem.setShaderTexture(0, SKILL_ICONS);

        RenderUtil.blitWithBlend(matrixStack, width / 2 - 110, 10, 0, 209, 221, 14, 256, 256, 1, 1);

        // Render bottom bars
        graphics.fillGradient(0, this.height * 3 / 4 + 20, this.width, this.height, 0xAA000000, 0xAA000000);
        graphics.fillGradient(0, this.height * 3 / 4 + 22, this.width, this.height * 3 / 4 + 23, 0xFF6E6B64, 0xFF6E6B64);

        renderHealth(matrixStack, width, height);
        renderStamina(matrixStack, width, height);
        renderMagicka(matrixStack, width, height);
        matrixStack.popPose();

//        RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
        // If no skill is selected, show skill icons etc.
        if(!skillSelected) {
            for (Map.Entry<Integer, ISkill> skillEntry : skillsList.entrySet()) {
                int x = this.width / 2 + 128 * (skillEntry.getKey() + 1) - (this.currentSkill + 1) * 128; //(width / 2) - ((SKILL_BAR_CONTAINER_WIDTH / 2) + (24 * currentSkill+1))
                drawSkill(skillEntry.getValue(), graphics, matrixStack, x, height / 2);
            }
        } else {
            // If skill selected, show perks for selection etc.
        }

        // If there is a level update, draw this shit on top
        if(shouldFocusLevelUpdates) {

        }

        matrixStack.popPose();
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

    public static AbstractMap.SimpleEntry<Integer, Integer> getIconUV(ISkill skill) {
        if(skill.getID() == SkillRegistry.ALTERATION.getID()) {
            return new AbstractMap.SimpleEntry<>(0, 0);
        } else if(skill.getID() == SkillRegistry.CONJURATION.getID()) {
            return new AbstractMap.SimpleEntry<>(64, 0);
        } else if(skill.getID() == SkillRegistry.DESTRUCTION.getID()) {
            return new AbstractMap.SimpleEntry<>(128, 0);
        } else if(skill.getID() == SkillRegistry.ILLUSION.getID()) {
            return new AbstractMap.SimpleEntry<>(192, 0);
        } else if(skill.getID() == SkillRegistry.RESTORATION.getID()) {
            return new AbstractMap.SimpleEntry<>(256, 0);
        } else if(skill.getID() == SkillRegistry.ENCHANTING.getID()) {
            return new AbstractMap.SimpleEntry<>(320, 0);
        } else if(skill.getID() == SkillRegistry.BLOCK.getID()) {
            return new AbstractMap.SimpleEntry<>(384, 0);
        } else if(skill.getID() == SkillRegistry.SMITHING.getID()) {
            return new AbstractMap.SimpleEntry<>(448, 0);
        } else if(skill.getID() == SkillRegistry.TWO_HANDED.getID()) {
            return new AbstractMap.SimpleEntry<>(0, 64);
        } else if(skill.getID() == SkillRegistry.ONE_HANDED.getID()) {
            return new AbstractMap.SimpleEntry<>(64, 64);
        } else if(skill.getID() == SkillRegistry.HEAVY_ARMOR.getID()) {
            return new AbstractMap.SimpleEntry<>(128, 64);
        } else if(skill.getID() == SkillRegistry.ARCHERY.getID()) {
            return new AbstractMap.SimpleEntry<>(192, 64);
        } else if(skill.getID() == SkillRegistry.PICKPOCKET.getID()) {
            return new AbstractMap.SimpleEntry<>(256, 64);
        } else if(skill.getID() == SkillRegistry.LIGHT_ARMOR.getID()) {
            return new AbstractMap.SimpleEntry<>(320, 64);
        } else if(skill.getID() == SkillRegistry.LOCKPICKING.getID()) {
            return new AbstractMap.SimpleEntry<>(384, 64);
        }
        return new AbstractMap.SimpleEntry<>(384, 0); // return null or perhaps a default no-icon tex when we have all icons?
    }

    private void drawSkill(ISkill skill, GuiGraphics graphics, PoseStack matrixStack, int x, int y) {
        float skillProgress = skill.getXpProgress();
        //float skillBarWidth = SKILL_BAR_WIDTH * skillProgress;

        matrixStack.pushPose();
        RenderSystem.setShaderTexture(0, SKILL_ICONS);

        RenderUtil.blitWithBlend(matrixStack, x - (SKILL_BAR_CONTAINER_WIDTH / 2), y + 48 + (SKILL_BAR_CONTAINER_HEIGHT / 2), SKILL_BAR_CONTAINER_U, SKILL_BAR_CONTAINER_V, SKILL_BAR_CONTAINER_WIDTH, SKILL_BAR_CONTAINER_HEIGHT, 512, 512, 2, 1);
        RenderUtil.blitWithBlend(matrixStack, x - (SKILL_BAR_CONTAINER_WIDTH / 2) + 7, y + 49 + (SKILL_BAR_CONTAINER_HEIGHT / 2) + SKILL_BAR_HEIGHT, SKILL_BAR_U, SKILL_BAR_V, (int)(SKILL_BAR_WIDTH * skillProgress), SKILL_BAR_HEIGHT, 512, 512, 2, 1);

        AbstractMap.SimpleEntry<Integer, Integer> iconUV = getIconUV(skill);
        RenderUtil.blitWithBlend(matrixStack, x - 32, y + 18 - 64, iconUV.getKey(), iconUV.getValue(), 64, 64, 512, 512, 2, 1);

        graphics.drawCenteredString(font, skill.getName() + " " + skill.getLevel(), x, y + 38, 0x00FFFFFF);
        matrixStack.popPose();
    }

    private void renderHealth(PoseStack matrixStack, int width, int height) {
        float healthPercentage = player.getHealth() / player.getMaxHealth();
        float healthBarWidth = PLAYER_BAR_MAX_WIDTH * healthPercentage;
        float healthBarStartX;

        if(player.level().getDifficulty() == Difficulty.HARD) {
            healthBarStartX = (width / 2 - 40) + (PLAYER_BAR_MAX_WIDTH - healthBarWidth) / 2.0f;
            RenderUtil.blitWithBlend(matrixStack, width / 2 - 51, height - 41, 96, 246, 100, 16, 256, 256, 3, 1);
        } else {
            healthBarStartX = (width / 2 - 39) + (PLAYER_BAR_MAX_WIDTH - healthBarWidth) / 2.0f;
            RenderUtil.blitWithBlend(matrixStack, width / 2 - 51, height - 35, 0, 226, 102, 10, 256, 256, 3, 1);
        }
        RenderUtil.blitWithBlend(matrixStack, (int)healthBarStartX, height - 33, 12 + ((PLAYER_BAR_MAX_WIDTH - healthBarWidth) / 2.0f), 247, healthBarWidth, 6, 256, 256, 3, 1);
    }

    private void renderStamina(PoseStack matrixStack, int width, int height) {
        float staminaPercentage = player.getFoodData().getFoodLevel() / 20.0f; // 20.0f is the max food value (this is apparently hardcoded...)
        float staminaBarWidth = PLAYER_BAR_MAX_WIDTH * staminaPercentage;
        float staminaBarStartX = (float)(width - 108) + (PLAYER_BAR_MAX_WIDTH - staminaBarWidth);

        RenderUtil.blitWithBlend(matrixStack, width - 120, height - 35, 0, 226, 102, 10, 256, 256, 3, 1);
        RenderUtil.blitWithBlend(matrixStack, (int)staminaBarStartX, height - 33, 12 + ((PLAYER_BAR_MAX_WIDTH - staminaBarWidth) / 2.0f), 255, staminaBarWidth, 6, 256, 256, 3, 1);
    }

    private void renderMagicka(PoseStack matrixStack, int width, int height) {
        float magickaPercentage = player.getData(PlayerAttachments.MAGICKA) / player.getData(PlayerAttachments.MAX_MAGICKA);
        float magickaBarWidth = PLAYER_BAR_MAX_WIDTH * magickaPercentage;
        RenderUtil.blitWithBlend(matrixStack, 20, height - 35, 0, 226, 102, 10, 256, 256, 3, 1);
        RenderUtil.blitWithBlend(matrixStack, 32, height - 33, 12 + ((PLAYER_BAR_MAX_WIDTH - magickaBarWidth) / 2.0f), 239, (int)(78 * magickaPercentage), 6, 256, 256, 3, 1);
    }
}
