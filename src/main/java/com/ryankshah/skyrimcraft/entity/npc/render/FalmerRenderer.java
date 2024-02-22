package com.ryankshah.skyrimcraft.entity.npc.render;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.npc.Falmer;
import com.ryankshah.skyrimcraft.entity.npc.Khajiit;
import com.ryankshah.skyrimcraft.entity.npc.model.FalmerModel;
import com.ryankshah.skyrimcraft.entity.npc.model.KhajiitModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FalmerRenderer extends HumanoidMobRenderer<Falmer, FalmerModel<Falmer>>
{
    public FalmerRenderer(EntityRendererProvider.Context pContext) {
        super(
                pContext,
                new FalmerModel<>(pContext.bakeLayer(FalmerModel.LAYER_LOCATION)),
                0.5f
        );
//        this.addLayer(
//                new HumanoidArmorLayer<>(
//                        this, new KhajiitModel<>(pContext.bakeLayer(pInnerModelLayer)), new SkeletonModel(pContext.bakeLayer(pOuterModelLayer)), pContext.getModelManager()
//                )
//        );
    }

    @Override
    public ResourceLocation getTextureLocation(Falmer pEntity) {
        return pEntity.getRandom().nextInt(1,2) == 1 ? new ResourceLocation(Skyrimcraft.MODID, "textures/entity/falmer_1.png") : new ResourceLocation(Skyrimcraft.MODID, "textures/entity/falmer_2.png");
    }
}