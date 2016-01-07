package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.utils.MutableVec3;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

public class MessageHelicopterDirection extends AbstractMessage<MessageHelicopterDirection>
{
    private int heliID;
    private MutableVec3 direction;

    public MessageHelicopterDirection()
    {
        direction = new MutableVec3(0, 0, 0);
    }

    public MessageHelicopterDirection(int heliID, MutableVec3 direction)
    {
        this.heliID = heliID;
        this.direction = direction;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientMessage(MessageHelicopterDirection messageHelicopterDirection, EntityPlayer entityPlayer)
    {
        EntityHelicopterBase helicopter = HelicopterMessages.getHeli(entityPlayer.worldObj, messageHelicopterDirection.heliID);
        if (helicopter != null)
        {
            helicopter.setDirection(messageHelicopterDirection.direction);
        }
    }

    @Override
    public void handleServerMessage(MessageHelicopterDirection messageHelicopterDirection, EntityPlayer entityPlayer)
    {
        EntityHelicopterBase helicopter = HelicopterMessages.getHeli(entityPlayer.worldObj, messageHelicopterDirection.heliID);
        if (helicopter != null)
        {
            helicopter.setDirection(messageHelicopterDirection.direction);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        heliID = buf.readInt();
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        direction.set(x, y, z);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(heliID);
        buf.writeDouble(direction.xCoord);
        buf.writeDouble(direction.yCoord);
        buf.writeDouble(direction.zCoord);
    }
}
