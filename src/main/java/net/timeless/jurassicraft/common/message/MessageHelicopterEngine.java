package net.timeless.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.timeless.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;
import net.timeless.jurassicraft.common.vehicles.helicopter.HelicopterSeat;

import java.util.UUID;

public class MessageHelicopterEngine implements IMessage
{
    private UUID heliID;
    private boolean engineState;

    public MessageHelicopterEngine() {}

    public MessageHelicopterEngine(UUID heliID, boolean engineState)
    {
        this.heliID = heliID;
        this.engineState = engineState;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        heliID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        engineState = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, heliID.toString());
        buf.writeBoolean(engineState);
    }

    public static class Handler implements IMessageHandler<MessageHelicopterEngine, IMessage>
    {
        @Override
        public IMessage onMessage(MessageHelicopterEngine packet, MessageContext ctx)
        {
            UUID id = packet.heliID;
            World world = null;
            if(ctx.side == Side.CLIENT)
            {
                world = FMLClientHandler.instance().getWorldClient();
            }
            else
            {
                world = ctx.getServerHandler().playerEntity.worldObj;
            }
            EntityHelicopterBase helicopter = HelicopterSeat.getParentFromID(world, packet.heliID);
            if(helicopter != null)
                helicopter.setEngineRunning(packet.engineState);
            return null;
        }
    }
}
