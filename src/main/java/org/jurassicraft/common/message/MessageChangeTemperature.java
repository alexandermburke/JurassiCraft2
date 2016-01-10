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
    private int slot;
    private int temp;
    private int x;
    private int y;
    private int z;
    private BlockPos pos;

    public MessageChangeTemperature()
    {
    }

    public MessageChangeTemperature(BlockPos pos, int slot, int temp)
    {
        this.slot = slot;
        this.temp = temp;
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.pos = new BlockPos(x, y, z);
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
        JurassiCraft.networkWrapper.sendToAll(new MessageChangeTemperature(messageChangeTemperature.pos, messageChangeTemperature.slot, messageChangeTemperature.temp));
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(slot);
        buffer.writeInt(temp);
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        slot = buffer.readInt();
        temp = buffer.readInt();
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();

        pos = new BlockPos(x, y, z);
    }
}
