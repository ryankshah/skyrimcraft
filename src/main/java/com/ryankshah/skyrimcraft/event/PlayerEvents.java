package com.ryankshah.skyrimcraft.event;

import com.mojang.datafixers.util.Pair;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.CompassFeatureHandler;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.init.AttributeInit;
import com.ryankshah.skyrimcraft.init.TagsInit;
import com.ryankshah.skyrimcraft.network.character.AddToCompassFeatures;
import com.ryankshah.skyrimcraft.network.spell.UpdateShoutCooldown;
import com.ryankshah.skyrimcraft.network.spell.UpdateSpellHandlerOnClient;
import com.ryankshah.skyrimcraft.screen.CharacterCreationScreen;
import com.ryankshah.skyrimcraft.util.CompassFeature;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents
{
    public static boolean flag = false;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player playerEntity = event.player;
        if (!playerEntity.isAlive() || playerEntity == null)
            return;

        if (event.phase == TickEvent.Phase.END) {

//            Minecraft.getInstance().getConnection().registryAccess().registry(QuestRegistry.QUESTS_REGISTRY_KEY).ifPresent(
//                    registry -> System.out.println(registry.stream().toList())
//            );
            if(playerEntity.level().isClientSide) {
                if (!playerEntity.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown().isEmpty()) {
                    for (Map.Entry<Spell, Float> entry : playerEntity.getData(PlayerAttachments.KNOWN_SPELLS).getSpellsOnCooldown().entrySet()) {
                        if (entry.getValue() <= 0f) {
                            final UpdateShoutCooldown updateShoutCooldown = new UpdateShoutCooldown(SpellRegistry.SPELLS_REGISTRY.getResourceKey(entry.getKey()).get(), 0f);
                            PacketDistributor.SERVER.noArg().send(updateShoutCooldown);
                        }
                        if (entry.getValue() > 0f) {
                            float cooldown = playerEntity.getData(PlayerAttachments.KNOWN_SPELLS).getSpellCooldown(entry.getKey());
                            final UpdateShoutCooldown updateShoutCooldown = new UpdateShoutCooldown(SpellRegistry.SPELLS_REGISTRY.getResourceKey(entry.getKey()).get(), cooldown - 0.05f);
                            PacketDistributor.SERVER.noArg().send(updateShoutCooldown);
                        }
                    }
                }
            }

            if ((playerEntity.hasEffect(ModEffects.SPECTRAL.get()) || playerEntity.hasEffect(ModEffects.ETHEREAL.get())) && !flag) {
                flag = true;
                playerEntity.setInvisible(true);
                flag = false;
            } else {
                playerEntity.setInvisible(playerEntity.hasEffect(MobEffects.INVISIBILITY));
            }

            if (!playerEntity.hasEffect(ModEffects.MAGICKA_REGEN.get()))
                playerEntity.getAttribute(AttributeInit.MAGICKA_REGEN.value()).removeModifiers();

            if (playerEntity.getData(PlayerAttachments.MAGICKA) < playerEntity.getData(PlayerAttachments.MAX_MAGICKA))
                playerEntity.setData(PlayerAttachments.MAGICKA, playerEntity.getData(PlayerAttachments.MAGICKA) + (0.005f * playerEntity.getData(PlayerAttachments.MAGICKA_REGEN_MODIFIER)));

            if (playerEntity instanceof ServerPlayer && playerEntity.level().isLoaded(playerEntity.blockPosition()) && event.side == LogicalSide.SERVER) {
                ServerPlayer player = (ServerPlayer) playerEntity;
                ServerLevel level = (ServerLevel) player.level();

//                level.registryAccess().registry(QuestRegistry.QUESTS_REGISTRY_KEY).ifPresent(
//                        registry -> System.out.println(registry.stream().toList())
//                );

                // TODO: see below...
                //                if(!PositionTrigger.Instance.located(LocationPredicate.inFeature(Structure.VILLAGE)).matches(world, player.getX(), player.getY(), player.getZ())) {
                //                    return;
                //                }

                // TODO: check if structures can even generate.

                List<ResourceKey<Structure>> structuresList = List.of(BuiltinStructures.VILLAGE_DESERT, BuiltinStructures.VILLAGE_TAIGA,
                        BuiltinStructures.VILLAGE_PLAINS, BuiltinStructures.VILLAGE_SNOWY, BuiltinStructures.VILLAGE_SAVANNA, BuiltinStructures.SHIPWRECK, BuiltinStructures.SHIPWRECK_BEACHED,
                        BuiltinStructures.FORTRESS, BuiltinStructures.MINESHAFT, BuiltinStructures.MINESHAFT_MESA);

                List<TagKey<Structure>> structureTags = List.of(StructureTags.VILLAGE, StructureTags.MINESHAFT, TagsInit.StructureTagsInit.NETHER_FORTRESS, StructureTags.SHIPWRECK);

                // TODO: We should do this check after we do the player bounds check...
                for (TagKey<Structure> structure : structureTags) {
                    BlockPos featureStartPos = locateFeatureStartChunkFromPlayerBlockPos(level, player.blockPosition(), structure);
                    if (featureStartPos != null) {
                        System.out.println("Found Structure: " + structure.toString());
                        List<CompassFeature> playerCompassFeatures = player.getData(PlayerAttachments.COMPASS_FEATURES).getCompassFeatures();
                        CompassFeature compassFeature = new CompassFeature(UUID.randomUUID().toString(), structure, featureStartPos);
                        if (playerCompassFeatures.stream().noneMatch(feature -> feature.equals(compassFeature))) {
//                                System.out.println(playerCompassFeatures);

                            playerCompassFeatures.add(compassFeature);
                            final AddToCompassFeatures features = new AddToCompassFeatures(compassFeature.getId(), compassFeature.getFeature().location(), compassFeature.getBlockPos());
                            player.setData(PlayerAttachments.COMPASS_FEATURES, new CompassFeatureHandler(playerCompassFeatures));
                            PacketDistributor.PLAYER.with(player).send(features);

//                                System.out.println(playerCompassFeatures);
                        }
                    }
                }
            }

            // check ethereal
            if (!playerEntity.hasEffect(ModEffects.ETHEREAL.get())) {
                if (playerEntity.isInvulnerable() && (!playerEntity.isCreative() || !playerEntity.isSpectator()))
                    playerEntity.setInvulnerable(false);
            }
        }
    }

    private static BlockPos locateFeatureStartChunkFromPlayerBlockPos(ServerLevel world, BlockPos pos, TagKey<Structure> feature) {
        // use 2 since based on min spacing, or we can use 7 in case user makes village spacing at every chunk
        BlockPos blockpos1 = world.findNearestMapStructure(feature, pos, 2, true);
        if (blockpos1 != null) {
            return blockpos1;
        } else {
            return null;
        }
    }

    // Open the character creation screen if first login / world created
    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        if(player instanceof AbstractClientPlayer) {
            if(!player.getData(PlayerAttachments.HAS_SETUP))
                Minecraft.getInstance().setScreen(new CharacterCreationScreen());
        }
//        if(player instanceof ServerPlayer) {
//            ServerPlayer serverPlayer = (ServerPlayer) player;
//            if (!serverPlayer.getData(PlayerAttachments.HAS_SETUP)) {
//                final OpenCharacterCreationScreen packet = new OpenCharacterCreationScreen(serverPlayer.getData(PlayerAttachments.HAS_SETUP));
//                PacketDistributor.PLAYER.with(serverPlayer).send(packet);
//            } else {
//                // Do sync
//            }
//        }
    }
}