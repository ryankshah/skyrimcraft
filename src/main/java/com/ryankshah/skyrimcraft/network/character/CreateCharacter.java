package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.SkillsHandler;
import com.ryankshah.skyrimcraft.character.feature.Race;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record CreateCharacter(int raceID) implements CustomPacketPayload //(int raceID, String raceName, Map<Integer, IntList> skills)
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"createcharacter");

    public CreateCharacter(final FriendlyByteBuf buf) {
        this(buf.readInt()); //, buf.readUtf(), buf.readMap(FriendlyByteBuf::readInt, FriendlyByteBuf::readIntIdList));
    }

    @Override
    public void write(final FriendlyByteBuf buf) {
        buf.writeInt(raceID);
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

        player.setData(PlayerAttachments.SKILLS, new SkillsHandler(race));
        player.setData(PlayerAttachments.HAS_SETUP, true);

        final CreateCharacter sendToClient = new CreateCharacter(race.getId());
        PacketDistributor.PLAYER.with(player).send(sendToClient);
    }

    public static void handleClient(final CreateCharacter data, final PlayPayloadContext context) {
        Player player = context.player().orElseThrow();
        Race race = Race.getRaces().stream().filter(r -> r.getId() == data.raceID()).findFirst().get();

        player.setData(PlayerAttachments.SKILLS, new SkillsHandler(race));
        player.setData(PlayerAttachments.HAS_SETUP, true);
    }
}