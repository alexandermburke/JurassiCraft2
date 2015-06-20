package net.ilexiconn.jurassicraft.client.model.animation;

import net.ilexiconn.jurassicraft.api.animation.Animator;
import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;

public class AnimationCarnotaurus implements IModelAnimator
{
    private final Animator animator;

    public AnimationCarnotaurus()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
    }
}
