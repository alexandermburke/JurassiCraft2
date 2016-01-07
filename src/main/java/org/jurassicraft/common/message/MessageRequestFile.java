package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
    @SideOnly(Side.CLIENT)
    public void handleClientMessage(MessageRequestFile messageRequestFile, EntityPlayer entityPlayer)
    {
        System.out.println("NOPE"); //TODO
    }

    @Override
    public void handleServerMessage(MessageRequestFile messageRequestFile, EntityPlayer entityPlayer)
    {
        JCPlayerData playerData = JCPlayerData.getPlayerData(entityPlayer);
        JurassiCraft.networkWrapper.sendTo(new MessageSendFile(playerData, playerData.getFileFromPath(messageRequestFile.path)), (EntityPlayerMP) entityPlayer);
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
