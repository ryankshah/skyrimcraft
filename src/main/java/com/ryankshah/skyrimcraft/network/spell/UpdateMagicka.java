package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UpdateMagicka(float magicka, float maxMagicka, float magickaRegenModifier) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<UpdateMagicka> TYPE = new Type<>(new ResourceLocation(Skyrimcraft.MODID, "updatemagicka"));

    public static final StreamCodec<ByteBuf, UpdateMagicka> CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            UpdateMagicka::magicka,
            ByteBufCodecs.FLOAT,
            UpdateMagicka::maxMagicka,
            ByteBufCodecs.FLOAT,
            UpdateMagicka::magickaRegenModifier,
            UpdateMagicka::new
    );

    public UpdateMagicka(final FriendlyByteBuf buffer) {
        this(buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
    }

    public static void handleServer(final UpdateMagicka data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        Character character = Character.get(player);

        character.setMaxMagicka(data.maxMagicka);

        float curMagicka = data.magicka;
        float maxMagicka = character.getMaxMagicka();

        if(curMagicka <= 0.0f)
            curMagicka = 0.0f;
        if(curMagicka >= maxMagicka)
            curMagicka = maxMagicka;

        character.setMagicka(curMagicka);
        character.setMagickaRegenModifier(data.magickaRegenModifier);

        final UpdateMagicka sendToClient = new UpdateMagicka(curMagicka, data.maxMagicka, data.magickaRegenModifier);
        PacketDistributor.sendToPlayer(player, sendToClient);
//        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final UpdateMagicka data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            Character character = Character.get(player);

            character.setMagicka(data.magicka);
            character.setMaxMagicka(data.maxMagicka);
            character.setMagickaRegenModifier(data.magickaRegenModifier);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}