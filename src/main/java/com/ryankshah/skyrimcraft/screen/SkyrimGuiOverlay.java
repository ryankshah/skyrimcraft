package com.ryankshah.skyrimcraft.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.magic.ISpell;
import com.ryankshah.skyrimcraft.event.KeyEvents;
import com.ryankshah.skyrimcraft.init.EntityInit;
import com.ryankshah.skyrimcraft.quest.registry.QuestRegistry;
import com.ryankshah.skyrimcraft.util.CompassFeature;
import com.ryankshah.skyrimcraft.util.LevelUpdate;
import com.ryankshah.skyrimcraft.util.RenderUtil;
import net.minecraft.client.AttackIndicatorStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.glfwGetKeyName;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public class SkyrimGuiOverlay
{
    public static final int PLAYER_BAR_MAX_WIDTH = 78;

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event){
        event.registerAboveAll(new ResourceLocation(Skyrimcraft.MODID,"skyrim_compass"),new SkyrimCompass());
        event.registerAboveAll(new ResourceLocation(Skyrimcraft.MODID, "skyrim_magicka"), new SkyrimMagicka());
        event.registerAboveAll(new ResourceLocation(Skyrimcraft.MODID, "current_spells"), new SkyrimSpells());
        event.registerAboveAll(new ResourceLocation(Skyrimcraft.MODID, "target_health"), new SkyrimTargetHealth());
        event.registerAboveAll(new ResourceLocation(Skyrimcraft.MODID, "level_updates"), new SkyrimLevelUpdates());
        event.registerAbove(VanillaGuiOverlay.PLAYER_HEALTH.id(), new ResourceLocation(Skyrimcraft.MODID, "skyrim_health"), new SkyrimHealth());
        event.registerAbove(VanillaGuiOverlay.FOOD_LEVEL.id(), new ResourceLocation(Skyrimcraft.MODID, "skyrim_stamina"), new SkyrimStamina());
        event.registerAbove(VanillaGuiOverlay.ARMOR_LEVEL.id(), new ResourceLocation(Skyrimcraft.MODID, "skyrim_armor"), new SkyrimArmorIcons());
        event.registerAbove(VanillaGuiOverlay.AIR_LEVEL.id(), new ResourceLocation(Skyrimcraft.MODID, "skyrim_air"), new SkyrimAir());
        event.registerAbove(VanillaGuiOverlay.CROSSHAIR.id(), new ResourceLocation(Skyrimcraft.MODID, "skyrim_crosshair"), new SkyrimCrosshair());
        // TODO: Fix
//        event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), new ResourceLocation(Skyrimcraft.MODID, "skyrim_xpbar"), new SkyrimXPBar());
    }

    public static class SkyrimLevelUpdates implements IGuiOverlay
    {
        private final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID + ":textures/gui/overlay_icons.png");

        public static List<LevelUpdate> LEVEL_UPDATES = new ArrayList<>();

        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();

            if(!LEVEL_UPDATES.isEmpty()) {
                LevelUpdate levelUpdate = LEVEL_UPDATES.get(0);

                if(levelUpdate.getLevelUpRenderTime() <= 0)
                    LEVEL_UPDATES.remove(levelUpdate);

                if(levelUpdate.getUpdateName().equalsIgnoreCase("characterLevel"))
                    renderCharacterLevelUpdate(guiGraphics, poseStack, mc.font, scaledWidth, scaledHeight, partialTick, levelUpdate.getLevel(), levelUpdate.getLevelUpRenderTime());
                else
                    renderLevelUpdate(guiGraphics, poseStack, mc.font, mc.player, scaledWidth, scaledHeight, partialTick, levelUpdate.getUpdateName(), levelUpdate.getLevel(), levelUpdate.getLevelUpRenderTime());

                levelUpdate.setLevelUpRenderTime(levelUpdate.getLevelUpRenderTime() - 1);
            }
        }

        private void renderCharacterLevelUpdate(GuiGraphics graphics, PoseStack poseStack, Font fontRenderer, int width, int height, float partialTicks, int level, int levelUpRenderTime) {
            String characterLevel = ""+level;
            String levelProgressString = "Progress";

            float hue = (float)levelUpRenderTime - partialTicks;
            int opacity = (int)(hue * 255.0F / 20.0F);
            if (opacity > 255) opacity = 255;

            poseStack.pushPose();
            //RenderSystem.translatef((float)(width / 2), (float)(height - 68), 0.0F);
//            RenderSystem.enableAlphaTest();
//            RenderSystem.defaultAlphaFunc();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
//            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.enableDepthTest();
            RenderSystem.clearColor(1F, 1F, 1f, opacity / 255f);

            RenderUtil.bind(OVERLAY_ICONS);
            RenderUtil.blitWithBlend(poseStack, width / 2 - 51, height / 2 - 30, 0, 51, 102, 10, 256, 256, 10, 1);
            RenderUtil.blitWithBlend(poseStack, width / 2 - 39, height / 2 - 28, 96, 64, 78, 6, 256, 256, 10, 1);

            if (opacity > 8) {
                graphics.drawCenteredString(fontRenderer, "Level Up".toUpperCase(), width / 2, height / 2 - 45, 0x00FFFFFF | (opacity << 24));
                graphics.drawString(fontRenderer, levelProgressString, width / 2 - 60 - fontRenderer.width(levelProgressString), height / 2 - 30, 0x00FFFFFF | (opacity << 24));
                graphics.drawString(fontRenderer, characterLevel, width / 2 + 60 + 8 - fontRenderer.width(characterLevel), height / 2 - 30, 0x00FFFFFF | (opacity << 24));
            }

            RenderSystem.disableDepthTest();
            RenderSystem.disableBlend();
//            RenderSystem.disableAlphaTest();
            poseStack.popPose();
        }

        private double getXpNeededForNextCharacterLevel(int level) {
            return 12.5*Math.pow(level+1, 2) + 62.5*level - 75;
        }

        private void renderLevelUpdate(GuiGraphics graphics, PoseStack poseStack, Font fontRenderer, Player player, int width, int height, float elapsed, String updateName, int level, int levelUpRenderTime) {
            String nextCharacterLevel = ""+(player.getData(PlayerAttachments.CHARACTER_LEVEL)+1);
            float characterProgress = player.getData(PlayerAttachments.CHARACTER_TOTAL_XP) / (float)getXpNeededForNextCharacterLevel(player.getData(PlayerAttachments.CHARACTER_LEVEL)+1);
            float characterProgressBarWidth = PLAYER_BAR_MAX_WIDTH * characterProgress;
            String levelProgressString = "Progress"; //"Level Progress";

            float hue = (float)levelUpRenderTime - elapsed;
            int opacity = (int)(hue * 255.0F / 20.0F);
            if (opacity > 255) opacity = 255;

            poseStack.pushPose();
//            RenderSystem.translatef((float)(width / 2), (float)(height - 68), 0.0F);
//            RenderSystem.enableAlphaTest();
//            RenderSystem.defaultAlphaFunc();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
//            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.enableDepthTest();
            RenderSystem.clearColor(1F, 1F, 1f, opacity / 255f);

//            mc.getTextureManager().bind(OVERLAY_ICONS);
            RenderUtil.bind(OVERLAY_ICONS);
            RenderUtil.blitWithBlend(poseStack, width / 2 - 51, height / 2 - 30, 0, 51, 102, 10, 256, 256, 10, 1);
            RenderUtil.blitWithBlend(poseStack, width / 2 - 39, height / 2 - 28, 96 + ((PLAYER_BAR_MAX_WIDTH - characterProgressBarWidth) / 2.0f), 64, (int)(78 * characterProgress), 6, 256, 256, 11, 1);

            if (opacity > 8) {
                graphics.drawCenteredString(fontRenderer, (updateName + " Increased To " + level).toUpperCase(), width / 2, height / 2 - 45, 0x00FFFFFF | (opacity << 24));
                graphics.drawString(fontRenderer, levelProgressString, width / 2 - 60 - fontRenderer.width(levelProgressString), height / 2 - 30, 0x00FFFFFF | (opacity << 24));
                graphics.drawString(fontRenderer, nextCharacterLevel, width / 2 + 60 + 8 - fontRenderer.width(nextCharacterLevel), height / 2 - 30, 0x00FFFFFF | (opacity << 24));
            }

            RenderSystem.disableDepthTest();
            RenderSystem.disableBlend();
//            RenderSystem.disableAlphaTest();
            poseStack.popPose();
        }
    }

    public static class SkyrimTargetHealth implements IGuiOverlay
    {
        private final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID + ":textures/gui/overlay_icons.png");

        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();

            Entity currentTarget = mc.player.level().getEntity(mc.player.getData(PlayerAttachments.PLAYER_TARGETS).getCurrentTarget());
            if(currentTarget instanceof LivingEntity && currentTarget.isAlive()) {
//            if(targets.contains(mc.player.getLastHurtMob().getId()) && mc.player.getLastHurtMob() != null && mc.player.getLastHurtMob().isAlive()) {
                LivingEntity target = (LivingEntity)currentTarget;
                String entityName = target.getDisplayName().getString();

                if(!mc.player.closerThan(target, 16.0D))
                    return;

                float healthPercentage = target.getHealth() / target.getMaxHealth();
                float healthBarWidth = 142 * healthPercentage;
                float healthBarStartX = (scaledWidth / 2 - 69) + (142 - healthBarWidth) / 2.0f;

                poseStack.pushPose();
                RenderUtil.bind(OVERLAY_ICONS);
                RenderUtil.blitWithBlend(poseStack, (scaledWidth / 2) - 78, 28, 3, 88, 156, 8, 256, 256, 3, 1);
                RenderUtil.blitWithBlend(poseStack, (int)healthBarStartX, 30, 10 + ((142 - healthBarWidth) / 2.0f), 101, (int)healthBarWidth, 3, 256, 256, 3, 1);

                int entityNameWidth = mc.font.width(entityName);
                // left banner
                RenderUtil.blitWithBlend(poseStack, (scaledWidth / 2) - 2 - (41 + entityNameWidth / 2), 38, 25, 107, 41, 12, 256, 256, 3, 1); // width / 2 - 69
                // right banner
                RenderUtil.blitWithBlend(poseStack, (scaledWidth / 2) + 2 + entityNameWidth / 2, 38, 84, 107, 41, 12, 256, 256, 3, 1); // width / 2 + 28
                guiGraphics.drawCenteredString(mc.font, entityName , scaledWidth / 2, 40, 0x00C0C0C0);
                poseStack.popPose();
            }
        }
    }

    public static class SkyrimCrosshair implements IGuiOverlay
    {
        private final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID + ":textures/gui/overlay_icons.png");
