package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurTylosaurus;
import org.jurassicraft.common.entity.EntityTylosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationTylosaurus extends DinosaurAnimator
{
    public AnimationTylosaurus()
    {
        super(new DinosaurTylosaurus());
    }

    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityTylosaurus entity = (EntityTylosaurus) parEntity;

        MowzieModelRenderer head = parModel.getCube("Main head");
        MowzieModelRenderer neck = parModel.getCube("Neck ");

        MowzieModelRenderer body1 = parModel.getCube("Body Section 1");
        MowzieModelRenderer body2 = parModel.getCube("Body Section 2");
        MowzieModelRenderer body3 = parModel.getCube("Body Section 3");

        MowzieModelRenderer tail1 = parModel.getCube("Tail Section 1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail Section 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail Section 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail Section 4");

        MowzieModelRenderer leftFrontFlipper = parModel.getCube("Left Front Flipper");
        MowzieModelRenderer rightFrontFlipper = parModel.getCube("Right Front Flipper");

        MowzieModelRenderer leftBackFlipper = parModel.getCube("Left Back Flipper");
        MowzieModelRenderer rightBackFlipper = parModel.getCube("Right Back Flipper");

        float scaleFactor = 0.3F;

//        f = entity.ticksExisted;
//        f1 = 0.4F;

        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[]{head, neck, body1, body2, body3, tail1, tail2, tail3, tail4};

        parModel.chainSwing(bodyParts, 1F * scaleFactor, 0.2F, -3, f, f1);
        head.rotationPointX -= 6 * f1 * Math.sin(f * scaleFactor);
        parModel.walk(rightFrontFlipper, 1 * scaleFactor, 0.6F, false, 0F, 0F, f, f1);
        parModel.walk(leftFrontFlipper, 1 * scaleFactor, 0.6F, false, 0F, 0F, f, f1);
        parModel.walk(leftBackFlipper, 1 * scaleFactor, 0.6F, false, -1F, 0F, f, f1);
        parModel.walk(rightBackFlipper, 1 * scaleFactor, 0.6F, false, -1F, 0F, f, f1);

        int ticksExisted = entity.ticksExisted;

        parModel.bob(head, 0.25F * scaleFactor, 5F, false, ticksExisted, 0.1F);

        parModel.walk(rightFrontFlipper, 0.25F * scaleFactor, 1.5F, false, 0F, 0F, ticksExisted, 0.1F);
        parModel.walk(leftFrontFlipper, 0.25F * scaleFactor, 1.5F, false, 0F, 0F, ticksExisted, 0.1F);
        parModel.walk(leftBackFlipper, 0.25F * scaleFactor, 1.5F, false, -1F, 0F, ticksExisted, 0.1F);
        parModel.walk(rightBackFlipper, 0.25F * scaleFactor, 1.5F, false, -1F, 0F, ticksExisted, 0.1F);

        entity.tailBuffer.applyChainSwingBuffer(bodyParts);
    }
}
