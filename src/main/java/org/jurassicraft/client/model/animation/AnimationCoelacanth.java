package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.common.animation.Animator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationCoelacanth extends DinosaurAnimator
{
    public AnimationCoelacanth()
    {
        super(JCEntityRegistry.coelacanth);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur entity)
    {
        Animator animator = model.animator;
        // NOTES: Because the animation does not need to be synced to the ground, global variables are not needed.

        // NOTES: The thing about aquatic creatures is that they are literally tails. Their whole bodies, including their heads, are single tails. Treat them as such.
        // tail
        MowzieModelRenderer head = model.getCube("headJoint"); // NOTES: Add in joint boxes to make sure rotations are level. You'll know when you need to!
        MowzieModelRenderer neck = model.getCube("neckJoint");
        MowzieModelRenderer body1 = model.getCube("Body Section 1");
        MowzieModelRenderer body2 = model.getCube("Body Section 2");
        MowzieModelRenderer body3 = model.getCube("Body Section 3");
        MowzieModelRenderer tail1 = model.getCube("Tail Section 1");
        MowzieModelRenderer tail2 = model.getCube("Tail Section 2");
        MowzieModelRenderer tail3 = model.getCube("Tail Section 3");
        // flipper
        MowzieModelRenderer leftFlipper = model.getCube("Left Front Flipper");
        MowzieModelRenderer rightFlipper = model.getCube("Right Front Flipper");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail3, tail2, tail1, body3, body2, body1, neck, head };

        // f = entity.ticksExisted;
        // f1 = 0.4f;

        // NOTES: A fish's movement involves moving its head side to side, which sends a wave impulse down its tail. Its fins move back in forth and up and down in a symmetrical rythm, too.
        head.rotationPointX -= -4 * f1 * Math.sin((f + 1) * 0.6); // Head moves side to side
        model.chainSwing(tail, 0.6F, 0.4F, 3.0D, f, f1); // and the tail follows with a delay.

        model.walk(leftFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);
        model.walk(rightFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);

        model.flap(leftFlipper, 0.6F, 0.6F, false, 0.0F, 0.8F, f, f1);
        model.flap(rightFlipper, 0.6F, 0.6F, true, 0.0F, -0.8F, f, f1);

        int ticksExisted = entity.ticksExisted;
        model.bob(head, 0.04F, 2.0F, false, ticksExisted, 1.0F);

        model.walk(leftFlipper, 0.2F, 0.25F, false, 1.0F, 0.1F, ticksExisted, 1.0F);

        model.walk(rightFlipper, 0.2F, 0.25F, false, 1.0F, 0.1F, ticksExisted, 1.0F);

        model.chainSwing(tail, 0.05F, -0.075F, 1.5D, ticksExisted, 1.0F);
        // ((EntityCoelacanth)entity).tailBuffer.applyChainSwingBuffer(this.bodyParts); //Tail buffer does not exist right now. Apply once added.
    }
}
