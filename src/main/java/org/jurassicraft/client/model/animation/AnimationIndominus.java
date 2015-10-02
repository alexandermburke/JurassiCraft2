package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurIndominus;
import org.jurassicraft.common.entity.EntityIndominus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationIndominus extends DinosaurAnimator
{
    public AnimationIndominus()
    {
        super(new DinosaurIndominus());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = parModel.animator;

        // f = entity.ticksExisted;
        // f1 = 1F;

        float globalSpeed = 0.5F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;

        MowzieModelRenderer head = parModel.getCube("Head");

        MowzieModelRenderer lowerJaw = parModel.getCube("Lower Jaw");
        MowzieModelRenderer upperJaw = parModel.getCube("Upper Jaw");

        MowzieModelRenderer neck1 = parModel.getCube("Neck BASE");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer neck4 = parModel.getCube("Neck 4");

        MowzieModelRenderer tail1 = parModel.getCube("Tail Base");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");
        MowzieModelRenderer tail7 = parModel.getCube("Tail 7");

        MowzieModelRenderer throat1 = parModel.getCube("Throat 1");
        MowzieModelRenderer throat2 = parModel.getCube("Throat 2");

        MowzieModelRenderer bodyFront = parModel.getCube("Body Front");
        MowzieModelRenderer bodyMid = parModel.getCube("Body Mid");
        MowzieModelRenderer bodyRear = parModel.getCube("Body Rear");

        MowzieModelRenderer leftThigh = parModel.getCube("Left Thigh");
        MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");

        MowzieModelRenderer leftCalf1 = parModel.getCube("Left Calf 1");
        MowzieModelRenderer rightCalf1 = parModel.getCube("Right Calf 1");

        MowzieModelRenderer leftCalf2 = parModel.getCube("Left Calf 2");
        MowzieModelRenderer rightCalf2 = parModel.getCube("Right Calf 2");

        MowzieModelRenderer leftFoot = parModel.getCube("Foot Left");
        MowzieModelRenderer rightFoot = parModel.getCube("Foot Right");

        MowzieModelRenderer upperArmLeft = parModel.getCube("Arm UPPER LEFT");
        MowzieModelRenderer upperArmRight = parModel.getCube("Arm UPPER RIGHT");

        MowzieModelRenderer lowerArmLeft = parModel.getCube("Arm MID LEFT");
        MowzieModelRenderer lowerArmRight = parModel.getCube("Arm MID RIGHT");

        MowzieModelRenderer handLeft = parModel.getCube("Hand LEFT");
        MowzieModelRenderer handRight = parModel.getCube("Hand RIGHT");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail7, tail6, tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] body = new MowzieModelRenderer[]{head, neck4, neck3, neck2, neck1, bodyFront, bodyMid,
                bodyRear};

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[]{handLeft, lowerArmLeft, upperArmLeft};
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[]{handRight, lowerArmRight, upperArmRight};

        parModel.bob(bodyRear, globalSpeed * 1F, globalHeight * 1.0F, false, f, f1);

        parModel.bob(leftThigh, globalSpeed * 1F, globalHeight * 1.0F, false, f, f1);
        parModel.bob(rightThigh, globalSpeed * 1F, globalHeight * 1.0F, false, f, f1);

        parModel.chainWave(body, globalSpeed * 1F, globalHeight * 0.05F, 3, f, f1);
        parModel.chainWave(tail, globalSpeed * 1F, -globalHeight * 0.05F, 2, f, f1);
        parModel.chainSwing(tail, globalSpeed * 0.5F, globalHeight * 0.025F, 2, f, f1);

        parModel.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.4F, f, f1);
        parModel.walk(leftCalf1, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.1F, f, f1);
        parModel.walk(leftCalf2, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        parModel.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.1F, f, f1);

        parModel.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.4F, f, f1);
        parModel.walk(rightCalf1, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.1F, f, f1);
        parModel.walk(rightCalf2, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        parModel.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.1F, f, f1);

        leftThigh.rotationPointY += 2 * f1 * Math.cos(f * 0.5F * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5F * globalSpeed);

        parModel.chainWave(armRight, globalSpeed * 1F, globalHeight * 0.2F, 3, f, f1);
        parModel.chainWave(armLeft, globalSpeed * 1F, globalHeight * 0.2F, 3, f, f1);

        int ticksExisted = parEntity.ticksExisted;

        parModel.chainWave(tail, 0.1F, -0.025F, 2, ticksExisted, 1F);
        parModel.chainWave(body, 0.1F, 0.03F, 5, ticksExisted, 1F);
        parModel.chainWave(armRight, -0.1F, 0.1F, 4, ticksExisted, 1F);
        parModel.chainWave(armLeft, -0.1F, 0.1F, 4, ticksExisted, 1F);

