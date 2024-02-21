package com.ryankshah.skyrimcraft.character.feature.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

public abstract class AbstractSkyrimPlayerModel<T extends Entity> extends EntityModel<T> implements IArmorModel
{
    public HumanoidModel.ArmPose leftArmPose;
    public HumanoidModel.ArmPose rightArmPose;

    public AbstractSkyrimPlayerModel(ModelPart root) {
        this.leftArmPose = ArmPose.EMPTY;
        this.rightArmPose = ArmPose.EMPTY;
    }

    public AbstractSkyrimPlayerModel() {
        this.leftArmPose = ArmPose.EMPTY;
        this.rightArmPose = ArmPose.EMPTY;
    }

    public abstract void setupAnim(AbstractSkyrimPlayerModel<T> var1, T var2, float var3, float var4, float var5, float var6, float var7);

    public void setRightArmPose(HumanoidModel.ArmPose pose) {
        this.rightArmPose = pose;
    }

    public void setLeftArmPose(HumanoidModel.ArmPose pose) {
        this.leftArmPose = pose;
    }

//    public void setAllVisible(boolean statement) {
//        this.getBody().visible = statement;
//        this.realBody().visible = statement;
//        this.head().visible = statement;
//        this.rightLeg().visible = statement;
//        this.leftLeg().visible = statement;
//        this.rightArm().visible = statement;
//        this.leftArm().visible = statement;
//    }
}