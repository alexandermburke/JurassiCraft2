package net.timeless.animationapi.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class PacketFinishedAnim implements IMessage
{
    private byte animID;
    private int entityID;

    public PacketFinishedAnim()
    {
    }

    public PacketFinishedAnim(byte anim, int entity)
    {
        animID = anim;
        entityID = entity;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeByte(animID);
        buffer.writeInt(entityID);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        animID = buffer.readByte();
        entityID = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<PacketFinishedAnim, IMessage>
    {
        @Override
        public IMessage onMessage(final PacketFinishedAnim packet, MessageContext ctx)
        {
        	final EntityPlayerMP player = (EntityPlayerMP) JurassiCraft.proxy.getPlayerEntityFromContext(ctx);

        	player.getServerForPlayer().addScheduledTask(new Runnable() 
            {
                @Override
                public void run() 
                {        	
                	World world = player.worldObj;
                	EntityDinosaur entity = (EntityDinosaur) world.getEntityByID(packet.entityID);

                    if (entity != null && packet.animID != -1)
                    {
                        entity.setAnimID(packet.animID);
                    }
                    
                    // DEBUG
                    System.out.println("PacketFinishedAnim received for entity "+packet.entityID+" and animation ID "+packet.animID);
                }
            });

            return null;
        }
    }
}
