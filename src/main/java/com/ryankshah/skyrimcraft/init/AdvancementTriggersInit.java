package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.advancement.LearnSpellTrigger;
import com.ryankshah.skyrimcraft.advancement.LevelUpTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AdvancementTriggersInit
{
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, Skyrimcraft.MODID);
    public static final DeferredHolder<CriterionTrigger<?>, LearnSpellTrigger> LEARN_SPELL = TRIGGERS.register("learn_spell", LearnSpellTrigger::new);
    public static final DeferredHolder<CriterionTrigger<?>, LevelUpTrigger> LEVEL_UP = TRIGGERS.register("level_up", LevelUpTrigger::new);
}
