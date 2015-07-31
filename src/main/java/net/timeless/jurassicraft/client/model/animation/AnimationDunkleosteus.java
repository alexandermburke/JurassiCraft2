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
public class AnimationDunkleosteus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

        float globalSpeed = 0.2F;
        float globalDegree = 0.77F;
        float globalHeight = 2F;

        f = entity.ticksExisted;
        f1 = 1F;

        //tail
        MowzieModelRenderer tail1 = model.getCube("Tail Section 1");
        MowzieModelRenderer tail2 = model.getCube("Tail Section 2");
        MowzieModelRenderer tail3 = model.getCube("Tail Section 3");
        MowzieModelRenderer tail4 = model.getCube("Tail Section 4");
        MowzieModelRenderer tail5 = model.getCube("Tail Section 5");
        MowzieModelRenderer tail6 = model.getCube("Tail Section 6");

        //body
        MowzieModelRenderer body2 = model.getCube("Body Section 2");
        MowzieModelRenderer body3 = model.getCube("Body Section 3");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1, body3, body2 };

        model.chainSwing(tail, 0.5F * globalSpeed, -0.1F, 2, f, f1);

        int ticksExisted = entity.ticksExisted;

        model.chainSwing(tail, 0.15F, -0.1F, 3, ticksExisted, 1.0F);
    }
}
