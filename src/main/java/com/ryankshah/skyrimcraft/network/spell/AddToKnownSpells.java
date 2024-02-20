package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.SpellHandler;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.ArrayList;
import java.util.List;

public record AddToKnownSpells(ResourceKey<Spell> spell) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"addtoknownspells");

    public AddToKnownSpells(final FriendlyByteBuf buffer) {
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

    public static void handleServer(final AddToKnownSpells data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();
        player.getData(PlayerAttachments.KNOWN_SPELLS).addNewSpell(SpellRegistry.SPELLS_REGISTRY.get(data.spell));

        final UpdateSpellHandlerOnClient sendToClient = new UpdateSpellHandlerOnClient(player.getData(PlayerAttachments.KNOWN_SPELLS));
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final AddToKnownSpells data, final PlayPayloadContext context) {
    }
}

