package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityTylosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationTylosaurus extends DinosaurAnimator
{
    public AnimationTylosaurus()
    {
        super(JCEntityRegistry.tylosaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityTylosaurus entity = (EntityTylosaurus) parEntity;

        MowzieModelRenderer head = model.getCube("Main head");
        MowzieModelRenderer neck = model.getCube("Neck ");

        MowzieModelRenderer body1 = model.getCube("Body Section 1");
        MowzieModelRenderer body2 = model.getCube("Body Section 2");
        MowzieModelRenderer body3 = model.getCube("Body Section 3");

        MowzieModelRenderer tail1 = model.getCube("Tail Section 1");
        MowzieModelRenderer tail2 = model.getCube("Tail Section 2");
        MowzieModelRenderer tail3 = model.getCube("Tail Section 3");
        MowzieModelRenderer tail4 = model.getCube("Tail Section 4");

        MowzieModelRenderer leftFrontFlipper = model.getCube("Left Front Flipper");
        MowzieModelRenderer rightFrontFlipper = model.getCube("Right Front Flipper");

        MowzieModelRenderer leftBackFlipper = model.getCube("Left Back Flipper");
        MowzieModelRenderer rightBackFlipper = model.getCube("Right Back Flipper");

        float scaleFactor = 0.3F;

        // f = entity.ticksExisted;
        // f1 = 0.4F;

        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[] { head, neck, body1, body2, body3, tail1, tail2, tail3, tail4 };

        model.chainSwing(bodyParts, 1F * scaleFactor, 0.2F, -3, f, f1);
        head.rotationPointX -= 6 * f1 * Math.sin(f * scaleFactor);
        model.walk(rightFrontFlipper, 1 * scaleFactor, 0.6F, false, 0F, 0F, f, f1);
        model.walk(leftFrontFlipper, 1 * scaleFactor, 0.6F, false, 0F, 0F, f, f1);
        model.walk(leftBackFlipper, 1 * scaleFactor, 0.6F, false, -1F, 0F, f, f1);
        model.walk(rightBackFlipper, 1 * scaleFactor, 0.6F, false, -1F, 0F, f, f1);

        int ticksExisted = entity.ticksExisted;

        model.bob(head, 0.25F * scaleFactor, 5F, false, ticksExisted, 0.1F);

        model.walk(rightFrontFlipper, 0.25F * scaleFactor, 1.5F, false, 0F, 0F, ticksExisted, 0.1F);
        model.walk(leftFrontFlipper, 0.25F * scaleFactor, 1.5F, false, 0F, 0F, ticksExisted, 0.1F);
        model.walk(leftBackFlipper, 0.25F * scaleFactor, 1.5F, false, -1F, 0F, ticksExisted, 0.1F);
        model.walk(rightBackFlipper, 0.25F * scaleFactor, 1.5F, false, -1F, 0F, ticksExisted, 0.1F);

        entity.tailBuffer.applyChainSwingBuffer(bodyParts);
    }
}
