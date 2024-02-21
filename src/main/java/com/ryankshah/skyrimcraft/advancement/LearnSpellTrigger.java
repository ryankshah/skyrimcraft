package com.ryankshah.skyrimcraft.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.init.AdvancementTriggersInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;

import javax.annotation.Nullable;
import java.util.Optional;

public final class LearnSpellTrigger extends SimpleCriterionTrigger<LearnSpellTrigger.Instance> {
    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public void trigger(ServerPlayer player, Spell spell) {
        trigger(player, instance -> instance.test(spell));
    }

    public static Criterion<Instance> onLearn(@Nullable ContextAwarePredicate conditions, @Nullable Spell spell) {
        return AdvancementTriggersInit.LEARN_SPELL.get().createCriterion(new Instance(Optional.ofNullable(conditions), spell != null ? Optional.of(spell) : Optional.empty()));
    }

    public static Criterion<Instance> onLearn(@Nullable Spell spell) {
        return onLearn(null, spell);
    }

    public record Instance(Optional<ContextAwarePredicate> player, Optional<Spell> spell) implements SimpleCriterionTrigger.SimpleInstance {
        private static final Codec<Instance> CODEC = RecordCodecBuilder.create(codec -> codec.group(
                ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(Instance::player),
                ExtraCodecs.strictOptionalField(SpellRegistry.SPELLS_REGISTRY.byNameCodec(), "skill").forGetter(Instance::spell)
        ).apply(codec, Instance::new));

        public boolean test(Spell spell) {
            return spell().isEmpty() || spell().get() == spell;
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return player;
        }
    }
}