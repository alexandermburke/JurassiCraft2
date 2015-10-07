package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

public class MessageHelicopterEngine implements IMessage
{
    private int heliID;
    private boolean engineState;

    public MessageHelicopterEngine() {}

    public MessageHelicopterEngine(int heliID, boolean engineState)
    {
        this.heliID = heliID;
        this.engineState = engineState;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        heliID = buf.readInt();
        engineState = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(heliID);
        buf.writeBoolean(engineState);
    }

    public static class Handler implements IMessageHandler<MessageHelicopterEngine, IMessage>
    {
        @Override
        public IMessage onMessage(MessageHelicopterEngine packet, MessageContext ctx)
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
            {
                helicopter.setEngineRunning(packet.engineState);
            }
            return null;
        }

        @SideOnly(Side.CLIENT)
        private World getClientWorld()
        {
            return FMLClientHandler.instance().getWorldClient();
        }
    }
}
