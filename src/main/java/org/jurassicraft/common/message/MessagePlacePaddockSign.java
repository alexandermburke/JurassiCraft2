package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.entity.item.EntityPaddockSign;

public class MessagePlacePaddockSign implements IMessage
{
    private int dino;
    private BlockPos pos;
    private EnumFacing facing;

    public MessagePlacePaddockSign()
    {
    }

    public MessagePlacePaddockSign(EnumFacing facing, BlockPos pos, Dinosaur dino)
    {
        this.dino = JCEntityRegistry.getDinosaurId(dino);
        this.pos = pos;
        this.facing = facing;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeLong(pos.toLong());
        buffer.writeInt(dino);
        buffer.writeByte((byte) facing.getIndex());
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        pos = BlockPos.fromLong(buffer.readLong());
        dino = buffer.readInt();
        facing = EnumFacing.getFront(buffer.readByte());
    }

    public static class Handler implements IMessageHandler<MessagePlacePaddockSign, IMessage>
    {
        @Override
        public IMessage onMessage(final MessagePlacePaddockSign packet, final MessageContext ctx)
        {
            JurassiCraft.proxy.scheduleTask(ctx, new Runnable()
            {
                @Override
                public void run()
                {
                    if (ctx.side.isServer())
                    {
                        EntityPlayer player = JurassiCraft.proxy.getPlayerEntityFromContext(ctx);
                        World world = player.worldObj;

                        EnumFacing side = packet.facing;
                        BlockPos pos = packet.pos;

                        EntityPaddockSign paddockSign = new EntityPaddockSign(world, pos, side, packet.dino);

                        if (player.canPlayerEdit(pos, side, player.getHeldItem()) && paddockSign.onValidSurface())
                        {
                            world.spawnEntityInWorld(paddockSign);

                            if (!player.capabilities.isCreativeMode)
                            {
                                InventoryPlayer inventory = player.inventory;
                                inventory.decrStackSize(inventory.currentItem, 1);
                            }
                        }
                    }
                }
            });

            return null;
        }
    }
}
