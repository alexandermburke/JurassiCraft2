package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;
import org.jurassicraft.common.vehicles.helicopter.modules.EnumModulePosition;
import org.jurassicraft.common.vehicles.helicopter.modules.HelicopterModuleSpot;

public class MessageHelicopterModules extends AbstractMessage<MessageHelicopterModules>
{
    private NBTTagCompound compound;
    private EnumModulePosition pos;
    private HelicopterModuleSpot spot;
    private int heliID;

    public MessageHelicopterModules()
    {
    }

    public MessageHelicopterModules(int heliID, EnumModulePosition pos, HelicopterModuleSpot spot)
    {
        this.heliID = heliID;
        this.pos = pos;
        this.spot = spot;
        compound = new NBTTagCompound();
        spot.writeToNBT(compound);
    }

    @Override
    public void handleClientMessage(MessageHelicopterModules messageHelicopterModules, EntityPlayer entityPlayer)
    {
        EntityHelicopterBase helicopter = HelicopterMessages.getHeli(entityPlayer.worldObj, messageHelicopterModules.heliID);
        if (helicopter != null)
        {
            System.out.println(messageHelicopterModules.heliID);
            HelicopterModuleSpot spot = helicopter.getModuleSpot(messageHelicopterModules.pos);
            spot.readFromNBT(messageHelicopterModules.compound);
            System.out.println(messageHelicopterModules.compound);
        }
    }

    @Override
    public void handleServerMessage(MessageHelicopterModules messageHelicopterModules, EntityPlayer entityPlayer)
    {
        EntityHelicopterBase helicopter = HelicopterMessages.getHeli(entityPlayer.worldObj, messageHelicopterModules.heliID);
        if (helicopter != null)
        {
            System.out.println(messageHelicopterModules.heliID);
            HelicopterModuleSpot spot = helicopter.getModuleSpot(messageHelicopterModules.pos);
            spot.readFromNBT(messageHelicopterModules.compound);
            System.out.println(messageHelicopterModules.compound);
        }
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
}
