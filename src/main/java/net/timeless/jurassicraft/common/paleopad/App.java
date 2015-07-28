package net.timeless.jurassicraft.common.paleopad;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;

import java.util.List;

public abstract class App
{
    public abstract String getName();

    public abstract void update();

    public abstract void writeToNBT(NBTTagCompound nbt);
    public abstract void readFromNBT(NBTTagCompound nbt);

    public abstract void init();

    /** Client Side Code */

    @SideOnly(Side.CLIENT)
    private List<GuiButton> buttons = Lists.newArrayList();

    private boolean requestShutdown;

    public void requestShutdown()
    {
        this.requestShutdown = true;

        JCPlayerData playerData = JCPlayerData.getPlayerData(Minecraft.getMinecraft().thePlayer);
        playerData.closeApp(this);
    }

    public boolean doesRequestShutdown()
    {
        return requestShutdown;
    }

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
