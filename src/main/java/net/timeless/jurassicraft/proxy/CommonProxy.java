package net.timeless.jurassicraft.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.event.CommonEventHandler;
import net.timeless.jurassicraft.handler.GuiHandler;
import net.timeless.jurassicraft.packets.MessageCleaningTable;

public class CommonProxy
{
    public void preInit()
    {
        JurassiCraft.entityRegistry.register();
        JurassiCraft.creativeTabRegistry.register();
        JurassiCraft.itemRegistry.register();
        JurassiCraft.blockRegistry.register();

        NetworkRegistry.INSTANCE.registerGuiHandler(JurassiCraft.instance, new GuiHandler());

        JurassiCraft.wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(JurassiCraft.modid);
        JurassiCraft.wrapper.registerMessage(MessageCleaningTable.Handler.class, MessageCleaningTable.class, 0, Side.SERVER);
        
        CommonEventHandler eventHandler = new CommonEventHandler();
        
        FMLCommonHandler.instance().bus().register(eventHandler);
        MinecraftForge.EVENT_BUS.register(eventHandler);
    }

    public void postInit()
    {

    }

    public void init()
    {

    }
}
