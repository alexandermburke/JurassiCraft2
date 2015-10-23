package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;
import org.jurassicraft.common.vehicles.helicopter.modules.EnumModulePosition;
import org.jurassicraft.common.vehicles.helicopter.modules.HelicopterModuleSpot;

public class MessageHelicopterModules implements IMessage
{
    private NBTTagCompound compound;
    private EnumModulePosition pos;
    private HelicopterModuleSpot spot;
    private int heliID;

    public MessageHelicopterModules()
    {}

    public MessageHelicopterModules(int heliID, EnumModulePosition pos, HelicopterModuleSpot spot)
    {
        this.heliID = heliID;
        this.pos = pos;
        this.spot = spot;
        compound = new NBTTagCompound();
        spot.writeToNBT(compound);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        heliID = buf.readInt();
        pos = EnumModulePosition.values()[buf.readInt()];
        compound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(heliID);
        buf.writeInt(pos.ordinal());
        ByteBufUtils.writeTag(buf, compound);
    }

    public static class Handler implements IMessageHandler<MessageHelicopterModules, IMessage>
    {
        @Override
        public IMessage onMessage(MessageHelicopterModules packet, MessageContext ctx)
        {
            World world;
            if (ctx.side == Side.CLIENT)
            {
                world = getClientWorld();
            }
            else
            {
                world = ctx.getServerHandler().playerEntity.worldObj;
            }
            EntityHelicopterBase helicopter = HelicopterMessages.getHeli(world, packet.heliID);
            if (helicopter != null)
            {
                System.out.println(packet.heliID);
                HelicopterModuleSpot spot = helicopter.getModuleSpot(packet.pos);
                spot.readFromNBT(packet.compound);
                System.out.println(packet.compound);
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
