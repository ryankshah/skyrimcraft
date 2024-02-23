package com.ryankshah.skyrimcraft.data.item;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.BlockInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import com.ryankshah.skyrimcraft.util.NameUtils;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SkyrimcraftItemModelProvider extends ItemModelProvider
{
    public SkyrimcraftItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Skyrimcraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        item(ItemInit.SWEET_ROLL.get());
        item(ItemInit.GARLIC_BREAD.get());
        item(ItemInit.POTATO_BREAD.get());
        item(ItemInit.TOMATO.get());
        item(ItemInit.GARLIC.get());
        item(ItemInit.APPLE_PIE.get());
        item(ItemInit.MAMMOTH_SNOUT.get());
        item(ItemInit.MAMMOTH_STEAK.get());
        item(ItemInit.VENISON.get());
        item(ItemInit.FLOUR.get());
        item(ItemInit.BUTTER.get());
        item(ItemInit.CABBAGE.get());
        item(ItemInit.TOMATO_SOUP.get());
        item(ItemInit.APPLE_CABBAGE_STEW.get());
        item(ItemInit.APPLE_DUMPLING.get());
        item(ItemInit.BEEF_STEW.get());
        item(ItemInit.CABBAGE_SOUP.get());
        item(ItemInit.CABBAGE_POTATO_SOUP.get());
        item(ItemInit.CHICKEN_DUMPLING.get());
        item(ItemInit.CLAM_MEAT.get());
        item(ItemInit.SLICED_GOAT_CHEESE.get());
        item(ItemInit.SLICED_EIDAR_CHEESE.get());
        item(ItemInit.GOURD.get());
        item(ItemInit.LEEK.get());
        item(ItemInit.LEG_OF_GOAT.get());
        item(ItemInit.LEG_OF_GOAT_ROAST.get());
        item(ItemInit.HORSE_MEAT.get());
        item(ItemInit.HORSE_HAUNCH.get());
        item(ItemInit.VEGETABLE_SOUP.get());

        item(ItemInit.TOMATO_SEEDS.get());

        item(ItemInit.ALE.get());
        item(ItemInit.ALTO_WINE.get());
        item(ItemInit.ARGONIAN_ALE.get());
        item(ItemInit.NORD_MEAD.get());
        item(ItemInit.BLACK_BRIAR_MEAD.get());
        item(ItemInit.BLACK_BRIAR_RESERVE.get());
        item(ItemInit.DRAGONS_BREATH_MEAD.get());
        item(ItemInit.FIREBRAND_WINE.get());
        item(ItemInit.HONNINGBREW_MEAD.get());
        item(ItemInit.MEAD_WITH_JUNIPER_BERRY.get());
        item(ItemInit.SKOOMA.get());
        item(ItemInit.SPICED_WINE.get());
        item(ItemInit.WINE.get());

        item(ItemInit.CORUNDUM_INGOT.get());
        item(ItemInit.DWARVEN_METAL_INGOT.get());
        item(ItemInit.EBONY_INGOT.get());
        item(ItemInit.MALACHITE_INGOT.get());
        item(ItemInit.MOONSTONE_INGOT.get());
        item(ItemInit.ORICHALCUM_INGOT.get());
        item(ItemInit.QUICKSILVER_INGOT.get());
        item(ItemInit.SILVER_INGOT.get());
        item(ItemInit.STEEL_INGOT.get());
        item(ItemInit.LEATHER_STRIPS.get());
        item(ItemInit.DAEDRA_HEART.get());

        item(ItemInit.FLAWED_AMETHYST.get());
        item(ItemInit.FLAWED_DIAMOND.get());
        item(ItemInit.FLAWED_EMERALD.get());
        item(ItemInit.FLAWED_RUBY.get());
        item(ItemInit.FLAWLESS_RUBY.get());
        item(ItemInit.FLAWED_GARNET.get());
        item(ItemInit.FLAWLESS_GARNET.get());

        item(ItemInit.SALT_PILE.get());
        item(ItemInit.CREEP_CLUSTER.get());
        item(ItemInit.GRASS_POD.get());
        item(ItemInit.VAMPIRE_DUST.get());
        item(ItemInit.MORA_TAPINELLA.get());
        item(ItemInit.BRIAR_HEART.get());
        item(ItemInit.GIANTS_TOE.get());
        item(ItemInit.SALMON_ROE.get());
        item(ItemInit.DWARVEN_OIL.get());
        item(ItemInit.FIRE_SALTS.get());
        item(ItemInit.ABECEAN_LONGFIN.get());
        item(ItemInit.CYRODILIC_SPADETAIL.get());
        item(ItemInit.SABRE_CAT_TOOTH.get());
        item(ItemInit.BLUE_DARTWING.get());
        item(ItemInit.ORANGE_DARTWING.get());
        item(ItemInit.PEARL.get());
        item(ItemInit.SMALL_PEARL.get());
        item(ItemInit.PINE_THRUSH_EGG.get());
        item(ItemInit.ROCK_WARBLER_EGG.get());
        item(ItemInit.SLAUGHTERFISH_EGG.get());
        item(ItemInit.SLAUGHTERFISH_SCALES.get());
        item(ItemInit.SPIDER_EGG.get());
        item(ItemInit.HAWK_EGG.get());
        item(ItemInit.TROLL_FAT.get());
        item(ItemInit.CHAURUS_EGGS.get());
        item(ItemInit.FLY_AMANITA.get());
        item(ItemInit.ELVES_EAR.get());
        item(ItemInit.TAPROOT.get());
        item(ItemInit.BEE.get());
        item(ItemInit.EYE_OF_SABRE_CAT.get());
        item(ItemInit.BEAR_CLAWS.get());
        item(ItemInit.BEEHIVE_HUSK.get());
        item(ItemInit.BERITS_ASHES.get());
//        item(ItemInit.BLEEDING_CROWN.get());
        item(ItemInit.BLISTERWORT.get());
        item(ItemInit.BLUE_BUTTERFLY_WING.get());
        item(ItemInit.BUTTERFLY_WING.get());
//        item(ItemInit.CANIS_ROOT.get());
        item(ItemInit.CHARRED_SKEEVER_HIDE.get());
        item(ItemInit.CRIMSON_NIRNROOT.get());
        item(ItemInit.DEATHBELL.get());
        item(ItemInit.DRAGONS_TONGUE.get());
        item(ItemInit.ECTOPLASM.get());
        item(ItemInit.FALMER_EAR.get());

        egg(ItemInit.BLUE_BUTTERFLY_SPAWN_EGG.get());
        egg(ItemInit.MONARCH_BUTTERFLY_SPAWN_EGG.get());
        egg(ItemInit.LUNAR_MOTH_SPAWN_EGG.get());
        egg(ItemInit.ORANGE_DARTWING_SPAWN_EGG.get());
        egg(ItemInit.BLUE_DARTWING_SPAWN_EGG.get());
        egg(ItemInit.TORCHBUG_SPAWN_EGG.get());
        egg(ItemInit.DRAGON_SPAWN_EGG.get());
        egg(ItemInit.GIANT_SPAWN_EGG.get());
        egg(ItemInit.SABRE_CAT_SPAWN_EGG.get());

        item(ItemInit.ANCIENT_NORD_HELMET.get());
        item(ItemInit.ANCIENT_NORD_CHESTPLATE.get());
        item(ItemInit.ANCIENT_NORD_LEGGINGS.get());
        item(ItemInit.ANCIENT_NORD_BOOTS.get());
        sword(ItemInit.ANCIENT_NORD_SWORD.get());
        greatsword(ItemInit.ANCIENT_NORD_GREATSWORD.get());
        sword(ItemInit.ANCIENT_NORD_WAR_AXE.get());
        sword(ItemInit.ANCIENT_NORD_BATTLEAXE.get());
        bow(ItemInit.ANCIENT_NORD_BOW.get());

        item(ItemInit.DAEDRIC_HELMET.get());
        item(ItemInit.DAEDRIC_CHESTPLATE.get());
        item(ItemInit.DAEDRIC_LEGGINGS.get());
        item(ItemInit.DAEDRIC_BOOTS.get());
        sword(ItemInit.DAEDRIC_DAGGER.get());
        sword(ItemInit.DAEDRIC_SWORD.get());
        sword(ItemInit.DAEDRIC_BATTLEAXE.get());
        bow(ItemInit.DAEDRIC_BOW.get());
        greatsword(ItemInit.DAEDRIC_GREATSWORD.get());
        sword(ItemInit.DAEDRIC_MACE.get());
        sword(ItemInit.DAEDRIC_WAR_AXE.get());
        sword(ItemInit.DAEDRIC_WARHAMMER.get());

        sword(ItemInit.DRAGONBONE_DAGGER.get());
        sword(ItemInit.DRAGONBONE_SWORD.get());
        sword(ItemInit.DRAGONBONE_BATTLEAXE.get());
        bow(ItemInit.DRAGONBONE_BOW.get());
        greatsword(ItemInit.DRAGONBONE_GREATSWORD.get());
        sword(ItemInit.DRAGONBONE_MACE.get());
        sword(ItemInit.DRAGONBONE_WAR_AXE.get());
        sword(ItemInit.DRAGONBONE_WARHAMMER.get());

        item(ItemInit.DWARVEN_HELMET.get());
        item(ItemInit.DWARVEN_CHESTPLATE.get());
        item(ItemInit.DWARVEN_LEGGINGS.get());
        item(ItemInit.DWARVEN_BOOTS.get());
        sword(ItemInit.DWARVEN_DAGGER.get());
        sword(ItemInit.DWARVEN_SWORD.get());
        sword(ItemInit.DWARVEN_BATTLEAXE.get());
        bow(ItemInit.DWARVEN_BOW.get());
        greatsword(ItemInit.DWARVEN_GREATSWORD.get());
        sword(ItemInit.DWARVEN_MACE.get());
        sword(ItemInit.DWARVEN_WAR_AXE.get());
        sword(ItemInit.DWARVEN_WARHAMMER.get());

        item(ItemInit.EBONY_HELMET.get());
        item(ItemInit.EBONY_CHESTPLATE.get());
        item(ItemInit.EBONY_LEGGINGS.get());
        item(ItemInit.EBONY_BOOTS.get());
        sword(ItemInit.EBONY_DAGGER.get());
        sword(ItemInit.EBONY_SWORD.get());
        sword(ItemInit.EBONY_BATTLEAXE.get());
        bow(ItemInit.EBONY_BOW.get());
        greatsword(ItemInit.EBONY_GREATSWORD.get());
        sword(ItemInit.EBONY_MACE.get());
        sword(ItemInit.EBONY_WAR_AXE.get());
        sword(ItemInit.EBONY_WARHAMMER.get());

        item(ItemInit.ELVEN_HELMET.get());
        item(ItemInit.ELVEN_CHESTPLATE.get());
        item(ItemInit.ELVEN_LEGGINGS.get());
        item(ItemInit.ELVEN_BOOTS.get());
        sword(ItemInit.ELVEN_DAGGER.get());
        sword(ItemInit.ELVEN_SWORD.get());
        sword(ItemInit.ELVEN_BATTLEAXE.get());
        bow(ItemInit.ELVEN_BOW.get());
        greatsword(ItemInit.ELVEN_GREATSWORD.get());
        sword(ItemInit.ELVEN_MACE.get());
        sword(ItemInit.ELVEN_WAR_AXE.get());
        sword(ItemInit.ELVEN_WARHAMMER.get());

        item(ItemInit.FALMER_HELMET.get());
        item(ItemInit.FALMER_CHESTPLATE.get());
        item(ItemInit.FALMER_LEGGINGS.get());
        item(ItemInit.FALMER_BOOTS.get());
        sword(ItemInit.FALMER_SWORD.get());
        bow(ItemInit.FALMER_BOW.get());
        sword(ItemInit.FALMER_WAR_AXE.get());

        item(ItemInit.GLASS_HELMET.get());
        item(ItemInit.GLASS_CHESTPLATE.get());
        item(ItemInit.GLASS_LEGGINGS.get());
        item(ItemInit.GLASS_BOOTS.get());
        sword(ItemInit.GLASS_DAGGER.get());
        sword(ItemInit.GLASS_SWORD.get());
        sword(ItemInit.GLASS_BATTLEAXE.get());
        bow(ItemInit.GLASS_BOW.get());
        greatsword(ItemInit.GLASS_GREATSWORD.get());
        sword(ItemInit.GLASS_MACE.get());
        sword(ItemInit.GLASS_WAR_AXE.get());
        sword(ItemInit.GLASS_WARHAMMER.get());

        item(ItemInit.IMPERIAL_HELMET.get());
        item(ItemInit.IMPERIAL_CHESTPLATE.get());
        item(ItemInit.IMPERIAL_LEGGINGS.get());
        item(ItemInit.IMPERIAL_BOOTS.get());
        sword(ItemInit.IMPERIAL_SWORD.get());

        item(ItemInit.IRON_HELMET.get());
        item(ItemInit.IRON_CHESTPLATE.get());
        item(ItemInit.IRON_LEGGINGS.get());
        item(ItemInit.IRON_BOOTS.get());
        sword(ItemInit.IRON_DAGGER.get());
        sword(ItemInit.IRON_SWORD.get());
        sword(ItemInit.IRON_BATTLEAXE.get());
        greatsword(ItemInit.IRON_GREATSWORD.get());
        sword(ItemInit.IRON_MACE.get());
        sword(ItemInit.IRON_WAR_AXE.get());
        sword(ItemInit.IRON_WARHAMMER.get());

        item(ItemInit.ORCISH_HELMET.get());
        item(ItemInit.ORCISH_CHESTPLATE.get());
        item(ItemInit.ORCISH_LEGGINGS.get());
        item(ItemInit.ORCISH_BOOTS.get());
        sword(ItemInit.ORCISH_DAGGER.get());
        sword(ItemInit.ORCISH_SWORD.get());
        sword(ItemInit.ORCISH_BATTLEAXE.get());
        bow(ItemInit.ORCISH_BOW.get());
        greatsword(ItemInit.ORCISH_GREATSWORD.get());
        sword(ItemInit.ORCISH_MACE.get());
        sword(ItemInit.ORCISH_WAR_AXE.get());
        sword(ItemInit.ORCISH_WARHAMMER.get());

//        item(ItemInit.STEEL_HELMET.get());
//        item(ItemInit.STEEL_CHESTPLATE.get());
//        item(ItemInit.STEEL_LEGGINGS.get());
//        item(ItemInit.STEEL_BOOTS.get());
        sword(ItemInit.STEEL_DAGGER.get());
        sword(ItemInit.STEEL_SWORD.get());
        sword(ItemInit.STEEL_BATTLEAXE.get());
        greatsword(ItemInit.STEEL_GREATSWORD.get());
        sword(ItemInit.STEEL_MACE.get());
        sword(ItemInit.STEEL_WAR_AXE.get());
        sword(ItemInit.STEEL_WARHAMMER.get());

        item(ItemInit.STORMCLOAK_OFFICER_HELMET.get());
        item(ItemInit.STORMCLOAK_OFFICER_CHESTPLATE.get());
        item(ItemInit.STORMCLOAK_OFFICER_LEGGINGS.get());
        item(ItemInit.STORMCLOAK_OFFICER_BOOTS.get());

        bow(ItemInit.HUNTING_BOW.get());
        bow(ItemInit.LONGBOW.get());

        item(ItemInit.SCALED_HELMET.get());
        item(ItemInit.SCALED_CHESTPLATE.get());
        item(ItemInit.SCALED_LEGGINGS.get());
        item(ItemInit.SCALED_BOOTS.get());

        item(ItemInit.HIDE_HELMET.get());
        item(ItemInit.HIDE_CHESTPLATE.get());
        item(ItemInit.HIDE_LEGGINGS.get());
        item(ItemInit.HIDE_BOOTS.get());

        item(ItemInit.CHILLREND.get());
        item(ItemInit.DAWNBREAKER.get());

        item(ItemInit.GOLD_RING.get());
        item(ItemInit.GOLD_SAPPHIRE_RING.get());
        item(ItemInit.GOLD_EMERALD_RING.get());
        item(ItemInit.GOLD_DIAMOND_RING.get());
        item(ItemInit.SILVER_RING.get());
        item(ItemInit.SILVER_AMETHYST_RING.get());
        item(ItemInit.SILVER_GARNET_RING.get());
        item(ItemInit.SILVER_RUBY_RING.get());
        item(ItemInit.ASGEIRS_WEDDING_BAND.get());
        item(ItemInit.AHZIDALS_RING_OF_ARCANA.get());
        item(ItemInit.BALWENS_ORNAMENTAL_RING.get());
        item(ItemInit.BONE_HAWK_RING.get());
        item(ItemInit.CALCELMOS_RING.get());
        item(ItemInit.ENCHANTED_RING.get());
        item(ItemInit.FJOLAS_WEDDING_BAND.get());
        item(ItemInit.ILAS_TEIS_RING.get());
        item(ItemInit.KATARINAS_ORNAMENTAL_RING.get());
        item(ItemInit.MADESIS_SILVER_RING.get());
        item(ItemInit.MUIRIS_RING.get());
        item(ItemInit.NIGHTWEAVERS_BAND.get());
        item(ItemInit.PITHIS_ORNAMENTAL_RING.get());
        item(ItemInit.RING_OF_NAMIRA.get());
        item(ItemInit.TREOYS_ORNAMENTAL_RING.get());

        item(ItemInit.GOLD_NECKLACE.get());
        item(ItemInit.GOLD_DIAMOND_NECKLACE.get());
        item(ItemInit.GOLD_JEWELLED_NECKLACE.get());
        item(ItemInit.GOLD_RUBY_NECKLACE.get());
        item(ItemInit.SILVER_NECKLACE.get());
        item(ItemInit.SILVER_EMERALD_NECKLACE.get());
        item(ItemInit.SILVER_JEWELLED_NECKLACE.get());
        item(ItemInit.SILVER_SAPPHIRE_NECKLACE.get());

        item(ItemInit.STAFF.get());
        spellbook(ItemInit.CONJURE_FAMILIAR_SPELLBOOK.get());
        spellbook(ItemInit.FIREBALL_SPELLBOOK.get());
        spellbook(ItemInit.HEALING_SPELLBOOK.get());
        spellbook(ItemInit.TURN_UNDEAD_SPELLBOOK.get());
        spellbook(ItemInit.LIGHTNING_SPELLBOOK.get());

        item(ItemInit.MINOR_MAGICKA_POTION.get());
        item(ItemInit.MAGICKA_POTION.get());
        item(ItemInit.PLENTIFUL_MAGICKA_POTION.get());
        item(ItemInit.VIGOROUS_MAGICKA_POTION.get());
        item(ItemInit.EXTREME_MAGICKA_POTION.get());
        item(ItemInit.ULTIMATE_MAGICKA_POTION.get());

        BlockInit.addBlockItemModels(this);
    }


    private void item(Item item) {
        String name = NameUtils.getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/" + name);
    }

    private void spellbook(Item item) {
        String name = NameUtils.getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "item/spellbook");
    }

    private ItemModelBuilder item(String name) {
        return getBuilder(name)
                .parent(getExistingFile(mcLoc("item/bow")))
                .texture("layer0", "item/" + name);
    }

    private void egg(Item item) {
        String name = NameUtils.getItemName(item);
        getBuilder(name).parent(getExistingFile(mcLoc("item/template_spawn_egg")));
    }

    private void sword(Item item) {
        String name = NameUtils.getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "item/" + name);
    }

    private void greatsword(Item item) {
        String name = NameUtils.getItemName(item);
        getBuilder(name+"_no_use")
                .parent(getExistingFile(modLoc("item/greatsword_no_use")))
                .texture("layer0", "item/" + name)
                .texture("layer1", mcLoc("item/barrier"));
        getBuilder(name+"_blocking")
                .parent(getExistingFile(modLoc("item/greatsword_blocking")))
                .texture("layer0", "item/" + name);
        getBuilder(name)
                .parent(getExistingFile(modLoc("item/greatsword")))
                .texture("layer0", "item/" + name)
                .override().predicate(new ResourceLocation(Skyrimcraft.MODID, "no_use"), 1.0f)
                .model(getExistingFile(modLoc("item/" + name + "_no_use"))).end()
                .override().predicate(modLoc("blocking"), 1.0f)
                .model(getExistingFile(modLoc("item/" + name + "_blocking"))).end();
    }

    private void bow(Item item) {
        String name = NameUtils.getItemName(item);
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/bow")))
                .texture("layer0", "item/" + name)
                .override().predicate(new ResourceLocation("pulling"), 1).model(item(name + "_pulling_0")).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.65f).model(item(name + "_pulling_1")).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.9f).model(item(name + "_pulling_2")).end();
    }
}