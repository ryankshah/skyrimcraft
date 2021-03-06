package com.ryankshah.skyrimcraft.item;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial
{
    ANCIENT_NORD("ancient_nord", 15, new int[]{3, 5, 8, 2}, 12, SoundEvents.ARMOR_EQUIP_GENERIC, 2.0F, 0.625F, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),
    DRAGONBONE("dragonbone", 15, new int[]{3, 5, 8, 2}, 12, SoundEvents.ARMOR_EQUIP_GENERIC, 2.0F, 0.625F, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),
    HIDE("hide", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC, 1.0F, 0.0F, () -> {
        return Ingredient.of(ModItems.LEATHER_STRIPS.get());
    }),
    SCALED("scaled", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_GENERIC, 1.5F, 0.0F, () -> {
        return Ingredient.of(ModItems.STEEL_INGOT.get());
    }),
    STEEL("steel", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> {
        return Ingredient.of(ModItems.STEEL_INGOT.get());
    }),
    GLASS("glass", 37, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_GENERIC, 2.0F, 0.1F, () -> {
        return Ingredient.of(ModItems.MALACHITE_INGOT.get());
    }),
    ELVEN("elven", 37, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_GENERIC, 2.0F, 0.1F, () -> {
        return Ingredient.of(ModItems.MOONSTONE_INGOT.get());
    }),
    DAEDRIC("daedric", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_GENERIC, 3.0F, 0.1F, () -> {
        return Ingredient.of(ModItems.EBONY_INGOT.get());
    }),
    EBONY("ebony", 37, new int[]{2, 5, 6, 2}, 15, SoundEvents.ARMOR_EQUIP_GENERIC, 2.0F, 0.1F, () -> {
        return Ingredient.of(ModItems.EBONY_INGOT.get());
    }),
    ORCISH("orcish", 15, new int[]{2, 8, 3, 1}, 9, SoundEvents.ARMOR_EQUIP_GENERIC, 1.325F, 1.5F, () -> {
        return Ingredient.of(ModItems.STEEL_INGOT.get());
    }),
    DWARVEN("dwarven", 15, new int[]{3, 4, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_GENERIC, 1.4F, 1.725F, () -> {
        return Ingredient.of(ModItems.STEEL_INGOT.get());
    }),
    STORMCLOAK("stormcloak", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> {
        return Ingredient.EMPTY;
    }),
    IMPERIAL("imperial", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> {
        return Ingredient.EMPTY;
    }),
    FALMER("falmer", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, () -> {
        return Ingredient.EMPTY;
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    private ModArmorMaterial(String p_i231593_3_, int p_i231593_4_, int[] p_i231593_5_, int p_i231593_6_, SoundEvent p_i231593_7_, float p_i231593_8_, float p_i231593_9_, Supplier<Ingredient> p_i231593_10_) {
        this.name = Skyrimcraft.MODID + ":" + p_i231593_3_;
        this.durabilityMultiplier = p_i231593_4_;
        this.slotProtections = p_i231593_5_;
        this.enchantmentValue = p_i231593_6_;
        this.sound = p_i231593_7_;
        this.toughness = p_i231593_8_;
        this.knockbackResistance = p_i231593_9_;
        this.repairIngredient = new LazyValue<>(p_i231593_10_);
    }

    public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
        return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
        return this.slotProtections[p_200902_1_.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}