package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationSegisaurus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

        float globalSpeed = 1.0F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;

        MowzieModelRenderer head = model.getCube("head");
        MowzieModelRenderer neck1 = model.getCube("neck1");

        model.faceTarget(head, 2, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 2, rotationYaw, rotationPitch);
    }
}
