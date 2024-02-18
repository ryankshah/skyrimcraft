package com.ryankshah.skyrimcraft.data.advancement;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.EntityInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SkyrimAdvancementProvider extends AdvancementProvider {
    /**
     * Constructs an advancement provider using the generators to write the
     * advancements to a file.
     *
     * @param output             the target directory of the data generator
     * @param registries         a future of a lookup for registries and their objects
     * @param existingFileHelper a helper used to find whether a file exists
     * @param subProviders       the generators used to create the advancements
     */
    public SkyrimAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper, List<AdvancementGenerator> subProviders) {
        super(output, registries, existingFileHelper, subProviders);
    }

    class SpellAdvancementProvider implements AdvancementGenerator
    {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
//            Advancement example = Advancement.Builder.advancement()
//                    .addCriterion("learn_unrelenting_force", LearnSpellTrigger.TriggerInstance.learnedSpell(SpellRegistry.UNRELENTING_FORCE.value())) // How the advancement is unlocked
//                    .save(saver, Skyrimcraft.MODID + "learned_first_shout", existingFileHelper); // Add data to builder

        }
    }

    public static class QuestAdvancementProvider implements AdvancementGenerator
    {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            AdvancementHolder questAdvancementHolder = Advancement.Builder.advancement()
                    .display(
                            ItemInit.IRON_HELMET.get(),
                            Component.translatable("advancements.skyrimcraft.quest.root.title"),
                            Component.translatable("advancements.skyrimcraft.quest.root.description"),
                            new ResourceLocation("textures/gui/advancements/backgrounds/end.png"),
                            AdvancementType.CHALLENGE,
                            false,
                            false,
                            false
                    )
                    .addCriterion("crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CRAFTING_TABLE))
                    .save(saver, new ResourceLocation(Skyrimcraft.MODID, "quest/root"), existingFileHelper);

            AdvancementHolder DRAGON_SLAYER = Advancement.Builder.advancement()
                    .parent(questAdvancementHolder)
                    .display(
                            ItemInit.DRAGONBONE_SWORD.get(),
                            Component.translatable("advancements.skyrimcraft.quest.dragon_slayer.title"),
                            Component.translatable("advancements.skyrimcraft.quest.dragon_slayer.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            false,
                            false
                    )
//                    .rewards(AdvancementRewards.Builder.loot(new ResourceLocation(Skyrimcraft.MODID, "quests/")))
                    .addCriterion("kill_dragon", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityInit.DRAGON.get()))) // How the advancement is unlocked
                    .save(saver, new ResourceLocation(Skyrimcraft.MODID, "quest/dragon_slayer"), existingFileHelper);

            AdvancementHolder EBONY_DREAMS = Advancement.Builder.advancement()
                    .parent(questAdvancementHolder)
                    .display(
                            ItemInit.EBONY_INGOT.get(),
                            Component.translatable("advancements.skyrimcraft.quest.ebony_dreams.title"),
                            Component.translatable("advancements.skyrimcraft.quest.ebony_dreams.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            false,
                            false
                    )
//                    .rewards(AdvancementRewards.Builder.loot(new ResourceLocation(Skyrimcraft.MODID, "quests/")))
                    .addCriterion("smelt_ebony", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.EBONY_INGOT))
                    .save(saver, new ResourceLocation(Skyrimcraft.MODID, "quest/ebony_dreams"), existingFileHelper);

            AdvancementHolder GOODBYE_WEBS = Advancement.Builder.advancement()
                    .parent(questAdvancementHolder)
                    .display(
                            Items.COBWEB,
                            Component.translatable("advancements.skyrimcraft.quest.goodbye_webs.title"),
                            Component.translatable("advancements.skyrimcraft.quest.goodbye_webs.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            false,
                            false
                    )
//                    .rewards(AdvancementRewards.Builder.loot(new ResourceLocation(Skyrimcraft.MODID, "quests/")))
                    .addCriterion("kill_spider", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.SPIDER))) // How the advancement is unlocked
                    .save(saver, new ResourceLocation(Skyrimcraft.MODID, "quest/goodbye_webs"), existingFileHelper);
        }
    }
}
