package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurLeptictidium;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationLeptictidium extends DinosaurAnimator
{
    public AnimationLeptictidium()
    {
        super(new DinosaurLeptictidium());
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
    }
}
