package org.jurassicraft.client.model.animation.vehicle;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

@SideOnly(Side.CLIENT)
public class AnimationHelicopter implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        EntityHelicopterBase helicopter = (EntityHelicopterBase) entity;

        MowzieModelRenderer rotor = model.getCube("rotorbase_rotatehere");
        MowzieModelRenderer tailrotor = model.getCube("tailrotor_rotatehere");
        rotor.rotateAngleY += helicopter.getEnginePower() * partialTicks;
        float time = helicopter.getEnginePower() / EntityHelicopterBase.MAX_POWER;
        rotor.rotateAngleY = easeInCubic(time, rotor.rotateAngleY, helicopter.getEnginePower() * partialTicks, 1f);
        rotor.rotateAngleY %= 360f;

        tailrotor.rotateAngleX = easeInCubic(time, tailrotor.rotateAngleX, helicopter.getEnginePower() * partialTicks, 1f);
        tailrotor.rotateAngleX %= 360f;
    }

    private float easeInCubic(float time, float startValue, float change, float duration)
    {
        time /= duration;
        return change * time * time * time + startValue;
    }
}
