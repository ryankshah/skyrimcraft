package com.ryankshah.skyrimcraft.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.init.AdvancementTriggersInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.damagesource.DamageSource;

import javax.annotation.Nullable;
import java.util.Optional;

//TODO: Check if player hit by arrow
public class BodyPartShotByArrowTrigger extends SimpleCriterionTrigger<BodyPartShotByArrowTrigger.TriggerInstance>
{
    @Override
    public Codec<BodyPartShotByArrowTrigger.TriggerInstance> codec() {
        return BodyPartShotByArrowTrigger.TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer pPlayer, DamageSource pSource, float pDealtDamage, float pTakenDamage, boolean pBlocked) {
        this.trigger(pPlayer, p_35186_ -> p_35186_.matches(pPlayer, pSource, pDealtDamage, pTakenDamage, pBlocked));
    }

    public static record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<DamagePredicate> damage)
            implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<BodyPartShotByArrowTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
                p_311421_ -> p_311421_.group(
                                ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(BodyPartShotByArrowTrigger.TriggerInstance::player),
                                ExtraCodecs.strictOptionalField(DamagePredicate.CODEC, "damage").forGetter(BodyPartShotByArrowTrigger.TriggerInstance::damage)
                        )
                        .apply(p_311421_, BodyPartShotByArrowTrigger.TriggerInstance::new)
        );

        public static Criterion<BodyPartShotByArrowTrigger.TriggerInstance> entityHurtPlayer() {
            return AdvancementTriggersInit.BODY_PART_SHOT_BY_ARROW.get().createCriterion(new BodyPartShotByArrowTrigger.TriggerInstance(Optional.empty(), Optional.empty()));
        }

        public static Criterion<BodyPartShotByArrowTrigger.TriggerInstance> entityHurtPlayer(DamagePredicate pDamage) {
            return AdvancementTriggersInit.BODY_PART_SHOT_BY_ARROW.get().createCriterion(new BodyPartShotByArrowTrigger.TriggerInstance(Optional.empty(), Optional.of(pDamage)));
        }

        public static Criterion<BodyPartShotByArrowTrigger.TriggerInstance> entityHurtPlayer(DamagePredicate.Builder pDamage) {
            return AdvancementTriggersInit.BODY_PART_SHOT_BY_ARROW.get()
                    .createCriterion(new BodyPartShotByArrowTrigger.TriggerInstance(Optional.empty(), Optional.of(pDamage.build())));
        }

        public boolean matches(ServerPlayer pPlayer, DamageSource pSource, float pDealtDamage, float pTakenDamage, boolean pBlocked) {
            return !this.damage.isPresent() || this.damage.get().matches(pPlayer, pSource, pDealtDamage, pTakenDamage, pBlocked);
        }
    }
}