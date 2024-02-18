package com.ryankshah.skyrimcraft.event;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.feature.render.RenderRaceLayer;
import com.ryankshah.skyrimcraft.character.feature.render.SpectralLayerRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value= Dist.CLIENT)
public class RenderLayerEvents
{
    private static boolean hasLayer = false;

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Post event) {
        Player player = event.getEntity();
        if(player instanceof AbstractClientPlayer) {
            if (!hasLayer) {
                event.getRenderer().addLayer(new RenderRaceLayer(event.getRenderer()));
                event.getRenderer().addLayer(new SpectralLayerRenderer(event.getRenderer(), event.getPackedLight()));
                hasLayer = true;
            }
        }
    }
}