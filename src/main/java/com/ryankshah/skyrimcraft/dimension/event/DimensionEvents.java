package com.ryankshah.skyrimcraft.dimension.event;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.dimension.sovngarde.renderer.SovngardeEffects;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;

@EventBusSubscriber(modid = Skyrimcraft.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DimensionEvents
{
    @SubscribeEvent
    public static void dimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(new ResourceLocation(Skyrimcraft.MODID, "sovngarde"), new SovngardeEffects());
    }
}