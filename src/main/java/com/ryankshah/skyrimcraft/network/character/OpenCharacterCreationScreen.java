package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.screen.CharacterCreationScreen;
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
import org.apache.logging.log4j.core.jmx.Server;

public record OpenCharacterCreationScreen(boolean hasSetup) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<OpenCharacterCreationScreen> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "opencharactercreationscreen"));

    public static final StreamCodec<ByteBuf, OpenCharacterCreationScreen> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            OpenCharacterCreationScreen::hasSetup,
            OpenCharacterCreationScreen::new
    );

    public OpenCharacterCreationScreen(final FriendlyByteBuf buffer) {
        this(buffer.readBoolean());
    }

    public static void handleServer(final OpenCharacterCreationScreen data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        Character character = Character.get(player);

        character.setHasSetup(true);

        final OpenCharacterCreationScreen sendToClient = new OpenCharacterCreationScreen(data.hasSetup);
        PacketDistributor.sendToPlayer(player, sendToClient);
//      PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final OpenCharacterCreationScreen data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            Character character = Character.get(player);
            character.setHasSetup(true);
            Minecraft.getInstance().setScreen(new CharacterCreationScreen());
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}