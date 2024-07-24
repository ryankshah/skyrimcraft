package com.ryankshah.skyrimcraft.network.spell;

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

public record ConsumeMagicka(float amount) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<ConsumeMagicka> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "consumemagicka"));

    public static final StreamCodec<ByteBuf, ConsumeMagicka> CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            ConsumeMagicka::amount,
            ConsumeMagicka::new
    );

    public ConsumeMagicka(final FriendlyByteBuf buffer) {
        this(buffer.readFloat());
    }

    public static void handleServer(final ConsumeMagicka data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        Character character = Character.get(player);
        character.setMagicka(data.amount);

        final ConsumeMagicka sendToClient = new ConsumeMagicka(data.amount);
        PacketDistributor.sendToPlayer(player, sendToClient);
//        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final ConsumeMagicka data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            Character character = Character.get(player);
            character.setMagicka(data.amount);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}