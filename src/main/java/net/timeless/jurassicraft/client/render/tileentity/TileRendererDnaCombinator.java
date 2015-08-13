package net.timeless.jurassicraft.client.render.tileentity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.model.block.ModelDnaCombinator;

public class TileRendererDnaCombinator extends TileEntitySpecialRenderer
{
    private ModelDnaCombinator model;
    private ResourceLocation texture;

    public TileRendererDnaCombinator()
    {
        super();
        this.texture = new ResourceLocation(JurassiCraft.modid, "textures/blocks/dna_combinator.png");
        this.model = new ModelDnaCombinator();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double posX, double posY, double posZ, float p_180535_8_, int p_180535_9_)
    {
        GlStateManager.pushMatrix();

        bindTexture(texture);
        model.render(null, 0, 0, 0, 0, 0, 0.0625F);

        GlStateManager.popMatrix();
    }
}
