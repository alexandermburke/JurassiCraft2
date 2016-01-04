package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.entity.item.EntityPaddockSign;

public class MessagePlacePaddockSign extends AbstractMessage<MessagePlacePaddockSign>
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
    public void handleClientMessage(MessagePlacePaddockSign messagePlacePaddockSign, EntityPlayer entityPlayer)
    {

    }

    @Override
    public void handleServerMessage(MessagePlacePaddockSign messagePlacePaddockSign, EntityPlayer entityPlayer)
    {
        World world = entityPlayer.worldObj;

        EnumFacing side = messagePlacePaddockSign.facing;
        BlockPos pos = messagePlacePaddockSign.pos;

        EntityPaddockSign paddockSign = new EntityPaddockSign(world, pos, side, messagePlacePaddockSign.dino);

        if (entityPlayer.canPlayerEdit(pos, side, entityPlayer.getHeldItem()) && paddockSign.onValidSurface())
        {
            world.spawnEntityInWorld(paddockSign);

            if (!entityPlayer.capabilities.isCreativeMode)
            {
                InventoryPlayer inventory = entityPlayer.inventory;
                inventory.decrStackSize(inventory.currentItem, 1);
            }
        }
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
}
