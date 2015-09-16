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
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.gui.GuiPaleoTab;
import net.timeless.jurassicraft.common.paleopad.App;
import net.timeless.jurassicraft.common.paleopad.AppFlappyDino;
import net.timeless.jurassicraft.common.paleopad.AppMinimap;

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

        int playerX = (int) player.posX;
        int playerZ = (int) player.posZ;

        int playerChunkX = playerX >> 4;
        int playerChunkZ = playerZ >> 4;

        for (int chunkX = playerChunkX - 2; chunkX < playerChunkX + 2; chunkX++)
        {
            for (int chunkZ = playerChunkZ - 2; chunkZ < playerChunkZ + 2; chunkZ++)
            {
                if(!world.getChunkFromChunkCoords(chunkX, chunkZ).isEmpty())
                {
                    for (int x = 0; x < 16; x++)
                    {
                        for (int z = 0; z < 16; z++)
                        {
                            int blockX = x + (chunkX * 16);
                            int blockZ = z + (chunkZ * 16);

                            //todo chunk.getheightvalue
                            IBlockState blockState = world.getBlockState(world.getTopSolidOrLiquidBlock(new BlockPos(blockX, 0, blockZ)));
                            Block block = blockState.getBlock();

                            MapColor color = block.getMapColor(blockState);

                            gui.drawScaledRect(blockX, blockZ, 1, 1, 1.0F, color.colorValue);
                        }
                    }
                }
            }
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
