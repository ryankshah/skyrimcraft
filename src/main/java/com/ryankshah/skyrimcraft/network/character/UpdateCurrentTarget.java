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

public record UpdateCurrentTarget(int target) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updatecurrenttarget");

    public UpdateCurrentTarget(final FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeInt(target);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final UpdateCurrentTarget data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();

        player.setData(PlayerAttachments.PLAYER_TARGETS, new PlayerTargetsHandler(player.getData(PlayerAttachments.PLAYER_TARGETS).getTargets(), data.target));

        final UpdateCurrentTarget sendToClient = new UpdateCurrentTarget(data.target);
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateCurrentTarget data, final PlayPayloadContext context) {
        Player player = context.player().orElseThrow();
        player.setData(PlayerAttachments.PLAYER_TARGETS, new PlayerTargetsHandler(player.getData(PlayerAttachments.PLAYER_TARGETS).getTargets(), data.target));
    }
}