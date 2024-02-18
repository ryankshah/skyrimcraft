package com.ryankshah.skyrimcraft.character.magic.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.entity.FireballEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class FireballRenderer extends EntityRenderer<FireballEntity>
{
    private static final ResourceLocation SPELL_FIREBALL_TEXTURE = new ResourceLocation(Skyrimcraft.MODID, "textures/effect/fireball2.png");

    public FireballRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    public void render(FireballEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(1.0F, 1.0F, 1.0F);

        VertexConsumer ivertexbuilder = pBuffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(pEntity)));
        PoseStack.Pose matrixstack$entry = pPoseStack.last();
        Matrix4f matrix4f = matrixstack$entry.pose();

        long t = System.currentTimeMillis() % 6;

        pPoseStack.mulPose(entityRenderDispatcher.cameraOrientation());

        ivertexbuilder.vertex(matrix4f, -1, -1, 0).color(255, 255, 255, 255).uv(0, 0 +  t * (1.0f / 4.0f)).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(0, 1, 0).endVertex();
        ivertexbuilder.vertex(matrix4f, -1, 1, 0).color(255, 255, 255, 255).uv(0, 0 +  t * (1.0f / 4.0f) + (1.0f / 4.0f)).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(0, 1, 0).endVertex();
        ivertexbuilder.vertex(matrix4f, 1, 1, 0).color(255, 255, 255, 255).uv(1, 0 +  t * (1.0f / 4.0f) + (1.0f / 4.0f)).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(0, 1, 0).endVertex();
        ivertexbuilder.vertex(matrix4f, 1, -1, 0).color(255, 255, 255, 255).uv(1, 0 +  t * (1.0f / 4.0f)).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(0, 1, 0).endVertex();

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

        @Override
    public ResourceLocation getTextureLocation(FireballEntity pEntity) {
        return SPELL_FIREBALL_TEXTURE;
    }
}
