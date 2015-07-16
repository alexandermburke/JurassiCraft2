package net.timeless.jurassicraft.common.entity.item;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

import com.google.common.collect.Lists;

public class EntityBluePrint extends EntityHanging implements IEntityAdditionalSpawnData
{
    public EnumBluePrint art;

    public EntityBluePrint(World world)
    {
        super(world);
    }

    public EntityBluePrint(World world, BlockPos blockPos, EnumFacing enumFacing)
    {
        super(world, blockPos);
        ArrayList arraylist = Lists.newArrayList();
        EntityBluePrint.EnumBluePrint[] aenumart = EntityBluePrint.EnumBluePrint.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j)
        {
            EntityBluePrint.EnumBluePrint enumart = aenumart[j];
            this.art = enumart;
            this.func_174859_a(enumFacing);

            if (this.onValidSurface())
            {
                arraylist.add(enumart);
            }
        }

        if (!arraylist.isEmpty())
        {
            this.art = (EntityBluePrint.EnumBluePrint) arraylist.get(this.rand.nextInt(arraylist.size()));
        }

        this.func_174859_a(enumFacing);
    }

    @SideOnly(Side.CLIENT)
    public EntityBluePrint(World world, BlockPos pos, EnumFacing enumFacing, String titleName)
    {
        this(world, pos, enumFacing);
        setType(titleName);

        this.func_174859_a(enumFacing);
    }

    private void setType(String titleName)
    {
        EntityBluePrint.EnumBluePrint[] art = EntityBluePrint.EnumBluePrint.values();
        int i = art.length;

        for (int j = 0; j < i; ++j)
        {
            EntityBluePrint.EnumBluePrint enumart = art[j];

            if (enumart.title.equals(titleName))
            {
                this.art = enumart;
                break;
            }
        }

        if (this.art == null)
            this.art = EnumBluePrint.INDOMINUS;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setString("Motive", this.art.title);
        super.writeEntityToNBT(tagCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        String s = tagCompund.getString("Motive");

        setType(s);

        super.readEntityFromNBT(tagCompund);
    }

    public int getWidthPixels()
    {
        return this.art.sizeX;
    }

    public int getHeightPixels()
    {
        return this.art.sizeY;
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

            this.entityDropItem(new ItemStack(JCItemRegistry.blue_print), 0.0F);
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

    public enum EnumBluePrint
    {
        INDOMINUS("Indominus", 32, 16, 0, 0);

        public final String title;
        public final int sizeX;
        public final int sizeY;
        public final int offsetX;
        public final int offsetY;

        EnumBluePrint(String title, int xSize, int ySize, int textureX, int textureY)
        {
            this.title = title;
            this.sizeX = xSize;
            this.sizeY = ySize;
            this.offsetX = textureX;
            this.offsetY = textureY;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_)
    {
        //        BlockPos blockpos = new BlockPos(p_180426_1_ - this.posX, p_180426_3_ - this.posY, p_180426_5_ - this.posZ);
        //        BlockPos blockpos1 = this.hangingPosition.add(blockpos);
        //        this.setPosition((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, art.title);
        buffer.writeLong(func_174857_n().toLong());
        buffer.writeByte(field_174860_b.getHorizontalIndex());
    }

    @Override
    public void readSpawnData(ByteBuf buf)
    {
        setType(ByteBufUtils.readUTF8String(buf));
        hangingPosition = BlockPos.fromLong(buf.readLong());
        func_174859_a(EnumFacing.getHorizontal(buf.readByte()));
    }
}