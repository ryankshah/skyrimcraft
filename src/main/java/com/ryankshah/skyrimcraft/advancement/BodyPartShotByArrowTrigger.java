package com.ryankshah.skyrimcraft.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;

import javax.annotation.Nullable;
import java.util.Optional;

//TODO: Check if player hit by arrow
public class BodyPartShotByArrowTrigger extends SimpleCriterionTrigger<BodyPartShotByArrowTrigger.Instance>
{
    @Override
    public Codec<BodyPartShotByArrowTrigger.Instance> codec() {
        return BodyPartShotByArrowTrigger.Instance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        trigger(player, Instance::test);
    }

//    public static Criterion<LearnSpellTrigger.Instance> onLearn(@Nullable ContextAwarePredicate conditions, @Nullable Spell spell) {
//        return AdvancementTriggersInit.LEARN_SPELL.get().createCriterion(new LearnSpellTrigger.Instance(Optional.ofNullable(conditions), spell != null ? Optional.of(spell) : Optional.empty()));
//    }

//    public static Criterion<LearnSpellTrigger.Instance> onLearn(@Nullable Spell spell) {
//        return onLearn;
//    }

    public record Instance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {
        private static final Codec<BodyPartShotByArrowTrigger.Instance> CODEC = RecordCodecBuilder.create(codec -> codec.group(
                ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(BodyPartShotByArrowTrigger.Instance::player)
        ).apply(codec, BodyPartShotByArrowTrigger.Instance::new));

        public boolean test() {
            return false;
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return player;
        }
    }
}