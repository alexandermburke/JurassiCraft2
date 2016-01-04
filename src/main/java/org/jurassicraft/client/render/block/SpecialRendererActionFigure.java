package org.jurassicraft.client.render.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jurassicraft.common.block.BlockOriented;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.tileentity.TileActionFigure;

public class SpecialRendererActionFigure extends TileEntitySpecialRenderer<TileActionFigure>
{
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void renderTileEntityAt(TileActionFigure tileEntity, double x, double y, double z, float p_180535_8_, int p_180535_9_)
    {
        World world = tileEntity.getWorld();

        IBlockState blockState = world.getBlockState(tileEntity.getPos());

        if (blockState.getBlock() == JCBlockRegistry.action_figure)
        {
            Dinosaur dino = JCEntityRegistry.getDinosaurById(tileEntity.dinosaur);

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

            RenderLiving<EntityDinosaur> renderer = (RenderLiving<EntityDinosaur>) mc.getRenderManager().entityRenderMap.get(dino.getDinosaurClass());

            if (tileEntity.entity != null)
            {
                renderer.doRender(tileEntity.entity, 0, 0, 0, 0, 0);
            }
            else
            {
                tileEntity.updateEntity();
            }

            GlStateManager.popMatrix();
        }
    }
}
