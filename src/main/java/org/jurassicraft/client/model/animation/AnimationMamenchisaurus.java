package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurMamenchisaurus;
import org.jurassicraft.common.entity.EntityMamenchisaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationMamenchisaurus extends DinosaurAnimator
{
    public AnimationMamenchisaurus()
    {
        super(new DinosaurMamenchisaurus());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
		EntityMamenchisaurus entity = (EntityMamenchisaurus) parEntity;
        Animator animator = parModel.animator;
    }
}
