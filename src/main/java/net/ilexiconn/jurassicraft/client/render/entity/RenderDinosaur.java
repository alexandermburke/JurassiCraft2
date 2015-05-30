package net.ilexiconn.jurassicraft.client.render.entity;

import net.ilexiconn.jurassicraft.dinosaur.Dinosaur;
import net.ilexiconn.jurassicraft.entity.base.EntityDinosaur;
import net.ilexiconn.llibrary.client.render.entity.RenderMultiPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class RenderDinosaur extends RenderMultiPart
{
    public Dinosaur dino;
    public ResourceLocation maleTexture;
    public ResourceLocation femaleTexture;
    public Random random;

    public RenderDinosaur(Dinosaur creature) throws Exception
    {
        super(creature.getModel(), creature.getShadowSize());
        dino = creature;
        random = new Random();
        maleTexture = new ResourceLocation(creature.getMaleTextures()[0]); // TODO: Random textures
        femaleTexture = new ResourceLocation(creature.getFemaleTextures()[0]);
    }

    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        float scale = dino.getScaleAdjustment();
        shadowSize = scale * dino.getShadowSize();
        GL11.glScalef(scale, scale, scale);
    }

    public ResourceLocation getEntityTexture(EntityDinosaur entity)
    {
        return entity.isMale() ? maleTexture : femaleTexture;
    }

    public ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityDinosaur) entity);
    }

}