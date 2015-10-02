package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurTriceratops;
import org.jurassicraft.common.entity.EntityTriceratops;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationTriceratops extends DinosaurAnimator
{
    public AnimationTriceratops()
    {
        super(new DinosaurTriceratops());
    }

    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        MowzieModelRenderer head = parModel.getCube("Head");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck1 = parModel.getCube("Neck 1");
        MowzieModelRenderer shoulders = parModel.getCube("Body shoulders");
        MowzieModelRenderer stomach = parModel.getCube("Body MAIN");
        MowzieModelRenderer waist = parModel.getCube("Body hips");
        MowzieModelRenderer tail1 = parModel.getCube("Tail 1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");
        MowzieModelRenderer armUpperLeft = parModel.getCube("FrontLeg Upper Left");
        MowzieModelRenderer armLowerLeft = parModel.getCube("FrontLeg MID Left");
        MowzieModelRenderer handLeft = parModel.getCube("FrontLeg FOOT Left");
        MowzieModelRenderer armUpperRight = parModel.getCube("FrontLeg Upper Right");
        MowzieModelRenderer armLowerRight = parModel.getCube("FrontLeg MID Right");
        MowzieModelRenderer handRight = parModel.getCube("FrontLeg FOOT Right");
        MowzieModelRenderer thighLeft = parModel.getCube("RearLeg Upper Left");
        MowzieModelRenderer calfLeft = parModel.getCube("RearLeg Middle Left");
        MowzieModelRenderer footLeft = parModel.getCube("RearLeg Foot Left");
        MowzieModelRenderer thighRight = parModel.getCube("RearLeg Upper Right");
        MowzieModelRenderer calfRight = parModel.getCube("RearLeg Middle Right");
        MowzieModelRenderer footRight = parModel.getCube("RearLeg Foot Right");
        MowzieModelRenderer jaw = parModel.getCube("Jaw LOWER");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};

//        f = entity.ticksExisted;
//        f1 = 0.5F;

        float sprintModifier = (float) (1 / (1 + Math.exp(30 * (-f1 + 0.92))));
        float legOffsetModifier = 2.5F;
        float bobBase = 2F;
        if (sprintModifier >= 0.9)
            bobBase = 1F;

        float scaleFactor = 0.25F;
        float height = 1.0F;
        float frontOffset = -2F;
        float animationDegree = 2 - sprintModifier * 0.2F;

//        parModel.faceTarget(head, 2, rotationYaw, rotationPitch);
//        parModel.faceTarget(neck1, 2, rotationYaw, rotationPitch);

        parModel.bob(waist, bobBase * scaleFactor, height, false, f, f1);
        parModel.bob(thighLeft, bobBase * scaleFactor, height, false, f, f1);
        parModel.bob(thighRight, bobBase * scaleFactor, height, false, f, f1);
        parModel.walk(waist, bobBase * scaleFactor, 0.1F * height, true, -1.5F, 0F, f, f1);
        parModel.walk(head, bobBase * scaleFactor, 0.1F * height, false, -1.5F, 0F, f, f1);
        waist.rotateAngleX += 0.1 * sprintModifier;
        head.rotateAngleX += 0.6 * sprintModifier;

        parModel.walk(thighLeft, 1F * scaleFactor, 0.2F * animationDegree, false, 0F + sprintModifier * legOffsetModifier, 0F + sprintModifier * 0.2F, f, f1);
        parModel.walk(thighRight, 1F * scaleFactor, 0.2F * animationDegree - sprintModifier * 0.1F, true, 1F + sprintModifier * legOffsetModifier, 0F, f, f1);
        parModel.walk(footLeft, 1F * scaleFactor, 0.2F * animationDegree - sprintModifier * 0.1F, false, 1.5F + sprintModifier * legOffsetModifier, 0F, f, f1);

        parModel.walk(thighRight, 1F * scaleFactor, 0.2F * animationDegree, true, 0F, 0F + sprintModifier * 0.2F, f, f1);
        parModel.walk(calfRight, 1F * scaleFactor, 0.2F * animationDegree - sprintModifier * 0.1F, false, 1F, 0F, f, f1);
        parModel.walk(footRight, 1F * scaleFactor, 0.2F * animationDegree - sprintModifier * 0.1F, true, 1.5F, 0F, f, f1);

        parModel.walk(armUpperRight, 1F * scaleFactor, 0.2F * animationDegree, true, frontOffset + 0F, -0.1F + sprintModifier * 0.2F, f, f1);
        parModel.walk(armLowerRight, 1F * scaleFactor, 0.1F * animationDegree, true, frontOffset + 1F, -0.2F, f, f1);
        parModel.walk(handRight, 1F * scaleFactor, 0.2F * animationDegree - sprintModifier * 0.1F, false, frontOffset + 1.5F, 0F, f, f1);

        parModel.walk(armUpperLeft, 1F * scaleFactor, 0.2F * animationDegree, false, frontOffset + 0F + sprintModifier * legOffsetModifier, -0.1F + sprintModifier * 0.2F, f, f1);
        parModel.walk(armLowerLeft, 1F * scaleFactor, 0.1F * animationDegree, false, frontOffset + 1F + sprintModifier * legOffsetModifier, -0.2F, f, f1);
        parModel.walk(handLeft, 1F * scaleFactor, 0.2F * animationDegree - sprintModifier * 0.1F, true, frontOffset + 1.5F + sprintModifier * legOffsetModifier, 0F, f, f1);

        parModel.chainWave(tail, bobBase * scaleFactor, 0.03F, 1F, f, f1);

        int ticksExisted = parEntity.ticksExisted;

        // Idling
        parModel.walk(neck1, 0.1F, 0.07F, false, -1F, 0F, ticksExisted, 1F);
        parModel.walk(head, 0.1F, 0.07F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(waist, 0.1F, 0.025F, false, 0F, 0F, ticksExisted, 1F);

        float inverseKinematicsConstant = 0.3F;
        parModel.walk(armUpperRight, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(armLowerRight, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(handRight, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(armUpperLeft, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(armLowerLeft, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(handLeft, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        armUpperRight.rotationPointZ -= 0.5 * Math.cos(ticksExisted * 0.1F);
        armUpperLeft.rotationPointZ -= 0.5 * Math.cos(ticksExisted * 0.1F);

        parModel.chainSwing(tail, 0.1F, 0.05F, 2, ticksExisted, 1F);
        parModel.chainWave(tail, 0.1F, -0.05F, 1, ticksExisted, 1F);

//        model.faceTarget(head, 4, rotationYaw, rotationPitch);
//        model.faceTarget(neck3, 4, rotationYaw, rotationPitch);
//        model.faceTarget(neck2, 4, rotationYaw, rotationPitch);
//        model.faceTarget(neck1, 4, rotationYaw, rotationPitch);
//
//        model.bob(waist, 1 * globalSpeed, height, false, f, f1);
//        model.walk(waist, 1 * globalSpeed, 0.1F * height, true, -1.5F, 0.05F, f, f1);

        ((EntityTriceratops) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
