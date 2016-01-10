package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntitySegisaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationSegisaurus extends DinosaurAnimator
{
    public AnimationSegisaurus()
    {
        super(JCEntityRegistry.segisaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntitySegisaurus entity = (EntitySegisaurus) parEntity;

        MowzieModelRenderer head = model.getCube("head");
        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");

        MowzieModelRenderer lowerJaw = model.getCube("down_jaw");

        MowzieModelRenderer waist = model.getCube("body3");
        MowzieModelRenderer chest = model.getCube("body2");
        MowzieModelRenderer shoulders = model.getCube("body1");

        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");

        MowzieModelRenderer upperArmR = model.getCube("arm1");
        MowzieModelRenderer upperArmL = model.getCube("arm2");

        MowzieModelRenderer lowerArmR = model.getCube("forearm1");
        MowzieModelRenderer lowerArmL = model.getCube("forearm2");

        MowzieModelRenderer handR = model.getCube("hand1");
        MowzieModelRenderer handL = model.getCube("hand2");

        MowzieModelRenderer thighR = model.getCube("thigh1");
        MowzieModelRenderer thighL = model.getCube("thigh2");

        MowzieModelRenderer lowerThighR = model.getCube("leg1");
        MowzieModelRenderer lowerThighL = model.getCube("leg2");

        MowzieModelRenderer upperFootR = model.getCube("upperfoot1");
        MowzieModelRenderer upperFootL = model.getCube("upperfoot2");

        MowzieModelRenderer footR = model.getCube("foot1");
        MowzieModelRenderer footL = model.getCube("foot2");

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
        model.chainWave(tailParts, 0.1F, 0.05F, 2, entity.ticksExisted, 1F);
        model.chainWave(bodyParts, 0.1F, -0.03F, 5, entity.ticksExisted, 1F);
        model.chainWave(rightArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);
        model.chainWave(leftArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);

        entity.tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