//        protected static final ResourceLocation CROSSHAIR_SPRITE = new ResourceLocation("hud/crosshair");
        protected static final ResourceLocation CROSSHAIR_ATTACK_INDICATOR_FULL_SPRITE = new ResourceLocation("hud/crosshair_attack_indicator_full");
        protected static final ResourceLocation CROSSHAIR_ATTACK_INDICATOR_BACKGROUND_SPRITE = new ResourceLocation("hud/crosshair_attack_indicator_background");
        protected static final ResourceLocation CROSSHAIR_ATTACK_INDICATOR_PROGRESS_SPRITE = new ResourceLocation("hud/crosshair_attack_indicator_progress");

        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();

            if(mc.crosshairPickEntity instanceof LivingEntity && mc.player.isCrouching()) {
                LivingEntity entity = (LivingEntity) mc.crosshairPickEntity;
                if(entity.getTags().contains(EntityInit.PICKPOCKET_TAG)) {
                    guiGraphics.drawCenteredString(mc.font, "(" + glfwGetKeyName(KeyEvents.PICKPOCKET_KEY.get().getKey().getValue(), 0).toUpperCase() + ") Pickpocket", scaledWidth / 2, scaledHeight / 2 + 8, 0x00FFFFFF);
                }
            }

            if(mc.player.getMainHandItem().getItem() instanceof ProjectileWeaponItem
                    && !mc.player.isCreative() && !mc.player.isSpectator()
                    && !mc.player.isUnderWater() && !(mc.player.getAirSupply() < 300)) {
                int top = scaledHeight - 48;
                if(mc.player.getArmorValue() > 0) top = scaledHeight - 60;
                ItemStack itemstack = mc.player.getProjectile(mc.player.getMainHandItem());
                if(itemstack != ItemStack.EMPTY) {
                    Component nameAndCount = Component.translatable(String.valueOf(itemstack.getHoverName().copy().append("(" + itemstack.getCount() + ")")));
                    guiGraphics.drawString(mc.font, nameAndCount, scaledWidth - 18 - mc.font.width(nameAndCount), top, 0x00FFFFFF);
                }
            }

            int texX = 166;
            int texY = 88;
            if(!mc.player.isSpectator()) { // mc.player.getMainHandItem().getItem() instanceof ShootableItem && // --> Player does not need bow for this iirc
                if (mc.player.isCrouching()) {
                    texX += 15;

                    if(!mc.player.canBeSeenAsEnemy()) {
                        texX += 15;
                    }
                }
            }
            poseStack.pushPose();
            RenderUtil.bind(OVERLAY_ICONS);
            RenderUtil.blitWithBlend(poseStack, (scaledWidth - 16) / 2, (scaledHeight - 16) / 2, texX, texY, 15, 15, 256, 256, 3, 1);
            poseStack.popPose();

            if (mc.options.attackIndicator().get() == AttackIndicatorStatus.CROSSHAIR) {
                float f = mc.player.getAttackStrengthScale(0.0F);
                boolean flag = false;
                if (mc.crosshairPickEntity != null && mc.crosshairPickEntity instanceof LivingEntity && f >= 1.0F) {
                    flag = mc.player.getCurrentItemAttackStrengthDelay() > 5.0F;
                    flag = flag & mc.crosshairPickEntity.isAlive();
                }

                int j = scaledHeight / 2 - 7 + 16;
                int k = scaledWidth / 2 - 8;
//                mc.textureManager.bind(AbstractGui.GUI_ICONS_LOCATION);
                poseStack.pushPose();
                if (flag) {
                    RenderUtil.blitWithBlend(poseStack, k, j, 68, 94, 16, 16, 256, 256, 4, 1);
                } else if (f < 1.0F) {
                    int l = (int)(f * 17.0F);
                    RenderUtil.blitWithBlend(poseStack, k, j, 36, 94, 16, 4, 256, 256, 4, 1);
                    RenderUtil.blitWithBlend(poseStack, k, j, 52, 94, l, 4, 256, 256, 4, 1);
                }
                poseStack.popPose();
            }
        }
    }

    public static class SkyrimSpells implements IGuiOverlay
    {
        private final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID + ":textures/gui/overlay_icons.png");
        private int SLOT_WIDTH = 22, SLOT_HEIGHT = 22;
        private int DOUBLE_SLOT_WIDTH = 22, DOUBLE_SLOT_HEIGHT = 41;
        private int ICON_WIDTH = 16, ICON_HEIGHT = 16;

        public Float getCooldown (List<Pair<ISpell, Float>> cooldowns, ISpell value) {
            for (Pair<ISpell, Float> p : cooldowns)
                if (p.getFirst().equals(value))
                    return p.getSecond();
            return null;
        }

        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();
            
            ISpell selectedSpell1 = mc.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1();
            ISpell selectedSpell2 = mc.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2();
            List<Pair<ISpell, Float>> spellCooldowns = mc.player.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown();

            poseStack.pushPose();
            RenderUtil.bind(OVERLAY_ICONS);
            if(selectedSpell1 != null && selectedSpell2 == null) {
                RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2), 234, 83, SLOT_WIDTH, SLOT_HEIGHT, 256, 256, 4, 1);
                poseStack.pushPose();
                RenderUtil.bind(selectedSpell1.getIcon());
                RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH + 3, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2) + 3, 0, 0, 16, 16, ICON_WIDTH, ICON_HEIGHT, 5, 1);
                if(spellCooldowns.stream().anyMatch(p -> p.getFirst().getID() == selectedSpell1.getID())) {
                    if (getCooldown(spellCooldowns, selectedSpell1) > 0) {
                        poseStack.pushPose();
                        RenderUtil.bind(OVERLAY_ICONS);
                        float cooldown = getCooldown(spellCooldowns, selectedSpell1);
                        float maxCooldown = selectedSpell1.getCooldown();
                        float offset = Mth.floor(cooldown/maxCooldown * 8);
                        RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH + 3, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2) + 3, 118 - (16 * (offset % 8)), 148, ICON_WIDTH, ICON_HEIGHT, 256, 256, 6, 1);
                        poseStack.popPose();
                    }
                }
                poseStack.popPose();
            } else if(selectedSpell1 == null && selectedSpell2 != null) {
                RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH, (scaledHeight / 2) + (DOUBLE_SLOT_HEIGHT / 2), 234, 83, SLOT_WIDTH, SLOT_HEIGHT, 256, 256, 4, 1);
                poseStack.pushPose();
                RenderUtil.bind(selectedSpell2.getIcon());
                RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH + 3, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2) + 3, 0, 0, 16, 16, 16, 16, 5, 1);
                if(spellCooldowns.stream().anyMatch(p -> p.getFirst().getID() == selectedSpell2.getID())) {
                    if (getCooldown(spellCooldowns, selectedSpell2) > 0) {
                        poseStack.pushPose();
                        RenderUtil.bind(OVERLAY_ICONS);
                        float cooldown = getCooldown(spellCooldowns, selectedSpell2);
                        float maxCooldown = selectedSpell2.getCooldown();
                        float offset = Mth.floor(cooldown/maxCooldown * 8);
                        RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH + 3, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2) + 3, 118 - (16 * (offset % 8)), 148, ICON_WIDTH, ICON_HEIGHT, 256, 256, 6, 1);
                        poseStack.popPose();
                    }
                }
                poseStack.popPose();
            } else if (selectedSpell1 != null && selectedSpell2 != null) {
                RenderUtil.blitWithBlend(poseStack, scaledWidth - DOUBLE_SLOT_WIDTH, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2), 234, 106, DOUBLE_SLOT_WIDTH, DOUBLE_SLOT_HEIGHT, 256, 256, 4, 1);

                poseStack.pushPose();
                RenderUtil.bind(selectedSpell1.getIcon());
                RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH + 3, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2) + 3 + 20, 0, 0, 16, 16, 16, 16, 5, 1);
                if(spellCooldowns.stream().anyMatch(p -> p.getFirst().getID() == selectedSpell1.getID())) {
                    if (getCooldown(spellCooldowns, selectedSpell1) > 0) {
                        poseStack.pushPose();
                        RenderUtil.bind(OVERLAY_ICONS);
                        float cooldown = getCooldown(spellCooldowns, selectedSpell1);
                        float maxCooldown = selectedSpell1.getCooldown();
                        float offset = Mth.floor(cooldown/maxCooldown * 8);
                        RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH + 3, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2) + 3, 118 - (16 * (offset % 8)), 148, ICON_WIDTH, ICON_HEIGHT, 256, 256, 6, 1);
                        poseStack.popPose();
                    }
                }
                poseStack.popPose();

                poseStack.pushPose();
                RenderUtil.bind(selectedSpell2.getIcon());
                RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH + 3, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2) + 3 + 40, 0, 0, 16, 16, 16, 16, 5, 1);
                if(spellCooldowns.stream().anyMatch(p -> p.getFirst().getID() == selectedSpell1.getID())) {
                    if (getCooldown(spellCooldowns, selectedSpell2) > 0) {
                        poseStack.pushPose();
                        RenderUtil.bind(OVERLAY_ICONS);
                        float cooldown = getCooldown(spellCooldowns, selectedSpell2);
                        float maxCooldown = selectedSpell2.getCooldown();
                        float offset = Mth.floor(cooldown/maxCooldown * 8);
                        RenderUtil.blitWithBlend(poseStack, scaledWidth - SLOT_WIDTH + 3, (scaledHeight / 2) - (DOUBLE_SLOT_HEIGHT / 2) + 3, 118 - (16 * (offset % 8)), 148, ICON_WIDTH, ICON_HEIGHT, 256, 256, 6, 1);
                        poseStack.popPose();
                    }
                }
                poseStack.popPose();
            }
            poseStack.popPose();


