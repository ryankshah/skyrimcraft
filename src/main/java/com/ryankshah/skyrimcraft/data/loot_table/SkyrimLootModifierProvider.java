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
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

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

        this.add("horse",
                new AddTableLootModifier(
                        new LootItemCondition[] {
                                LootItemKilledByPlayerCondition.killedByPlayer()
                                        .and(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.HORSE)
                                        )).build()
                        },
                        new ResourceLocation(Skyrimcraft.MODID, "horse")
                )
        );
    }
}
