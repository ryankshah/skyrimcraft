package com.ryankshah.skyrimcraft.event;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.character.feature.model.AbstractSkyrimPlayerModel;
import com.ryankshah.skyrimcraft.character.feature.model.KhajiitFullModel;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;

import java.lang.reflect.Constructor;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents
{
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(ItemInit::registerItemModelProperties);
    }

    public static float noUse(ItemStack sword, ClientLevel clientWorld, LivingEntity entity, int i) {
        return entity != null && entity.getMainHandItem() == sword && entity.getOffhandItem() != ItemStack.EMPTY ? 1.0F : 0.0F;
    }

    public static float blocking(ItemStack itemStack, ClientLevel clientWorld, LivingEntity livingEntity, int i) {
        return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
    }

    public static float pulling(ItemStack bow, ClientLevel clientWorld, LivingEntity livingEntity, int i) {
        return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == bow ? 1.0F : 0.0F;
    }

    public static float pull(ItemStack bow, ClientLevel clientWorld, LivingEntity livingEntity, int i) {
        if (livingEntity == null) {
            return 0.0F;
        } else {
            return livingEntity.getUseItem() != bow ? 0.0F : (float)(bow.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
        }
    }
}
