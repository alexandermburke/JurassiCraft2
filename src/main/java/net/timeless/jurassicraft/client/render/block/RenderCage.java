package net.timeless.jurassicraft.client.render.block;

import eu.thog92.isbrh.example.BlockExample;
import eu.thog92.isbrh.render.ISimpleBlockRenderingHandler;
import eu.thog92.isbrh.render.SimpleBlockRender;
import eu.thog92.isbrh.render.TextureLoader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.IModel;
import net.timeless.jurassicraft.common.block.BlockCage;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;

import java.util.Iterator;
import java.util.List;

public class RenderCage implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, int renderId)
    {
        BlockCage block = (BlockCage) Block.getBlockFromItem(itemStack.getItem());

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, BlockPos pos, IBlockState state, int renderId, WorldRenderer renderer)
    {
        Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlock(JCBlockRegistry.amber_ore.getDefaultState(), pos, world, renderer);

        Minecraft.getMinecraft().getRenderManager().doRenderEntity(Minecraft.getMinecraft().thePlayer, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 2.0F, 0.0625F, false);

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
