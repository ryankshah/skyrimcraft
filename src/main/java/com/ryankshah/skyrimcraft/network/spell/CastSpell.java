package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CastSpell(ResourceKey<Spell> spell) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<CastSpell> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "castspell"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CastSpell> CODEC = StreamCodec.composite(
            ResourceKey.streamCodec(SpellRegistry.SPELLS_KEY),
            CastSpell::spell,
            CastSpell::new
    );

    public CastSpell(final RegistryFriendlyByteBuf buffer) {
        this(buffer.readResourceKey(SpellRegistry.SPELLS_KEY));
    }

    public static void handle(final CastSpell data, final IPayloadContext context) {
        Player player = context.player();

        if (player instanceof ServerPlayer serverPlayer) {
            Spell spellInstance = SpellRegistry.SPELLS_REGISTRY.get(data.spell);
            spellInstance.setCaster(serverPlayer);
            spellInstance.cast();
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
