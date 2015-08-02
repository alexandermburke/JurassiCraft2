package net.timeless.jurassicraft.client.gui.app;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.gui.GuiPaleoPad;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;
import net.timeless.jurassicraft.common.paleopad.App;
import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public abstract class GuiApp
{
    protected App app;

    protected static final Minecraft mc = Minecraft.getMinecraft();

    public GuiApp(App app)
    {
        this.app = app;
    }

    public List<GuiButton> buttons = Lists.newArrayList();

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

    protected void renderButtons(int mouseX, int mouseY, GuiPaleoPad gui)
    {
        for (GuiButton button : buttons)
        {
            button.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
        }
    }

    public void keyPressed(int key){}
    public void mouseClicked(int mouseX, int mouseY, GuiPaleoPad gui){}
    public abstract void actionPerformed(GuiButton button);

    public abstract void init();

    public abstract ResourceLocation getTexture(GuiPaleoPad gui);

    public App getApp()
    {
        return app;
    }
}
