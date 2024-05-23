package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UpdateShoutCooldown(ResourceKey<Spell> spell, float cooldown) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<UpdateShoutCooldown> TYPE = new Type<>(new ResourceLocation(Skyrimcraft.MODID, "updateshoutcooldown"));

    public static final StreamCodec<ByteBuf, UpdateShoutCooldown> CODEC = StreamCodec.composite(
            ResourceKey.streamCodec(SpellRegistry.SPELLS_KEY),
            UpdateShoutCooldown::spell,
            ByteBufCodecs.FLOAT,
            UpdateShoutCooldown::cooldown,
            UpdateShoutCooldown::new
    );

    public UpdateShoutCooldown(final FriendlyByteBuf buffer) {
        this(buffer.readResourceKey(SpellRegistry.SPELLS_KEY), buffer.readFloat());
    }

    public static void handleServer(final UpdateShoutCooldown data, final IPayloadContext context) {
        Player player = context.player();

        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            Character character = Character.get(serverPlayer);

            character.addSpellAndCooldown(SpellRegistry.SPELLS_REGISTRY.get(data.spell), data.cooldown);

            final UpdateShoutCooldown sendToClient = new UpdateShoutCooldown(data.spell, data.cooldown);
            PacketDistributor.sendToPlayer(serverPlayer, sendToClient);
//            PacketDistributor.PLAYER.with(serverPlayer).send(sendToClient);
        }
    }

    public static void handleClient(final UpdateShoutCooldown data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            Character character = Character.get(player);
            character.addSpellAndCooldown(SpellRegistry.SPELLS_REGISTRY.get(data.spell), data.cooldown);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
