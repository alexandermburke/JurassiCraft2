package org.jurassicraft.client.render.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jurassicraft.common.block.BlockOriented;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.tileentity.TileActionFigure;

public class SpecialRendererActionFigure extends TileEntitySpecialRenderer
{
    private Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_180535_8_, int p_180535_9_)
    {
        TileActionFigure tile = (TileActionFigure) tileEntity;

        World world = tileEntity.getWorld();

        IBlockState blockState = world.getBlockState(tile.getPos());

        if (blockState.getBlock() == JCBlockRegistry.action_figure)
        {
            Dinosaur dino = JCEntityRegistry.getDinosaurById(tile.dinosaur);

            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.translate(x + 0.5, y, z + 0.5);

            EnumFacing value = blockState.getValue(BlockOriented.FACING);

            if (value == EnumFacing.EAST)
            {
                value = EnumFacing.WEST;
            }
            else if (value == EnumFacing.WEST)
            {
                value = EnumFacing.EAST;
            }

            GlStateManager.rotate(value.getHorizontalIndex() * 90, 0, 1, 0);

            double scale = 0.15;
            GlStateManager.scale(scale, scale, scale);

            RenderLiving renderer = (RenderLiving) mc.getRenderManager().entityRenderMap.get(dino.getDinosaurClass());

            if (tile.entity != null)
            {
                renderer.doRender(tile.entity, 0, 0, 0, 0, 0);
            }
            else
            {
                tile.updateEntity();
            }

            GlStateManager.popMatrix();
        }
    }
}
