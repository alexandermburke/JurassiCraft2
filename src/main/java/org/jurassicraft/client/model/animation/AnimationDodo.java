package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.common.animation.Animator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animations;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationDodo extends DinosaurAnimator
{
    public AnimationDodo()
    {
        super(JCEntityRegistry.dodo);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = model.animator;

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer neck1 = model.getCube("Neck1");
        MowzieModelRenderer neck2 = model.getCube("Neck2");
        MowzieModelRenderer neck3 = model.getCube("Neck3");
        MowzieModelRenderer neck4 = model.getCube("Neck4");
        MowzieModelRenderer neck5 = model.getCube("Neck5");
        MowzieModelRenderer neck6 = model.getCube("Neck6");
        MowzieModelRenderer neck7 = model.getCube("Neck7");

        MowzieModelRenderer lowerJaw = model.getCube("LowerJaw1");
        MowzieModelRenderer upperJaw = model.getCube("UpperJaw1");

        MowzieModelRenderer body = model.getCube("Body1");
        MowzieModelRenderer bodyFront = model.getCube("Body2");
        MowzieModelRenderer bodyBack = model.getCube("Body3");

        MowzieModelRenderer tail = model.getCube("Tail");

        MowzieModelRenderer leftWing1 = model.getCube("LeftWing1");
        MowzieModelRenderer leftWing2 = model.getCube("LeftWing2");

        MowzieModelRenderer rightWing1 = model.getCube("RightWing1");
        MowzieModelRenderer rightWing2 = model.getCube("RightWing2");

        MowzieModelRenderer leftLegBase = model.getCube("LeftLeg1");
        MowzieModelRenderer leftLeg2 = model.getCube("LeftLeg2");
        MowzieModelRenderer leftFoot = model.getCube("LeftFeet");

        MowzieModelRenderer rightLegBase = model.getCube("RightLeg1");
        MowzieModelRenderer rightLeg2 = model.getCube("RightLeg2");
        MowzieModelRenderer rightFoot = model.getCube("RightFeet");

        MowzieModelRenderer[] neckParts = new MowzieModelRenderer[] { head, neck7, neck6, neck5, neck4, neck3, neck2, neck1 };
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[] { bodyFront, body, bodyBack, tail };

        // f = entity.ticksExisted;
        // f1 = 0.25F;

        float globalSpeed = 1.0F;
        float globalDegree = 1.0F;
        float globalHeight = 0.5F;

        model.chainWave(neckParts, globalSpeed * 1.0F, globalHeight * 0.1F, 3, f, f1);
        model.chainWave(bodyParts, globalSpeed * 1.0F, globalHeight * 0.1F, 3, f, f1);

        model.swing(tail, globalSpeed * 1.0F, globalHeight * 2.0F, false, 0.0F, 0.0F, f, f1);

        model.bob(body, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(leftLegBase, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(rightLegBase, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.walk(rightLegBase, globalSpeed * 0.5F, globalDegree * 1.0F, false, 0.0F, 0.0F, f, f1);
        model.walk(rightLeg2, globalSpeed * 0.5F, globalDegree * 0.5F, false, 0.0F, 0.0F, f, f1);
        model.walk(rightFoot, globalSpeed * 0.5F, globalDegree * 1.0F, false, 0.0F, 0.0F, f, f1);

        model.walk(leftLegBase, globalSpeed * 0.5F, globalDegree * 1.0F, true, 0.0F, 0.0F, f, f1);
        model.walk(leftLeg2, globalSpeed * 0.5F, globalDegree * 0.5F, true, 0.0F, 0.0F, f, f1);
        model.walk(leftFoot, globalSpeed * 0.5F, globalDegree * 1.0F, true, 0.0F, 0.0F, f, f1);

        leftLegBase.rotationPointZ -= 1 * f1 * Math.cos(f * 1.0F * globalSpeed);
        rightLegBase.rotationPointZ -= -1 * f1 * Math.cos(f * 1.0F * globalSpeed);

        int ticksExisted = parEntity.ticksExisted;

        model.chainWave(neckParts, globalSpeed * 0.125F, globalHeight * 0.05F, 3, ticksExisted, 1.0F);
        model.chainWave(bodyParts, globalSpeed * 0.125F, globalHeight * 0.05F, 3, ticksExisted, 1.0F);

        animator.setAnimationId(Animations.EATING.get().animationId);
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
