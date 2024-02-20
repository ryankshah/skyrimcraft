package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record UpdateSelectedSpell(int position, ResourceKey<Spell> spell) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updateselectedspells");

    public UpdateSelectedSpell(final FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readResourceKey(SpellRegistry.SPELLS_KEY));
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeInt(position);
        buffer.writeResourceKey(spell);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final UpdateSelectedSpell data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();
        if(!data.spell.equals(SpellRegistry.EMPTY_SPELL)) {
            Spell spell = SpellRegistry.SPELLS_REGISTRY.get(data.spell);
            if(data.position == 1)
                player.getData(PlayerAttachments.KNOWN_SPELLS).setSelectedSpell1(spell);
            else if(data.position == 2)
                player.getData(PlayerAttachments.KNOWN_SPELLS).setSelectedSpell2(spell);

            final UpdateSpellHandlerOnClient sendToClient = new UpdateSpellHandlerOnClient(player.getData(PlayerAttachments.KNOWN_SPELLS));
            PacketDistributor.PLAYER.with(player).send(sendToClient);
        }
    }

    public static void handleClient(final UpdateSelectedSpell data, final PlayPayloadContext context) {
    }
}

