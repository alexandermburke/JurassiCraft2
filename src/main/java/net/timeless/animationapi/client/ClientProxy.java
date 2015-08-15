package net.timeless.animationapi.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.CommonProxy;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{

    private Timer mcTimer;

    @Override
    public void initTimer()
    {
        mcTimer = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), AnimationAPI.getFTimer());
    }

    @Override
    public float getPartialTick()
    {
        return mcTimer.renderPartialTicks;
    }

    @Override
    public World getWorldClient()
    {
        return FMLClientHandler.instance().getWorldClient();
    }
}
