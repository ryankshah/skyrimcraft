package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.event.ClientEvents;
import com.ryankshah.skyrimcraft.item.ArmorMaterials;
import com.ryankshah.skyrimcraft.item.*;
import com.ryankshah.skyrimcraft.item.potion.MagickaPotion;
import com.ryankshah.skyrimcraft.util.IngredientEffect;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemInit
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Skyrimcraft.MODID);

    public static final DeferredItem<Item> SWEET_ROLL = ITEMS.register("sweet_roll",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
                    .rarity(Rarity.EPIC)
            ));
    public static final DeferredItem<Item> GARLIC_BREAD = ITEMS.register("garlic_bread",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> POTATO_BREAD = ITEMS.register("potato_bread",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> APPLE_PIE = ITEMS.register("apple_pie",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> MAMMOTH_STEAK = ITEMS.register("mammoth_steak",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> VENISON = ITEMS.register("venison",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> CABBAGE = ITEMS.register("cabbage",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> TOMATO_SOUP = ITEMS.register("tomato_soup",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> APPLE_CABBAGE_STEW = ITEMS.register("apple_cabbage_stew",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> APPLE_DUMPLING = ITEMS.register("apple_dumpling",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> BEEF_STEW = ITEMS.register("beef_stew",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> CABBAGE_SOUP = ITEMS.register("cabbage_soup",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> CABBAGE_POTATO_SOUP = ITEMS.register("cabbage_potato_soup",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> CHICKEN_DUMPLING = ITEMS.register("chicken_dumpling",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> CLAM_MEAT = ITEMS.register("clam_meat",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> SLICED_GOAT_CHEESE = ITEMS.register("sliced_goat_cheese",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> SLICED_EIDAR_CHEESE = ITEMS.register("sliced_eidar_cheese",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> GOURD = ITEMS.register("gourd",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> LEEK = ITEMS.register("leek",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> LEG_OF_GOAT = ITEMS.register("leg_of_goat",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> LEG_OF_GOAT_ROAST = ITEMS.register("leg_of_goat_roast",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> HORSE_MEAT = ITEMS.register("horse_meat",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> HORSE_HAUNCH = ITEMS.register("horse_haunch",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));
    public static final DeferredItem<Item> VEGETABLE_SOUP = ITEMS.register("vegetable_soup",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1f)
                            .build())
            ));

    public static final DeferredItem<Item> ALE = ITEMS.register("ale", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ALTO_WINE = ITEMS.register("alto_wine", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> ARGONIAN_ALE = ITEMS.register("argonian_ale", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> NORD_MEAD = ITEMS.register("nord_mead", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BLACK_BRIAR_MEAD = ITEMS.register("black_briar_mead", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BLACK_BRIAR_RESERVE = ITEMS.register("black_briar_reserve", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> DRAGONS_BREATH_MEAD = ITEMS.register("dragons_breath_mead", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> FIREBRAND_WINE = ITEMS.register("firebrand_wine", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> HONNINGBREW_MEAD = ITEMS.register("honningbrew_mead", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> MEAD_WITH_JUNIPER_BERRY = ITEMS.register("mead_with_juniper_berry", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SKOOMA = ITEMS.register("skooma", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SPICED_WINE = ITEMS.register("spiced_wine", () -> new PotionItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> WINE = ITEMS.register("wine", () -> new PotionItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> MAMMOTH_SNOUT = ITEMS.register("mammoth_snout", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLOUR = ITEMS.register("flour", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BUTTER = ITEMS.register("butter", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GARLIC = ITEMS.register("garlic", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CORUNDUM_INGOT = ITEMS.register("corundum_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DWARVEN_METAL_INGOT = ITEMS.register("dwarven_metal_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EBONY_INGOT = ITEMS.register("ebony_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MALACHITE_INGOT = ITEMS.register("malachite_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MOONSTONE_INGOT = ITEMS.register("moonstone_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ORICHALCUM_INGOT = ITEMS.register("orichalcum_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> QUICKSILVER_INGOT = ITEMS.register("quicksilver_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEATHER_STRIPS = ITEMS.register("leather_strips", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DAEDRA_HEART = ITEMS.register("daedra_heart", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_HEALTH, IngredientEffect.DAMAGE_STAMINA_REGEN, IngredientEffect.DAMAGE_MAGICKA, IngredientEffect.FEAR));

    // TODO: Make these a new roll in ore drop loot tables.
    public static final DeferredItem<Item> FLAWED_AMETHYST = ITEMS.register("flawed_amethyst", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLAWED_DIAMOND = ITEMS.register("flawed_diamond", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLAWED_EMERALD = ITEMS.register("flawed_emerald", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLAWED_RUBY = ITEMS.register("flawed_ruby", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLAWLESS_RUBY = ITEMS.register("flawless_ruby", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLAWED_GARNET = ITEMS.register("flawed_garnet", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FLAWLESS_GARNET = ITEMS.register("flawless_garnet", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SALT_PILE = ITEMS.register("salt_pile", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.WEAKNESS_TO_MAGIC, IngredientEffect.FORTIFY_RESTORATION, IngredientEffect.SLOW, IngredientEffect.REGENERATE_MAGICKA));
    public static final DeferredItem<Item> CREEP_CLUSTER = ITEMS.register("creep_cluster", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_MAGICKA, IngredientEffect.DAMAGE_STAMINA_REGEN, IngredientEffect.FORTIFY_CARRY_WEIGHT, IngredientEffect.WEAKNESS_TO_MAGIC));
    public static final DeferredItem<Item> GRASS_POD = ITEMS.register("grass_pod", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESIST_POISON, IngredientEffect.RAVAGE_MAGICKA, IngredientEffect.FORTIFY_ALTERATION, IngredientEffect.RESTORE_MAGICKA));
    public static final DeferredItem<Item> VAMPIRE_DUST = ITEMS.register("vampire_dust", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.INVISIBILITY, IngredientEffect.RESTORE_MAGICKA, IngredientEffect.REGENERATE_HEALTH, IngredientEffect.CURE_DISEASE));
    public static final DeferredItem<Item> MORA_TAPINELLA = ITEMS.register("mora_tapinella", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_MAGICKA, IngredientEffect.LINGERING_DAMAGE_HEALTH, IngredientEffect.REGENERATE_STAMINA, IngredientEffect.FORTIFY_ILLUSION));
    public static final DeferredItem<Item> BRIAR_HEART = ITEMS.register("briar_heart", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_MAGICKA, IngredientEffect.FORTIFY_BLOCK, IngredientEffect.PARALYSIS, IngredientEffect.FORTIFY_MAGICKA));
    public static final DeferredItem<Item> GIANTS_TOE = ITEMS.register("giants_toe", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_STAMINA, IngredientEffect.FORTIFY_HEALTH, IngredientEffect.FORTIFY_CARRY_WEIGHT, IngredientEffect.DAMAGE_STAMINA_REGEN));
    public static final DeferredItem<Item> SALMON_ROE = ITEMS.register("salmon_roe", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.WATERBREATHING, IngredientEffect.FORTIFY_MAGICKA, IngredientEffect.REGENERATE_MAGICKA));
    public static final DeferredItem<Item> DWARVEN_OIL = ITEMS.register("dwarven_oil", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.WEAKNESS_TO_MAGIC, IngredientEffect.FORTIFY_ILLUSION, IngredientEffect.REGENERATE_MAGICKA, IngredientEffect.RESTORE_MAGICKA));
    public static final DeferredItem<Item> FIRE_SALTS = ITEMS.register("fire_salts", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.WEAKNESS_TO_FROST, IngredientEffect.RESIST_FIRE, IngredientEffect.RESTORE_MAGICKA, IngredientEffect.REGENERATE_MAGICKA));
    public static final DeferredItem<Item> ABECEAN_LONGFIN = ITEMS.register("abecean_longfin", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.WEAKNESS_TO_FROST, IngredientEffect.FORTIFY_SNEAK, IngredientEffect.WEAKNESS_TO_POISON, IngredientEffect.FORTIFY_RESTORATION));
    public static final DeferredItem<Item> CYRODILIC_SPADETAIL = ITEMS.register("cyrodilic_spadetail", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_STAMINA, IngredientEffect.FORTIFY_RESTORATION, IngredientEffect.FEAR, IngredientEffect.RAVAGE_HEALTH));
    public static final DeferredItem<Item> SABRE_CAT_TOOTH = ITEMS.register("sabre_cat_tooth", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.FORTIFY_HEAVY_ARMOR, IngredientEffect.FORTIFY_SMITHING, IngredientEffect.WEAKNESS_TO_POISON));
    public static final DeferredItem<Item> BLUE_DARTWING = ITEMS.register("blue_dartwing", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESIST_SHOCK, IngredientEffect.FORTIFY_PICKPOCKET, IngredientEffect.RESTORE_HEALTH, IngredientEffect.FEAR));
    public static final DeferredItem<Item> ORANGE_DARTWING = ITEMS.register("orange_dartwing", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.RAVAGE_MAGICKA, IngredientEffect.FORTIFY_PICKPOCKET, IngredientEffect.LINGERING_DAMAGE_HEALTH));
    public static final DeferredItem<Item> PEARL = ITEMS.register("pearl", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.FORTIFY_BLOCK, IngredientEffect.RESTORE_MAGICKA, IngredientEffect.RESIST_SHOCK));
    public static final DeferredItem<Item> SMALL_PEARL = ITEMS.register("small_pearl", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.FORTIFY_ONE_HANDED, IngredientEffect.FORTIFY_RESTORATION, IngredientEffect.RESIST_FROST));
    public static final DeferredItem<Item> PINE_THRUSH_EGG = ITEMS.register("pine_thrush_egg", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.FORTIFY_LOCKPICKING, IngredientEffect.WEAKNESS_TO_POISON, IngredientEffect.RESIST_SHOCK));
    public static final DeferredItem<Item> ROCK_WARBLER_EGG = ITEMS.register("rock_warbler_egg", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_HEALTH, IngredientEffect.FORTIFY_ONE_HANDED, IngredientEffect.DAMAGE_STAMINA, IngredientEffect.WEAKNESS_TO_MAGIC));
    public static final DeferredItem<Item> SLAUGHTERFISH_EGG = ITEMS.register("slaughterfish_egg", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESIST_POISON, IngredientEffect.FORTIFY_PICKPOCKET, IngredientEffect.LINGERING_DAMAGE_HEALTH, IngredientEffect.FORTIFY_STAMINA));
    public static final DeferredItem<Item> SLAUGHTERFISH_SCALES = ITEMS.register("slaughterfish_scales", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESIST_FROST, IngredientEffect.LINGERING_DAMAGE_HEALTH, IngredientEffect.FORTIFY_HEAVY_ARMOR, IngredientEffect.FORTIFY_BLOCK));
    public static final DeferredItem<Item> SPIDER_EGG = ITEMS.register("spider_egg", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_STAMINA, IngredientEffect.DAMAGE_MAGICKA_REGEN, IngredientEffect.FORTIFY_LOCKPICKING, IngredientEffect.FORTIFY_MARKSMAN));
    public static final DeferredItem<Item> HAWK_EGG = ITEMS.register("hawk_egg", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESIST_MAGIC, IngredientEffect.DAMAGE_MAGICKA_REGEN, IngredientEffect.WATERBREATHING, IngredientEffect.LINGERING_DAMAGE_STAMINA));
    public static final DeferredItem<Item> TROLL_FAT = ITEMS.register("troll_fat", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESIST_POISON, IngredientEffect.FORTIFY_TWO_HANDED, IngredientEffect.FRENZY, IngredientEffect.DAMAGE_HEALTH));
    public static final DeferredItem<Item> CHAURUS_EGGS = ITEMS.register("chaurus_eggs", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.WEAKNESS_TO_POISON, IngredientEffect.FORTIFY_STAMINA, IngredientEffect.DAMAGE_MAGICKA, IngredientEffect.INVISIBILITY));
    public static final DeferredItem<Item> FLY_AMANITA = ITEMS.register("fly_amanita", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESIST_FIRE, IngredientEffect.FORTIFY_TWO_HANDED, IngredientEffect.FRENZY, IngredientEffect.REGENERATE_STAMINA));
    public static final DeferredItem<Item> ELVES_EAR = ITEMS.register("elves_ear", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_MAGICKA, IngredientEffect.FORTIFY_MARKSMAN, IngredientEffect.WEAKNESS_TO_FROST, IngredientEffect.RESIST_FIRE));
    public static final DeferredItem<Item> TAPROOT = ITEMS.register("taproot", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.WEAKNESS_TO_MAGIC, IngredientEffect.FORTIFY_ILLUSION, IngredientEffect.REGENERATE_MAGICKA, IngredientEffect.RESTORE_MAGICKA));
    public static final DeferredItem<Item> BEE = ITEMS.register("bee", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.RAVAGE_STAMINA, IngredientEffect.REGENERATE_STAMINA, IngredientEffect.WEAKNESS_TO_SHOCK));
    public static final DeferredItem<Item> EYE_OF_SABRE_CAT = ITEMS.register("eye_of_sabre_cat", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.RAVAGE_HEALTH, IngredientEffect.DAMAGE_MAGICKA, IngredientEffect.RESTORE_HEALTH));
    public static final DeferredItem<Item> BEAR_CLAWS = ITEMS.register("bear_claws", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.FORTIFY_HEALTH, IngredientEffect.FORTIFY_ONE_HANDED, IngredientEffect.DAMAGE_MAGICKA_REGEN));
    public static final DeferredItem<Item> BEEHIVE_HUSK = ITEMS.register("beehive_husk", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESIST_POISON, IngredientEffect.FORTIFY_LIGHT_ARMOR, IngredientEffect.FORTIFY_SNEAK, IngredientEffect.FORTIFY_DESTRUCTION));
    public static final DeferredItem<Item> BERITS_ASHES = ITEMS.register("berits_ashes", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_STAMINA, IngredientEffect.RESIST_FIRE, IngredientEffect.FORTIFY_CONJURATION, IngredientEffect.RAVAGE_STAMINA));
//    public static final DeferredItem<Item> BLEEDING_CROWN = ITEMS.register("bleeding_crown", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.WEAKNESS_TO_FIRE, IngredientEffect.FORTIFY_BLOCK, IngredientEffect.WEAKNESS_TO_POISON, IngredientEffect.RESIST_MAGIC));
    public static final DeferredItem<Item> BLISTERWORT = ITEMS.register("blisterwort", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_STAMINA, IngredientEffect.FRENZY, IngredientEffect.RESTORE_HEALTH, IngredientEffect.FORTIFY_SMITHING));
    public static final DeferredItem<Item> BLUE_BUTTERFLY_WING = ITEMS.register("blue_butterfly_wing", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_STAMINA, IngredientEffect.FORTIFY_CONJURATION, IngredientEffect.DAMAGE_MAGICKA_REGEN, IngredientEffect.FORTIFY_ENCHANTING));
    public static final DeferredItem<Item> BUTTERFLY_WING = ITEMS.register("butterfly_wing", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_HEALTH, IngredientEffect.FORTIFY_BARTER, IngredientEffect.LINGERING_DAMAGE_STAMINA, IngredientEffect.DAMAGE_MAGICKA));
//    public static final DeferredItem<Item> CANIS_ROOT = ITEMS.register("canis_root", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_STAMINA, IngredientEffect.FORTIFY_ONE_HANDED, IngredientEffect.FORTIFY_MARKSMAN, IngredientEffect.PARALYSIS));
    public static final DeferredItem<Item> CHARRED_SKEEVER_HIDE = ITEMS.register("charred_skeever_hide", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.CURE_DISEASE, IngredientEffect.RESIST_POISON, IngredientEffect.RESTORE_HEALTH));
    public static final DeferredItem<Item> CRIMSON_NIRNROOT = ITEMS.register("crimson_nirnroot", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_HEALTH, IngredientEffect.DAMAGE_STAMINA, IngredientEffect.INVISIBILITY, IngredientEffect.RESIST_MAGIC));
    public static final DeferredItem<Item> DEATHBELL = ITEMS.register("deathbell", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_HEALTH, IngredientEffect.RAVAGE_STAMINA, IngredientEffect.SLOW, IngredientEffect.WEAKNESS_TO_POISON));
    public static final DeferredItem<Item> DRAGONS_TONGUE = ITEMS.register("dragons_tongue", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESIST_FIRE, IngredientEffect.FORTIFY_BARTER, IngredientEffect.FORTIFY_ILLUSION, IngredientEffect.FORTIFY_TWO_HANDED));
    public static final DeferredItem<Item> ECTOPLASM = ITEMS.register("ectoplasm", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.RESTORE_MAGICKA, IngredientEffect.FORTIFY_DESTRUCTION, IngredientEffect.FORTIFY_MAGICKA, IngredientEffect.DAMAGE_HEALTH));
    public static final DeferredItem<Item> FALMER_EAR = ITEMS.register("falmer_ear", () -> new SkyrimIngredient(new Item.Properties(), IngredientEffect.DAMAGE_HEALTH, IngredientEffect.FRENZY, IngredientEffect.RESIST_POISON, IngredientEffect.FORTIFY_LOCKPICKING));

    //// COMBAT ////
    // Ancient Nord
    public static final Supplier<SkyrimArmor> ANCIENT_NORD_HELMET = ITEMS.register("ancient_nord_helmet", () -> new SkyrimArmor(ArmorMaterials.ANCIENT_NORD, SkyrimArmor.Type.HELMET, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> ANCIENT_NORD_CHESTPLATE = ITEMS.register("ancient_nord_chestplate", () -> new SkyrimArmor(ArmorMaterials.ANCIENT_NORD, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> ANCIENT_NORD_LEGGINGS = ITEMS.register("ancient_nord_leggings", () -> new SkyrimArmor(ArmorMaterials.ANCIENT_NORD, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> ANCIENT_NORD_BOOTS = ITEMS.register("ancient_nord_boots", () -> new SkyrimArmor(ArmorMaterials.ANCIENT_NORD, SkyrimArmor.Type.BOOTS, new Item.Properties(), true));
//    public static final Supplier<Item> ANCIENT_NORD_ARROW = ITEMS.register("ancient_nord_arrow", () -> new SkyrimArrow.AncientNordArrow(new Item.Properties(), "Ancient Nord Arrow"));
    public static final Supplier<SwordItem> ANCIENT_NORD_SWORD = ITEMS.register("ancient_nord_sword", () -> new SwordItem(ItemTier.ANCIENT_NORD, 3, -2.4f, new Item.Properties()));
    public static final Supplier<SkyrimTwoHandedSword> ANCIENT_NORD_BATTLEAXE = ITEMS.register("ancient_nord_battleaxe", () -> new SkyrimTwoHandedSword(ItemTier.ANCIENT_NORD, 3, -2.4f, new Item.Properties()));
    public static final Supplier<SkyrimTwoHandedSword> ANCIENT_NORD_GREATSWORD = ITEMS.register("ancient_nord_greatsword", () -> new SkyrimTwoHandedSword(ItemTier.ANCIENT_NORD, 3, -2.4f, new Item.Properties()));
    public static final Supplier<SwordItem> ANCIENT_NORD_WAR_AXE = ITEMS.register("ancient_nord_war_axe", () -> new SwordItem(ItemTier.ANCIENT_NORD, 3, -2.4f, new Item.Properties()));
    public static final Supplier<BowItem> ANCIENT_NORD_BOW = ITEMS.register("ancient_nord_bow", () -> new BowItem(new Item.Properties()));
    // Daedric
    public static final Supplier<SkyrimArmor> DAEDRIC_HELMET = ITEMS.register("daedric_helmet", () -> new SkyrimArmor(ArmorMaterials.DAEDRIC, SkyrimArmor.Type.HELMET, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> DAEDRIC_CHESTPLATE = ITEMS.register("daedric_chestplate", () -> new SkyrimArmor(ArmorMaterials.DAEDRIC, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> DAEDRIC_LEGGINGS = ITEMS.register("daedric_leggings", () -> new SkyrimArmor(ArmorMaterials.DAEDRIC, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> DAEDRIC_BOOTS = ITEMS.register("daedric_boots", () -> new SkyrimArmor(ArmorMaterials.DAEDRIC, SkyrimArmor.Type.BOOTS, new Item.Properties(), true));
    public static final Supplier<ShieldItem> DAEDRIC_SHIELD = ITEMS.register("daedric_shield", () -> new ShieldItem(new Item.Properties().stacksTo(1)));
//    public static final Supplier<Item> DAEDRIC_ARROW = ITEMS.register("daedric_arrow", () -> new SkyrimArrow.DaedricArrow(new Item.Properties().fireResistant(), "Daedric Arrow"));
    public static final Supplier<SwordItem> DAEDRIC_DAGGER = ITEMS.register("daedric_dagger", () -> new SwordItem(ItemTier.DAEDRIC, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DAEDRIC_SWORD = ITEMS.register("daedric_sword", () -> new SwordItem(ItemTier.DAEDRIC, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> DAEDRIC_BATTLEAXE = ITEMS.register("daedric_battleaxe", () -> new SkyrimTwoHandedSword(ItemTier.DAEDRIC, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<BowItem> DAEDRIC_BOW = ITEMS.register("daedric_bow", () -> new BowItem(new Item.Properties()));
    public static final Supplier<SkyrimTwoHandedSword> DAEDRIC_GREATSWORD = ITEMS.register("daedric_greatsword", () -> new SkyrimTwoHandedSword(ItemTier.DAEDRIC, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DAEDRIC_MACE = ITEMS.register("daedric_mace", () -> new SwordItem(ItemTier.DAEDRIC, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DAEDRIC_WAR_AXE = ITEMS.register("daedric_war_axe", () -> new SwordItem(ItemTier.DAEDRIC, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> DAEDRIC_WARHAMMER = ITEMS.register("daedric_warhammer", () -> new SkyrimTwoHandedSword(ItemTier.DAEDRIC, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Dragonbone
//    public static final Supplier<SwordItem> DRAGONBONE_ARROW = ITEMS.register("dragonbone_arrow", () -> new SkyrimArrow.DragonboneArrow(new Item.Properties().fireResistant(), "Dragonbone Arrow"));
    public static final Supplier<SwordItem> DRAGONBONE_DAGGER = ITEMS.register("dragonbone_dagger", () -> new SwordItem(ItemTier.DRAGONBONE, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DRAGONBONE_SWORD = ITEMS.register("dragonbone_sword", () -> new SwordItem(ItemTier.DRAGONBONE, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DRAGONBONE_BATTLEAXE = ITEMS.register("dragonbone_battleaxe", () -> new SwordItem(ItemTier.DRAGONBONE, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<BowItem> DRAGONBONE_BOW = ITEMS.register("dragonbone_bow", () -> new BowItem(new Item.Properties()));
    public static final Supplier<SkyrimTwoHandedSword> DRAGONBONE_GREATSWORD = ITEMS.register("dragonbone_greatsword", () -> new SkyrimTwoHandedSword(ItemTier.DRAGONBONE, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DRAGONBONE_MACE = ITEMS.register("dragonbone_mace", () -> new SwordItem(ItemTier.DRAGONBONE, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DRAGONBONE_WAR_AXE = ITEMS.register("dragonbone_war_axe", () -> new SwordItem(ItemTier.DRAGONBONE, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> DRAGONBONE_WARHAMMER = ITEMS.register("dragonbone_warhammer", () -> new SkyrimTwoHandedSword(ItemTier.DRAGONBONE, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Dwarven
    public static final Supplier<SkyrimArmor> DWARVEN_HELMET = ITEMS.register("dwarven_helmet", () -> new SkyrimArmor(ArmorMaterials.DWARVEN, SkyrimArmor.Type.HELMET, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> DWARVEN_CHESTPLATE = ITEMS.register("dwarven_chestplate", () -> new SkyrimArmor(ArmorMaterials.DWARVEN, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> DWARVEN_LEGGINGS = ITEMS.register("dwarven_leggings", () -> new SkyrimArmor(ArmorMaterials.DWARVEN, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> DWARVEN_BOOTS = ITEMS.register("dwarven_boots", () -> new SkyrimArmor(ArmorMaterials.DWARVEN, SkyrimArmor.Type.BOOTS, new Item.Properties(), true));
    public static final Supplier<ShieldItem> DWARVEN_SHIELD = ITEMS.register("dwarven_shield", () -> new ShieldItem(new Item.Properties().stacksTo(1)));
    //    public static final Supplier<SwordItem> DWARVEN_ARROW = ITEMS.register("dwarven_arrow", () -> new SkyrimArrow.DwarvenArrow(new Item.Properties().fireResistant(), "Dwarven Arrow"));
    public static final Supplier<SwordItem> DWARVEN_DAGGER = ITEMS.register("dwarven_dagger", () -> new SwordItem(ItemTier.DWARVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DWARVEN_SWORD = ITEMS.register("dwarven_sword", () -> new SwordItem(ItemTier.DWARVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> DWARVEN_BATTLEAXE = ITEMS.register("dwarven_battleaxe", () -> new SkyrimTwoHandedSword(ItemTier.DWARVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<BowItem> DWARVEN_BOW = ITEMS.register("dwarven_bow", () -> new BowItem(new Item.Properties()));
    public static final Supplier<SkyrimTwoHandedSword> DWARVEN_GREATSWORD = ITEMS.register("dwarven_greatsword", () -> new SkyrimTwoHandedSword(ItemTier.DWARVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DWARVEN_MACE = ITEMS.register("dwarven_mace", () -> new SwordItem(ItemTier.DWARVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> DWARVEN_WAR_AXE = ITEMS.register("dwarven_war_axe", () -> new SwordItem(ItemTier.DWARVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> DWARVEN_WARHAMMER = ITEMS.register("dwarven_warhammer", () -> new SkyrimTwoHandedSword(ItemTier.DWARVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Ebony
    public static final Supplier<SkyrimArmor> EBONY_HELMET = ITEMS.register("ebony_helmet", () -> new SkyrimArmor(ArmorMaterials.EBONY, SkyrimArmor.Type.HELMET, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> EBONY_CHESTPLATE = ITEMS.register("ebony_chestplate", () -> new SkyrimArmor(ArmorMaterials.EBONY, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> EBONY_LEGGINGS = ITEMS.register("ebony_leggings", () -> new SkyrimArmor(ArmorMaterials.EBONY, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> EBONY_BOOTS = ITEMS.register("ebony_boots", () -> new SkyrimArmor(ArmorMaterials.EBONY, SkyrimArmor.Type.BOOTS, new Item.Properties(), true));
    public static final Supplier<ShieldItem> EBONY_SHIELD = ITEMS.register("ebony_shield", () -> new ShieldItem(new Item.Properties().stacksTo(1)));
    //    public static final Supplier<SwordItem> EBONY_ARROW = ITEMS.register("ebony_arrow", () -> new SkyrimArrow.EbonyArrow(new Item.Properties().fireResistant(), "Ebony Arrow"));
    public static final Supplier<SwordItem> EBONY_DAGGER = ITEMS.register("ebony_dagger", () -> new SwordItem(ItemTier.EBONY, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> EBONY_SWORD = ITEMS.register("ebony_sword", () -> new SwordItem(ItemTier.EBONY, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> EBONY_BATTLEAXE = ITEMS.register("ebony_battleaxe", () -> new SkyrimTwoHandedSword(ItemTier.EBONY, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<BowItem> EBONY_BOW = ITEMS.register("ebony_bow", () -> new BowItem(new Item.Properties()));
    public static final Supplier<SkyrimTwoHandedSword> EBONY_GREATSWORD = ITEMS.register("ebony_greatsword", () -> new SkyrimTwoHandedSword(ItemTier.EBONY, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> EBONY_MACE = ITEMS.register("ebony_mace", () -> new SwordItem(ItemTier.EBONY, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> EBONY_WAR_AXE = ITEMS.register("ebony_war_axe", () -> new SwordItem(ItemTier.EBONY, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> EBONY_WARHAMMER = ITEMS.register("ebony_warhammer", () -> new SkyrimTwoHandedSword(ItemTier.EBONY, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Elven
    public static final Supplier<SkyrimArmor> ELVEN_HELMET = ITEMS.register("elven_helmet", () -> new SkyrimArmor(ArmorMaterials.ELVEN, SkyrimArmor.Type.HELMET, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> ELVEN_CHESTPLATE = ITEMS.register("elven_chestplate", () -> new SkyrimArmor(ArmorMaterials.ELVEN, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> ELVEN_LEGGINGS = ITEMS.register("elven_leggings", () -> new SkyrimArmor(ArmorMaterials.ELVEN, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> ELVEN_BOOTS = ITEMS.register("elven_boots", () -> new SkyrimArmor(ArmorMaterials.ELVEN, SkyrimArmor.Type.BOOTS, new Item.Properties(), false));
    public static final Supplier<ShieldItem> ELVEN_SHIELD = ITEMS.register("elven_shield", () -> new ShieldItem(new Item.Properties().stacksTo(1)));
    //    public static final Supplier<SwordItem> ELVEN_ARROW = ITEMS.register("elven_arrow", () -> new SkyrimArrow.ElvenArrow(new Item.Properties().fireResistant(), "Elven Arrow"));
    public static final Supplier<SwordItem> ELVEN_DAGGER = ITEMS.register("elven_dagger", () -> new SwordItem(ItemTier.ELVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> ELVEN_SWORD = ITEMS.register("elven_sword", () -> new SwordItem(ItemTier.ELVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> ELVEN_BATTLEAXE = ITEMS.register("elven_battleaxe", () -> new SkyrimTwoHandedSword(ItemTier.ELVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<BowItem> ELVEN_BOW = ITEMS.register("elven_bow", () -> new BowItem(new Item.Properties()));
    public static final Supplier<SkyrimTwoHandedSword> ELVEN_GREATSWORD = ITEMS.register("elven_greatsword", () -> new SkyrimTwoHandedSword(ItemTier.ELVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> ELVEN_MACE = ITEMS.register("elven_mace", () -> new SwordItem(ItemTier.ELVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> ELVEN_WAR_AXE = ITEMS.register("elven_war_axe", () -> new SwordItem(ItemTier.ELVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> ELVEN_WARHAMMER = ITEMS.register("elven_warhammer", () -> new SkyrimTwoHandedSword(ItemTier.ELVEN, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Falmer
    public static final Supplier<SkyrimArmor> FALMER_HELMET = ITEMS.register("falmer_helmet", () -> new SkyrimArmor(ArmorMaterials.FALMER, SkyrimArmor.Type.HELMET, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> FALMER_CHESTPLATE = ITEMS.register("falmer_chestplate", () -> new SkyrimArmor(ArmorMaterials.FALMER, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> FALMER_LEGGINGS = ITEMS.register("falmer_leggings", () -> new SkyrimArmor(ArmorMaterials.FALMER, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> FALMER_BOOTS = ITEMS.register("falmer_boots", () -> new SkyrimArmor(ArmorMaterials.FALMER, SkyrimArmor.Type.BOOTS, new Item.Properties(), true));
    //    public static final Supplier<SwordItem> FALMER_ARROW = ITEMS.register("falmer_arrow", () -> new SkyrimArrow.FalmerArrow(new Item.Properties().fireResistant(), "Falmer Arrow"));
    public static final Supplier<SwordItem> FALMER_SWORD = ITEMS.register("falmer_sword", () -> new SwordItem(ItemTier.FALMER, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<BowItem> FALMER_BOW = ITEMS.register("falmer_bow", () -> new BowItem(new Item.Properties()));
    public static final Supplier<SwordItem> FALMER_WAR_AXE = ITEMS.register("falmer_war_axe", () -> new SwordItem(ItemTier.FALMER, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Glass
    public static final Supplier<SkyrimArmor> GLASS_HELMET = ITEMS.register("glass_helmet", () -> new SkyrimArmor(ArmorMaterials.GLASS, SkyrimArmor.Type.HELMET, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> GLASS_CHESTPLATE = ITEMS.register("glass_chestplate", () -> new SkyrimArmor(ArmorMaterials.GLASS, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> GLASS_LEGGINGS = ITEMS.register("glass_leggings", () -> new SkyrimArmor(ArmorMaterials.GLASS, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> GLASS_BOOTS = ITEMS.register("glass_boots", () -> new SkyrimArmor(ArmorMaterials.GLASS, SkyrimArmor.Type.BOOTS, new Item.Properties(), false));
    public static final Supplier<ShieldItem> GLASS_SHIELD = ITEMS.register("glass_shield", () -> new ShieldItem(new Item.Properties().stacksTo(1)));
    //    public static final Supplier<SwordItem> GLASS_ARROW = ITEMS.register("glass_arrow", () -> new SkyrimArrow.GlassArrow(new Item.Properties(), "Glass Arrow"));
    public static final Supplier<SwordItem> GLASS_DAGGER = ITEMS.register("glass_dagger", () -> new SwordItem(ItemTier.GLASS, 3, -2.4F, (new Item.Properties())));
    public static final Supplier<SwordItem> GLASS_SWORD = ITEMS.register("glass_sword", () -> new SwordItem(ItemTier.GLASS, 3, -2.4F, (new Item.Properties())));
    public static final Supplier<SkyrimTwoHandedSword> GLASS_BATTLEAXE = ITEMS.register("glass_battleaxe", () -> new SkyrimTwoHandedSword(ItemTier.GLASS, 3, -2.4F, (new Item.Properties())));
    public static final Supplier<BowItem> GLASS_BOW = ITEMS.register("glass_bow", () -> new BowItem(new Item.Properties()));
    public static final Supplier<SkyrimTwoHandedSword> GLASS_GREATSWORD = ITEMS.register("glass_greatsword", () -> new SkyrimTwoHandedSword(ItemTier.GLASS, 3, -2.4F, (new Item.Properties())));
    public static final Supplier<SwordItem> GLASS_MACE = ITEMS.register("glass_mace", () -> new SwordItem(ItemTier.GLASS, 3, -2.4F, (new Item.Properties())));
    public static final Supplier<SwordItem> GLASS_WAR_AXE = ITEMS.register("glass_war_axe", () -> new SwordItem(ItemTier.GLASS, 3, -2.4F, (new Item.Properties())));
    public static final Supplier<SkyrimTwoHandedSword> GLASS_WARHAMMER = ITEMS.register("glass_warhammer", () -> new SkyrimTwoHandedSword(ItemTier.GLASS, 3, -2.4F, (new Item.Properties())));
    // Imperial
    public static final Supplier<SkyrimArmor> IMPERIAL_HELMET = ITEMS.register("imperial_helmet", () -> new SkyrimArmor(ArmorMaterials.IMPERIAL, SkyrimArmor.Type.HELMET, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> IMPERIAL_CHESTPLATE = ITEMS.register("imperial_chestplate", () -> new SkyrimArmor(ArmorMaterials.IMPERIAL, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> IMPERIAL_LEGGINGS = ITEMS.register("imperial_leggings", () -> new SkyrimArmor(ArmorMaterials.IMPERIAL, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> IMPERIAL_BOOTS = ITEMS.register("imperial_boots", () -> new SkyrimArmor(ArmorMaterials.IMPERIAL, SkyrimArmor.Type.BOOTS, new Item.Properties(), true));
    public static final Supplier<SwordItem> IMPERIAL_SWORD = ITEMS.register("imperial_sword", () -> new SwordItem(Tiers.IRON, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Iron (Skyrim)
    public static final Supplier<SkyrimArmor> IRON_HELMET = ITEMS.register("iron_helmet", () -> new SkyrimArmor(ArmorMaterials.IRON, SkyrimArmor.Type.HELMET, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> IRON_CHESTPLATE = ITEMS.register("iron_chestplate", () -> new SkyrimArmor(ArmorMaterials.IRON, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> IRON_LEGGINGS = ITEMS.register("iron_leggings", () -> new SkyrimArmor(ArmorMaterials.IRON, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> IRON_BOOTS = ITEMS.register("iron_boots", () -> new SkyrimArmor(net.minecraft.world.item.ArmorMaterials.IRON, SkyrimArmor.Type.BOOTS, new Item.Properties(),true));
    public static final Supplier<ShieldItem> IRON_SHIELD = ITEMS.register("iron_shield", () -> new ShieldItem(new Item.Properties().stacksTo(1)));
    //    public static final Supplier<SwordItem> IRON_ARROW = ITEMS.register("iron_arrow", () -> new SkyrimArrow.IronArrow(new Item.Properties().fireResistant(), "Iron Arrow"));
    public static final Supplier<SwordItem> IRON_DAGGER = ITEMS.register("iron_dagger", () -> new SwordItem(Tiers.IRON, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> IRON_SWORD = ITEMS.register("iron_sword", () -> new SwordItem(Tiers.IRON, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> IRON_BATTLEAXE = ITEMS.register("iron_battleaxe", () -> new SkyrimTwoHandedSword(Tiers.IRON, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> IRON_GREATSWORD = ITEMS.register("iron_greatsword", () -> new SkyrimTwoHandedSword(Tiers.IRON, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> IRON_MACE = ITEMS.register("iron_mace", () -> new SwordItem(Tiers.IRON, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> IRON_WAR_AXE = ITEMS.register("iron_war_axe", () -> new SwordItem(Tiers.IRON, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> IRON_WARHAMMER = ITEMS.register("iron_warhammer", () -> new SkyrimTwoHandedSword(Tiers.IRON, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Orcish
    public static final Supplier<SkyrimArmor> ORCISH_HELMET = ITEMS.register("orcish_helmet", () -> new SkyrimArmor(ArmorMaterials.ORCISH, SkyrimArmor.Type.HELMET, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> ORCISH_CHESTPLATE = ITEMS.register("orcish_chestplate", () -> new SkyrimArmor(ArmorMaterials.ORCISH, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> ORCISH_LEGGINGS = ITEMS.register("orcish_leggings", () -> new SkyrimArmor(ArmorMaterials.ORCISH, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> ORCISH_BOOTS = ITEMS.register("orcish_boots", () -> new SkyrimArmor(ArmorMaterials.ORCISH, SkyrimArmor.Type.BOOTS, new Item.Properties(), true));
    public static final Supplier<ShieldItem> ORCISH_SHIELD = ITEMS.register("orcish_shield", () -> new ShieldItem(new Item.Properties().stacksTo(1)));
    //    public static final Supplier<SwordItem> ORCISH_ARROW = ITEMS.register("orcish_arrow", () -> new SkyrimArrow.OrcishArrow(new Item.Properties().fireResistant(), "Orcish Arrow"));
    public static final Supplier<SwordItem> ORCISH_DAGGER = ITEMS.register("orcish_dagger", () -> new SwordItem(ItemTier.ORCISH, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> ORCISH_SWORD = ITEMS.register("orcish_sword", () -> new SwordItem(ItemTier.ORCISH, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> ORCISH_BATTLEAXE = ITEMS.register("orcish_battleaxe", () -> new SkyrimTwoHandedSword(ItemTier.ORCISH, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<BowItem> ORCISH_BOW = ITEMS.register("orcish_bow", () -> new BowItem(new Item.Properties()));
    public static final Supplier<SkyrimTwoHandedSword> ORCISH_GREATSWORD = ITEMS.register("orcish_greatsword", () -> new SkyrimTwoHandedSword(ItemTier.ORCISH, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> ORCISH_MACE = ITEMS.register("orcish_mace", () -> new SwordItem(ItemTier.ORCISH, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> ORCISH_WAR_AXE = ITEMS.register("orcish_war_axe", () -> new SwordItem(ItemTier.ORCISH, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> ORCISH_WARHAMMER = ITEMS.register("orcish_warhammer", () -> new SkyrimTwoHandedSword(ItemTier.ORCISH, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Steel
//    public static final Supplier<SkyrimArmor> STEEL_HELMET = ITEMS.register("steel_helmet", () -> new SkyrimArmor(ArmorMaterials.STEEL, ArmorItem.Type.HELMET, new Item.Properties(), true));
//    public static final Supplier<SkyrimArmor> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate", () -> new SkyrimArmor(ArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties(), true));
//    public static final Supplier<SkyrimArmor> STEEL_LEGGINGS = ITEMS.register("steel_leggings", () -> new SkyrimArmor(ArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties(), true));
//    public static final Supplier<SkyrimArmor> STEEL_BOOTS = ITEMS.register("steel_boots", () -> new SkyrimArmor(ArmorMaterials.STEEL, ArmorItem.Type.BOOTS, new Item.Properties(), true));
    public static final Supplier<ShieldItem> STEEL_SHIELD = ITEMS.register("steel_shield", () -> new ShieldItem(new Item.Properties().stacksTo(1)));
    //    public static final Supplier<SwordItem> STEEL_ARROW = ITEMS.register("steel_arrow", () -> new SkyrimArrow.SteelArrow(new Item.Properties().fireResistant(), "Steel Arrow"));
    public static final Supplier<SwordItem> STEEL_DAGGER = ITEMS.register("steel_dagger", () -> new SwordItem(ItemTier.STEEL, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> STEEL_SWORD = ITEMS.register("steel_sword", () -> new SwordItem(ItemTier.STEEL, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> STEEL_BATTLEAXE = ITEMS.register("steel_battleaxe", () -> new SkyrimTwoHandedSword(ItemTier.STEEL, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> STEEL_GREATSWORD = ITEMS.register("steel_greatsword", () -> new SkyrimTwoHandedSword(ItemTier.STEEL, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> STEEL_MACE = ITEMS.register("steel_mace", () -> new SwordItem(ItemTier.STEEL, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SwordItem> STEEL_WAR_AXE = ITEMS.register("steel_war_axe", () -> new SwordItem(ItemTier.STEEL, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final Supplier<SkyrimTwoHandedSword> STEEL_WARHAMMER = ITEMS.register("steel_warhammer", () -> new SkyrimTwoHandedSword(ItemTier.STEEL, 3, -2.4F, (new Item.Properties()).fireResistant()));
    // Stormcloak + Stormcloak Officer Armor
    public static final Supplier<SkyrimArmor> STORMCLOAK_OFFICER_HELMET = ITEMS.register("stormcloak_officer_helmet", () -> new SkyrimArmor(ArmorMaterials.STORMCLOAK, SkyrimArmor.Type.HELMET, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> STORMCLOAK_OFFICER_CHESTPLATE = ITEMS.register("stormcloak_officer_chestplate", () -> new SkyrimArmor(ArmorMaterials.STORMCLOAK, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> STORMCLOAK_OFFICER_LEGGINGS = ITEMS.register("stormcloak_officer_leggings", () -> new SkyrimArmor(ArmorMaterials.STORMCLOAK, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), true));
    public static final Supplier<SkyrimArmor> STORMCLOAK_OFFICER_BOOTS = ITEMS.register("stormcloak_officer_boots", () -> new SkyrimArmor(ArmorMaterials.STORMCLOAK, SkyrimArmor.Type.BOOTS, new Item.Properties(), true));

    //// MISC ////
    // Hunting bow
    public static final Supplier<BowItem> HUNTING_BOW = ITEMS.register("hunting_bow", () -> new BowItem(new Item.Properties()));
    // Longbow
    public static final Supplier<BowItem> LONGBOW = ITEMS.register("longbow", () -> new BowItem(new Item.Properties()));
    // Scaled armor
    public static final Supplier<SkyrimArmor> SCALED_HELMET = ITEMS.register("scaled_helmet", () -> new SkyrimArmor(ArmorMaterials.SCALED, SkyrimArmor.Type.HELMET, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> SCALED_CHESTPLATE = ITEMS.register("scaled_chestplate", () -> new SkyrimArmor(ArmorMaterials.SCALED, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> SCALED_LEGGINGS = ITEMS.register("scaled_leggings", () -> new SkyrimArmor(ArmorMaterials.SCALED, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> SCALED_BOOTS = ITEMS.register("scaled_boots", () -> new SkyrimArmor(ArmorMaterials.SCALED, SkyrimArmor.Type.BOOTS, new Item.Properties(), false));
    // Hide armor
    public static final Supplier<SkyrimArmor> HIDE_HELMET = ITEMS.register("hide_helmet", () -> new SkyrimArmor(ArmorMaterials.HIDE, SkyrimArmor.Type.HELMET, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> HIDE_CHESTPLATE = ITEMS.register("hide_chestplate", () -> new SkyrimArmor(ArmorMaterials.HIDE, SkyrimArmor.Type.CHESTPLATE, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> HIDE_LEGGINGS = ITEMS.register("hide_leggings", () -> new SkyrimArmor(ArmorMaterials.HIDE, SkyrimArmor.Type.LEGGINGS, new Item.Properties(), false));
    public static final Supplier<SkyrimArmor> HIDE_BOOTS = ITEMS.register("hide_boots", () -> new SkyrimArmor(ArmorMaterials.HIDE, SkyrimArmor.Type.BOOTS, new Item.Properties(), false));

    //// MAGIC ////
    // Staff
    public static final Supplier<Item> STAFF = ITEMS.register("staff", () -> new Item(new Item.Properties()));
    // Spell books
    public static final Supplier<Item> FIREBALL_SPELLBOOK = ITEMS.register("fireball_spellbook", () -> new SpellBook(new Item.Properties().rarity(Rarity.EPIC), SpellRegistry.FIREBALL));
    public static final Supplier<Item> TURN_UNDEAD_SPELLBOOK = ITEMS.register("turn_undead_spellbook", () -> new SpellBook(new Item.Properties().rarity(Rarity.EPIC), SpellRegistry.TURN_UNDEAD));
    public static final Supplier<Item> CONJURE_FAMILIAR_SPELLBOOK = ITEMS.register("conjure_familiar_spellbook", () -> new SpellBook(new Item.Properties().rarity(Rarity.EPIC), SpellRegistry.CONJURE_FAMILIAR));
    public static final Supplier<Item> HEALING_SPELLBOOK = ITEMS.register("healing_spellbook", () -> new SpellBook(new Item.Properties().rarity(Rarity.EPIC), SpellRegistry.HEALING));
    public static final Supplier<Item> LIGHTNING_SPELLBOOK = ITEMS.register("lightning_spellbook", () -> new SpellBook(new Item.Properties().rarity(Rarity.EPIC), SpellRegistry.LIGHTNING));

    //// Jewellery
    public static final Supplier<Item> GOLD_RING = ITEMS.register("gold_ring", () -> new SkyrimRing());
    public static final Supplier<Item> GOLD_DIAMOND_RING = ITEMS.register("gold_diamond_ring", () -> new SkyrimRing());
    public static final Supplier<Item> GOLD_EMERALD_RING = ITEMS.register("gold_emerald_ring", () -> new SkyrimRing());
    public static final Supplier<Item> GOLD_SAPPHIRE_RING = ITEMS.register("gold_sapphire_ring", () -> new SkyrimRing());
    public static final Supplier<Item> SILVER_RING = ITEMS.register("silver_ring", () -> new SkyrimRing());
    public static final Supplier<Item> SILVER_AMETHYST_RING = ITEMS.register("silver_amethyst_ring", () -> new SkyrimRing());
    public static final Supplier<Item> SILVER_GARNET_RING = ITEMS.register("silver_garnet_ring", () -> new SkyrimRing());
    public static final Supplier<Item> SILVER_RUBY_RING = ITEMS.register("silver_ruby_ring", () -> new SkyrimRing());
    public static final Supplier<Item> ASGEIRS_WEDDING_BAND = ITEMS.register("asgeirs_wedding_band", () -> new SkyrimRing());
    public static final Supplier<Item> AHZIDALS_RING_OF_ARCANA = ITEMS.register("ahzidals_ring_of_arcana", () -> new SkyrimRing());
    public static final Supplier<Item> BALWENS_ORNAMENTAL_RING = ITEMS.register("balwens_ornamental_ring", () -> new SkyrimRing());
    public static final Supplier<Item> BONE_HAWK_RING = ITEMS.register("bone_hawk_ring", () -> new SkyrimRing());
    public static final Supplier<Item> CALCELMOS_RING = ITEMS.register("calcelmos_ring", () -> new SkyrimRing());
    public static final Supplier<Item> ENCHANTED_RING = ITEMS.register("enchanted_ring", () -> new SkyrimRing());
    public static final Supplier<Item> FJOLAS_WEDDING_BAND = ITEMS.register("fjolas_wedding_band", () -> new SkyrimRing());
    public static final Supplier<Item> ILAS_TEIS_RING = ITEMS.register("ilas_teis_ring", () -> new SkyrimRing());
    public static final Supplier<Item> KATARINAS_ORNAMENTAL_RING = ITEMS.register("katarinas_ornamental_ring", () -> new SkyrimRing());
    public static final Supplier<Item> MADESIS_SILVER_RING = ITEMS.register("madesis_silver_ring", () -> new SkyrimRing());
    public static final Supplier<Item> MUIRIS_RING = ITEMS.register("muiris_ring", () -> new SkyrimRing());
    public static final Supplier<Item> NIGHTWEAVERS_BAND = ITEMS.register("nightweavers_band", () -> new SkyrimRing());
    public static final Supplier<Item> PITHIS_ORNAMENTAL_RING = ITEMS.register("pithis_ornamental_ring", () -> new SkyrimRing());
    public static final Supplier<Item> RING_OF_NAMIRA = ITEMS.register("ring_of_namira", () -> new SkyrimRing());
    public static final Supplier<Item> TREOYS_ORNAMENTAL_RING = ITEMS.register("treoys_ornamental_ring", () -> new SkyrimRing());

    public static final Supplier<Item> GOLD_NECKLACE = ITEMS.register("gold_necklace", () -> new SkyrimNecklace());
    public static final Supplier<Item> GOLD_DIAMOND_NECKLACE = ITEMS.register("gold_diamond_necklace", () -> new SkyrimNecklace());
    public static final Supplier<Item> GOLD_JEWELLED_NECKLACE = ITEMS.register("gold_jewelled_necklace", () -> new SkyrimNecklace());
    public static final Supplier<Item> GOLD_RUBY_NECKLACE = ITEMS.register("gold_ruby_necklace", () -> new SkyrimNecklace());
    public static final Supplier<Item> SILVER_NECKLACE = ITEMS.register("silver_necklace", () -> new SkyrimNecklace());
    public static final Supplier<Item> SILVER_EMERALD_NECKLACE = ITEMS.register("silver_emerald_necklace", () -> new SkyrimNecklace());
    public static final Supplier<Item> SILVER_JEWELLED_NECKLACE = ITEMS.register("silver_jewelled_necklace", () -> new SkyrimNecklace());
    public static final Supplier<Item> SILVER_SAPPHIRE_NECKLACE = ITEMS.register("silver_sapphire_necklace", () -> new SkyrimNecklace());

    public static final DeferredItem<Item> MINOR_MAGICKA_POTION = ITEMS.register("minor_magicka_potion", () -> new MagickaPotion(new Item.Properties(), 2.0f));
    public static final DeferredItem<Item> MAGICKA_POTION = ITEMS.register("magicka_potion", () -> new MagickaPotion(new Item.Properties(), 4.0f));
    public static final DeferredItem<Item> PLENTIFUL_MAGICKA_POTION = ITEMS.register("plentiful_magicka_potion", () -> new MagickaPotion(new Item.Properties(), 6.0f));
    public static final DeferredItem<Item> VIGOROUS_MAGICKA_POTION = ITEMS.register("vigorous_magicka_potion", () -> new MagickaPotion(new Item.Properties(), 8.0f));
    public static final DeferredItem<Item> EXTREME_MAGICKA_POTION = ITEMS.register("extreme_magicka_potion", () -> new MagickaPotion(new Item.Properties(), 12.0f));
    public static final DeferredItem<Item> ULTIMATE_MAGICKA_POTION = ITEMS.register("ultimate_magicka_potion", () -> new MagickaPotion(new Item.Properties(), 20.0f));

    public static final DeferredItem<Item> SABRE_CAT_SPAWN_EGG = ITEMS.register("sabre_cat_spawn_egg", () -> new DeferredSpawnEggItem(EntityInit.SABRE_CAT, 0x505050, 0x606060, new Item.Properties()));
    public static final DeferredItem<Item> GIANT_SPAWN_EGG = ITEMS.register("giant_spawn_egg", () -> new DeferredSpawnEggItem(EntityInit.GIANT, 0x505050, 0x606060, new Item.Properties()));
    public static final DeferredItem<Item> DRAGON_SPAWN_EGG = ITEMS.register("dragon_spawn_egg", () -> new DeferredSpawnEggItem(EntityInit.DRAGON, 0x505050, 0x606060, new Item.Properties()));
    public static final DeferredItem<Item> BLUE_BUTTERFLY_SPAWN_EGG = ITEMS.register("blue_butterfly_spawn_egg", () -> new DeferredSpawnEggItem(EntityInit.BLUE_BUTTERFLY, 0x505050, 0x606060, new Item.Properties()));
    public static final DeferredItem<Item> MONARCH_BUTTERFLY_SPAWN_EGG = ITEMS.register("monarch_butterfly_spawn_egg", () -> new DeferredSpawnEggItem(EntityInit.MONARCH_BUTTERFLY, 0x505050, 0x606060, new Item.Properties()));
    public static final DeferredItem<Item> ORANGE_DARTWING_SPAWN_EGG = ITEMS.register("orange_dartwing_spawn_egg", () -> new DeferredSpawnEggItem(EntityInit.ORANGE_DARTWING, 0x505050, 0x606060, new Item.Properties()));
    public static final DeferredItem<Item> BLUE_DARTWING_SPAWN_EGG = ITEMS.register("blue_dartwing_spawn_egg", () -> new DeferredSpawnEggItem(EntityInit.BLUE_DARTWING, 0x505050, 0x606060, new Item.Properties()));
    public static final DeferredItem<Item> LUNAR_MOTH_SPAWN_EGG = ITEMS.register("lunar_moth_spawn_egg", () -> new DeferredSpawnEggItem(EntityInit.LUNAR_MOTH, 0x505050, 0x606060, new Item.Properties()));
    public static final DeferredItem<Item> TORCHBUG_SPAWN_EGG = ITEMS.register("torchbug_spawn_egg", () -> new DeferredSpawnEggItem(EntityInit.TORCHBUG, 0x505050, 0x606060, new Item.Properties()));

    public static void addItemTranslations(LanguageProvider provider) {
        provider.addItem(SWEET_ROLL, "Sweet Roll");
        provider.addItem(GARLIC_BREAD, "Garlic Bread");
        provider.addItem(POTATO_BREAD, "Potato Bread");
        provider.addItem(TOMATO, "Tomato");
        provider.addItem(GARLIC, "Garlic");
        provider.addItem(APPLE_PIE, "Apple Pie");
        provider.addItem(MAMMOTH_SNOUT, "Mammoth Snout");
        provider.addItem(MAMMOTH_STEAK, "Mammoth Steak");
        provider.addItem(VENISON, "Venison");
        provider.addItem(FLOUR, "Flour");
        provider.addItem(BUTTER, "Butter");
        provider.addItem(CABBAGE, "Cabbage");
        provider.addItem(TOMATO_SOUP, "Tomato Soup");
        provider.addItem(APPLE_CABBAGE_STEW, "Apple Cabbage Stew");
        provider.addItem(APPLE_DUMPLING, "Apple Dumpling");
        provider.addItem(BEEF_STEW, "Beef Stew");
        provider.addItem(CABBAGE_SOUP, "Cabbage Soup");
        provider.addItem(CABBAGE_POTATO_SOUP, "Cabbage Potato Soup");
        provider.addItem(CHICKEN_DUMPLING, "Chicken Dumpling");
        provider.addItem(CLAM_MEAT, "Clam Meat");
        provider.addItem(SLICED_GOAT_CHEESE, "Sliced Goat Cheese");
        provider.addItem(SLICED_EIDAR_CHEESE, "Sliced Eidar Cheese");
        provider.addItem(GOURD, "Gourd");
        provider.addItem(LEEK, "Leek");
        provider.addItem(LEG_OF_GOAT, "Leg of Goat");
        provider.addItem(LEG_OF_GOAT_ROAST, "Leg of Goat Roast");
        provider.addItem(HORSE_MEAT, "Horse Meat");
        provider.addItem(HORSE_HAUNCH, "Horse Haunch");
        provider.addItem(VEGETABLE_SOUP, "Vegetable Soup");

        provider.addItem(ALE, "Ale");
        provider.addItem(ALTO_WINE, "Alto Wine");
        provider.addItem(ARGONIAN_ALE, "Argonian Ale");
        provider.addItem(NORD_MEAD, "Nord Mead");
        provider.addItem(BLACK_BRIAR_MEAD, "Black-Briar Mead");
        provider.addItem(BLACK_BRIAR_RESERVE, "Black-Briar Reserve");
        provider.addItem(DRAGONS_BREATH_MEAD, "Dragon's Breath Mead");
        provider.addItem(FIREBRAND_WINE, "Firebrand Wine");
        provider.addItem(HONNINGBREW_MEAD, "Honningbrew Mead");
        provider.addItem(MEAD_WITH_JUNIPER_BERRY, "Mead with Juniper Berry");
        provider.addItem(SKOOMA, "Skooma");
        provider.addItem(SPICED_WINE, "Spiced Wine");
        provider.addItem(WINE, "Wine");

        provider.addItem(TOMATO_SEEDS, "Tomato Seeds");

        provider.addItem(SALT_PILE, "Salt Pile");
        provider.addItem(CREEP_CLUSTER, "Creep Cluster");
        provider.addItem(GRASS_POD, "Grass Pod");
        provider.addItem(VAMPIRE_DUST, "Vampire Dust");
        provider.addItem(MORA_TAPINELLA, "Mora Tapinella");
        provider.addItem(BRIAR_HEART, "Briar Heart");
        provider.addItem(GIANTS_TOE, "Giant's Toe");
        provider.addItem(SALMON_ROE, "Salmon Roe");
        provider.addItem(DWARVEN_OIL, "Dwarven Oil");
        provider.addItem(FIRE_SALTS, "Fire Salts");
        provider.addItem(ABECEAN_LONGFIN, "Abecean Longfin");
        provider.addItem(CYRODILIC_SPADETAIL, "Cyrodilic Spadetail");
        provider.addItem(SABRE_CAT_TOOTH, "Sabre Cat Tooth");
        provider.addItem(BLUE_DARTWING, "Blue Dartwing");
        provider.addItem(ORANGE_DARTWING, "Orange Dartwing");
        provider.addItem(PEARL, "Pearl");
        provider.addItem(SMALL_PEARL, "Small Pearl");
        provider.addItem(PINE_THRUSH_EGG, "Pine Thrush Egg");
        provider.addItem(ROCK_WARBLER_EGG, "Rock Warbler Egg");
        provider.addItem(SLAUGHTERFISH_EGG, "Slaughterfish Egg");
        provider.addItem(SLAUGHTERFISH_SCALES, "Slaughterfish Scales");
        provider.addItem(SPIDER_EGG, "Spider Egg");
        provider.addItem(HAWK_EGG, "Hawk Egg");
        provider.addItem(TROLL_FAT, "Troll Fat");
        provider.addItem(CHAURUS_EGGS, "Chaurus Eggs");
        provider.addItem(FLY_AMANITA, "Fly Amanita");
        provider.addItem(ELVES_EAR, "Elves Ear");
        provider.addItem(TAPROOT, "Taproot");
        provider.addItem(BEE, "Bee");
        provider.addItem(EYE_OF_SABRE_CAT, "Eye of Sabre Cat");
        provider.addItem(BEAR_CLAWS, "Bear Claws");
        provider.addItem(BEEHIVE_HUSK, "Beehive Husk");
        provider.addItem(BERITS_ASHES, "Berit's Ashes");
//        provider.addItem(BLEEDING_CROWN, "Bleeding Crown");
        provider.addItem(BLISTERWORT, "Blisterwort");
        provider.addItem(BLUE_BUTTERFLY_WING, "Blue Butterfly Wing");
        provider.addItem(BUTTERFLY_WING, "Butterfly Wing");
//        provider.addItem(CANIS_ROOT, "Canis Root");
        provider.addItem(CHARRED_SKEEVER_HIDE, "Charred Skeever Hide");
        provider.addItem(CRIMSON_NIRNROOT, "Crimson Nirnroot");
        provider.addItem(DEATHBELL, "Deathbell");
        provider.addItem(DRAGONS_TONGUE, "Dragon's Tongue");
        provider.addItem(ECTOPLASM, "Ectoplasm");
        provider.addItem(FALMER_EAR, "Falmer Ear");

        provider.addItem(CORUNDUM_INGOT, "Corundum Ingot");
        provider.addItem(DWARVEN_METAL_INGOT, "Dwarven Metal Ingot");
        provider.addItem(EBONY_INGOT, "Ebony Ingot");
        provider.addItem(MALACHITE_INGOT, "Malachite Ingot");
        provider.addItem(MOONSTONE_INGOT, "Moonstone Ingot");
        provider.addItem(ORICHALCUM_INGOT, "Orichalcum Ingot");
        provider.addItem(QUICKSILVER_INGOT, "Quicksilver Ingot");
        provider.addItem(SILVER_INGOT, "Silver Ingot");
        provider.addItem(STEEL_INGOT, "Steel Ingot");
        provider.addItem(DAEDRA_HEART, "Daedra Heart");
        provider.addItem(LEATHER_STRIPS, "Leather Strips");

        provider.addItem(FLAWED_AMETHYST, "Flawed Amethyst");
        provider.addItem(FLAWED_DIAMOND, "Flawed Diamond");
        provider.addItem(FLAWED_EMERALD, "Flawed Emerald");
        provider.addItem(FLAWED_RUBY, "Flawed Ruby");
        provider.addItem(FLAWLESS_RUBY, "Flawless Ruby");
        provider.addItem(FLAWED_GARNET, "Flawed Garnet");
        provider.addItem(FLAWLESS_GARNET, "Flawless Garnet");

        provider.addItem(SABRE_CAT_SPAWN_EGG, "Sabre Cat");
        provider.addItem(GIANT_SPAWN_EGG, "Giant");
        provider.addItem(DRAGON_SPAWN_EGG, "Dragon");
        provider.addItem(BLUE_BUTTERFLY_SPAWN_EGG, "Blue Butterfly");
        provider.addItem(MONARCH_BUTTERFLY_SPAWN_EGG, "Monarch Butterfly");
        provider.addItem(ORANGE_DARTWING_SPAWN_EGG, "Orange Dartwing");
        provider.addItem(BLUE_DARTWING_SPAWN_EGG, "Blue Dartwing");
        provider.addItem(LUNAR_MOTH_SPAWN_EGG, "Lunar Moth");
        provider.addItem(TORCHBUG_SPAWN_EGG, "Torchbug");

        provider.addItem(ANCIENT_NORD_HELMET, "Ancient Nord Helmet");
        provider.addItem(ANCIENT_NORD_CHESTPLATE, "Ancient Nord Chestplate");
        provider.addItem(ANCIENT_NORD_LEGGINGS, "Ancient Nord Leggings");
        provider.addItem(ANCIENT_NORD_BOOTS, "Ancient Nord Boots");
        provider.addItem(ANCIENT_NORD_SWORD, "Ancient Nord Sword");
        provider.addItem(ANCIENT_NORD_BATTLEAXE, "Ancient Nord Battleaxe");
        provider.addItem(ANCIENT_NORD_GREATSWORD, "Ancient Nord Greatsword");
        provider.addItem(ANCIENT_NORD_WAR_AXE, "Ancient Nord War Axe");
        provider.addItem(ANCIENT_NORD_BOW, "Ancient Nord Bow");
        provider.addItem(DAEDRIC_HELMET, "Daedric Helmet");
        provider.addItem(DAEDRIC_CHESTPLATE, "Daedric Chestplate");
        provider.addItem(DAEDRIC_LEGGINGS, "Daedric Leggings");
        provider.addItem(DAEDRIC_BOOTS, "Daedric Boots");
        provider.addItem(DAEDRIC_SHIELD, "Daedric Shield");
        provider.addItem(DAEDRIC_DAGGER, "Daedric Dagger");
        provider.addItem(DAEDRIC_SWORD, "Daedric Sword");
        provider.addItem(DAEDRIC_GREATSWORD, "Daedric Greatsword");
        provider.addItem(DAEDRIC_BATTLEAXE, "Daedric Battleaxe");
        provider.addItem(DAEDRIC_BOW, "Daedric Bow");
        provider.addItem(DAEDRIC_MACE, "Daedric Mace");
        provider.addItem(DAEDRIC_WAR_AXE, "Daedric War Axe");
        provider.addItem(DAEDRIC_WARHAMMER, "Daedric Warhammer");
        provider.addItem(DRAGONBONE_DAGGER, "Dragonbone Dagger");
        provider.addItem(DRAGONBONE_SWORD, "Dragonbone Sword");
        provider.addItem(DRAGONBONE_BATTLEAXE, "Dragonbone Battleaxe");
        provider.addItem(DRAGONBONE_BOW, "Dragonbone Bow");
        provider.addItem(DRAGONBONE_MACE, "Dragonbone Mace");
        provider.addItem(DRAGONBONE_WAR_AXE, "Dragonbone War Axe");
        provider.addItem(DRAGONBONE_WARHAMMER, "Dragonbone Warhammer");
        provider.addItem(DRAGONBONE_GREATSWORD, "Dragonbone Greatsword");
        provider.addItem(DWARVEN_HELMET, "Dwarven Helmet");
        provider.addItem(DWARVEN_CHESTPLATE, "Dwarven Chestplate");
        provider.addItem(DWARVEN_LEGGINGS, "Dwarven Leggings");
        provider.addItem(DWARVEN_BOOTS, "Dwarven Boots");
        provider.addItem(DWARVEN_SHIELD, "Dwarven Shield");
        provider.addItem(DWARVEN_DAGGER, "Dwarven Dagger");
        provider.addItem(DWARVEN_SWORD, "Dwarven Sword");
        provider.addItem(DWARVEN_BATTLEAXE, "Dwarven Battleaxe");
        provider.addItem(DWARVEN_GREATSWORD, "Dwarven Greatsword");
        provider.addItem(DWARVEN_BOW, "Dwarven Bow");
        provider.addItem(DWARVEN_MACE, "Dwarven Mace");
        provider.addItem(DWARVEN_WAR_AXE, "Dwarven War Axe");
        provider.addItem(DWARVEN_WARHAMMER, "Dwarven Warhammer");
        provider.addItem(EBONY_HELMET, "Ebony Helmet");
        provider.addItem(EBONY_CHESTPLATE, "Ebony Chestplate");
        provider.addItem(EBONY_LEGGINGS, "Ebony Leggings");
        provider.addItem(EBONY_BOOTS, "Ebony Boots");
        provider.addItem(EBONY_SHIELD, "Ebony Shield");
        provider.addItem(EBONY_DAGGER, "Ebony Dagger");
        provider.addItem(EBONY_SWORD, "Ebony Sword");
        provider.addItem(EBONY_GREATSWORD, "Ebony Greatsword");
        provider.addItem(EBONY_BATTLEAXE, "Ebony Battleaxe");
        provider.addItem(EBONY_BOW, "Ebony Bow");
        provider.addItem(EBONY_MACE, "Ebony Mace");
        provider.addItem(EBONY_WAR_AXE, "Ebony War Axe");
        provider.addItem(EBONY_WARHAMMER, "Ebony Warhammer");
        provider.addItem(ELVEN_HELMET, "Elven Helmet");
        provider.addItem(ELVEN_CHESTPLATE, "Elven Chestplate");
        provider.addItem(ELVEN_LEGGINGS, "Elven Leggings");
        provider.addItem(ELVEN_BOOTS, "Elven Boots");
        provider.addItem(ELVEN_SHIELD, "Elven Shield");
        provider.addItem(ELVEN_DAGGER, "Elven Dagger");
        provider.addItem(ELVEN_SWORD, "Elven Sword");
        provider.addItem(ELVEN_GREATSWORD, "Elven Greatsword");
        provider.addItem(ELVEN_BATTLEAXE, "Elven Battleaxe");
        provider.addItem(ELVEN_BOW, "Elven Bow");
        provider.addItem(ELVEN_MACE, "Elven Mace");
        provider.addItem(ELVEN_WAR_AXE, "Elven War Axe");
        provider.addItem(ELVEN_WARHAMMER, "Elven Warhammer");
        provider.addItem(FALMER_HELMET, "Falmer Helmet");
        provider.addItem(FALMER_CHESTPLATE, "Falmer Chestplate");
        provider.addItem(FALMER_LEGGINGS, "Falmer Leggings");
        provider.addItem(FALMER_BOOTS, "Falmer Boots");
        provider.addItem(FALMER_SWORD, "Falmer Sword");
        provider.addItem(FALMER_BOW, "Falmer Bow");
        provider.addItem(FALMER_WAR_AXE, "Falmer War Axe");
        provider.addItem(GLASS_HELMET, "Glass Helmet");
        provider.addItem(GLASS_CHESTPLATE, "Glass Chestplate");
        provider.addItem(GLASS_LEGGINGS, "Glass Leggings");
        provider.addItem(GLASS_BOOTS, "Glass Boots");
        provider.addItem(GLASS_SHIELD, "Glass Shield");
        provider.addItem(GLASS_DAGGER, "Glass Dagger");
        provider.addItem(GLASS_SWORD, "Glass Sword");
        provider.addItem(GLASS_GREATSWORD, "Glass Greatsword");
        provider.addItem(GLASS_BATTLEAXE, "Glass Battleaxe");
        provider.addItem(GLASS_BOW, "Glass Bow");
        provider.addItem(GLASS_MACE, "Glass Mace");
        provider.addItem(GLASS_WAR_AXE, "Glass War Axe");
        provider.addItem(GLASS_WARHAMMER, "Glass Warhammer");
        provider.addItem(IMPERIAL_HELMET, "Imperial Helmet");
        provider.addItem(IMPERIAL_CHESTPLATE, "Imperial Chestplate");
        provider.addItem(IMPERIAL_LEGGINGS, "Imperial Leggings");
        provider.addItem(IMPERIAL_BOOTS, "Imperial Boots");
        provider.addItem(IMPERIAL_SWORD, "Imperial Sword");
        provider.addItem(IRON_HELMET, "Iron Helmet");
        provider.addItem(IRON_CHESTPLATE, "Iron Chestplate");
        provider.addItem(IRON_LEGGINGS, "Iron Leggings");
        provider.addItem(IRON_BOOTS, "Iron Boots");
        provider.addItem(IRON_SHIELD, "Iron Shield");
        provider.addItem(IRON_DAGGER, "Iron Dagger");
        provider.addItem(IRON_SWORD, "Iron Sword");
        provider.addItem(IRON_BATTLEAXE, "Iron Battleaxe");
        provider.addItem(IRON_GREATSWORD, "Iron Greatsword");
        provider.addItem(IRON_MACE, "Iron Mace");
        provider.addItem(IRON_WAR_AXE, "Iron War Axe");
        provider.addItem(IRON_WARHAMMER, "Iron Warhammer");
        provider.addItem(ORCISH_HELMET, "Orcish Helmet");
        provider.addItem(ORCISH_CHESTPLATE, "Orcish Chestplate");
        provider.addItem(ORCISH_LEGGINGS, "Orcish Leggings");
        provider.addItem(ORCISH_BOOTS, "Orcish Boots");
        provider.addItem(ORCISH_SHIELD, "Orcish Shield");
        provider.addItem(ORCISH_DAGGER, "Orcish Dagger");
        provider.addItem(ORCISH_SWORD, "Orcish Sword");
        provider.addItem(ORCISH_GREATSWORD, "Orcish Greatsword");
        provider.addItem(ORCISH_BATTLEAXE, "Orcish Battleaxe");
        provider.addItem(ORCISH_BOW, "Orcish Bow");
        provider.addItem(ORCISH_MACE, "Orcish Mace");
        provider.addItem(ORCISH_WAR_AXE, "Orcish War Axe");
        provider.addItem(ORCISH_WARHAMMER, "Orcish Warhammer");
//        provider.addItem(STEEL_HELMET, "Steel Helmet");
//        provider.addItem(STEEL_CHESTPLATE, "Steel Chestplate");
//        provider.addItem(STEEL_LEGGINGS, "Steel Leggings");
//        provider.addItem(STEEL_BOOTS, "Steel Boots");
        provider.addItem(STEEL_SHIELD, "Steel Shield");
        provider.addItem(STEEL_DAGGER, "Steel Dagger");
        provider.addItem(STEEL_SWORD, "Steel Sword");
        provider.addItem(STEEL_GREATSWORD, "Steel Greatsword");
        provider.addItem(STEEL_BATTLEAXE, "Steel Battleaxe");
        provider.addItem(STEEL_MACE, "Steel Mace");
        provider.addItem(STEEL_WAR_AXE, "Steel War Axe");
        provider.addItem(STEEL_WARHAMMER, "Steel Warhammer");
        provider.addItem(STORMCLOAK_OFFICER_HELMET, "Stormcloak Officer Helmet");
        provider.addItem(STORMCLOAK_OFFICER_CHESTPLATE, "Stormcloak Officer Chestplate");
        provider.addItem(STORMCLOAK_OFFICER_LEGGINGS, "Stormcloak Officer Leggings");
        provider.addItem(STORMCLOAK_OFFICER_BOOTS, "Stormcloak Officer Boots");
        provider.addItem(HUNTING_BOW, "Hunting Bow");
        provider.addItem(LONGBOW, "Longbow");
        provider.addItem(SCALED_HELMET, "Scaled Helmet");
        provider.addItem(SCALED_CHESTPLATE, "Scaled Chestplate");
        provider.addItem(SCALED_LEGGINGS, "Scaled Leggings");
        provider.addItem(SCALED_BOOTS, "Scaled Boots");
        provider.addItem(HIDE_HELMET, "Hide Helmet");
        provider.addItem(HIDE_CHESTPLATE, "Hide Chestplate");
        provider.addItem(HIDE_LEGGINGS, "Hide Leggings");
        provider.addItem(HIDE_BOOTS, "Hide Boots");

        provider.addItem(STAFF, "Staff");
        provider.addItem(FIREBALL_SPELLBOOK, "Spellbook");
        provider.addItem(CONJURE_FAMILIAR_SPELLBOOK, "Spellbook");
        provider.addItem(HEALING_SPELLBOOK, "Spellbook");
        provider.addItem(TURN_UNDEAD_SPELLBOOK, "Spellbook");
        provider.addItem(LIGHTNING_SPELLBOOK, "Spellbook");

        provider.addItem(GOLD_RING, "Gold Ring");
        provider.addItem(GOLD_SAPPHIRE_RING, "Gold Sapphire Ring");
        provider.addItem(GOLD_EMERALD_RING, "Gold Emerald Ring");
        provider.addItem(GOLD_DIAMOND_RING, "Gold Diamond Ring");
        provider.addItem(SILVER_RING, "Silver Ring");
        provider.addItem(SILVER_AMETHYST_RING, "Silver Amethyst Ring");
        provider.addItem(SILVER_GARNET_RING, "Silver Garnet Ring");
        provider.addItem(SILVER_RUBY_RING, "Silver Ruby Ring");
        provider.addItem(ASGEIRS_WEDDING_BAND, "Asgeir's Wedding Band");
        provider.addItem(AHZIDALS_RING_OF_ARCANA, "Ahzidal's Ring of Arcana");
        provider.addItem(BALWENS_ORNAMENTAL_RING, "Balwen's Ornamental Ring");
        provider.addItem(BONE_HAWK_RING, "Bone Hawk Ring");
        provider.addItem(CALCELMOS_RING, "Calcelmo's Ring");
        provider.addItem(ENCHANTED_RING, "Enchanted Ring");
        provider.addItem(FJOLAS_WEDDING_BAND, "Fjola's Wedding Band");
        provider.addItem(ILAS_TEIS_RING, "Ilas Tei's Ring");
        provider.addItem(KATARINAS_ORNAMENTAL_RING, "Katarina's Ornamental Ring");
        provider.addItem(MADESIS_SILVER_RING, "Madesi's Silver Ring");
        provider.addItem(MUIRIS_RING, "Muiri's Ring");
        provider.addItem(NIGHTWEAVERS_BAND, "Nightweaver's Band");
        provider.addItem(PITHIS_ORNAMENTAL_RING, "Pithi's Ornamental Ring");
        provider.addItem(RING_OF_NAMIRA, "Ring of Namira");
        provider.addItem(TREOYS_ORNAMENTAL_RING, "Treoy's Ornamental Ring");

        provider.addItem(GOLD_NECKLACE, "Gold Necklace");
        provider.addItem(GOLD_DIAMOND_NECKLACE, "Gold Diamond Necklace");
        provider.addItem(GOLD_JEWELLED_NECKLACE, "Gold Jewelled Necklace");
        provider.addItem(GOLD_RUBY_NECKLACE, "Gold Ruby Necklace");
        provider.addItem(SILVER_NECKLACE, "Silver Necklace");
        provider.addItem(SILVER_EMERALD_NECKLACE, "Silver Emerald Necklace");
        provider.addItem(SILVER_JEWELLED_NECKLACE, "Silver Jewelled Necklace");
        provider.addItem(SILVER_SAPPHIRE_NECKLACE, "Silver Sapphire Necklace");

        provider.addItem(MINOR_MAGICKA_POTION, "Potion of Minor Magicka");
        provider.addItem(MAGICKA_POTION, "Potion of Magicka");
        provider.addItem(PLENTIFUL_MAGICKA_POTION, "Potion of Plentiful Magicka");
        provider.addItem(VIGOROUS_MAGICKA_POTION, "Potion of Vigorous Magicka");
        provider.addItem(EXTREME_MAGICKA_POTION, "Potion of Extreme Magicka");
        provider.addItem(ULTIMATE_MAGICKA_POTION, "Potion of Ultimate Magicka");
    }

    public static void registerItemModelProperties() {
        registerTwoHandedProperties(ANCIENT_NORD_BATTLEAXE.get());
        registerTwoHandedProperties(ANCIENT_NORD_GREATSWORD.get());
        registerBowProperties(ANCIENT_NORD_BOW.get());

        registerTwoHandedProperties(DAEDRIC_BATTLEAXE.get());
        registerTwoHandedProperties(DAEDRIC_GREATSWORD.get());
        registerTwoHandedProperties(DAEDRIC_WARHAMMER.get());
        registerBowProperties(DAEDRIC_BOW.get());
        registerShield(DAEDRIC_SHIELD.get());

        registerTwoHandedProperties(DRAGONBONE_BATTLEAXE.get());
        registerTwoHandedProperties(DRAGONBONE_GREATSWORD.get());
        registerTwoHandedProperties(DRAGONBONE_WARHAMMER.get());
        registerBowProperties(DRAGONBONE_BOW.get());

        registerTwoHandedProperties(DWARVEN_BATTLEAXE.get());
        registerTwoHandedProperties(DWARVEN_GREATSWORD.get());
        registerTwoHandedProperties(DWARVEN_WARHAMMER.get());
        registerBowProperties(DWARVEN_BOW.get());
        registerShield(DWARVEN_SHIELD.get());

        registerTwoHandedProperties(EBONY_BATTLEAXE.get());
        registerTwoHandedProperties(EBONY_GREATSWORD.get());
        registerTwoHandedProperties(EBONY_WARHAMMER.get());
        registerBowProperties(EBONY_BOW.get());
        registerShield(EBONY_SHIELD.get());

        registerTwoHandedProperties(ELVEN_BATTLEAXE.get());
        registerTwoHandedProperties(ELVEN_GREATSWORD.get());
        registerTwoHandedProperties(ELVEN_WARHAMMER.get());
        registerBowProperties(ELVEN_BOW.get());
        registerShield(ELVEN_SHIELD.get());

        registerTwoHandedProperties(GLASS_BATTLEAXE.get());
        registerTwoHandedProperties(GLASS_GREATSWORD.get());
        registerTwoHandedProperties(GLASS_WARHAMMER.get());
        registerBowProperties(GLASS_BOW.get());
        registerShield(GLASS_SHIELD.get());

        registerTwoHandedProperties(IRON_BATTLEAXE.get());
        registerTwoHandedProperties(IRON_GREATSWORD.get());
        registerTwoHandedProperties(IRON_WARHAMMER.get());
        registerShield(IRON_SHIELD.get());

        registerTwoHandedProperties(ORCISH_BATTLEAXE.get());
        registerTwoHandedProperties(ORCISH_GREATSWORD.get());
        registerTwoHandedProperties(ORCISH_WARHAMMER.get());
        registerBowProperties(ORCISH_BOW.get());
        registerShield(ORCISH_SHIELD.get());

        registerTwoHandedProperties(STEEL_BATTLEAXE.get());
        registerTwoHandedProperties(STEEL_GREATSWORD.get());
        registerTwoHandedProperties(STEEL_WARHAMMER.get());
        registerShield(STEEL_SHIELD.get());

        // misc
        registerBowProperties(HUNTING_BOW.get());
        registerBowProperties(LONGBOW.get());
    }

    private static void registerTwoHandedProperties(Item item) {
        ItemProperties.register(item, new ResourceLocation(Skyrimcraft.MODID, "no_use"), ClientEvents::noUse);
        ItemProperties.register(item, new ResourceLocation(Skyrimcraft.MODID, "blocking"), ClientEvents::blocking);
    }

    private static void registerBowProperties(Item item) {
        ItemProperties.register(item, new ResourceLocation(Skyrimcraft.MODID, "pulling"), ClientEvents::pulling);
        ItemProperties.register(item, new ResourceLocation(Skyrimcraft.MODID, "pull"), ClientEvents::pull);
    }

    private static void registerShield(Item item) {
        ItemProperties.register(item, new ResourceLocation(Skyrimcraft.MODID, "blocking"), ClientEvents::blocking);
    }
}
