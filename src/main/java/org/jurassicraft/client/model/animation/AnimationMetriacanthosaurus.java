package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.common.animation.Animator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityMetriacanthosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationMetriacanthosaurus extends DinosaurAnimator
{
    public AnimationMetriacanthosaurus()
    {
        super(JCEntityRegistry.metriacanthosaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityMetriacanthosaurus entity = (EntityMetriacanthosaurus) parEntity;
        Animator animator = model.animator;

        float globalSpeed = 0.65F;
        float globalDegree = 0.4F;
        float height = 1.0F;

        // middle
        MowzieModelRenderer shoulders = model.getCube("Body Front");
        MowzieModelRenderer chest = model.getCube("Body Mid");
        MowzieModelRenderer waist = model.getCube("Body Rear");

        // right feet
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");
        MowzieModelRenderer rightCalf = model.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
        MowzieModelRenderer rightFoot = model.getCube("Foot Right");

        // left feet
        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer leftCalf = model.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");
        MowzieModelRenderer leftFoot = model.getCube("Foot Left");

        // neck
        MowzieModelRenderer neck1 = model.getCube("Neck BASE");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");

        // head
        MowzieModelRenderer head = model.getCube("Head");

        // arms
        MowzieModelRenderer lowerArmLeft = model.getCube("Arm MID LEFT");
        MowzieModelRenderer upperArmLeft = model.getCube("Arm UPPER LEFT");
        MowzieModelRenderer upperArmRight = model.getCube("Arm UPPER RIGHT");
        MowzieModelRenderer lowerArmRight = model.getCube("Arm MID RIGHT");

        // hands
        MowzieModelRenderer handLeft = model.getCube("Hand LEFT");
        MowzieModelRenderer handRight = model.getCube("Hand RIGHT");

        // tail
        MowzieModelRenderer tail1 = model.getCube("Tail Base");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");
        MowzieModelRenderer tail7 = model.getCube("Tail 7");
        MowzieModelRenderer tail8 = model.getCube("Tail 8");

        // jaw
        MowzieModelRenderer upperJaw1 = model.getCube("Upper Jaw");
        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");

        // throat
        MowzieModelRenderer throat1 = model.getCube("Throat 1");
        MowzieModelRenderer throat2 = model.getCube("Throat 2");

        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[] { handRight, lowerArmRight, upperArmRight };
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[] { handLeft, lowerArmLeft, upperArmLeft };
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail8, tail7, tail6, tail5, tail4, tail3, tail2, tail1 };
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[] { head, neck1, neck2, neck3, neck4, shoulders, chest, waist };

        // Body animations
        model.bob(waist, 1F * globalSpeed, height, false, f, f1);
        model.bob(leftThigh, 1F * globalSpeed, height, false, f, f1);
        model.bob(rightThigh, 1F * globalSpeed, height, false, f, f1);
        leftThigh.rotationPointY -= -2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        model.chainWave(bodyParts, 1F * globalSpeed, 0.05F, 3, f, f1);
        model.chainWave(tailParts, 1F * globalSpeed, height * 0.05F, 3, f, f1);
        model.chainSwing(tailParts, 0.5F * globalSpeed, height * -0.05F, 2, f, f1);
        model.chainWave(leftArmParts, 1F * globalSpeed, height * 0.05F, 3, f, f1);
        model.chainWave(rightArmParts, 1F * globalSpeed, height * 0.05F, 3, f, f1);

        model.walk(head, 1F * globalSpeed, 0.1F, true, 0F, -0.2F, f, f1);
        model.walk(neck1, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);
        model.walk(neck2, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);
        model.walk(neck3, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);
        model.walk(neck4, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);
//        model.walk(neck5, 1F * globalSpeed, 0.02F, false, 0F, 0.04F, f, f1);

        model.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.2F, f, f1);
        model.walk(leftCalf, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        model.walk(leftCalf2, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        model.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.1F, f, f1);

        model.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.2F, f, f1);
        model.walk(rightCalf, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        model.walk(rightCalf2, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        model.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.1F, f, f1);

        // idling
        model.chainWave(tailParts, 0.1F, 0.05F, 2, parEntity.ticksExisted, 1F);
        model.chainWave(bodyParts, 0.1F, -0.03F, 4, parEntity.ticksExisted, 1F);
        model.chainWave(rightArmParts, 0.1F, -0.1F, 4, parEntity.ticksExisted, 1F);
        model.chainWave(leftArmParts, 0.1F, -0.1F, 4, parEntity.ticksExisted, 1F);

        parEntity.tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
