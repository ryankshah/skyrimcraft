package com.ryankshah.skyrimcraft.character.feature.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.RandomSource;

public interface IPlayerModel {
    ModelPart getRandomModelPart(RandomSource var1);
}
