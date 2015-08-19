package net.timeless.jurassicraft.common.message;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class JCNetworkManager
{
    public static SimpleNetworkWrapper networkWrapper;
    private static int packetId = 0;

    public void register()
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("jurassicraft");

        registerPacket(MessageSyncPaleoPad.Handler.class, MessageSyncPaleoPad.class);
        registerPacket(MessageRequestFile.Handler.class, MessageRequestFile.class);
        registerPacket(MessageSendFile.Handler.class, MessageSendFile.class);
        registerPacket(MessageChangeTemperature.Handler.class, MessageChangeTemperature.class);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId++, Side.SERVER);
    }
}
