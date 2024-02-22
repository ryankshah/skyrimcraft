package com.ryankshah.skyrimcraft.entity.npc.render;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.npc.Khajiit;
import com.ryankshah.skyrimcraft.entity.npc.model.KhajiitModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class KhajiitRenderer extends HumanoidMobRenderer<Khajiit, KhajiitModel<Khajiit>>
{
    public KhajiitRenderer(EntityRendererProvider.Context pContext) {
        super(
                pContext,
                new KhajiitModel<>(pContext.bakeLayer(KhajiitModel.LAYER_LOCATION)),
                1.0f
        );
//        this.addLayer(
//                new HumanoidArmorLayer<>(
//                        this, new KhajiitModel<>(pContext.bakeLayer(pInnerModelLayer)), new SkeletonModel(pContext.bakeLayer(pOuterModelLayer)), pContext.getModelManager()
//                )
//        );
    }

    @Override
    public ResourceLocation getTextureLocation(Khajiit pEntity) {
        return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/khajiit_male.png");
    }
}