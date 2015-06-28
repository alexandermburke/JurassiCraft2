package net.timeless.jurassicraft.packets;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.timeless.jurassicraft.JurassiCraft;

public class JCMessageRegistry
{
    public static final byte CLEANING_TABLE_MESSAGE_ID = 1;

    public static void preInitCommon()
    {
        JurassiCraft.wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(JurassiCraft.MODID + "_channel");
        JurassiCraft.wrapper.registerMessage(MessageCleaningTable.Handler.class, MessageCleaningTable.class, JCMessageRegistry.CLEANING_TABLE_MESSAGE_ID, Side.SERVER);
    }

    public static void initCommon()
    {

    }

    public static void postInitCommon()
    {

    }

    public static void preInitClientOnly()
    {

    }

    public static void initClientOnly()
    {

    }

    public static void postInitClientOnly()
    {

    }
}