//            for(Map.Entry<Integer, ISpell> spell : selectedSpells.entrySet()) {
//                if(spell.getValue() != null)
//                    guiGraphics.drawString(mc.font, "(" + spell.getKey() + ") : " + spell.getValue().getName(), 20, scaledHeight - 120 + 10 * spell.getKey(), 0xFFFFFF);
//            }
        }
    }

    public static class SkyrimStamina implements IGuiOverlay
    {
        private final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID + ":textures/gui/overlay_icons.png");

        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();

            float staminaPercentage = mc.player.getFoodData().getFoodLevel() / 20.0f; // 20.0f is the max food value (this is apparently hardcoded...)
            float staminaBarWidth = PLAYER_BAR_MAX_WIDTH * staminaPercentage;
            float staminaBarStartX = (float)(scaledWidth - 109) + (PLAYER_BAR_MAX_WIDTH - staminaBarWidth);

            poseStack.pushPose();
            RenderUtil.bind(OVERLAY_ICONS);
            RenderUtil.blitWithBlend(poseStack, scaledWidth - 120, scaledHeight - 35, 0, 51, 102, 10, 256, 256, 1, 1);
            RenderUtil.blitWithBlend(poseStack, (int)staminaBarStartX, scaledHeight - 33, 12 + ((PLAYER_BAR_MAX_WIDTH - staminaBarWidth) / 2.0f), 80, (int)staminaBarWidth, 6, 256, 256, 1, 1);
            poseStack.popPose();
        }
    }

    public static class SkyrimAir implements IGuiOverlay
    {
        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();

            final ResourceLocation AIR_SPRITE = new ResourceLocation("hud/air");
            final ResourceLocation AIR_BURSTING_SPRITE = new ResourceLocation("hud/air_bursting");

            mc.getProfiler().push("air");
            Player player = (Player)mc.getCameraEntity();
            RenderSystem.enableBlend();
            int left = screenWidth - 20;
            int top;
            if(mc.player.getArmorValue() > 0)
                top = screenHeight - 60;
            else
                top = screenHeight - 48;
            int air = player.getAirSupply();

            if (player.isEyeInFluidType((FluidType) NeoForgeMod.WATER_TYPE.value()) || air < 300) {
                int full = Mth.ceil((double)(air - 2) * 10.0 / 300.0);
                int partial = Mth.ceil((double)air * 10.0 / 300.0) - full;

                for(int i = 0; i < full + partial; ++i) {
                    guiGraphics.blitSprite(i < full ? AIR_SPRITE : AIR_BURSTING_SPRITE, left - i * 8 - 9, top, 9, 9);
                }
            }
            RenderSystem.disableBlend();
            mc.getProfiler().pop();
        }
    }

    public static class SkyrimXPBar implements IGuiOverlay
    {
        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();

            final ResourceLocation EXPERIENCE_BAR_BACKGROUND_SPRITE = new ResourceLocation("hud/experience_bar_background");
            final ResourceLocation EXPERIENCE_BAR_PROGRESS_SPRITE = new ResourceLocation("hud/experience_bar_progress");

            RenderSystem.enableBlend();
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

            if (mc.gameMode.hasExperience()) {
                int pX = screenWidth / 2 - 91;
                int k, l;

                mc.getProfiler().push("expBar");
                int i = mc.player.getXpNeededForNextLevel();
                if (i > 0) {
                    int j = 182;
                    k = (int)(mc.player.experienceProgress * 183.0F);
                    l = screenHeight - 32 + 3;
                    guiGraphics.blitSprite(EXPERIENCE_BAR_BACKGROUND_SPRITE, pX, l, 182, 5);
                    if (k > 0) {
                        guiGraphics.blitSprite(EXPERIENCE_BAR_PROGRESS_SPRITE, 182, 182, 5, 0, 0, pX, k, l, 5);
                    }
                }

                mc.getProfiler().pop();
                if (mc.player.experienceLevel > 0) {
                    mc.getProfiler().push("expLevel");
                    String s = "" + mc.player.experienceLevel;
                    k = (screenWidth - mc.font.width(s)) / 2;
                    l = screenHeight - 31 - 4;
                    guiGraphics.drawString(mc.font, s, k + 1, l, 0, false);
                    guiGraphics.drawString(mc.font, s, k - 1, l, 0, false);
                    guiGraphics.drawString(mc.font, s, k, l + 1, 0, false);
                    guiGraphics.drawString(mc.font, s, k, l - 1, 0, false);
                    guiGraphics.drawString(mc.font, s, k, l, 8453920, false);
                    mc.getProfiler().pop();
                }
            }

            RenderSystem.disableBlend();
        }
    }

    public static class SkyrimArmorIcons implements IGuiOverlay
    {
        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();

            final ResourceLocation ARMOR_EMPTY_SPRITE = new ResourceLocation("hud/armor_empty");
            final ResourceLocation ARMOR_HALF_SPRITE = new ResourceLocation("hud/armor_half");
            final ResourceLocation ARMOR_FULL_SPRITE = new ResourceLocation("hud/armor_full");

            mc.getProfiler().push("armor");
            RenderSystem.enableBlend();
            int left = screenWidth - 20 - 8;
            int top = screenHeight - 48;

            int level = mc.player.getArmorValue();
            for (int i = 1; level > 0 && i < 20; i += 2) {
                if (i < level) {
                    guiGraphics.blitSprite(ARMOR_FULL_SPRITE, left, top, 9, 9);
                } else if (i == level) {
                    guiGraphics.blitSprite(ARMOR_HALF_SPRITE, left, top, 9, 9);
                } else {
                    guiGraphics.blitSprite(ARMOR_EMPTY_SPRITE, left, top, 9, 9);
                }

                left -= 8;
            }
            RenderSystem.disableBlend();
            mc.getProfiler().pop();
        }
    }

    public static class SkyrimMagicka implements IGuiOverlay
    {
        private final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID + ":textures/gui/overlay_icons.png");

        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();

            float magickaPercentage = mc.player.getData(PlayerAttachments.MAGICKA) / mc.player.getData(PlayerAttachments.MAX_MAGICKA.get());
            float magickaBarWidth = PLAYER_BAR_MAX_WIDTH * magickaPercentage;

            poseStack.pushPose();
            RenderUtil.bind(OVERLAY_ICONS);
            RenderUtil.blitWithBlend(poseStack, 20, scaledHeight - 35, 0, 51, 102, 10, 256, 256, 1, 1);
            RenderUtil.blitWithBlend(poseStack, 32, scaledHeight - 33, 12 + ((PLAYER_BAR_MAX_WIDTH - magickaBarWidth) / 2.0f), 64, (int)(78 * magickaPercentage), 6, 256, 256, 1, 1);
            poseStack.popPose();
        }
    }

    public static class SkyrimHealth implements IGuiOverlay
    {
        private final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID + ":textures/gui/overlay_icons.png");

        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();
            int scaledHeight = window.getGuiScaledHeight();

            float healthPercentage = mc.player.getHealth() / mc.player.getMaxHealth();
            float healthBarWidth = PLAYER_BAR_MAX_WIDTH * healthPercentage;
            float healthBarStartX;

            poseStack.pushPose();
            RenderUtil.bind(OVERLAY_ICONS);
            if(mc.player.level().getDifficulty() == Difficulty.HARD) {
                healthBarStartX = (scaledWidth / 2 - 40) + (PLAYER_BAR_MAX_WIDTH - healthBarWidth) / 2.0f;
                RenderUtil.blitWithBlend(poseStack, scaledWidth / 2 - 51, scaledHeight - 41, 96, 71, 100, 16, 256, 256, 1, 1);
            } else {
                healthBarStartX = (scaledWidth / 2 - 39) + (PLAYER_BAR_MAX_WIDTH - healthBarWidth) / 2.0f;
                RenderUtil.blitWithBlend(poseStack, scaledWidth / 2 - 51, scaledHeight - 35, 0, 51, 102, 10, 256, 256, 1, 1);
            }
            RenderUtil.blitWithBlend(poseStack, (int)healthBarStartX, scaledHeight - 33, 12 + ((PLAYER_BAR_MAX_WIDTH - healthBarWidth) / 2.0f), 72, healthBarWidth, 6, 256, 256, 1, 1);
            poseStack.popPose();
        }
    }

    public static class SkyrimCompass implements IGuiOverlay
    {
        private final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID + ":textures/gui/overlay_icons.png");

        @Override
        public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
            PoseStack poseStack = guiGraphics.pose();
            Minecraft mc = gui.getMinecraft();
            Window window = mc.getWindow();
            int scaledWidth = window.getGuiScaledWidth();

            poseStack.pushPose();
            RenderUtil.bind(OVERLAY_ICONS);
            RenderUtil.blitWithBlend(poseStack, scaledWidth / 2 - 110, 10, 0, 37, 221, 14, 256, 256, 1, 1.0f);
            poseStack.popPose();
