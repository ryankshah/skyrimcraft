package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.screen.CharacterCreationScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record OpenCharacterCreationScreen(boolean hasSetup) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"opencharactercreationscreen");

    public OpenCharacterCreationScreen(final FriendlyByteBuf buffer) {
        this(buffer.readBoolean());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeBoolean(hasSetup);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final OpenCharacterCreationScreen data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    ServerPlayer player = (ServerPlayer) context.player().orElseThrow();

                    player.setData(PlayerAttachments.HAS_SETUP.get(), true);

                    final OpenCharacterCreationScreen sendToClient = new OpenCharacterCreationScreen(data.hasSetup);
                    PacketDistributor.PLAYER.with(player).send(sendToClient);
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleClient(final OpenCharacterCreationScreen data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    Minecraft.getInstance().player.setData(PlayerAttachments.HAS_SETUP.get(), true);
                    Minecraft.getInstance().setScreen(new CharacterCreationScreen());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }
}