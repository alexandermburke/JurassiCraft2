package org.jurassicraft.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.model.animation.vehicle.AnimationHelicopter;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.json.TabulaModelHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHelicopter extends Render
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.MODID, "textures/entities/helicopter/ranger_helicopter_texture.png");
    private ModelJson minigunfulModel;
    private ModelJson minigunlessModel;

    public RenderHelicopter()
    {
        super(Minecraft.getMinecraft().getRenderManager());

        try
        {
            minigunlessModel = new ModelJson(TabulaModelHelper.parseModel("/assets/jurassicraft/models/entities/helicopter/ranger_helicopter"), new AnimationHelicopter());
            minigunlessModel.setResetEachFrame(false);

            minigunfulModel = new ModelJson(TabulaModelHelper.parseModel("/assets/jurassicraft/models/entities/helicopter/ranger_helicopter_minigun"), new AnimationHelicopter());
            minigunfulModel.setResetEachFrame(false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityHelicopterBase) entity, x, y, z, p_76986_8_, partialTicks);
    }

    public void doRender(EntityHelicopterBase helicopter, double x, double y, double z, float yaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y + 1.5F, (float) z);
        GlStateManager.rotate(180.0F - yaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(helicopter.rotationPitch, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(helicopter.getRoll(), 0.0F, 0.0F, 1.0F);

        float f4 = 1f;
        GlStateManager.scale(f4, f4, f4);
        GlStateManager.scale(1.0F / f4, 1.0F / f4, 1.0F / f4);
        this.bindEntityTexture(helicopter);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        if (helicopter.hasMinigun())
        {
            this.minigunfulModel.render(helicopter, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
        else
        {
            this.minigunlessModel.render(helicopter, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        }
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GlStateManager.popMatrix();
        super.doRender(helicopter, x, y, z, yaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }

}
