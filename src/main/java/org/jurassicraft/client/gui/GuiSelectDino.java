package org.jurassicraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.message.MessagePlacePaddockSign;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GuiSelectDino extends GuiScreen
{
    private static final ResourceLocation paddock_sign = new ResourceLocation(JurassiCraft.MODID, "textures/paddock/paddock_signs.png");

    private int page;
    private int pageCount;

    public int columnsPerPage = 5;
    public int rowsPerPage = 3;

    private GuiButton forward;
    private GuiButton backward;

    private BlockPos pos;
    private EnumFacing facing;

    private List<Dinosaur> dinosaurs;

    public GuiSelectDino(BlockPos pos, EnumFacing facing)
    {
        this.pos = pos;
        this.facing = facing;
    }

    public void initGui()
    {
        super.initGui();

        this.buttonList.add(new GuiButton(0, (this.width - 150) / 2, this.height / 5 + 150, 150, 20, I18n.format("gui.cancel", new Object[0])));
        this.buttonList.add(backward = new GuiButton(1, this.width / 2 - 105, this.height / 5 + 150, 20, 20, "<"));
        this.buttonList.add(forward = new GuiButton(2, this.width / 2 + 85, this.height / 5 + 150, 20, 20, ">"));

        page = 0;

        dinosaurs = new ArrayList<Dinosaur>(JCEntityRegistry.getRegisteredDinosaurs());

        Collections.sort(dinosaurs);

        pageCount = dinosaurs.size() / (columnsPerPage * rowsPerPage);

        enableDisablePages();
    }

    /**
     * Called when a mouse button is released.  Args : mouseX, mouseY, releaseButton
     */
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        super.mouseReleased(mouseX, mouseY, state);

        if (state == 0)
        {
            int signsPerPage = columnsPerPage * rowsPerPage;
            int xOffset = 0;
            int yOffset = 0;

            pageCount = dinosaurs.size() / signsPerPage;

            for (int i = 0; i < dinosaurs.size(); i++)
            {
                if (i >= signsPerPage * page && i < signsPerPage * (page + 1))
                {
                    float scale = 3F;

                    int x = (int) ((width / 2 - 140 + xOffset) / scale);
                    int y = (int) ((height / 8 + yOffset - 20) / scale);

                    float scaledMouseX = mouseX / scale;
                    float scaledMouseY = mouseY / scale;

                    if (scaledMouseX > x && scaledMouseY > y && scaledMouseX < x + 16 && scaledMouseY < y + 16)
                    {
                        selectDinosaur(dinosaurs.get(i));
                        break;
                    }

                    xOffset += 180 / scale;

                    if (i % columnsPerPage >= columnsPerPage - 1)
                    {
                        xOffset = 0;
                        yOffset += 180 / scale;
                    }
                }
            }
        }
    }

    public void actionPerformed(GuiButton button)
    {
        int id = button.id;

        if (id == 1)
        {
            if (page > 0)
            {
                page--;
            }

        }
        else if (id == 2)
        {
            if (page < pageCount)
            {
                page++;
            }
        }
        else
        {
            mc.displayGuiScreen(null);
        }

        enableDisablePages();
    }

    public void enableDisablePages()
    {
        backward.enabled = !(page <= 0);
        forward.enabled = page < pageCount;
    }

    public void selectDinosaur(Dinosaur dinosaur)
    {
        mc.displayGuiScreen(null);
        if (!mc.thePlayer.capabilities.isCreativeMode)
        {
            InventoryPlayer inventory = mc.thePlayer.inventory;
            inventory.decrStackSize(inventory.currentItem, 1);
        }
        JurassiCraft.networkManager.networkWrapper.sendToServer(new MessagePlacePaddockSign(facing, pos, dinosaur));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);

        int signsPerPage = columnsPerPage * rowsPerPage;
        int xOffset = 0;
        int yOffset = 0;

        pageCount = dinosaurs.size() / signsPerPage;

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        int i = 0;

        for (Dinosaur dinosaur : dinosaurs)
        {
            if (i >= signsPerPage * page && i < signsPerPage * (page + 1))
            {
                int id = JCEntityRegistry.getDinosaurId(dinosaur);

                GlStateManager.pushMatrix();

                mc.getTextureManager().bindTexture(paddock_sign);

                float scale = 3F;

                GlStateManager.scale(scale, scale, scale);

                int x = (int) ((width / 2 - 140 + xOffset) / scale);
                int y = (int) ((height / 8 + yOffset - 20) / scale);

                float scaledMouseX = mouseX / scale;
                float scaledMouseY = mouseY / scale;

                if (scaledMouseX > x && scaledMouseY > y && scaledMouseX < x + 16 && scaledMouseY < y + 16)
                {
                    drawBoxOutline(x - 1, y - 1, 18, 17, 1, 0x60606060);
                }

                drawTexturedModalRect(x, y, ((id) % 16) * 16, (int) Math.floor((id) / 16) * 16, 16, 16);

                float textScale = 0.22F;

                GlStateManager.scale(textScale, textScale, textScale);

                drawCenteredString(mc.fontRendererObj, dinosaur.getName(), (int) ((x + 8) / textScale), (int) ((y + 17) / textScale), 0xFFFFFF);

                GlStateManager.popMatrix();

                xOffset += 180 / scale;

                if (i % columnsPerPage >= columnsPerPage - 1)
                {
                    xOffset = 0;
                    yOffset += 180 / scale;
                }
            }

            i++;
        }
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
     */
    public void drawScaledRect(int x, int y, int width, int height, int colour)
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        float red = (float) (colour >> 24 & 255) / 255.0F;
        float green = (float) (colour >> 16 & 255) / 255.0F;
        float blue = (float) (colour >> 8 & 255) / 255.0F;
        float a = (float) (colour & 255) / 255.0F;

        GL11.glColor4f(red, green, blue, a);

        float f = 1.0F / (float) width;
        float f1 = 1.0F / (float) height;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double) (x), (double) (y + height), (double) this.zLevel).tex((double) (0), (double) ((float) (height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) (y + height), (double) this.zLevel).tex((double) ((float) (width) * f), (double) ((float) (height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) (y), (double) this.zLevel).tex((double) ((float) (width) * f), (double) ((float) 0)).endVertex();
        worldrenderer.pos((double) (x), (double) (y), (double) this.zLevel).tex((double) ((float) 0), (double) ((float) 0)).endVertex();
        tessellator.draw();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public void drawBoxOutline(int x, int y, int sizeX, int sizeY, int borderSize, int colour)
    {
        drawScaledRect(x, y, sizeX, borderSize, colour);
        drawScaledRect(x + sizeX, y, borderSize, sizeY + borderSize, colour);
        drawScaledRect(x, y + borderSize, borderSize, sizeY, colour);
        drawScaledRect(x + borderSize, y + sizeY, sizeX - borderSize, borderSize, colour);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();

        //TODO send packet
    }
}
