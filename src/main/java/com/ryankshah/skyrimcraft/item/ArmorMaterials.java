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
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "ancient_nord"))));

    public static final Holder<ArmorMaterial> DRAGONBONE = register(Skyrimcraft.MODID+":dragonbone", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 2);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266652_.put(ArmorItem.Type.HELMET, 2);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.25F, () -> Ingredient.of(Items.IRON_INGOT),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "dragonbone"))));

    public static final Holder<ArmorMaterial> HIDE = register(Skyrimcraft.MODID+":hide", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
            () -> Ingredient.of(ItemInit.LEATHER_STRIPS.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "hide"))));

    public static final Holder<ArmorMaterial> SCALED = register(Skyrimcraft.MODID+":scaled", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(ItemInit.STEEL_INGOT.get()),
        List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "scaled"))));

    public static final Holder<ArmorMaterial> STEEL = register(Skyrimcraft.MODID+":steel", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 7);
        p_266654_.put(ArmorItem.Type.HELMET, 3);
    }), 10, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.STEEL_INGOT),
        List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "steel"))));

    public static final Holder<ArmorMaterial> GLASS = register(Skyrimcraft.MODID+":glass", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.MALACHITE_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "glass"))));


    public static final Holder<ArmorMaterial> ELVEN = register(Skyrimcraft.MODID+":elven", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.2F, 0.1F, () -> Ingredient.of(ItemInit.MOONSTONE_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "elven"))));


    public static final Holder<ArmorMaterial> DAEDRIC = register(Skyrimcraft.MODID+":daedric", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266655_ -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 4);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 8);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266655_.put(ArmorItem.Type.HELMET, 4);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.25F, () -> Ingredient.of(ItemInit.EBONY_INGOT),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "daedric"))));


    public static final Holder<ArmorMaterial> EBONY = register(Skyrimcraft.MODID+":ebony", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266655_ -> {
        p_266655_.put(ArmorItem.Type.BOOTS, 3);
        p_266655_.put(ArmorItem.Type.LEGGINGS, 6);
        p_266655_.put(ArmorItem.Type.CHESTPLATE, 8);
        p_266655_.put(ArmorItem.Type.HELMET, 3);
    }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(ItemInit.EBONY_INGOT),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "ebony"))));


    public static final Holder<ArmorMaterial> ORCISH = register(Skyrimcraft.MODID+":orcish", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.ORICHALCUM_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "orcish"))));


    public static final Holder<ArmorMaterial> DWARVEN = register(Skyrimcraft.MODID+":dwarven", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_GENERIC, 0.1F, 0.0F, () -> Ingredient.of(ItemInit.DWARVEN_METAL_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "dwarven"))));


    public static final Holder<ArmorMaterial> IRON = register(Skyrimcraft.MODID+":iron", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266654_ -> {
        p_266654_.put(ArmorItem.Type.BOOTS, 2);
        p_266654_.put(ArmorItem.Type.LEGGINGS, 5);
        p_266654_.put(ArmorItem.Type.CHESTPLATE, 6);
        p_266654_.put(ArmorItem.Type.HELMET, 2);
    }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "iron"))));


    public static final Holder<ArmorMaterial> STORMCLOAK = register(Skyrimcraft.MODID+":stormcloak", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.EMPTY,
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "stormcloak"))));


    public static final Holder<ArmorMaterial> IMPERIAL = register(Skyrimcraft.MODID+":imperial", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.EMPTY,
        List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "imperial"))));


    public static final Holder<ArmorMaterial> FALMER = register(Skyrimcraft.MODID+":falmer", Util.make(new EnumMap<>(ArmorItem.Type.class), p_266652_ -> {
        p_266652_.put(ArmorItem.Type.BOOTS, 1);
        p_266652_.put(ArmorItem.Type.LEGGINGS, 2);
        p_266652_.put(ArmorItem.Type.CHESTPLATE, 3);
        p_266652_.put(ArmorItem.Type.HELMET, 1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.EMPTY,
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "falmer"))));


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
                ResourceLocation.parse(pName),
                new ArmorMaterial(enummap, pEnchantmentValue, pEquipSound, pRepairIngridient, pLayers, pToughness, pKnockbackResistance)
        );
    }
}