//        parModel.faceTarget(bodyMid, 6.0F, rotationYaw, rotationPitch);
//        parModel.faceTarget(bodyFront, 6.0F, rotationYaw, rotationPitch);
//        parModel.faceTarget(head, 3.0F, rotationYaw, rotationPitch);
//        parModel.faceTarget(neck1, 3.0F, rotationYaw, rotationPitch);

        ((EntityIndominus) parEntity).tailBuffer.applyChainSwingBuffer(tail);

//        animator.setAnim(AnimID.IDLE);
//        animator.startPhase(15);
//        animator.move(bodyRear, 0, -3, -5);
//        animator.move(rightThigh, 0, -3, -5);
//        animator.move(leftThigh, 0, -3, -5);
//        animator.rotate(bodyRear, -0.3F, 0, 0);
//        animator.rotate(head, 0.3F, 0, 0);
//        animator.rotate(rightThigh, 0.3F, 0, 0);
//        animator.rotate(rightCalf1, -0.4F, 0, 0);
//        animator.rotate(rightCalf2, 0.4F, 0, 0);
//        animator.rotate(rightFoot, -0.3F, 0, 0);
//        animator.rotate(leftThigh, -0.7F, 0, 0);
//        animator.rotate(leftCalf1, 0.7F, 0, 0);
//        animator.rotate(leftCalf2, -0.5F, 0, 0);
//        animator.rotate(leftFoot, 0.7F, 0, 0);
//        animator.endPhase();
//        animator.startPhase(10);
//        animator.move(bodyRear, 0, 3, -10);
//        animator.move(rightThigh, 0, 3, -10);
//        animator.move(leftThigh, 0, 3, -10);
//        animator.move(head, 0, 1, 2);
//        animator.move(lowerJaw, 0, 0, 1);
//        animator.rotate(bodyRear, 0.2F, 0, 0);
//        animator.rotate(neck1, 0.2F, 0, 0);
//        animator.rotate(neck2, 0.2F, 0, 0);
//        animator.rotate(neck3, -0.2F, 0, 0);
//        animator.rotate(neck4, -0.2F, 0, 0);
//        animator.move(throat1, 0, -0.5F, 0);
//        animator.move(throat2, 0, -1, 0);
//        animator.rotate(head, -0.5F, 0, 0);
//        animator.move(head, 0, 1, 0);
//        animator.rotate(lowerJaw, 0.9F, 0, 0);
//        animator.rotate(rightThigh, 0.6F, 0, 0);
//        animator.rotate(rightCalf1, 0.05F, 0, 0);
//        animator.rotate(rightCalf2, -0.3F, 0, 0);
//        animator.rotate(rightFoot, -0.3F, 0, 0);
//        animator.rotate(leftThigh, -0.3F, 0, 0);
//        animator.rotate(leftCalf1, 0.2F, 0, 0);
//        animator.rotate(leftCalf2, -0.2F, 0, 0);
//        animator.rotate(leftFoot, 0.3F, 0, 0);
//        animator.endPhase();
//        animator.setStationaryPhase(35);
//        animator.resetPhase(15);
    }
}
