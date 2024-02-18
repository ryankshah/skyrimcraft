package com.ryankshah.skyrimcraft.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.magic.ISpell;
import com.ryankshah.skyrimcraft.event.KeyEvents;
import com.ryankshah.skyrimcraft.network.spell.UpdateSelectedSpells;
import com.ryankshah.skyrimcraft.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.lwjgl.glfw.GLFW.glfwGetKeyName;

public class SkyrimMagicScreen extends Screen
{
    protected static final ResourceLocation OVERLAY_ICONS = new ResourceLocation(Skyrimcraft.MODID, "textures/gui/overlay_icons.png");
    private static final int PADDING = 7;

    private Map<ISpell.SpellType, ArrayList<ISpell>> spellsAndTypes;
    private List<Object> spellTypes;
    private List<ISpell> spellsListForCurrentSpellType;
    private boolean spellTypeChosen;
    private int currentSpellType;
    private int currentSpell;
    private ISpell currentSpellObject;
    private ISpell.SpellType currentSpellTypeObject;

    private float currentTick, lastTick;
    private int currentSpellFrame;

    public SkyrimMagicScreen(List<ISpell> knownSpells) {
        super(Component.translatable(Skyrimcraft.MODID + ".magicgui.title"));
        this.spellsAndTypes = new HashMap<>();
        spellsAndTypes.put(ISpell.SpellType.ALL, new ArrayList<>());

        for(ISpell spell : knownSpells) {
            if(spellsAndTypes.containsKey(spell.getType()))
                spellsAndTypes.get(spell.getType()).add(spell);
            else {
                ArrayList<ISpell> temp = new ArrayList<>();
                temp.add(spell);
                spellsAndTypes.put(spell.getType(), temp);
            }
            spellsAndTypes.get(ISpell.SpellType.ALL).add(spell);
        }

        spellsAndTypes = spellsAndTypes.entrySet().stream().sorted((e1,e2) -> Integer.compare(e1.getKey().getTypeID(), (e2.getKey().getTypeID()))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        this.currentSpellType = 0;
        this.currentSpell = 0;
        this.currentSpellObject = null;
        this.currentSpellTypeObject = null;
        this.spellTypes = Arrays.asList(spellsAndTypes.keySet().toArray());
        this.spellsListForCurrentSpellType = new ArrayList<>();
        this.currentTick = 0;
        this.lastTick = 0;
        this.currentSpellFrame = 0;
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        PoseStack poseStack = graphics.pose();
        Minecraft mc = this.minecraft;
        Window window = mc.getWindow();
        int scaledWidth = window.getGuiScaledWidth();
        int scaledHeight = window.getGuiScaledHeight();

        poseStack.pushPose();
//        RenderUtil.bind(MENU_ICONS);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.renderBackground(graphics, mouseX, mouseY, partialTick);

        if(this.spellTypeChosen) {
            graphics.fillGradient(this.width - 90, 0, this.width - 10, this.height, 0xAA000000, 0xAA000000);
            graphics.fillGradient(this.width - 88, 0, this.width - 87, this.height, 0xFF5D5A51, 0xFF5D5A51);
            graphics.fillGradient(this.width - 13, 0, this.width - 12, this.height, 0xFF5D5A51, 0xFF5D5A51);
            graphics.fillGradient(this.width - 190, 0, this.width - 110, this.height, 0xAA222222, 0xAA222222);
            graphics.fillGradient(this.width - 188, 0, this.width - 187, this.height, 0xFF5D5A51, 0xFF5D5A51);
            graphics.fillGradient(this.width - 113, 0, this.width - 112, this.height, 0xFF5D5A51, 0xFF5D5A51);
        } else {
            graphics.fillGradient(this.width - 90, 0, this.width - 10, this.height, 0xAA222222, 0xAA222222);
            graphics.fillGradient(this.width - 88, 0, this.width - 87, this.height, 0xFF5D5A51, 0xFF5D5A51);
            graphics.fillGradient(this.width - 13, 0, this.width - 12, this.height, 0xFF5D5A51, 0xFF5D5A51);
            graphics.fillGradient(this.width - 190, 0, this.width - 110, this.height, 0xAA000000, 0xAA000000);
            graphics.fillGradient(this.width - 188, 0, this.width - 187, this.height, 0xFF5D5A51, 0xFF5D5A51);
            graphics.fillGradient(this.width - 113, 0, this.width - 112, this.height, 0xFF5D5A51, 0xFF5D5A51);
        }
        graphics.fillGradient(0, this.height * 3 / 4 + 20, this.width, this.height, 0xAA000000, 0xAA000000);
        graphics.fillGradient(0, this.height * 3 / 4 + 22, this.width, this.height * 3 / 4 + 23, 0xFF5D5A51, 0xFF5D5A51);

        // Draw "buttons" for keys for selecting spells
        drawGradientRect(graphics, poseStack, 17, this.height - 29, 32, this.height - 14, 0xAA000000, 0xAA000000, 0xFF5D5A51);
        drawGradientRect(graphics, poseStack, 37, this.height - 29, 52, this.height - 14, 0xAA000000, 0xAA000000, 0xFF5D5A51);
        graphics.drawCenteredString(font, glfwGetKeyName(KeyEvents.SPELL_SLOT_1_KEY.get().getKey().getValue(), 0).toUpperCase(), 25, this.height - 25, 0x0000FF00);
        graphics.drawCenteredString(font, glfwGetKeyName(KeyEvents.SPELL_SLOT_2_KEY.get().getKey().getValue(), 0).toUpperCase(), 45, this.height - 25, 0x0000FFFF);
        graphics.drawCenteredString(font, "Equip", 70, this.height - 25, 0x00FFFFFF);

        renderMagicka(graphics, poseStack, this.width, this.height);

        int MIN_Y = 30;
        int MAX_Y = height / 2 + 14 * 6 - 10;

        int i;
        for(i = 0; i < spellTypes.size(); i++) {
            int y = this.height / 2 + 14 * i - this.currentSpellType * 7;
            if(y <= MIN_Y || y >= MAX_Y)
                continue;
            String spellTypeName = spellTypes.get(i).toString();
            if (spellTypeName.length() >= 12)
                spellTypeName = spellTypeName.substring(0, 10) + "..";
            graphics.drawString(font, spellTypeName, this.width - 20 - font.width(spellTypeName), y, i == this.currentSpellType ? 0x00FFFFFF : 0x00C0C0C0);
        }

        // Get ISpell.SpellType
        currentSpellTypeObject = (ISpell.SpellType) spellTypes.get(currentSpellType);
        // Get player's known spells for chosen spell type
        spellsListForCurrentSpellType = spellsAndTypes.get(currentSpellTypeObject);

        for(int j = 0; j < spellsListForCurrentSpellType.size(); j++) {
            ISpell spell = spellsListForCurrentSpellType.get(j);
            String displayName = spell.getName();

            AtomicInteger color = new AtomicInteger(0x00C0C0C0);

            int finalJ = j;
            ISpell selectedSpell1 = minecraft.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1();
            ISpell selectedSpell2 = minecraft.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2();
            if(selectedSpell1 != null && selectedSpell1 == spell) color.set(0x0000FF00);
            else if(selectedSpell2 != null && selectedSpell2 == spell) color.set(0x0000FFFF);
            else if(finalJ == this.currentSpell) {
                color.set(0x00FFFFFF);
            }

            if (j == this.currentSpell && this.spellTypeChosen) {
                this.currentSpellObject = spell;
                poseStack.pushPose();
                // TODO work on scaling.
//                poseStack.scale(0.5f, 0.5f, 0.5f);
                drawSpellInformation(graphics, poseStack, currentSpellObject, this.width, this.height, partialTick);
                poseStack.popPose();
            }

            int y = this.height / 2 + 14 * j - this.currentSpell * 7;
            if(y <= MIN_Y || y >= MAX_Y)
                continue;

            if (displayName.length() >= 12)
                displayName = displayName.substring(0, 10) + "..";

            graphics.drawString(font, displayName, this.width - 183, this.height / 2 + 14 * j - this.currentSpell * 7, color.get());
        }

        poseStack.popPose();

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void drawGradientRect(GuiGraphics graphics, PoseStack matrixStack, int startX, int startY, int endX, int endY, int colorStart, int colorEnd, int borderColor) {
        matrixStack.pushPose();
        // Draw background
        graphics.fillGradient(startX, startY, endX, endY, colorStart, colorEnd);
        // Draw borders
        graphics.fill(startX, startY, endX, startY+1, borderColor); // top
        graphics.fill(startX, endY-1, endX, endY, borderColor); // bottom
        graphics.fill(startX, startY+1, startX+1, endY-1, borderColor); // left
        graphics.fill(endX-1, startY+1, endX, endY-1, borderColor); // right
        matrixStack.popPose();
    }


    private void drawSpellInformation(GuiGraphics graphics, PoseStack matrixStack, ISpell spell, int width, int height, float partialTicks) {
        drawGradientRect(graphics, matrixStack, 40, (this.height) / 2 - 20, 200, (this.height) / 2 + 60, 0xAA000000, 0xAA000000, 0xFF6E6B64);
        graphics.fillGradient(50, (this.height) / 2, 190, (this.height) / 2 + 1, 0xFF6E6B64, 0xFF6E6B64); // Line under spell name

        graphics.drawCenteredString(font, spell.getName(), 120, (this.height) / 2 - 10, 0x00FFFFFF); // Spell name
        for(int i = 1; i < spell.getDescription().size()+1; i++)
            graphics.drawCenteredString(font, spell.getDescription().get(i-1), 120, (this.height) / 2 + (8 * i), 0x00FFFFFF); // Spell description

        if(spell.getType() != ISpell.SpellType.SHOUT) {
            graphics.drawString(font, "Cost: " + (int) spell.getCost(), 50, (this.height) / 2 + 40, 0x00FFFFFF);
            // TODO: Draw the spell difficulty (spell#getDifficulty)
        } else
            graphics.drawString(font, "Cooldown: " + (int)spell.getCooldown(), 50, (this.height) / 2 + 40, 0x00FFFFFF);

        // Draw the spell entity?
//        minecraft.getTextureManager().bind(spell.getDisplayAnimation());
        matrixStack.pushPose();
        RenderUtil.bind(spell.getDisplayAnimation());
        currentSpellFrame = (int)(lastTick + (currentTick - lastTick) * partialTicks) / 64;
        int uOffset = 64 * (currentSpellFrame % 5), vOffset = 0;
        RenderUtil.blitWithBlend(matrixStack, 88, (this.height / 2) - 94, uOffset, vOffset, 64, 64, 320, 64, 1, 1);
        matrixStack.popPose();
//        minecraft.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
    }

    private void renderMagicka(GuiGraphics graphics, PoseStack matrixStack, int width, int height) {
//        minecraft.getTextureManager().bind(OVERLAY_ICONS);
        RenderUtil.bind(OVERLAY_ICONS);
        float magicka = minecraft.player.getData(PlayerAttachments.MAGICKA);
        float maxMagicka = minecraft.player.getData(PlayerAttachments.MAX_MAGICKA);
        float magickaPercentage = magicka / maxMagicka;
        float magickaBarWidth = 80.0f * magickaPercentage;
        float magickaBarStartX = (float)(width - 109) + (80.0f - magickaBarWidth);
        RenderUtil.blitWithBlend(matrixStack, this.width - 120, this.height - 25, 0, 51, 102, 10, 256, 256, 1, 1);
        RenderUtil.blitWithBlend(matrixStack, this.width - 109, this.height - 23, 11 + ((80 - magickaBarWidth) / 2.0f), 64, 80 * magickaPercentage, 6, 256, 256, 1, 1);
//        minecraft.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
    }

    @Override
    public void tick() {
        if(!spellTypeChosen)
            currentTick = 0;
        else {
            currentTick = lastTick;
            lastTick += 32f; // 32f
//            if(currentSpellFrame > 14)
//                currentSpellFrame = 0;
        }

        super.tick();
    }

//    @Override
//    public boolean mouseScrolled(double mouseX, double mouseY, double scrollDelta) {
//        if(scrollDelta > 0) {
//            if (!this.spellTypeChosen) {
//                if (this.currentSpellType < this.spellTypes.size() - 1)
//                    ++this.currentSpellType;
//            } else {
//                if (this.currentSpell < this.spellsListForCurrentSpellType.size() - 1)
//                    ++this.currentSpell;
//            }
//        } else if(scrollDelta < 0) {
//            if (!this.spellTypeChosen) {
//                if(this.currentSpellType > 0)
//                    --this.currentSpellType;
//            } else {
//                if (this.currentSpell > 0)
//                    --this.currentSpell;
//            }
//        }
//        return true;
//    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(KeyEvents.SKYRIM_MENU_SOUTH.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            if(!this.spellTypeChosen) {
                if(this.currentSpellType < this.spellTypes.size()-1)
                    ++this.currentSpellType;
                else
                    this.currentSpellType = this.spellTypes.size()-1;
            } else {
                if(this.currentSpell >= 0 && this.currentSpell < spellsListForCurrentSpellType.size()-1)
                    this.currentSpell++;
            }
        }

