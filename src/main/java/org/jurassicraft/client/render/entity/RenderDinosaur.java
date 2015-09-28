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
import net.timeless.animationapi.IAnimatedEntity;
import org.jurassicraft.client.render.renderdef.RenderDinosaurDefinition;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.EntityVelociraptor;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class RenderDinosaur extends RenderLiving implements IDinosaurRenderer
{
    public Dinosaur dinosaur;
    public RenderDinosaurDefinition renderDef;

    public ResourceLocation[][] maleTextures;
    public ResourceLocation[][] femaleTextures;
    public Random random;

    public RenderDinosaur(RenderDinosaurDefinition renderDef)
    {
        super(Minecraft.getMinecraft().getRenderManager(), renderDef.getModel(0, EnumGrowthStage.INFANT), renderDef.getShadowSize());

        this.dinosaur = renderDef.getDinosaur();
        this.random = new Random();
        this.renderDef = renderDef;

        this.maleTextures = new ResourceLocation[dinosaur.getMaleTextures(0, EnumGrowthStage.INFANT).length][4]; //TODO
        this.femaleTextures = new ResourceLocation[dinosaur.getFemaleTextures(0, EnumGrowthStage.INFANT).length][4];

        for (EnumGrowthStage stage : EnumGrowthStage.values())
        {
            int i = 0;

            for (String texture : dinosaur.getMaleTextures(0, stage))
            {
                this.maleTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }

            i = 0;

            for (String texture : dinosaur.getFemaleTextures(0, stage))
            {
                this.femaleTextures[i][stage.ordinal()] = new ResourceLocation(texture);
                i++;
            }
        }
    }

    @Override
    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        EntityDinosaur entityDinosaur = (EntityDinosaur) entity;

        int geneticVariant = entityDinosaur.getGeneticVariant();

        float scale = (float) entityDinosaur.transitionFromAge(renderDef.getBabyScaleAdjustment(), renderDef.getAdultScaleAdjustment());

//        scale *= (((float) entityDinosaur.getScaleOffset()) * 0.09F); TODO color offset and scale offset

//        float color = (((float) entityDinosaur.getColorOffset()) * 0.004F);
//
//        if(entityDinosaur.getColorOffset() % 2 == 0)
//        {
//            GL11.glColor3f(1.0F + color, 1.0F - color, 1.0F + color);
//        }
//        else
//        {
//            GL11.glColor3f(1.0F - color, 1.0F + color, 1.0F - color);
//        }

        shadowSize = scale * renderDef.getShadowSize();

        GL11.glTranslatef(renderDef.getRenderXOffset(geneticVariant) * scale, renderDef.getRenderYOffset(geneticVariant) * scale, renderDef.getRenderZOffset(geneticVariant) * scale);

        String name = entity.getCustomNameTag();

        if (entity instanceof EntityVelociraptor && (name.equals("iLexiconn") || name.equals("JTGhawk137")))
            GL11.glScalef(scale - 0.86F, scale, scale);
        else if (name.equals("Gegy"))
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

            GL11.glScalef(scale * (0.5F + time * 0.5F), scale * (1 + time * 0.5F), scale * (0.9F + time * 0.25F));
        }
        else if (name.equals("Notch") || name.equals("Jumbo"))
            GL11.glScalef(scale * 2, scale * 2, scale * 2);
        else if (name.equals("jglrxavpok"))
            GL11.glScalef(scale, scale, scale * -1);
        else
            GL11.glScalef(scale, scale, scale);
    }

    public ResourceLocation getEntityTexture(EntityDinosaur entity)
    {
        return entity.isMale() ? maleTextures[entity.getTexture()][entity.getGrowthStage().ordinal()] : femaleTextures[entity.getTexture()][entity.getGrowthStage().ordinal()];
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityDinosaur) entity);
    }

    @Override
    protected void rotateCorpse(EntityLivingBase parEntity, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        if (!(parEntity.deathTime > 0))
        {
            super.rotateCorpse(parEntity, p_77043_2_, p_77043_3_, p_77043_4_);
        }
        else
        {
            GlStateManager.rotate(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
        }
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