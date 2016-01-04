package org.jurassicraft.common.message;

import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class JCNetworkManager
{
    public SimpleNetworkWrapper networkWrapper;
    private int packetId = 0;

    public void register()
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("jurassicraft");

        registerMessage(MessageSyncPaleoPad.class);
        registerMessage(MessageRequestFile.class);
        registerMessage(MessageSendFile.class);
        registerMessage(MessagePlacePaddockSign.class);
        registerMessage(MessageChangeTemperature.class);
        registerMessage(MessageHelicopterEngine.class);
        registerMessage(MessageHelicopterDirection.class);
        registerMessage(MessageHelicopterModules.class);
    }

    private <T extends AbstractMessage<T> & IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz)
    {
        networkWrapper.registerMessage(clazz, clazz, packetId, Side.CLIENT);
        packetId += 1;
        networkWrapper.registerMessage(clazz, clazz, packetId, Side.SERVER);
        packetId += 1;
    }

    private <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId, Side.CLIENT);
        packetId += 1;
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId, Side.SERVER);
        packetId += 1;
    }
}
