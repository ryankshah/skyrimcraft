package com.ryankshah.skyrimcraft.item;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorMaterials
{
    public static final ArmorMaterial ANCIENT_NORD = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":ancient_nord";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 3;
                case CHESTPLATE -> 5;
                case LEGGINGS -> 8;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 1;
        }

        @Override
        public float getKnockbackResistance() {
            return 1;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.IRON_INGOT);
        }
    };

    public static final ArmorMaterial DRAGONBONE = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":dragonbone";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 3;
                case CHESTPLATE -> 5;
                case LEGGINGS -> 8;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 1;
        }

        @Override
        public float getKnockbackResistance() {
            return 1;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.IRON_INGOT);
        }
    };

    public static final ArmorMaterial HIDE = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":hide";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 1;
                case CHESTPLATE -> 2;
                case LEGGINGS -> 3;
                case BOOTS -> 1;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemInit.LEATHER_STRIPS);
        }
    };

    public static final ArmorMaterial SCALED = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":scaled";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 1;
                case CHESTPLATE -> 2;
                case LEGGINGS -> 3;
                case BOOTS -> 1;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemInit.STEEL_INGOT);
        }
    };

    public static final ArmorMaterial STEEL = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":steel";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 2;
                case CHESTPLATE -> 5;
                case LEGGINGS -> 6;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemInit.STEEL_INGOT);
        }
    };

    public static final ArmorMaterial GLASS = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":glass";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 2;
                case CHESTPLATE -> 5;
                case LEGGINGS -> 6;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemInit.MALACHITE_INGOT);
        }
    };

    public static final ArmorMaterial ELVEN = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":elven";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 2;
                case CHESTPLATE -> 5;
                case LEGGINGS -> 6;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemInit.MOONSTONE_INGOT);
        }
    };

    public static final ArmorMaterial DAEDRIC = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":daedric";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 3;
                case CHESTPLATE -> 6;
                case LEGGINGS -> 8;
                case BOOTS -> 3;
            };
        }

        @Override
        public float getToughness() {
            return 2;
        }

        @Override
        public float getKnockbackResistance() {
            return 1;
        }

        @Override
        public int getEnchantmentValue() {
            return 25;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemInit.EBONY_INGOT);
        }
    };

    public static final ArmorMaterial EBONY = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":ebony";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 2;
                case CHESTPLATE -> 5;
                case LEGGINGS -> 6;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 1;
        }

        @Override
        public float getKnockbackResistance() {
            return 1;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemInit.EBONY_INGOT);
        }
    };

    public static final ArmorMaterial ORCISH = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":orcish";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 2;
                case CHESTPLATE -> 8;
                case LEGGINGS -> 3;
                case BOOTS -> 1;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 1;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemInit.ORICHALCUM_INGOT);
        }
    };

    public static final ArmorMaterial DWARVEN = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":dwarven";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 3;
                case CHESTPLATE -> 4;
                case LEGGINGS -> 6;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 1;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(ItemInit.DWARVEN_METAL_INGOT);
        }
    };

    public static final ArmorMaterial STORMCLOAK = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":stormcloak";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 1;
                case CHESTPLATE -> 3;
                case LEGGINGS -> 5;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 10;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }
    };

    public static final ArmorMaterial IRON = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":iron";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 1;
                case CHESTPLATE -> 3;
                case LEGGINGS -> 5;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 10;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.IRON_INGOT);
        }
    };

    public static final ArmorMaterial IMPERIAL = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":imperial";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 1;
                case CHESTPLATE -> 3;
                case LEGGINGS -> 5;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 10;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }
    };

    public static final ArmorMaterial FALMER = new ArmorMaterial() {
        @Override
        public String getName() {
            return Skyrimcraft.MODID+":falmer";
        }
        @Override
        public int getDefenseForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 11 * 15;
                case CHESTPLATE -> 16 * 15;
                case LEGGINGS -> 15 * 15;
                case BOOTS -> 13 * 15;
            };
        }

        // Determines the defense value of this armor material, depending on what armor piece it is.
        @Override
        public int getDurabilityForType(ArmorItem.Type type) {
            return switch (type) {
                case HELMET -> 1;
                case CHESTPLATE -> 3;
                case LEGGINGS -> 5;
                case BOOTS -> 2;
            };
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 10;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.EMPTY;
        }
    };
}