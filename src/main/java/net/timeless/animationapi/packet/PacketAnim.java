package net.timeless.animationapi.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class PacketAnim implements IMessage
{
    private int animID;
    private int entityID;

    public PacketAnim()
    {

    }

    public PacketAnim(AnimID parAnimID, int parEntityID)
    {
        JurassiCraft.instance.getLogger().debug("Constructing PacketAnim for entity " + parEntityID + " with animation id " + parAnimID);

        animID = parAnimID.ordinal();
        entityID = parEntityID;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        entityID = buf.readInt();
        animID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(entityID);
        buf.writeInt(animID);
    }

    public static class Handler implements IMessageHandler<PacketAnim, IMessage>
    {
        @Override
        public IMessage onMessage(final PacketAnim packet, MessageContext ctx)
        {
            final AnimID animationId = AnimID.values()[packet.animID];

            JurassiCraft.instance.getLogger().info("PacketAnim received for entity " + packet.entityID + " and animation ID " + animationId);

            final EntityPlayer player = JurassiCraft.proxy.getPlayerEntityFromContext(ctx);

            Minecraft.getMinecraft().addScheduledTask(new Runnable()
            {
                @Override
                public void run()
                {
                    World world = player.worldObj;
                    EntityDinosaur entity = (EntityDinosaur) world.getEntityByID(packet.entityID);

                    if (entity != null)
                    {
                        entity.setAnimID(animationId);
                    }

                }
            });

            return null;
        }
    }
}
