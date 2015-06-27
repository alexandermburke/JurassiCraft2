package net.timeless.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.timeless.jurassicraft.api.animation.Animator;

public class AnimationGallimimus implements IModelAnimator
{
    private final Animator animator;

    public AnimationGallimimus()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");
        MowzieModelRenderer neck5 = model.getCube("neck5");

        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");
        MowzieModelRenderer tail6 = model.getCube("tail6");

        MowzieModelRenderer body1 = model.getCube("body1");
        MowzieModelRenderer body2 = model.getCube("body2");
        MowzieModelRenderer body3 = model.getCube("body3");

        MowzieModelRenderer head = model.getCube("Head Base");

        MowzieModelRenderer thighRight = model.getCube("thigh right");
        MowzieModelRenderer thighLeft = model.getCube("thigh left");

        MowzieModelRenderer legRight = model.getCube("leg right");
        MowzieModelRenderer legLeft = model.getCube("leg left");

        MowzieModelRenderer lowerLegRight = model.getCube("upperfoot right");
        MowzieModelRenderer lowerLegLeft = model.getCube("upperfoot left");

        MowzieModelRenderer footRight = model.getCube("foot right");
        MowzieModelRenderer footLeft = model.getCube("foot left");

        MowzieModelRenderer[] body = new MowzieModelRenderer[] { body3, body2, body1, neck1, neck2, neck3, neck4, neck5 };

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail1, tail2, tail3, tail4, tail5, tail6 };
    }
}
