package net.timeless.jurassicraft.client.render.entity;

import java.util.Random;

import net.ilexiconn.llibrary.client.render.entity.RenderMultiPart;
import net.minecraft.client.renderer.GlStateManager;
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
public class RenderDinosaur extends RenderMultiPart
{
    public Dinosaur dino;
    public ResourceLocation[] maleTextures;
    public ResourceLocation[] femaleTextures;
    public Random random;

    public RenderDinosaur(Dinosaur creature)
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
        GL11.glTranslatef(dino.getRenderXOffset(), dino.getRenderYOffset(), dino.getRenderZOffset());

        float scale = dino.getScaleAdjustment();
        shadowSize = scale * dino.getShadowSize();

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

}