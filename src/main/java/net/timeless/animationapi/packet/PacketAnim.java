package net.timeless.animationapi.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.timeless.animationapi.client.AnimID;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class PacketAnim implements IMessage
{
    private int animID;
    private int entityID;

    public PacketAnim()
    {
    }

    public PacketAnim(int anim, int entity)
    {
//        // DEBUG
//        System.out.println("Constructing PacketAnim");

        animID = anim;
        entityID = entity;
    }

    public PacketAnim(AnimID anim, int entity)
    {
//        // DEBUG
//        System.out.println("Constructing PacketAnim");

        animID = anim.ordinal();
        entityID = entity;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(animID);
        buffer.writeInt(entityID);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        animID = buffer.readInt();
        entityID = buffer.readInt();
    }

    public static class Handler implements IMessageHandler<PacketAnim, IMessage>
    {
        @Override
        public IMessage onMessage(final PacketAnim packet, MessageContext ctx)
        {
            // DEBUG
            System.out.println("PacketAnim received for entity " + packet.entityID + " and animation ID "
                    + packet.animID);

            final EntityPlayer player = JurassiCraft.proxy.getPlayerEntityFromContext(ctx);

            Minecraft.getMinecraft().addScheduledTask(new Runnable()
            {
                @Override
                public void run()
                {
                    World world = player.worldObj;
                    EntityDinosaur entity = (EntityDinosaur) world.getEntityByID(packet.entityID);

                    if (entity != null && packet.animID != -1)
                    {
                        entity.setAnimID(AnimID.values()[packet.animID]);
                    }

                }
            });

            return null;
        }
    }
}
