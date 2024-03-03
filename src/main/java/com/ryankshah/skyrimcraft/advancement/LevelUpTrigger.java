package com.ryankshah.skyrimcraft.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.character.skill.Skill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.character.skill.SkillWrapper;
import com.ryankshah.skyrimcraft.init.AdvancementTriggersInit;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;

import javax.annotation.Nullable;
import java.util.Optional;

public final class LevelUpTrigger extends SimpleCriterionTrigger<LevelUpTrigger.Instance> {
    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public void trigger(ServerPlayer player, SkillWrapper skill, int newLevel) {
        trigger(player, instance -> instance.test(skill, newLevel));
    }

    public static Criterion<Instance> onLevelUp(@Nullable ContextAwarePredicate conditions, @Nullable SkillWrapper skill, Optional<Integer> level) {
        return AdvancementTriggersInit.LEVEL_UP.get().createCriterion(new Instance(Optional.ofNullable(conditions), skill != null ? Optional.of(skill) : Optional.empty(), level));
    }

    public static Criterion<Instance> onLevelUp(@Nullable SkillWrapper skill, Optional<Integer> level) {
        return onLevelUp(null, skill, level);
    }

    public static Criterion<Instance> onLevelUp(@Nullable SkillWrapper skill) {
        return onLevelUp(skill, Optional.empty());
    }

    public static Criterion<Instance> onLevelUp(Optional<Integer> level) {
        return onLevelUp(null, level);
    }

    public record Instance(Optional<ContextAwarePredicate> player, Optional<SkillWrapper> skill, Optional<Integer> levelReq) implements SimpleCriterionTrigger.SimpleInstance {
        private static final Codec<Instance> CODEC = RecordCodecBuilder.create(codec -> codec.group(
                ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(Instance::player),
                ExtraCodecs.strictOptionalField(SkillWrapper.CODEC, "skill").forGetter(Instance::skill),
                ExtraCodecs.strictOptionalField(Codec.INT, "level").forGetter(Instance::levelReq)
        ).apply(codec, Instance::new));

        public boolean test(SkillWrapper skill, int level) {
            return (skill().isEmpty() || skill().get() == skill) && (levelReq().isEmpty() || level >= levelReq().get());
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return Optional.empty();
        }
    }
}