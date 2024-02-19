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
        context.workHandler().submitAsync(() -> {
                    ServerPlayer player = (ServerPlayer) context.player().orElseThrow();

                    // TODO: Check if we need to add this on client too
//                    if (player instanceof ServerPlayer) {
                    List<Spell> knownSpells = player.getData(PlayerAttachments.KNOWN_SPELLS).getKnownSpells();
//                    System.out.println("AddToKnownSpells: " + knownSpells);
                    Spell spell = SpellRegistry.SPELLS_REGISTRY.get(data.spell);

//                            SpellRegistry.SPELLS_REGISTRY.stream().filter(s -> s.getID() == data.spell)
//                            .findFirst().orElseThrow();
                    knownSpells.add(spell);
//                        System.out.println("AddToKnownSpells: " + knownSpells);
                    player.setData(PlayerAttachments.KNOWN_SPELLS, new SpellHandler(knownSpells, player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1(), player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2(), player.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown()));

                    final AddToKnownSpells sendToClient = new AddToKnownSpells(data.spell);
                    PacketDistributor.PLAYER.with(player).send(sendToClient);
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleClient(final AddToKnownSpells data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    Player player = context.player().orElseThrow();

                    // TODO: Check if we need to add this on client too
//                    if (player instanceof ServerPlayer) {
                    List<Spell> knownSpells = player.getData(PlayerAttachments.KNOWN_SPELLS).getKnownSpells();
//                    System.out.println("AddToKnownSpells: " + knownSpells);
                    Spell spell = SpellRegistry.SPELLS_REGISTRY.get(data.spell);
                    knownSpells.add(spell);
//                        System.out.println("AddToKnownSpells: " + knownSpells);
                    player.setData(PlayerAttachments.KNOWN_SPELLS, new SpellHandler(knownSpells, player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1(), player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2(), player.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown()));
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }
}

