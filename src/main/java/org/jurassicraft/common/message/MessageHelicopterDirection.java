package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.utils.MutableVec3;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

public class MessageHelicopterDirection implements IMessage
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

    public static class Handler implements IMessageHandler<MessageHelicopterDirection, IMessage>
    {
        @Override
        public IMessage onMessage(MessageHelicopterDirection packet, MessageContext ctx)
        {
            World world = null;
            if (ctx.side == Side.CLIENT)
            {
                world = getClientWorld();
            }
            else
            {
                world = ctx.getServerHandler().playerEntity.worldObj;
            }
            EntityHelicopterBase helicopter = (EntityHelicopterBase) world.getEntityByID(packet.heliID);
            if (helicopter != null)
                helicopter.setDirection(packet.direction);
            return null;
        }

        @SideOnly(Side.CLIENT)
        private World getClientWorld()
        {
            return FMLClientHandler.instance().getWorldClient();
        }

    }
}
