package com.ryankshah.skyrimcraft.data;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.data.advancement.SkyrimAdvancementProvider;
import com.ryankshah.skyrimcraft.data.block.SkyrimcraftBlockStateProvider;
import com.ryankshah.skyrimcraft.data.block.SkyrimcraftBlockTagsProvider;
import com.ryankshah.skyrimcraft.data.curios.SkyrimCuriosDataProvider;
import com.ryankshah.skyrimcraft.data.item.SkyrimcraftItemModelProvider;
import com.ryankshah.skyrimcraft.data.item.SkyrimcraftItemTagProvider;
import com.ryankshah.skyrimcraft.data.lang.SkyrimcraftLanguageProvider;
import com.ryankshah.skyrimcraft.data.loot_table.SkyrimLootModifierProvider;
import com.ryankshah.skyrimcraft.data.loot_table.SkyrimcraftLootTables;
import com.ryankshah.skyrimcraft.data.recipe.provider.AlchemyRecipeProvider;
import com.ryankshah.skyrimcraft.data.recipe.provider.ForgeRecipeProvider;
import com.ryankshah.skyrimcraft.data.recipe.provider.OvenRecipeProvider;
import com.ryankshah.skyrimcraft.data.recipe.SkyrimcraftRecipeProvider;
import com.ryankshah.skyrimcraft.data.sound.SkyrimSoundsProvider;
import com.ryankshah.skyrimcraft.data.world.SkyrimBiomeTagsProvider;
import com.ryankshah.skyrimcraft.data.world.SkyrimcraftStructureTagsProvider;
import com.ryankshah.skyrimcraft.data.world.SkyrimcraftWorldGenProvider;
import com.ryankshah.skyrimcraft.quest.IQuest;
import com.ryankshah.skyrimcraft.quest.Quest;
import com.ryankshah.skyrimcraft.quest.provider.SkyrimQuestDataProvider;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Map;

public class DataGenerators
{
    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = event.getGenerator().getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

            generator.addProvider(true, new SkyrimAdvancementProvider(output, event.getLookupProvider(), existingFileHelper, List.of(new SkyrimAdvancementProvider.SkyrimAdvancements())));
            generator.addProvider(true, new SkyrimcraftLanguageProvider(output, Skyrimcraft.MODID, "en_us"));
            generator.addProvider(true, new SkyrimBiomeTagsProvider(output, event.getLookupProvider(), Skyrimcraft.MODID, existingFileHelper));
            generator.addProvider(true, new SkyrimcraftItemModelProvider(output, existingFileHelper));
            generator.addProvider(true, new SkyrimcraftBlockStateProvider(output, Skyrimcraft.MODID, existingFileHelper));
            generator.addProvider(true, new SkyrimcraftLootTables(output));
            generator.addProvider(true, new SkyrimcraftRecipeProvider(output, event.getLookupProvider()));
            generator.addProvider(true, new AlchemyRecipeProvider(output, event.getLookupProvider()));
            generator.addProvider(true, new OvenRecipeProvider(output, event.getLookupProvider()));
            generator.addProvider(true, new ForgeRecipeProvider(output, event.getLookupProvider()));
            SkyrimcraftBlockTagsProvider blockTagsProvider = new SkyrimcraftBlockTagsProvider(output, event.getLookupProvider(), Skyrimcraft.MODID, existingFileHelper);
            generator.addProvider(true, blockTagsProvider);
            generator.addProvider(true, new SkyrimcraftItemTagProvider(output, event.getLookupProvider(), blockTagsProvider, existingFileHelper));
            generator.addProvider(true, new SkyrimcraftStructureTagsProvider(output, event.getLookupProvider(), Skyrimcraft.MODID, existingFileHelper));
            generator.addProvider(event.includeServer(), new SkyrimcraftWorldGenProvider(output, event.getLookupProvider()));
            generator.addProvider(event.includeServer(), new SkyrimCuriosDataProvider(Skyrimcraft.MODID, output,existingFileHelper, event.getLookupProvider()));
            generator.addProvider(true, new SkyrimLootModifierProvider(output, Skyrimcraft.MODID));
            generator.addProvider(event.includeClient(), new SkyrimSoundsProvider(output, Skyrimcraft.MODID, existingFileHelper));

//            RegistryOps<JsonElement> registryOps = RegistryOps.create(JsonOps.INSTANCE, );
//
//            Map<ResourceLocation, IQuest> map = addQuests();
//            );
//
//            JsonCodecProvider<Quest> provider = DataProvider.Factory.forDatapackRegistry(
//                    generator,
//                    existingFileHelper,
//                    Skyrimcraft.MODID,
//                    registryOps,
//                    // The registry you want to generate in.
//                    Registry.PLACED_FEATURE_REGISTRY,
//                    // The elements to generate.
//                    map
//            );

            generator.addProvider(true, new SkyrimQuestDataProvider(Skyrimcraft.MODID, output, existingFileHelper, event.getLookupProvider()));
        } catch (RuntimeException e) {
            Skyrimcraft.logger.error("Failed to generate data", e);
        }
    }

    protected Map<ResourceLocation, IQuest> addQuests() {
        return Map.of(
            new ResourceLocation(Skyrimcraft.MODID, "quests/kill_spider"), new Quest()
                    .name("Kill Your First Spider")
                    .lootTable(BuiltInLootTables.BASTION_HOGLIN_STABLE)
                    .repeatDelay(0f)
                    .icon(BuiltInRegistries.BLOCK.getKey(Blocks.DIRT))
                    .addStep(new Quest.QuestStep()
                            .task("Kill a Spider")
                            .reward(BuiltInLootTables.BASTION_HOGLIN_STABLE)
                            .addTrigger(KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(EntityType.SPIDER))))
                            .addTrigger(InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Items.STRING))))
        );
    }
}