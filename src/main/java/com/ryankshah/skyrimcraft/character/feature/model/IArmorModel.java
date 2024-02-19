package com.ryankshah.skyrimcraft.character.feature.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public interface IArmorModel
{
    ModelPart getBody();

    ModelPart realBody();

    ModelPart rightArm();

    ModelPart leftArm();

    ModelPart head();

    ModelPart rightLeg();

    ModelPart leftLeg();

    ModelLayerLocation layerLocation();

    LayerDefinition createBodyLayer();
}