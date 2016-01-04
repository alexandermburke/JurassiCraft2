package org.jurassicraft.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.timeless.animationapi.client.model.json.ModelJson;
import net.timeless.animationapi.client.model.json.TabulaModelHelper;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.item.EntityCageSmall;
import org.lwjgl.opengl.GL11;

public class RenderCageSmall implements IRenderFactory<EntityCageSmall>
{
    @Override
    public Render<? super EntityCageSmall> createRenderFor(RenderManager manager)
    {
        return new Renderer();
    }

    public static class Renderer extends Render<EntityCageSmall>
    {
        private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.MODID, "textures/entities/cage_small/cage_small.png");
        private static final ResourceLocation texture_marine = new ResourceLocation(JurassiCraft.MODID, "textures/entities/cage_small/cage_small_marine.png");
        private ModelJson model;

        public Renderer()
        {
            super(Minecraft.getMinecraft().getRenderManager());

            String modelLoc = "/assets/jurassicraft/models/entities/cage_small/cage_small";
            try
            {
                model = new ModelJson(TabulaModelHelper.parseModel(modelLoc));
            }
            catch (Exception e)
            {
                JurassiCraft.instance.getLogger().fatal("Couldn't load the model " + modelLoc, e);
            }
        }

        @Override
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
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.model.render(cage, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GlStateManager.popMatrix();
            super.doRender(cage, x, y, z, yaw, partialTicks);
        }

        @Override
        protected ResourceLocation getEntityTexture(EntityCageSmall entity)
        {
            return entity.isMarine() ? texture_marine : texture;
        }
    }
}