package net.timeless.jurassicraft.client.render.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.timeless.jurassicraft.common.block.BlockOriented;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.tileentity.TileDNAExtractor;

public class SpecialRendererDNAExtractor extends TileEntitySpecialRenderer
{
    private Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_180535_8_, int p_180535_9_)
    {
        TileDNAExtractor tile = (TileDNAExtractor) tileEntity;

        IBlockState blockState = tileEntity.getWorld().getBlockState(tile.getPos());

        if (blockState.getBlock() == JCBlockRegistry.dna_extractor)
        {
            GlStateManager.pushMatrix();
            mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.translate(x + 0.5, y, z + 0.5);

            EnumFacing value = (EnumFacing) blockState.getValue(BlockOriented.FACING);

            if (value == EnumFacing.EAST)
            {
                value = EnumFacing.WEST;
            }
            else if (value == EnumFacing.WEST)
            {
                value = EnumFacing.EAST;
            }

            GlStateManager.rotate(value.getHorizontalIndex() * 90, 0, 1, 0);
            GlStateManager.translate(0, 0.325, 0.1);

            GlStateManager.scale(1.25F, 1.25F, 1.25F);
            GlStateManager.rotate(90, 1, 0, 0);

            ItemStack extraction = tile.getStackInSlot(0);

            RenderItem renderItem = mc.getRenderItem();

            if (extraction != null)
            {
                renderItem.renderItem(extraction, renderItem.getItemModelMesher().getItemModel(extraction));
            }

            ItemStack disc = tile.getStackInSlot(1);

            if (disc != null)
            {
                GlStateManager.translate(0, 0, -0.45);
                GlStateManager.rotate(15, 1, 0, 0);

                if (tile.isProcessing(0))
                {
                    GlStateManager.rotate(mc.thePlayer.ticksExisted * 2 % 360, 0, 0, 1);
                }

                renderItem.renderItem(disc, renderItem.getItemModelMesher().getItemModel(disc));
            }

            GlStateManager.popMatrix();
        }
    }
}
