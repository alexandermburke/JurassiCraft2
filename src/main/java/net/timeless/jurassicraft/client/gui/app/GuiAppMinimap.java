package net.timeless.jurassicraft.client.gui.app;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.gui.GuiPaleoTab;
import net.timeless.jurassicraft.common.paleopad.App;
import net.timeless.jurassicraft.common.paleopad.AppFlappyDino;

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

        AppFlappyDino app = (AppFlappyDino) getApp();
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
