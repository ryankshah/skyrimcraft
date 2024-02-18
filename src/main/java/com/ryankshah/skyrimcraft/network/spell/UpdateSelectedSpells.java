package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.SpellHandler;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record UpdateSelectedSpells(int position, int spell) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updateselectedspells");

    public UpdateSelectedSpells(final FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readInt());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeInt(position);
        buffer.writeInt(spell);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final UpdateSelectedSpells data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    ServerPlayer player = (ServerPlayer) context.player().orElseThrow();

                    // TODO: Check if we need to add this on client too
                    if(data.spell != -1) {
                        Spell spell = SpellRegistry.SPELLS_REGISTRY.stream().filter(s -> s.getID() == data.spell)
                                .findFirst().orElseThrow();
                        if(data.position == 1)
                            player.setData(PlayerAttachments.KNOWN_SPELLS, new SpellHandler(player.getData(PlayerAttachments.KNOWN_SPELLS).getKnownSpells(), spell, player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2(), player.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown()));
                        if(data.position == 2)
                            player.setData(PlayerAttachments.KNOWN_SPELLS, new SpellHandler(player.getData(PlayerAttachments.KNOWN_SPELLS).getKnownSpells(), player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1(), spell, player.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown()));

                        final UpdateSelectedSpells sendToClient = new UpdateSelectedSpells(data.position, data.spell);
                        PacketDistributor.PLAYER.with(player).send(sendToClient);
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleClient(final UpdateSelectedSpells data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    Player player = context.player().orElseThrow();

                    // TODO: Check if we need to add this on client too
//                    if (player instanceof ServerPlayer) {
//                    System.out.println("AddToKnownSpells: " + knownSpells);
                    Spell spell = SpellRegistry.SPELLS_REGISTRY.stream().filter(s -> s.getID() == data.spell)
                            .findFirst().orElseThrow();
                    if(data.position == 1)
                        player.setData(PlayerAttachments.KNOWN_SPELLS, new SpellHandler(player.getData(PlayerAttachments.KNOWN_SPELLS).getKnownSpells(), spell, player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2(), player.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown()));
                    if(data.position == 2)
                        player.setData(PlayerAttachments.KNOWN_SPELLS, new SpellHandler(player.getData(PlayerAttachments.KNOWN_SPELLS).getKnownSpells(), player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1(), spell, player.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown()));

                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }
}

