package net.timeless.jurassicraft.client.model.animation.vehicle;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityApatosaurus;
import net.timeless.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationHelicopter implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        EntityHelicopterBase helicopter = (EntityHelicopterBase) entity;

        MowzieModelRenderer rotor = model.getCube("rotorbase_rotatehere");
        rotor.rotateAngleY += helicopter.getEnginePower()*10f;
    }
}
