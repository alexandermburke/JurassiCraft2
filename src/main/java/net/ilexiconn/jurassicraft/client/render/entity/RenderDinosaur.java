package net.ilexiconn.jurassicraft.client.render.entity;

import java.util.Random;

import net.ilexiconn.jurassicraft.dinosaur.Dinosaur;
import net.ilexiconn.jurassicraft.entity.EntityVelociraptor;
import net.ilexiconn.jurassicraft.entity.base.EntityDinosaur;
import net.ilexiconn.llibrary.client.render.entity.RenderMultiPart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDinosaur extends RenderMultiPart
{
    public Dinosaur dino;
    public ResourceLocation[] maleTextures;
    public ResourceLocation[] femaleTextures;
    public Random random;

    public RenderDinosaur(Dinosaur creature) throws Exception
    {
        super(creature.getModel(), creature.getShadowSize());
        dino = creature;
        random = new Random();

        maleTextures = new ResourceLocation[creature.getMaleTextures().length];
        femaleTextures = new ResourceLocation[creature.getFemaleTextures().length];

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
    }

    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        float scale = dino.getScaleAdjustment();
        shadowSize = scale * dino.getShadowSize();
        if (entity instanceof EntityVelociraptor && (entity.getCustomNameTag().equals("iLexiconn") || entity.getCustomNameTag().equals("JTGhawk137")))
        {
            GL11.glScalef(scale - 0.86F, scale, scale);
        }
        else
        {
            GL11.glScalef(scale, scale, scale);
        }

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
        else if(entity.getCustomNameTag().equals("Notch") || entity.getCustomNameTag().equals("Jumbo"))
        {
            GL11.glScalef(5, 5, 5);
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

}