package net.ilexiconn.jurassicraft.client.model.animation;

import net.ilexiconn.jurassicraft.entity.EntityVelociraptor;
import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.ChainBuffer;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class AnimationVelociraptor implements IModelAnimator
{
    public ChainBuffer tailBuffer = new ChainBuffer(5);

    @Override
    public void setRotationAngles(ModelJson model, float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        EntityVelociraptor velociraptor = (EntityVelociraptor) entity;

        limbSwingAmount *= 1.5F;
        limbSwing *= 1.5F;

        /*
         * f = entity.ticksExisted; limbSwingAmount = 1F;
         */
        // if (raptor.leaping)
        // limbSwingAmount = 0;
        // if (raptor.getAnimationId() == JurassiCraftAnimationIDs.LEAP.animID()
        // && raptor.getAnimationTick() >= 6)
        // limbSwingAmount = 0;
        float scaleFactor = 0.75F;
        float height = 2F * limbSwingAmount;

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, (EntityLivingBase) entity);

        MowzieModelRenderer body = model.getCube("body1");
        MowzieModelRenderer leftThigh = model.getCube("Left thigh");
        MowzieModelRenderer rightThigh = model.getCube("Right thigh");
        MowzieModelRenderer neck = model.getCube("neck1");
        MowzieModelRenderer head = model.getCube("head");
        MowzieModelRenderer leftShin = model.getCube("Left shin");
        MowzieModelRenderer rightShin = model.getCube("Right shin");
        MowzieModelRenderer leftUpperFoot = model.getCube("Left upper foot");
        MowzieModelRenderer leftFoot = model.getCube("Left foot");
        MowzieModelRenderer rightUpperFoot = model.getCube("Right upper foot");
        MowzieModelRenderer rightFoot = model.getCube("Right foot");
        MowzieModelRenderer upperArmRight = model.getCube("Right arm");
        MowzieModelRenderer upperArmLeft = model.getCube("Left arm");
        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");
        // MowzieModelRenderer Right_Claw_1 = model.getCube("Right Claw 1");
        // MowzieModelRenderer Left_Claw_1 = model.getCube("Left Claw 1");
        // MowzieModelRenderer Lower_Jaw = model.getCube("down_jaw");

        MowzieModelRenderer Lower_Arm_Right = model.getCube("Right forearm");
        MowzieModelRenderer Lower_Arm_Left = model.getCube("Left forearm");
        MowzieModelRenderer Hand_Right = model.getCube("Right hand");
        MowzieModelRenderer Hand_Left = model.getCube("Left hand");

        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[]{Hand_Right, Lower_Arm_Right, upperArmRight};
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[]{Hand_Left, Lower_Arm_Left, upperArmLeft};
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail5, tail4, tail3, tail2, tail1};

        model.bob(body, 1F * scaleFactor, height, false, limbSwing, limbSwingAmount);
        model.bob(leftThigh, 1F * scaleFactor, height, false, limbSwing, limbSwingAmount);
        model.bob(rightThigh, 1F * scaleFactor, height, false, limbSwing, limbSwingAmount);
        model.bob(neck, 1F * scaleFactor, height / 2, false, limbSwing, limbSwingAmount);

        model.walk(neck, 1F * scaleFactor, 0.25F, false, 1F, 0.4F, limbSwing, limbSwingAmount);
        model.walk(head, 1F * scaleFactor, 0.25F, true, 1F, -0.4F, limbSwing, limbSwingAmount);

        model.walk(leftThigh, 0.5F * scaleFactor, 0.8F, false, 0F, 0.4F, limbSwing, limbSwingAmount);
        model.walk(leftShin, 0.5F * scaleFactor, 0.5F, true, 1F, 0F, limbSwing, limbSwingAmount);
        model.walk(leftUpperFoot, 0.5F * scaleFactor, 0.5F, false, 0F, 0F, limbSwing, limbSwingAmount);
        model.walk(leftFoot, 0.5F * scaleFactor, 1.5F, true, 0.5F, 1F, limbSwing, limbSwingAmount);

        model.walk(rightThigh, 0.5F * scaleFactor, 0.8F, true, 0F, 0.4F, limbSwing, limbSwingAmount);
        model.walk(rightShin, 0.5F * scaleFactor, 0.5F, false, 1F, 0F, limbSwing, limbSwingAmount);
        model.walk(rightUpperFoot, 0.5F * scaleFactor, 0.5F, true, 0F, 0F, limbSwing, limbSwingAmount);
        model.walk(rightFoot, 0.5F * scaleFactor, 1.5F, false, 0.5F, 1F, limbSwing, limbSwingAmount);

        model.chainSwing(tailParts, 0.5F * scaleFactor, -0.1F, 2, limbSwing, limbSwingAmount);
        model.chainWave(tailParts, 1F * scaleFactor, -0.05F, 2, limbSwing, limbSwingAmount);
        model.chainWave(rightArmParts, 1F * scaleFactor, -0.3F, 4, limbSwing, limbSwingAmount);
        model.chainWave(leftArmParts, 1F * scaleFactor, -0.3F, 4, limbSwing, limbSwingAmount);

        // float sittingProgress =
        // raptor.sittingProgress.getAnimationProgressSin();
        //
        // if (sittingProgress > 0.001F)
        // {
        // //Sitting Pose
        // float sittingProgressTemporary =
        // raptor.sittingProgress.getAnimationProgressTemporaryFS();
        //
        // model.faceTarget(Head, 5, rotationYaw, rotationPitch);
        // model.faceTarget(Neck, 4, rotationYaw, rotationPitch);
        //
        // Body_1.rotationPointY += 13.25F * sittingProgress;
        // Right_Thigh.rotationPointY += 14.25F * sittingProgress;
        // Left_Thigh.rotationPointY += 14.25F * sittingProgress;
        // Right_Thigh.rotationPointZ += 0.5F * sittingProgress;
        // Left_Thigh.rotationPointZ += 0.5F * sittingProgress;
        //
        // if (sittingProgressTemporary > 0.001F)
        // {
        // Body_1.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Neck.rotateAngleX += 0.4F * sittingProgressTemporary;
        // Head.rotateAngleX += 0.2F * sittingProgressTemporary;
        // Upper_Arm_Right.rotateAngleX += 0.5F * sittingProgressTemporary;
        // Upper_Arm_Left.rotateAngleX += 0.5F * sittingProgressTemporary;
        //
        // if (raptor.isSitting())
        // {
        // Tail_1.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Tail_2.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Tail_3.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Tail_4.rotateAngleX += 0.1F * sittingProgressTemporary;
        // Tail_5.rotateAngleX += 0.1F * sittingProgressTemporary;
        // }
        // else
        // {
        // Tail_1.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // Tail_2.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // Tail_3.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // Tail_4.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // Tail_5.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // }
        // }
        //
        // Body_1.rotateAngleX -= 0.075F * sittingProgress;
        //
        // Upper_Arm_Right.rotateAngleX -= 0.8F * sittingProgress;
        // Upper_Arm_Left.rotateAngleX -= 0.8F * sittingProgress;
        //
        // Right_Thigh.rotateAngleX -= 0.75F * sittingProgress;
        // Left_Thigh.rotateAngleX -= 0.75F * sittingProgress;
        //
        // Right_Calf_1.rotationPointZ += 0.5F * sittingProgress;
        // Left_Calf_1.rotationPointZ += 0.5F * sittingProgress;
        // Right_Calf_1.rotationPointY += 1.5F * sittingProgress;
        // Left_Calf_1.rotationPointY += 1.5F * sittingProgress;
        // Right_Calf_1.rotateAngleX += 1.2F * sittingProgress;
        // Left_Calf_1.rotateAngleX += 1.2F * sittingProgress;
        //
        // Right_Upper_Foot.rotationPointZ -= 0.6F * sittingProgress;
        // Left_Upper_Foot.rotationPointZ -= 0.6F * sittingProgress;
        // Right_Upper_Foot.rotateAngleX -= 1.45F * sittingProgress;
        // Left_Upper_Foot.rotateAngleX -= 1.45F * sittingProgress;
        //
        // Foot_Right.rotationPointZ -= 0.5F * sittingProgress;
        // Foot_Left.rotationPointZ -= 0.5F * sittingProgress;
        // Foot_Right.rotateAngleX += 1.0F * sittingProgress;
        // Foot_Left.rotateAngleX += 1.0F * sittingProgress;
        //
        // Right_Claw_1.rotateAngleX += 0.7F * sittingProgress;
        // Left_Claw_1.rotateAngleX += 0.7F * sittingProgress;
        //
        // //Idling
        // model.chainWave(tailParts, 0.1F, -0.05F, 2, entity.ticksExisted, 1.0F
        // - 0.5F * sittingProgress);
        // model.walk(Neck, 0.1F, 0.07F, false, -1F, 0F, entity.ticksExisted, 1F
        // - 0.3F * sittingProgress);
        // model.walk(Head, 0.1F, 0.07F, true, 0F, 0F, entity.ticksExisted, 1F -
        // 0.3F * sittingProgress);
        // model.walk(Body_1, 0.1F, 0.05F, false, 0F, 0F, entity.ticksExisted,
        // 1.0F - 0.7F * sittingProgress);
        // model.chainWave(rightArmParts, 0.1F, -0.1F, 4, entity.ticksExisted,
        // 1.0F - 0.5F * sittingProgress);
        // model.chainWave(leftArmParts, 0.1F, -0.1F, 4, entity.ticksExisted,
        // 1.0F - 0.5F * sittingProgress);
        // }
        // else
        // {

        model.faceTarget(head, 2, rotationYaw, rotationPitch);
        model.faceTarget(neck, 2, rotationYaw, rotationPitch);

        // Idling
        model.chainWave(tailParts, 0.1F, -0.05F, 2, entity.ticksExisted, 1F);
        model.walk(neck, 0.1F, 0.07F, false, -1F, 0F, entity.ticksExisted, 1F);
        model.walk(head, 0.1F, 0.07F, true, 0F, 0F, entity.ticksExisted, 1F);
        model.walk(body, 0.1F, 0.05F, false, 0F, 0F, entity.ticksExisted, 1F);
        model.chainWave(rightArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);
        model.chainWave(leftArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);
        // }

        tailBuffer.applyChainSwingBuffer(tailParts);

        // if (raptor.getAnimationTick() == 20 && raptor.getAnimationId() ==
        // JurassiCraftAnimationIDs.LEAP.animID())
        // raptor.setLeaping(true);

        // if (raptor.leaping == true)
        // {
        // Body_1.rotateAngleX -= 0.8;
        // Neck.rotateAngleX += 0.8;
        // Neck.rotationPointY -= 2;
        // Neck.rotationPointZ -= 3;
        // Head.rotateAngleX += 0.5;
        // Lower_Jaw.rotateAngleX += 0.7;
        // Tail_1.rotationPointZ -= 2;
        // Tail_1.rotateAngleX += 0.7F;
        //
        // Right_Thigh.rotateAngleX -= 1.2;
        // Left_Thigh.rotateAngleX -= 1.2;
        // Right_Calf_1.rotateAngleX -= 0.3;
        // Left_Calf_1.rotateAngleX -= 0.3;
        // Right_Upper_Foot.rotateAngleX += 0.3;
        // Left_Upper_Foot.rotateAngleX += 0.3;
        // Foot_Right.rotateAngleX -= 0.3;
        // Foot_Left.rotateAngleX -= 0.3;
        //
        // Upper_Arm_Right.rotateAngleX -= 0.5F;
        // Upper_Arm_Right.rotateAngleZ += 1F;
        // Upper_Arm_Left.rotateAngleX -= 0.5F;
        // Upper_Arm_Left.rotateAngleZ -= 1F;
        // Lower_Arm_Right.rotateAngleX += 0.5F;
        // Lower_Arm_Right.rotateAngleY += 1.5F;
        // Lower_Arm_Left.rotateAngleX += 0.5F;
        // Lower_Arm_Left.rotateAngleY -= 1.5F;
        // Hand_Right.rotateAngleX -= 1;
        // Hand_Left.rotateAngleX -= 1;
        // }
    }
}
