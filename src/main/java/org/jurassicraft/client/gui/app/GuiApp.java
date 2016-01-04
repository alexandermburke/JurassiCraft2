package org.jurassicraft.client.gui.app;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.gui.GuiPaleoPad;
import org.jurassicraft.common.entity.data.JCPlayerData;
import org.jurassicraft.common.paleopad.App;

import java.util.List;

@SideOnly(Side.CLIENT)
public abstract class GuiApp
{
    final App app;

    static final Minecraft mc = Minecraft.getMinecraft();

    GuiApp(App app)
    {
        this.app = app;
    }

    public final List<GuiButton> buttons = Lists.newArrayList();

    private boolean requestShutdown;

    public void requestShutdown()
    {
        this.requestShutdown = true;

        JCPlayerData playerData = JCPlayerData.getPlayerData(Minecraft.getMinecraft().thePlayer);
        playerData.closeApp(app);
    }

    public boolean doesRequestShutdown()
    {
        return requestShutdown;
    }

    public abstract void render(int mouseX, int mouseY, GuiPaleoPad gui);

    void renderButtons(int mouseX, int mouseY)
    {
        for (GuiButton button : buttons)
        {
            button.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
        }
    }

    public void keyPressed()
    {
    }

    public void mouseClicked(int mouseX, int mouseY)
    {
    }

    public abstract void actionPerformed();

    public abstract void init();

    public abstract ResourceLocation getTexture();

    public App getApp()
    {
        return app;
    }

    public void update()
    {
    }
}
