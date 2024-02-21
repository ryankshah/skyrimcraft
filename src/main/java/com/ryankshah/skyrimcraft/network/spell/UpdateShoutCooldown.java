package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record UpdateShoutCooldown(ResourceKey<Spell> spell, float cooldown) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updateshoutcooldown");

    public UpdateShoutCooldown(final FriendlyByteBuf buffer) {
        this(buffer.readResourceKey(SpellRegistry.SPELLS_KEY), buffer.readFloat());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeResourceKey(spell);
        buffer.writeFloat(cooldown);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final UpdateShoutCooldown data, final PlayPayloadContext context) {
        Player player = context.player().orElseThrow();

        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            Character character = Character.get(serverPlayer);

            character.addSpellAndCooldown(SpellRegistry.SPELLS_REGISTRY.get(data.spell), data.cooldown);

            final UpdateShoutCooldown sendToClient = new UpdateShoutCooldown(data.spell, data.cooldown);
            PacketDistributor.PLAYER.with(serverPlayer).send(sendToClient);
        }
    }

    public static void handleClient(final UpdateShoutCooldown data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            Character character = Character.get(player);
            character.addSpellAndCooldown(SpellRegistry.SPELLS_REGISTRY.get(data.spell), data.cooldown);
        });
    }
}
