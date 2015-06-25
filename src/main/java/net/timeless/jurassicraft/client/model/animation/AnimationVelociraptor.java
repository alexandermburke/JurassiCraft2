package net.timeless.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.timeless.jurassicraft.api.animation.Animator;

public class AnimationVelociraptor implements IModelAnimator
{

    private final Animator animator;

    public AnimationVelociraptor()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        // if (!animator.isInitialized())
        // animator.init(model);
        // EntityVelociraptor velociraptor = (EntityVelociraptor) entity;
        //
        // int frame = velociraptor.ticksExisted;
        //
        // // f = entity.ticksExisted;
        // // f1 = 1F;
        // // f1 = (float) (Math.sin(velociraptor.ticksExisted * 0.01) * Math.sin(velociraptor.ticksExisted * 0.01));
        //
        // // if (raptor.leaping)
        // // limbSwingAmount = 0;
        // // if (raptor.getAnimationId() == JurassiCraftAnimationIDs.LEAP.animID()
        // // && raptor.getAnimationTick() >= 6)
        // // limbSwingAmount = 0;
        // float speed = 0.75F;
        // float height = 2F * f1;
        //
        // MowzieModelRenderer waist = model.getCube("body3");
        // MowzieModelRenderer chest = model.getCube("body2");
        // MowzieModelRenderer shoulders = model.getCube("body1");
        // MowzieModelRenderer leftThigh = model.getCube("Left thigh");
        // MowzieModelRenderer rightThigh = model.getCube("Right thigh");
        // MowzieModelRenderer neck1 = model.getCube("neck1");
        // MowzieModelRenderer neck2 = model.getCube("neck2");
        // MowzieModelRenderer neck3 = model.getCube("neck3");
        // MowzieModelRenderer neck4 = model.getCube("neck4");
        // MowzieModelRenderer head = model.getCube("head");
        // MowzieModelRenderer leftShin = model.getCube("Left shin");
        // MowzieModelRenderer rightShin = model.getCube("Right shin");
        // MowzieModelRenderer leftUpperFoot = model.getCube("Left upper foot");
        // MowzieModelRenderer leftFoot = model.getCube("Left foot");
        // MowzieModelRenderer rightUpperFoot = model.getCube("Right upper foot");
        // MowzieModelRenderer rightFoot = model.getCube("Right foot");
        // MowzieModelRenderer upperArmRight = model.getCube("Right arm");
        // MowzieModelRenderer upperArmLeft = model.getCube("Left arm");
        // MowzieModelRenderer tail1 = model.getCube("tail1");
        // MowzieModelRenderer tail2 = model.getCube("tail2");
        // MowzieModelRenderer tail3 = model.getCube("tail3");
        // MowzieModelRenderer tail4 = model.getCube("tail4");
        // MowzieModelRenderer tail5 = model.getCube("tail5");
        // // MowzieModelRenderer Right_Claw_1 = model.getCube("Right Claw 1");
        // // MowzieModelRenderer Left_Claw_1 = model.getCube("Left Claw 1");
        // // MowzieModelRenderer Lower_Jaw = model.getCube("down_jaw");
        //
        // MowzieModelRenderer Lower_Arm_Right = model.getCube("Right forearm");
        // MowzieModelRenderer Lower_Arm_Left = model.getCube("Left forearm");
        // MowzieModelRenderer Hand_Right = model.getCube("Right hand");
        // MowzieModelRenderer Hand_Left = model.getCube("Left hand");
        //
        // MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[] { Hand_Right, Lower_Arm_Right, upperArmRight };
        // MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[] { Hand_Left, Lower_Arm_Left, upperArmLeft };
        // MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail5, tail4, tail3, tail2, tail1 };
        // MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[] { waist, chest, shoulders, neck4, neck3, neck2, neck1, head };
        //
        // model.bob(waist, 1F * speed, height, false, f, f1);
        // model.bob(leftThigh, 1F * speed, height, false, f, f1);
        // model.bob(rightThigh, 1F * speed, height, false, f, f1);
        // model.walk(shoulders, 1F * speed, 0.2F, true, 1, 0, f, f1);
        // model.walk(chest, 1F * speed, 0.2F, false, 0.5F, 0, f, f1);
        //
        // model.walk(leftThigh, 0.5F * speed, 0.7F, false, 3.14F, 0.2F, f, f1);
        // model.walk(leftShin, 0.5F * speed, 0.6F, false, 1.5F, 0.3F, f, f1);
        // model.walk(leftUpperFoot, 0.5F * speed, 0.8F, false, -1F, -0.1F, f, f1);
        // model.walk(leftFoot, 0.5F * speed, 1.5F, true, -1F, 1F, f, f1);
        //
        // model.walk(rightThigh, 0.5F * speed, 0.7F, true, 3.14F, 0.2F, f, f1);
        // model.walk(rightShin, 0.5F * speed, 0.6F, true, 1.5F, 0.3F, f, f1);
        // model.walk(rightUpperFoot, 0.5F * speed, 0.8F, true, -1F, -0.1F, f, f1);
        // model.walk(rightFoot, 0.5F * speed, 1.5F, false, -1F, 1F, f, f1);
        //
        // model.chainSwing(tailParts, 0.5F * speed, -0.1F, 2, f, f1);
        // model.chainWave(tailParts, 1F * speed, -0.1F, 2.5F, f, f1);
        // model.chainWave(bodyParts, 1F * speed, -0.1F, 4, f, f1);
        // shoulders.rotationPointY -= 0.5 * f1;
        // shoulders.rotationPointZ -= 0.5 * f1;
        // shoulders.rotateAngleX += 0.6 * f1;
        // chest.rotateAngleX += 0.1 * f1;
        // neck1.rotateAngleX += 0.1 * f1;
        // neck2.rotateAngleX += 0.1 * f1;
        // neck3.rotateAngleX -= 0.2 * f1;
        // neck4.rotateAngleX -= 0.2 * f1;
        // head.rotateAngleX -= 0.3 * f1;
        // model.chainWave(rightArmParts, 1F * speed, -0.3F, 4, f, f1);
        // model.chainWave(leftArmParts, 1F * speed, -0.3F, 4, f, f1);
        //
        // // Idling
        // model.chainWave(tailParts, 0.1F, -0.05F, 2, entity.ticksExisted, 1F);
        // model.chainWave(bodyParts, 0.1F, -0.03F, 5, entity.ticksExisted, 1F);
        // model.chainWave(rightArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);
        // model.chainWave(leftArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);
        //
        // // float sittingProgress =
        // // raptor.sittingProgress.getAnimationProgressSin();
        // //
        // // if (sittingProgress > 0.001F)
        // // {
        // // //Sitting Pose
        // // float sittingProgressTemporary =
        // // raptor.sittingProgress.getAnimationProgressTemporaryFS();
        // //
        // // model.faceTarget(Head, 5, rotationYaw, rotationPitch);
        // // model.faceTarget(Neck, 4, rotationYaw, rotationPitch);
        // //
        // // Body_1.rotationPointY += 13.25F * sittingProgress;
        // // Right_Thigh.rotationPointY += 14.25F * sittingProgress;
        // // Left_Thigh.rotationPointY += 14.25F * sittingProgress;
        // // Right_Thigh.rotationPointZ += 0.5F * sittingProgress;
        // // Left_Thigh.rotationPointZ += 0.5F * sittingProgress;
        // //
        // // if (sittingProgressTemporary > 0.001F)
        // // {
        // // Body_1.rotateAngleX += 0.1F * sittingProgressTemporary;
        // // Neck.rotateAngleX += 0.4F * sittingProgressTemporary;
        // // Head.rotateAngleX += 0.2F * sittingProgressTemporary;
        // // Upper_Arm_Right.rotateAngleX += 0.5F * sittingProgressTemporary;
        // // Upper_Arm_Left.rotateAngleX += 0.5F * sittingProgressTemporary;
        // //
        // // if (raptor.isSitting())
        // // {
        // // Tail_1.rotateAngleX += 0.1F * sittingProgressTemporary;
        // // Tail_2.rotateAngleX += 0.1F * sittingProgressTemporary;
        // // Tail_3.rotateAngleX += 0.1F * sittingProgressTemporary;
        // // Tail_4.rotateAngleX += 0.1F * sittingProgressTemporary;
        // // Tail_5.rotateAngleX += 0.1F * sittingProgressTemporary;
        // // }
        // // else
        // // {
        // // Tail_1.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // // Tail_2.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // // Tail_3.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // // Tail_4.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // // Tail_5.rotateAngleX -= 0.1F * sittingProgressTemporary;
        // // }
        // // }
        // //
        // // Body_1.rotateAngleX -= 0.075F * sittingProgress;
        // //
        // // Upper_Arm_Right.rotateAngleX -= 0.8F * sittingProgress;
        // // Upper_Arm_Left.rotateAngleX -= 0.8F * sittingProgress;
        // //
        // // Right_Thigh.rotateAngleX -= 0.75F * sittingProgress;
        // // Left_Thigh.rotateAngleX -= 0.75F * sittingProgress;
        // //
        // // Right_Calf_1.rotationPointZ += 0.5F * sittingProgress;
        // // Left_Calf_1.rotationPointZ += 0.5F * sittingProgress;
        // // Right_Calf_1.rotationPointY += 1.5F * sittingProgress;
        // // Left_Calf_1.rotationPointY += 1.5F * sittingProgress;
        // // Right_Calf_1.rotateAngleX += 1.2F * sittingProgress;
        // // Left_Calf_1.rotateAngleX += 1.2F * sittingProgress;
        // //
        // // Right_Upper_Foot.rotationPointZ -= 0.6F * sittingProgress;
        // // Left_Upper_Foot.rotationPointZ -= 0.6F * sittingProgress;
        // // Right_Upper_Foot.rotateAngleX -= 1.45F * sittingProgress;
        // // Left_Upper_Foot.rotateAngleX -= 1.45F * sittingProgress;
        // //
        // // Foot_Right.rotationPointZ -= 0.5F * sittingProgress;
        // // Foot_Left.rotationPointZ -= 0.5F * sittingProgress;
        // // Foot_Right.rotateAngleX += 1.0F * sittingProgress;
        // // Foot_Left.rotateAngleX += 1.0F * sittingProgress;
        // //
        // // Right_Claw_1.rotateAngleX += 0.7F * sittingProgress;
        // // Left_Claw_1.rotateAngleX += 0.7F * sittingProgress;
        // //
        // // //Idling
        // // model.chainWave(tailParts, 0.1F, -0.05F, 2, entity.ticksExisted, 1.0F
        // // - 0.5F * sittingProgress);
        // // model.walk(Neck, 0.1F, 0.07F, false, -1F, 0F, entity.ticksExisted, 1F
        // // - 0.3F * sittingProgress);
        // // model.walk(Head, 0.1F, 0.07F, true, 0F, 0F, entity.ticksExisted, 1F -
        // // 0.3F * sittingProgress);
        // // model.walk(Body_1, 0.1F, 0.05F, false, 0F, 0F, entity.ticksExisted,
        // // 1.0F - 0.7F * sittingProgress);
        // // model.chainWave(rightArmParts, 0.1F, -0.1F, 4, entity.ticksExisted,
        // // 1.0F - 0.5F * sittingProgress);
        // // model.chainWave(leftArmParts, 0.1F, -0.1F, 4, entity.ticksExisted,
        // // 1.0F - 0.5F * sittingProgress);
        // // }
        // // else
        // // {
        //
        // model.faceTarget(head, 2, rotationYaw, rotationPitch);
        // model.faceTarget(neck1, 2, rotationYaw, rotationPitch);
        //
        // ((EntityVelociraptor) entity).tailBuffer.applyChainSwingBuffer(tailParts);
        //
        // animator.begin(velociraptor, partialTicks); // Start animating the velociraptor
        // animator.startPhase(65L); // Main phase, duration: 150 ticks
        // {
        // animator.startPhase(55L + 10L, 10L);
        // {
        // if (velociraptor.isMale())
        // {
        // animator.rotate(Lower_Arm_Left, (float) (Math.PI * 2f), 0, 0);
        // animator.rotate(Hand_Left, (float) (Math.PI * 2f), 0, 0);
        // }
        // else
        // {
        // animator.rotate(Lower_Arm_Right, (float) (Math.PI * 2f), 0, 0);
        // animator.rotate(Hand_Right, (float) (Math.PI * 2f), 0, 0);
        // }
        // }
        // animator.endPhase();
        //
        // float angle = (float) (Math.PI / 4f);
        // animator.startPhase(65L / 2L);
        // animator.rotate(neck1, -angle, 0, 0);
        // animator.endPhase();
        //
        // animator.startPhase(65L / 2L + 1, 65L / 2L);
        // animator.rotate(neck1, angle, 0, 0);
        // animator.endPhase();
        // // OtherStuff
        // }
        // animator.endPhase(); // Ends main phase definition
        // animator.end(); // Ends animation definition and applies it
    }
}
