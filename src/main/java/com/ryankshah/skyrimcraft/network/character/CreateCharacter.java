package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.function.Supplier;

public record CreateCharacter(int raceID, boolean fin) implements CustomPacketPayload //(int raceID, String raceName, Map<Integer, IntList> skills)
{
    public static final CustomPacketPayload.Type<CreateCharacter> TYPE = new Type<>(new ResourceLocation(Skyrimcraft.MODID, "createcharacter"));

    public static final StreamCodec<ByteBuf, CreateCharacter> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            CreateCharacter::raceID,
            ByteBufCodecs.BOOL,
            CreateCharacter::fin,
            CreateCharacter::new
    );

    public CreateCharacter(final FriendlyByteBuf buf) {
        this(buf.readInt(), buf.readBoolean()); //, buf.readUtf(), buf.readMap(FriendlyByteBuf::readInt, FriendlyByteBuf::readIntIdList));
    }

    public static void handleServer(final CreateCharacter data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
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
        PacketDistributor.sendToPlayer(player, sendToClient);
//        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final CreateCharacter data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = minecraft.player;

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
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}