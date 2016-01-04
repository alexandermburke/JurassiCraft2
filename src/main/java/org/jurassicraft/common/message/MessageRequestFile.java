package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.data.JCPlayerData;

public class MessageRequestFile extends AbstractMessage<MessageRequestFile>
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
    public void handleClientMessage(MessageRequestFile messageRequestFile, EntityPlayer entityPlayer)
    {

    }

    @Override
    public void handleServerMessage(MessageRequestFile messageRequestFile, EntityPlayer entityPlayer)
    {
        JCPlayerData playerData = JCPlayerData.getPlayerData(entityPlayer);
        JurassiCraft.networkManager.networkWrapper.sendTo(new MessageSendFile(playerData, playerData.getFileFromPath(messageRequestFile.path)), (EntityPlayerMP) entityPlayer);
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
}
