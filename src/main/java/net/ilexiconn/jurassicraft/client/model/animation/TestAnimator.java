package net.ilexiconn.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;

public class TestAnimator implements IModelAnimator
{
    public void setRotationAngles(ModelJson model, float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
//        model.faceTarget(model.getCube("head"), 1, rotationYaw, rotationPitch);
    	if(!model.isAnimationInProgress())
    	{
    		model.startAnimation(0);
    	}
    }
}
