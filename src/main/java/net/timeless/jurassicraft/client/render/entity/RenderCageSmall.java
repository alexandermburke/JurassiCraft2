package net.timeless.jurassicraft.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.entity.item.EntityCageSmall;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.json.TabulaModelHelper;

public class RenderCageSmall extends Render
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/entities/cage_small/cage_small.png");
    private ModelJson model;

    public RenderCageSmall()
    {
        super(Minecraft.getMinecraft().getRenderManager());

        try
        {
            model = new ModelJson(TabulaModelHelper.parseModel("/assets/jurassicraft/models/entities/cage_small/cage_small"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityCageSmall) entity, x, y, z, p_76986_8_, partialTicks);
    }

    public void doRender(EntityCageSmall cage, double x, double y, double z, float yaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y + 1.5F, (float) z);
        GlStateManager.rotate(180.0F - yaw, 0.0F, 1.0F, 0.0F);

        if (cage.getEntity() != null)
        {
            Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(cage.getEntity(), 0.0D, -1.45D, 0.0D, 0.0F, 0.0F);
        }

        float f4 = 0.75F;
        GlStateManager.scale(f4, f4, f4);
        GlStateManager.scale(1.0F / f4, 1.0F / f4, 1.0F / f4);
        this.bindEntityTexture(cage);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        this.model.render(cage, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
        super.doRender(cage, x, y, z, yaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
}