        if(KeyEvents.SKYRIM_MENU_NORTH.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            if(!this.spellTypeChosen) {
                if(this.currentSpellType > 0)
                    --this.currentSpellType;
                else
                    this.currentSpellType = 0;
            } else {
                if(this.currentSpell > 0 && this.currentSpell < spellsListForCurrentSpellType.size())
                    this.currentSpell--;
            }
        }

        if(KeyEvents.SKYRIM_MENU_WEST.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            if(this.spellTypeChosen) {
                this.spellTypeChosen = false;
                this.currentSpell = 0;
            }
        }

        if(KeyEvents.SKYRIM_MENU_EAST.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            if(!this.spellTypeChosen) {
                this.spellTypeChosen = true;
                this.currentSpell = 0;
            }
        }

        if(KeyEvents.SPELL_SLOT_1_KEY.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            ISpell selectedSpell1 = minecraft.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1();
            ISpell selectedSpell2 = minecraft.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2();

            if(selectedSpell1 != currentSpellObject) {
                if (selectedSpell2 != currentSpellObject) {
                    final UpdateSelectedSpells updatedSpells0 = new UpdateSelectedSpells(0, currentSpellObject.getID());
                    PacketDistributor.SERVER.noArg().send(updatedSpells0);
                } else {
                    final UpdateSelectedSpells updatedSpells0 = new UpdateSelectedSpells(1, minecraft.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1().getID());
                    PacketDistributor.SERVER.noArg().send(updatedSpells0);
                    final UpdateSelectedSpells updatedSpells1 = new UpdateSelectedSpells(0, currentSpellObject.getID());
                    PacketDistributor.SERVER.noArg().send(updatedSpells1);
                }
            } else {
                final UpdateSelectedSpells updatedSpells0 = new UpdateSelectedSpells(0, -1);
                PacketDistributor.SERVER.noArg().send(updatedSpells0);
            }
        }

