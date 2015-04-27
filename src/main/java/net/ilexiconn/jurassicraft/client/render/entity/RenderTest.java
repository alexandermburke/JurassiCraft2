package net.ilexiconn.jurassicraft.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.ilexiconn.jurassicraft.entity.json.JsonCreature;
import net.ilexiconn.llibrary.client.render.entity.RenderMultiPart;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderTest extends RenderMultiPart
{
    public ResourceLocation creeperTextures = new ResourceLocation("textures/entity/creeper/creeper.png");

    public RenderTest(JsonCreature creature)
    {
        super(new ModelCreeper(), 2f);
    }

    public ResourceLocation getEntityTexture(Entity entity)
    {
        return creeperTextures;
    }
}
