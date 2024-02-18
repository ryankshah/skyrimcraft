package com.ryankshah.skyrimcraft.character.feature.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class KhajiitFullModel extends EntityModel<AbstractClientPlayer>
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft:player"), "khajiitfull");
    private final ModelPart khajiit;

    public KhajiitFullModel(ModelPart root) {
        this.khajiit = root.getChild("khajiit");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition khajiit = partdefinition.addOrReplaceChild("khajiit", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition LeftLeg = khajiit.addOrReplaceChild("left_pants", CubeListBuilder.create().texOffs(71, 32).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(71, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(1.9F, -12.0F, 0.0F));

        PartDefinition RightLeg = khajiit.addOrReplaceChild("right_pants", CubeListBuilder.create().texOffs(55, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(55, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

        PartDefinition Body = khajiit.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition tail = Body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 2.0F, -2.7053F, 0.0F, 0.0F));

        PartDefinition tail_down = tail.addOrReplaceChild("tail_down", CubeListBuilder.create().texOffs(0, 55).addBox(-1.5F, -7.0F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.25F))
                .texOffs(0, 45).addBox(-1.0F, -8.0F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -8.0F, 1.0F, 0.5672F, 0.0F, 0.0F));

        PartDefinition Head = khajiit.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition ears = Head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_ear = ears.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(14, 32).mirror().addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(14, 38).mirror().addBox(-0.5F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -8.0F, -1.0F, 0.0F, 0.2618F, 0.0873F));

        PartDefinition right_ear = ears.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(8, 32).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 38).addBox(-0.5F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, -1.0F, 0.0F, -0.2618F, -0.0873F));

        PartDefinition nose = Head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(8, 40).addBox(-2.0F, -2.0F, -5.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = nose.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 43).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -5.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition whisker = nose.addOrReplaceChild("whisker", CubeListBuilder.create().texOffs(8, 47).addBox(-4.0F, -5.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.5F, 0.0F, 3.1416F, 0.0F));

        PartDefinition eyes = Head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(8, 46).addBox(-2.5F, -4.0F, -4.01F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightArm = khajiit.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(56, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(56, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

        PartDefinition LeftArm = khajiit.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(72, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(72, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(5.0F, -22.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 96, 96);
    }

    @Override
    public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        khajiit.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}