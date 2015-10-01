package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurMajungasaurus;
import org.jurassicraft.common.entity.EntityMajungasaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationMajungasaurus extends DinosaurAnimator
{
    public AnimationMajungasaurus()
    {
        super(new DinosaurMajungasaurus());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = parModel.animator;

        EntityMajungasaurus dino = (EntityMajungasaurus) parEntity;

        //        f = dino.ticksExisted;
        //        f1 = 1F;

        MowzieModelRenderer tail1 = parModel.getCube("Tail Base");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");

        MowzieModelRenderer leftThigh = parModel.getCube("Left Thigh");
        MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");

        MowzieModelRenderer leftCalf1 = parModel.getCube("Left Calf 1");
        MowzieModelRenderer rightCalf1 = parModel.getCube("Right Calf 1");

        MowzieModelRenderer leftCalf2 = parModel.getCube("Left Calf 2");
        MowzieModelRenderer rightCalf2 = parModel.getCube("Right Calf 2");

        MowzieModelRenderer leftFoot = parModel.getCube("Foot Left");
        MowzieModelRenderer rightFoot = parModel.getCube("Foot Right");

        MowzieModelRenderer bodyRear = parModel.getCube("Body Rear");
        MowzieModelRenderer bodyMid = parModel.getCube("Body Mid");
        MowzieModelRenderer bodyFront = parModel.getCube("Body Front");

        MowzieModelRenderer neck1 = parModel.getCube("Neck BASE");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer neck4 = parModel.getCube("Neck 4");

        MowzieModelRenderer head = parModel.getCube("Head");

        MowzieModelRenderer upperJaw = parModel.getCube("Upper Jaw");
        MowzieModelRenderer lowerJaw = parModel.getCube("Lower jaw");

        MowzieModelRenderer upperArmRight = parModel.getCube("Upper Arm Right");
        MowzieModelRenderer upperArmLeft = parModel.getCube("Upper Arm LEFT");

        MowzieModelRenderer lowerArmRight = parModel.getCube("Lower Arm Right");
        MowzieModelRenderer lowerArmLeft = parModel.getCube("Lower Arm LEFT");

        MowzieModelRenderer handRight = parModel.getCube("Hand Right");
        MowzieModelRenderer handLeft = parModel.getCube("Hand LEFT");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail1, tail2, tail3, tail4, tail5, tail6};

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[]{upperArmLeft, lowerArmLeft, handLeft};
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[]{upperArmRight, lowerArmRight, handRight};

        MowzieModelRenderer[] body = new MowzieModelRenderer[]{bodyRear, bodyMid, bodyFront, neck1, neck2, neck3, neck4, head};

        float globalSpeed = 0.5F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;

        parModel.bob(bodyRear, globalSpeed * 1F, globalHeight * 0.8F, false, f, f1);

        parModel.bob(leftThigh, globalSpeed * 1F, globalHeight * 0.8F, false, f, f1);
        parModel.bob(rightThigh, globalSpeed * 1F, globalHeight * 0.8F, false, f, f1);

        parModel.chainWave(body, globalSpeed * 1F, globalHeight * -0.02F, -3, f, f1);
        parModel.chainWave(tail, globalSpeed * 1F, globalHeight * 0.05F, -2, f, f1);

        parModel.walk(rightThigh, globalSpeed * 0.5F, globalDegree * 0.8F, true, 0F, 0.2F, f, f1);
        parModel.walk(leftThigh, globalSpeed * 0.5F, globalDegree * 0.8F, false, 0F, 0.2F, f, f1);

        parModel.walk(leftCalf1, globalSpeed * 0.5F, globalDegree * 1F, false, -1.3F, 0.4F, f, f1);
        parModel.walk(rightCalf1, globalSpeed * 0.5F, globalDegree * 1F, true, -1.3F, 0.4F, f, f1);

        parModel.walk(leftCalf2, globalSpeed * 0.5F, globalDegree * 1F, true, -2F, 0F, f, f1);
        parModel.walk(rightCalf2, globalSpeed * 0.5F, globalDegree * 1F, false, -2F, 0F, f, f1);

        parModel.walk(leftFoot, globalSpeed * 0.5F, globalDegree * 1.7F, false, -0.8F, 0.5F, f, f1);
        parModel.walk(rightFoot, globalSpeed * 0.5F, globalDegree * 1.7F, true, -0.8F, 0.5F, f, f1);

        parModel.chainWave(armRight, globalSpeed * 1F, globalHeight * -0.25F, -3, f, f1);
        parModel.chainWave(armLeft, globalSpeed * 1F, globalHeight * -0.25F, -3, f, f1);

        parModel.walk(head, 1F * globalSpeed, 0.15F, true, 0F, -0.2F, f, f1);
        parModel.walk(neck1, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        parModel.walk(neck2, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        parModel.walk(neck3, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        parModel.walk(neck4, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);

        int ticksExisted = parEntity.ticksExisted;

        parModel.chainWave(tail, 0.1F, 0.05F, -2, ticksExisted, 1F);
        parModel.chainWave(body, 0.1F, 0.03F, -5, ticksExisted, 1F);
        parModel.chainWave(armRight, 0.1F, 0.1F, -4, ticksExisted, 1F);
        parModel.chainWave(armLeft, 0.1F, 0.1F, -4, ticksExisted, 1F);

//        parModel.faceTarget(head, 3, rotationYaw, rotationPitch);
//        parModel.faceTarget(neck1, 3, rotationYaw, rotationPitch);
//        parModel.faceTarget(neck2, 3, rotationYaw, rotationPitch);

        ((EntityMajungasaurus) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
