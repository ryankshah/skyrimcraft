package com.ryankshah.skyrimcraft.data.block;

import com.ryankshah.skyrimcraft.init.BlockInit;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class SkyrimcraftBlockStateProvider extends BlockStateProvider
{
    public SkyrimcraftBlockStateProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        BlockInit.addBlockStateModels(this);
    }
}
