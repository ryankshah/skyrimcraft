package com.ryankshah.skyrimcraft.item;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ArmorMaterials //implements StringRepresentable, ArmorMaterial
{
    public static final Holder<ArmorMaterial> ANCIENT_NORD = register(Skyrimcraft.MODID+":ancient_nord",
            Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
                p_266652_.put(ArmorItem.Type.BOOTS, 2);
                p_266652_.put(ArmorItem.Type.LEGGINGS, 4);
                p_266652_.put(ArmorItem.Type.CHESTPLATE, 5);
                p_266652_.put(ArmorItem.Type.HELMET, 2);
            }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.5F, 0.125F,
            () -> Ingredient.of(Items.IRON_INGOT),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("ancient_nord"), "", true), new ArmorMaterial.Layer(new ResourceLocation("ancient_nord"), "_overlay", false)));

    public static final Holder<ArmorMaterial> DRAGONBONE = register(Skyrimcraft.MODID+":dragonbone", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 2);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266652_.put(ArmorItem.Type.HELMET, 2);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.25F, () -> Ingredient.of(Items.IRON_INGOT),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("dragonbone"), "", true), new ArmorMaterial.Layer(new ResourceLocation("dragonbone"), "_overlay", false)));

    public static final Holder<ArmorMaterial> HIDE = register(Skyrimcraft.MODID+":hide", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
            () -> Ingredient.of(ItemInit.LEATHER_STRIPS.get()),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("hide"), "", true), new ArmorMaterial.Layer(new ResourceLocation("hide"), "_overlay", false)));

    public static final Holder<ArmorMaterial> SCALED = register(Skyrimcraft.MODID+":scaled", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(ItemInit.STEEL_INGOT.get()),
        List.of(new ArmorMaterial.Layer(new ResourceLocation("scaled"), "", true), new ArmorMaterial.Layer(new ResourceLocation("scaled"), "_overlay", false)));

    public static final Holder<ArmorMaterial> STEEL = register(Skyrimcraft.MODID+":steel", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 7);
        p_266654_.put(ArmorItem.Type.HELMET, 3);
    }), 10, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.STEEL_INGOT),
        List.of(new ArmorMaterial.Layer(new ResourceLocation("steel"), "", true), new ArmorMaterial.Layer(new ResourceLocation("steel"), "_overlay", false)));

    public static final Holder<ArmorMaterial> GLASS = register(Skyrimcraft.MODID+":glass", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.MALACHITE_INGOT.get()),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("glass"), "", true), new ArmorMaterial.Layer(new ResourceLocation("glass"), "_overlay", false)));


    public static final Holder<ArmorMaterial> ELVEN = register(Skyrimcraft.MODID+":elven", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.2F, 0.1F, () -> Ingredient.of(ItemInit.MOONSTONE_INGOT.get()),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("elven"), "", true), new ArmorMaterial.Layer(new ResourceLocation("elven"), "_overlay", false)));


    public static final Holder<ArmorMaterial> DAEDRIC = register(Skyrimcraft.MODID+":daedric", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266655_ -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 4);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 8);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266655_.put(ArmorItem.Type.HELMET, 4);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.25F, () -> Ingredient.of(ItemInit.EBONY_INGOT),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("daedric"), "", true), new ArmorMaterial.Layer(new ResourceLocation("daedric"), "_overlay", false)));


    public static final Holder<ArmorMaterial> EBONY = register(Skyrimcraft.MODID+":ebony", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266655_ -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 3);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266655_.put(ArmorItem.Type.HELMET, 3);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(ItemInit.EBONY_INGOT),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("ebony"), "", true), new ArmorMaterial.Layer(new ResourceLocation("ebony"), "_overlay", false)));


    public static final Holder<ArmorMaterial> ORCISH = register(Skyrimcraft.MODID+":orcish", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.ORICHALCUM_INGOT.get()),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("orcish"), "", true), new ArmorMaterial.Layer(new ResourceLocation("orcish"), "_overlay", false)));


    public static final Holder<ArmorMaterial> DWARVEN = register(Skyrimcraft.MODID+":dwarven", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.DWARVEN_METAL_INGOT.get()),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("dwarven"), "", true), new ArmorMaterial.Layer(new ResourceLocation("dwarven"), "_overlay", false)));


    public static final Holder<ArmorMaterial> IRON = register(Skyrimcraft.MODID+":iron", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT),
            List.of(new ArmorMaterial.Layer(new ResourceLocation("iron"), "", true), new ArmorMaterial.Layer(new ResourceLocation("iron"), "_overlay", false)));


    public static final Holder<ArmorMaterial> STORMCLOAK = register(Skyrimcraft.MODID+":stormcloak", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.EMPTY,
            List.of(new ArmorMaterial.Layer(new ResourceLocation("stormcloak"), "", true), new ArmorMaterial.Layer(new ResourceLocation("stormcloak"), "_overlay", false)));


    public static final Holder<ArmorMaterial> IMPERIAL = register(Skyrimcraft.MODID+":imperial", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.EMPTY,
        List.of(new ArmorMaterial.Layer(new ResourceLocation("imperial"), "", true), new ArmorMaterial.Layer(new ResourceLocation("imperial"), "_overlay", false)));


    public static final Holder<ArmorMaterial> FALMER = register(Skyrimcraft.MODID+":falmer", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.EMPTY,
            List.of(new ArmorMaterial.Layer(new ResourceLocation("falmer"), "", true), new ArmorMaterial.Layer(new ResourceLocation("falmer"), "_overlay", false)));


    private static Holder<ArmorMaterial> register(
        String pName,
        EnumMap<ArmorItem.Type, Integer> pDefense,
        int pEnchantmentValue,
        Holder<SoundEvent> pEquipSound,
        float pToughness,
        float pKnockbackResistance,
        Supplier<Ingredient> pRepairIngridient,
        List<ArmorMaterial.Layer> pLayers
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, pDefense.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                new ResourceLocation(pName),
                new ArmorMaterial(enummap, pEnchantmentValue, pEquipSound, pRepairIngridient, pLayers, pToughness, pKnockbackResistance)
        );
    }

