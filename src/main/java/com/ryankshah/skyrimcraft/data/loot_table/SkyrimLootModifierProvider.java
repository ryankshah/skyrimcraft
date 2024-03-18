package com.ryankshah.skyrimcraft.data.loot_table;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.neoforged.neoforge.common.DungeonHooks;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

public class SkyrimLootModifierProvider extends GlobalLootModifierProvider
{
    public SkyrimLootModifierProvider(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void start() {
        this.add("grass_pod_from_small_vegetation",
                new AddTableLootModifier(
                    new LootItemCondition[] {
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS)
                                    .or(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.LARGE_FERN)).build()
                    },
                    new ResourceLocation(Skyrimcraft.MODID, "grasspod")
                )
        );

        this.add("beehive",
                new AddTableLootModifier(
                        new LootItemCondition[] {
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.BEEHIVE).build()
                        },
                        new ResourceLocation(Skyrimcraft.MODID, "beehive")
                )
        );

        this.add("spider",
                new AddTableLootModifier(
                        new LootItemCondition[] {
                                LootItemKilledByPlayerCondition.killedByPlayer()
                                        .and(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.SPIDER)
                                        )).build()
                        },
                        new ResourceLocation(Skyrimcraft.MODID, "spider")
                )
        );

        this.add("salmon",
                new AddTableLootModifier(
                        new LootItemCondition[] {
                                LootItemKilledByPlayerCondition.killedByPlayer()
                                        .and(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.SALMON)
                                        )).build()
                        },
                        new ResourceLocation(Skyrimcraft.MODID, "salmon")
                )
        );

        this.add("goat",
                new AddTableLootModifier(
                        new LootItemCondition[] {
                                LootItemKilledByPlayerCondition.killedByPlayer()
                                        .and(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.GOAT)
                                )).build()
                        },
                        new ResourceLocation(Skyrimcraft.MODID, "goat")
                )
        );

        this.add("bee",
                new AddTableLootModifier(
                        new LootItemCondition[] {
                                LootItemKilledByPlayerCondition.killedByPlayer()
                                        .and(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.BEE)
                                        )).build()
                        },
                        new ResourceLocation(Skyrimcraft.MODID, "bee")
                )
        );


        this.add("chests/simple_dungeon", new DungeonLootEnhancerModifier(
            new LootItemCondition[] {
                    LootTableIdCondition.builder(new ResourceLocation("chests/simple_dungeon")).build()
            },
            2)
        );
        this.add("chests/abandoned_mineshaft", new DungeonLootEnhancerModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("chests/abandoned_mineshaft")).build()
                },
                2)
        );
        this.add("chests/buried_treasure", new DungeonLootEnhancerModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("chests/buried_treasure")).build()
                },
                2)
        );
        this.add("chests/desert_pyramid", new DungeonLootEnhancerModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("chests/desert_pyramid")).build()
                },
                2)
        );
        this.add("chests/shipwreck_supply", new DungeonLootEnhancerModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("chests/shipwreck_supply")).build()
                },
                2)
        );
        this.add("chests/stronghold_corridor", new DungeonLootEnhancerModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("chests/stronghold_corridor")).build()
                },
                2)
        );
        this.add("gameplay/piglin_bartering", new DungeonLootEnhancerModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("gameplay/piglin_bartering")).build()
                },
                2)
        );
        this.add("gameplay/sniffer_digging", new DungeonLootEnhancerModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("gameplay/sniffer_digging")).build()
                },
                2)
        );
    }
}
