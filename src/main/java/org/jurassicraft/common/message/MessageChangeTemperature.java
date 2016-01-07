package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;

public class MessageChangeTemperature extends AbstractMessage<MessageChangeTemperature>
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
    @SideOnly(Side.CLIENT)
    public void handleClientMessage(MessageChangeTemperature messageChangeTemperature, EntityPlayer entityPlayer)
    {
        IInventory incubator = (IInventory) entityPlayer.worldObj.getTileEntity(messageChangeTemperature.pos);
        incubator.setField(messageChangeTemperature.slot + 10, messageChangeTemperature.temp);
    }

    @Override
    public void handleServerMessage(MessageChangeTemperature messageChangeTemperature, EntityPlayer entityPlayer)
    {
        IInventory incubator = (IInventory) entityPlayer.worldObj.getTileEntity(messageChangeTemperature.pos);
        incubator.setField(messageChangeTemperature.slot + 10, messageChangeTemperature.temp);
        JurassiCraft.networkWrapper.sendToAll(messageChangeTemperature);
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
}
