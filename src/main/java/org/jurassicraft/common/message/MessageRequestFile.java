package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.data.JCPlayerData;

public class MessageRequestFile implements IMessage
{
    private String path;

    public MessageRequestFile()
    {
    }

    public MessageRequestFile(String path)
    {
        this.path = path;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, path);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        path = ByteBufUtils.readUTF8String(buffer);
    }

    public static class Handler implements IMessageHandler<MessageRequestFile, IMessage>
    {
        @Override
        public IMessage onMessage(final MessageRequestFile packet, final MessageContext ctx)
        {
            JurassiCraft.proxy.scheduleTask(ctx, new Runnable()
            {
                @Override
                public void run()
                {
                    if (ctx.side.isServer())
                    {
                        EntityPlayerMP player = ctx.getServerHandler().playerEntity;

                        if (player != null)
                        {
                            JCPlayerData playerData = JCPlayerData.getPlayerData(player);
                            JurassiCraft.networkManager.networkWrapper.sendTo(new MessageSendFile(playerData, playerData.getFileFromPath(packet.path)), player);
                        }
                    }
                    else // TODO
                    {

                    }
                }
            });

            return null;
        }
    }
}
