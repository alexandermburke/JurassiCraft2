package net.timeless.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;
import sun.plugin2.message.Message;

public class MessageSyncPaleoPad implements IMessage
{
    private NBTTagCompound nbt;

    public MessageSyncPaleoPad()
    {
    }

    public MessageSyncPaleoPad(EntityPlayer player)
    {
        NBTTagCompound nbt = new NBTTagCompound();
        JCPlayerData.getPlayerData(player).saveNBTData(nbt);
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

    public static class Handler implements IMessageHandler<MessageSyncPaleoPad, IMessage>
    {
        @Override
        public IMessage onMessage(MessageSyncPaleoPad packet, MessageContext ctx)
        {
            if (ctx.side.isClient())
            {
                JCPlayerData.setPlayerData(null, packet.nbt);
            }
            else
            {
                EntityPlayerMP player = ctx.getServerHandler().playerEntity;

                if (player != null)
                {
                    JCPlayerData.setPlayerData(player, packet.nbt);
                }
            }


            return null;
        }
    }
}
