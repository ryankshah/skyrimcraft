package com.ryankshah.skyrimcraft.network.skill;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.skill.Skill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.character.skill.SkillWrapper;
import com.ryankshah.skyrimcraft.init.AdvancementTriggersInit;
import com.ryankshah.skyrimcraft.network.character.AddToLevelUpdates;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Optional;

public record AddXpToSkill(ResourceKey<Skill> skill, int baseXp) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<AddXpToSkill> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "addxptoskill"));

    public static final StreamCodec<ByteBuf, AddXpToSkill> CODEC = StreamCodec.composite(
            ResourceKey.streamCodec(SkillRegistry.SKILLS_KEY),
            AddXpToSkill::skill,
            ByteBufCodecs.VAR_INT,
            AddXpToSkill::baseXp,
            AddXpToSkill::new
    );

    public AddXpToSkill(final FriendlyByteBuf buffer) {
        this(buffer.readResourceKey(SkillRegistry.SKILLS_KEY), buffer.readInt());
    }

    public static void handleServer(final AddXpToSkill data, final IPayloadContext context) {
        context.enqueueWork(() -> {
                    Player player = context.player();
                    if (player instanceof ServerPlayer) {
                        ServerPlayer serverPlayer = (ServerPlayer) player;
                        Character character = Character.get(serverPlayer);
                        SkillWrapper skill = character.getSkill(SkillRegistry.SKILLS_REGISTRY.get(data.skill).getID());

                        int oldSkillLevel = Integer.valueOf(skill.getLevel());
                        character.giveExperiencePoints(skill.getID(), data.baseXp);
//                        skill.giveExperiencePoints(data.baseXp);

                        final AddXpToSkill sendToClient = new AddXpToSkill(data.skill, data.baseXp);
                        PacketDistributor.sendToPlayer(serverPlayer, sendToClient);
//                        PacketDistributor.PLAYER.with(serverPlayer).send(sendToClient);

                        if(skill.getLevel() > oldSkillLevel) {
                            // The skill has leveled up, so send packet to client to add to the skyrim ingame gui levelUpdates list.
                            final AddToLevelUpdates levelUpdates = new AddToLevelUpdates(skill.getName(), skill.getLevel(), 200);
                            PacketDistributor.sendToServer(levelUpdates);
//                            PacketDistributor.SERVER.noArg().send(levelUpdates); //.with(serverPlayer).send(levelUpdates);

                            int level = character.getCharacterLevel();
                            int totalXp = character.getCharacterTotalXp();
                            int newLevel = (int)Math.floor(-2.5 + Math.sqrt(8 * totalXp + 1225) / 10);

                            if(newLevel == 10)
                                AdvancementTriggersInit.LEVEL_UP.get().trigger(serverPlayer, Optional.of(skill), Optional.of(10));

                            character.setCharacterTotalXp(totalXp + skill.getLevel());
                            if(newLevel > level) {
                                character.setCharacterLevel(newLevel);
                                character.addLevelPerkPoint();
                                final AddToLevelUpdates charLevelUpdates = new AddToLevelUpdates("characterLevel", character.getCharacterLevel(), 200);
                                PacketDistributor.sendToPlayer(serverPlayer, charLevelUpdates);
//                                PacketDistributor.PLAYER.with(serverPlayer).send(charLevelUpdates);
                            }
                        }
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.disconnect(Component.translatable(Skyrimcraft.MODID + ".networking.failed", e.getMessage()));
                    return null;
                });
    }

    public static void handleClient(final AddXpToSkill data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            Character character = Character.get(player);
            SkillWrapper skill = character.getSkill(SkillRegistry.SKILLS_REGISTRY.get(data.skill).getID());

            int oldSkillLevel = skill.getLevel();
            character.giveExperiencePoints(skill.getID(), data.baseXp);

            if(skill.getLevel() > oldSkillLevel) {
                int level = character.getCharacterLevel();
                int totalXp = character.getCharacterTotalXp();
                int newLevel = (int)Math.floor(-2.5 + Math.sqrt(8 * totalXp + 1225) / 10);

                character.setCharacterTotalXp(totalXp + skill.getLevel());
                if(newLevel > level) {
                    character.setCharacterLevel(newLevel);
                    character.addLevelPerkPoint();
                }
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
