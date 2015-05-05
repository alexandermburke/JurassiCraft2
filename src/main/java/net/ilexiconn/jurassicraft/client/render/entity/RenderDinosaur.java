package net.ilexiconn.jurassicraft.client.render.entity;

import java.util.Random;

import net.ilexiconn.jurassicraft.entity.EntityDinosaur;
import net.ilexiconn.jurassicraft.json.JsonCreature;
import net.ilexiconn.llibrary.client.render.entity.RenderMultiPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDinosaur extends RenderMultiPart
{
    public JsonCreature jsonCreature;
    public ResourceLocation maleTexture;
    public ResourceLocation femaleTexture;
    public Random random;

    public RenderDinosaur(JsonCreature creature) throws Exception
    {
        super(creature.getModel(), creature.getShadowSize());
        jsonCreature = creature;
        random = new Random();
        maleTexture = new ResourceLocation(creature.getMaleTextures().get(0)); //TODO: Genders and random textures
        femaleTexture = new ResourceLocation(creature.getFemaleTextures().get(0));
    }

    public void preRenderCallback(EntityLivingBase entity, float side)
    {
        float scale = jsonCreature.getScaleAdjustment();
        shadowSize = scale * jsonCreature.getShadowSize();
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