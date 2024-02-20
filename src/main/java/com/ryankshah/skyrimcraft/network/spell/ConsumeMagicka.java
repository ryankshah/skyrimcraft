package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record ConsumeMagicka(float amount) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"consumemagicka");

    public ConsumeMagicka(final FriendlyByteBuf buffer) {
        this(buffer.readFloat());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeFloat(amount);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final ConsumeMagicka data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();
        context.player().get().setData(PlayerAttachments.MAGICKA, data.amount);

        final ConsumeMagicka sendToClient = new ConsumeMagicka(data.amount);
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final ConsumeMagicka data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            player.setData(PlayerAttachments.MAGICKA, data.amount);
        });
    }
}