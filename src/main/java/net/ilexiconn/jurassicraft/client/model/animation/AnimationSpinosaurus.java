package net.ilexiconn.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.ChainBuffer;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class AnimationSpinosaurus implements IModelAnimator
{
    private ChainBuffer tailBuffer = new ChainBuffer(6);

    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {

        float globalSpeed = 0.45F;
        float globalDegree = 0.4F;
        float height = 1.0F;

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 10, 4.0F, (EntityLiving) entity);

        // middle
        MowzieModelRenderer shoulders = model.getCube("Body 3");
        MowzieModelRenderer chest = model.getCube("Body 2");
        MowzieModelRenderer waist = model.getCube("Body 1");

        // right feet
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");
        MowzieModelRenderer rightCalf = model.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
        MowzieModelRenderer rightFoot = model.getCube("Right foot");

        // left feet
        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer leftCalf = model.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");
        MowzieModelRenderer leftFoot = model.getCube("Left foot");

        // neck
        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");
        MowzieModelRenderer neck5 = model.getCube("Neck 5");
        MowzieModelRenderer neck6 = model.getCube("Neck Under 1");
        MowzieModelRenderer neck7 = model.getCube("Neck Under 2");

        // head
        MowzieModelRenderer head = model.getCube("Head");

        // arms
        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm LEFT");
        MowzieModelRenderer upperArmLeft = model.getCube("Upper Arm LEFT");
        MowzieModelRenderer upperArmRight = model.getCube("Upper Arm Right");
        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");

        // hands
        MowzieModelRenderer handLeft = model.getCube("hand left");
        MowzieModelRenderer handRight = model.getCube("hand right");

        // tail
        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        // teeth
        MowzieModelRenderer teeth = model.getCube("Teeth");
        MowzieModelRenderer teethFront = model.getCube("Teeth front");

        // jaw
        MowzieModelRenderer upperJaw1 = model.getCube("Upper Jaw1");
        MowzieModelRenderer upperJaw2 = model.getCube("Upper Jaw2");
        MowzieModelRenderer upperJaw3 = model.getCube("Upper Jaw3");
        MowzieModelRenderer upperJawFront = model.getCube("Upper Jaw front");
        MowzieModelRenderer lowerJaw = model.getCube("Lower jaw");
        MowzieModelRenderer lowerJawFront = model.getCube("Lower jaw front");

        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[]{handRight, lowerArmRight, upperArmRight};
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[]{handLeft, lowerArmLeft, upperArmLeft};
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[]{waist, chest, shoulders, neck5, neck4, neck3, neck2, neck1, head};
        MowzieModelRenderer[] bottomJaw = new MowzieModelRenderer[]{lowerJawFront, lowerJaw};

        // Body animations
        model.bob(waist, 1F * globalSpeed, height, false, f, f1);
        model.bob(leftThigh, 1F * globalSpeed, height, false, f, f1);
        model.bob(rightThigh, 1F * globalSpeed, height, false, f, f1);
        leftThigh.rotationPointY -= -2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        rightThigh.rotationPointY -= 2 *  f1 * Math.cos(f * 0.5 * globalSpeed);
        model.chainWave(bodyParts, 1F * globalSpeed, height * 0.05F, 3, f, f1);
        model.chainWave(tailParts, 1F * globalSpeed, height * 0.05F, 3, f, f1);

        // idling
        model.chainWave(tailParts, 0.1F, -0.05F, 2, entity.ticksExisted, 1F);
        model.chainWave(bodyParts, 0.1F, -0.03F, 5, entity.ticksExisted, 1F);
        model.chainWave(rightArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);
        model.chainWave(leftArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);

        model.faceTarget(head, 6, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 6, rotationYaw, rotationPitch);

        tailBuffer.applyChainSwingBuffer(tailParts);
    }
}