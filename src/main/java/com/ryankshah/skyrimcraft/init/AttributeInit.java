package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.UUID;

public class AttributeInit
{
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Skyrimcraft.MODID);

    public static final Holder<Attribute> MAGICKA_REGEN = ATTRIBUTES.register("magicka_regen_attribute", () -> new RangedAttribute("skyrimcraft.character.attribute.magicka_regen", 1F, 0F, 2F).setSyncable(true));
//    public static final Holder<Attribute> POISON_RESISTANCE = ATTRIBUTES.register("poison_resistance_attribute", () -> new RangedAttribute("skyrimcraft.character.attribute.poison_resistance", 0F, 0F, 10F).setSyncable(true));

    public static final UUID MODIFIER_ID_MAGICKA_REGEN = UUID.fromString("26fcb349-bc96-4593-9b29-5ace7bdee19f");
    private static final UUID MODIFIER_ID_PLAYER_HEALTH = UUID.fromString("671fbcca-aac7-4de7-9399-d951d58adc12");

    private static final String MODIFIER_NAME_HEALTH = Skyrimcraft.MODID + ".healthModifier";

    public static final UUID ELEMENTAL_FURY_SHOUT_ID = UUID.fromString("92837cbf-7285-41f5-9b12-811421af56fb");

    public static void setModifier(LivingEntity entity, Attribute attribute, UUID uuid, String name, double amount, AttributeModifier.Operation op) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance == null) return;
        AttributeModifier mod = instance.getModifier(uuid);
        if (mod != null) instance.removeModifier(mod.getId());
        instance.addPermanentModifier(new AttributeModifier(uuid, name, amount, op));
    }

    public static void setMaxHealth(LivingEntity entity, double amount, AttributeModifier.Operation op) {
        double oldMax = entity.getMaxHealth();
        setModifier(entity, Attributes.MAX_HEALTH, MODIFIER_ID_PLAYER_HEALTH, MODIFIER_NAME_HEALTH, amount, op);
        double newMax = entity.getMaxHealth();

        // Heal entity when increasing max health
        if (newMax > oldMax) {
            float healAmount = (float) (newMax - oldMax);
            entity.heal(healAmount);
        } else if (entity.getHealth() > newMax) {
            entity.setHealth((float) newMax);
        }
    }

//    public static void setMaxMagicka(LivingEntity entity, double amount, AttributeModifier.Operation op) {
//        Character character = Character.get((Player) entity);
//        double oldMax = character.getMaxMagicka();
//        setModifier(entity, Attributes.MAX_MAGICKA, MODIFIER_ID_PLAYER_HEALTH, MODIFIER_NAME_HEALTH, amount, op);
//        double newMax = entity.getMaxHealth();
//
//        // Heal entity when increasing max health
//        if (newMax > oldMax) {
//            float healAmount = (float) (newMax - oldMax);
//            entity.heal(healAmount);
//        } else if (entity.getHealth() > newMax) {
//            entity.setHealth((float) newMax);
//        }
//    }
}