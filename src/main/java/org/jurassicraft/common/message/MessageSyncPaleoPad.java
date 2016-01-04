package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.jurassicraft.common.entity.data.JCPlayerData;

class MessageSyncPaleoPad extends AbstractMessage<MessageSyncPaleoPad>
{
    private NBTTagCompound nbt;

    public MessageSyncPaleoPad()
    {
    }

    public MessageSyncPaleoPad(EntityPlayer player)
    {
        nbt = new NBTTagCompound();
        JCPlayerData.getPlayerData(player).saveNBTData(nbt);
    }

    @Override
    public void handleClientMessage(MessageSyncPaleoPad messageSyncPaleoPad, EntityPlayer entityPlayer)
    {
        JCPlayerData.setPlayerData(null, messageSyncPaleoPad.nbt);
    }

    @Override
    public void handleServerMessage(MessageSyncPaleoPad messageSyncPaleoPad, EntityPlayer entityPlayer)
    {
        JCPlayerData.setPlayerData(entityPlayer, messageSyncPaleoPad.nbt);
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        ByteBufUtils.writeTag(buffer, nbt);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        nbt = ByteBufUtils.readTag(buffer);
    }
}
