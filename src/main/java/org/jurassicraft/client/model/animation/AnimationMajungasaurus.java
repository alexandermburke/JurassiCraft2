package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.common.animation.Animator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityMajungasaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationMajungasaurus extends DinosaurAnimator
{
    public AnimationMajungasaurus()
    {
        super(JCEntityRegistry.majungasaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = model.animator;

        EntityMajungasaurus dino = (EntityMajungasaurus) parEntity;

        // f = dino.ticksExisted;
        // f1 = 1F;

        MowzieModelRenderer tail1 = model.getCube("Tail Base");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");

        MowzieModelRenderer leftCalf1 = model.getCube("Left Calf 1");
        MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");

        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");

        MowzieModelRenderer leftFoot = model.getCube("Foot Left");
        MowzieModelRenderer rightFoot = model.getCube("Foot Right");

        MowzieModelRenderer bodyRear = model.getCube("Body Rear");
        MowzieModelRenderer bodyMid = model.getCube("Body Mid");
        MowzieModelRenderer bodyFront = model.getCube("Body Front");

        MowzieModelRenderer neck1 = model.getCube("Neck BASE");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer upperJaw = model.getCube("Upper Jaw");
        MowzieModelRenderer lowerJaw = model.getCube("Lower jaw");

        MowzieModelRenderer upperArmRight = model.getCube("Upper Arm Right");
        MowzieModelRenderer upperArmLeft = model.getCube("Upper Arm LEFT");

        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm LEFT");

        MowzieModelRenderer handRight = model.getCube("Hand Right");
        MowzieModelRenderer handLeft = model.getCube("Hand LEFT");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail1, tail2, tail3, tail4, tail5, tail6 };

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { upperArmLeft, lowerArmLeft, handLeft };
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { upperArmRight, lowerArmRight, handRight };

        MowzieModelRenderer[] body = new MowzieModelRenderer[] { bodyRear, bodyMid, bodyFront, neck1, neck2, neck3, neck4, head };

        float globalSpeed = 0.5F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;

        model.bob(bodyRear, globalSpeed * 1F, globalHeight * 0.8F, false, f, f1);

        model.bob(leftThigh, globalSpeed * 1F, globalHeight * 0.8F, false, f, f1);
        model.bob(rightThigh, globalSpeed * 1F, globalHeight * 0.8F, false, f, f1);

        model.chainWave(body, globalSpeed * 1F, globalHeight * -0.02F, -3, f, f1);
        model.chainWave(tail, globalSpeed * 1F, globalHeight * 0.05F, -2, f, f1);

        model.walk(rightThigh, globalSpeed * 0.5F, globalDegree * 0.8F, true, 0F, 0.2F, f, f1);
        model.walk(leftThigh, globalSpeed * 0.5F, globalDegree * 0.8F, false, 0F, 0.2F, f, f1);

        model.walk(leftCalf1, globalSpeed * 0.5F, globalDegree * 1F, false, -1.3F, 0.4F, f, f1);
        model.walk(rightCalf1, globalSpeed * 0.5F, globalDegree * 1F, true, -1.3F, 0.4F, f, f1);

        model.walk(leftCalf2, globalSpeed * 0.5F, globalDegree * 1F, true, -2F, 0F, f, f1);
        model.walk(rightCalf2, globalSpeed * 0.5F, globalDegree * 1F, false, -2F, 0F, f, f1);

        model.walk(leftFoot, globalSpeed * 0.5F, globalDegree * 1.7F, false, -0.8F, 0.5F, f, f1);
        model.walk(rightFoot, globalSpeed * 0.5F, globalDegree * 1.7F, true, -0.8F, 0.5F, f, f1);

        model.chainWave(armRight, globalSpeed * 1F, globalHeight * -0.25F, -3, f, f1);
        model.chainWave(armLeft, globalSpeed * 1F, globalHeight * -0.25F, -3, f, f1);

        model.walk(head, 1F * globalSpeed, 0.15F, true, 0F, -0.2F, f, f1);
        model.walk(neck1, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck2, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck3, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck4, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);

        int ticksExisted = parEntity.ticksExisted;

        model.chainWave(tail, 0.1F, 0.05F, -2, ticksExisted, 1F);
        model.chainWave(body, 0.1F, 0.03F, -5, ticksExisted, 1F);
        model.chainWave(armRight, 0.1F, 0.1F, -4, ticksExisted, 1F);
        model.chainWave(armLeft, 0.1F, 0.1F, -4, ticksExisted, 1F);

        // model.faceTarget(head, 3, rotationYaw, rotationPitch);
        // model.faceTarget(neck1, 3, rotationYaw, rotationPitch);
        // model.faceTarget(neck2, 3, rotationYaw, rotationPitch);

        ((EntityMajungasaurus) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
