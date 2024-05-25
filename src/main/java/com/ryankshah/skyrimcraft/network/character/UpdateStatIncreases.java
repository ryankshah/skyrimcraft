package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.StatIncreases;
import com.ryankshah.skyrimcraft.init.AttributeInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UpdateStatIncreases(StatIncreases statIncreases) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<UpdateStatIncreases> TYPE = new Type<>(new ResourceLocation(Skyrimcraft.MODID, "updatestatincreases"));

    public static final StreamCodec<ByteBuf, UpdateStatIncreases> CODEC = StreamCodec.composite(
            StatIncreases.STREAM_CODEC,
            UpdateStatIncreases::statIncreases,
            UpdateStatIncreases::new
    );

    public UpdateStatIncreases(final FriendlyByteBuf buffer) {
        this(buffer.readJsonWithCodec(StatIncreases.CODEC));
    }

    public static void handleServer(final UpdateStatIncreases data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();

        StatIncreases oldStats = player.getData(PlayerAttachments.STAT_INCREASES);
        if(data.statIncreases.getHealthIncrease() > oldStats.getHealthIncrease()) {
            AttributeInit.setMaxHealth(player, data.statIncreases.getHealthIncrease(), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        } else if(data.statIncreases.getMagickaIncrease() > oldStats.getMagickaIncrease()) {
            AttributeInit.setMaxMagicka(player, data.statIncreases.getMagickaIncrease(), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        }

        player.setData(PlayerAttachments.STAT_INCREASES, data.statIncreases);
        player.setData(PlayerAttachments.LEVEL_UPDATES, player.getData(PlayerAttachments.LEVEL_UPDATES)-1);

        final UpdateLevelUpdates levelUpdates = new UpdateLevelUpdates(player.getData(PlayerAttachments.LEVEL_UPDATES)-1);
        PacketDistributor.sendToPlayer(player, levelUpdates);
//        PacketDistributor.PLAYER.with(player).send(levelUpdates);

        final UpdateStatIncreases sendToClient = new UpdateStatIncreases(player.getData(PlayerAttachments.STAT_INCREASES));
        PacketDistributor.sendToPlayer(player, sendToClient);
//        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateStatIncreases data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = minecraft.player;

            StatIncreases oldStats = player.getData(PlayerAttachments.STAT_INCREASES);
            if(data.statIncreases.getHealthIncrease() > oldStats.getHealthIncrease()) {
                AttributeInit.setMaxHealth(player, data.statIncreases.getHealthIncrease(), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            } else if(data.statIncreases.getMagickaIncrease() > oldStats.getMagickaIncrease()) {
                AttributeInit.setMaxMagicka(player, data.statIncreases.getMagickaIncrease(), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            }

            Minecraft.getInstance().player.setData(PlayerAttachments.STAT_INCREASES, data.statIncreases);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}