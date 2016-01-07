package org.jurassicraft.common.message;

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

        networkWrapper.registerMessage(MessageSyncPaleoPad.Handler.class, MessageSyncPaleoPad.class, packetId, Side.CLIENT);
        networkWrapper.registerMessage(MessageRequestFile.Handler.class, MessageRequestFile.class, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(MessageSendFile.Handler.class, MessageSendFile.class, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(MessagePlacePaddockSign.Handler.class, MessagePlacePaddockSign.class, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(MessageChangeTemperature.Handler.class, MessageChangeTemperature.class, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(MessageHelicopterEngine.Handler.class, MessageHelicopterEngine.class, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(MessageHelicopterDirection.Handler.class, MessageHelicopterDirection.class, packetId++, Side.CLIENT);
        networkWrapper.registerMessage(MessageHelicopterModules.Handler.class, MessageHelicopterModules.class, packetId++, Side.CLIENT);

        /**
        registerPacket(MessageSyncPaleoPad.Handler.class, MessageSyncPaleoPad.class);
        registerPacket(MessageRequestFile.Handler.class, MessageRequestFile.class);
        registerPacket(MessageSendFile.Handler.class, MessageSendFile.class);
        registerPacket(MessagePlacePaddockSign.Handler.class, MessagePlacePaddockSign.class);
        registerPacket(MessageChangeTemperature.Handler.class, MessageChangeTemperature.class);
        registerPacket(MessageHelicopterEngine.Handler.class, MessageHelicopterEngine.class);
        registerPacket(MessageHelicopterDirection.Handler.class, MessageHelicopterDirection.class);
        registerPacket(MessageHelicopterModules.Handler.class, MessageHelicopterModules.class);
         **/
    }

    // crashes MultiPlayer Crash
    // TODO make a new one :D
    private <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId, Side.CLIENT);
        packetId++;
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId, Side.SERVER);
        packetId++;
    }
}
