package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityTyrannosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationTyrannosaurus extends DinosaurAnimator
{
    public AnimationTyrannosaurus()
    {
        super(JCEntityRegistry.tyrannosaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        // Walking-dependent animation
        float globalSpeed = 0.45F;
        float globalDegree = 0.5F;
        float height = 1.0F;

        MowzieModelRenderer stomach = model.getCube("Body 2");
        MowzieModelRenderer chest = model.getCube("Body 3");
        MowzieModelRenderer head = model.getCube("Head");
        MowzieModelRenderer waist = model.getCube("Body 1");

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

        MowzieModelRenderer throat1 = model.getCube("Throat 1");
        MowzieModelRenderer throat2 = model.getCube("Throat 2");
        MowzieModelRenderer throat3 = model.getCube("Throat 3");

        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");

        MowzieModelRenderer handLeft = model.getCube("Hand Left");
        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm Left");
        MowzieModelRenderer upperArmLeft = model.getCube("Upper Arm Left");

        MowzieModelRenderer handRight = model.getCube("Hand Right");
        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
        MowzieModelRenderer upperArmRight = model.getCube("Upper Arm Right");

        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");

        MowzieModelRenderer leftCalf1 = model.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");
        MowzieModelRenderer leftFoot = model.getCube("Foot Left");

        MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
        MowzieModelRenderer rightFoot = model.getCube("Foot Right");

        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[] { head, neck5, neck4, neck3, neck2, neck1, chest, stomach, waist };
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[] { handLeft, lowerArmLeft, upperArmLeft };
        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[] { handRight, lowerArmRight, upperArmRight };

        model.bob(waist, 1F * globalSpeed, height, false, f, f1);
        model.bob(leftThigh, 1F * globalSpeed, height, false, f, f1);
        model.bob(rightThigh, 1F * globalSpeed, height, false, f, f1);
        leftThigh.rotationPointY -= -2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        model.walk(neck1, 1F * globalSpeed, 0.15F, false, 0F, 0.2F, f, f1);
        model.walk(head, 1F * globalSpeed, 0.15F, true, 0F, -0.2F, f, f1);

        model.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.4F, f, f1);
        model.walk(leftCalf1, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        model.walk(leftCalf2, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        model.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.3F, f, f1);

        model.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.4F, f, f1);
        model.walk(rightCalf1, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        model.walk(rightCalf2, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        model.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.3F, f, f1);

        model.chainWave(tailParts, 1F * globalSpeed, 0.05F, 2, f, f1);
        model.chainWave(bodyParts, 1F * globalSpeed, 0.05F, 3, f, f1);
        model.chainWave(leftArmParts, 1F * globalSpeed, 0.2F, 1, f, f1);
        model.chainWave(rightArmParts, 1F * globalSpeed, 0.2F, 1, f, f1);

        // Idling
        model.chainWave(bodyParts, 0.1F, -0.03F, 3, parEntity.ticksExisted, 1.0F);
        model.chainWave(rightArmParts, -0.1F, 0.2F, 4, parEntity.ticksExisted, 1.0F);
        model.chainWave(leftArmParts, -0.1F, 0.2F, 4, parEntity.ticksExisted, 1.0F);

        model.chainSwing(tailParts, 0.1F, 0.05F - (0.05F), 1, parEntity.ticksExisted, 1.0F - 0.6F);
        model.chainWave(tailParts, 0.1F, -0.1F, 2, parEntity.ticksExisted, 1.0F - 0.6F);

        ((EntityTyrannosaurus) parEntity).tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
