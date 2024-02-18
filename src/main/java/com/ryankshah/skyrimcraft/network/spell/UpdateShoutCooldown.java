package com.ryankshah.skyrimcraft.network.spell;

import com.mojang.datafixers.util.Pair;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.SpellHandler;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.List;

public record UpdateShoutCooldown(int spellID, float cooldown) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updateshoutcooldown");

    public UpdateShoutCooldown(final FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readFloat());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeInt(spellID);
        buffer.writeFloat(cooldown);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final UpdateShoutCooldown data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    Player player = context.player().orElseThrow();

                    if (player instanceof ServerPlayer) {
                        ServerPlayer serverPlayer = (ServerPlayer) player;
                        List<Pair<Spell, Float>> cooldowns = serverPlayer.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown();

//                        cooldowns.stream().filter(pair -> pair.getFirst().getID() == data.spellID).findFirst().ifPresent(
//                                pair -> new Pair<>(pair.getFirst(), data.cooldown)
//                        );
                        setCooldown(cooldowns, data.spellID, data.cooldown);

                        serverPlayer.setData(PlayerAttachments.KNOWN_SPELLS,
                                new SpellHandler(serverPlayer.getData(PlayerAttachments.KNOWN_SPELLS).getKnownSpells(),
                                        serverPlayer.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1(),
                                        serverPlayer.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2(),
                                        cooldowns));

                        final UpdateShoutCooldown sendToClient = new UpdateShoutCooldown(data.spellID, data.cooldown);
                        PacketDistributor.PLAYER.with(serverPlayer).send(sendToClient);
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static List<Pair<Spell, Float>> setCooldown(List<Pair<Spell, Float>> cooldowns, int id, float cooldown) {
        for(int i = 0; i < cooldowns.size(); i++) {
            Pair<Spell, Float> p = cooldowns.get(i);
            if(p.getFirst().getID() == id) {
                cooldowns.add(i, new Pair<>(p.getFirst(), cooldown));
            }
        }
        return cooldowns;
    }
    public static Float getCooldown (List<Pair<Spell, Float>> cooldowns, Spell value) {
        for (Pair<Spell, Float> p : cooldowns)
            if (p.getFirst().equals(value))
                return p.getSecond();
        return null;
    }

    public static void handleClient(final UpdateShoutCooldown data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    Player player = context.player().orElseThrow();

                    List<Pair<Spell, Float>> cooldowns = player.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown();

//                    cooldowns.stream().filter(pair -> pair.getFirst().getID() == data.spellID).findFirst().ifPresent(
//                            pair -> new Pair<>(pair.getFirst(), data.cooldown)
//                    );

                    setCooldown(cooldowns, data.spellID, data.cooldown);

                    player.setData(PlayerAttachments.KNOWN_SPELLS,
                            new SpellHandler(player.getData(PlayerAttachments.KNOWN_SPELLS).getKnownSpells(),
                                    player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1(),
                                    player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2(),
                                    cooldowns));

//                    System.out.println(cooldowns);
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }
}
