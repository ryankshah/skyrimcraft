package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.block.*;
import com.ryankshah.skyrimcraft.item.SkyrimBlockItemIngredient;
import com.ryankshah.skyrimcraft.util.IngredientEffect;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class BlockInit
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Skyrimcraft.MODID);

    public static final DeferredBlock<Block> CORUNDUM_ORE = BLOCKS.register("corundum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            )
    );
    public static final DeferredItem<BlockItem> CORUNDUM_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(CORUNDUM_ORE);
    public static final DeferredBlock<Block> DEEPSLATE_CORUNDUM_ORE = BLOCKS.register("deepslate_corundum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> DEEPSLATE_CORUNDUM_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(DEEPSLATE_CORUNDUM_ORE);
    public static final DeferredBlock<Block> EBONY_ORE = BLOCKS.register("ebony_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> EBONY_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(EBONY_ORE);
    public static final DeferredBlock<Block> DEEPSLATE_EBONY_ORE = BLOCKS.register("deepslate_ebony_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> DEEPSLATE_EBONY_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(DEEPSLATE_EBONY_ORE);
    public static final DeferredBlock<Block> MALACHITE_ORE = BLOCKS.register("malachite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> MALACHITE_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(MALACHITE_ORE);
    public static final DeferredBlock<Block> DEEPSLATE_MALACHITE_ORE = BLOCKS.register("deepslate_malachite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> DEEPSLATE_MALACHITE_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(DEEPSLATE_MALACHITE_ORE);
    public static final DeferredBlock<Block> MOONSTONE_ORE = BLOCKS.register("moonstone_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> MOONSTONE_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(MOONSTONE_ORE);
    public static final DeferredBlock<Block> DEEPSLATE_MOONSTONE_ORE = BLOCKS.register("deepslate_moonstone_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> DEEPSLATE_MOONSTONE_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(DEEPSLATE_MOONSTONE_ORE);
    public static final DeferredBlock<Block> ORICHALCUM_ORE = BLOCKS.register("orichalcum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> ORICHALCUM_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(ORICHALCUM_ORE);
    public static final DeferredBlock<Block> DEEPSLATE_ORICHALCUM_ORE = BLOCKS.register("deepslate_orichalcum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> DEEPSLATE_ORICHALCUM_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(DEEPSLATE_ORICHALCUM_ORE);
    public static final DeferredBlock<Block> QUICKSILVER_ORE = BLOCKS.register("quicksilver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> QUICKSILVER_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(QUICKSILVER_ORE);
    public static final DeferredBlock<Block> DEEPSLATE_QUICKSILVER_ORE = BLOCKS.register("deepslate_quicksilver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> DEEPSLATE_QUICKSILVER_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(DEEPSLATE_QUICKSILVER_ORE);
    public static final DeferredBlock<Block> SILVER_ORE = BLOCKS.register("silver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> SILVER_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(SILVER_ORE);
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = BLOCKS.register("deepslate_silver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredItem<BlockItem> DEEPSLATE_SILVER_ORE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(DEEPSLATE_SILVER_ORE);

    public static final DeferredBlock<Block> BIRDS_NEST = BLOCKS.register("birds_nest",
            BirdsNestBlock::new);
    public static final DeferredItem<BlockItem> BIRDS_NEST_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(BIRDS_NEST);


    public static final DeferredBlock<Block> SHOUT_BLOCK = BLOCKS.register("shout_block",
            ShoutBlock::new);
    public static final DeferredItem<BlockItem> SHOUT_BLOCK_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(SHOUT_BLOCK);

    public static final DeferredBlock<Block> ALCHEMY_TABLE = BLOCKS.register("alchemy_table",
            AlchemyTableBlock::new);
    public static final DeferredItem<BlockItem> ALCHEMY_TABLE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(ALCHEMY_TABLE);
    public static final DeferredBlock<Block> OVEN = BLOCKS.register("oven",
            OvenBlock::new);
    public static final DeferredItem<BlockItem> OVEN_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(OVEN);
    public static final DeferredBlock<Block> BLACKSMITH_FORGE = BLOCKS.register("blacksmith_forge",
            BlacksmithForgeBlock::new);
    public static final DeferredItem<BlockItem> BLACKSMITH_FORGE_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(BLACKSMITH_FORGE);

    public static final DeferredBlock<Block> WEAPON_RACK = BLOCKS.register("weapon_rack",
            WeaponRackBlock::new);
    public static final DeferredItem<BlockItem> WEAPON_RACK_ITEM = ItemInit.ITEMS.registerSimpleBlockItem(WEAPON_RACK);

    public static final DeferredBlock<Block> RED_MOUNTAIN_FLOWER = BLOCKS.register("red_mountain_flower",
            () -> new SkyrimFlower(
                    MobEffects.SATURATION,
                    7,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
                            .pushReaction(PushReaction.DESTROY)
            ));
    public static final DeferredItem<BlockItem> RED_MOUNTAIN_FLOWER_ITEM = ItemInit.ITEMS.register("red_mountain_flower", () ->
            new SkyrimBlockItemIngredient(RED_MOUNTAIN_FLOWER.get(), new Item.Properties(), IngredientEffect.RESTORE_MAGICKA, IngredientEffect.RAVAGE_MAGICKA, IngredientEffect.FORTIFY_MAGICKA, IngredientEffect.DAMAGE_HEALTH));
    public static final DeferredBlock<Block> BLUE_MOUNTAIN_FLOWER = BLOCKS.register("blue_mountain_flower",
            () -> new SkyrimFlower(
                    MobEffects.SATURATION,
                    7,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
                            .pushReaction(PushReaction.DESTROY)
            ));
    public static final DeferredItem<BlockItem> BLUE_MOUNTAIN_FLOWER_ITEM = ItemInit.ITEMS.register("blue_mountain_flower", () ->
            new SkyrimBlockItemIngredient(BLUE_MOUNTAIN_FLOWER.get(), new Item.Properties(), IngredientEffect.RESTORE_HEALTH, IngredientEffect.FORTIFY_CONJURATION, IngredientEffect.FORTIFY_HEALTH, IngredientEffect.DAMAGE_MAGICKA_REGEN));
    public static final DeferredBlock<Block> YELLOW_MOUNTAIN_FLOWER = BLOCKS.register("yellow_mountain_flower",
            () -> new SkyrimFlower(
                    MobEffects.SATURATION,
                    7,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
                            .pushReaction(PushReaction.DESTROY)
            ));
    public static final DeferredItem<BlockItem> YELLOW_MOUNTAIN_FLOWER_ITEM = ItemInit.ITEMS.register("yellow_mountain_flower", () ->
        new SkyrimBlockItemIngredient(YELLOW_MOUNTAIN_FLOWER.get(), new Item.Properties(), IngredientEffect.RESIST_POISON, IngredientEffect.FORTIFY_RESTORATION, IngredientEffect.FORTIFY_HEALTH, IngredientEffect.DAMAGE_STAMINA_REGEN));
    public static final DeferredBlock<Block> PURPLE_MOUNTAIN_FLOWER = BLOCKS.register("purple_mountain_flower",
            () -> new SkyrimFlower(
                    MobEffects.SATURATION,
                    7,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.GRASS)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
                            .pushReaction(PushReaction.DESTROY)
            ));
    public static final DeferredItem<BlockItem> PURPLE_MOUNTAIN_FLOWER_ITEM = ItemInit.ITEMS.register("purple_mountain_flower", () ->
            new SkyrimBlockItemIngredient(PURPLE_MOUNTAIN_FLOWER.get(), new Item.Properties(), IngredientEffect.RESTORE_STAMINA, IngredientEffect.FORTIFY_SNEAK, IngredientEffect.LINGERING_DAMAGE_MAGICKA, IngredientEffect.RESIST_FROST));
    public static final DeferredBlock<Block> CANIS_ROOT_BLOCK = BLOCKS.register("canis_root",
            () -> new CanisRoot(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.SWEET_BERRY_BUSH)
                            .offsetType(BlockBehaviour.OffsetType.XZ)
                            .pushReaction(PushReaction.DESTROY)
            ));
    public static final DeferredItem<BlockItem> CANIS_ROOT = ItemInit.ITEMS.register("canis_root",
            () -> new SkyrimBlockItemIngredient(CANIS_ROOT_BLOCK.get(), new Item.Properties(), IngredientEffect.DAMAGE_STAMINA, IngredientEffect.FORTIFY_ONE_HANDED, IngredientEffect.FORTIFY_MARKSMAN, IngredientEffect.PARALYSIS));

    public static final DeferredBlock<Block> BLEEDING_CROWN_BLOCK = BLOCKS.register("bleeding_crown",
            GenericTripleMushroom::new);
    public static final DeferredItem<BlockItem> BLEEDING_CROWN = ItemInit.ITEMS.register("bleeding_crown",
            () -> new SkyrimBlockItemIngredient(BLEEDING_CROWN_BLOCK.get(), new Item.Properties(), IngredientEffect.WEAKNESS_TO_FIRE, IngredientEffect.FORTIFY_BLOCK, IngredientEffect.WEAKNESS_TO_POISON, IngredientEffect.RESIST_MAGIC));

    public static final DeferredBlock<Block> WHITE_CAP_BLOCK = BLOCKS.register("white_cap",
            GenericTripleMushroom::new);
    public static final DeferredItem<BlockItem> WHITE_CAP = ItemInit.ITEMS.register("white_cap",
            () -> new SkyrimBlockItemIngredient(WHITE_CAP_BLOCK.get(), new Item.Properties(), IngredientEffect.WEAKNESS_TO_FIRE, IngredientEffect.FORTIFY_BLOCK, IngredientEffect.WEAKNESS_TO_POISON, IngredientEffect.RESIST_MAGIC));


    public static final DeferredBlock<Block> BLISTERWORT_BLOCK = BLOCKS.register("blisterwort",
            GenericTripleMushroom::new);
    public static final DeferredItem<BlockItem> BLISTERWORT = ItemInit.ITEMS.register("blisterwort",
            () -> new SkyrimBlockItemIngredient(BLISTERWORT_BLOCK.get(), new Item.Properties(), IngredientEffect.DAMAGE_STAMINA, IngredientEffect.FRENZY, IngredientEffect.RESTORE_HEALTH, IngredientEffect.FORTIFY_SMITHING));

    public static final DeferredBlock<Block> FLY_AMANITA_BLOCK = BLOCKS.register("fly_amanita",
            GenericTripleMushroom::new);
    public static final DeferredItem<BlockItem> FLY_AMANITA = ItemInit.ITEMS.register("fly_amanita",
            () -> new SkyrimBlockItemIngredient(FLY_AMANITA_BLOCK.get(), new Item.Properties(), IngredientEffect.RESIST_FIRE, IngredientEffect.FORTIFY_TWO_HANDED, IngredientEffect.FRENZY, IngredientEffect.REGENERATE_STAMINA));

    public static final DeferredBlock<Block> CREEP_CLUSTER_BLOCK = BLOCKS.register("creep_cluster",
            () -> new CreepClusterBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .noOcclusion()
                            .sound(SoundType.HARD_CROP)
                            .randomTicks()
            ));
    public static final DeferredItem<BlockItem> CREEP_CLUSTER = ItemInit.ITEMS.register("creep_cluster",
            () -> new SkyrimBlockItemIngredient(CREEP_CLUSTER_BLOCK.get(), new Item.Properties(), IngredientEffect.RESTORE_MAGICKA, IngredientEffect.DAMAGE_STAMINA_REGEN, IngredientEffect.FORTIFY_CARRY_WEIGHT, IngredientEffect.WEAKNESS_TO_MAGIC));


    public static final DeferredBlock<Block> PEARL_OYSTER_BLOCK = BLOCKS.register("pearl_oyster",
            PearlOysterBlock::new);
    public static final DeferredItem<BlockItem> PEARL_OYSTER = ItemInit.ITEMS.register("pearl_oyster", () -> new BlockItem(PEARL_OYSTER_BLOCK.get(), new Item.Properties()));

    public static final DeferredBlock<Block> TOMATO_CROP = BLOCKS.register("tomatoes", () -> new TomatoCrop(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final DeferredBlock<Block> GARLIC_CROP = BLOCKS.register("garlic", () -> new GarlicCrop(
            BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY)

    ));

    public static void addBlockTranslations(LanguageProvider provider) {
        provider.addBlock(CORUNDUM_ORE, "Corundum Ore");
        provider.addBlock(DEEPSLATE_CORUNDUM_ORE, "Corundum Ore");
        provider.addBlock(EBONY_ORE, "Ebony Ore");
        provider.addBlock(DEEPSLATE_EBONY_ORE, "Ebony Ore");
        provider.addBlock(MALACHITE_ORE, "Malachite Ore");
        provider.addBlock(DEEPSLATE_MALACHITE_ORE, "Malachite Ore");
        provider.addBlock(MOONSTONE_ORE, "Moonstone Ore");
        provider.addBlock(DEEPSLATE_MOONSTONE_ORE, "Moonstone Ore");
        provider.addBlock(ORICHALCUM_ORE, "Orichalcum Ore");
        provider.addBlock(DEEPSLATE_ORICHALCUM_ORE, "Orichalcum Ore");
        provider.addBlock(QUICKSILVER_ORE, "Quicksilver Ore");
        provider.addBlock(DEEPSLATE_QUICKSILVER_ORE, "Quicksilver Ore");
        provider.addBlock(SILVER_ORE, "Silver Ore");
        provider.addBlock(DEEPSLATE_SILVER_ORE, "Silver Ore");

        provider.addBlock(SHOUT_BLOCK, "Shout Block");

        provider.addBlock(BIRDS_NEST, "Bird's Nest");

        provider.addBlock(ALCHEMY_TABLE, "Alchemy Table");
        provider.addBlock(OVEN, "Oven");
        provider.addBlock(BLACKSMITH_FORGE, "Blacksmith Forge");
        provider.addBlock(WEAPON_RACK, "Weapon Rack");

        provider.addBlock(RED_MOUNTAIN_FLOWER, "Red Mountain Flower");
        provider.addBlock(BLUE_MOUNTAIN_FLOWER, "Blue Mountain Flower");
        provider.addBlock(YELLOW_MOUNTAIN_FLOWER, "Yellow Mountain Flower");
        provider.addBlock(PURPLE_MOUNTAIN_FLOWER, "Purple Mountain Flower");

        provider.addBlock(BLEEDING_CROWN_BLOCK, "Bleeding Crown");
        provider.addBlock(WHITE_CAP_BLOCK, "White Cap");
        provider.addBlock(BLISTERWORT_BLOCK, "Blisterwort");
        provider.addBlock(FLY_AMANITA_BLOCK, "Fly Amanita");
        provider.addBlock(CANIS_ROOT_BLOCK, "Canis Root");
        provider.addBlock(CREEP_CLUSTER_BLOCK, "Creep Cluster");

        provider.addBlock(PEARL_OYSTER_BLOCK, "Pearl Oyster");

        provider.addBlock(TOMATO_CROP, "Tomatoes");
        provider.addBlock(GARLIC_CROP, "Garlic");
    }

    public static void addBlockStateModels(BlockStateProvider provider) {
        normalBlock(provider, CORUNDUM_ORE.get());
        normalBlock(provider, DEEPSLATE_CORUNDUM_ORE.get());
        normalBlock(provider, EBONY_ORE.get());
        normalBlock(provider, DEEPSLATE_EBONY_ORE.get());
        normalBlock(provider, MALACHITE_ORE.get());
        normalBlock(provider, DEEPSLATE_MALACHITE_ORE.get());
        normalBlock(provider, MOONSTONE_ORE.get());
        normalBlock(provider, DEEPSLATE_MOONSTONE_ORE.get());
        normalBlock(provider, ORICHALCUM_ORE.get());
        normalBlock(provider, DEEPSLATE_ORICHALCUM_ORE.get());
        normalBlock(provider, QUICKSILVER_ORE.get());
        normalBlock(provider, DEEPSLATE_QUICKSILVER_ORE.get());
        normalBlock(provider, SILVER_ORE.get());
        normalBlock(provider, DEEPSLATE_SILVER_ORE.get());

        normalBlock(provider, SHOUT_BLOCK.get());

        flowerBlock(provider, RED_MOUNTAIN_FLOWER.get());
        flowerBlock(provider, BLUE_MOUNTAIN_FLOWER.get());
        flowerBlock(provider, YELLOW_MOUNTAIN_FLOWER.get());
        flowerBlock(provider, PURPLE_MOUNTAIN_FLOWER.get());

        crossBlock(provider, CANIS_ROOT_BLOCK.get());

        mushroomBlock(provider, BLEEDING_CROWN_BLOCK.get());
        mushroomBlock(provider, FLY_AMANITA_BLOCK.get());
        mushroomBlock(provider, WHITE_CAP_BLOCK.get());
        mushroomBlock(provider, BLISTERWORT_BLOCK.get());
        lilyPadBlock(provider, CREEP_CLUSTER_BLOCK.get());

        provider.getVariantBuilder(PEARL_OYSTER_BLOCK.get())
                .forAllStates(state -> {
                    boolean open = state.getValue(PearlOysterBlock.IS_OPEN);
                    boolean empty = state.getValue(PearlOysterBlock.IS_EMPTY);
                    Direction facing = state.getValue(PearlOysterBlock.FACING);

                    return ConfiguredModel.builder()
                            .modelFile(provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster")))
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .nextModel()
                            .modelFile(provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster_open")))
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .nextModel()
                            .modelFile(provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster_empty")))
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .build();
                });

//                .partialState().with(PearlOysterBlock.IS_OPEN, true)
//                .modelForState().modelFile(provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster_open"))).addModel()
//                .partialState().with(PearlOysterBlock.IS_EMPTY, true)
//                .modelForState().modelFile(provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster_empty"))).addModel()
//                .partialState().with(PearlOysterBlock.IS_OPEN, false)
//                .modelForState().modelFile(provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster"))).addModel();

//        forAllStates(state -> {
//            boolean open = state.getValue(PearlOysterBlock.IS_OPEN);
//            boolean empty = state.getValue(PearlOysterBlock.IS_EMPTY);
//            Direction facing = state.getValue(PearlOysterBlock.FACING);
//
//            return ConfiguredModel.builder()
//                    .modelFile(open ?
//                            (empty ?
//                                    provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster_empty")) :
//                                    provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster_open"))
//                            ) :
//                            provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster"))
//                    )
//                    .rotationY(((int) facing.toYRot() + 180) % 360) // 180 is default angle offset
//                    .build();
//        });
        provider.simpleBlockItem(PEARL_OYSTER_BLOCK.get(), provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/pearl_oyster")));

        provider.horizontalBlock(ALCHEMY_TABLE.get(), state -> provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/alchemy_table")));
        provider.simpleBlockItem(ALCHEMY_TABLE.get(), provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/alchemy_table")));

        provider.horizontalBlock(WEAPON_RACK.get(), state -> provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/weapon_rack")));
        provider.simpleBlockItem(WEAPON_RACK.get(), provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/weapon_rack")));

        provider.simpleBlock(BIRDS_NEST.get(), provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/birds_nest")));
        provider.simpleBlockItem(BIRDS_NEST.get(), provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/birds_nest")));

        provider.horizontalBlock(OVEN.get(), state -> provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/oven")));
        provider.simpleBlockItem(OVEN.get(), provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/oven")));

        provider.horizontalBlock(BLACKSMITH_FORGE.get(), state -> provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/blacksmith_forge")));
        provider.simpleBlockItem(BLACKSMITH_FORGE.get(), provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/blacksmith_forge")));
    }

    public static void addBlockItemModels(ItemModelProvider provider) {
        provider.basicItem(CANIS_ROOT.asItem());
        provider.basicItem(BLEEDING_CROWN.asItem());
        provider.basicItem(BLISTERWORT.asItem());
        provider.basicItem(FLY_AMANITA.asItem());
        provider.basicItem(WHITE_CAP.asItem());
        provider.basicItem(CREEP_CLUSTER.asItem());
    }

    public static void normalBlock(BlockStateProvider provider, Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();
        provider.simpleBlock(block, provider.models().cubeAll(path, provider.modLoc("block/" + path)));
        provider.simpleBlockItem(block, provider.models().getExistingFile(provider.modLoc("block/" + path)));
    }

    public static void mushroomBlock(BlockStateProvider provider, Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();

        provider.simpleBlock(block, provider.models().getBuilder(blockKey.toString())
                .parent(provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/triple_mushroom_generic")))
                .texture("0", provider.modLoc("block/"+path))
                .renderType("cutout")
                .texture("particle", provider.modLoc("block/"+path))
        );
    }

    public static void crossBlock(BlockStateProvider provider, Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();

        provider.simpleBlock(block, provider.models().getBuilder(blockKey.toString()).parent(provider.models().getExistingFile(new ResourceLocation("minecraft:block/cross")))
                .texture("cross", provider.modLoc("block/"+path))
                .renderType("cutout"));
    }

    public static void lilyPadBlock(BlockStateProvider provider, Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();

        provider.simpleBlock(block, provider.models().getBuilder(blockKey.toString())
                .parent(provider.models().getExistingFile(new ResourceLocation("minecraft:block/lily_pad")))
                .texture("texture", provider.modLoc("block/"+path))
                .renderType("cutout")
                .texture("particle", provider.modLoc("block/"+path)));
    }

    public static void flowerBlock(BlockStateProvider provider, Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();

        provider.simpleBlock(block, provider.models().getBuilder(blockKey.toString()).parent(provider.models().getExistingFile(new ResourceLocation("minecraft:block/cross"))).texture("cross", provider.modLoc("block/"+path)).renderType("cutout"));
        provider.simpleBlockItem(block, provider.models().getExistingFile(provider.modLoc("block/" + path)));

    }

    public static void crop(BlockStateProvider provider, Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();

        provider.simpleBlock(block, provider.models().crop(path, provider.modLoc("block/" + path)).renderType("cutout"));
    }
}
