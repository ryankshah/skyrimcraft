package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record UpdateCharacter(boolean hasSetup, int level, int xp) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updatecharacter");

    public UpdateCharacter(final FriendlyByteBuf buffer) {
        this(buffer.readBoolean(), buffer.readInt(), buffer.readInt());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeBoolean(hasSetup);
        buffer.writeInt(level);
        buffer.writeInt(xp);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final UpdateCharacter data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();

        player.setData(PlayerAttachments.HAS_SETUP, data.hasSetup);
        player.setData(PlayerAttachments.CHARACTER_LEVEL, data.level);
        player.setData(PlayerAttachments.CHARACTER_TOTAL_XP, data.xp);

        final UpdateCharacter sendToClient = new UpdateCharacter(data.hasSetup, data.level, data.xp);
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateCharacter data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            player.setData(PlayerAttachments.HAS_SETUP, data.hasSetup);
            player.setData(PlayerAttachments.CHARACTER_LEVEL, data.level);
            player.setData(PlayerAttachments.CHARACTER_TOTAL_XP, data.xp);
        });
    }
}