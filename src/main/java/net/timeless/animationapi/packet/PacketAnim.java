package net.timeless.animationapi.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.JurassiCraft;

public class PacketAnim implements IMessage
{
    public short animationId;
    public int entityId;

    public PacketAnim()
    {
    }

    public PacketAnim(Entity entity)
    {
        this.entityId = entity.getEntityId();

        IAnimatedEntity animatedEntity = (IAnimatedEntity) entity;
        this.animationId = (short) animatedEntity.getAnimID().ordinal();
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(entityId);
        buffer.writeShort(animationId);
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        entityId = buffer.readInt();
        animationId = buffer.readShort();
    }

    public static class Handler implements IMessageHandler<PacketAnim, IMessage>
    {
        @Override
        public IMessage onMessage(final PacketAnim packet, final MessageContext ctx)
        {
            AnimationAPI.proxy.scheduleTask(ctx, new Runnable()
            {
                @Override
                public void run()
                {
                    if (ctx.side.isClient())
                    {
                        EntityPlayer player = JurassiCraft.proxy.getPlayerEntityFromContext(ctx);

                        Entity entity = player.worldObj.getEntityByID(packet.entityId);

                        if (entity != null)
                        {
                            IAnimatedEntity animatedEntity = (IAnimatedEntity) entity;
                            animatedEntity.setAnimID(AnimID.values()[packet.animationId]);
                        }
                    }
                }
            });

            return null;
        }
    }
}
