package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.util.CompassFeature;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AddToCompassFeatures(String uuid, ResourceLocation location, BlockPos blockPos) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<AddToCompassFeatures> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "addtocompassfeatures"));
    public static final StreamCodec<ByteBuf, AddToCompassFeatures> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            AddToCompassFeatures::uuid,
            ResourceLocation.STREAM_CODEC,
            AddToCompassFeatures::location,
            BlockPos.STREAM_CODEC,
            AddToCompassFeatures::blockPos,
            AddToCompassFeatures::new
    );

    public AddToCompassFeatures(final FriendlyByteBuf buffer) {
        this(buffer.readUtf(), buffer.readResourceLocation(), buffer.readBlockPos());
    }
    public static void handleServer(final AddToCompassFeatures data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        Character character = Character.get(player);
        character.addCompassFeature(new CompassFeature(data.uuid, TagKey.create(Registries.STRUCTURE, data.location), data.blockPos));

        final AddToCompassFeatures sendToClient = new AddToCompassFeatures(data.uuid, data.location, data.blockPos);
        PacketDistributor.sendToPlayer(player, sendToClient);
//        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final AddToCompassFeatures data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            if(Minecraft.getInstance().player != null) {
                Player player = Minecraft.getInstance().player;
                Character character = Character.get(player);
                character.addCompassFeature(new CompassFeature(data.uuid, TagKey.create(Registries.STRUCTURE, data.location), data.blockPos));
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

