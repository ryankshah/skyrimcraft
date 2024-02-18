package com.ryankshah.skyrimcraft.data.item;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class SkyrimcraftItemTagProvider extends ItemTagsProvider {
    public SkyrimcraftItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, blockTagsProvider.contentsGetter(), Skyrimcraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // ores
        copy(TagsInit.BlockTagsInit.CORUNDUM_ORE_TAG, TagsInit.ItemTagsInit.CORUNDUM_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.EBONY_ORE_TAG, TagsInit.ItemTagsInit.EBONY_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.MALACHITE_ORE_TAG, TagsInit.ItemTagsInit.MALACHITE_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.MOONSTONE_ORE_TAG, TagsInit.ItemTagsInit.MOONSTONE_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.ORICHALCUM_ORE_TAG, TagsInit.ItemTagsInit.ORICHALCUM_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.QUICKSILVER_ORE_TAG, TagsInit.ItemTagsInit.QUICKSILVER_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.SILVER_ORE_TAG, TagsInit.ItemTagsInit.SILVER_ORE_ITEM_TAG);

        // deepslate ores
        copy(TagsInit.BlockTagsInit.DEEPSLATE_CORUNDUM_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_CORUNDUM_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.DEEPSLATE_EBONY_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_EBONY_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.DEEPSLATE_MALACHITE_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_MALACHITE_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.DEEPSLATE_MOONSTONE_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_MOONSTONE_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.DEEPSLATE_ORICHALCUM_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_ORICHALCUM_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.DEEPSLATE_QUICKSILVER_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_QUICKSILVER_ORE_ITEM_TAG);
        copy(TagsInit.BlockTagsInit.DEEPSLATE_SILVER_ORE_TAG, TagsInit.ItemTagsInit.DEEPSLATE_SILVER_ORE_ITEM_TAG);

//        // ingots
//        tag(TagsInit.ItemTagsInit.INGOTS_EXAMPLE).add(ItemInit.EXAMPLE_ITEM.get());
//
//        // scraps
//        tag(TagsInit.ItemTagsInit.SCRAPS_EXAMPLE).add(ItemInit.EXAMPLE_ITEM.get());
    }
}