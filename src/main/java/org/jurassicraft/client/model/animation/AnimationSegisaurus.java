package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurSegisaurus;
import org.jurassicraft.common.entity.EntitySegisaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationSegisaurus extends DinosaurAnimator
{
    public AnimationSegisaurus()
    {
        super(new DinosaurSegisaurus());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntitySegisaurus entity = (EntitySegisaurus) parEntity;

        MowzieModelRenderer head = parModel.getCube("head");
        MowzieModelRenderer neck1 = parModel.getCube("neck1");
        MowzieModelRenderer neck2 = parModel.getCube("neck2");
        MowzieModelRenderer neck3 = parModel.getCube("neck3");
        MowzieModelRenderer neck4 = parModel.getCube("neck4");

        MowzieModelRenderer lowerJaw = parModel.getCube("down_jaw");

        MowzieModelRenderer waist = parModel.getCube("body3");
        MowzieModelRenderer chest = parModel.getCube("body2");
        MowzieModelRenderer shoulders = parModel.getCube("body1");

        MowzieModelRenderer tail1 = parModel.getCube("tail1");
        MowzieModelRenderer tail2 = parModel.getCube("tail2");
        MowzieModelRenderer tail3 = parModel.getCube("tail3");
        MowzieModelRenderer tail4 = parModel.getCube("tail4");
        MowzieModelRenderer tail5 = parModel.getCube("tail5");

        MowzieModelRenderer upperArmR = parModel.getCube("arm1");
        MowzieModelRenderer upperArmL = parModel.getCube("arm2");

        MowzieModelRenderer lowerArmR = parModel.getCube("forearm1");
        MowzieModelRenderer lowerArmL = parModel.getCube("forearm2");

        MowzieModelRenderer handR = parModel.getCube("hand1");
        MowzieModelRenderer handL = parModel.getCube("hand2");

        MowzieModelRenderer thighR = parModel.getCube("thigh1");
        MowzieModelRenderer thighL = parModel.getCube("thigh2");

        MowzieModelRenderer lowerThighR = parModel.getCube("leg1");
        MowzieModelRenderer lowerThighL = parModel.getCube("leg2");

        MowzieModelRenderer upperFootR = parModel.getCube("upperfoot1");
        MowzieModelRenderer upperFootL = parModel.getCube("upperfoot2");

        MowzieModelRenderer footR = parModel.getCube("foot1");
        MowzieModelRenderer footL = parModel.getCube("foot2");

        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[]{handR, lowerArmR, upperArmR};
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[]{handL, lowerArmL, upperArmL};
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[]{waist, chest, shoulders, neck4, neck3, neck2, neck1, head};

        float globalSpeed = 1.0F;
        float globalHeight = 2F * f1;

//        float dontLeanProgress = entity.dontLean.getAnimationProgressSinSqrt();

        parModel.bob(waist, 1F * globalSpeed, globalHeight, false, f, f1);
        parModel.bob(thighL, 1F * globalSpeed, globalHeight, false, f, f1);
        parModel.bob(thighR, 1F * globalSpeed, globalHeight, false, f, f1);
        parModel.walk(shoulders, 1F * globalSpeed, 0.2F, true, 1, 0, f, f1);
        parModel.walk(chest, 1F * globalSpeed, 0.2F, false, 0.5F, 0, f, f1);

        parModel.walk(thighL, 0.5F * globalSpeed, 0.7F, false, 3.14F, 0.2F, f, f1);
        parModel.walk(lowerThighL, 0.5F * globalSpeed, 0.6F, false, 1.5F, 0.3F, f, f1);
        parModel.walk(upperFootL, 0.5F * globalSpeed, 0.8F, false, -1F, -0.1F, f, f1);
        parModel.walk(footL, 0.5F * globalSpeed, 1.5F, true, -1F, 1F, f, f1);

        parModel.walk(thighR, 0.5F * globalSpeed, 0.7F, true, 3.14F, 0.2F, f, f1);
        parModel.walk(lowerThighR, 0.5F * globalSpeed, 0.6F, true, 1.5F, 0.3F, f, f1);
        parModel.walk(upperFootR, 0.5F * globalSpeed, 0.8F, true, -1F, -0.1F, f, f1);
        parModel.walk(footR, 0.5F * globalSpeed, 1.5F, false, -1F, 1F, f, f1);

//        shoulders.rotationPointY -= 0.5 * f1 * dontLeanProgress;
//        shoulders.rotationPointZ -= 0.5 * f1 * dontLeanProgress;
//        shoulders.rotateAngleX += 0.6 * f1 * dontLeanProgress;
//        chest.rotateAngleX += 0.1 * f1 * dontLeanProgress;
//        neck1.rotateAngleX += 0.1 * f1 * dontLeanProgress;
//        neck2.rotateAngleX += 0.1 * f1 * dontLeanProgress;
//        neck3.rotateAngleX -= 0.2 * f1 * dontLeanProgress;
//        neck4.rotateAngleX -= 0.2 * f1 * dontLeanProgress;
//        head.rotateAngleX -= 0.3 * f1 * dontLeanProgress;

        parModel.chainSwing(tailParts, 0.5F * globalSpeed, -0.1F, 2, f, f1);
        parModel.chainWave(tailParts, 1F * globalSpeed, -0.1F, 2.5F, f, f1);
        parModel.chainWave(bodyParts, 1F * globalSpeed, -0.1F, 4, f, f1);

        parModel.chainWave(rightArmParts, 1F * globalSpeed, -0.3F, 4, f, f1);
        parModel.chainWave(leftArmParts, 1F * globalSpeed, -0.3F, 4, f, f1);

        // Idling
        parModel.chainWave(tailParts, 0.1F, 0.05F, 2, entity.ticksExisted, 1F);
        parModel.chainWave(bodyParts, 0.1F, -0.03F, 5, entity.ticksExisted, 1F);
        parModel.chainWave(rightArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);
        parModel.chainWave(leftArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);

        entity.tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
