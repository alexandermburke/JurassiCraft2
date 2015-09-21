package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageChangeTemperature implements IMessage
{
    private byte slot;
    private byte temp;
    private BlockPos pos;

    public MessageChangeTemperature()
    {
    }

    public MessageChangeTemperature(BlockPos pos, int slot, int temp)
    {
        this.slot = (byte) slot;
        this.temp = (byte) temp;
        this.pos = pos;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeByte(slot);
        buffer.writeByte(temp);
        buffer.writeLong(pos.toLong());
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        slot = buffer.readByte();
        temp = buffer.readByte();
        pos = BlockPos.fromLong(buffer.readLong());
    }

    public static class Handler implements IMessageHandler<MessageChangeTemperature, IMessage>
    {
        @Override
        public IMessage onMessage(MessageChangeTemperature packet, MessageContext ctx)
        {
            if (ctx.side.isServer())
            {
                IInventory incubator = (IInventory) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(packet.pos);
                incubator.setField(packet.slot + 10, packet.temp);
            }

            return null;
        }
    }
}
