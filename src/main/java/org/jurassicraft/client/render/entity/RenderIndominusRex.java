package org.jurassicraft.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.render.renderdef.RenderDinosaurDefinition;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.EntityIndominusRex;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class RenderIndominusRex extends RenderLiving implements IDinosaurRenderer
{
    public Dinosaur dinosaur;
    public RenderDinosaurDefinition renderDef;
    public ResourceLocation[][] maleTextures;
    public ResourceLocation[][] femaleTextures;
    public Random random;

    public RenderIndominusRex(RenderDinosaurDefinition renderDef)
    {
        super(Minecraft.getMinecraft().getRenderManager(), renderDef.getModel(EnumGrowthStage.INFANT), renderDef.getShadowSize());

        this.dinosaur = renderDef.getDinosaur();
        this.random = new Random();
        this.renderDef = renderDef;

        this.maleTextures = new ResourceLocation[dinosaur.getMaleTextures(EnumGrowthStage.INFANT).length][EnumGrowthStage.values().length];
        this.femaleTextures = new ResourceLocation[dinosaur.getFemaleTextures(EnumGrowthStage.INFANT).length][EnumGrowthStage.values().length];

        for (EnumGrowthStage stage : EnumGrowthStage.values())
        {
            int i = 0;

            for (String texture : dinosaur.getMaleTextures(stage))
            {
                this.maleTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }

            i = 0;

            for (String texture : dinosaur.getFemaleTextures(stage))
            {
                this.femaleTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }
        }
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }

    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        EntityDinosaur entityDinosaur = (EntityDinosaur) entity;

        float scale = (float) entityDinosaur.transitionFromAge(renderDef.getBabyScaleAdjustment(), renderDef.getAdultScaleAdjustment());
//        scale *= (((float) entityDinosaur.getScaleOffset()) * 0.09F);
        shadowSize = scale * renderDef.getShadowSize();

        GlStateManager.color(1.0F, 1.0F, 1.0F);

        EntityIndominusRex iRex = (EntityIndominusRex) entity;

        if (iRex.isCamouflaging())
        {
            // int color = BiomeColorHelper.getGrassColorAtPos(entity.worldObj, entity.getPosition()); //BlockGrass
            // float red = (float)(color >> 16 & 255) / 255.0F;
            // float green = (float)(color >> 8 & 255) / 255.0F;
            // float blue = (float)(color & 255) / 255.0F;

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//            float[] color = iRex.getSkinColor();
//            GlStateManager.color(color[0], color[1], color[2], 0.7F);
        }

        GlStateManager.translate(renderDef.getRenderXOffset() * scale, renderDef.getRenderYOffset() * scale, renderDef.getRenderZOffset() * scale);

        String name = entity.getCustomNameTag();

        if (name.equals("Gegy"))
        {
            int ticksExisted = entity.ticksExisted / 25 + entity.getEntityId();
            int colorTypes = EnumDyeColor.values().length;
            int k = ticksExisted % colorTypes;
            int l = (ticksExisted + 1) % colorTypes;
            float time = ((float) (entity.ticksExisted % 25) + 2) / 25.0F;
            float[] colors = EntitySheep.func_175513_a(EnumDyeColor.byMetadata(k));
            float[] colors2 = EntitySheep.func_175513_a(EnumDyeColor.byMetadata(l));
            GlStateManager.color(colors[0] * (1.0F - time) + colors2[0] * time, colors[1] * (1.0F - time) + colors2[1] * time, colors[2] * (1.0F - time) + colors2[2] * time);

            if (time > 0.5F)
                time = 1 - time;

            GlStateManager.scale(scale * (0.5F + time * 0.5F), scale * (1 + time * 0.5F), scale * (0.9F + time * 0.25F));
        }
        else if (name.equals("Notch") || name.equals("Jumbo"))
            GlStateManager.scale(scale * 2, scale * 2, scale * 2);
        else if (name.equals("jglrxavpok"))
            GlStateManager.scale(scale, scale, scale * -1);
        else
            GlStateManager.scale(scale, scale, scale);
    }

    public ResourceLocation getEntityTexture(EntityDinosaur entity)
    {
        return entity.isMale() ? maleTextures[entity.getTexture()][entity.getGrowthStage().ordinal()] : femaleTextures[entity.getTexture()][entity.getGrowthStage().ordinal()];
    }

    public ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityDinosaur) entity);
    }

    @Override
    public void setModel(ModelBase model)
    {
        this.mainModel = model;
    }

    @Override
    public RenderDinosaurDefinition getRenderDef()
    {
        return renderDef;
    }
}