package com.ryankshah.skyrimcraft.item;

import com.mojang.serialization.Codec;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ArmorMaterials implements StringRepresentable, ArmorMaterial
{
    ANCIENT_NORD(Skyrimcraft.MODID+":ancient_nord", 10, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 2);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 4);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 5);
        p_266652_.put(ArmorItem.Type.HELMET, 2);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.5F, 0.125F, () -> Ingredient.of(Items.IRON_INGOT)),
    DRAGONBONE(Skyrimcraft.MODID+":dragonbone", 15, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 2);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266652_.put(ArmorItem.Type.HELMET, 2);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.25F, () -> Ingredient.of(Items.IRON_INGOT)),
    HIDE(Skyrimcraft.MODID+":hide", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(ItemInit.LEATHER_STRIPS.get())),
    SCALED(Skyrimcraft.MODID+":scaled", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(ItemInit.STEEL_INGOT.get())),
    STEEL(Skyrimcraft.MODID+":steel", 17, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 7);
        p_266654_.put(ArmorItem.Type.HELMET, 3);
    }), 10, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.STEEL_INGOT)),
    GLASS(Skyrimcraft.MODID+":glass", 12, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.GLASS_HIT, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.MALACHITE_INGOT.get())),
    ELVEN(Skyrimcraft.MODID+":elven", 12, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.2F, 0.1F, () -> Ingredient.of(ItemInit.MOONSTONE_INGOT.get())),
    DAEDRIC(Skyrimcraft.MODID+":daedric", 37, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266655_ -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 4);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 8);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266655_.put(ArmorItem.Type.HELMET, 4);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.25F, () -> Ingredient.of(ItemInit.EBONY_INGOT)),
    EBONY(Skyrimcraft.MODID+":ebony", 30, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266655_ -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 3);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266655_.put(ArmorItem.Type.HELMET, 3);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(ItemInit.EBONY_INGOT)),
    ORCISH(Skyrimcraft.MODID+":orcish", 12, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.ORICHALCUM_INGOT.get())),
    DWARVEN(Skyrimcraft.MODID+":dwarven", 12, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.DWARVEN_METAL_INGOT.get())),
    IRON(Skyrimcraft.MODID+":iron", 15, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
    STORMCLOAK(Skyrimcraft.MODID+":stormcloak", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.EMPTY),
    IMPERIAL(Skyrimcraft.MODID+":imperial", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.EMPTY),
    FALMER(Skyrimcraft.MODID+":falmer", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.EMPTY);

    public static final Codec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), p_266653_ -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });

    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ArmorMaterials(
            String pName,
            int pDurabilityMultiplier,
            EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType,
            int pEnchantmentValue,
            SoundEvent pSound,
            float pToughness,
            float pKnockbackResistance,
            Supplier<Ingredient> pRepairIngredient
    ) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.protectionFunctionForType = pProtectionFunctionForType;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionFunctionForType.get(pType);
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }


//    public static final ArmorMaterial IRON = new ArmorMaterial() {
//        @Override
//        public String getName() {
//            return Skyrimcraft.MODID+":iron";
//        }
//        @Override
//        public int getDefenseForType(ArmorItem.Type type) {
//            return switch (type) {
//                case HELMET -> 11 * 15;
//                case CHESTPLATE -> 16 * 15;
//                case LEGGINGS -> 15 * 15;
//                case BOOTS -> 13 * 15;
//            };
//        }
//
//        // Determines the defense value of this armor material, depending on what armor piece it is.
//        @Override
//        public int getDurabilityForType(ArmorItem.Type type) {
//            return switch (type) {
//                case HELMET -> 1;
//                case CHESTPLATE -> 3;
//                case LEGGINGS -> 5;
//                case BOOTS -> 2;
//            };
//        }
//
//        @Override
//        public float getToughness() {
//            return 0;
//        }
//
//        @Override
//        public float getKnockbackResistance() {
//            return 0;
//        }
//
//        @Override
//        public int getEnchantmentValue() {
//            return 10;
//        }
//
//        @Override
//        public SoundEvent getEquipSound() {
//            return SoundEvents.ARMOR_EQUIP_GENERIC;
//        }
//
//        @Override
//        public Ingredient getRepairIngredient() {
//            return Ingredient.of(Items.IRON_INGOT);
//        }
//    };
//
//    public static final ArmorMaterial IMPERIAL = new ArmorMaterial() {
//        @Override
//        public String getName() {
//            return Skyrimcraft.MODID+":imperial";
//        }
//        @Override
//        public int getDefenseForType(ArmorItem.Type type) {
//            return switch (type) {
//                case HELMET -> 11 * 15;
//                case CHESTPLATE -> 16 * 15;
//                case LEGGINGS -> 15 * 15;
//                case BOOTS -> 13 * 15;
//            };
//        }
//
//        // Determines the defense value of this armor material, depending on what armor piece it is.
//        @Override
//        public int getDurabilityForType(ArmorItem.Type type) {
//            return switch (type) {
//                case HELMET -> 1;
//                case CHESTPLATE -> 3;
//                case LEGGINGS -> 5;
//                case BOOTS -> 2;
//            };
//        }
//
//        @Override
//        public float getToughness() {
//            return 0;
//        }
//
//        @Override
//        public float getKnockbackResistance() {
//            return 0;
//        }
//
//        @Override
//        public int getEnchantmentValue() {
//            return 10;
//        }
//
//        @Override
//        public SoundEvent getEquipSound() {
//            return SoundEvents.ARMOR_EQUIP_GENERIC;
//        }
//
//        @Override
//        public Ingredient getRepairIngredient() {
//            return Ingredient.EMPTY;
//        }
//    };
//
//    public static final ArmorMaterial FALMER = new ArmorMaterial() {
//        @Override
//        public String getName() {
//            return Skyrimcraft.MODID+":falmer";
//        }
//        @Override
//        public int getDefenseForType(ArmorItem.Type type) {
//            return switch (type) {
//                case HELMET -> 11 * 15;
//                case CHESTPLATE -> 16 * 15;
//                case LEGGINGS -> 15 * 15;
//                case BOOTS -> 13 * 15;
//            };
//        }
//
//        // Determines the defense value of this armor material, depending on what armor piece it is.
//        @Override
//        public int getDurabilityForType(ArmorItem.Type type) {
//            return switch (type) {
//                case HELMET -> 1;
//                case CHESTPLATE -> 3;
//                case LEGGINGS -> 5;
//                case BOOTS -> 2;
//            };
//        }
//
//        @Override
//        public float getToughness() {
//            return 0;
//        }
//
//        @Override
//        public float getKnockbackResistance() {
//            return 0;
//        }
//
//        @Override
//        public int getEnchantmentValue() {
//            return 10;
//        }
//
//        @Override
//        public SoundEvent getEquipSound() {
//            return SoundEvents.ARMOR_EQUIP_GENERIC;
//        }
//
//        @Override
//        public Ingredient getRepairIngredient() {
//            return Ingredient.EMPTY;
//        }
//    };
}