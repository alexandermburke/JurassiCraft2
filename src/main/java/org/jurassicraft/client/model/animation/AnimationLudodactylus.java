package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurLudodactylus;
import org.jurassicraft.common.entity.EntityLudodactylus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationLudodactylus extends DinosaurAnimator
{
    public AnimationLudodactylus()
    {
        super(new DinosaurLudodactylus());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityLudodactylus entity = (EntityLudodactylus) parEntity;

        MowzieModelRenderer leftThigh = parModel.getCube("Left thigh");
        MowzieModelRenderer leftCalf = parModel.getCube("Left calf");
        MowzieModelRenderer leftUpperFoot = parModel.getCube("Left upper foot");
        MowzieModelRenderer leftFoot = parModel.getCube("Left foot");
        MowzieModelRenderer rightThigh = parModel.getCube("Right thigh");
        MowzieModelRenderer rightCalf = parModel.getCube("right calf");
        MowzieModelRenderer rightUpperFoot = parModel.getCube("Right upper foot");
        MowzieModelRenderer rightFoot = parModel.getCube("Right foot");
        MowzieModelRenderer jaw = parModel.getCube("Lower jaw 1");
        MowzieModelRenderer head = parModel.getCube("Head");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck1 = parModel.getCube("Neck 1");
        MowzieModelRenderer body3 = parModel.getCube("Body 3");
        MowzieModelRenderer body2 = parModel.getCube("Body 2");
        MowzieModelRenderer body1 = parModel.getCube("Body 1");
        MowzieModelRenderer tail1 = parModel.getCube("Tail 1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer leftArm1 = parModel.getCube("Left Arm 1");
        MowzieModelRenderer leftArm2 = parModel.getCube("Left Arm 2");
        MowzieModelRenderer leftArm3 = parModel.getCube("Left Arm 3");
        MowzieModelRenderer leftArm4 = parModel.getCube("Left Arm 4");
        MowzieModelRenderer rightArm1 = parModel.getCube("Right Arm 1");
        MowzieModelRenderer rightArm2 = parModel.getCube("Right Arm 2");
        MowzieModelRenderer rightArm3 = parModel.getCube("Right Arm 3");
        MowzieModelRenderer rightArm4 = parModel.getCube("Right Arm 4");

        MowzieModelRenderer[] neck = new MowzieModelRenderer[]{head, neck3, neck2, neck1};
        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail1, tail2, tail3};
        MowzieModelRenderer[] wingLeft = new MowzieModelRenderer[]{leftArm4, leftArm3, leftArm2, leftArm1};
        MowzieModelRenderer[] wingRight = new MowzieModelRenderer[]{rightArm4, rightArm3, rightArm2, rightArm1};
        MowzieModelRenderer[] legLeft = new MowzieModelRenderer[]{leftThigh, leftCalf, leftUpperFoot, leftFoot};
        MowzieModelRenderer[] legRight = new MowzieModelRenderer[]{rightThigh, rightCalf, rightUpperFoot, rightFoot};

        f = entity.ticksExisted;
        f1 = 1.0f;

        float globalSpeed = 0.45F;
        float globalDegree = 2F;
        float globalHeight = 2F;
        float frontOffset = -1.35f;

//        model.bob(body1, 1 * globalSpeed, 1 * globalHeight, false, f, f1);
//        model.bob(leftThigh, 1 * globalSpeed, 1 * globalHeight, false, f, f1);
//        model.bob(rightThigh, 1 * globalSpeed, 1 * globalHeight, false, f, f1);
//        model.walk(body1, 1 * globalSpeed, -0.08f * globalHeight, false, 0, 0.1f, f, f1);
//        model.walk(leftArm1, 1 * globalSpeed, -0.08f * globalHeight, true, 0, 0, f, f1);
//        model.walk(rightArm1, 1 * globalSpeed, -0.08f * globalHeight, true, 0, 0, f, f1);
//        model.chainWave(neck, 1 * globalSpeed, -0.15f * globalHeight, 4, f, f1);
//        model.chainWave(tail, 1 * globalSpeed, 0.1f * globalHeight, 1, f, f1);
//
//        model.walk(leftThigh, 0.5F * globalSpeed, 0.7F * globalDegree, false, 3.14F, 0.2F, f, f1);
//        model.walk(leftCalf, 0.5F * globalSpeed, 0.6F * globalDegree, false, 1.5F, 0.3F, f, f1);
//        model.walk(leftUpperFoot, 0.5F * globalSpeed, 0.8F * globalDegree, false, -2F, -0.4F, f, f1);
//        model.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, -2F, 2.3F, f, f1);
//
//        model.walk(rightThigh, 0.5F * globalSpeed, 0.7F * globalDegree, true, 3.14F, 0.2F, f, f1);
//        model.walk(rightCalf, 0.5F * globalSpeed, 0.6F * globalDegree, true, 1.5F, 0.3F, f, f1);
//        model.walk(rightUpperFoot, 0.5F * globalSpeed, 0.8F * globalDegree, true, -2F, -0.4F, f, f1);
//        model.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, -2F, 2.3F, f, f1);
//
//        model.walk(leftArm1, 0.5F * globalSpeed, 0.5F * globalDegree, true, -3.14F + frontOffset, 0.5F, f, f1);
//        model.walk(leftArm2, 0.5F * globalSpeed, 0.4F * globalDegree, true, -1.5F + frontOffset, -0.3F, f, f1);
//        model.walk(leftArm3, 0.5F * globalSpeed, 0.7F * globalDegree, true, 2F + frontOffset, 0.4F, f, f1);
//
//        model.walk(rightArm1, 0.5F * globalSpeed, 0.5F * globalDegree, false, -3.14F + frontOffset, 0.5F, f, f1);
//        model.walk(rightArm2, 0.5F * globalSpeed, 0.4F * globalDegree, false, -1.5F + frontOffset, -0.3F, f, f1);
//        model.walk(rightArm3, 0.5F * globalSpeed, 0.7F * globalDegree, false, 2F + frontOffset, 0.4F, f, f1);

        int frame = entity.ticksExisted;
        parModel.walk(body1, 0.08f, -0.05f, false, 0, 0, frame, 1);
        parModel.chainWave(neck, 0.08f, 0.03f, 2, frame, 1);
        parModel.walk(leftArm1, 0.08f, 0.1f, false, 0, 0, frame, 1);
        parModel.walk(rightArm1, 0.08f, 0.1f, false, 0, 0, frame, 1);
        parModel.walk(leftArm2, 0.08f, 0.1f, false, 0, 0, frame, 1);
        parModel.walk(rightArm2, 0.08f, 0.1f, false, 0, 0, frame, 1);
        parModel.walk(leftArm3, 0.08f, 0.2f, true, 0, 0, frame, 1);
        parModel.walk(rightArm3, 0.08f, 0.2f, true, 0, 0, frame, 1);
        parModel.flap(leftArm1, 0.08f, 0.03f, false, 0, 0, frame, 1);
        parModel.flap(rightArm1, 0.08f, 0.03f, true, 0, 0, frame, 1);
        leftArm1.rotationPointZ -= 1 * Math.cos(frame * 0.08);
        rightArm1.rotationPointZ -= 1 * Math.cos(frame * 0.08);

        //Flying
        body1.rotateAngleX += 0.3;
        neck1.rotateAngleX -= 0.1;
        leftThigh.rotateAngleX += 0.8;
        rightThigh.rotateAngleX += 0.8;
        leftCalf.rotateAngleX += 0.7;
        rightCalf.rotateAngleX += 0.7;
        leftUpperFoot.rotateAngleX -= 0.3;
        rightUpperFoot.rotateAngleX -= 0.3;
        leftFoot.rotateAngleX += 2;
        rightFoot.rotateAngleX += 2;
        leftArm1.rotateAngleZ -= 1;
        leftArm2.rotateAngleZ -= 0.4;
        leftArm3.rotateAngleZ -= 0.1;
        leftArm4.rotateAngleZ += 3.3;
        leftArm4.rotateAngleY += 2.6;
        leftArm4.rotateAngleX += 1.2;
        rightArm1.rotateAngleZ += 1;
        rightArm2.rotateAngleZ += 0.4;
        rightArm3.rotateAngleZ += 0.1;
        rightArm4.rotateAngleZ -= 3.3;
        rightArm4.rotateAngleY -= 2.6;
        rightArm4.rotateAngleX += 1.2;

        parModel.bob(body1, 0.3f, 7, false, f, f1);
        parModel.bob(leftThigh, 0.3f, 7, false, f, f1);
        parModel.bob(rightThigh, 0.3f, 7, false, f, f1);
        parModel.walk(body1, 0.3f, 0.2f, true, 1, 0, f, f1);
        parModel.swing(leftArm1, 0.3f, 0.2f, false, 1, 0, f, f1);
        parModel.swing(leftArm2, 0.3f, 0.2f, false, 1, 0, f, f1);
        parModel.walk(neck1, 0.3f, 0.2f, false, 1, 0.2f, f, f1);
        parModel.walk(head, 0.3f, 0.2f, true, 1, -0.4f, f, f1);

        parModel.chainFlap(wingLeft, 0.3f, 0.8f, 2, f, f1);
        parModel.walk(leftArm1, 0.3f, 0.6f, false, -1f, -0.2f, f, f1);
        parModel.walk(leftArm2, 0.3f, 1.2f, true, -1f, 0, f, f1);
        parModel.walk(leftArm3, 0.3f, 0.7f, false, -1f, 0.2f, f, f1);
        parModel.chainFlap(wingRight, 0.3f, -0.8f, 2, f, f1);
        parModel.walk(rightArm1, 0.3f, 0.6f, false, -1f, -0.2f, f, f1);
        parModel.walk(rightArm2, 0.3f, 1.2f, true, -1f, 0, f, f1);
        parModel.walk(rightArm3, 0.3f, 0.7f, false, -1f, 0.2f, f, f1);
        parModel.chainWave(legLeft, 0.3f, 0.2f, -3, f, f1);
        parModel.chainWave(legRight, 0.3f, 0.2f, -3, f, f1);
        parModel.chainWave(tail, 0.3f, 0.2f, 1, f, f1);
        parModel.chainWave(neck, 0.3f, 0.4f, 4, f, f1);
    }
}
