package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.ArrayList;
import java.util.function.Supplier;

public record CreateCharacter(int raceID, boolean fin) implements CustomPacketPayload //(int raceID, String raceName, Map<Integer, IntList> skills)
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"createcharacter");

    public CreateCharacter(final FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readBoolean()); //, buf.readUtf(), buf.readMap(FriendlyByteBuf::readInt, FriendlyByteBuf::readIntIdList));
    }

    @Override
    public void write(final FriendlyByteBuf buf) {
        buf.writeInt(raceID);
        buf.writeBoolean(fin);
//        buf.writeUtf(raceName);
//        buf.writeMap(skills, FriendlyByteBuf::writeInt, FriendlyByteBuf::writeIntIdList);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final CreateCharacter data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();
        Race race = Race.getRaces().stream().filter(r -> r.getId() == data.raceID()).findFirst().get();
        Character character = Character.get(player);

        character.setRace(race);
        character.setSkills(new ArrayList<>(character.getStartingSkills(race)));
        character.setHasSetup(true);

        if(data.fin) {
            for (Supplier<Spell> spell : SpellRegistry.getPowersForRace(race)) {
                character.addNewSpell(spell.get());
            }
        }

        final UpdateCharacter sendToClient = new UpdateCharacter(character);
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final CreateCharacter data, final PlayPayloadContext context) {
    }
}