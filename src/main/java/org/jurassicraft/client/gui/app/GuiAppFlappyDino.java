package org.jurassicraft.client.gui.app;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.GuiPaleoTab;
import org.jurassicraft.common.paleopad.App;
import org.jurassicraft.common.paleopad.AppFlappyDino;

import java.util.*;

public class GuiAppFlappyDino extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/flappy_dino.png");
    private static final ResourceLocation logo = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/flappy_dino.png");
    private static final ResourceLocation pteranodon = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/flappy_dino_pteranodon.png");
    private static final ResourceLocation character = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/pteranodon_char.png");
    private static final ResourceLocation pillar_bottom = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/pillar_bottom.png");
    private static final ResourceLocation pillar_top = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/pillar_top.png");
    private static final ResourceLocation background = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/background/flappy_dino_background.png");

    private boolean mainScreen;

    private int x;
    private int y;
    private int motionY;

    private boolean playing;

    private Map<Integer, Integer> pillars = new HashMap<Integer, Integer>();

    public GuiAppFlappyDino(App app)
    {
        super(app);
    }

    @Override
    public void render(int mouseX, int mouseY, GuiPaleoTab gui)
    {
        super.renderButtons(mouseX, mouseY, gui);

        AppFlappyDino app = (AppFlappyDino) getApp();

        mc.getTextureManager().bindTexture(background);
        gui.drawScaledTexturedModalRect(0, 0, 0, 0, 229, 150, 229, 150, 1.0F);

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
            gui.drawScaledTexturedModalRect(5, 150 - y, 0, 0, 32, 32, 32, 32, 1.0F);

            mc.getTextureManager().bindTexture(pillar_bottom);

            for (Map.Entry<Integer, Integer> entry : pillars.entrySet())
            {
                int drawX = entry.getKey() - this.x;

                if(drawX > 0 && drawX < 200)
                {
                    for (int height = 0; height < entry.getValue(); height++)
                    {
                        gui.drawScaledTexturedModalRect(drawX, 130 - (height * 20), 0, 12, 32, 20, 32, 32, 1.0F);
                    }

                    gui.drawScaledTexturedModalRect(drawX, 139 - (entry.getValue() * 20), 0, 0, 32, 12, 32, 32, 1.0F);
                }
            }

            mc.getTextureManager().bindTexture(pillar_top);

            for (Map.Entry<Integer, Integer> entry : pillars.entrySet())
            {
                int drawX = entry.getKey() - this.x;

                if(drawX > 0 && drawX < 200)
                {
                    Integer totalHeight = 4 - entry.getValue();

                    for (int height = 0; height < totalHeight; height++)
                    {
                        gui.drawScaledTexturedModalRect(drawX, 0 + (height * 20), 0, 0, 32, 20, 32, 32, 1.0F);
                    }

                    gui.drawScaledTexturedModalRect(drawX, 0 + (totalHeight * 20), 0, 20, 32, 12, 32, 32, 1.0F);
                }
            }
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
            motionY = 6;
        }
    }

    @Override
    public void init()
    {
        x = 0;
        y = 150;
        motionY = 0;

        pillars.clear();

        Random rand = new Random();

        for (int i = 0; i < 100; i++)
        {
            pillars.put((i * 70) + 70, rand.nextInt(5));
        }

        mainScreen = true;
    }

    public void update()
    {
        if(!mainScreen && mc.thePlayer.ticksExisted % 2 == 0)
        {
            x++;

            if (y > 140)
            {
                y = 140;
            }

            if(motionY < -5)
            {
                motionY = -5;
            }

            y += motionY;

            motionY--;

            boolean died = false;

            for (Map.Entry<Integer, Integer> entry : pillars.entrySet())
            {
                int renderX = x - entry.getKey();
                int pillarX = entry.getKey();

                int bottomHeight = (entry.getValue() * 20) + 10;
                int topHeight = 139 - (((4 - entry.getValue()) * 20) - 12);

                if(renderX > 0 && renderX < 200)
                {
                    boolean collideX = x < (pillarX + 29) && x + 30 > pillarX;
                    boolean collideY = y - 24 < bottomHeight || y > topHeight;

                    if(collideX && collideY)
                    {
                        died = true;
                        break;
                    }
                }
            }

            if (y < 20)
            {
                died = true;
            }

            if(died)
            {
                init();
            }
        }
    }

    @Override
    public ResourceLocation getTexture(GuiPaleoTab gui)
    {
        return texture;
    }
}
