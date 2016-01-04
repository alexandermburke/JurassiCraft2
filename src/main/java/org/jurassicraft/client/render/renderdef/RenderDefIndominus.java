package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.renderer.entity.RenderLiving;
import org.jurassicraft.client.model.animation.AnimationIndominus;
import org.jurassicraft.client.render.entity.RenderIndominus;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

public class RenderDefIndominus extends RenderDinosaurDefinition
{
    public RenderDefIndominus(float adultScaleAdjustment, float babyScaleAdjustment, float parShadowSize)
    {
        super(JCEntityRegistry.indominus, new AnimationIndominus(), adultScaleAdjustment, babyScaleAdjustment, parShadowSize, 0.0F, 0.0F, 0.0F);
    }

    public RenderLiving getRenderer()
    {
        return new RenderIndominus(this);
    }
}
