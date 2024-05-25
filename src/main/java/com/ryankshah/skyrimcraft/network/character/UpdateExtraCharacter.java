package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.ExtraCharacter;
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

public record UpdateExtraCharacter(ExtraCharacter character) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<UpdateExtraCharacter> TYPE = new Type<>(new ResourceLocation(Skyrimcraft.MODID, "updateextracharacter"));

    public static final StreamCodec<ByteBuf, UpdateExtraCharacter> CODEC = StreamCodec.composite(
            ExtraCharacter.STREAM_CODEC,
            UpdateExtraCharacter::character,
            UpdateExtraCharacter::new
    );

    public UpdateExtraCharacter(final FriendlyByteBuf buffer) {
        this(buffer.readJsonWithCodec(ExtraCharacter.CODEC));
    }

    public static void handleServer(final UpdateExtraCharacter data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        player.setData(PlayerAttachments.EXTRA_CHARACTER, data.character);
        final UpdateExtraCharacter sendToClient = new UpdateExtraCharacter(player.getData(PlayerAttachments.EXTRA_CHARACTER));
        PacketDistributor.sendToPlayer(player, sendToClient);
//        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateExtraCharacter data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Minecraft.getInstance().player.setData(PlayerAttachments.EXTRA_CHARACTER, data.character);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}