package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
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
        context.workHandler().submitAsync(() -> {
                    ServerPlayer player = (ServerPlayer) context.player().orElseThrow();

                    float newMagicka = data.amount;

                    if(context.player().get().getData(PlayerAttachments.MAGICKA) - data.amount <= 0.0f)
                        newMagicka = 0.0f;
                    context.player().get().setData(PlayerAttachments.MAGICKA, newMagicka);

                    final ConsumeMagicka sendToClient = new ConsumeMagicka(data.amount);
                    PacketDistributor.PLAYER.with(player).send(sendToClient);
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleClient(final ConsumeMagicka data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    Player player = context.player().orElseThrow();
                    float newMagicka = data.amount;

                    if(context.player().get().getData(PlayerAttachments.MAGICKA) - data.amount <= 0.0f)
                        newMagicka = 0.0f;
                    context.player().get().setData(PlayerAttachments.MAGICKA, newMagicka);
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }
}