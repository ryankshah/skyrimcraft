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

public record UpdateMagicka(float magicka, float maxMagicka, float magickaRegenModifier) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updatemagicka");

    public UpdateMagicka(final FriendlyByteBuf buffer) {
        this(buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeFloat(magicka);
        buffer.writeFloat(maxMagicka);
        buffer.writeFloat(magickaRegenModifier);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final UpdateMagicka data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();

        player.setData(PlayerAttachments.MAX_MAGICKA, data.maxMagicka);

        float curMagicka = data.magicka;
        float maxMagicka = player.getData(PlayerAttachments.MAX_MAGICKA);

        if(curMagicka <= 0.0f)
            curMagicka = 0.0f;
        if(curMagicka >= maxMagicka)
            curMagicka = maxMagicka;

        player.setData(PlayerAttachments.MAGICKA, curMagicka);

        player.setData(PlayerAttachments.MAGICKA_REGEN_MODIFIER, data.magickaRegenModifier);

        final UpdateMagicka sendToClient = new UpdateMagicka(curMagicka, data.maxMagicka, data.magickaRegenModifier);
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateMagicka data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            player.setData(PlayerAttachments.MAGICKA, data.magicka);
            player.setData(PlayerAttachments.MAX_MAGICKA, data.maxMagicka);
            player.setData(PlayerAttachments.MAGICKA_REGEN_MODIFIER, data.magickaRegenModifier);
        });
    }
}