package net.timeless.jurassicraft.proxy;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.render.JCRenderingRegistry;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    public static JCRenderingRegistry renderingRegistry = new JCRenderingRegistry();

    @Override
    public void preInit()
    {
        super.preInit();

        renderingRegistry.preInit();
    }

    @Override
    public void init()
    {
        super.init();

        renderingRegistry.init();
    }

    @Override
    public void postInit()
    {
        super.postInit();

        renderingRegistry.postInit();
    }
}