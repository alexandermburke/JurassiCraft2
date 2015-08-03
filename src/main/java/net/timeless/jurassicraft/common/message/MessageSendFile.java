package net.timeless.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;
import net.timeless.jurassicraft.common.paleopad.JCFile;

import java.util.List;

public class MessageSendFile implements IMessage
{
    //Send
    private JCFile file;

    //Receive
    private String path;
    private List<String> children;
    private NBTTagCompound data;
    private boolean isFile;

    public MessageSendFile()
    {
    }

    public MessageSendFile(JCFile file)
    {
        this.file = file;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, file.getPath());

        List<JCFile> children = file.getChildren();

        buffer.writeInt(children.size());
        buffer.writeBoolean(file.isFile());

        for (JCFile child : children)
        {
            ByteBufUtils.writeUTF8String(buffer, child.getName());
        }

        if(file.isFile() && file.getData() != null)
        {
            ByteBufUtils.writeTag(buffer, file.getData());
        }
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        path = ByteBufUtils.readUTF8String(buffer);

        int childCount = buffer.readInt();
        isFile = buffer.readBoolean();

        for (int i = 0; i < childCount; i++)
        {
            String childName = ByteBufUtils.readUTF8String(buffer);

            children.add(childName);
        }

        if(isFile)
        {
            data = ByteBufUtils.readTag(buffer);
        }
    }

    public static class Handler implements IMessageHandler<MessageSendFile, IMessage>
    {
        @Override
        public IMessage onMessage(MessageSendFile packet, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                JCFile file = JCPlayerData.getPlayerData(null).getFileFromPath(packet.path);

                if(packet.isFile)
                {
                    file.setData(file.getData());
                }

                for (String child : packet.children)
                {
                    new JCFile(child, file, JurassiCraft.proxy.getPlayer(), !packet.isFile);
                }
            }
            else
            {
                EntityPlayerMP player = ctx.getServerHandler().playerEntity;

                if (player != null)
                {
                    JCFile file = JCPlayerData.getPlayerData(player).getFileFromPath(packet.path);

                    if(packet.isFile)
                    {
                        file.setData(file.getData());
                    }

                    for (String child : packet.children)
                    {
                        new JCFile(child, file, player, !packet.isFile);
                    }
                }
            }

            return null;
        }
    }
}
