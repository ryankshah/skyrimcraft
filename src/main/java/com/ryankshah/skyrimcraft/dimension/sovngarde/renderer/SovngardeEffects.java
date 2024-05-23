package com.ryankshah.skyrimcraft.dimension.sovngarde.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SovngardeEffects extends DimensionSpecialEffects {
    public SovngardeEffects() {
        super(256F, false, SkyType.NONE, false, false);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 p_108894_, float p_108895_) {
        return p_108894_.scale(0.25F);
    }

    @Override
    public boolean isFoggyAt(int p_108891_, int p_108892_) {
        return true;
    }

//    @Override
//    public Vec3 getBrightnessDependentFogColor(Vec3 p_108908_, float p_108909_) {
//        return p_108908_.multiply((double)(p_108909_ * 0.94F + 0.06F), (double)(p_108909_ * 0.94F + 0.06F), (double)(p_108909_ * 0.91F + 0.09F));
//    }

    private static final ResourceLocation SOVNGARDE_SKY_LOCATION = new ResourceLocation(Skyrimcraft.MODID, "textures/environment/sovngarde_sky.png");

//    @Override
//    public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack pPoseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
//        RenderSystem.enableBlend();
//        RenderSystem.depthMask(false);
//        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
//        RenderSystem.setShaderTexture(0, SOVNGARDE_SKY_LOCATION);
//        Tesselator tesselator = Tesselator.getInstance();
//        BufferBuilder bufferbuilder = tesselator.getBuilder();
//
//        for(int i = 0; i < 6; ++i) {
//            pPoseStack.pushPose();
//            if (i == 1) {
//                pPoseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
//            }
//
//            if (i == 2) {
//                pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
//            }
//
//            if (i == 3) {
//                pPoseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
//            }
//
//            if (i == 4) {
//                pPoseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
//            }
//
//            if (i == 5) {
//                pPoseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
//            }
//
//            Matrix4f matrix4f = pPoseStack.last().pose();
//            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
//            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, -100.0F).uv(0.0F, 0.0F).color(40, 40, 40, 255).endVertex();
//            bufferbuilder.vertex(matrix4f, -100.0F, -100.0F, 100.0F).uv(0.0F, 16.0F).color(40, 40, 40, 255).endVertex();
//            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, 100.0F).uv(16.0F, 16.0F).color(40, 40, 40, 255).endVertex();
//            bufferbuilder.vertex(matrix4f, 100.0F, -100.0F, -100.0F).uv(16.0F, 0.0F).color(40, 40, 40, 255).endVertex();
//            tesselator.end();
//            pPoseStack.popPose();
//        }
//
//        RenderSystem.depthMask(true);
//        RenderSystem.disableBlend();
//        return true;
//    }

    @Nullable
    @Override
    public float[] getSunriseColor(float p_108888_, float p_108889_) {
        return null;
    }
}