package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UpdateLevelUpdates(int levelUpdates) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<UpdateLevelUpdates> TYPE = new Type<>(new ResourceLocation(Skyrimcraft.MODID, "updatelevelupdates"));

    public static final StreamCodec<ByteBuf, UpdateLevelUpdates> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            UpdateLevelUpdates::levelUpdates,
            UpdateLevelUpdates::new
    );

    public UpdateLevelUpdates(final FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

    public static void handleServer(final UpdateLevelUpdates data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        player.setData(PlayerAttachments.LEVEL_UPDATES, data.levelUpdates);

        final UpdateLevelUpdates sendToClient = new UpdateLevelUpdates(data.levelUpdates);
        PacketDistributor.sendToPlayer(player, sendToClient);
//        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateLevelUpdates data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Minecraft.getInstance().player.setData(PlayerAttachments.LEVEL_UPDATES, data.levelUpdates);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}