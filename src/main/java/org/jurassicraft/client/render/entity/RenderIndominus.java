package org.jurassicraft.client.render.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.render.renderdef.RenderDinosaurDefinition;
import org.jurassicraft.common.dinosaur.DinosaurIndominus;
import org.jurassicraft.common.entity.EntityIndominus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class RenderIndominus extends RenderDinosaur
{
    public RenderIndominus(RenderDinosaurDefinition renderDef)
    {
        super(renderDef);

        addLayer(new LayerIndominusCamo(this));
    }

    @Override
    public void doRender(EntityDinosaur entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void preRenderCallback(EntityDinosaur entity, float side)
    {
        super.preRenderCallback(entity, side);

        EntityIndominus indominus = (EntityIndominus) entity;
        float[] color = indominus.getSkinColor();
        GlStateManager.color(color[0], color[1], color[2], 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public class LayerIndominusCamo implements LayerRenderer
    {
        private final RenderIndominus renderer;

        public LayerIndominusCamo(RenderIndominus renderer)
        {
            this.renderer = renderer;
        }

        public void render(EntityDinosaur entity, float armSwing, float armSwingAmount, float p_177148_4_, float p_177148_5_, float p_177148_6_, float p_177148_7_, float partialTicks)
        {
            if (!entity.isInvisible())
            {
                DinosaurIndominus indominusDino = (DinosaurIndominus) dinosaur;

                this.renderer.bindTexture(indominusDino.getCamoTexture());

                this.renderer.getMainModel().render(entity, armSwing, armSwingAmount, p_177148_5_, p_177148_6_, p_177148_7_, partialTicks);
                this.renderer.func_177105_a(entity, p_177148_4_);
            }
        }

        @Override
        public boolean shouldCombineTextures()
        {
            return true;
        }

        @Override
        public void doRenderLayer(EntityLivingBase entity, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
        {
            this.render((EntityDinosaur) entity, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
        }
    }
}
