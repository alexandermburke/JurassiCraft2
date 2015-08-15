package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityIndominusRex;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationIndominusRex implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

        //        f = entity.ticksExisted;
        //        f1 = 1F;

        float globalSpeed = 0.5F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");
        MowzieModelRenderer upperJaw = model.getCube("Upper Jaw");

        MowzieModelRenderer neck1 = model.getCube("Neck BASE");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");

        MowzieModelRenderer tail1 = model.getCube("Tail Base");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");
        MowzieModelRenderer tail7 = model.getCube("Tail 7");

        MowzieModelRenderer bodyFront = model.getCube("Body Front");
        MowzieModelRenderer bodyMid = model.getCube("Body Mid");
        MowzieModelRenderer bodyRear = model.getCube("Body Rear");

        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");

        MowzieModelRenderer leftCalf1 = model.getCube("Left Calf 1");
        MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");

        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");

        MowzieModelRenderer leftFoot = model.getCube("Foot Left");
        MowzieModelRenderer rightFoot = model.getCube("Foot Right");

        MowzieModelRenderer upperArmLeft = model.getCube("Arm UPPER LEFT");
        MowzieModelRenderer upperArmRight = model.getCube("Arm UPPER RIGHT");

        MowzieModelRenderer lowerArmLeft = model.getCube("Arm MID LEFT");
        MowzieModelRenderer lowerArmRight = model.getCube("Arm MID RIGHT");

        MowzieModelRenderer handLeft = model.getCube("Hand LEFT");
        MowzieModelRenderer handRight = model.getCube("Hand RIGHT");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail7, tail6, tail5, tail4, tail3, tail2, tail1 };
        MowzieModelRenderer[] body = new MowzieModelRenderer[] { head, neck4, neck3, neck2, neck1, bodyFront, bodyMid, bodyRear };

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { handLeft, lowerArmLeft, upperArmLeft };
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { handRight, lowerArmRight, upperArmRight };

        model.bob(bodyRear, globalSpeed * 1F, globalHeight * 1.0F, false, f, f1);

        model.bob(leftThigh, globalSpeed * 1F, globalHeight * 1.0F, false, f, f1);
        model.bob(rightThigh, globalSpeed * 1F, globalHeight * 1.0F, false, f, f1);

        model.chainWave(body, globalSpeed * 1F, globalHeight * 0.025F, 3, f, f1);
        model.chainWave(tail, globalSpeed * 1F, -globalHeight * 0.025F, 2, f, f1);
        model.chainSwing(tail, globalSpeed * 0.5F, globalHeight * 0.025F, 2, f, f1);

        model.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.4F, f, f1);
        model.walk(leftCalf1, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.1F, f, f1);
        model.walk(leftCalf2, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        model.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.1F, f, f1);

        model.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.4F, f, f1);
        model.walk(rightCalf1, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.1F, f, f1);
        model.walk(rightCalf2, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        model.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.1F, f, f1);

        leftThigh.rotationPointY += 2 * f1 * Math.cos(f * 0.5F * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5F * globalSpeed);

        model.chainWave(armRight, globalSpeed * 1F, globalHeight * 0.2F, 3, f, f1);
        model.chainWave(armLeft, globalSpeed * 1F, globalHeight * 0.2F, 3, f, f1);

        int ticksExisted = entity.ticksExisted;

        model.chainWave(tail, 0.1F, -0.05F, 2, ticksExisted, 1F);
        model.chainWave(body, 0.1F, 0.03F, 5, ticksExisted, 1F);
        model.chainWave(armRight, -0.1F, 0.1F, 4, ticksExisted, 1F);
        model.chainWave(armLeft, -0.1F, 0.1F, 4, ticksExisted, 1F);

        model.faceTarget(bodyMid, 6.0F, rotationYaw, rotationPitch);
        model.faceTarget(bodyFront, 6.0F, rotationYaw, rotationPitch);
        model.faceTarget(head, 3.0F, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 3.0F, rotationYaw, rotationPitch);

        ((EntityIndominusRex) entity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
