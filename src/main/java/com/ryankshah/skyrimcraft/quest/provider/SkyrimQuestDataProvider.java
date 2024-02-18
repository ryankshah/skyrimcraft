package com.ryankshah.skyrimcraft.quest.provider;

import com.ryankshah.skyrimcraft.quest.Quest;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class SkyrimQuestDataProvider extends QuestDataProvider
{
    public SkyrimQuestDataProvider(String modId, PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, fileHelper, registries);
    }

    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        this.createQuest("kill_spider")
                .name("Kill Your First Spider")
                .lootTable(BuiltInLootTables.BASTION_HOGLIN_STABLE)
                .repeatDelay(0f)
                .icon(BuiltInRegistries.BLOCK.getKey(Blocks.DIRT))
                .addStep(new Quest.QuestStep()
                    .task("Kill a Spider")
                    .reward(BuiltInLootTables.BASTION_HOGLIN_STABLE)
                    .addTrigger(KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(EntityType.SPIDER))))
                    .addTrigger(InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Items.STRING)))
                );
    }
}
