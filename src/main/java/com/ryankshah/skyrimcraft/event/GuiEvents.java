package com.ryankshah.skyrimcraft.event;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.util.ClientUtil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderGuiOverlayEvent;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value= Dist.CLIENT)
public class GuiEvents
{
    @SubscribeEvent
    public static void renderOverlays(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay() == VanillaGuiOverlay.BOSS_EVENT_PROGRESS.type() && !ClientUtil.getMinecraft().options.hideGui && !event.isCanceled()) {
            event.getGuiGraphics().pose().pushPose();
            event.getGuiGraphics().pose().translate(0, 28, 0);
            event.getGuiGraphics().pose().popPose();
        }

        if(event.getOverlay() == VanillaGuiOverlay.PLAYER_HEALTH.type()
            || event.getOverlay() == VanillaGuiOverlay.CROSSHAIR.type()
            || event.getOverlay() == VanillaGuiOverlay.FOOD_LEVEL.type()
                || event.getOverlay() == VanillaGuiOverlay.ARMOR_LEVEL.type()
                || event.getOverlay() == VanillaGuiOverlay.AIR_LEVEL.type()
                || event.getOverlay() == VanillaGuiOverlay.EXPERIENCE_BAR.type()
        ) {
            event.setCanceled(true);
        }
    }
}