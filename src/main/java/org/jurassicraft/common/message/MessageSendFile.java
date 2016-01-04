package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.data.JCPlayerData;
import org.jurassicraft.common.paleopad.JCFile;

import java.util.ArrayList;
import java.util.List;

class MessageSendFile extends AbstractMessage<MessageSendFile>
{
    // Send
    private JCFile file;
    private JCPlayerData playerData;

    // Receive
    private String path;
    private final List<String> children = new ArrayList<>();
    private NBTTagCompound data;
    private boolean isDir;

    public MessageSendFile()
    {
    }

    public MessageSendFile(JCPlayerData data, JCFile file)
    {
        this.file = file;
        this.playerData = data;
    }

    @Override
    public void handleClientMessage(MessageSendFile messageSendFile, EntityPlayer entityPlayer)
    {
        JCFile file = JCPlayerData.getPlayerData(null).getFileFromPath(messageSendFile.path);

        if (!messageSendFile.isDir)
        {
            file.setData(messageSendFile.data);
        }

        for (String child : messageSendFile.children)
        {
            new JCFile(child, file, JurassiCraft.proxy.getPlayer(), messageSendFile.isDir);
        }
    }

    @Override
    public void handleServerMessage(MessageSendFile messageSendFile, EntityPlayer entityPlayer)
    {
        JCFile file = JCPlayerData.getPlayerData(entityPlayer).getFileFromPath(messageSendFile.path);

        if (!messageSendFile.isDir)
        {
            file.setData(messageSendFile.data);
        }

        for (String child : messageSendFile.children)
        {
            new JCFile(child, file, entityPlayer, messageSendFile.isDir);
        }
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        if (file == null)
        {
            ByteBufUtils.writeUTF8String(buffer, "");

            List<JCFile> children = playerData.getFilesAtPath("");

            int i = 0;

            for (JCFile child : children)
            {
                if (child != null)
                {
                    i++;
                }
            }

            buffer.writeInt(i);

            for (JCFile child : children)
            {
                if (child != null)
                {
                    ByteBufUtils.writeUTF8String(buffer, child.getName());
                }
            }
        }
        else
        {
            ByteBufUtils.writeUTF8String(buffer, file.getPath());

            List<JCFile> children = file.getChildren();

            int i = 0;

            for (JCFile child : children)
            {
                if (child != null)
                {
                    i++;
                }
            }

            buffer.writeInt(i);
            buffer.writeBoolean(file.isDirectory());

            for (JCFile child : children)
            {
                if (child != null)
                {
                    ByteBufUtils.writeUTF8String(buffer, child.getName());
                }
            }

            if (file.isFile() && file.getData() != null)
            {
                ByteBufUtils.writeTag(buffer, file.getData());
            }
        }
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        path = ByteBufUtils.readUTF8String(buffer);

        if (path.length() > 0)
        {
            int childCount = buffer.readInt();
            isDir = buffer.readBoolean();

            for (int i = 0; i < childCount; i++)
            {
                String childName = ByteBufUtils.readUTF8String(buffer);

                children.add(childName);
            }

            if (!isDir)
            {
                data = ByteBufUtils.readTag(buffer);
            }
        }
        else
        {
            int childCount = buffer.readInt();
            isDir = true;

            for (int i = 0; i < childCount; i++)
            {
                String childName = ByteBufUtils.readUTF8String(buffer);

                children.add(childName);
            }
        }
    }
}