        if(KeyEvents.SPELL_SLOT_2_KEY.get().isActiveAndMatches(InputConstants.getKey(keyCode, scanCode))) {
            ISpell selectedSpell1 = minecraft.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1();
            ISpell selectedSpell2 = minecraft.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2();

            if(selectedSpell2 != currentSpellObject) {
                if (selectedSpell1 != currentSpellObject) {
                    final UpdateSelectedSpells updatedSpells0 = new UpdateSelectedSpells(1, currentSpellObject.getID());
                    PacketDistributor.SERVER.noArg().send(updatedSpells0);
                } else {
                    final UpdateSelectedSpells updatedSpells0 = new UpdateSelectedSpells(0, minecraft.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2().getID());
                    PacketDistributor.SERVER.noArg().send(updatedSpells0);
                    final UpdateSelectedSpells updatedSpells1 = new UpdateSelectedSpells(1, currentSpellObject.getID());
                    PacketDistributor.SERVER.noArg().send(updatedSpells1);
                }
            } else {
                final UpdateSelectedSpells updatedSpells0 = new UpdateSelectedSpells(1, -1);
                PacketDistributor.SERVER.noArg().send(updatedSpells0);
            }
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

//    @Override
//    public boolean handleComponentClicked(@Nullable Style style) {
//        return false;
//    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return true;
    }

    @Override
    public void removed() {
        super.removed();
    }
}