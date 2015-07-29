package net.timeless.jurassicraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.JurassiCraft;

@SideOnly(Side.CLIENT)
public class GuiPaleoPad extends GuiScreen
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/gui/paleo_pad.png");

    public GuiPaleoPad()
    {
    }

    @Override
    public void initGui()
    {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(texture);

        ScaledResolution dimensions = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        drawTexturedModalRect(dimensions.getScaledWidth() / 2 - 128, dimensions.getScaledHeight() / 2 - 128, 0, 0, 256, 256);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {

    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
