package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.PlayerTargetsHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.List;

public record AddToTargetingEntities(int entityId) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"addtotargetingentities");

    public AddToTargetingEntities(final FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeInt(entityId);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final AddToTargetingEntities data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    ServerPlayer player = (ServerPlayer) context.player().orElseThrow();
                    List<Integer> targets = player.getData(PlayerAttachments.PLAYER_TARGETS).getTargets();
                    targets.add(data.entityId());
                    player.setData(PlayerAttachments.PLAYER_TARGETS, new PlayerTargetsHandler(targets, player.getData(PlayerAttachments.PLAYER_TARGETS).getCurrentTarget()));

                    final AddToTargetingEntities sendToClient = new AddToTargetingEntities(data.entityId);
                    PacketDistributor.PLAYER.with(player).send(sendToClient);
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleClient(final AddToTargetingEntities data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread
//        blah(data.name());

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    List<Integer> targets = context.player().get().getData(PlayerAttachments.PLAYER_TARGETS).getTargets();
                    targets.add(data.entityId());
                    context.player().get().setData(PlayerAttachments.PLAYER_TARGETS, new PlayerTargetsHandler(targets, context.player().get().getData(PlayerAttachments.PLAYER_TARGETS).getCurrentTarget()));
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }
}

