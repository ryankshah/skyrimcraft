package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.CompassFeatureHandler;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.util.CompassFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.List;
import java.util.UUID;

public record AddToCompassFeatures(UUID uuid, ResourceLocation location, BlockPos blockPos) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"addtocompassfeatures");

    public AddToCompassFeatures(final FriendlyByteBuf buffer) {
        this(buffer.readUUID(), buffer.readResourceLocation(), buffer.readBlockPos());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeUUID(uuid);
        buffer.writeResourceLocation(location);
        buffer.writeBlockPos(blockPos);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final AddToCompassFeatures data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    if(context.player().isPresent()) {
                        ServerPlayer player = (ServerPlayer) context.player().get();
                        List<CompassFeature> features = player.getData(PlayerAttachments.COMPASS_FEATURES).getCompassFeatures();
                        features.add(new CompassFeature(data.uuid, TagKey.create(Registries.STRUCTURE, data.location), data.blockPos));
                        player.setData(PlayerAttachments.COMPASS_FEATURES, new CompassFeatureHandler(features));

                        final AddToCompassFeatures sendToClient = new AddToCompassFeatures(data.uuid, data.location, data.blockPos);
                        PacketDistributor.PLAYER.with(player).send(sendToClient);
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleClient(final AddToCompassFeatures data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    if(context.player().isPresent()) {
                        List<CompassFeature> features = context.player().get().getData(PlayerAttachments.COMPASS_FEATURES).getCompassFeatures();
                        features.add(new CompassFeature(data.uuid, TagKey.create(Registries.STRUCTURE, data.location), data.blockPos));
                        context.player().get().setData(PlayerAttachments.COMPASS_FEATURES, new CompassFeatureHandler(features));
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }
}

