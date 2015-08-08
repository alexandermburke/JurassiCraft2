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
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.IModel;
import net.timeless.jurassicraft.common.block.BlockCage;

import java.util.Iterator;
import java.util.List;

public class RenderCage implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, int renderId)
    {
        BlockCage block = (BlockCage) Block.getBlockFromItem(itemStack.getItem());

        Minecraft minecraft = Minecraft.getMinecraft();
        RenderItem renderItem = minecraft.getRenderItem();
//        renderItem.renderItem(itemStack, renderItem.getItemModelMesher().getItemModel(itemStack));

        IBakedModel model = renderItem.getItemModelMesher().getItemModel(itemStack);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.setVertexFormat(DefaultVertexFormats.ITEM);
        EnumFacing[] aenumfacing = EnumFacing.values();
        int j = aenumfacing.length;

        for (int k = 0; k < j; ++k)
        {
            EnumFacing enumfacing = aenumfacing[k];
            this.renderQuads(worldrenderer, model.getFaceQuads(enumfacing), 0xFFFFFF, itemStack);
        }

        this.renderQuads(worldrenderer, model.getGeneralQuads(), 0xFFFFFF, itemStack);
        tessellator.draw();

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

    private void renderQuads(WorldRenderer renderer, List quads, int color, ItemStack stack)
    {
        boolean flag = color == -1 && stack != null;
        BakedQuad bakedquad;
        int j;

        for (Iterator iterator = quads.iterator(); iterator.hasNext(); this.renderQuad(renderer, bakedquad, j))
        {
            bakedquad = (BakedQuad)iterator.next();
            j = color;

            if (flag && bakedquad.hasTintIndex())
            {
//                j = stack.getItem().getColorFromItemStack(stack, bakedquad.getTintIndex());

                if (EntityRenderer.anaglyphEnable)
                {
                    j = TextureUtil.anaglyphColor(j);
                }

                j |= -16777216;
            }
        }
    }

    private void renderQuad(WorldRenderer renderer, BakedQuad quad, int color)
    {
        renderer.addVertexData(quad.getVertexData());
        if(quad instanceof net.minecraftforge.client.model.IColoredBakedQuad)
            net.minecraftforge.client.ForgeHooksClient.putQuadColor(renderer, quad, color);
        else
            renderer.putColor4(color);
        this.putQuadNormal(renderer, quad);
    }

    private void putQuadNormal(WorldRenderer renderer, BakedQuad quad)
    {
        Vec3i vec3i = quad.getFace().getDirectionVec();
        renderer.putNormal((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
    }


    @Override
    public boolean renderWorldBlock(IBlockAccess world, BlockPos pos, IBlockState state, int renderId, WorldRenderer renderer)
    {
        System.out.println("RENDER");

        Block block = state.getBlock();
        if(!(block instanceof BlockCage)) return false;

        BlockCage cage = (BlockCage) block;

        Minecraft minecraft = Minecraft.getMinecraft();
        RenderItem renderItem = minecraft.getRenderItem();
//        renderItem.renderItem(itemStack, renderItem.getItemModelMesher().getItemModel(itemStack));

        IBakedModel model = renderItem.getItemModelMesher().getModelManager().getBlockModelShapes().getModelForState(state);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.setVertexFormat(DefaultVertexFormats.ITEM);
        EnumFacing[] aenumfacing = EnumFacing.values();
        int j = aenumfacing.length;

        for (int k = 0; k < j; ++k)
        {
            EnumFacing enumfacing = aenumfacing[k];
            this.renderQuads(worldrenderer, model.getFaceQuads(enumfacing), 0xFFFFFF, null);
        }

        this.renderQuads(worldrenderer, model.getGeneralQuads(), 0xFFFFFF, null);
        tessellator.draw();

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
