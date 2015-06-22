package net.timeless.jurassicraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.timeless.jurassicraft.container.ContainerFossilGrinder;
import net.timeless.jurassicraft.tileentity.TileFossilGrinder;

import org.lwjgl.opengl.GL11;

public class GuiFossilGrinder extends GuiContainer
{

    private TileFossilGrinder fossilGrinder;
    private ResourceLocation texture = new ResourceLocation("jurassicraft:textures/gui/gui_fossil_grinder.png");

    public GuiFossilGrinder(InventoryPlayer inventoryPlayer, TileEntity tileEntity)
    {
        super(new ContainerFossilGrinder(inventoryPlayer, tileEntity));
        if (tileEntity instanceof TileFossilGrinder)
        {
            fossilGrinder = (TileFossilGrinder) tileEntity;
            this.xSize = 176;
            this.ySize = 188;
        }
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
        String name = StatCollector.translateToLocal(fossilGrinder.getName());
        this.fontRendererObj.drawString(name, xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 5, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
