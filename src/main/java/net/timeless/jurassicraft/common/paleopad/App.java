package net.timeless.jurassicraft.common.paleopad;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public abstract class App
{
    private boolean requestShutdown;

    public abstract String getName();

    public abstract void update();

    public abstract void writeToNBT(NBTTagCompound nbt);
    public abstract void readFromNBT(NBTTagCompound nbt);

    public abstract void init();

    public void requestShutdown()
    {
        this.requestShutdown = true;
    }

    public boolean doesRequestShutdown()
    {
        return requestShutdown;
    }

    /** Client Side Code */

    private List<GuiButton> buttons = Lists.newArrayList();

    public abstract void render(int mouseX, int mouseY);

    protected void renderButtons(int mouseX, int mouseY)
    {
        for (GuiButton button : buttons)
            button.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
    }

    public void keyPressed(int key){}
    public void mouseClicked(int mouseX, int mouseY){}
    public abstract void actionPerformed(GuiButton button);
}
