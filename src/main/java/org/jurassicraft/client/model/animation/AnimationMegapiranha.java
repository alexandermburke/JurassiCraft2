package org.jurassicraft.client.model.animation;

import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurMegapiranha;
import org.jurassicraft.common.entity.EntityMegapiranha;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class AnimationMegapiranha extends DinosaurAnimator
{
    public AnimationMegapiranha()
    {
        super(new DinosaurMegapiranha());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = parModel.animator;

        EntityMegapiranha dino = (EntityMegapiranha) parEntity;

        float globalSpeed = 0.2F;
        float globalDegree = 0.77F;
        float globalHeight = 2F;

//        f = dino.ticksExisted;
//        f1 = 1F;

        MowzieModelRenderer head = parModel.getCube("Neck ");
        MowzieModelRenderer body1 = parModel.getCube("Body Section 1");
        MowzieModelRenderer body2 = parModel.getCube("Body Section 2");
        MowzieModelRenderer body3 = parModel.getCube("Body Section 3");

        MowzieModelRenderer tail1 = parModel.getCube("Tail Section 1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail Section 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail Section 3");

        MowzieModelRenderer leftFlipper = parModel.getCube("Left Front Flipper");
        MowzieModelRenderer rightFlipper = parModel.getCube("Right Front Flipper");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail3, tail2, tail1, body3, body2, body1, head};

        head.rotationPointX -= -1 * f1 * Math.sin((f + 1) * 0.6);
        parModel.chainSwing(tail, 0.3F, 0.2F, 3.0D, f, f1);

        parModel.walk(leftFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);
        parModel.walk(rightFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);

        parModel.flap(leftFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);
        parModel.flap(rightFlipper, 0.6F, 0.6F, true, 0.0F, -0.8F, f, f1);

        int ticksExisted = parEntity.ticksExisted;

        parModel.bob(head, 0.04F, 2.0F, false, ticksExisted, 1.0F);

        parModel.walk(leftFlipper, 0.2F, 0.25F, false, 1.0F, 0.1F, ticksExisted, 1.0F);

        parModel.walk(rightFlipper, 0.2F, 0.25F, false, 1.0F, 0.1F, ticksExisted, 1.0F);

        parModel.chainSwing(tail, 0.05F, -0.075F, 1.5D, ticksExisted, 1.0F);
    }
}
