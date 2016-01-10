package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityParasaurolophus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationParasaurolophus extends DinosaurAnimator
{
    public AnimationParasaurolophus()
    {
        super(JCEntityRegistry.parasaurolophus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer neck1 = model.getCube("Neck");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");

//         body parts
        MowzieModelRenderer stomach = model.getCube("Body 1");
        MowzieModelRenderer shoulders = model.getCube("Body 2");
        MowzieModelRenderer waist = model.getCube("Body 3");

//         tail parts
        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

//         left foot
        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer leftCalf = model.getCube("Left Calf 1");
        MowzieModelRenderer leftUpperFoot = model.getCube("Left Upper Foot");
        MowzieModelRenderer leftFoot = model.getCube("Foot Left");

//         right foot
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");
        MowzieModelRenderer rightCalf = model.getCube("Right Calf 1");
        MowzieModelRenderer rightUpperFoot = model.getCube("Right Upper Foot");
        MowzieModelRenderer rightFoot = model.getCube("Foot Right");

        // right arm
        MowzieModelRenderer upperArmRight = model.getCube("Upper Arm Right");
        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
        MowzieModelRenderer rightHand = model.getCube("Right Hand");
        MowzieModelRenderer rightFingers = model.getCube("Right Fingers");

        // left arm
        MowzieModelRenderer upperArmLeft = model.getCube("Upper Arm Left");
        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm Left");
        MowzieModelRenderer leftHand = model.getCube("Left Hand");
        MowzieModelRenderer leftFingers = model.getCube("Left Fingers");

        MowzieModelRenderer jaw = model.getCube("Jaw");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        float scaleFactor = 0.6F;
        float height = 2F;
        float allFoursLean = (float) (Math.pow(f1, 1 / (f1 * 10)) / 4);

        if (allFoursLean > 0.15F)
        {
            allFoursLean = 0.15F;
        }

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

        model.bob(waist, 1F * scaleFactor, 1F * height, false, f, f1);
        model.bob(leftThigh, 1F * scaleFactor, 1F * height, false, f, f1);
        model.bob(rightThigh, 1F * scaleFactor, 1F * height, false, f, f1);

        model.walk(neck1, 1F * scaleFactor, 0.15F * height, false, 1F, 0F, f, f1);
        model.walk(head, 1F * scaleFactor, 0.15F * height, true, 1F, 0F, f, f1);

        model.walk(leftThigh, 0.5F * scaleFactor, 0.5F, false, 0F, 0.3F, f, f1);
        model.walk(leftCalf, 0.5F * scaleFactor, 0.5F, true, 2F, 0F, f, f1);
        model.walk(leftUpperFoot, 0.5F * scaleFactor, 0.7F, false, 0F, -0.4F, f, f1);
        model.walk(leftFoot, 0.5F * scaleFactor, 1F, true, 0.5F, 1F, f, f1);

        model.walk(rightThigh, 0.5F * scaleFactor, 0.5F, true, 0F, 0.3F, f, f1);
        model.walk(rightCalf, 0.5F * scaleFactor, 0.5F, false, 2F, 0F, f, f1);
        model.walk(rightUpperFoot, 0.5F * scaleFactor, 0.7F, true, 0F, -0.4F, f, f1);
        model.walk(rightFoot, 0.5F * scaleFactor, 1F, false, 0.5F, 1F, f, f1);

        float frontOffset = 1.3F;
        model.walk(upperArmLeft, 0.5F * scaleFactor, 1F, false, -0.5F - frontOffset, 0F, f, f1);
        model.walk(lowerArmLeft, 0.5F * scaleFactor, 1F, true, -1F - frontOffset, 0F, f, f1);
        model.walk(leftHand, 0.5F * scaleFactor, 0.5F, false, -1F - frontOffset, 0F, f, f1);

        model.walk(upperArmRight, 0.5F * scaleFactor, 1F, true, -0.5F - frontOffset, 0F, f, f1);
        model.walk(lowerArmRight, 0.5F * scaleFactor, 1F, false, -1F - frontOffset, 0F, f, f1);
        model.walk(rightHand, 0.5F * scaleFactor, 0.5F, true, -1F - frontOffset, 0F, f, f1);

        model.chainWave(tail, 1F * scaleFactor, -0.1F, 2, f, f1);
        model.chainSwing(tail, 0.5F * scaleFactor, 0.1F, 2, f, f1);

        // Idle
        int ticksExisted = parEntity.ticksExisted;

        model.walk(neck1, 0.1F, 0.07F, false, -1F, 0F, ticksExisted, 1F);
        model.walk(head, 0.1F, 0.07F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(waist, 0.1F, 0.04F, false, 0F, 0F, ticksExisted, 1F);
        model.walk(upperArmRight, 0.1F, 0.1F, false, -1F, 0F, ticksExisted, 1F);
        model.walk(upperArmLeft, 0.1F, 0.1F, false, -1F, 0F, ticksExisted, 1F);
        model.walk(lowerArmRight, 0.1F, 0.1F, true, -1.5F, 0F, ticksExisted, 1F);
        model.walk(lowerArmLeft, 0.1F, 0.1F, true, -1.5F, 0F, ticksExisted, 1F);
        model.walk(rightHand, 0.1F, 0.1F, false, -2F, 0F, ticksExisted, 1F);
        model.walk(leftHand, 0.1F, 0.1F, false, -2F, 0F, ticksExisted, 1F);

        model.chainWave(tail, 0.1F, -0.02F, 2, ticksExisted, 1F);

        ((EntityParasaurolophus) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
