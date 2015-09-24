package org.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityStegosaurus;

@SideOnly(Side.CLIENT)
public class AnimationStegosaurus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

//        f = entity.ticksExisted;
//        f1 = 0.25F;

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer upperJaw = model.getCube("Upper Jaw");
        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");

        MowzieModelRenderer neck1 = model.getCube("Neck");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");

        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer rearBody = model.getCube("Body REAR");
        MowzieModelRenderer hips = model.getCube("Body hips");
        MowzieModelRenderer bodyFrontBase = model.getCube("Body MAIN");
        MowzieModelRenderer shoulders = model.getCube("Body shoulders");
        MowzieModelRenderer bodyFront = model.getCube("Body FRONT");

        MowzieModelRenderer thighRight = model.getCube("RearLeg Upper Right");
        MowzieModelRenderer thighLeft = model.getCube("RearLeg Upper Left");

        MowzieModelRenderer calfRight = model.getCube("RearLeg Middle Right");
        MowzieModelRenderer calfLeft = model.getCube("RearLeg Middle Left");

        MowzieModelRenderer footRight = model.getCube("RearLeg Foot Right");
        MowzieModelRenderer footLeft = model.getCube("RearLeg Foot Left");

        MowzieModelRenderer armUpperRight = model.getCube("FrontLeg Upper Right");
        MowzieModelRenderer armUpperLeft = model.getCube("FrontLeg Upper Left");

        MowzieModelRenderer armLowerRight = model.getCube("FrontLeg MID Right");
        MowzieModelRenderer armLowerLeft = model.getCube("FrontLeg MID Left");

        MowzieModelRenderer handRight = model.getCube("FrontLeg FOOT Right");
        MowzieModelRenderer handLeft = model.getCube("FrontLeg FOOT Left");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};

        float scaleFactor = 0.5F;
        float height = 0.8F;
        float frontOffset = -2F;

        model.faceTarget(head, 3, rotationYaw, rotationPitch);
        model.faceTarget(neck3, 3, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 3, rotationYaw, rotationPitch);

        model.bob(hips, 2 * scaleFactor, height, false, f, f1);
        model.bob(thighLeft, 2 * scaleFactor, height, false, f, f1);
        model.bob(thighRight, 2 * scaleFactor, height, false, f, f1);
        model.walk(hips, 2 * scaleFactor, 0.1F * height, true, -1.5F, 0F, f, f1);
        model.walk(neck1, 2 * scaleFactor, 0.07F * height, false, -0.5F, 0F, f, f1);
        model.walk(neck3, 2 * scaleFactor, 0.07F * height, false, 0.5F, 0F, f, f1);
        model.walk(head, 2 * scaleFactor, 0.07F * height, true, 1.5F, 0F, f, f1);

        model.walk(thighLeft, 1F * scaleFactor, 0.8F, false, 0F, 0F, f, f1);
        model.walk(calfLeft, 1F * scaleFactor, 0.8F, true, 1F, 0F, f, f1);
        model.walk(footLeft, 1F * scaleFactor, 0.8F, false, 1.5F, 0F, f, f1);

        model.walk(thighRight, 1F * scaleFactor, 0.8F, true, 0F, 0F, f, f1);
        model.walk(calfRight, 1F * scaleFactor, 0.8F, false, 1F, 0F, f, f1);
        model.walk(footRight, 1F * scaleFactor, 0.8F, true, 1.5F, 0F, f, f1);

        model.walk(armUpperRight, 1F * scaleFactor, 0.8F, true, frontOffset + 0F, -0.1F, f, f1);
        model.walk(armLowerRight, 1F * scaleFactor, 0.6F, true, frontOffset + 1F, -0.2F, f, f1);
        model.walk(handRight, 1F * scaleFactor, 0.8F, false, frontOffset + 1.5F, 0F, f, f1);

        model.walk(armUpperLeft, 1F * scaleFactor, 0.8F, false, frontOffset + 0F, -0.1F, f, f1);
        model.walk(armLowerLeft, 1F * scaleFactor, 0.6F, false, frontOffset + 1F, -0.2F, f, f1);
        model.walk(handLeft, 1F * scaleFactor, 0.8F, true, frontOffset + 1.5F, 0F, f, f1);

        // Idling
        int ticksExisted = entity.ticksExisted;

        model.walk(neck1, 0.1F, 0.04F, false, -1F, 0F, ticksExisted, 1F);
        model.walk(head, 0.1F, 0.07F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(neck3, 0.1F, 0.03F, false, 0F, 0F, ticksExisted, 1F);
        model.walk(hips, 0.1F, 0.025F, false, 0F, 0F, ticksExisted, 1F);

        float inverseKinematicsConstant = 0.6F;
        model.walk(armUpperRight, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        model.walk(armLowerRight, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticksExisted, 1F);
        model.walk(handRight, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        model.walk(armUpperLeft, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        model.walk(armLowerLeft, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticksExisted, 1F);
        model.walk(handLeft, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        armUpperLeft.rotationPointZ -= 0.5 * Math.cos(ticksExisted * 0.1F);
        armUpperRight.rotationPointZ -= 0.5 * Math.cos(ticksExisted * 0.1F);

        model.chainSwing(tail, 0.1F, 0.2F, 3, ticksExisted, 1.0F);
        model.chainWave(tail, 0.1F, -0.05F, 1, ticksExisted, 1.0F);

        ((EntityStegosaurus) entity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
