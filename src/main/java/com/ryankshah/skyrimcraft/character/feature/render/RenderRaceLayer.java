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
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRaceLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>
{
    private final HighElfEarModel highElfEarModel;
    private final DunmerEarModel dunmerEarModel;
    private final KhajiitHeadModel khajiitHeadModel;
    private final KhajiitFullModel khajiitFullModel;

    public RenderRaceLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> entityRenderer) {
        super(entityRenderer);

        highElfEarModel = new HighElfEarModel(Minecraft.getInstance().getEntityModels().bakeLayer(HighElfEarModel.LAYER_LOCATION));
        dunmerEarModel = new DunmerEarModel(Minecraft.getInstance().getEntityModels().bakeLayer(DunmerEarModel.LAYER_LOCATION));
        khajiitHeadModel = new KhajiitHeadModel(Minecraft.getInstance().getEntityModels().bakeLayer(KhajiitHeadModel.LAYER_LOCATION));
        khajiitFullModel = new KhajiitFullModel(Minecraft.getInstance().getEntityModels().bakeLayer(KhajiitFullModel.LAYER_LOCATION));

        entityRenderer.getModel().copyPropertiesTo(highElfEarModel);
        entityRenderer.getModel().copyPropertiesTo(dunmerEarModel);
        entityRenderer.getModel().copyPropertiesTo(khajiitHeadModel);
        entityRenderer.getModel().copyPropertiesTo(khajiitFullModel);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, AbstractClientPlayer pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        Race race = pLivingEntity.getData(PlayerAttachments.SKILLS).getRace();
        if (race.getId() == Race.ALTMER.getId() || race.getId() == Race.BOSMER.getId())
            renderAltmer(pPoseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        else if (race.getId() == Race.DUNMER.getId())
            renderDunmer(pPoseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        else if (race.getId() == Race.KHAJIIT.getId())
            renderKhajiit(pPoseStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick, pAgeInTicks, pNetHeadYaw, pHeadPitch);
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
        VertexConsumer ivertexbuilder = renderBuffer.getBuffer(RenderType.entityTranslucent(new ResourceLocation(Skyrimcraft.MODID, "textures/entity/khajiit_male.png")));
        int overlayCoords = PlayerRenderer.getOverlayCoords(playerEntity, 0.0F);
        poseStack.pushPose();
        this.getParentModel().copyPropertiesTo(khajiitFullModel);
        this.getParentModel().setAllVisible(false);
        khajiitFullModel.renderToBuffer(poseStack, ivertexbuilder, packedLight, overlayCoords, 1.0F, 1.0F, 1.0F, 1.0F);
//        khajiitHeadModel.renderToBuffer(matrixStack, ivertexbuilder, packedLight, overlayCoords, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
    }

}