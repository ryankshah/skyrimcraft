package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UpdateCharacter(Character character) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<UpdateCharacter> TYPE = new Type<>(new ResourceLocation(Skyrimcraft.MODID, "updatecharacter"));

    public static final StreamCodec<RegistryFriendlyByteBuf, UpdateCharacter> CODEC = StreamCodec.composite(
            Character.STREAM_CODEC,
            UpdateCharacter::character,
            UpdateCharacter::new
    );

    public UpdateCharacter(final FriendlyByteBuf buffer) {
        this(buffer.readJsonWithCodec(Character.CODEC));
    }

    public static void handleServer(final UpdateCharacter data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        player.setData(PlayerAttachments.CHARACTER, data.character);
        final UpdateCharacter sendToClient = new UpdateCharacter(player.getData(PlayerAttachments.CHARACTER));
        PacketDistributor.sendToPlayer(player, sendToClient);
//        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateCharacter data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Minecraft.getInstance().player.setData(PlayerAttachments.CHARACTER, data.character);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}