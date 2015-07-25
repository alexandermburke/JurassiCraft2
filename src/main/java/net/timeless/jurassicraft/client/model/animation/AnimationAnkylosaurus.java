package net.timeless.jurassicraft.client.model.animation;

import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import net.minecraft.entity.Entity;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;

public class AnimationAnkylosaurus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;
    }
}
