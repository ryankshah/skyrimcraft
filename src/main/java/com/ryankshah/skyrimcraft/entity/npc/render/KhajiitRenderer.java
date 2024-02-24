package com.ryankshah.skyrimcraft.entity.npc.render;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.npc.Khajiit;
import com.ryankshah.skyrimcraft.entity.npc.model.KhajiitModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.VexRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class KhajiitRenderer extends HumanoidMobRenderer<Khajiit, KhajiitModel<Khajiit>>
{
    public KhajiitRenderer(EntityRendererProvider.Context pContext) {
        this(pContext, KhajiitModel.LAYER_LOCATION, KhajiitModel.INNER_ARMOR_LAYER_LOCATION, KhajiitModel.OUTER_ARMOR_LAYER_LOCATION);
    }

    public KhajiitRenderer(EntityRendererProvider.Context pContext, ModelLayerLocation pKhajiitLayer, ModelLayerLocation pInnerModelLayer, ModelLayerLocation pOuterModelLayer) {
        super(pContext, new KhajiitModel<>(pContext.bakeLayer(pKhajiitLayer)), 0.5F);
        this.addLayer(
                new HumanoidArmorLayer<>(
                        this, new KhajiitModel<>(pContext.bakeLayer(pInnerModelLayer)), new KhajiitModel<>(pContext.bakeLayer(pOuterModelLayer)), pContext.getModelManager()
                )
        );
    }

    @Override
    public ResourceLocation getTextureLocation(Khajiit pEntity) {
        return pEntity.getSex() ? new ResourceLocation(Skyrimcraft.MODID, "textures/entity/khajiit_male.png") : new ResourceLocation(Skyrimcraft.MODID, "textures/entity/khajiit_female.png");
    }
}