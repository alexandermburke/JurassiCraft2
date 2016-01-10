package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityBaryonyx;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationBaryonyx extends DinosaurAnimator
{
    public AnimationBaryonyx()
    {
        super(JCEntityRegistry.baryonyx);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        float scaleFactor = 0.62F;
        float height = 2F * f1;

        MowzieModelRenderer thighRight = model.getCube("Right Thigh");
        MowzieModelRenderer thighLeft = model.getCube("Left Thigh");

        MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
        MowzieModelRenderer leftCalf1 = model.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");

        MowzieModelRenderer rightFoot = model.getCube("Foot Right");
        MowzieModelRenderer leftFoot = model.getCube("Foot Left");

        MowzieModelRenderer upperArmRight = model.getCube("Upper Arm Right");
        MowzieModelRenderer upperArmLeft = model.getCube("Upper Arm LEFT");

        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm LEFT");

        MowzieModelRenderer handRight = model.getCube("hand right");
        MowzieModelRenderer handLeft = model.getCube("hand left");

        MowzieModelRenderer waist = model.getCube("Body 1");
        MowzieModelRenderer stomach = model.getCube("Body 2");
        MowzieModelRenderer shoulders = model.getCube("Body 3");

        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");
        MowzieModelRenderer neck5 = model.getCube("Neck 5");

        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[] { handLeft, lowerArmLeft, upperArmLeft };
        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[] { handRight, lowerArmRight, upperArmRight };

        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        model.bob(waist, 1F * scaleFactor, height, false, f, f1);
        model.bob(thighLeft, 1F * scaleFactor, height, false, f, f1);
        model.bob(thighRight, 1F * scaleFactor, height, false, f, f1);
        model.bob(neck1, 1F * scaleFactor, height / 2, false, f, f1);

        model.walk(neck1, 1F * scaleFactor, 0.25F, false, 1F, 0.1F, f, f1);
        model.walk(head, 1F * scaleFactor, 0.25F, true, 1F, -0.1F, f, f1);
        model.walk(waist, 1F * scaleFactor, 0.1F, true, 0F, 0.05F, f, f1);

        model.walk(thighLeft, 0.5F * scaleFactor, 0.8F, false, 0F, 0.4F, f, f1);
        model.walk(leftCalf1, 0.5F * scaleFactor, 0.5F, true, 1F, 0F, f, f1);
        model.walk(leftCalf2, 0.5F * scaleFactor, 0.5F, false, 0F, 0F, f, f1);
        model.walk(leftFoot, 0.5F * scaleFactor, 1.5F, true, 0.5F, 1F, f, f1);

        model.walk(thighRight, 0.5F * scaleFactor, 0.8F, true, 0F, 0.4F, f, f1);
        model.walk(rightCalf1, 0.5F * scaleFactor, 0.5F, false, 1F, 0F, f, f1);
        model.walk(rightCalf2, 0.5F * scaleFactor, 0.5F, true, 0F, 0F, f, f1);
        model.walk(rightFoot, 0.5F * scaleFactor, 1.5F, false, 0.5F, 1F, f, f1);

        model.chainSwing(tailParts, 0.5F * scaleFactor, -0.1F, 2, f, f1);
        model.chainWave(tailParts, 1F * scaleFactor, -0.03F, 2, f, f1);
        model.chainWave(rightArmParts, 1F * scaleFactor, -0.3F, 4, f, f1);
        model.chainWave(leftArmParts, 1F * scaleFactor, -0.3F, 4, f, f1);

        // Idling
        int ticksExisted = parEntity.ticksExisted;

        model.chainWave(tailParts, 0.1F, -0.05F, 2, ticksExisted, 1.0F);
        model.walk(neck1, 0.1F, 0.07F, false, -1F, 0F, ticksExisted, 1.0F);
        model.walk(head, 0.1F, 0.07F, true, 0F, 0F, ticksExisted, 1.0F);
        model.walk(waist, 0.1F, 0.05F, false, 0F, 0F, ticksExisted, 1.0F);
        model.chainWave(rightArmParts, 0.1F, -0.1F, 4, ticksExisted, 1.0F);
        model.chainWave(leftArmParts, 0.1F, -0.1F, 4, ticksExisted, 1.0F);
        model.chainSwing(tailParts, 0.1F, -0.1F, 3, ticksExisted, 1.0F);

        ((EntityBaryonyx) parEntity).tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
