package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record CastSpell(int spellID) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"castspell");

    public CastSpell(final FriendlyByteBuf buffer) {
        this(buffer.readInt());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeInt(spellID);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handle(final CastSpell data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    Player player = context.player().orElseThrow();

                    if (player instanceof ServerPlayer) {
                        Spell spellInstance = SpellRegistry.SPELLS_REGISTRY.stream().filter(spell -> spell.getID() == data.spellID).findFirst().orElseThrow();
                        spellInstance.setCaster(player);
                        spellInstance.cast();
//                        player.setData(PlayerAttachments.KNOWN_SPELLS, new SpellHandler(knownSpells, player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpells(), player.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown()));
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }
}
