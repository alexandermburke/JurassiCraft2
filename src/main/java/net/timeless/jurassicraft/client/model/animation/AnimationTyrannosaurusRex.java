package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityTyrannosaurusRex;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationTyrannosaurusRex implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;
        // f = entity.ticksExisted;
        // f1 = (float) Math.cos(f/20)*0.25F + 0.5F;
        // f1 = 0.5F;

        // Walking-dependent animation
        float globalSpeed = 0.45F;
        float globalDegree = 0.5F;
        float height = 1.0F;

        MowzieModelRenderer stomach = model.getCube("Body 2");
        MowzieModelRenderer chest = model.getCube("Body 3");
        MowzieModelRenderer head = model.getCube("Head");
        MowzieModelRenderer waist = model.getCube("Body 1");

        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");
        MowzieModelRenderer neck5 = model.getCube("Neck 5");

        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer throat1 = model.getCube("Throat 1");
        MowzieModelRenderer throat2 = model.getCube("Throat 2");
        MowzieModelRenderer throat3 = model.getCube("Throat 3");

        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");

        MowzieModelRenderer handLeft = model.getCube("Hand Left");
        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm Left");
        MowzieModelRenderer upperArmLeft = model.getCube("Upper Arm Left");

        MowzieModelRenderer handRight = model.getCube("Hand Right");
        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
        MowzieModelRenderer upperArmRight = model.getCube("Upper Arm Right");

        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");

        MowzieModelRenderer leftCalf1 = model.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");
        MowzieModelRenderer leftFoot = model.getCube("Foot Left");

        MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
        MowzieModelRenderer rightFoot = model.getCube("Foot Right");

        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[]{head, neck5, neck4, neck3, neck2, neck1, chest, stomach, waist};
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[]{handLeft, lowerArmLeft, upperArmLeft};
        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[]{handRight, lowerArmRight, upperArmRight};

        model.faceTarget(stomach, 6.0F, rotationYaw, rotationPitch);
        model.faceTarget(chest, 6.0F, rotationYaw, rotationPitch);
        model.faceTarget(head, 3.0F, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 3.0F, rotationYaw, rotationPitch);

        model.bob(waist, 1F * globalSpeed, height, false, f, f1);
        model.bob(leftThigh, 1F * globalSpeed, height, false, f, f1);
        model.bob(rightThigh, 1F * globalSpeed, height, false, f, f1);
        leftThigh.rotationPointY -= -2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        model.walk(neck1, 1F * globalSpeed, 0.15F, false, 0F, 0.2F, f, f1);
        model.walk(head, 1F * globalSpeed, 0.15F, true, 0F, -0.2F, f, f1);

        model.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.4F, f, f1);
        model.walk(leftCalf1, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        model.walk(leftCalf2, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        model.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.3F, f, f1);

        model.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.4F, f, f1);
        model.walk(rightCalf1, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        model.walk(rightCalf2, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        model.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.3F, f, f1);

        model.chainWave(tailParts, 1F * globalSpeed, 0.05F, 2, f, f1);
        model.chainWave(bodyParts, 1F * globalSpeed, 0.05F, 3, f, f1);
        model.chainWave(leftArmParts, 1F * globalSpeed, 0.2F, 1, f, f1);
        model.chainWave(rightArmParts, 1F * globalSpeed, 0.2F, 1, f, f1);

        // Idling
        model.chainWave(bodyParts, 0.1F, -0.03F, 3, entity.ticksExisted, 1.0F);
        model.chainWave(rightArmParts, -0.1F, 0.2F, 4, entity.ticksExisted, 1.0F);
        model.chainWave(leftArmParts, -0.1F, 0.2F, 4, entity.ticksExisted, 1.0F);

        model.chainSwing(tailParts, 0.1F, 0.05F - (0.05F), 1, entity.ticksExisted, 1.0F - 0.6F);
        model.chainWave(tailParts, 0.1F, -0.1F, 2, entity.ticksExisted, 1.0F - 0.6F);

        ((EntityTyrannosaurusRex) entity).tailBuffer.applyChainSwingBuffer(tailParts);

        animator.setAnim(1);
        animator.startPhase(15);
        animator.move(waist, 0, -3, -5);
        animator.move(rightThigh, 0, -3, -5);
        animator.move(leftThigh, 0, -3, -5);
        animator.rotate(waist, -0.3F, 0, 0);
        animator.rotate(head, 0.3F, 0, 0);
        animator.rotate(rightThigh, 0.3F, 0, 0);
        animator.rotate(rightCalf1, -0.4F, 0, 0);
        animator.rotate(rightCalf2, 0.4F, 0, 0);
        animator.rotate(rightFoot, -0.3F, 0, 0);
        animator.rotate(leftThigh, -0.7F, 0, 0);
        animator.rotate(leftCalf1, 0.7F, 0, 0);
        animator.rotate(leftCalf2, -0.5F, 0, 0);
        animator.rotate(leftFoot, 0.7F, 0, 0);
        animator.endPhase();
        animator.startPhase(10);
        animator.move(waist, 0, 3, -10);
        animator.move(rightThigh, 0, 3, -10);
        animator.move(leftThigh, 0, 3, -10);
        animator.move(head, 0, 1, 2);
        animator.move(lowerJaw, 0, 0, 1);
        animator.rotate(waist, 0.2F, 0, 0);
        animator.rotate(neck1, 0.2F, 0, 0);
        animator.rotate(neck2, 0.2F, 0, 0);
        animator.rotate(neck3, -0.2F, 0, 0);
        animator.rotate(neck4, -0.1F, 0, 0);
        animator.rotate(neck5, -0.1F, 0, 0);
        animator.move(neck5, 0, 0, 1);
        animator.move(throat1, 0, -0.5F, 0);
        animator.move(throat2, 0, -1, 0);
        animator.move(throat3, 0, -1, 0);
        animator.rotate(head, -0.5F, 0, 0);
        animator.move(head, 0, 1, 0);
        animator.rotate(lowerJaw, 0.9F, 0, 0);
        animator.rotate(rightThigh, 0.6F, 0, 0);
        animator.rotate(rightCalf1, 0.05F, 0, 0);
        animator.rotate(rightCalf2, -0.3F, 0, 0);
        animator.rotate(rightFoot, -0.3F, 0, 0);
        animator.rotate(leftThigh, -0.3F, 0, 0);
        animator.rotate(leftCalf1, 0.2F, 0, 0);
        animator.rotate(leftCalf2, -0.2F, 0, 0);
        animator.rotate(leftFoot, 0.3F, 0, 0);
        animator.endPhase();
        animator.setStationaryPhase(35);
        animator.resetPhase(15);

        animator.setAnim(2);
        animator.startPhase(15);
        animator.rotate(waist, -0.2F, 0, 0);
        animator.rotate(stomach, -0.1F, 0, 0);
        animator.rotate(chest, 0.1F, 0, 0);
        animator.rotate(neck1, -0.1F, 0, 0);
        animator.rotate(neck2, -0.1F, 0, 0);
        animator.rotate(neck3, 0.1F, 0, 0);
        animator.rotate(neck4, 0.1F, 0, 0);
        animator.rotate(neck5, 0.1F, 0, 0);
        animator.rotate(head, 0.3F, 0, 0);
        animator.endPhase();
        animator.startPhase(10);
        animator.rotate(waist, 0.1F, 0, 0);
        animator.rotate(neck1, 0.2F, 0, 0);
        animator.rotate(neck2, 0.2F, 0, 0);
        animator.rotate(neck3, 0.1F, 0, 0);
        animator.rotate(neck4, -0.2F, 0, 0);
        animator.rotate(neck5, -0.2F, 0, 0);
        animator.move(throat1, 0, 0, 0);
        animator.move(throat2, 0, -1, -3.5F);
        animator.move(throat3, 0, -1.5F, 0);
        animator.rotate(head, -0.4F, 0, 0);
        animator.move(head, 0, 1, 2F);
        animator.rotate(lowerJaw, 0.8F, 0, 0);
        animator.endPhase();
        animator.setStationaryPhase(35);
        animator.resetPhase(15);

//        if (entity.getAnimationId() == JurassiCraftAnimationIDs.EATING.animID())
//        {
//            float shakeProgress = ((EntityTyrannosaurus) entity).shakePrey.getAnimationProgressSinSqrt();
//            chainSwing(bodyParts, 0.6F, 0.2F * shakeProgress, 1, ((EntityTyrannosaurus) entity).frame, 1F);
//            chainSwing(tailParts, 0.6F, -0.2F * shakeProgress, 3, ((EntityTyrannosaurus) entity).frame, 1F);
//            waist.rotateAngleX += 0.3 * shakeProgress;
//            head.rotateAngleX -= 0.3 * shakeProgress;
//            animator.setAnimation(JurassiCraftAnimationIDs.EATING.animID());
//            animator.startPhase(0);
//            animator.rotate(lowerJaw, 0.3F, 0.0F, 0.0F);
//            animator.endPhase();
//            animator.setStationaryPhase(30);
//            animator.startPhase(7);
//            animator.rotate(lowerJaw, 0.4F, 0.0F, 0.0F);
//            animator.rotate(neck1, -0.4F, 0.0F, 0.0F);
//            animator.rotate(head, -0.4F, 0.0F, 0.0F);
//            animator.endPhase();
//            animator.setStationaryPhase(3);
//            animator.resetPhase(10);
//        }

        animator.setAnim(3);
        animator.startPhase(6);
        animator.rotate(neck1, -0.1F, -0.2F, 0);
        animator.rotate(head, -0.2F, -0.3F, 0);
        animator.rotate(waist, -0.1F, -0.2F, 0);
        animator.rotate(lowerJaw, 1F, 0, 0);
        animator.endPhase();
        animator.setStationaryPhase(1);
        animator.startPhase(3);
        animator.rotate(neck1, 0.2F, 0.1F, 0);
        animator.rotate(neck2, 0.2F, 0.1F, 0);
        animator.rotate(neck3, 0.1F, 0.1F, 0);
        animator.rotate(neck4, -0.2F, 0.1F, 0);
        animator.rotate(neck5, -0.2F, 0.1F, 0);
        animator.move(throat2, 0, 0, -2.7F);
        animator.move(throat3, 0, 0, 1.5F);
        animator.rotate(head, -0.2F, 0.4F, 0);
        animator.rotate(waist, 0.2F, 0.2F, 0);
        animator.endPhase();
        animator.setStationaryPhase(2);
        animator.resetPhase(8);
    }
}

