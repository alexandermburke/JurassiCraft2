package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityMegapiranha;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

public class AnimationMegapiranha implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

        EntityMegapiranha dino = (EntityMegapiranha) entity;

        float globalSpeed = 0.2F;
        float globalDegree = 0.77F;
        float globalHeight = 2F;

//        f = dino.ticksExisted;
//        f1 = 1F;

        MowzieModelRenderer head = model.getCube("Neck ");
        MowzieModelRenderer body1 = model.getCube("Body Section 1");
        MowzieModelRenderer body2 = model.getCube("Body Section 2");
        MowzieModelRenderer body3 = model.getCube("Body Section 3");

        MowzieModelRenderer tail1 = model.getCube("Tail Section 1");
        MowzieModelRenderer tail2 = model.getCube("Tail Section 2");
        MowzieModelRenderer tail3 = model.getCube("Tail Section 3");

        MowzieModelRenderer leftFlipper = model.getCube("Left Front Flipper");
        MowzieModelRenderer rightFlipper = model.getCube("Right Front Flipper");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail3, tail2, tail1, body3, body2, body1, head};

        head.rotationPointX -= -1 * f1 * Math.sin((f + 1) * 0.6);
        model.chainSwing(tail, 0.3F, 0.2F, 3.0D, f, f1);

        model.walk(leftFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);
        model.walk(rightFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);

        model.flap(leftFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);
        model.flap(rightFlipper, 0.6F, 0.6F, true, 0.0F, -0.8F, f, f1);

        int ticksExisted = entity.ticksExisted;

        model.bob(head, 0.04F, 2.0F, false, ticksExisted, 1.0F);

        model.walk(leftFlipper, 0.2F, 0.25F, false, 1.0F, 0.1F, ticksExisted, 1.0F);

        model.walk(rightFlipper, 0.2F, 0.25F, false, 1.0F, 0.1F, ticksExisted, 1.0F);

        model.chainSwing(tail, 0.05F, -0.075F, 1.5D, ticksExisted, 1.0F);
    }
}
