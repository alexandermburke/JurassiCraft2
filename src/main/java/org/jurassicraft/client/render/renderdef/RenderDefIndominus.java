package org.jurassicraft.client.render.renderdef;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import org.jurassicraft.client.model.animation.AnimationIndominus;
import org.jurassicraft.client.render.entity.RenderIndominus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

public class RenderDefIndominus extends RenderDinosaurDefinition
{
    public RenderDefIndominus(float adultScaleAdjustment, float babyScaleAdjustment, float parShadowSize)
    {
        super(JCEntityRegistry.indominus, new AnimationIndominus(), adultScaleAdjustment, babyScaleAdjustment, parShadowSize, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public Render<? super EntityDinosaur> createRenderFor(RenderManager manager)
    {
        return new RenderIndominus(this);
    }
}
