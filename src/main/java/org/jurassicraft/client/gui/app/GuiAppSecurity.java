package org.jurassicraft.client.gui.app;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.GuiPaleoPad;
import org.jurassicraft.common.entity.data.JCPlayerData;
import org.jurassicraft.common.paleopad.App;
import org.jurassicraft.common.paleopad.AppSecurity;

import java.util.List;

public class GuiAppSecurity extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/security.png");

    public GuiAppSecurity(App app)
    {
        super(app);
    }

    @Override
    public void render(int mouseX, int mouseY, GuiPaleoPad gui)
    {
        super.renderButtons(mouseX, mouseY);

        AppSecurity app = (AppSecurity) getApp();

        EntityPlayer player = mc.thePlayer;
        World world = mc.theWorld;

        JCPlayerData data = JCPlayerData.getPlayerData(player);

        List<BlockPos> cameras = data.getCameras();
    }

    @Override
    public void actionPerformed()
    {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY)
    {
        ScaledResolution dimensions = new ScaledResolution(mc);
        mouseX -= dimensions.getScaledWidth() / 2 - 115;
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
