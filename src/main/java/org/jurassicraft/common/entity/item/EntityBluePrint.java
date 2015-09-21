package org.jurassicraft.common.entity.item;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.item.JCItemRegistry;

public class EntityBluePrint extends EntityHanging implements IEntityAdditionalSpawnData
{
    private int dinosaur;

    public EntityBluePrint(World world)
    {
        super(world);
    }

    public EntityBluePrint(World world, BlockPos pos, EnumFacing enumFacing, int dinosaur)
    {
        super(world, pos);
        setType(dinosaur);

        this.func_174859_a(enumFacing);
    }

    private void setType(int dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setInteger("Dinosaur", this.dinosaur);
        super.writeEntityToNBT(tagCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        setType(tagCompund.getInteger("Dinosaur"));

        super.readEntityFromNBT(tagCompund);
    }

    public int getWidthPixels()
    {
        return 32;
    }

    public int getHeightPixels()
    {
        return 16;
    }

    /**
     * Called when this entity is broken. Entity parameter may be null.
     */
    public void onBroken(Entity entity)
    {
        if (this.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        {
            if (entity instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer) entity;

                if (entityplayer.capabilities.isCreativeMode)
                {
                    return;
                }
            }

            ItemStack stack = new ItemStack(JCItemRegistry.blue_print);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("Dinosaur", dinosaur);

            this.entityDropItem(stack, 0.0F);
        }
    }

    /**
     * Sets the location and Yaw/Pitch of an entity in the world
     */
    public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch)
    {
        BlockPos blockpos = new BlockPos(x - this.posX, y - this.posY, z - this.posZ);
        BlockPos blockpos1 = this.hangingPosition.add(blockpos);
        this.setPosition((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean p_180426_10_)
    {
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeInt(dinosaur);
        buffer.writeLong(getHangingPosition().toLong());
        buffer.writeByte(facingDirection.getHorizontalIndex());
    }

    @Override
    public void readSpawnData(ByteBuf buf)
    {
        setType(buf.readInt());
        hangingPosition = BlockPos.fromLong(buf.readLong());
        func_174859_a(EnumFacing.getHorizontal(buf.readByte()));
    }

    public int getDinosaur()
    {
        return dinosaur;
    }
}