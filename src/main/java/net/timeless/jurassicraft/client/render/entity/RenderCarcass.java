package net.timeless.jurassicraft.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.entity.base.EntityCarcass;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class RenderCarcass extends Render
{
    public RenderCarcass()
    {
        super(Minecraft.getMinecraft().getRenderManager());
    }

    public void doRender(EntityCarcass carcass, double x, double y, double z, float p_180552_8_, float partialTicks)
    {
        GlStateManager.pushMatrix();

        EntityDinosaur dinosaur = carcass.getDinosaur();

        if(dinosaur != null)
        {
            RendererLivingEntity renderer = (RendererLivingEntity) renderManager.getEntityRenderObject(dinosaur);

            if(renderer != null)
            {
                GlStateManager.translate((float)x, (float)y + 0.25F, (float)z);

                float scale = 0.75F;

                GlStateManager.scale(scale, scale, scale);
                GlStateManager.scale(1.0F / scale, 1.0F / scale, 1.0F / scale);
                GlStateManager.scale(-1.0F, -1.0F, 1.0F);

                dinosaur.hurtTime = 0;
                dinosaur.deathTime = 20;

                GlStateManager.translate(-dinosaur.height / 4, -dinosaur.width / 4, dinosaur.height / 2);
                
                renderer.doRender(dinosaur, 0, 0, 0, 0, 0);
            }
        }

        GlStateManager.popMatrix();

        super.doRender(carcass, x, y, z, p_180552_8_, partialTicks);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityCarcass)entity, x, y, z, p_76986_8_, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return null;
    }
}