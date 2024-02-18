package com.ryankshah.skyrimcraft.data.curios;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.concurrent.CompletableFuture;

public class SkyrimCuriosDataProvider extends CuriosDataProvider
{
    public SkyrimCuriosDataProvider(String modId, PackOutput output,
                              ExistingFileHelper fileHelper,
                              CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        this.createEntities("entities").addEntities(EntityType.PLAYER).addSlots("circlet", "necklace", "ring");
        this.createSlot("circlet")
                .size(1)
                .icon(new ResourceLocation(Skyrimcraft.MODID, "slot/circlet"))
                .dropRule(ICurio.DropRule.ALWAYS_KEEP);
        this.createSlot("necklace")
                .size(1)
                .icon(new ResourceLocation(Skyrimcraft.MODID, "slot/necklace"))
                .dropRule(ICurio.DropRule.ALWAYS_KEEP);
        this.createSlot("ring")
                .size(1)
                .icon(new ResourceLocation(Skyrimcraft.MODID, "slot/ring"))
                .dropRule(ICurio.DropRule.ALWAYS_KEEP);
    }
}
