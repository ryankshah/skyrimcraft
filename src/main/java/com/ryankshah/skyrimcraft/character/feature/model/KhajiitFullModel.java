package com.ryankshah.skyrimcraft.character.feature.model;

import com.google.common.base.Preconditions;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.IArmPoseTransformer;
import net.neoforged.neoforge.common.IExtensibleEnum;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class KhajiitFullModel<T extends Player> extends AbstractSkyrimPlayerModel<T> implements ArmedModel, HeadedModel, IPlayerModel
{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft:player"), "khajiit_full");

    public static final float OVERLAY_SCALE = 0.25F;
    public static final float HAT_OVERLAY_SCALE = 0.5F;
    public static final float LEGGINGS_OVERLAY_SCALE = -0.1F;
    private static final float DUCK_WALK_ROTATION = 0.005F;
    private static final float SPYGLASS_ARM_ROT_Y = 0.2617994F;
    private static final float SPYGLASS_ARM_ROT_X = 1.9198622F;
    private static final float SPYGLASS_ARM_CROUCH_ROT_X = 0.2617994F;
    private static final float HIGHEST_SHIELD_BLOCKING_ANGLE = -1.3962634F;
    private static final float LOWEST_SHIELD_BLOCKING_ANGLE = 0.43633232F;
    private static final float HORIZONTAL_SHIELD_MOVEMENT_LIMIT = 0.5235988F;
    public static final float TOOT_HORN_XROT_BASE = 1.4835298F;
    public static final float TOOT_HORN_YROT_BASE = 0.5235988F;

    public ModelPart body;
    public ModelPart head;
    public ModelPart rightLeg;
    public ModelPart leftLeg;
    public ModelPart rightArm;
    public ModelPart leftArm;
    public ModelPart tail;
    public ModelPart ears;
    public int battleTick;
    public boolean crouching;
    public float swimAmount;
    private List<ModelPart> parts;

//    private final ModelPart khajiit;

    public KhajiitFullModel(ModelPart pRoot) {
        super(pRoot);
//        this.khajiit = pRoot.getChild("khajiit");
        this.body = pRoot.getChild("body");
        this.head = this.body.getChild("head");
        this.rightLeg = this.body.getChild("right_leg");
        this.leftLeg = this.body.getChild("left_leg");
        this.rightArm = this.body.getChild("right_arm");
        this.leftArm = this.body.getChild("left_arm");
        this.tail = this.body.getChild("tail");
        this.ears = this.body.getChild("ears");
    }

    public ModelLayerLocation layerLocation() {
        return LAYER_LOCATION;
    }

    public LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

//        PartDefinition khajiit = partdefinition.addOrReplaceChild("khajiit", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition Head = Body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));


        PartDefinition LeftLeg = Body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(71, 32).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(71, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(1.9F, -12.0F, 0.0F));

        PartDefinition RightLeg = Body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(55, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(55, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, -12.0F, 0.0F));


        PartDefinition tail = Body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 2.0F, -2.7053F, 0.0F, 0.0F));

        PartDefinition tail_down = tail.addOrReplaceChild("tail_down", CubeListBuilder.create().texOffs(0, 55).addBox(-1.5F, -7.0F, -2.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.25F))
                .texOffs(0, 45).addBox(-1.0F, -8.0F, -2.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -8.0F, 1.0F, 0.5672F, 0.0F, 0.0F));


        PartDefinition ears = Head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_ear = ears.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(14, 32).mirror().addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(14, 38).mirror().addBox(-0.5F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -8.0F, -1.0F, 0.0F, 0.2618F, 0.0873F));

        PartDefinition right_ear = ears.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(8, 32).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 38).addBox(-0.5F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, -1.0F, 0.0F, -0.2618F, -0.0873F));

        PartDefinition nose = Head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(8, 40).addBox(-2.0F, -2.0F, -5.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = nose.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 43).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -5.0F, -0.5236F, 0.0F, 0.0F));

        PartDefinition whisker = nose.addOrReplaceChild("whisker", CubeListBuilder.create().texOffs(8, 47).addBox(-4.0F, -5.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.5F, 0.0F, 3.1416F, 0.0F));

        PartDefinition eyes = Head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(8, 46).addBox(-2.5F, -4.0F, -4.01F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightArm = Body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(56, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(56, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

        PartDefinition LeftArm = Body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(72, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(72, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(5.0F, -22.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 96, 96);
    }

    @Override
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        boolean flag = pEntity.getFallFlyingTicks() > 4;
        boolean flag1 = pEntity.isVisuallySwimming();
        this.head.yRot = pNetHeadYaw * 0.017453292F;
        if (flag) {
            this.head.xRot = -0.7853982F;
        } else if (this.swimAmount > 0.0F) {
            if (flag1) {
                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, -0.7853982F);
            } else {
                this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, pHeadPitch * 0.017453292F);
            }
        } else {
            this.head.xRot = pHeadPitch * 0.017453292F;
        }

        this.body.yRot = 0.0F;
        this.rightArm.z = 0.0F;
        this.rightArm.x = -5.0F;
        this.leftArm.z = 0.0F;
        this.leftArm.x = 5.0F;
        float f = 1.0F;
        if (flag) {
            f = (float)pEntity.getDeltaMovement().lengthSqr();
            f /= 0.2F;
            f *= f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.rightArm.xRot = Mth.cos(pLimbSwing * 0.6662F + 3.1415927F) * 2.0F * pLimbSwingAmount * 0.5F / f;
        this.leftArm.xRot = Mth.cos(pLimbSwing * 0.6662F) * 2.0F * pLimbSwingAmount * 0.5F / f;
        this.rightArm.zRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        this.rightLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount / f;
        this.leftLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + 3.1415927F) * 1.4F * pLimbSwingAmount / f;
        this.rightLeg.yRot = 0.005F;
        this.leftLeg.yRot = -0.005F;
        this.rightLeg.zRot = 0.005F;
        this.leftLeg.zRot = -0.005F;
        ModelPart var10000;
        if (this.riding) {
            var10000 = this.rightArm;
            var10000.xRot += -0.62831855F;
            var10000 = this.leftArm;
            var10000.xRot += -0.62831855F;
            this.rightLeg.xRot = -1.4137167F;
            this.rightLeg.yRot = 0.31415927F;
            this.rightLeg.zRot = 0.07853982F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = -0.31415927F;
            this.leftLeg.zRot = -0.07853982F;
        }

        this.rightArm.yRot = 0.0F;
        this.leftArm.yRot = 0.0F;
        boolean flag2 = pEntity.getMainArm() == HumanoidArm.RIGHT;
        boolean flag3;
        if (pEntity.isUsingItem()) {
            flag3 = pEntity.getUsedItemHand() == InteractionHand.MAIN_HAND;
            if (flag3 == flag2) {
                this.poseRightArm(pEntity);
            } else {
                this.poseLeftArm(pEntity);
            }
        } else {
            flag3 = flag2 ? this.leftArmPose.isTwoHanded() : this.rightArmPose.isTwoHanded();
            if (flag2 != flag3) {
                this.poseLeftArm(pEntity);
                this.poseRightArm(pEntity);
            } else {
                this.poseRightArm(pEntity);
                this.poseLeftArm(pEntity);
            }
        }

        this.setupAttackAnimation(pEntity, pAgeInTicks);
        if (this.crouching) {
            this.body.xRot = 0.5F;
            var10000 = this.rightArm;
            var10000.xRot += 0.4F;
            var10000 = this.leftArm;
            var10000.xRot += 0.4F;
            this.rightLeg.z = 4.0F;
            this.leftLeg.z = 4.0F;
            this.rightLeg.y = 12.2F;
            this.leftLeg.y = 12.2F;
            this.head.y = 4.2F;
            this.body.y = 3.2F;
            this.leftArm.y = 5.2F;
            this.rightArm.y = 5.2F;
        } else {
            this.body.xRot = 0.0F;
            this.rightLeg.z = 0.0F;
            this.leftLeg.z = 0.0F;
            this.rightLeg.y = 12.0F;
            this.leftLeg.y = 12.0F;
            this.head.y = 0.0F;
            this.body.y = 0.0F;
            this.leftArm.y = 2.0F;
            this.rightArm.y = 2.0F;
        }

        if (this.rightArmPose != HumanoidModel.ArmPose.SPYGLASS) {
            AnimationUtils.bobModelPart(this.rightArm, pAgeInTicks, 1.0F);
        }

        if (this.leftArmPose != HumanoidModel.ArmPose.SPYGLASS) {
            AnimationUtils.bobModelPart(this.leftArm, pAgeInTicks, -1.0F);
        }

        if (this.swimAmount > 0.0F) {
            float f5 = pLimbSwing % 26.0F;
            HumanoidArm humanoidarm = this.getAttackArm(pEntity);
            float f1 = humanoidarm == HumanoidArm.RIGHT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
            float f2 = humanoidarm == HumanoidArm.LEFT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
            float f3;
            if (!pEntity.isUsingItem()) {
                if (f5 < 14.0F) {
                    this.leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot, 0.0F);
                    this.rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot, 0.0F);
                    this.leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, 3.1415927F);
                    this.rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, 3.1415927F);
                    this.leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, 3.1415927F + 1.8707964F * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0F));
                    this.rightArm.zRot = Mth.lerp(f1, this.rightArm.zRot, 3.1415927F - 1.8707964F * this.quadraticArmUpdate(f5) / this.quadraticArmUpdate(14.0F));
                } else if (f5 >= 14.0F && f5 < 22.0F) {
                    f3 = (f5 - 14.0F) / 8.0F;
                    this.leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot, 1.5707964F * f3);
                    this.rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot, 1.5707964F * f3);
                    this.leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, 3.1415927F);
                    this.rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, 3.1415927F);
                    this.leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, 5.012389F - 1.8707964F * f3);
                    this.rightArm.zRot = Mth.lerp(f1, this.rightArm.zRot, 1.2707963F + 1.8707964F * f3);
                } else if (f5 >= 22.0F && f5 < 26.0F) {
                    f3 = (f5 - 22.0F) / 4.0F;
                    this.leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot, 1.5707964F - 1.5707964F * f3);
                    this.rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot, 1.5707964F - 1.5707964F * f3);
                    this.leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, 3.1415927F);
                    this.rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, 3.1415927F);
                    this.leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, 3.1415927F);
                    this.rightArm.zRot = Mth.lerp(f1, this.rightArm.zRot, 3.1415927F);
                }
            }

            f3 = 0.3F;
            float f4 = 0.33333334F;
            this.leftLeg.xRot = Mth.lerp(this.swimAmount, this.leftLeg.xRot, 0.3F * Mth.cos(pLimbSwing * 0.33333334F + 3.1415927F));
            this.rightLeg.xRot = Mth.lerp(this.swimAmount, this.rightLeg.xRot, 0.3F * Mth.cos(pLimbSwing * 0.33333334F));
        }

