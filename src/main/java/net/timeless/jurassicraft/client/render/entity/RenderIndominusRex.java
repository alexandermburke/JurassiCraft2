package net.timeless.jurassicraft.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDinosaurDefinition;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.EntityIndominusRex;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class RenderIndominusRex extends RenderLiving
{
    public Dinosaur dinosaur;
    public RenderDinosaurDefinition renderDef;
    public ResourceLocation[] maleTextures;
    public ResourceLocation[] femaleTextures;
    public ResourceLocation[] maleOverlayTextures;
    public ResourceLocation[] femaleOverlayTextures;
    public Random random;

    public RenderIndominusRex(RenderDinosaurDefinition renderDef)
    {
        super(Minecraft.getMinecraft().getRenderManager(), renderDef.getModel(), renderDef.getShadowSize());

        this.addLayer(new LayerDinosaurFeatures(this));

        this.dinosaur = renderDef.getDinosaur();
        this.random = new Random();
        this.renderDef = renderDef;

        this.maleTextures = new ResourceLocation[dinosaur.getMaleTextures().length];
        this.femaleTextures = new ResourceLocation[dinosaur.getFemaleTextures().length];
        this.maleOverlayTextures = new ResourceLocation[dinosaur.getMaleOverlayTextures().length];
        this.femaleOverlayTextures = new ResourceLocation[dinosaur.getFemaleOverlayTextures().length];

        int i = 0;

        for (String texture : dinosaur.getMaleTextures())
        {
            this.maleTextures[i] = new ResourceLocation(texture);
            i++;
        }

        i = 0;

        for (String texture : dinosaur.getFemaleTextures())
        {
            this.femaleTextures[i] = new ResourceLocation(texture);
            i++;
        }

        i = 0;

        for (String texture : dinosaur.getMaleOverlayTextures())
        {
            this.maleOverlayTextures[i] = new ResourceLocation(texture);
            i++;
        }

        i = 0;

        for (String texture : dinosaur.getFemaleOverlayTextures())
        {
            this.femaleOverlayTextures[i] = new ResourceLocation(texture);
            i++;
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
        scale *= (((float) entityDinosaur.getScaleOffset()) * 0.09F);
        shadowSize = scale * renderDef.getShadowSize();

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
            float[] color = iRex.getSkinColor();
            GlStateManager.color(color[0], color[1], color[2], 0.7F);
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
        return entity.isMale() ? maleTextures[entity.getTexture()] : femaleTextures[entity.getTexture()];
    }

    public ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityDinosaur) entity);
    }

    @SideOnly(Side.CLIENT)
    public class LayerDinosaurFeatures implements LayerRenderer
    {
        private final RenderIndominusRex renderer;

        public LayerDinosaurFeatures(RenderIndominusRex renderer)
        {
            this.renderer = renderer;
        }

        public void render(EntityDinosaur entity, float armSwing, float armSwingAmount, float p_177148_4_, float p_177148_5_, float p_177148_6_, float p_177148_7_, float partialTicks)
        {
            if (!entity.isInvisible())
            {
                int texture = entity.getTexture();

                if (entity.isMale())
                {
                    if (texture > maleOverlayTextures.length)
                        texture = maleOverlayTextures.length;

                    this.renderer.bindTexture(maleOverlayTextures[texture]);
                }
                else
                {
                    if (texture > femaleOverlayTextures.length)
                        texture = femaleOverlayTextures.length;

                    this.renderer.bindTexture(femaleOverlayTextures[texture]);
                }

                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);

                this.renderer.getMainModel().render(entity, armSwing, armSwingAmount, p_177148_5_, p_177148_6_, p_177148_7_, partialTicks);
                this.renderer.func_177105_a(entity, p_177148_4_);
            }
        }

        public boolean shouldCombineTextures()
        {
            return true;
        }

        public void doRenderLayer(EntityLivingBase entity, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
        {
            this.render((EntityDinosaur) entity, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
        }
    }
}