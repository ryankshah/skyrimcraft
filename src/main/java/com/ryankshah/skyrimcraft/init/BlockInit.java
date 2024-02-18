package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.block.AlchemyTableBlock;
import com.ryankshah.skyrimcraft.block.ShoutBlock;
import com.ryankshah.skyrimcraft.block.SkyrimFlower;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockInit
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Skyrimcraft.MODID);

    public static final DeferredBlock<Block> CORUNDUM_ORE = registerBlock("corundum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            )
    );
    public static final DeferredBlock<Block> DEEPSLATE_CORUNDUM_ORE = registerBlock("deepslate_corundum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> EBONY_ORE = registerBlock("ebony_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_EBONY_ORE = registerBlock("deepslate_ebony_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> MALACHITE_ORE = registerBlock("malachite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_MALACHITE_ORE = registerBlock("deepslate_malachite_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> MOONSTONE_ORE = registerBlock("moonstone_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_MOONSTONE_ORE = registerBlock("deepslate_moonstone_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> ORICHALCUM_ORE = registerBlock("orichalcum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_ORICHALCUM_ORE = registerBlock("deepslate_orichalcum_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> QUICKSILVER_ORE = registerBlock("quicksilver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_QUICKSILVER_ORE = registerBlock("deepslate_quicksilver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(4, 7),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
            ));


    public static final DeferredBlock<Block> SHOUT_BLOCK = registerBlock("shout_block",
            ShoutBlock::new);

    public static final DeferredBlock<Block> ALCHEMY_TABLE = registerBlock("alchemy_table",
            AlchemyTableBlock::new);

    public static final DeferredBlock<Block> RED_MOUNTAIN_FLOWER = registerBlock("red_mountain_flower",
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
    public static final DeferredBlock<Block> BLUE_MOUNTAIN_FLOWER = registerBlock("blue_mountain_flower",
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
    public static final DeferredBlock<Block> YELLOW_MOUNTAIN_FLOWER = registerBlock("yellow_mountain_flower",
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
    public static final DeferredBlock<Block> PURPLE_MOUNTAIN_FLOWER = registerBlock("purple_mountain_flower",
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

    public static DeferredBlock<Block> registerBlock(
            String name, Supplier<Block> block) {
        DeferredBlock<Block> blockReg = BLOCKS.register(name, block);
        ItemInit.ITEMS.register(name, () -> new BlockItem(blockReg.get(), new Item.Properties()));
        return blockReg;
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
//        (ModBlocks.RED_MOUNTAIN_FLOWER.get(), models().getBuilder("red_mountain_flower").parent(models().getExistingFile(new ResourceLocation("minecraft:block/cross"))).texture("cross", new ResourceLocation("skyrimcraft:block/red_mountain_flower")));
//        simpleBlock(ModBlocks.BLUE_MOUNTAIN_FLOWER.get(), models().getBuilder("blue_mountain_flower").parent(models().getExistingFile(new ResourceLocation("minecraft:block/cross"))).texture("cross", new ResourceLocation("skyrimcraft:block/blue_mountain_flower")));
//        simpleBlock(ModBlocks.YELLOW_MOUNTAIN_FLOWER.get(), models().getBuilder("yellow_mountain_flower").parent(models().getExistingFile(new ResourceLocation("minecraft:block/cross"))).texture("cross", new ResourceLocation("skyrimcraft:block/yellow_mountain_flower")));
//        simpleBlock(ModBlocks.PURPLE_MOUNTAIN_FLOWER.get(), models().getBuilder("purple_mountain_flower").parent(models().getExistingFile(new ResourceLocation("minecraft:block/cross"))).texture("cross", new ResourceLocation("skyrimcraft:block/purple_mountain_flower")));


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
}
