package net.timeless.jurassicraft.client.gui.app;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.gui.GuiPaleoPad;
import net.timeless.jurassicraft.common.paleopad.App;

public class GuiAppJurassiPedia extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/gui/paleo_pad/apps/jurassipedia.png");

    public GuiAppJurassiPedia(App app)
    {
        super(app);
    }

    @Override
    public void render(int mouseX, int mouseY, GuiPaleoPad gui)
    {
        super.renderButtons(mouseX, mouseY, gui);

        gui.drawScaledText("Hello " + mc.thePlayer.getName() + "! I am " + app.getName() + "!", 10, 10, 1.0F, 0xFFFFFF);
        mc.getTextureManager().bindTexture(texture);
        gui.drawScaledTexturedModalRect(8, 25, 0, 0, 32 ,32, 32, 32, 1.0F);
        gui.drawScaledText("<-- I look like this", 45, 35, 1.0F, 0xFFFFFF);
    }

    @Override
    public void actionPerformed(GuiButton button)
    {

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
