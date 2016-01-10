package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.common.animation.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityMegapiranha;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

public class AnimationMegapiranha extends DinosaurAnimator
{
    public AnimationMegapiranha()
    {
        super(JCEntityRegistry.megapiranha);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = model.animator;

        EntityMegapiranha dino = (EntityMegapiranha) parEntity;

        float globalSpeed = 5.5F;
        float globalDegree = 0.77F;
        float globalHeight = 2F;

        // f = dino.ticksExisted;
        // f1 = 1F;

        MowzieModelRenderer head = model.getCube("Neck ");
        MowzieModelRenderer body1 = model.getCube("Body Section 1");
        MowzieModelRenderer body2 = model.getCube("Body Section 2");
        MowzieModelRenderer body3 = model.getCube("Body Section 3");

        MowzieModelRenderer tail1 = model.getCube("Tail Section 1");
        MowzieModelRenderer tail2 = model.getCube("Tail Section 2");
        MowzieModelRenderer tail3 = model.getCube("Tail Section 3");

        MowzieModelRenderer leftFlipper = model.getCube("Left Front Flipper");
        MowzieModelRenderer rightFlipper = model.getCube("Right Front Flipper");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail3, tail2, tail1, body3, body2, body1, head };

        head.rotationPointX -= -4 * f1 * Math.sin((f + 1) * 0.6); // Head moves side to side
        model.chainSwing(tail, 0.6F, 0.4F, 3.0D, f, f1); // and the tail follows with a delay.

        model.walk(leftFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);
        model.walk(rightFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);

        model.flap(leftFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);
        model.flap(rightFlipper, 0.6F, 0.6F, true, 0.0F, -0.8F, f, f1);

        int ticksExisted = dino.ticksExisted;
        model.bob(head, 0.04F, 2.0F, false, ticksExisted, 1.0F);

        model.walk(leftFlipper, 0.2F, 0.25F, false, 1.0F, 0.1F, ticksExisted, 1.0F);

        model.walk(rightFlipper, 0.2F, 0.25F, false, 1.0F, 0.1F, ticksExisted, 1.0F);

        model.chainSwing(tail, 0.05F, -0.075F, 1.5D, ticksExisted, 1.0F);
    }
}
