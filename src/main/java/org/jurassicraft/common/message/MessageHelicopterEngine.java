package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

public class MessageHelicopterEngine extends AbstractMessage<MessageHelicopterEngine>
{
    private int heliID;
    private boolean engineState;

    public MessageHelicopterEngine()
    {
    }

    public MessageHelicopterEngine(int heliID, boolean engineState)
    {
        this.heliID = heliID;
        this.engineState = engineState;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientMessage(MessageHelicopterEngine messageHelicopterEngine, EntityPlayer entityPlayer)
    {
        EntityHelicopterBase helicopter = HelicopterMessages.getHeli(entityPlayer.worldObj, messageHelicopterEngine.heliID);
        if (helicopter != null)
        {
            helicopter.setEngineRunning(messageHelicopterEngine.engineState);
        }
    }

    @Override
    public void handleServerMessage(MessageHelicopterEngine messageHelicopterEngine, EntityPlayer entityPlayer)
    {
        EntityHelicopterBase helicopter = HelicopterMessages.getHeli(entityPlayer.worldObj, messageHelicopterEngine.heliID);
        if (helicopter != null)
        {
            helicopter.setEngineRunning(messageHelicopterEngine.engineState);
        }
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
}
