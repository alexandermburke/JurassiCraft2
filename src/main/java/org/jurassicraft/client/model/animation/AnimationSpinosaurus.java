package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurSpinosaurus;
import org.jurassicraft.common.entity.EntitySpinosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationSpinosaurus extends DinosaurAnimator
{
    public AnimationSpinosaurus()
    {
        super(new DinosaurSpinosaurus());
    }

    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        float globalSpeed = 0.45F;
        float globalDegree = 0.4F;
        float height = 1.0F;

        // middle
        MowzieModelRenderer shoulders = parModel.getCube("Body 3");
        MowzieModelRenderer chest = parModel.getCube("Body 2");
        MowzieModelRenderer waist = parModel.getCube("Body 1");

        // right feet
        MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");
        MowzieModelRenderer rightCalf = parModel.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = parModel.getCube("Right Calf 2");
        MowzieModelRenderer rightFoot = parModel.getCube("Foot Right");

        // left feet
        MowzieModelRenderer leftThigh = parModel.getCube("Left Thigh");
        MowzieModelRenderer leftCalf = parModel.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = parModel.getCube("Left Calf 2");
        MowzieModelRenderer leftFoot = parModel.getCube("Foot Left");

        // neck
        MowzieModelRenderer neck1 = parModel.getCube("Neck 1");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer neck4 = parModel.getCube("Neck 4");
        MowzieModelRenderer neck5 = parModel.getCube("Neck 5");
        MowzieModelRenderer neck6 = parModel.getCube("Neck Under 1");
        MowzieModelRenderer neck7 = parModel.getCube("Neck Under 2");

        // head
        MowzieModelRenderer head = parModel.getCube("Head");

        // arms
        MowzieModelRenderer lowerArmLeft = parModel.getCube("Lower Arm LEFT");
        MowzieModelRenderer upperArmLeft = parModel.getCube("Upper Arm LEFT");
        MowzieModelRenderer upperArmRight = parModel.getCube("Upper Arm Right");
        MowzieModelRenderer lowerArmRight = parModel.getCube("Lower Arm Right");

        // hands
        MowzieModelRenderer handLeft = parModel.getCube("hand left");
        MowzieModelRenderer handRight = parModel.getCube("hand right");

        // tail
        MowzieModelRenderer tail1 = parModel.getCube("Tail 1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");

        // teeth
        MowzieModelRenderer teeth = parModel.getCube("Teeth");
        MowzieModelRenderer teethFront = parModel.getCube("Teeth front");

        // jaw
        MowzieModelRenderer upperJaw1 = parModel.getCube("Upper Jaw1");
        MowzieModelRenderer upperJaw2 = parModel.getCube("Upper Jaw2");
        MowzieModelRenderer upperJaw3 = parModel.getCube("Upper Jaw3");
        MowzieModelRenderer upperJawFront = parModel.getCube("Upper Jaw front");
        MowzieModelRenderer lowerJaw = parModel.getCube("Lower jaw");
        MowzieModelRenderer lowerJawFront = parModel.getCube("Lower jaw front");

        //throat
        MowzieModelRenderer throat1 = parModel.getCube("Neck Under 1");
        MowzieModelRenderer throat2 = parModel.getCube("Neck Under 2");

        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[]{handRight, lowerArmRight, upperArmRight};
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[]{handLeft, lowerArmLeft, upperArmLeft};
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[]{head, neck1, neck2, neck3, neck4, neck5, shoulders, chest, waist};
        MowzieModelRenderer[] bottomJaw = new MowzieModelRenderer[]{lowerJawFront, lowerJaw};

        // Body animations
        parModel.bob(waist, 1F * globalSpeed, height, false, f, f1);
        parModel.bob(leftThigh, 1F * globalSpeed, height, false, f, f1);
        parModel.bob(rightThigh, 1F * globalSpeed, height, false, f, f1);
        leftThigh.rotationPointY -= -2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        parModel.chainWave(bodyParts, 1F * globalSpeed, 0.05F, 3, f, f1);
        parModel.chainWave(tailParts, 1F * globalSpeed, height * 0.05F, 3, f, f1);
        parModel.chainSwing(tailParts, 0.5F * globalSpeed, height * -0.05F, 2, f, f1);
        parModel.chainWave(leftArmParts, 1F * globalSpeed, height * 0.05F, 3, f, f1);
        parModel.chainWave(rightArmParts, 1F * globalSpeed, height * 0.05F, 3, f, f1);

        parModel.walk(head, 1F * globalSpeed, 0.1F, true, 0F, -0.2F, f, f1);
        parModel.walk(neck1, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);
        parModel.walk(neck2, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);
        parModel.walk(neck3, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);
        parModel.walk(neck4, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);
        parModel.walk(neck5, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);

        parModel.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.2F, f, f1);
        parModel.walk(leftCalf, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        parModel.walk(leftCalf2, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        parModel.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.1F, f, f1);

        parModel.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.2F, f, f1);
        parModel.walk(rightCalf, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        parModel.walk(rightCalf2, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        parModel.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.1F, f, f1);

        // idling
        parModel.chainWave(tailParts, 0.1F, 0.05F, 2, parEntity.ticksExisted, 1F);
        parModel.chainWave(bodyParts, 0.1F, -0.03F, 4, parEntity.ticksExisted, 1F);
        parModel.chainWave(rightArmParts, 0.1F, -0.1F, 4, parEntity.ticksExisted, 1F);
        parModel.chainWave(leftArmParts, 0.1F, -0.1F, 4, parEntity.ticksExisted, 1F);

        ((EntitySpinosaurus) parEntity).tailBuffer.applyChainSwingBuffer(tailParts);
    }
}