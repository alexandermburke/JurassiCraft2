package org.jurassicraft.client.render.entity;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jurassicraft.client.render.renderdef.RenderDinosaurDefinition;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;

@SideOnly(Side.CLIENT)
public class RenderDinosaurMultilayer extends RenderDinosaur
{
    public ResourceLocation[][] maleOverlayTextures;
    public ResourceLocation[][] femaleOverlayTextures;

    public RenderDinosaurMultilayer(RenderDinosaurDefinition renderDef)
    {
        super(renderDef);
        this.addLayer(new LayerDinosaurFeatures(this));

        this.maleOverlayTextures = new ResourceLocation[dinosaur.getMaleOverlayTextures(EnumGrowthStage.INFANT).length][EnumGrowthStage.values().length];
        this.femaleOverlayTextures = new ResourceLocation[dinosaur.getFemaleOverlayTextures(EnumGrowthStage.INFANT).length][EnumGrowthStage.values().length];

        for (EnumGrowthStage stage : EnumGrowthStage.values())
        {
            int i = 0;

            for (String texture : dinosaur.getMaleOverlayTextures(stage))
            {
                this.maleOverlayTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }

            i = 0;

            for (String texture : dinosaur.getFemaleOverlayTextures(stage))
            {
                this.femaleOverlayTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }
        }
    }

    @Override
    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        super.preRenderCallback(entity, side);
        EntityDinosaur dinosaur = (EntityDinosaur) entity;
        int variation = dinosaur.getGeneticVariant();
    }

    @SideOnly(Side.CLIENT)
    public class LayerDinosaurFeatures implements LayerRenderer
    {
        private final RenderDinosaurMultilayer renderer;

        public LayerDinosaurFeatures(RenderDinosaurMultilayer renderer)
        {
            this.renderer = renderer;
        }

        public void render(EntityDinosaur entity, float armSwing, float armSwingAmount, float p_177148_4_, float p_177148_5_, float p_177148_6_, float p_177148_7_, float partialTicks)
        {
            if (!entity.isInvisible())
            {
                int texture = entity.getTexture();
                int variation = entity.getGeneticVariant();

                if (entity.isMale())
                {
                    if (texture > maleOverlayTextures.length)
                    {
                        texture = maleOverlayTextures.length;
                    }

                    this.renderer.bindTexture(maleOverlayTextures[texture][entity.getGrowthStage().ordinal()]);
                }
                else
                {
                    if (texture > femaleOverlayTextures.length)
                    {
                        texture = femaleOverlayTextures.length;
                    }

                    this.renderer.bindTexture(femaleOverlayTextures[texture][entity.getGrowthStage().ordinal()]);
                }

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
