package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UpdateCurrentTarget(int target) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<UpdateCurrentTarget> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "updatecurrenttarget"));

    public static final StreamCodec<ByteBuf, UpdateCurrentTarget> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            UpdateCurrentTarget::target,
            UpdateCurrentTarget::new
    );

    public UpdateCurrentTarget(final FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

    public static void handleServer(final UpdateCurrentTarget data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        Character character = Character.get(player);

        character.addTarget(data.target);

        final UpdateCurrentTarget sendToClient = new UpdateCurrentTarget(data.target);
        PacketDistributor.sendToPlayer(player, sendToClient);
//        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateCurrentTarget data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            Character character = Character.get(player);

            character.addTarget(data.target);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}