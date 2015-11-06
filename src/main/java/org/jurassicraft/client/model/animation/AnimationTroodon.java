package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityTroodon;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationTroodon extends DinosaurAnimator
{
    public AnimationTroodon()
    {
        super(JCEntityRegistry.troodon);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityTroodon entity = (EntityTroodon) parEntity;
        Animator animator = model.animator;

        MowzieModelRenderer head = model.getCube("head UPPER");
        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");

        MowzieModelRenderer lowerJaw = model.getCube("Jaw LOWER");

        MowzieModelRenderer waist = model.getCube("body3");
        MowzieModelRenderer chest = model.getCube("body2");
        MowzieModelRenderer shoulders = model.getCube("body1");

        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");

        MowzieModelRenderer upperArmR = model.getCube("Right arm");
        MowzieModelRenderer upperArmL = model.getCube("Left arm");

        MowzieModelRenderer lowerArmR = model.getCube("Right forearm");
        MowzieModelRenderer lowerArmL = model.getCube("Left forearm");

        MowzieModelRenderer handR = model.getCube("Right hand");
        MowzieModelRenderer handL = model.getCube("Left hand");

        MowzieModelRenderer thighR = model.getCube("Right thigh");
        MowzieModelRenderer thighL = model.getCube("Left thigh");

        MowzieModelRenderer lowerThighR = model.getCube("Right shin");
        MowzieModelRenderer lowerThighL = model.getCube("Left shin");

        MowzieModelRenderer upperFootR = model.getCube("Right upper foot");
        MowzieModelRenderer upperFootL = model.getCube("Left upper foot");

        MowzieModelRenderer footR = model.getCube("Right foot");
        MowzieModelRenderer footL = model.getCube("Left foot");

        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[] { handR, lowerArmR, upperArmR };
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[] { handL, lowerArmL, upperArmL };
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail5, tail4, tail3, tail2, tail1 };
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[] { waist, chest, shoulders, neck4, neck3, neck2, neck1, head };

        float globalSpeed = 1.0F;
        float globalHeight = 2F * f1;

        // float dontLeanProgress = entity.dontLean.getAnimationProgressSinSqrt();

        model.bob(waist, 1F * globalSpeed, globalHeight, false, f, f1);
        model.bob(thighL, 1F * globalSpeed, globalHeight, false, f, f1);
        model.bob(thighR, 1F * globalSpeed, globalHeight, false, f, f1);
        model.walk(shoulders, 1F * globalSpeed, 0.2F, true, 1, 0, f, f1);
        model.walk(chest, 1F * globalSpeed, 0.2F, false, 0.5F, 0, f, f1);

        model.walk(thighL, 0.5F * globalSpeed, 0.7F, false, 3.14F, 0.2F, f, f1);
        model.walk(lowerThighL, 0.5F * globalSpeed, 0.6F, false, 1.5F, 0.3F, f, f1);
        model.walk(upperFootL, 0.5F * globalSpeed, 0.8F, false, -1F, -0.1F, f, f1);
        model.walk(footL, 0.5F * globalSpeed, 1.5F, true, -1F, 1F, f, f1);

        model.walk(thighR, 0.5F * globalSpeed, 0.7F, true, 3.14F, 0.2F, f, f1);
        model.walk(lowerThighR, 0.5F * globalSpeed, 0.6F, true, 1.5F, 0.3F, f, f1);
        model.walk(upperFootR, 0.5F * globalSpeed, 0.8F, true, -1F, -0.1F, f, f1);
        model.walk(footR, 0.5F * globalSpeed, 1.5F, false, -1F, 1F, f, f1);

        // shoulders.rotationPointY -= 0.5 * f1 * dontLeanProgress;
        // shoulders.rotationPointZ -= 0.5 * f1 * dontLeanProgress;
        // shoulders.rotateAngleX += 0.6 * f1 * dontLeanProgress;
        // chest.rotateAngleX += 0.1 * f1 * dontLeanProgress;
        // neck1.rotateAngleX += 0.1 * f1 * dontLeanProgress;
        // neck2.rotateAngleX += 0.1 * f1 * dontLeanProgress;
        // neck3.rotateAngleX -= 0.2 * f1 * dontLeanProgress;
        // neck4.rotateAngleX -= 0.2 * f1 * dontLeanProgress;
        // head.rotateAngleX -= 0.3 * f1 * dontLeanProgress;

        model.chainSwing(tailParts, 0.5F * globalSpeed, -0.1F, 2, f, f1);
        model.chainWave(tailParts, 1F * globalSpeed, -0.1F, 2.5F, f, f1);
        model.chainWave(bodyParts, 1F * globalSpeed, -0.1F, 4, f, f1);

        model.chainWave(rightArmParts, 1F * globalSpeed, -0.3F, 4, f, f1);
        model.chainWave(leftArmParts, 1F * globalSpeed, -0.3F, 4, f, f1);

        // Idling
        model.chainWave(tailParts, 0.2F, 0.05F, 2, entity.ticksExisted, 1F);
        model.chainWave(bodyParts, 0.2F, -0.03F, 5, entity.ticksExisted, 1F);
        model.chainWave(rightArmParts, 0.2F, -0.1F, 4, entity.ticksExisted, 1F);
        model.chainWave(leftArmParts, 0.2F, -0.1F, 4, entity.ticksExisted, 1F);

        entity.tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
