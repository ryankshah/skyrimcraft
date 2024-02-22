package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.block.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockInit
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Skyrimcraft.MODID);

    public static final DeferredBlock<Block> CORUNDUM_ORE = registerBlockAndItem("corundum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            )
    );
    public static final DeferredBlock<Block> DEEPSLATE_CORUNDUM_ORE = registerBlockAndItem("deepslate_corundum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> EBONY_ORE = registerBlockAndItem("ebony_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_EBONY_ORE = registerBlockAndItem("deepslate_ebony_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> MALACHITE_ORE = registerBlockAndItem("malachite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_MALACHITE_ORE = registerBlockAndItem("deepslate_malachite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> MOONSTONE_ORE = registerBlockAndItem("moonstone_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_MOONSTONE_ORE = registerBlockAndItem("deepslate_moonstone_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> ORICHALCUM_ORE = registerBlockAndItem("orichalcum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_ORICHALCUM_ORE = registerBlockAndItem("deepslate_orichalcum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> QUICKSILVER_ORE = registerBlockAndItem("quicksilver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_QUICKSILVER_ORE = registerBlockAndItem("deepslate_quicksilver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> SILVER_ORE = registerBlockAndItem("silver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = registerBlockAndItem("deepslate_silver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));


    public static final DeferredBlock<Block> SHOUT_BLOCK = registerBlockAndItem("shout_block",
            ShoutBlock::new);

    public static final DeferredBlock<Block> ALCHEMY_TABLE = registerBlockAndItem("alchemy_table",
            AlchemyTableBlock::new);

    public static final DeferredBlock<Block> RED_MOUNTAIN_FLOWER = registerBlockAndItem("red_mountain_flower",
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
    public static final DeferredBlock<Block> BLUE_MOUNTAIN_FLOWER = registerBlockAndItem("blue_mountain_flower",
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
    public static final DeferredBlock<Block> YELLOW_MOUNTAIN_FLOWER = registerBlockAndItem("yellow_mountain_flower",
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
    public static final DeferredBlock<Block> PURPLE_MOUNTAIN_FLOWER = registerBlockAndItem("purple_mountain_flower",
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
//    public static final DeferredBlock<Block> CANIS_ROOT = registerBlock("canis_root",
//            () -> new CanisRoot(
//                    BlockBehaviour.Properties.of()
//                            .mapColor(MapColor.PLANT)
//                            .noCollission()
//                            .instabreak()
//                            .sound(SoundType.SWEET_BERRY_BUSH)
//                            .offsetType(BlockBehaviour.OffsetType.XZ)
//                            .pushReaction(PushReaction.DESTROY)
//            ));

//    public static final DeferredBlock<Block> TOMATO_CROP = registerBlock("tomatoes", () -> new TomatoCrop(
//            BlockBehaviour.Properties.of()
//                    .mapColor(MapColor.PLANT)
//                    .noCollission()
//                    .randomTicks()
//                    .instabreak()
//                    .sound(SoundType.CROP)
//                    .pushReaction(PushReaction.DESTROY)
//    ));
//    public static final DeferredBlock<Block> GARLIC_CROP = registerBlock("garlic", () -> new GarlicCrop(
//            BlockBehaviour.Properties.of()
//            .mapColor(MapColor.PLANT)
//            .noCollission()
//            .randomTicks()
//            .instabreak()
//            .sound(SoundType.CROP)
//            .pushReaction(PushReaction.DESTROY)
//
//    ));
//    public static final DeferredItem<Item> TOMATO_SEEDS = BLOCK_ITEMS.register("tomato_seeds", () -> new BlockItem(TOMATO_CROP.get(), new Item.Properties()));
//    public static final DeferredItem<Item> GARLIC = BLOCK_ITEMS.register("garlic", () -> new Item( new Item.Properties()));

    public static DeferredBlock<Block> registerBlockAndItem(String name, Supplier<Block> block) {
        DeferredBlock<Block> blockReg = BLOCKS.register(name, block);
        ItemInit.ITEMS.register(name, () -> new BlockItem(blockReg.get(), new Item.Properties()));
        return blockReg;
    }

    public static DeferredBlock<Block> registerBlock(String name, Supplier<Block> block) {
        return BLOCKS.register(name, block);
    }

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

        provider.addBlock(ALCHEMY_TABLE, "Alchemy Table");

        provider.addBlock(RED_MOUNTAIN_FLOWER, "Red Mountain Flower");
        provider.addBlock(BLUE_MOUNTAIN_FLOWER, "Blue Mountain Flower");
        provider.addBlock(YELLOW_MOUNTAIN_FLOWER, "Yellow Mountain Flower");
        provider.addBlock(PURPLE_MOUNTAIN_FLOWER, "Purple Mountain Flower");
//        provider.addBlock(CANIS_ROOT, "Canis Root");

//        provider.addBlock(TOMATO_CROP, "Tomatoes");
//        provider.addBlock(GARLIC_CROP, "Garlic");
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
//        flowerBlock(provider, CANIS_ROOT.get());

        provider.horizontalBlock(ALCHEMY_TABLE.get(), state -> provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/alchemy_table")));
        provider.simpleBlockItem(ALCHEMY_TABLE.get(), provider.models().getExistingFile(new ResourceLocation(Skyrimcraft.MODID, "block/alchemy_table")));
    }

    public static void normalBlock(BlockStateProvider provider, Block block) {
        ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockKey.getPath();
        provider.simpleBlock(block, provider.models().cubeAll(path, provider.modLoc("block/" + path)));
        provider.simpleBlockItem(block, provider.models().getExistingFile(provider.modLoc("block/" + path)));
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
