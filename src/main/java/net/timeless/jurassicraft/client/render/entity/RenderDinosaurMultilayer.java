package net.timeless.jurassicraft.client.render.entity;

import java.util.Random;

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
import net.timeless.jurassicraft.dinosaur.Dinosaur;
import net.timeless.jurassicraft.entity.EntityVelociraptor;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDinosaurMultilayer extends RenderLiving
{
    public Dinosaur dinosaur;
    public RenderDinosaurDefinition renderDef;
    public ResourceLocation[] maleTextures;
    public ResourceLocation[] femaleTextures;
    public ResourceLocation[] maleOverlayTextures;
    public ResourceLocation[] femaleOverlayTextures;
    public Random random;

    public RenderDinosaurMultilayer(Dinosaur dinosaur, RenderDinosaurDefinition renderDef)
    {
        super(Minecraft.getMinecraft().getRenderManager(), renderDef.getModel(), renderDef.getShadowSize());
        this.addLayer(new LayerDinosaurFeatures(this));

        this.dinosaur = dinosaur;
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

    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        EntityDinosaur entityDinosaur = (EntityDinosaur) entity;

        float scale = (float) entityDinosaur.transitionFromAge(renderDef.getBabyScaleAdjustment(), renderDef.getAdultScaleAdjustment());
        shadowSize = scale * renderDef.getShadowSize();

        GL11.glTranslatef(renderDef.getRenderXOffset() * scale, renderDef.getRenderYOffset() * scale, renderDef.getRenderZOffset() * scale);

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
        return entity.isMale() ? maleTextures[entity.getTexture()] : femaleTextures[entity.getTexture()];
    }

    public ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityDinosaur) entity);
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