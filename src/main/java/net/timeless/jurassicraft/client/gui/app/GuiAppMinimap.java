package net.timeless.jurassicraft.client.gui.app;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec4b;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.gui.GuiPaleoTab;
import net.timeless.jurassicraft.common.paleopad.App;
import net.timeless.jurassicraft.common.paleopad.AppFlappyDino;
import net.timeless.jurassicraft.common.paleopad.AppMinimap;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;

public class GuiAppMinimap extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/gui/paleo_pad/apps/minimap.png");

    public GuiAppMinimap(App app)
    {
        super(app);
    }

    @Override
    public void render(int mouseX, int mouseY, GuiPaleoTab gui)
    {
        super.renderButtons(mouseX, mouseY, gui);

        AppMinimap app = (AppMinimap) getApp();

        EntityPlayer player = mc.thePlayer;
        World world = mc.theWorld;

        gui.drawScaledText("Loc: " + (int) player.posX + " " + (int) player.posY + " " + (int) player.posZ, 2, 3, 1.0F, 0xFFFFFF);

        int playerX = (int) player.posX;
        int playerZ = (int) player.posZ;

        int playerChunkX = playerX >> 4;
        int playerChunkZ = playerZ >> 4;

        int renderX = 0;
        int renderY = 0;
        int renderChunkX = 0;
        int renderChunkY = 0;

        for (int chunkX = playerChunkX - 4; chunkX < playerChunkX + 4; chunkX++)
        {
            for (int chunkZ = playerChunkZ - 4; chunkZ < playerChunkZ + 4; chunkZ++)
            {
                Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);

                if(!chunk.isEmpty())
                {
                    int shadowY = 0;
                    int shadowX = 0;
                    int shadowZ = 0;

                    for (int x = 0; x < 16; x++)
                    {
                        for (int z = 0; z < 16; z++)
                        {
                            int blockX = x + (chunkX * 16);
                            int blockY = chunk.getHeightValue(x, z);
                            int blockZ = z + (chunkZ * 16);

                            if(world.isAirBlock(new BlockPos(blockX, blockY, blockZ)))
                            {
                                blockY--;
                            }

                            IBlockState blockState = world.getBlockState(new BlockPos(blockX, blockY, blockZ));
                            Block block = blockState.getBlock();

                            MapColor color = block.getMapColor(blockState);

                            int rgb = 0xFFFFFF;

                            if(shadowX == blockX && shadowZ == blockX - 1 && blockY < shadowY)
                            {
                                int dark = 245;

                                rgb = dark;
                                rgb = (rgb << 8) + dark;
                                rgb = (rgb << 8) + dark;
                            }

                            shadowX = blockX;
                            shadowY = blockY;
                            shadowZ = blockZ;

                            gui.drawScaledRect(renderX + (renderChunkX * 16) + 90, renderY + (renderChunkY * 16) + 15, 1, 1, 1.0F, color.colorValue/**(color.colorValue & rgb) >> 1)**/);

                            renderY++;
                        }

                        renderY = 0;
                        renderX++;
                    }
                }

                renderX = 0;
                renderY = 0;

                renderChunkY++;

                gui.drawBoxOutline(renderChunkX * 16 + 89, renderChunkY * 16 - 2, 16, 16, 1, 1.0F, 0x606060);
            }

            renderChunkY = 0;
            renderChunkX++;
        }
    }

    @Override
    public void actionPerformed(GuiButton button)
    {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, GuiPaleoTab gui)
    {
        ScaledResolution dimensions = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        mouseX -= dimensions.getScaledWidth() / 2 - 115;
        mouseY -= 65;
    }

    @Override
    public void init()
    {
    }

    @Override
    public ResourceLocation getTexture(GuiPaleoTab gui)
    {
        return texture;
    }
}
