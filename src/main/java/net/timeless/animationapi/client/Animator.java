package net.timeless.animationapi.client;

import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.unilib.client.model.tools.MowzieModelBase;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import java.util.HashMap;

@SideOnly(Side.CLIENT)
public class Animator
{
    private MowzieModelBase modelBase;

    public Animator(MowzieModelBase model)
    {
        tempTick = 0;
        correctAnim = false;
        transformMap = new HashMap<MowzieModelRenderer, Transform>();
        prevTransformMap = new HashMap<MowzieModelRenderer, Transform>();
        modelBase = model;
    }

    public IAnimatedEntity getEntity()
    {
        return animEntity;
    }

    public void update(IAnimatedEntity entity)
    {
        tempTick = prevTempTick = 0;
        correctAnim = false;
        animEntity = entity;
        transformMap.clear();
        prevTransformMap.clear();

//        modelBase.setToInitPose();
    }

    public boolean setAnim(int animID)
    {
        tempTick = prevTempTick = 0;
        correctAnim = animEntity.getAnimID() == animID;
        return correctAnim;
    }

    public void startPhase(int duration)
    {
        if (!correctAnim)
            return;
        prevTempTick = tempTick;
        tempTick += duration;
    }

    public void setStationaryPhase(int duration)
    {
        startPhase(duration);
        endPhase(true);
    }

    public void resetPhase(int duration)
    {
        startPhase(duration);
        endPhase();
    }

    public void rotate(MowzieModelRenderer box, float x, float y, float z)
    {
        if (!correctAnim)
            return;
        if (!transformMap.containsKey(box))
            transformMap.put(box, new Transform(x, y, z));
        else
            transformMap.get(box).addRot(x, y, z);
    }

    public void move(MowzieModelRenderer box, float x, float y, float z)
    {
        if (!correctAnim)
            return;

        if (!transformMap.containsKey(box))
            transformMap.put(box, new Transform(x, y, z, 0F, 0F, 0F));
        else
            transformMap.get(box).addOffset(x, y, z);
    }

    public void endPhase()
    {
        endPhase(false);
    }

    private void endPhase(boolean stationary)
    {
        if (!correctAnim)
            return;
        int animTick = animEntity.getAnimTick();
        if (animTick >= prevTempTick && animTick < tempTick)
        {
            if (stationary)
            {
                for (MowzieModelRenderer box : prevTransformMap.keySet())
                {
                    Transform transform = prevTransformMap.get(box);
                    box.rotateAngleX += transform.rotX;
                    box.rotateAngleY += transform.rotY;
                    box.rotateAngleZ += transform.rotZ;
                    box.rotationPointX += transform.offsetX;
                    box.rotationPointY += transform.offsetY;
                    box.rotationPointZ += transform.offsetZ;
                }
            }
            else
            {
                float tick = (animTick - prevTempTick + AnimationAPI.getProxy().getPartialTick()) / (tempTick - prevTempTick);
                float inc = MathHelper.sin(tick * PI / 2F), dec = 1F - inc;

                for (MowzieModelRenderer box : prevTransformMap.keySet())
                {
                    Transform transform = prevTransformMap.get(box);
                    box.rotateAngleX += dec * transform.rotX;
                    box.rotateAngleY += dec * transform.rotY;
                    box.rotateAngleZ += dec * transform.rotZ;
                    box.rotationPointX += dec * transform.offsetX;
                    box.rotationPointY += dec * transform.offsetY;
                    box.rotationPointZ += dec * transform.offsetZ;
                }

                for (MowzieModelRenderer box : transformMap.keySet())
                {
                    Transform transform = transformMap.get(box);
                    box.rotateAngleX += inc * transform.rotX;
                    box.rotateAngleY += inc * transform.rotY;
                    box.rotateAngleZ += inc * transform.rotZ;
                    box.rotationPointX += inc * transform.offsetX;
                    box.rotationPointY += inc * transform.offsetY;
                    box.rotationPointZ += inc * transform.offsetZ;
                }
            }
        }
        if (!stationary)
        {
            prevTransformMap.clear();
            prevTransformMap.putAll(transformMap);
            transformMap.clear();
        }
    }

    private int tempTick, prevTempTick;
    private boolean correctAnim;
    private IAnimatedEntity animEntity;
    private HashMap<MowzieModelRenderer, Transform> transformMap, prevTransformMap;

    public static final float PI = (float) Math.PI;
}
