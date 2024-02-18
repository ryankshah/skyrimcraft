package com.ryankshah.skyrimcraft.data.block;

import com.ryankshah.skyrimcraft.init.BlockInit;
import com.ryankshah.skyrimcraft.init.TagsInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SkyrimcraftBlockTagsProvider extends BlockTagsProvider
{
    public SkyrimcraftBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //ores
        tag(TagsInit.BlockTagsInit.CORUNDUM_ORE_TAG).add(BlockInit.CORUNDUM_ORE.get());
        tag(TagsInit.BlockTagsInit.EBONY_ORE_TAG).add(BlockInit.EBONY_ORE.get());
        tag(TagsInit.BlockTagsInit.MALACHITE_ORE_TAG).add(BlockInit.MALACHITE_ORE.get());
        tag(TagsInit.BlockTagsInit.MOONSTONE_ORE_TAG).add(BlockInit.MOONSTONE_ORE.get());
        tag(TagsInit.BlockTagsInit.ORICHALCUM_ORE_TAG).add(BlockInit.ORICHALCUM_ORE.get());
        tag(TagsInit.BlockTagsInit.QUICKSILVER_ORE_TAG).add(BlockInit.QUICKSILVER_ORE.get());
        tag(TagsInit.BlockTagsInit.SILVER_ORE_TAG).add(BlockInit.SILVER_ORE.get());

        // deepslate ores
        tag(TagsInit.BlockTagsInit.DEEPSLATE_CORUNDUM_ORE_TAG).add(BlockInit.DEEPSLATE_CORUNDUM_ORE.get());
        tag(TagsInit.BlockTagsInit.DEEPSLATE_EBONY_ORE_TAG).add(BlockInit.DEEPSLATE_EBONY_ORE.get());
        tag(TagsInit.BlockTagsInit.DEEPSLATE_MALACHITE_ORE_TAG).add(BlockInit.DEEPSLATE_MALACHITE_ORE.get());
        tag(TagsInit.BlockTagsInit.DEEPSLATE_MOONSTONE_ORE_TAG).add(BlockInit.DEEPSLATE_MOONSTONE_ORE.get());
        tag(TagsInit.BlockTagsInit.DEEPSLATE_ORICHALCUM_ORE_TAG).add(BlockInit.DEEPSLATE_ORICHALCUM_ORE.get());
        tag(TagsInit.BlockTagsInit.DEEPSLATE_QUICKSILVER_ORE_TAG).add(BlockInit.DEEPSLATE_QUICKSILVER_ORE.get());
        tag(TagsInit.BlockTagsInit.DEEPSLATE_SILVER_ORE_TAG).add(BlockInit.DEEPSLATE_SILVER_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockInit.CORUNDUM_ORE.get())
                .add(BlockInit.EBONY_ORE.get())
                .add(BlockInit.MALACHITE_ORE.get())
                .add(BlockInit.MOONSTONE_ORE.get())
                .add(BlockInit.ORICHALCUM_ORE.get())
                .add(BlockInit.QUICKSILVER_ORE.get())
                .add(BlockInit.SILVER_ORE.get())
                .add(BlockInit.DEEPSLATE_CORUNDUM_ORE.get())
                .add(BlockInit.DEEPSLATE_EBONY_ORE.get())
                .add(BlockInit.DEEPSLATE_MALACHITE_ORE.get())
                .add(BlockInit.DEEPSLATE_MOONSTONE_ORE.get())
                .add(BlockInit.DEEPSLATE_ORICHALCUM_ORE.get())
                .add(BlockInit.DEEPSLATE_QUICKSILVER_ORE.get())
                .add(BlockInit.DEEPSLATE_SILVER_ORE.get())
                .add(BlockInit.SHOUT_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockInit.CORUNDUM_ORE.get())
                .add(BlockInit.MALACHITE_ORE.get())
                .add(BlockInit.MOONSTONE_ORE.get())
                .add(BlockInit.ORICHALCUM_ORE.get())
                .add(BlockInit.QUICKSILVER_ORE.get())
                .add(BlockInit.SILVER_ORE.get())
                .add(BlockInit.DEEPSLATE_CORUNDUM_ORE.get())
                .add(BlockInit.DEEPSLATE_MALACHITE_ORE.get())
                .add(BlockInit.DEEPSLATE_MOONSTONE_ORE.get())
                .add(BlockInit.DEEPSLATE_ORICHALCUM_ORE.get())
                .add(BlockInit.DEEPSLATE_QUICKSILVER_ORE.get())
                .add(BlockInit.DEEPSLATE_SILVER_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockInit.EBONY_ORE.get())
                .add(BlockInit.DEEPSLATE_EBONY_ORE.get());
    }
}