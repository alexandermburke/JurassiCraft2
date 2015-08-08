package net.timeless.jurassicraft.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;

public class TileEntityRendererCage extends TileEntitySpecialRenderer
{
    private static final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void renderTileEntityAt(TileEntity tile, double posX, double posY, double posZ, float partialTicks, int p_180535_9_)
    {
        mc.getBlockRendererDispatcher().renderBlock(JCBlockRegistry.cage_small.getDefaultState(), tile.getPos(), tile.getWorld(), Tessellator.getInstance().getWorldRenderer());
    }
}
