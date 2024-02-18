package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

public class TagsInit {

    public static class BlockTagsInit {
        // Ores
        public static TagKey<Block> CORUNDUM_ORE_TAG = BlockTags.create(createResourceLocation("ores/corundum_ore"));
        public static TagKey<Block> EBONY_ORE_TAG = BlockTags.create(createResourceLocation("ores/ebony_ore"));
        public static TagKey<Block> MALACHITE_ORE_TAG = BlockTags.create(createResourceLocation("ores/malachite_ore"));
        public static TagKey<Block> MOONSTONE_ORE_TAG = BlockTags.create(createResourceLocation("ores/moonstone_ore"));
        public static TagKey<Block> ORICHALCUM_ORE_TAG = BlockTags.create(createResourceLocation("ores/orichalcum_ore"));
        public static TagKey<Block> QUICKSILVER_ORE_TAG = BlockTags.create(createResourceLocation("ores/quicksilver_ore"));
        public static TagKey<Block> SILVER_ORE_TAG = BlockTags.create(createResourceLocation("ores/silver_ore"));
        //Deepslate Ores
        public static TagKey<Block> DEEPSLATE_CORUNDUM_ORE_TAG = BlockTags.create(createResourceLocation("ores/deepslate_corundum_ore"));
        public static TagKey<Block> DEEPSLATE_EBONY_ORE_TAG = BlockTags.create(createResourceLocation("ores/deepslate_ebony_ore"));
        public static TagKey<Block> DEEPSLATE_MALACHITE_ORE_TAG = BlockTags.create(createResourceLocation("ores/deepslate_malachite_ore"));
        public static TagKey<Block> DEEPSLATE_MOONSTONE_ORE_TAG = BlockTags.create(createResourceLocation("ores/deepslate_moonstone_ore"));
        public static TagKey<Block> DEEPSLATE_ORICHALCUM_ORE_TAG = BlockTags.create(createResourceLocation("ores/deepslate_orichalcum_ore"));
        public static TagKey<Block> DEEPSLATE_QUICKSILVER_ORE_TAG = BlockTags.create(createResourceLocation("ores/deepslate_quicksilver_ore"));
        public static TagKey<Block> DEEPSLATE_SILVER_ORE_TAG = BlockTags.create(createResourceLocation("ores/deepslate_silver_ore"));


        public static TagKey<Block> NEEDS_ANCIENT_NORD_TOOL = BlockTags.create(createResourceLocation("needs_ancient_nord_tool"));
        public static TagKey<Block> NEEDS_DRAGONBONE_TOOL = BlockTags.create(createResourceLocation("needs_dragonbone_tool"));
        public static TagKey<Block> NEEDS_STEEL_TOOL = BlockTags.create(createResourceLocation("needs_steel_tool"));
        public static TagKey<Block> NEEDS_FALMER_TOOL = BlockTags.create(createResourceLocation("needs_falmer_tool"));
        public static TagKey<Block> NEEDS_GLASS_TOOL = BlockTags.create(createResourceLocation("needs_glass_tool"));
        public static TagKey<Block> NEEDS_ELVEN_TOOL = BlockTags.create(createResourceLocation("needs_elven_tool"));
        public static TagKey<Block> NEEDS_ORCISH_TOOL = BlockTags.create(createResourceLocation("needs_orcish_tool"));
        public static TagKey<Block> NEEDS_DWARVEN_TOOL = BlockTags.create(createResourceLocation("needs_dwarven_tool"));
        public static TagKey<Block> NEEDS_DAEDRIC_TOOL = BlockTags.create(createResourceLocation("needs_daedric_tool"));
        public static TagKey<Block> NEEDS_EBONY_TOOL = BlockTags.create(createResourceLocation("needs_ebony_tool"));

//        // For normal blocks - storage
//        public static TagKey<Block> STORAGE_BLOCKS_EXAMPLE = BlockTags.create(createResourceLocation("storage_blocks/example_block"));

        private static ResourceLocation createResourceLocation(String name) {
            return new ResourceLocation(Skyrimcraft.MODID, name);
        }
    }

    public static class ItemTagsInit {
        // Ores
        public static TagKey<Item> CORUNDUM_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/corundum_ore"));
        public static TagKey<Item> EBONY_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/ebony_ore"));
        public static TagKey<Item> MALACHITE_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/malachite_ore"));
        public static TagKey<Item> MOONSTONE_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/moonstone_ore"));
        public static TagKey<Item> ORICHALCUM_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/orichalcum_ore"));
        public static TagKey<Item> QUICKSILVER_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/quicksilver_ore"));
        public static TagKey<Item> SILVER_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/silver_ore"));
        //Deepslate Ores
        public static TagKey<Item> DEEPSLATE_CORUNDUM_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/deepslate_corundum_ore"));
        public static TagKey<Item> DEEPSLATE_EBONY_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/deepslate_ebony_ore"));
        public static TagKey<Item> DEEPSLATE_MALACHITE_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/deepslate_malachite_ore"));
        public static TagKey<Item> DEEPSLATE_MOONSTONE_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/deepslate_moonstone_ore"));
        public static TagKey<Item> DEEPSLATE_ORICHALCUM_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/deepslate_orichalcum_ore"));
        public static TagKey<Item> DEEPSLATE_QUICKSILVER_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/deepslate_quicksilver_ore"));
        public static TagKey<Item> DEEPSLATE_SILVER_ORE_ITEM_TAG = ItemTags.create(createResourceLocation("ores/deepslate_silver_ore"));

        // Items

//        // ore ingot
//        public static TagKey<Item> INGOTS_EXAMPLE = ItemTags.create(createResourceLocation("ingots/example"));
        public static TagKey<Item> SKYRIM_INGREDIENTS = ItemTags.create(createResourceLocation("ingredients"));
//
//        // Raw ore scrap
//        public static TagKey<Item> SCRAPS_EXAMPLE = ItemTags.create(createResourceLocation("scraps/example"));


        private static ResourceLocation createResourceLocation(String name) {
            return new ResourceLocation(Skyrimcraft.MODID, name);
        }
    }

    public static class StructureTagsInit {
        public static TagKey<Structure> NETHER_FORTRESS = TagKey.create(Registries.STRUCTURE, new ResourceLocation(Skyrimcraft.MODID, "fortress"));
        public static TagKey<Structure> SHOUT_WALL = TagKey.create(Registries.STRUCTURE, new ResourceLocation(Skyrimcraft.MODID, "shout_wall"));
    }

    public static class BiomeTagsInit {
        public static TagKey<Biome> WHITE_SABRE_BIOMES = TagKey.create(Registries.BIOME, new ResourceLocation(Skyrimcraft.MODID, "all_snow"));
    }
}