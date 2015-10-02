package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurParasaurolophus;
import org.jurassicraft.common.entity.EntityParasaurolophus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationParasaurolophus extends DinosaurAnimator
{
    public AnimationParasaurolophus()
    {
        super(new DinosaurParasaurolophus());
    }

    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        MowzieModelRenderer head = parModel.getCube("Head");

        MowzieModelRenderer neck1 = parModel.getCube("Neck");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");

        // body parts
        MowzieModelRenderer stomach = parModel.getCube("Body 1");
        MowzieModelRenderer shoulders = parModel.getCube("Body 2");
        MowzieModelRenderer waist = parModel.getCube("Body 3");

        // tail parts
        MowzieModelRenderer tail1 = parModel.getCube("Tail 1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");

        // left foot
        MowzieModelRenderer leftThigh = parModel.getCube("Left Thigh");
        MowzieModelRenderer leftCalf = parModel.getCube("Left Calf 1");
        MowzieModelRenderer leftUpperFoot = parModel.getCube("Left Upper Foot");
        MowzieModelRenderer leftFoot = parModel.getCube("Foot Left");

        // right foot
        MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");
        MowzieModelRenderer rightCalf = parModel.getCube("Right Calf 1");
        MowzieModelRenderer rightUpperFoot = parModel.getCube("Right Upper Foot");
        MowzieModelRenderer rightFoot = parModel.getCube("Foot Right");

        // right arm
        MowzieModelRenderer upperArmRight = parModel.getCube("Upper Arm Right");
        MowzieModelRenderer lowerArmRight = parModel.getCube("Lower Arm Right");
        MowzieModelRenderer rightHand = parModel.getCube("Right Hand");
        MowzieModelRenderer rightFingers = parModel.getCube("Right Fingers");

        // left arm
        MowzieModelRenderer upperArmLeft = parModel.getCube("Upper Arm Left");
        MowzieModelRenderer lowerArmLeft = parModel.getCube("Lower Arm Left");
        MowzieModelRenderer leftHand = parModel.getCube("Left Hand");
        MowzieModelRenderer leftFingers = parModel.getCube("Left Fingers");

        MowzieModelRenderer jaw = parModel.getCube("Jaw");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};

        float scaleFactor = 0.6F;
        float height = 2F;
        float allFoursLean = (float) (Math.pow(f1, 1 / (f1 * 10)) / 4);

        if (allFoursLean > 0.15F)
            allFoursLean = 0.15F;

        // All fours behavior
        waist.rotateAngleX += allFoursLean * 1.5;
        shoulders.rotateAngleX -= allFoursLean / 5;
        neck1.rotateAngleX -= 2 * allFoursLean / 5 * 1.5;
        head.rotateAngleX -= 2 * allFoursLean / 5 * 1.5;
        tail1.rotateAngleX -= 2 * allFoursLean / 7;
        tail2.rotateAngleX -= 1 * allFoursLean / 7;
        tail3.rotateAngleX -= 1 * allFoursLean / 7;
        tail4.rotateAngleX -= 1 * allFoursLean / 7;
        tail5.rotateAngleX -= 1 * allFoursLean / 7;
        tail6.rotateAngleX -= 1 * allFoursLean / 7;
        upperArmLeft.rotateAngleX -= allFoursLean * 4;
        upperArmRight.rotateAngleX -= allFoursLean * 4;
        lowerArmLeft.rotateAngleX += allFoursLean * 4;
        lowerArmRight.rotateAngleX += allFoursLean * 4;
        leftHand.rotateAngleX -= allFoursLean * 12;
        rightHand.rotateAngleX -= allFoursLean * 12;

        parModel.bob(waist, 1F * scaleFactor, 1F * height, false, f, f1);
        parModel.bob(leftThigh, 1F * scaleFactor, 1F * height, false, f, f1);
        parModel.bob(rightThigh, 1F * scaleFactor, 1F * height, false, f, f1);

        parModel.walk(neck1, 1F * scaleFactor, 0.15F * height, false, 1F, 0F, f, f1);
        parModel.walk(head, 1F * scaleFactor, 0.15F * height, true, 1F, 0F, f, f1);

        parModel.walk(leftThigh, 0.5F * scaleFactor, 0.5F, false, 0F, 0.3F, f, f1);
        parModel.walk(leftCalf, 0.5F * scaleFactor, 0.5F, true, 2F, 0F, f, f1);
        parModel.walk(leftUpperFoot, 0.5F * scaleFactor, 0.7F, false, 0F, -0.4F, f, f1);
        parModel.walk(leftFoot, 0.5F * scaleFactor, 1F, true, 0.5F, 1F, f, f1);

        parModel.walk(rightThigh, 0.5F * scaleFactor, 0.5F, true, 0F, 0.3F, f, f1);
        parModel.walk(rightCalf, 0.5F * scaleFactor, 0.5F, false, 2F, 0F, f, f1);
        parModel.walk(rightUpperFoot, 0.5F * scaleFactor, 0.7F, true, 0F, -0.4F, f, f1);
        parModel.walk(rightFoot, 0.5F * scaleFactor, 1F, false, 0.5F, 1F, f, f1);

        float frontOffset = 1.3F;
        parModel.walk(upperArmLeft, 0.5F * scaleFactor, 1F, false, -0.5F - frontOffset, 0F, f, f1);
        parModel.walk(lowerArmLeft, 0.5F * scaleFactor, 1F, true, -1F - frontOffset, 0F, f, f1);
        parModel.walk(leftHand, 0.5F * scaleFactor, 0.5F, false, -1F - frontOffset, 0F, f, f1);

        parModel.walk(upperArmRight, 0.5F * scaleFactor, 1F, true, -0.5F - frontOffset, 0F, f, f1);
        parModel.walk(lowerArmRight, 0.5F * scaleFactor, 1F, false, -1F - frontOffset, 0F, f, f1);
        parModel.walk(rightHand, 0.5F * scaleFactor, 0.5F, true, -1F - frontOffset, 0F, f, f1);

        parModel.chainWave(tail, 1F * scaleFactor, -0.1F, 2, f, f1);
        parModel.chainSwing(tail, 0.5F * scaleFactor, 0.1F, 2, f, f1);

        // Idle
        int ticksExisted = parEntity.ticksExisted;

        parModel.walk(neck1, 0.1F, 0.07F, false, -1F, 0F, ticksExisted, 1F);
        parModel.walk(head, 0.1F, 0.07F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(waist, 0.1F, 0.04F, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(upperArmRight, 0.1F, 0.1F, false, -1F, 0F, ticksExisted, 1F);
        parModel.walk(upperArmLeft, 0.1F, 0.1F, false, -1F, 0F, ticksExisted, 1F);
        parModel.walk(lowerArmRight, 0.1F, 0.1F, true, -1.5F, 0F, ticksExisted, 1F);
        parModel.walk(lowerArmLeft, 0.1F, 0.1F, true, -1.5F, 0F, ticksExisted, 1F);
        parModel.walk(rightHand, 0.1F, 0.1F, false, -2F, 0F, ticksExisted, 1F);
        parModel.walk(leftHand, 0.1F, 0.1F, false, -2F, 0F, ticksExisted, 1F);

        parModel.chainWave(tail, 0.1F, -0.02F, 2, ticksExisted, 1F);

        ((EntityParasaurolophus) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