//    public static final Codec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
//    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), p_266653_ -> {
//        p_266653_.put(ArmorItem.Type.BOOTS, 13);
//        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
//        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
//        p_266653_.put(ArmorItem.Type.HELMET, 11);
//    });
//
//    private final String name;
//    private final int durabilityMultiplier;
//    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
//    private final int enchantmentValue;
//    private final SoundEvent sound;
//    private final float toughness;
//    private final float knockbackResistance;
//    private final LazyLoadedValue<Ingredient> repairIngredient;
//
//    private ArmorMaterials(
//            String pName,
//            int pDurabilityMultiplier,
//            EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType,
//            int pEnchantmentValue,
//            SoundEvent pSound,
//            float pToughness,
//            float pKnockbackResistance,
//            Supplier<Ingredient> pRepairIngredient
//    ) {
//        this.name = pName;
//        this.durabilityMultiplier = pDurabilityMultiplier;
//        this.protectionFunctionForType = pProtectionFunctionForType;
//        this.enchantmentValue = pEnchantmentValue;
//        this.sound = pSound;
//        this.toughness = pToughness;
//        this.knockbackResistance = pKnockbackResistance;
//        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
//    }
//
//    @Override
//    public int getDurabilityForType(ArmorItem.Type pType) {
//        return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
//    }
//
//    @Override
//    public int getDefenseForType(ArmorItem.Type pType) {
//        return this.protectionFunctionForType.get(pType);
//    }
//
//    @Override
//    public int getEnchantmentValue() {
//        return this.enchantmentValue;
//    }
//
//    @Override
//    public SoundEvent getEquipSound() {
//        return this.sound;
//    }
//
//    @Override
//    public Ingredient getRepairIngredient() {
//        return this.repairIngredient.get();
//    }
//
//    @Override
//    public String getName() {
//        return this.name;
//    }
//
//    @Override
//    public float getToughness() {
//        return this.toughness;
//    }
//
//    /**
//     * Gets the percentage of knockback resistance provided by armor of the material.
//     */
//    @Override
//    public float getKnockbackResistance() {
//        return this.knockbackResistance;
//    }
//
//    @Override
//    public String getSerializedName() {
//        return this.name;
//    }


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