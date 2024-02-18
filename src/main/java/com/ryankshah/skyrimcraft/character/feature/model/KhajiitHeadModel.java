package com.ryankshah.skyrimcraft.character.feature.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

public class KhajiitHeadModel extends EntityModel<AbstractClientPlayer> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft:player"), "khajiit_ears");
    private final ModelPart Ears;
    private final ModelPart Whiskers;

    public KhajiitHeadModel(ModelPart root) {
        this.Ears = root.getChild("Ears");
        this.Whiskers = root.getChild("Whiskers");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Ears = partdefinition.addOrReplaceChild("Ears", CubeListBuilder.create(), PartPose.offset(0.0F, -5.5F, 0.0F));

        PartDefinition Ear_Left = Ears.addOrReplaceChild("Ear_Left", CubeListBuilder.create().texOffs(4, 11).addBox(4.0F, -3.0F, -2.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(4, 11).addBox(4.0F, -4.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(5, 13).addBox(4.0F, -5.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Ear_Right = Ears.addOrReplaceChild("Ear_Right", CubeListBuilder.create().texOffs(4, 11).addBox(-5.0F, -3.0F, -2.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(4, 11).addBox(-5.0F, -4.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(5, 13).addBox(-5.0F, -5.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Whiskers = partdefinition.addOrReplaceChild("Whiskers", CubeListBuilder.create(), PartPose.offset(0.0F, 1.75F, 0.1F));

        PartDefinition Whisker_Right = Whiskers.addOrReplaceChild("Whisker_Right", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition w2_r1 = Whisker_Right.addOrReplaceChild("w2_r1", CubeListBuilder.create().texOffs(10, 14).addBox(-7.0F, -4.0F, -3.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0436F));

        PartDefinition w1_r1 = Whisker_Right.addOrReplaceChild("w1_r1", CubeListBuilder.create().texOffs(10, 14).addBox(-7.0F, -3.0F, -3.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, -0.0436F));

        PartDefinition Whisker_Left = Whiskers.addOrReplaceChild("Whisker_Left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition w4_r1 = Whisker_Left.addOrReplaceChild("w4_r1", CubeListBuilder.create().texOffs(10, 14).addBox(3.0F, -3.0F, -3.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2182F, 0.0436F));

        PartDefinition w3_r1 = Whisker_Left.addOrReplaceChild("w3_r1", CubeListBuilder.create().texOffs(10, 14).addBox(3.0F, -4.0F, -3.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2182F, -0.0436F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Ears.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        Whiskers.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}