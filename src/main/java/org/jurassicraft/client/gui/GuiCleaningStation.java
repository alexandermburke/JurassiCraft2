package org.jurassicraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.container.ContainerCleaningStation;
import org.jurassicraft.common.tileentity.TileCleaningStation;

@SideOnly(Side.CLIENT)
public class GuiCleaningStation extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation("jurassicraft:textures/gui/cleaning_station.png");
    /**
     * The player inventory bound to this GUI.
     */
    private final InventoryPlayer playerInventory;
    private IInventory cleaningStation;

    public GuiCleaningStation(InventoryPlayer playerInv, IInventory cleaningStation)
    {
        super(new ContainerCleaningStation(playerInv, cleaningStation));
        this.playerInventory = playerInv;
        this.cleaningStation = cleaningStation;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items). Args : mouseX, mouseY
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.cleaningStation.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Args : renderPartialTicks, mouseX, mouseY
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int progress;

        if (TileCleaningStation.isCleaning(this.cleaningStation))
        {
            progress = this.func_175382_i(51);
            this.drawTexturedModalRect(k + 46, l + 18 + 51 - progress, 176, 81 - progress, 14, progress + 1);
        }

        progress = this.getProgress(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, progress + 1, 16);
    }

    private int getProgress(int scale)
    {
        int j = this.cleaningStation.getField(2);
        int k = this.cleaningStation.getField(3);
        return k != 0 && j != 0 ? j * scale / k : 0;
    }

    private int func_175382_i(int p_175382_1_)
    {
        int j = this.cleaningStation.getField(1);

        if (j == 0)
        {
            j = 200;
        }

        return this.cleaningStation.getField(0) * p_175382_1_ / j;
    }
}
