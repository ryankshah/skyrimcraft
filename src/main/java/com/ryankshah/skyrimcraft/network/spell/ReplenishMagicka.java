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

public record ReplenishMagicka(int amount) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"replenishmagicka");

    public ReplenishMagicka(final FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeInt(amount);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final ReplenishMagicka data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();
        float newMagicka = player.getData(PlayerAttachments.MAGICKA) + player.getData(PlayerAttachments.MAX_MAGICKA);
        player.setData(PlayerAttachments.MAGICKA, newMagicka >= player.getData(PlayerAttachments.MAX_MAGICKA) ? player.getData(PlayerAttachments.MAX_MAGICKA) : newMagicka);
        final ReplenishMagicka sendToClient = new ReplenishMagicka(data.amount);
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final ReplenishMagicka data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            float newMagicka = player.getData(PlayerAttachments.MAGICKA) + player.getData(PlayerAttachments.MAX_MAGICKA);
            player.setData(PlayerAttachments.MAGICKA, newMagicka >= player.getData(PlayerAttachments.MAX_MAGICKA) ? player.getData(PlayerAttachments.MAX_MAGICKA) : newMagicka);
        });
    }
}