//            guiGraphics.blitSprite(compassBackground, scaledWidth / 2 - 110, 10, 1, 221, 14);
//        guiGraphics.blit(scaledWidth / 2 - 110, 10, 0, 37, 221, compassBackground);

            assert mc.player != null;
            float yaw = Mth.lerp(mc.getFrameTime(), mc.player.yHeadRotO, mc.player.yHeadRot) % 360;
            if (yaw < 0) yaw += 360;

            drawCardinalDirection(guiGraphics, yaw, 0, scaledWidth / 2, "S");
            drawCardinalDirection(guiGraphics, yaw, 90, scaledWidth / 2, "W");
            drawCardinalDirection(guiGraphics, yaw, 180, scaledWidth / 2, "N");
            drawCardinalDirection(guiGraphics, yaw, 270, scaledWidth / 2, "E");

//        double playerPosX = Mth.lerp(mc.getFrameTime(), mc.player.xo, mc.player.getX());
//        double playerPosY = Mth.lerp(mc.getFrameTime(), mc.player.yo, mc.player.getY());
//        double playerPosZ = Mth.lerp(mc.getFrameTime(), mc.player.zo, mc.player.getZ());
            double playerPosX = Mth.lerp(mc.getFrameTime(), mc.player.xo, mc.player.getX());
            double playerPosY = Mth.lerp(mc.getFrameTime(), mc.player.yo, mc.player.getY());
            double playerPosZ = Mth.lerp(mc.getFrameTime(), mc.player.zo, mc.player.getZ());
            final float finalYaw = yaw;

            List<CompassFeature> compassFeatures = mc.player.getData(PlayerAttachments.COMPASS_FEATURES).getCompassFeatures();
            if (compassFeatures.size() > 0) {
                List<CompassFeature> sortedFeatures = Lists.newArrayList(compassFeatures);
                sortedFeatures.sort((a, b) -> {
                    Vec3 positionA = new Vec3(a.getBlockPos().getX(), 0, a.getBlockPos().getZ()); //mc.player.getY()
                    Vec3 positionB = new Vec3(b.getBlockPos().getX(), 0, b.getBlockPos().getZ());
                    float angleA = Mth.abs(angleDistance(finalYaw, angleFromTarget(positionA, positionB).x));
                    float angleB = Mth.abs(angleDistance(finalYaw, angleFromTarget(positionB, positionA).x));
                    return (int) Math.signum(angleB - angleA);
                });

                for (CompassFeature feature : sortedFeatures) {
                    if (mc.player.position().distanceToSqr(feature.getBlockPos().getX(), mc.player.position().y, feature.getBlockPos().getZ()) <= 512 * 16) { // 256 blocks?
                        Vec3 position = new Vec3(feature.getBlockPos().getX(), 0, feature.getBlockPos().getZ());
                        Vec2 angleYd = angleFromTarget(position, new Vec3(playerPosX, playerPosY, playerPosZ));
                        drawStructureIndicator(poseStack, finalYaw, angleYd.x, scaledWidth / 2, feature);
                    }
                }
            }
            List<Integer> targetingEntities = mc.player.getData(PlayerAttachments.PLAYER_TARGETS).getTargets();
            if (targetingEntities != null) {
                for (int entityID : targetingEntities) {
                    if (mc.player.level().getEntity(entityID) == null)
                        return;

                    LivingEntity targetingEntity = (LivingEntity) mc.player.level().getEntity(entityID);
                    // Check player is out of target range
                    assert targetingEntity != null;
                    if (!mc.player.closerThan(targetingEntity, 16.0D))
                        return;

                    Vec3 a = new Vec3(playerPosX, playerPosY, playerPosZ);
                    Vec3 b = targetingEntity.position();

                    Vec2 angleYd = angleFromTarget(b, a);
                    drawTargetIndicator(poseStack, finalYaw, angleYd.x, scaledWidth / 2); //ydelta = angleYd.y
                }
            }
        }

        private static void drawCardinalDirection(GuiGraphics guiGraphics, float yaw, float angle, int xPos, String text) {
            float nDist = angleDistance(yaw, angle);
            if (Mth.abs(nDist) <= 90) {
                float nPos = xPos + nDist;
                //fill(matrixStack, (int)(nPos-0.5f), 10, (int)(nPos+0.5f), 18, 0x7FFFFFFF);
                guiGraphics.drawCenteredString(Minecraft.getInstance().font, text, (int)nPos, 13, 0xFFFFFF);
            }
        }

        private static Vec2 angleFromTarget(Vec3 targetPos, Vec3 playerPos) {
            double xd = targetPos.x - playerPos.x;
            double yd = targetPos.y - playerPos.y;
            double zd = targetPos.z - playerPos.z;
            return new Vec2((float) Math.toDegrees(-Math.atan2(xd, zd)), (float)yd);
        }

        private static float angleDistance(float yaw, float other) {
            float dist = other - yaw;
            if (dist > 0) return dist > 180 ? (dist - 360) : dist;
            else return dist < -180 ? (dist + 360) : dist;
        }

        private void drawTargetIndicator(PoseStack poseStack, float yaw, float angle, int xPos) {
            float nDist = angleDistance(yaw, angle);
            if (Mth.abs(nDist) <= 90) {
                float nPos = xPos + nDist;
                //fill(matrixStack, (int)(nPos-0.5f), 10, (int)(nPos+0.5f), 18, 0x7FFFFFFF);
                poseStack.pushPose();
                RenderUtil.bind(OVERLAY_ICONS);
                RenderUtil.blitWithBlend(poseStack, nPos-2, 15, 106, 53, 4, 4, 256, 256, 3, 1.0f);
                poseStack.popPose();
            }
        }

        private void drawStructureIndicator(PoseStack poseStack, float yaw, float angle, int xPos, CompassFeature feature) {
            if(feature.getIconUV() == null)
                return;

            float nDist = angleDistance(yaw, angle);
            if (Mth.abs(nDist) <= 90)
            {
                float nPos = xPos + nDist;
                int u = feature.getIconUV().getKey(), v = feature.getIconUV().getValue();
                //fill(matrixStack, (int)(nPos-0.5f), 10, (int)(nPos+0.5f), 18, 0x7FFFFFFF);
                poseStack.pushPose();
                RenderUtil.bind(OVERLAY_ICONS);
                RenderUtil.blitWithBlend(poseStack, nPos-2, 17 - (CompassFeature.ICON_HEIGHT / 2), u, v, CompassFeature.ICON_WIDTH, CompassFeature.ICON_HEIGHT, 256, 256, 3, 1.0f);
                poseStack.popPose();
            }
        }
    }
}