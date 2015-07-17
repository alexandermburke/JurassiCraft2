package net.timeless.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.timeless.jurassicraft.common.api.animation.Animator;

public class AnimationBrachiosaurus implements IModelAnimator
{
    private final Animator animator;

    public AnimationBrachiosaurus()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        MowzieModelRenderer head = model.getCube("head");

        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");
        MowzieModelRenderer neck5 = model.getCube("neck5");
        MowzieModelRenderer neck6 = model.getCube("neck6");

        model.faceTarget(neck1, 7, rotationYaw, rotationPitch);
        model.faceTarget(neck2, 7, rotationYaw, rotationPitch);
        model.faceTarget(neck3, 7, rotationYaw, rotationPitch);
        model.faceTarget(neck4, 7, rotationYaw, rotationPitch);
        model.faceTarget(neck5, 7, rotationYaw, rotationPitch);
        model.faceTarget(neck6, 7, rotationYaw, rotationPitch);
        model.faceTarget(head, 7, rotationYaw, rotationPitch);
    }
}
