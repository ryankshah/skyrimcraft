package com.ryankshah.skyrimcraft.network.skill;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.SkillsHandler;
import com.ryankshah.skyrimcraft.character.skill.ISkill;
import com.ryankshah.skyrimcraft.network.character.AddToLevelUpdates;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.Map;

public record AddXpToSkill(int skillID, int baseXp) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"addxptoskill");

    public AddXpToSkill(final FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readInt());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeInt(skillID);
        buffer.writeInt(baseXp);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final AddXpToSkill data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    Player player = context.player().orElseThrow();
                    if (player instanceof ServerPlayer) {
                        ServerPlayer serverPlayer = (ServerPlayer) player;
                        Map<Integer, ISkill> skillsList = serverPlayer.getData(PlayerAttachments.SKILLS).getSkills();
                        ISkill skill = skillsList.get(data.skillID);
                        ISkill skillOld = new ISkill(skill);

                        int oldSkillLevel = skillOld.getLevel();
                        skill.giveExperiencePoints(data.baseXp);

                        skillsList.put(data.skillID, skill);

                        serverPlayer.setData(PlayerAttachments.SKILLS, new SkillsHandler(skillsList, serverPlayer.getData(PlayerAttachments.SKILLS).getRace()));

                        final AddXpToSkill sendToClient = new AddXpToSkill(data.skillID, data.baseXp);
                        PacketDistributor.PLAYER.with(serverPlayer).send(sendToClient);

                        if(skill.getLevel() > oldSkillLevel) {
                            // The skill has leveled up, so send packet to client to add to the skyrim ingame gui levelUpdates list.
                            final AddToLevelUpdates levelUpdates = new AddToLevelUpdates(skill.getName(), skill.getLevel(), 200);
//                            player.setData(PlayerAttachments.XP, new CompassFeatureHandler(playerCompassFeatures));
                            PacketDistributor.PLAYER.with(serverPlayer).send(levelUpdates);

                            int level = serverPlayer.getData(PlayerAttachments.CHARACTER_LEVEL);
                            int totalXp = serverPlayer.getData(PlayerAttachments.CHARACTER_TOTAL_XP);
                            int newLevel = (int)Math.floor(-2.5 + Math.sqrt(8 * totalXp + 1225) / 10);

                            serverPlayer.setData(PlayerAttachments.CHARACTER_TOTAL_XP, totalXp + skill.getLevel());
                            if(newLevel > level) {
                                serverPlayer.setData(PlayerAttachments.CHARACTER_LEVEL, newLevel);
                                final AddToLevelUpdates charLevelUpdates = new AddToLevelUpdates("characterLevel", serverPlayer.getData(PlayerAttachments.CHARACTER_LEVEL), 200);
                                PacketDistributor.PLAYER.with(serverPlayer).send(charLevelUpdates);
                            }
                        }
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleClient(final AddXpToSkill data, final PlayPayloadContext context) {
        context.workHandler().submitAsync(() -> {
                    Player player = context.player().orElseThrow();
                    Map<Integer, ISkill> skillsList = player.getData(PlayerAttachments.SKILLS).getSkills();
                    ISkill skill = skillsList.get(data.skillID);
                    ISkill skillOld = new ISkill(skill);

                    int oldSkillLevel = skillOld.getLevel();
                    skill.giveExperiencePoints(data.baseXp);

                    skillsList.put(data.skillID, skill);

                    player.setData(PlayerAttachments.SKILLS, new SkillsHandler(skillsList, player.getData(PlayerAttachments.SKILLS).getRace()));

                    if(skill.getLevel() > oldSkillLevel) {
                        int level = player.getData(PlayerAttachments.CHARACTER_LEVEL);
                        int totalXp = player.getData(PlayerAttachments.CHARACTER_TOTAL_XP);
                        int newLevel = (int)Math.floor(-2.5 + Math.sqrt(8 * totalXp + 1225) / 10);

                        player.setData(PlayerAttachments.CHARACTER_TOTAL_XP, totalXp + skill.getLevel());
                        if(newLevel > level) {
                            player.setData(PlayerAttachments.CHARACTER_LEVEL, newLevel);
                        }
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }
}
