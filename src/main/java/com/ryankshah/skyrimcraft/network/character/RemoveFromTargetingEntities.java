package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.PlayerTargetsHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.List;

public record RemoveFromTargetingEntities(int entityId) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"removefromtargetingentities");

    public RemoveFromTargetingEntities(final FriendlyByteBuf buffer) {
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

    public static void handleServer(final RemoveFromTargetingEntities data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();
        List<Integer> targets = player.getData(PlayerAttachments.PLAYER_TARGETS).getTargets();
        targets.remove(data.entityId());
        player.setData(PlayerAttachments.PLAYER_TARGETS, new PlayerTargetsHandler(targets, player.getData(PlayerAttachments.PLAYER_TARGETS).getCurrentTarget()));

        final AddToTargetingEntities sendToClient = new AddToTargetingEntities(data.entityId);
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final RemoveFromTargetingEntities data, final PlayPayloadContext context) {
        Player player = context.player().orElseThrow();
        List<Integer> targets = player.getData(PlayerAttachments.PLAYER_TARGETS).getTargets();
        targets.remove(data.entityId());
        player.setData(PlayerAttachments.PLAYER_TARGETS, new PlayerTargetsHandler(targets, player.getData(PlayerAttachments.PLAYER_TARGETS).getCurrentTarget()));
    }
}

