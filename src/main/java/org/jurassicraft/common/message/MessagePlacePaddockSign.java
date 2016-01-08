package org.jurassicraft.common.message;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.entity.item.EntityPaddockSign;

public class MessagePlacePaddockSign extends AbstractMessage<MessagePlacePaddockSign>
{
    private int dino;
    private BlockPos pos;
    private int x;
    private int y;
    private int z;
    private EnumFacing facing;

    public MessagePlacePaddockSign()
    {
    }

    public MessagePlacePaddockSign(EnumFacing facing, BlockPos pos, Dinosaur dino)
    {
        this.dino = JCEntityRegistry.getDinosaurId(dino);
        this.pos = new BlockPos(x, y, z);
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.facing = facing;
    }

    @Override
    @SideOnly(Side.CLIENT)
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
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeInt(dino);
        buffer.writeByte((byte) facing.getIndex());
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        dino = buffer.readInt();
        facing = EnumFacing.getFront(buffer.readByte());
    }
}
