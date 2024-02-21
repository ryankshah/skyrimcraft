package com.ryankshah.skyrimcraft.data.advancement;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.advancement.LearnSpellTrigger;
import com.ryankshah.skyrimcraft.advancement.LevelUpTrigger;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.init.AdvancementTriggersInit;
import com.ryankshah.skyrimcraft.init.BlockInit;
import com.ryankshah.skyrimcraft.init.EntityInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public static class SkyrimAdvancements implements AdvancementGenerator
    {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {

            AdvancementHolder skyrimcraft = Advancement.Builder.advancement()
                    .display(ItemInit.SWEET_ROLL.get(),
                            Component.literal("Skyrimcraft"),
                            Component.literal("Your adventure begins here..."),
                            new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                            AdvancementType.TASK, false, false, false)
                    .addCriterion("skyrimcraft_login", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.location().setX(MinMaxBounds.Doubles.ANY)))
                    .save(saver, new ResourceLocation(Skyrimcraft.MODID, "root"), existingFileHelper);

            AdvancementHolder spells = Advancement.Builder.advancement().parent(skyrimcraft)
                    .display(ItemInit.FIREBALL_SPELLBOOK.get(), Component.literal("Spells"), Component.literal("Learned spells"), (ResourceLocation)null, AdvancementType.CHALLENGE, true, true, true)
                    .addCriterion("spells_list", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance())).save(saver, new ResourceLocation(Skyrimcraft.MODID, "spell/root"), existingFileHelper);
            AdvancementHolder shouts = Advancement.Builder.advancement().parent(skyrimcraft)
                    .display(BlockInit.SHOUT_BLOCK.get(), Component.literal("Shouts"), Component.literal("Learnt shouts"), (ResourceLocation)null, AdvancementType.CHALLENGE, true, true, true)
                    .addCriterion("shouts_list", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance())).save(saver, new ResourceLocation(Skyrimcraft.MODID, "shout/root"), existingFileHelper);

            for(DeferredHolder<Spell, ? extends Spell> spell : SpellRegistry.SPELLS.getEntries()) {
                if(spell.get().equals(SpellRegistry.EMPTY_SPELL.get()))
                    continue;

                ItemLike provider = spell.get().getType() == Spell.SpellType.SHOUT ? BlockInit.SHOUT_BLOCK.get() : ItemInit.FIREBALL_SPELLBOOK.get();
                AdvancementHolder adv = Advancement.Builder.advancement().parent(spell.get().getType() == Spell.SpellType.SHOUT ? shouts : spells)
                        .display(provider, Component.literal(spell.get().getName()),
                                Component.literal("Learn the " + spell.get().getName() + " " + (spell.get().getType() == Spell.SpellType.SHOUT ? "shout" : "spell")),
                                (ResourceLocation)null, AdvancementType.CHALLENGE, true, true, true)
                        .addCriterion("spell_learned_" + spell.get().getName().toLowerCase(Locale.ENGLISH).replace(" ", "_"), LearnSpellTrigger.onLearn(spell.get()))
                        .save(saver, new ResourceLocation(Skyrimcraft.MODID, (spell.get().getType() == Spell.SpellType.SHOUT ? "shout" : "spell") + "/" + spell.get().getName().toLowerCase(Locale.ENGLISH).replace(" ", "_")), existingFileHelper);
            }


            // Level-Based
            AdvancementHolder combat = Advancement.Builder.advancement().parent(skyrimcraft)
                    .display(ItemInit.DAEDRIC_SWORD.get(), Component.literal("Combat"), Component.literal("Skyrimcraft Combat Achievements"), (ResourceLocation)null, AdvancementType.CHALLENGE, false, false, false)
                    .addCriterion("deal_damage", PlayerHurtEntityTrigger.TriggerInstance.playerHurtEntity()).save(saver, new ResourceLocation(Skyrimcraft.MODID, "combat/root"), existingFileHelper);

            AdvancementHolder reach_level_10 = Advancement.Builder.advancement().parent(combat)
                    .display(ItemInit.IRON_SWORD.get(), Component.literal("Level 10"), Component.literal("Reach Combat Level 10"), (ResourceLocation)null, AdvancementType.TASK, true, true, false)
                    .addCriterion("level_10", LevelUpTrigger.onLevelUp(Optional.of(10))).save(saver, new ResourceLocation(Skyrimcraft.MODID, "combat/reach_level_10"), existingFileHelper);

        }
    }
}
