package net.ilexiconn.jurassicraft.packets;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.jurassicraft.tileentity.TileCleaningStation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageCleaningTable implements IMessage
{
    private int xCoord;
    private int yCoord;
    private int zCoord;

    public MessageCleaningTable(int xCoord, int yCoord, int zCoord)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.xCoord = ByteBufUtils.readVarInt(buf, 5);
        this.yCoord = ByteBufUtils.readVarInt(buf, 5);
        this.zCoord = ByteBufUtils.readVarInt(buf, 5);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeVarInt(buf, this.xCoord, 5);
        ByteBufUtils.writeVarInt(buf, this.yCoord, 5);
        ByteBufUtils.writeVarInt(buf, this.zCoord, 5);
    }

    public static class Handler implements IMessageHandler<MessageCleaningTable, IMessage>
    {
        @Override
        public IMessage onMessage(MessageCleaningTable message, MessageContext ctx)
        {
            if (ctx.getServerHandler().playerEntity != null && !ctx.getServerHandler().playerEntity.worldObj.isRemote)
            {
                BlockPos pos = new BlockPos(message.xCoord, message.yCoord, message.zCoord);
                TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(pos);
              
                if (tileEntity instanceof TileCleaningStation)
                {
                    TileCleaningStation cleaningStation = (TileCleaningStation) tileEntity;
                    cleaningStation.canCleanFossil();
                    cleaningStation.cleanFossil();
                }
            }
            
            return null;
        }
    }
}
