package net.timeless.jurassicraft.client.gui.app;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.gui.GuiPaleoTab;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.paleopad.App;
import net.timeless.jurassicraft.common.paleopad.AppMinimap;
import org.lwjgl.opengl.GL11;

public class GuiAppMinimap extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/gui/paleo_pad/apps/minimap.png");

    private static final ResourceLocation entity = new ResourceLocation(JurassiCraft.modid, "textures/gui/paleo_pad/apps/background/entity.png");

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
//                    int shadowY = 0;
//                    int shadowX = 0;
//                    int shadowZ = 0;

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

                            int rgb = color.colorValue;

//                            if(shadowX == blockX && shadowZ == blockZ - 1 && blockY < shadowY)
//                            {
//                            int red = (rgb >> 16) & 255;
//                            int green = (rgb >> 8) & 255;
//                            int blue = rgb & 255;
//
//                            float dark = -((float) (blockY - 64)) / 255.0F;
//
//                            red *= dark;
//                            green *= dark;
//                            blue *= dark;
//
//                            rgb = red;
//                            rgb = (rgb << 8) + green;
//                            rgb = (rgb << 8) + blue;
//                            }
//
//                            shadowX = blockX;
//                            shadowY = blockY;
//                            shadowZ = blockZ;

                            gui.drawScaledRect(renderX + (renderChunkX * 16) + 90, renderY + (renderChunkY * 16) + 15, 1, 1, 1.0F, rgb/**(color.colorValue & rgb) >> 1)**/);

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

        renderChunkX = 0;
        renderChunkY = 0;

        for (int chunkX = playerChunkX - 4; chunkX < playerChunkX + 4; chunkX++)
        {
            for (int chunkZ = playerChunkZ - 4; chunkZ < playerChunkZ + 4; chunkZ++)
            {
                Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);

                if (!chunk.isEmpty())
                {
                    for (Object e : world.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.fromBounds(chunkX * 16, 0, chunkZ * 16, (chunkX * 16) + 16, 256, (chunkZ * 16) + 16)))
                    {
                        Entity entity = (Entity) e;

                        if(entity instanceof EntityDinosaur)
                        {
                            EntityDinosaur dino = (EntityDinosaur) entity;

                            if(dino.hasTracker())
                            {
                                Dinosaur dinosaur = dino.getDinosaur();
                                int colour = dinosaur.getEggPrimaryColor();

                                float red = (float) (colour >> 16 & 255) / 255.0F;
                                float green = (float) (colour >> 8 & 255) / 255.0F;
                                float blue = (float) (colour & 255) / 255.0F;

                                GL11.glColor3f(red, green, blue);

                                mc.getTextureManager().bindTexture(GuiAppMinimap.entity);
                                gui.drawScaledTexturedModalRect(((int) dino.posX & 15) + (renderChunkX * 16) + 90, ((int) dino.posZ & 15) + (renderChunkY * 16) + 15, 0, 0, 16, 16, 16, 16, 0.6F);
                            }
                        }
                    }
                }

                renderChunkY++;
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
