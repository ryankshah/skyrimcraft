package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.network.character.UpdateCompassFeatureHandlerOnClient;
import com.ryankshah.skyrimcraft.util.CompassFeature;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class CompassFeatureHandler implements INBTSerializable<CompoundTag>
{
    private List<CompassFeature> compassFeatures;

    public static Codec<CompassFeatureHandler> COMPASS_FEATURE_HANDLER_CODEC = RecordCodecBuilder.create(skillsHandlerInstance -> skillsHandlerInstance.group(
            CompassFeature.CODEC.listOf().fieldOf("compassFeatures").forGetter(CompassFeatureHandler::getCompassFeatures)
    ).apply(skillsHandlerInstance, CompassFeatureHandler::new));

    public CompassFeatureHandler() {
        this.compassFeatures = new ArrayList<>();
    }

    public CompassFeatureHandler(List<CompassFeature> features) {
        this.compassFeatures = features;
    }

    public CompassFeatureHandler(IAttachmentHolder iAttachmentHolder) {
        this.compassFeatures = new ArrayList<>();
    }

    public List<CompassFeature> getCompassFeatures() {
        return compassFeatures;
    }

    public void addCompassFeature(CompassFeature feature) {
        this.compassFeatures.add(feature);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putInt("mapFeaturesSize", compassFeatures.size());
        int counter = 0;
        for(CompassFeature feature : compassFeatures) {
            tag.put("feature"+counter++, feature.serialise());
        }

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        int mapFeaturesSize = tag.getInt("mapFeaturesSize");
        for(int i = 0; i < mapFeaturesSize; i++) {
            CompoundTag comp = tag.getCompound("feature"+i);
            compassFeatures.add(CompassFeature.deserialise(comp));
        }
    }

    public static void register(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(new CompassFeatureHandlerEvents());
    }

    public static CompassFeatureHandler get(LivingEntity player) {
        return player.getData(PlayerAttachments.COMPASS_FEATURES);
    }

    private void syncToSelf(Player owner) {
        syncTo(owner);
    }

    protected void syncTo(Player owner) {
        PacketDistributor.PLAYER.with((ServerPlayer) owner).send(new UpdateCompassFeatureHandlerOnClient(this));
    }

    protected void syncTo(PacketDistributor.PacketTarget target, Player owner) {
        target.send(new UpdateCompassFeatureHandlerOnClient(this));
    }

    private static class CompassFeatureHandlerEvents
    {
        @SubscribeEvent
        public void attachCapabilities(EntityJoinLevelEvent event)
        {
        }

        @SubscribeEvent
        public void joinWorld(PlayerEvent.PlayerLoggedInEvent event)
        {
            Player target = event.getEntity();
            if (target.level().isClientSide)
                return;
            get(target).syncToSelf(target);
        }

        @SubscribeEvent
        public void joinWorld(PlayerEvent.PlayerChangedDimensionEvent event)
        {
            Player target = event.getEntity();
            if (target.level().isClientSide)
                return;
            get(target).syncToSelf(target);
        }

        @SubscribeEvent
        public void track(PlayerEvent.StartTracking event)
        {
            Entity target = event.getTarget();
            if (target.level().isClientSide)
                return;
            if (target instanceof Player)
            {
                get((Player)target).syncToSelf((Player)target);
            }
        }

        @SubscribeEvent
        public void playerClone(PlayerEvent.Clone event) {
            Player oldPlayer = event.getOriginal();
            Player newPlayer = event.getEntity();
            var oldHandler = get(oldPlayer);
            var newHandler = get(newPlayer);

            newHandler.compassFeatures = oldHandler.compassFeatures;

            newPlayer.setData(PlayerAttachments.COMPASS_FEATURES, newHandler);
            PacketDistributor.PLAYER.with((ServerPlayer) newPlayer).send(new UpdateCompassFeatureHandlerOnClient(newHandler));
        }
    }
}
