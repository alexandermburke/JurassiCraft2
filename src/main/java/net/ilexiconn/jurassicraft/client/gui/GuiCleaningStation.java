package net.ilexiconn.jurassicraft.client.gui;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.container.ContainerFossilGrinder;
import net.ilexiconn.jurassicraft.packets.MessageCleaningTable;
import net.ilexiconn.jurassicraft.tileentity.TileCleaningStation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiCleaningStation extends GuiContainer
{

    private TileCleaningStation cleaningStation;
    private ResourceLocation texture = new ResourceLocation("jurassicraft:textures/gui/gui_cleaning_station.png");

    public GuiCleaningStation(InventoryPlayer inventoryPlayer, TileEntity tileEntity)
    {
        super(new ContainerFossilGrinder(inventoryPlayer, tileEntity));
        if (tileEntity instanceof TileCleaningStation)
        {
            cleaningStation = (TileCleaningStation) tileEntity;
            this.xSize = 176;
            this.ySize = 188;
        }
    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.buttonList.clear();
        String buttonName = StatCollector.translateToLocal("container.cleaning_station.cleanFossilButton");
        int buttonNameWidth = this.fontRendererObj.getStringWidth(buttonName);
        this.buttonList.add(new GuiButton(0, (this.guiLeft - buttonNameWidth - 20) / 2, this.guiTop + 145, 20 + buttonNameWidth, 20, buttonName));
    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        if (button.id == 0)
            if (this.cleaningStation.canCleanFossil())
            {
                BlockPos pos = this.cleaningStation.getPos();
                JurassiCraft.wrapper.sendToServer(new MessageCleaningTable(pos.getX(), pos.getY(), pos.getZ()));
            }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
        String name = StatCollector.translateToLocal(this.cleaningStation.getName());
        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 5, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i1, int i2)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
