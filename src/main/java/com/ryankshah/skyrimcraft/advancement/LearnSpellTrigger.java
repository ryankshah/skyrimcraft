package com.ryankshah.skyrimcraft.advancement;

public class LearnSpellTrigger //extends SimpleCriterionTrigger<LearnSpellTrigger.TriggerInstance>
{
//    @Override
//    public Codec<LearnSpellTrigger.TriggerInstance> codec() {
//        return LearnSpellTrigger.TriggerInstance.CODEC;
//    }
//
//    public void trigger(ServerPlayer pPlayer, ISpell spell) {
//        this.trigger(pPlayer, p_43676_ -> p_43676_.matches(spell));
//    }
//
//    public static record TriggerInstance(
//            Optional<ContextAwarePredicate> player, Optional<ISpell> spell
//    ) implements SimpleCriterionTrigger.SimpleInstance {
//        public static final Codec<LearnSpellTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
//                p_311425_ -> p_311425_.group(
//                                ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(LearnSpellTrigger.TriggerInstance::player),
//                                ExtraCodecs.strictOptionalField(SpellPredicate.CODEC, "spell").forGetter(LearnSpellTrigger.TriggerInstance::spell)
//                        )
//                        .apply(p_311425_, LearnSpellTrigger.TriggerInstance::new)
//        );
//
//        public static Criterion<LearnSpellTrigger.TriggerInstance> changedDurability(Optional<SpellPredicate> pItem) {
//            return learnedSpell(Optional.empty(), pItem);
//        }
//
//        public static Criterion<LearnSpellTrigger.TriggerInstance> learnedSpell(
//                Optional<ContextAwarePredicate> pPlayer, Optional<ItemPredicate> pItem
//        ) {
//            return CriteriaTriggers.ITEM_DURABILITY_CHANGED // change to mod trigger for spell
//                    .createCriterion(new LearnSpellTrigger.TriggerInstance(pPlayer, pItem, pDurability, MinMaxBounds.Ints.ANY));
//        }
//
//        public boolean matches(ISpell spell) {
//            if (!this.spell.isPresent() || !this.spell.get().equals(spell)) {
//                return false;
//            } else {
//                return this.spell.get().equals(spell);
//            }
//        }
//    }
}
