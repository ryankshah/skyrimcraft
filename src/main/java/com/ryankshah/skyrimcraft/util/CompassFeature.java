package com.ryankshah.skyrimcraft.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.attachment.CompassFeatureHandler;
import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.init.TagsInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.AbstractMap;
import java.util.UUID;

public class CompassFeature
{
    private TagKey<Structure> feature;
    private BlockPos blockPos;
    private String id;

    public static Codec<CompassFeature> CODEC = RecordCodecBuilder.create(cf -> cf.group(
            Codec.STRING.fieldOf("id").forGetter(CompassFeature::getId),
            TagKey.codec(Registries.STRUCTURE).fieldOf("feature").forGetter(CompassFeature::getFeature),
            BlockPos.CODEC.fieldOf("blockPos").forGetter(CompassFeature::getBlockPos)
    ).apply(cf, CompassFeature::new));

    public static final int ICON_WIDTH = 12, ICON_HEIGHT = 16;

    public CompassFeature(String id, TagKey<Structure> feature, BlockPos blockPos) {
        this.feature = feature;
        this.blockPos = blockPos;
        this.id = id;
    }

    public TagKey<Structure> getFeature() {
        return feature;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public String getId() {
        return id;
    }

    public AbstractMap.SimpleEntry<Integer, Integer> getIconUV() {
        if(feature.equals(StructureTags.VILLAGE)) {
            return new AbstractMap.SimpleEntry<>(0, 124);
        } else if(feature.equals(TagsInit.StructureTagsInit.NETHER_FORTRESS)) {
            return new AbstractMap.SimpleEntry<>(16, 124);
//        } else if(feature.equals(ModStructures.SHOUT_WALL.getId())) {
//            return new AbstractMap.SimpleEntry<>(29, 124);
        } else if(feature.equals(StructureTags.MINESHAFT)) {
            return new AbstractMap.SimpleEntry<>(44, 125);
        } else if(feature.equals(StructureTags.SHIPWRECK)) {
            return new AbstractMap.SimpleEntry<>(57, 124);
        }
        return null;
    }

    public CompoundTag serialise() {
        CompoundTag nbt = new CompoundTag();

        nbt.putString("id", id);
        nbt.putString("resourcelocation", feature.location().toString());
        nbt.putInt("xPos", blockPos.getX());
        nbt.putInt("yPos", blockPos.getY());
        nbt.putInt("zPos", blockPos.getZ());

        return nbt;
    }

    public static CompassFeature deserialise(CompoundTag nbt) {
        String id = nbt.getString("id");
        ResourceLocation feature = new ResourceLocation(nbt.getString("resourcelocation"));
        int x = nbt.getInt("xPos");
        int y = nbt.getInt("yPos");
        int z = nbt.getInt("zPos");
        BlockPos blockPos = new BlockPos(x, y, z);
        return new CompassFeature(id, TagKey.create(Registries.STRUCTURE, feature), blockPos);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CompassFeature))
            return false;

        CompassFeature featureToCompare = (CompassFeature)obj;
        return this.feature.equals(featureToCompare.feature) && this.blockPos.getX() == featureToCompare.getBlockPos().getX() && this.getBlockPos().getZ() == featureToCompare.getBlockPos().getZ();
    }
}