package org.jurassicraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jurassicraft.common.container.ContainerIncubator;
import org.jurassicraft.common.message.JCNetworkManager;
import org.jurassicraft.common.message.MessageChangeTemperature;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiIncubator extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation("jurassicraft:textures/gui/incubator.png");
    /**
     * The player inventory bound to this GUI.
     */
    private final InventoryPlayer playerInventory;
    private IInventory incubator;

    public GuiIncubator(InventoryPlayer playerInv, IInventory incubator)
    {
        super(new ContainerIncubator(playerInv, (TileEntity) incubator));
        this.playerInventory = playerInv;
        this.incubator = incubator;
    }


    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        dragTemperature(mouseX, mouseY);
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
    {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

        dragTemperature(mouseX, mouseY);
    }

    private void dragTemperature(int mouseX, int mouseY)
    {
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        for (int i = 0; i < 5; i++)
        {
            int x = 0;
            int y = 0;

            switch (i)
            {
                case 0:
                    x = 33;
                    y = 28;
                    break;
                case 1:
                    x = 56;
                    y = 21;
                    break;
                case 2:
                    x = 79;
                    y = 14;
                    break;
                case 3:
                    x = 102;
                    y = 21;
                    break;
                case 4:
                    x = 125;
                    y = 28;
                    break;
            }

            x += k - 2;
            y += 18 + l;

            if (mouseX > x && mouseY > y && mouseX < x + 21 && mouseY < y + 5)
            {
                int temp = (mouseX - x + 1) * 4;

                if (temp != incubator.getField(i + 10))
                {
                    incubator.setField(i + 10, temp);
                    JCNetworkManager.networkWrapper.sendToServer(new MessageChangeTemperature(((TileEntity) incubator).getPos(), i, temp));
                }

                break;
            }
        }
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items). Args : mouseX, mouseY
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.incubator.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 4, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 4, 4210752);
    }

    /**
     * Args : renderPartialTicks, mouseX, mouseY
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        for (int i = 0; i < 5; i++)
        {
            int progress = this.getProgress(i, 14);

            int x = 0;
            int y = 0;

            switch (i)
            {
                case 0:
                    x = 33;
                    y = 28;
                    break;
                case 1:
                    x = 56;
                    y = 21;
                    break;
                case 2:
                    x = 79;
                    y = 14;
                    break;
                case 3:
                    x = 102;
                    y = 21;
                    break;
                case 4:
                    x = 125;
                    y = 28;
                    break;
            }

            x++;
            y += 24;

            this.drawTexturedModalRect(k + x, l + y, 176, 5, progress, 5);

            int temp = getTemperature(i, 20);

            this.drawTexturedModalRect(k + x + temp - 3, l + y - 6, 176, 0, 3, 5);
        }
    }

    private int getProgress(int slot, int scale)
    {
        int j = this.incubator.getField(slot);
        int k = this.incubator.getField(slot + 5);
        return k != 0 && j != 0 ? j * scale / k : 0;
    }

    private int getTemperature(int slot, int scale)
    {
        int j = this.incubator.getField(slot + 10);
        int k = 100;
        return k != 0 && j != 0 ? j * scale / k : 0;
    }
}