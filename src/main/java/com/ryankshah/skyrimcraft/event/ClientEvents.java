package com.ryankshah.skyrimcraft.event;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.ItemInit;
import com.ryankshah.skyrimcraft.init.ParticleInit;
import com.ryankshah.skyrimcraft.particle.EmittingLightningParticle;
import com.ryankshah.skyrimcraft.particle.FireParticle;
import com.ryankshah.skyrimcraft.particle.LightningParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents
{
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(ItemInit::registerItemModelProperties);
    }

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleInit.LIGHTNING.get(), LightningParticle.Provider::new);
        event.registerSpriteSet(ParticleInit.EMITTING_LIGHTNING.get(), EmittingLightningParticle.Provider::new);
        event.registerSpriteSet(ParticleInit.FIRE.get(), FireParticle.Provider::new);
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
