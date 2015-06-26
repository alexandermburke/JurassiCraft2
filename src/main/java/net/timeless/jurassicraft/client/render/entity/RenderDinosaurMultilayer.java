package net.timeless.jurassicraft.client.render.entity;

import java.util.Random;

import net.ilexiconn.llibrary.client.render.entity.RenderMultiPart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.dinosaur.Dinosaur;
import net.timeless.jurassicraft.entity.EntityVelociraptor;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDinosaurMultilayer extends RenderMultiPart
{
    public Dinosaur dino;
    public ResourceLocation[] maleTextures;
    public ResourceLocation[] femaleTextures;
    public ResourceLocation[] maleOverlayTextures;
    public ResourceLocation[] femaleOverlayTextures;
    public Random random;

    public RenderDinosaurMultilayer(Dinosaur creature)
    {
        super(creature.getModel(), creature.getShadowSize());
        dino = creature;
        random = new Random();
        this.addLayer(new LayerDinosaurFeatures(this));

        maleTextures = new ResourceLocation[creature.getMaleTextures().length];
        femaleTextures = new ResourceLocation[creature.getFemaleTextures().length];
        
        maleOverlayTextures = new ResourceLocation[creature.getMaleOverlayTextures().length];
        femaleOverlayTextures = new ResourceLocation[creature.getFemaleOverlayTextures().length];

        int i = 0;

        for (String texture : creature.getMaleTextures())
        {
            maleTextures[i] = new ResourceLocation(texture);
            i++;
        }

        i = 0;

        for (String texture : creature.getFemaleTextures())
        {
            femaleTextures[i] = new ResourceLocation(texture);
            i++;
        }
        
        i = 0;

        for (String texture : creature.getMaleOverlayTextures())
        {
            maleOverlayTextures[i] = new ResourceLocation(texture);
            i++;
        }
        
        i = 0;

        for (String texture : creature.getFemaleOverlayTextures())
        {
            femaleOverlayTextures[i] = new ResourceLocation(texture);
            i++;
        }
    }

    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        GL11.glTranslatef(dino.getRenderXOffset(), dino.getRenderYOffset(), dino.getRenderZOffset());

        float scale = dino.getScaleAdjustment();
        shadowSize = scale * dino.getShadowSize();

        if (entity instanceof EntityVelociraptor && (entity.getCustomNameTag().equals("iLexiconn") || entity.getCustomNameTag().equals("JTGhawk137")))
            GL11.glScalef(scale - 0.86F, scale, scale);
        else
            GL11.glScalef(scale, scale, scale);

        if (entity.getCustomNameTag().equals("Gegy"))
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

            GL11.glScalef(0.5F + time * 0.5F, 1 + time * 0.5F, 0.9F + time * 0.25F);
        }
        else if (entity.getCustomNameTag().equals("Notch") || entity.getCustomNameTag().equals("Jumbo"))
        {
            GL11.glScalef(5, 5, 5);
        }
        else if (entity.getCustomNameTag().equals("jglrxavpok"))
        {
            GL11.glScalef(1, 1, -1);
        }
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
            this.renderer.bindTexture(entity.isMale() ? maleOverlayTextures[entity.getTexture()] : femaleOverlayTextures[entity.getTexture()]);
        
            this.renderer.getMainModel().render(entity, armSwing, armSwingAmount, p_177148_5_, p_177148_6_, p_177148_7_, partialTicks);
            this.renderer.func_177105_a(entity, p_177148_4_);
        }

        public boolean shouldCombineTextures()
        {
            return false;
        }

        public void doRenderLayer(EntityLivingBase entity, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
        {
            this.render((EntityDinosaur)entity, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
        }
    }
}