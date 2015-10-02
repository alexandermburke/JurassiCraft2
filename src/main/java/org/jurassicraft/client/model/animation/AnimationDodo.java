package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.AnimID;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurDodo;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationDodo extends DinosaurAnimator
{
    public AnimationDodo()
    {
        super(new DinosaurDodo());
    }

    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = parModel.animator;

        MowzieModelRenderer head = parModel.getCube("Head");

        MowzieModelRenderer neck1 = parModel.getCube("Neck1");
        MowzieModelRenderer neck2 = parModel.getCube("Neck2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck3");
        MowzieModelRenderer neck4 = parModel.getCube("Neck4");
        MowzieModelRenderer neck5 = parModel.getCube("Neck5");
        MowzieModelRenderer neck6 = parModel.getCube("Neck6");
        MowzieModelRenderer neck7 = parModel.getCube("Neck7");

        MowzieModelRenderer lowerJaw = parModel.getCube("LowerJaw1");
        MowzieModelRenderer upperJaw = parModel.getCube("UpperJaw1");

        MowzieModelRenderer body = parModel.getCube("Body1");
        MowzieModelRenderer bodyFront = parModel.getCube("Body2");
        MowzieModelRenderer bodyBack = parModel.getCube("Body3");

        MowzieModelRenderer tail = parModel.getCube("Tail");

        MowzieModelRenderer leftWing1 = parModel.getCube("LeftWing1");
        MowzieModelRenderer leftWing2 = parModel.getCube("LeftWing2");

        MowzieModelRenderer rightWing1 = parModel.getCube("RightWing1");
        MowzieModelRenderer rightWing2 = parModel.getCube("RightWing2");

        MowzieModelRenderer leftLegBase = parModel.getCube("LeftLeg1");
        MowzieModelRenderer leftLeg2 = parModel.getCube("LeftLeg2");
        MowzieModelRenderer leftFoot = parModel.getCube("LeftFeet");

        MowzieModelRenderer rightLegBase = parModel.getCube("RightLeg1");
        MowzieModelRenderer rightLeg2 = parModel.getCube("RightLeg2");
        MowzieModelRenderer rightFoot = parModel.getCube("RightFeet");

        MowzieModelRenderer[] neckParts = new MowzieModelRenderer[]{head, neck7, neck6, neck5, neck4, neck3, neck2, neck1};
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[]{bodyFront, body, bodyBack, tail};

        // f = entity.ticksExisted;
        // f1 = 0.25F;

        float globalSpeed = 1.0F;
        float globalDegree = 1.0F;
        float globalHeight = 0.5F;

        parModel.chainWave(neckParts, globalSpeed * 1.0F, globalHeight * 0.1F, 3, f, f1);
        parModel.chainWave(bodyParts, globalSpeed * 1.0F, globalHeight * 0.1F, 3, f, f1);

        parModel.swing(tail, globalSpeed * 1.0F, globalHeight * 2.0F, false, 0.0F, 0.0F, f, f1);

        parModel.bob(body, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        parModel.bob(leftLegBase, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        parModel.bob(rightLegBase, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        parModel.walk(rightLegBase, globalSpeed * 0.5F, globalDegree * 1.0F, false, 0.0F, 0.0F, f, f1);
        parModel.walk(rightLeg2, globalSpeed * 0.5F, globalDegree * 0.5F, false, 0.0F, 0.0F, f, f1);
        parModel.walk(rightFoot, globalSpeed * 0.5F, globalDegree * 1.0F, false, 0.0F, 0.0F, f, f1);

        parModel.walk(leftLegBase, globalSpeed * 0.5F, globalDegree * 1.0F, true, 0.0F, 0.0F, f, f1);
        parModel.walk(leftLeg2, globalSpeed * 0.5F, globalDegree * 0.5F, true, 0.0F, 0.0F, f, f1);
        parModel.walk(leftFoot, globalSpeed * 0.5F, globalDegree * 1.0F, true, 0.0F, 0.0F, f, f1);

        leftLegBase.rotationPointZ -= 1 * f1 * Math.cos(f * 1.0F * globalSpeed);
        rightLegBase.rotationPointZ -= -1 * f1 * Math.cos(f * 1.0F * globalSpeed);

        int ticksExisted = parEntity.ticksExisted;

        parModel.chainWave(neckParts, globalSpeed * 0.125F, globalHeight * 0.05F, 3, ticksExisted, 1.0F);
        parModel.chainWave(bodyParts, globalSpeed * 0.125F, globalHeight * 0.05F, 3, ticksExisted, 1.0F);

        animator.setAnim(AnimID.EATING);
        animator.startPhase(7);
        animator.rotate(head, -0.3f, 0, 0);
        animator.rotate(body, 0.35f, 0, 0);
        animator.rotate(bodyFront, 0.4f, 0, 0);
        animator.rotate(neck1, 0.3f, 0, 0);
        animator.rotate(neck2, 0.2f, 0, 0);
        animator.rotate(neck3, 0.2f, 0, 0);
        animator.rotate(lowerJaw, 0.2f, 0, 0);
        animator.endPhase();

        animator.startPhase(6);
        animator.rotate(lowerJaw, 0, 0, 0);
        animator.endPhase();

        animator.resetPhase(5);
    }
}
