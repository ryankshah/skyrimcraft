package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.UUID;
import java.util.function.Supplier;

public class AttributeInit
{
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Skyrimcraft.MODID);

    public static final Supplier<Attribute> MAX_MAGICKA = ATTRIBUTES.register("max_magicka_attribute", () -> new RangedAttribute("skyrimcraft.character.attribute.max_magicka", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> MAX_STAMINA = ATTRIBUTES.register("max_stamina_attribute", () -> new RangedAttribute("skyrimcraft.character.attribute.max_stamina", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> MAGICKA_REGEN = ATTRIBUTES.register("magicka_regen_attribute", () -> new RangedAttribute("skyrimcraft.character.attribute.magicka_regen", 1F, 0F, 20F).setSyncable(true));

    public static final Supplier<Attribute> POISON_RESIST = ATTRIBUTES.register("poison_resist", () -> new RangedAttribute("skyrimcraft.character.attribute.poison_resistance", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> SHOCK_RESIST = ATTRIBUTES.register("shock_resist", () -> new RangedAttribute("skyrimcraft.character.attribute.shock_resistance", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> FIRE_RESIST = ATTRIBUTES.register("fire_resist", () -> new RangedAttribute("skyrimcraft.character.attribute.fire_resistance", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> FROST_RESIST = ATTRIBUTES.register("frost_resist", () -> new RangedAttribute("skyrimcraft.character.attribute.frost_resistance", 1F, 0F, 100F).setSyncable(true));

    public static final Supplier<Attribute> POISON_POWER = ATTRIBUTES.register("poison_power", () -> new RangedAttribute("skyrimcraft.character.attribute.poison_power", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> SHOCK_POWER = ATTRIBUTES.register("shock_power", () -> new RangedAttribute("skyrimcraft.character.attribute.shock_power", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> FIRE_POWER = ATTRIBUTES.register("fire_power", () -> new RangedAttribute("skyrimcraft.character.attribute.fire_power", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> FROST_POWER = ATTRIBUTES.register("frost_power", () -> new RangedAttribute("skyrimcraft.character.attribute.frost_power", 1F, 0F, 100F).setSyncable(true));

    public static final Supplier<Attribute> ALTERATION_POWER = ATTRIBUTES.register("alteration_power", () -> new RangedAttribute("skyrimcraft.character.attribute.alteration_power", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> DESTRUCTION_POWER = ATTRIBUTES.register("destruction_power", () -> new RangedAttribute("skyrimcraft.character.attribute.destruction_power", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> RESTORATION_POWER = ATTRIBUTES.register("restoration_power", () -> new RangedAttribute("skyrimcraft.character.attribute.restoration_power", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> SHOUT_POWER = ATTRIBUTES.register("shout_power", () -> new RangedAttribute("skyrimcraft.character.attribute.shout_power", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> POWERS_POWER = ATTRIBUTES.register("powers_power", () -> new RangedAttribute("skyrimcraft.character.attribute.powers_power", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> ILLUSION_POWER = ATTRIBUTES.register("illusion_power", () -> new RangedAttribute("skyrimcraft.character.attribute.illusion_power", 1F, 0F, 100F).setSyncable(true));
    public static final Supplier<Attribute> CONJURATION_POWER = ATTRIBUTES.register("conjuration_power", () -> new RangedAttribute("skyrimcraft.character.attribute.conjuration_power", 1F, 0F, 100F).setSyncable(true));

    public static final UUID MODIFIER_ID_MAGICKA_REGEN = UUID.fromString("26fcb349-bc96-4593-9b29-5ace7bdee19f");
    private static final UUID MODIFIER_ID_PLAYER_HEALTH = UUID.fromString("671fbcca-aac7-4de7-9399-d951d58adc12");
    private static final UUID MODIFIER_ID_PLAYER_MAGICKA = UUID.fromString("496fe98b-9c36-41b9-a04b-5e06e6a13bb5");
    private static final UUID MODIFIER_ID_PLAYER_STAMINA = UUID.fromString("8745453b-a3d7-47c6-a986-a01f043da892");

    private static final String MODIFIER_NAME_HEALTH = Skyrimcraft.MODID + ".healthModifier";
    private static final String MODIFIER_NAME_MAGICKA = Skyrimcraft.MODID + ".magickaModifier";
    private static final String MODIFIER_NAME_STAMINA = Skyrimcraft.MODID + ".staminaModifier";

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

    public static void setMaxMagicka(LivingEntity entity, double amount, AttributeModifier.Operation op) {
        Character character = entity.getData(PlayerAttachments.CHARACTER);
        double oldMax = character.getMaxMagicka();
        setModifier(entity, AttributeInit.MAX_MAGICKA.get(), MODIFIER_ID_PLAYER_MAGICKA, MODIFIER_NAME_MAGICKA, amount, op);
        double newMax = character.getMaxMagicka();

//        // Heal entity when increasing max health
//        if (newMax > oldMax) {
//            float healAmount = (float) (newMax - oldMax);
//            entity.heal(healAmount);
//        } else if (entity.getHealth() > newMax) {
//            entity.setHealth((float) newMax);
//        }
    }

    public static void modifyEntityAttributes(EntityAttributeModificationEvent e) {
        e.getTypes().forEach(entity -> {
            e.add(entity, MAX_MAGICKA.get());
            e.add(entity, MAGICKA_REGEN.get());
            e.add(entity, MAX_STAMINA.get());
            e.add(entity, POISON_RESIST.get());
            e.add(entity, SHOCK_RESIST.get());
            e.add(entity, FIRE_RESIST.get());
            e.add(entity, FROST_RESIST.get());
            e.add(entity, POISON_POWER.get());
            e.add(entity, SHOCK_POWER.get());
            e.add(entity, FIRE_POWER.get());
            e.add(entity, FROST_POWER.get());
        });
    }
}