package com.ryankshah.skyrimcraft.character.feature.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.character.feature.model.DunmerEarModel;
import com.ryankshah.skyrimcraft.character.feature.model.HighElfEarModel;
import com.ryankshah.skyrimcraft.character.feature.model.KhajiitFullModel;
import com.ryankshah.skyrimcraft.character.feature.model.KhajiitHeadModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.IronGolemModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.ClientHooks;

public class RenderRaceLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>
{
    private final HighElfEarModel highElfEarModel;
    private final DunmerEarModel dunmerEarModel;
    private final KhajiitHeadModel khajiitHeadModel;
    private final KhajiitFullModel khajiitFullModel;

    public RenderRaceLayer(PlayerRenderer entityRenderer) {
        super(entityRenderer);

        highElfEarModel = new HighElfEarModel(Minecraft.getInstance().getEntityModels().bakeLayer(HighElfEarModel.LAYER_LOCATION));
        dunmerEarModel = new DunmerEarModel(Minecraft.getInstance().getEntityModels().bakeLayer(DunmerEarModel.LAYER_LOCATION));
        khajiitHeadModel = new KhajiitHeadModel(Minecraft.getInstance().getEntityModels().bakeLayer(KhajiitHeadModel.LAYER_LOCATION));
        khajiitFullModel = new KhajiitFullModel(Minecraft.getInstance().getEntityModels().bakeLayer(KhajiitFullModel.LAYER_LOCATION));

//        getParentModel().setAllVisible(false);

//        entityRenderer.getModel().copyPropertiesTo(highElfEarModel);
//        entityRenderer.getModel().copyPropertiesTo(dunmerEarModel);
//        entityRenderer.getModel().copyPropertiesTo(khajiitHeadModel);
//        entityRenderer.getModel().copyPropertiesTo(khajiitFullModel);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        Race race = pLivingEntity.getData(PlayerAttachments.SKILLS).getRace();
        if (race.getId() == Race.ALTMER.getId() || race.getId() == Race.BOSMER.getId())
            renderAltmer(pPoseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        else if (race.getId() == Race.DUNMER.getId())
            renderDunmer(pPoseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        else if (race.getId() == Race.KHAJIIT.getId()) {
            renderKhajiit(pPoseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        }
    }

    private void renderAltmer(PoseStack matrixStack, MultiBufferSource renderBuffer, int packedLight, AbstractClientPlayer playerEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer ivertexbuilder = renderBuffer.getBuffer(RenderType.entitySolid(playerEntity.getSkin().texture()));
        int overlayCoords = PlayerRenderer.getOverlayCoords(playerEntity, 0.0F);

        matrixStack.pushPose();
        this.getParentModel().getHead().translateAndRotate(matrixStack);
        //matrixStack.mulPose(YP.rotationDegrees(180F));
        highElfEarModel.renderToBuffer(matrixStack, ivertexbuilder, packedLight, overlayCoords, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.popPose();
    }

    private void renderDunmer(PoseStack matrixStack, MultiBufferSource renderBuffer, int packedLight, AbstractClientPlayer playerEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer ivertexbuilder = renderBuffer.getBuffer(RenderType.entitySolid(playerEntity.getSkin().texture()));
        int overlayCoords = PlayerRenderer.getOverlayCoords(playerEntity, 0.0F);

        matrixStack.pushPose();
        this.getParentModel().getHead().translateAndRotate(matrixStack);
        //matrixStack.mulPose(YP.rotationDegrees(180F));
        dunmerEarModel.renderToBuffer(matrixStack, ivertexbuilder, packedLight, overlayCoords, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.popPose();
    }

    private void renderKhajiit(PoseStack poseStack, MultiBufferSource renderBuffer, int packedLight, AbstractClientPlayer playerEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        coloredCutoutModelCopyLayerRender(getParentModel(), khajiitFullModel, new ResourceLocation(Skyrimcraft.MODID, "textures/entity/khajiit_male.png"), poseStack, renderBuffer, packedLight, playerEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1, 1, 1);
        getParentModel().setAllVisible(false);
//        VertexConsumer ivertexbuilder = renderBuffer.getBuffer(RenderType.entityTranslucent(new ResourceLocation(Skyrimcraft.MODID, "textures/entity/khajiit_male.png")));
//        int overlayCoords = PlayerRenderer.getOverlayCoords(playerEntity, 0.0F);
//        poseStack.pushPose();
////        this.getParentModel().copyPropertiesTo(khajiitFullModel);
////        this.getParentModel().setAllVisible(false);
////        this.getParentModel().rightArm.translateAndRotate(poseStack);
////        this.getParentModel().rightLeg.translateAndRotate(poseStack);
////        this.getParentModel().leftArm.translateAndRotate(poseStack);
////        this.getParentModel().leftLeg.translateAndRotate(poseStack);
//        this.getParentModel().copyPropertiesTo(this.khajiitFullModel);
//        this.khajiitFullModel.setupAnim(playerEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
//        this.khajiitFullModel.renderToBuffer(poseStack, ivertexbuilder, packedLight, overlayCoords, 1.0F, 1.0F, 1.0F, 1.0F);
////        this.getParentModel().setAllVisible(false);
////        khajiitHeadModel.renderToBuffer(matrixStack, ivertexbuilder, packedLight, overlayCoords, 1.0F, 1.0F, 1.0F, 1.0F);
//        poseStack.popPose();
    }
}