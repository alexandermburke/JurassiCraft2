package net.timeless.jurassicraft.client.gui.app;

import net.minecraft.client.gui.GuiButton;
import net.timeless.jurassicraft.common.paleopad.App;

public class GuiAppTest extends GuiApp
{
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
}
