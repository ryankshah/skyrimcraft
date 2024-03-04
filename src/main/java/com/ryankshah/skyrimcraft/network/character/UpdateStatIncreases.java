package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.StatIncreases;
import com.ryankshah.skyrimcraft.init.AttributeInit;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record UpdateStatIncreases(StatIncreases statIncreases) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updatestatincreases");

    public UpdateStatIncreases(final FriendlyByteBuf buffer) {
        this(buffer.readJsonWithCodec(StatIncreases.CODEC));
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeJsonWithCodec(StatIncreases.CODEC, statIncreases);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final UpdateStatIncreases data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();

        StatIncreases oldStats = player.getData(PlayerAttachments.STAT_INCREASES);
        if(data.statIncreases.getHealthIncrease() > oldStats.getHealthIncrease()) {
            AttributeInit.setMaxHealth(player, data.statIncreases.getHealthIncrease(), AttributeModifier.Operation.ADDITION);
        }

        player.setData(PlayerAttachments.STAT_INCREASES, data.statIncreases);
        player.setData(PlayerAttachments.LEVEL_UPDATES, player.getData(PlayerAttachments.LEVEL_UPDATES)-1);

        final UpdateLevelUpdates levelUpdates = new UpdateLevelUpdates(player.getData(PlayerAttachments.LEVEL_UPDATES)-1);
        PacketDistributor.PLAYER.with(player).send(levelUpdates);

        final UpdateStatIncreases sendToClient = new UpdateStatIncreases(player.getData(PlayerAttachments.STAT_INCREASES));
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateStatIncreases data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Minecraft.getInstance().player.setData(PlayerAttachments.STAT_INCREASES, data.statIncreases);
        });
    }
}