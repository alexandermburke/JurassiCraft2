package net.timeless.jurassicraft.client.gui.app;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.paleopad.App;

public class GuiAppTest extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/gui/paleo_pad/apps/test.png");

    public GuiAppTest(App app)
    {
        super(app);
    }

    @Override
    public void render(int mouseX, int mouseY)
    {
        super.renderButtons(mouseX, mouseY);
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
    public ResourceLocation getTexture()
    {
        return texture;
    }
}
