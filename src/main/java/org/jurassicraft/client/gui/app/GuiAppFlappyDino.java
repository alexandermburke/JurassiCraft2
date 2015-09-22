package org.jurassicraft.client.gui.app;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.GuiPaleoTab;
import org.jurassicraft.common.paleopad.App;
import org.jurassicraft.common.paleopad.AppFlappyDino;

public class GuiAppFlappyDino extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/flappy_dino.png");
    private static final ResourceLocation logo = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/flappy_dino.png");
    private static final ResourceLocation pteranodon = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/flappy_dino_pteranodon.png");
    private static final ResourceLocation character = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/flappy_dino_char.png");

    private boolean mainScreen;

    private int x;
    private int y;
    private int motionY;

    public GuiAppFlappyDino(App app)
    {
        super(app);
    }

    @Override
    public void render(int mouseX, int mouseY, GuiPaleoTab gui)
    {
        super.renderButtons(mouseX, mouseY, gui);

        AppFlappyDino app = (AppFlappyDino) getApp();

        gui.drawScaledRect(0, 0, 229, 150, 1.0F, 0x56FFF0);

        if (mainScreen)
        {
            mc.getTextureManager().bindTexture(logo);
            gui.drawScaledTexturedModalRect(5, 5, 0, 0, 128, 64, 128, 64, 1.0F);

            mc.getTextureManager().bindTexture(pteranodon);
            gui.drawScaledTexturedModalRect(145, 15, 0, 0, 128, 64, 128, 64, 1.0F);

            gui.drawBoxOutline(90, 100, 50, 20, 1, 1.0F, 0x545454);
            gui.drawScaledRect(91, 101, 49, 19, 1.0F, 0x747474);

            gui.drawScaledText("Play", 105, 107, 1.0F, 0xFFFFFF);
        }
        else
        {
            mc.getTextureManager().bindTexture(character);
            gui.drawScaledTexturedModalRect(5, 150 - y, 0, 0, 128, 64, 128, 64, 1.0F);
        }
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

        if (mainScreen)
        {
            if (mouseX > 90 && mouseX < 140 && mouseY > 100 && mouseY < 120)
            {
                mainScreen = false;
            }
        }
        else
        {
            motionY = 10;
        }
    }

    @Override
    public void init()
    {
        x = 0;
        y = 150;
        motionY = 0;
        mainScreen = true;
    }

    public void update()
    {
        x++;

        if (y > 140)
        {
            y = 140;
        }

        y += motionY;

        motionY--;

        if (y < 20)
        {
            init();
        }
    }

    @Override
    public ResourceLocation getTexture(GuiPaleoTab gui)
    {
        return texture;
    }
}
