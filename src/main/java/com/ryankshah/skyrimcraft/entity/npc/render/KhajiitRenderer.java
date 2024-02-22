package com.ryankshah.skyrimcraft.entity.npc.render;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.npc.Khajiit;
import com.ryankshah.skyrimcraft.entity.npc.model.KhajiitModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.VexRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class KhajiitRenderer extends HumanoidMobRenderer<Khajiit, KhajiitModel<Khajiit>>
{
    public KhajiitRenderer(EntityRendererProvider.Context pContext) {
        super(
                pContext,
                new KhajiitModel<>(pContext.bakeLayer(KhajiitModel.LAYER_LOCATION)),
                0.5f
        );
//        this.addLayer(
//                new HumanoidArmorLayer<>(
//                        this, new KhajiitModel<>(pContext.bakeLayer(pInnerModelLayer)), new SkeletonModel(pContext.bakeLayer(pOuterModelLayer)), pContext.getModelManager()
//                )
//        );
    }

//    @Override
//    protected RenderType getRenderType(Khajiit pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
//        ResourceLocation resourcelocation = this.getTextureLocation(pLivingEntity);
//        return RenderType.entityCutoutNoCull(resourcelocation);
//////        if (pTranslucent) {
//////            return RenderType.itemEntityTranslucentCull(resourcelocation);
//////        } else if (pBodyVisible) {
//////            return this.model.renderType(resourcelocation);
//////        } else {
//////            return pGlowing ? RenderType.outline(resourcelocation) : null;
//////        }
//    }

    @Override
    public ResourceLocation getTextureLocation(Khajiit pEntity) {
        return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/khajiit_male.png");
    }
}