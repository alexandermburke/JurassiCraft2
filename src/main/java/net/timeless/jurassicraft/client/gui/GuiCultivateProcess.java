package net.timeless.jurassicraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.tileentity.TileCultivate;

@SideOnly(Side.CLIENT)
public class GuiCultivateProcess extends GuiScreen
{
    private TileCultivate cultivator;
    private int xSize;
    private int ySize;
    private int guiLeft;
    private int guiTop;

    private ResourceLocation gui = new ResourceLocation(JurassiCraft.modid, "textures/gui/cultivator_progress.png");

    public GuiCultivateProcess(TileCultivate entity)
    {
        super();
        this.cultivator = entity;
        this.xSize = 176;
        this.ySize = 107;
    }

    public void updateScreen()
    {
        if (!this.cultivator.isCultivating())
        {
            this.mc.thePlayer.closeScreen();
        }
    }

    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }

    protected void keyTyped(char var1, int key)
    {
        if (key == 1 || key == this.mc.gameSettings.keyBindInventory.getKeyCode())
        {
            this.mc.thePlayer.closeScreen();
        }
    }

    public void initGui()
    {
        super.initGui();

        this.buttonList.clear();

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

        this.buttonList.add(new GuiButton(0, this.guiLeft + (this.xSize - 100) / 2, this.guiTop + 70, 100, 20, StatCollector.translateToLocal("container.close.name")));
    }

    public void actionPerformed(GuiButton button)
    {
        if (button.id == 0)
        {
            this.mc.thePlayer.closeScreen();
        }
    }

    public void drawScreen(int x, int y, float f)
    {
        this.drawDefaultBackground();
        this.mc.renderEngine.bindTexture(gui);

        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(this.guiLeft + 13, this.guiTop + 49, 0, 107, getScaled(cultivator.getField(0), cultivator.getField(1), 150), 9);

        Dinosaur dinosaur = this.cultivator.getDinosaur();

        String name;

        if (dinosaur != null)
        {
            name = dinosaur.getName(0);
        }
        else
        {
            name = "Unknown";
        }

        String cultivatingLang = StatCollector.translateToLocal("container.cultivator.cultivating");
        String progressLang = StatCollector.translateToLocal("container.cultivator.progress");

        String progress = progressLang + ": " + getScaled(cultivator.getField(0), cultivator.getField(1), 100) + "%";
        String cultivating = cultivatingLang + ": " + name;

        this.fontRendererObj.drawString(cultivating, this.guiLeft + (this.xSize - this.fontRendererObj.getStringWidth(cultivating)) / 2, this.guiTop + 10, 4210752);
        this.fontRendererObj.drawString(progress, this.guiLeft + (this.xSize - this.fontRendererObj.getStringWidth(progress)) / 2, this.guiTop + 40, 4210752);

        super.drawScreen(x, y, f);
    }

    private int getScaled(int value, int maxValue, int scale)
    {
        return maxValue != 0 && value != 0 ? value * scale / maxValue : 0;
    }
}
