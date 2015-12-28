package org.jurassicraft.client.gui.app;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.GuiPaleoPad;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.paleopad.App;
import org.jurassicraft.common.paleopad.AppMinimap;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuiAppMinimap extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/minimap.png");

    private static final ResourceLocation entity = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/entity.png");

    public GuiAppMinimap(App app)
    {
        super(app);
    }

    @Override
    public void render(int mouseX, int mouseY, GuiPaleoPad gui)
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

        int mapX = 0;
        int renderY = 0;
        int renderChunkX = 0;
        int renderChunkY = 0;

        for (int chunkX = playerChunkX - 4; chunkX < playerChunkX + 4; chunkX++)
        {
            for (int chunkZ = playerChunkZ - 4; chunkZ < playerChunkZ + 4; chunkZ++)
            {
                Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);

                if (!chunk.isEmpty())
                {
                    // int shadowY = 0;
                    // int shadowX = 0;
                    // int shadowZ = 0;

                    for (int x = 0; x < 16; x++)
                    {
                        for (int z = 0; z < 16; z++)
                        {
                            int blockX = x + (chunkX * 16);
                            int blockY = chunk.getHeight(x, z);
                            int blockZ = z + (chunkZ * 16);

                            if (world.isAirBlock(new BlockPos(blockX, blockY, blockZ)))
                            {
                                blockY--;
                            }

                            IBlockState blockState = world.getBlockState(new BlockPos(blockX, blockY, blockZ));
                            Block block = blockState.getBlock();

                            MapColor color = block.getMapColor(blockState);

                            int rgb = color.colorValue;

                            // if(shadowX == blockX && shadowZ == blockZ - 1 && blockY < shadowY)
                            // {
                            // int red = (rgb >> 16) & 255;
                            // int green = (rgb >> 8) & 255;
                            // int blue = rgb & 255;
                            //
                            // float dark = -((float) (blockY - 64)) / 255.0F;
                            //
                            // red *= dark;
                            // green *= dark;
                            // blue *= dark;
                            //
                            // rgb = red;
                            // rgb = (rgb << 8) + green;
                            // rgb = (rgb << 8) + blue;
                            // }
                            //
                            // shadowX = blockX;
                            // shadowY = blockY;
                            // shadowZ = blockZ;

                            gui.drawScaledRect(mapX + (renderChunkX * 16) + 90, renderY + (renderChunkY * 16) + 15, 1, 1, 1.0F, rgb/** (color.colorValue & rgb) >> 1) **/
                            );

                            renderY++;
                        }

                        renderY = 0;
                        mapX++;
                    }
                }

                mapX = 0;
                renderY = 0;

                renderChunkY++;

                gui.drawBoxOutline(renderChunkX * 16 + 90, renderChunkY * 16 - 1, 15, 15, 1, 1.0F, (renderChunkX + renderChunkY) % 2 == 0 ? 0x606060 : 0x505050);
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
                    for (Object e : getEntitiesInChunk(chunk, null, IEntitySelector.NOT_SPECTATING))
                    {
                        Entity entity = (Entity) e;

                        if (entity instanceof EntityDinosaur)
                        {
                            EntityDinosaur dino = (EntityDinosaur) entity;

                            if (dino.hasTracker())
                            {
                                Dinosaur dinosaur = dino.getDinosaur();
                                int colour = dino.isMale() ? dinosaur.getEggPrimaryColorMale() : dinosaur.getEggPrimaryColorFemale();

                                float red = (colour >> 16 & 255) / 255.0F;
                                float green = (colour >> 8 & 255) / 255.0F;
                                float blue = (colour & 255) / 255.0F;

                                GL11.glColor3f(red, green, blue);

                                mc.getTextureManager().bindTexture(GuiAppMinimap.entity);
                                int dinoX = (int) dino.posX;
                                int dinoZ = (int) dino.posZ;

                                int entityRenderX = (dinoX & 15) + (renderChunkX * 16) + 90 - 4;
                                int entityRenderY = (dinoZ & 15) + (renderChunkY * 16) + 15 - 4;

                                gui.drawScaledTexturedModalRect(entityRenderX, entityRenderY, 0, 0, 16, 16, 16, 16, 0.6F);

                                gui.drawCenteredScaledText(dinoX + " " + (int) dino.posY + " " + dinoZ, entityRenderX + 5, entityRenderY + 8, 0.3F, 0xFFFFFF);
                            }
                        }
                        else if (player == entity)
                        {
                            mc.getTextureManager().bindTexture(GuiAppMinimap.entity);

                            gui.drawScaledTexturedModalRect((playerX & 15) + (renderChunkX * 16) + 90 - 4, (playerZ & 15) + (renderChunkY * 16) + 15 - 4, 0, 0, 16, 16, 16, 16, 0.6F);
                        }
                    }
                }

                renderChunkY++;
            }

            renderChunkY = 0;
            renderChunkX++;
        }
    }

    /**
     * Fills the given list of all entities that intersect within the given bounding box that aren't the passed entity.
     */
    public List<Entity> getEntitiesInChunk(Chunk chunk, Entity exclude, Predicate<Entity> predicate)
    {
        List<Entity> entities = new ArrayList<Entity>();

        int i = MathHelper.floor_double((0 - World.MAX_ENTITY_RADIUS) / 16.0D);
        int j = MathHelper.floor_double((256 + World.MAX_ENTITY_RADIUS) / 16.0D);
        ClassInheritanceMultiMap[] entityLists = chunk.getEntityLists();
        i = MathHelper.clamp_int(i, 0, entityLists.length - 1);
        j = MathHelper.clamp_int(j, 0, entityLists.length - 1);

        for (int k = i; k <= j; ++k)
        {
            Iterator<Entity> iterator = entityLists[k].iterator();

            while (iterator.hasNext())
            {
                Entity entity = iterator.next();

                if (entity != exclude && (predicate == null || predicate.apply(entity)))
                {
                    entities.add(entity);
                    Entity[] parts = entity.getParts();

                    if (parts != null)
                    {
                        for (Entity part : parts)
                        {
                            entity = part;

                            if (entity != exclude && (predicate == null || predicate.apply(entity)))
                            {
                                entities.add(entity);
                            }
                        }
                    }
                }
            }
        }

        return entities;
    }

    @Override
    public void actionPerformed(GuiButton button)
    {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, GuiPaleoPad gui)
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
    public ResourceLocation getTexture(GuiPaleoPad gui)
    {
        return texture;
    }
}