//        this.hat.copyFrom(this.head);
    }

    @Override
    public ModelPart getBody() {
        return this.body;
    }

    @Override
    public ModelPart realBody() {
        return this.body.getChild("realbody");
    }

    @Override
    public ModelPart rightArm() {
        return this.rightArm;
    }

    @Override
    public ModelPart leftArm() {
        return this.leftArm;
    }

    @Override
    public ModelPart head() {
        return this.head;
    }

    @Override
    public ModelPart rightLeg() {
        return this.rightLeg;
    }

    @Override
    public ModelPart leftLeg() {
        return this.leftLeg;
    }

//    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//        super.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getRandomModelPart(RandomSource var1) {
        return null;
    }

    private void poseRightArm(T pLivingEntity) {
        switch (this.rightArmPose) {
            case EMPTY:
                this.rightArm.yRot = 0.0F;
                break;
            default:
                break;
            case BLOCK:
                this.poseBlockingArm(this.rightArm, true);
                break;
            case ITEM:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - 0.31415927F;
                this.rightArm.yRot = 0.0F;
                break;
            case THROW_SPEAR:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - 3.1415927F;
                this.rightArm.yRot = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yRot = -0.1F + this.head.yRot;
                this.leftArm.yRot = 0.1F + this.head.yRot + 0.4F;
                this.rightArm.xRot = -1.5707964F + this.head.xRot;
                this.leftArm.xRot = -1.5707964F + this.head.xRot;
                break;
            case CROSSBOW_CHARGE:
                AnimationUtils.animateCrossbowCharge(this.rightArm, this.leftArm, pLivingEntity, true);
                break;
            case CROSSBOW_HOLD:
                AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, true);
                break;
            case BRUSH:
                this.rightArm.xRot = this.rightArm.xRot * 0.5F - 0.62831855F;
                this.rightArm.yRot = 0.0F;
                break;
            case SPYGLASS:
                this.rightArm.xRot = Mth.clamp(this.head.xRot - 1.9198622F - (pLivingEntity.isCrouching() ? 0.2617994F : 0.0F), -2.4F, 3.3F);
                this.rightArm.yRot = this.head.yRot - 0.2617994F;
                break;
            case TOOT_HORN:
                this.rightArm.xRot = Mth.clamp(this.head.xRot, -1.2F, 1.2F) - 1.4835298F;
                this.rightArm.yRot = this.head.yRot - 0.5235988F;
//            default:
//                this.rightArmPose.applyTransform(this, pLivingEntity, HumanoidArm.RIGHT);
        }

    }

    private void poseLeftArm(T pLivingEntity) {
        switch (this.leftArmPose) {
            case EMPTY:
                this.leftArm.yRot = 0.0F;
                break;
            default:
                break;
            case BLOCK:
                this.poseBlockingArm(this.leftArm, false);
                break;
            case ITEM:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - 0.31415927F;
                this.leftArm.yRot = 0.0F;
                break;
            case THROW_SPEAR:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - 3.1415927F;
                this.leftArm.yRot = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.yRot = -0.1F + this.head.yRot - 0.4F;
                this.leftArm.yRot = 0.1F + this.head.yRot;
                this.rightArm.xRot = -1.5707964F + this.head.xRot;
                this.leftArm.xRot = -1.5707964F + this.head.xRot;
                break;
            case CROSSBOW_CHARGE:
                AnimationUtils.animateCrossbowCharge(this.rightArm, this.leftArm, pLivingEntity, false);
                break;
            case CROSSBOW_HOLD:
                AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, false);
                break;
            case BRUSH:
                this.leftArm.xRot = this.leftArm.xRot * 0.5F - 0.62831855F;
                this.leftArm.yRot = 0.0F;
                break;
            case SPYGLASS:
                this.leftArm.xRot = Mth.clamp(this.head.xRot - 1.9198622F - (pLivingEntity.isCrouching() ? 0.2617994F : 0.0F), -2.4F, 3.3F);
                this.leftArm.yRot = this.head.yRot + 0.2617994F;
                break;
            case TOOT_HORN:
                this.leftArm.xRot = Mth.clamp(this.head.xRot, -1.2F, 1.2F) - 1.4835298F;
                this.leftArm.yRot = this.head.yRot + 0.5235988F;
        }

    }

    private void poseBlockingArm(ModelPart pArm, boolean pIsRightArm) {
        pArm.xRot = pArm.xRot * 0.5F - 0.9424779F + Mth.clamp(this.head.xRot, -1.3962634F, 0.43633232F);
        pArm.yRot = (pIsRightArm ? -30.0F : 30.0F) * 0.017453292F + Mth.clamp(this.head.yRot, -0.5235988F, 0.5235988F);
    }

    protected void setupAttackAnimation(T pLivingEntity, float pAgeInTicks) {
        if (!(this.attackTime <= 0.0F)) {
            HumanoidArm humanoidarm = this.getAttackArm(pLivingEntity);
            ModelPart modelpart = this.getArm(humanoidarm);
            float f = this.attackTime;
            this.body.yRot = Mth.sin(Mth.sqrt(f) * 6.2831855F) * 0.2F;
            ModelPart var10000;
            if (humanoidarm == HumanoidArm.LEFT) {
                var10000 = this.body;
                var10000.yRot *= -1.0F;
            }

            this.rightArm.z = Mth.sin(this.body.yRot) * 5.0F;
            this.rightArm.x = -Mth.cos(this.body.yRot) * 5.0F;
            this.leftArm.z = -Mth.sin(this.body.yRot) * 5.0F;
            this.leftArm.x = Mth.cos(this.body.yRot) * 5.0F;
            var10000 = this.rightArm;
            var10000.yRot += this.body.yRot;
            var10000 = this.leftArm;
            var10000.yRot += this.body.yRot;
            var10000 = this.leftArm;
            var10000.xRot += this.body.yRot;
            f = 1.0F - this.attackTime;
            f *= f;
            f *= f;
            f = 1.0F - f;
            float f1 = Mth.sin(f * 3.1415927F);
            float f2 = Mth.sin(this.attackTime * 3.1415927F) * -(this.head.xRot - 0.7F) * 0.75F;
            modelpart.xRot -= f1 * 1.2F + f2;
            modelpart.yRot += this.body.yRot * 2.0F;
            modelpart.zRot += Mth.sin(this.attackTime * 3.1415927F) * -0.4F;
        }

    }

    protected float rotlerpRad(float pAngle, float pMaxAngle, float pMul) {
        float f = (pMul - pMaxAngle) % 6.2831855F;
        if (f < -3.1415927F) {
            f += 6.2831855F;
        }

        if (f >= 3.1415927F) {
            f -= 6.2831855F;
        }

        return pMaxAngle + pAngle * f;
    }

    private float quadraticArmUpdate(float pLimbSwing) {
        return -65.0F * pLimbSwing + pLimbSwing * pLimbSwing;
    }

    public void copyPropertiesTo(HumanoidModel<T> pModel) {
        super.copyPropertiesTo(pModel);
        pModel.leftArmPose = this.leftArmPose;
        pModel.rightArmPose = this.rightArmPose;
        pModel.crouching = this.crouching;
        pModel.head.copyFrom(this.head);
//        pModel.hat.copyFrom(this.hat);
        pModel.body.copyFrom(this.body);
        pModel.rightArm.copyFrom(this.rightArm);
        pModel.leftArm.copyFrom(this.leftArm);
        pModel.rightLeg.copyFrom(this.rightLeg);
        pModel.leftLeg.copyFrom(this.leftLeg);
    }

    public void setAllVisible(boolean pVisible) {
        this.head.visible = pVisible;
//        this.hat.visible = pVisible;
        this.body.visible = pVisible;
        this.rightArm.visible = pVisible;
        this.leftArm.visible = pVisible;
        this.rightLeg.visible = pVisible;
        this.leftLeg.visible = pVisible;
    }

    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        this.getArm(pSide).translateAndRotate(pPoseStack);
    }

    protected ModelPart getArm(HumanoidArm pSide) {
        return pSide == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    public ModelPart getHead() {
        return this.head;
    }

    private HumanoidArm getAttackArm(T pEntity) {
        HumanoidArm humanoidarm = pEntity.getMainArm();
        return pEntity.swingingArm == InteractionHand.MAIN_HAND ? humanoidarm : humanoidarm.getOpposite();
    }

    @OnlyIn(Dist.CLIENT)
    public static enum ArmPose implements IExtensibleEnum {
        EMPTY(false),
        ITEM(false),
        BLOCK(false),
        BOW_AND_ARROW(true),
        THROW_SPEAR(false),
        CROSSBOW_CHARGE(true),
        CROSSBOW_HOLD(true),
        SPYGLASS(false),
        TOOT_HORN(false),
        BRUSH(false);

        private final boolean twoHanded;
        @Nullable
        private final IArmPoseTransformer forgeArmPose;

        private ArmPose(boolean pTwoHanded) {
            this.twoHanded = pTwoHanded;
            this.forgeArmPose = null;
        }

        public boolean isTwoHanded() {
            return this.twoHanded;
        }

        private ArmPose(boolean twoHanded, @Nonnull IArmPoseTransformer forgeArmPose) {
            this.twoHanded = twoHanded;
            Preconditions.checkNotNull(forgeArmPose, "Cannot create new ArmPose with null transformer!");
            this.forgeArmPose = forgeArmPose;
        }

        public static HumanoidModel.ArmPose create(String name, boolean twoHanded, @Nonnull IArmPoseTransformer forgeArmPose) {
            throw new IllegalStateException("Enum not extended");
        }

        public <T extends LivingEntity> void applyTransform(HumanoidModel<T> model, T entity, HumanoidArm arm) {
            if (this.forgeArmPose != null) {
                this.forgeArmPose.applyTransform(model, entity, arm);
            }

        }
    }

    @Override
    public void setupAnim(AbstractSkyrimPlayerModel<T> var1, T var2, float var3, float var4, float var5, float var6, float var7) {
    }
}