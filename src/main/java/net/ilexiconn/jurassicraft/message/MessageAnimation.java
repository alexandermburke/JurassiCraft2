package net.ilexiconn.jurassicraft.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.jurassicraft.api.IAnimatedEntity;
import net.ilexiconn.llibrary.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;

public class MessageAnimation extends AbstractMessage<MessageAnimation>
{
    private byte animationId;
    private int entityId;

    public MessageAnimation()
    {

    }

    public MessageAnimation(byte animation, int entity)
    {
        animationId = animation;
        entityId = entity;
    }

    public void handleClientMessage(MessageAnimation packetAnimation, EntityPlayer entityPlayer)
    {
        IAnimatedEntity entity = (IAnimatedEntity) entityPlayer.getEntityWorld().getEntityByID(packetAnimation.entityId);
        if (entity != null && packetAnimation.animationId != -1)
        {
            entity.setAnimationId(packetAnimation.animationId);
            if (packetAnimation.animationId == 0)
                entity.setAnimationTick(0);
        }
    }

    public void handleServerMessage(MessageAnimation packetAnimation, EntityPlayer entityPlayer)
    {

    }

    public void toBytes(ByteBuf buffer)
    {
        buffer.writeByte(animationId);
        buffer.writeInt(entityId);
    }

    public void fromBytes(ByteBuf buffer)
    {
        animationId = buffer.readByte();
        entityId = buffer.readInt();
    }
}
