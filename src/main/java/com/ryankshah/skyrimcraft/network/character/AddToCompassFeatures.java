package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.util.CompassFeature;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record AddToCompassFeatures(String uuid, ResourceLocation location, BlockPos blockPos) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"addtocompassfeatures");

    public AddToCompassFeatures(final FriendlyByteBuf buffer) {
        this(buffer.readUtf(), buffer.readResourceLocation(), buffer.readBlockPos());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeUtf(uuid);
        buffer.writeResourceLocation(location);
        buffer.writeBlockPos(blockPos);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final AddToCompassFeatures data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();
        Character character = Character.get(player);
        character.addCompassFeature(new CompassFeature(data.uuid, TagKey.create(Registries.STRUCTURE, data.location), data.blockPos));

        final AddToCompassFeatures sendToClient = new AddToCompassFeatures(data.uuid, data.location, data.blockPos);
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final AddToCompassFeatures data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            if(Minecraft.getInstance().player != null) {
                Player player = Minecraft.getInstance().player;
                Character character = Character.get(player);
                character.addCompassFeature(new CompassFeature(data.uuid, TagKey.create(Registries.STRUCTURE, data.location), data.blockPos));
            }
        });
    }
}

