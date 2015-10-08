package org.jurassicraft.client.render.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.render.renderdef.RenderDinosaurDefinition;
import org.jurassicraft.common.entity.EntityIndominus;

@SideOnly(Side.CLIENT)
public class RenderIndominus extends RenderDinosaurMultilayer
{
    public RenderIndominus(RenderDinosaurDefinition renderDef)
    {
        super(renderDef);
    }

    @Override
    public void doRender(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        super.preRenderCallback(entity, side);

        EntityIndominus iRex = (EntityIndominus) entity;
        float[] color = iRex.getSkinColor();
        GlStateManager.color(color[0], color[1], color[2], 1.0F);
    }

}
