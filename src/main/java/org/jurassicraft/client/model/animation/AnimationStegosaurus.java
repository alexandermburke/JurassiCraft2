package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurStegosaurus;
import org.jurassicraft.common.entity.EntityStegosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationStegosaurus extends DinosaurAnimator
{
    public AnimationStegosaurus()
    {
        super(new DinosaurStegosaurus());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = parModel.animator;

//        f = entity.ticksExisted;
//        f1 = 0.25F;

        MowzieModelRenderer head = parModel.getCube("Head");

        MowzieModelRenderer upperJaw = parModel.getCube("Upper Jaw");
        MowzieModelRenderer lowerJaw = parModel.getCube("Lower Jaw");

        MowzieModelRenderer neck1 = parModel.getCube("Neck");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");

        MowzieModelRenderer tail1 = parModel.getCube("Tail 1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");

        MowzieModelRenderer rearBody = parModel.getCube("Body REAR");
        MowzieModelRenderer hips = parModel.getCube("Body hips");
        MowzieModelRenderer bodyFrontBase = parModel.getCube("Body MAIN");
        MowzieModelRenderer shoulders = parModel.getCube("Body shoulders");
        MowzieModelRenderer bodyFront = parModel.getCube("Body FRONT");

        MowzieModelRenderer thighRight = parModel.getCube("RearLeg Upper Right");
        MowzieModelRenderer thighLeft = parModel.getCube("RearLeg Upper Left");

        MowzieModelRenderer calfRight = parModel.getCube("RearLeg Middle Right");
        MowzieModelRenderer calfLeft = parModel.getCube("RearLeg Middle Left");

        MowzieModelRenderer footRight = parModel.getCube("RearLeg Foot Right");
        MowzieModelRenderer footLeft = parModel.getCube("RearLeg Foot Left");

        MowzieModelRenderer armUpperRight = parModel.getCube("FrontLeg Upper Right");
        MowzieModelRenderer armUpperLeft = parModel.getCube("FrontLeg Upper Left");

        MowzieModelRenderer armLowerRight = parModel.getCube("FrontLeg MID Right");
        MowzieModelRenderer armLowerLeft = parModel.getCube("FrontLeg MID Left");

        MowzieModelRenderer handRight = parModel.getCube("FrontLeg FOOT Right");
        MowzieModelRenderer handLeft = parModel.getCube("FrontLeg FOOT Left");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};

        float scaleFactor = 0.5F;
        float height = 0.8F;
        float frontOffset = -2F;

//        parModel.faceTarget(head, 3, rotationYaw, rotationPitch);
//        parModel.faceTarget(neck3, 3, rotationYaw, rotationPitch);
//        parModel.faceTarget(neck1, 3, rotationYaw, rotationPitch);

        parModel.bob(hips, 2 * scaleFactor, height, false, f, f1);
        parModel.bob(thighLeft, 2 * scaleFactor, height, false, f, f1);
        parModel.bob(thighRight, 2 * scaleFactor, height, false, f, f1);
        parModel.walk(hips, 2 * scaleFactor, 0.1F * height, true, -1.5F, 0F, f, f1);
        parModel.walk(neck1, 2 * scaleFactor, 0.07F * height, false, -0.5F, 0F, f, f1);
        parModel.walk(neck3, 2 * scaleFactor, 0.07F * height, false, 0.5F, 0F, f, f1);
        parModel.walk(head, 2 * scaleFactor, 0.07F * height, true, 1.5F, 0F, f, f1);

        parModel.walk(thighLeft, 1F * scaleFactor, 0.8F, false, 0F, 0F, f, f1);
        parModel.walk(calfLeft, 1F * scaleFactor, 0.8F, true, 1F, 0F, f, f1);
        parModel.walk(footLeft, 1F * scaleFactor, 0.8F, false, 1.5F, 0F, f, f1);

        parModel.walk(thighRight, 1F * scaleFactor, 0.8F, true, 0F, 0F, f, f1);
        parModel.walk(calfRight, 1F * scaleFactor, 0.8F, false, 1F, 0F, f, f1);
        parModel.walk(footRight, 1F * scaleFactor, 0.8F, true, 1.5F, 0F, f, f1);

        parModel.walk(armUpperRight, 1F * scaleFactor, 0.8F, true, frontOffset + 0F, -0.1F, f, f1);
        parModel.walk(armLowerRight, 1F * scaleFactor, 0.6F, true, frontOffset + 1F, -0.2F, f, f1);
        parModel.walk(handRight, 1F * scaleFactor, 0.8F, false, frontOffset + 1.5F, 0F, f, f1);

        parModel.walk(armUpperLeft, 1F * scaleFactor, 0.8F, false, frontOffset + 0F, -0.1F, f, f1);
        parModel.walk(armLowerLeft, 1F * scaleFactor, 0.6F, false, frontOffset + 1F, -0.2F, f, f1);
        parModel.walk(handLeft, 1F * scaleFactor, 0.8F, true, frontOffset + 1.5F, 0F, f, f1);

        // Idling
        int ticksExisted = parEntity.ticksExisted;

        parModel.walk(neck1, 0.1F, 0.04F, false, -1F, 0F, ticksExisted, 1F);
        parModel.walk(head, 0.1F, 0.07F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(neck3, 0.1F, 0.03F, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(hips, 0.1F, 0.025F, false, 0F, 0F, ticksExisted, 1F);

        float inverseKinematicsConstant = 0.6F;
        parModel.walk(armUpperRight, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(armLowerRight, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(handRight, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(armUpperLeft, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(armLowerLeft, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(handLeft, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        armUpperLeft.rotationPointZ -= 0.5 * Math.cos(ticksExisted * 0.1F);
        armUpperRight.rotationPointZ -= 0.5 * Math.cos(ticksExisted * 0.1F);

        parModel.chainSwing(tail, 0.1F, 0.2F, 3, ticksExisted, 1.0F);
        parModel.chainWave(tail, 0.1F, -0.05F, 1, ticksExisted, 1.0F);

        ((EntityStegosaurus) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
