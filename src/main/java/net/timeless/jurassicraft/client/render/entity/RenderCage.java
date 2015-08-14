package net.timeless.jurassicraft.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.entity.item.EntityCage;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.json.TabulaModelHelper;

public class RenderCage extends Render
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/entities/cage/cage.png");
    private ModelJson model;

    public RenderCage()
    {
        super(Minecraft.getMinecraft().getRenderManager());

        try
        {
            model = new ModelJson(TabulaModelHelper.parseModel("/assets/jurassicraft/models/entities/cage/cage"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityCage) entity, x, y, z, p_76986_8_, partialTicks);
    }

    public void doRender(EntityCage cage, double x, double y, double z, float yaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        float scale = cage.getScale();

        GlStateManager.translate((float)x, (float)y + 1.5F * scale, (float)z);
        GlStateManager.rotate(180.0F - yaw, 0.0F, 1.0F, 0.0F);

        if(cage.getEntity() != null)
        {
            Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(cage.getEntity(), 0.0D, -1.45D, 0.0D, 0.0F, 0.0F);
        }

        float f4 = 0.75F * scale;
        GlStateManager.scale(f4, f4, f4);
        GlStateManager.scale(1.0F / f4, 1.0F / f4, 1.0F / f4);
        this.bindEntityTexture(cage);
        GlStateManager.scale(-1.0F * scale, -1.0F * scale, 1.0F * scale);
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
