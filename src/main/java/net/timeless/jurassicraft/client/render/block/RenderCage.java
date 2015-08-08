package net.timeless.jurassicraft.client.render.block;

import eu.thog92.isbrh.example.BlockExample;
import eu.thog92.isbrh.render.ISimpleBlockRenderingHandler;
import eu.thog92.isbrh.render.SimpleBlockRender;
import eu.thog92.isbrh.render.TextureLoader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.timeless.jurassicraft.common.block.BlockCage;

public class RenderCage implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, int renderId)
    {
        BlockCage block = (BlockCage) Block.getBlockFromItem(itemStack.getItem());
//
//        Tessellator tessellator = Tessellator.getInstance();
//        SimpleBlockRender render = new SimpleBlockRender();
//        render.worldRenderer = tessellator.getWorldRenderer();
//        render.setRenderBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.1F, 0.8F);
//        this.renderInInventory(tessellator, render, block, transformType);
//
//        render.setRenderBounds(0.45F, 0.1F, 0.45F, 0.55F, 0.8F, 0.55F);
//        this.renderInInventory(tessellator, render, block, transformType);
//
//        render.setRenderBounds(0.0F, 0.8F, 0.0F, 1F, 0.9F, 1F);
//        this.renderInInventory(tessellator, render, block, transformType);

    }

    private void renderInInventory(Tessellator tessellator, SimpleBlockRender render, BlockExample block, ItemCameraTransforms.TransformType transformType)
    {
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        GlStateManager.pushMatrix();

        if (transformType.equals(ItemCameraTransforms.TransformType.THIRD_PERSON))
        {
            GlStateManager.scale(0.55F, 0.55F, 0.55F);
            GlStateManager.rotate(45, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(-1.4F, -1.9F, -1F);
        }

        render.renderInventoryStandardBlock(block, block.getDefaultState(), tessellator);
        GlStateManager.popMatrix();
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
    }


    @Override
    public boolean renderWorldBlock(IBlockAccess world, BlockPos pos, IBlockState state, int renderId, WorldRenderer renderer)
    {
        Block block = state.getBlock();
        if(!(block instanceof BlockCage)) return false;

        BlockCage cage = (BlockCage) block;
//        SimpleBlockRender render = new SimpleBlockRender();
//        render.renderAllFaces = true;
//        render.worldRenderer = renderer;
//
//        render.setRenderBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.1F, 0.8F);
//        render.renderStandardBlock(cage, pos);
//
//        render.setRenderBounds(0.45F, 0.1F, 0.45F, 0.55F, 0.8F, 0.55F);
//        render.renderStandardBlock(cage, pos);
//
//        render.setRenderBounds(0.0F, 0.8F, 0.0F, 1F, 0.9F, 1F);
//        render.renderStandardBlock(cage, pos);

        return true;
    }

    @Override
    public void renderBlockBrightness(int renderId, IBlockState state, float brightness)
    {
        // TODO: really use the render
        Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(Blocks.sand.getDefaultState(), brightness);
    }

    @Override
    public boolean shouldRender3DInInventory(int renderId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return JCBlockRenderingRegistry.cageID;
    }

    @Override
    public void loadTextures(TextureLoader loader)
    {

    }

    @Override
    public TextureAtlasSprite getSidedTexture(IBlockState state, EnumFacing facing)
    {
        return null;
    }
}
