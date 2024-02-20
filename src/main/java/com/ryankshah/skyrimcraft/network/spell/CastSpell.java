package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record CastSpell(ResourceKey<Spell> spell) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"castspell");

    public CastSpell(final FriendlyByteBuf buffer) {
        this(buffer.readResourceKey(SpellRegistry.SPELLS_KEY));
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeResourceKey(spell);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handle(final CastSpell data, final PlayPayloadContext context) {
        Player player = context.player().orElseThrow();

        if (player instanceof ServerPlayer) {
            Spell spellInstance = SpellRegistry.SPELLS_REGISTRY.get(data.spell);
            spellInstance.setCaster(player);
            spellInstance.cast();
        }
    }
}
