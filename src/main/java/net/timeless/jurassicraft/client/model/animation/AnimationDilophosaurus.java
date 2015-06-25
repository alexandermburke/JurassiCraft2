package net.timeless.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.timeless.jurassicraft.api.animation.Animator;

public class AnimationDilophosaurus implements IModelAnimator
{
    private final Animator animator;

    public AnimationDilophosaurus()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        boolean scary = false;

        ModelRenderer frillLeftBottom = model.getCube("frill4");
        ModelRenderer frillLeftTop = model.getCube("frill2");

        ModelRenderer frillRightBottom = model.getCube("frill3");
        ModelRenderer frillRightTop = model.getCube("frill1");

        frillLeftBottom.showModel = scary;
        frillLeftTop.showModel = scary;
        frillRightBottom.showModel = scary;
        frillRightTop.showModel = scary;

        frillLeftTop.rotateAngleY = (float) Math.toRadians(180);
        frillLeftTop.rotationPointX += 10F;

        frillLeftBottom.rotateAngleY = (float) Math.toRadians(180);
        frillLeftBottom.rotationPointX += 10F;

        MowzieModelRenderer head = model.getCube("head");

        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");

        MowzieModelRenderer body1 = model.getCube("body1");
        MowzieModelRenderer body2 = model.getCube("body2");
        MowzieModelRenderer bodymain = model.getCube("body3");

        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");

        MowzieModelRenderer[] neck = new MowzieModelRenderer[] { neck1, neck2, neck3, neck4, head };
        MowzieModelRenderer[] body = new MowzieModelRenderer[] { body1, body2, bodymain };
        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail1, tail2, tail3, tail4, tail5 };

        int ticksExisted = entity.ticksExisted;

        model.chainWave(neck, 0.05F, 0.01F, 0, ticksExisted, 1);
        model.chainWave(body, 0.05F, 0.01F, 0, ticksExisted, 1);

        model.chainWave(tail, 0.055F, 0.02F, 0, ticksExisted, 1);
    }
}
