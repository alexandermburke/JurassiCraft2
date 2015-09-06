package net.timeless.jurassicraft.common.entity.item;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
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

import java.util.ArrayList;

public class EntityJurassiCraftSign extends EntityHanging implements IEntityAdditionalSpawnData
{
    public EnumSignType signType;

    public EntityJurassiCraftSign(World world)
    {
        super(world);
    }

    public EntityJurassiCraftSign(World world, BlockPos blockPos, EnumFacing enumFacing)
    {
        super(world, blockPos);
        ArrayList arraylist = Lists.newArrayList();
        EntityJurassiCraftSign.EnumSignType[] aenumart = EntityJurassiCraftSign.EnumSignType.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j)
        {
            EntityJurassiCraftSign.EnumSignType enumart = aenumart[j];
            this.signType = enumart;
            this.func_174859_a(enumFacing);

            if (this.onValidSurface())
            {
                arraylist.add(enumart);
            }
        }

        if (!arraylist.isEmpty())
        {
            this.signType = (EntityJurassiCraftSign.EnumSignType) arraylist.get(this.rand.nextInt(arraylist.size()));
        }

        this.func_174859_a(enumFacing);
    }

    @SideOnly(Side.CLIENT)
    public EntityJurassiCraftSign(World world, BlockPos pos, EnumFacing enumFacing, String titleName)
    {
        this(world, pos, enumFacing);
        setType(titleName);

        this.func_174859_a(enumFacing);
    }

    private void setType(String titleName)
    {
        EntityJurassiCraftSign.EnumSignType[] art = EntityJurassiCraftSign.EnumSignType.values();
        int i = art.length;

        for (int j = 0; j < i; ++j)
        {
            EntityJurassiCraftSign.EnumSignType enumart = art[j];

            if (enumart.title.equals(titleName))
            {
                this.signType = enumart;
                break;
            }
        }

        if (this.signType == null)
            this.signType = EnumSignType.GENTLE_GIANTS;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setString("Motive", this.signType.title);
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
        return this.signType.sizeX;
    }

    public int getHeightPixels()
    {
        return this.signType.sizeY;
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

            this.entityDropItem(new ItemStack(JCItemRegistry.jc_sign), 0.0F);
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

    public enum EnumSignType
    {
        GENTLE_GIANTS("Gentle Giants", 128, 64, 0, 0);

        public final String title;
        public final int sizeX;
        public final int sizeY;
        public final int offsetX;
        public final int offsetY;

        EnumSignType(String title, int xSize, int ySize, int textureX, int textureY)
        {
            this.title = title;
            this.sizeX = xSize;
            this.sizeY = ySize;
            this.offsetX = textureX;
            this.offsetY = textureY;
        }
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean p_180426_10_)
    {
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, signType.title);
        buffer.writeLong(getHangingPosition().toLong());
        buffer.writeByte(facingDirection.getHorizontalIndex());
    }

    @Override
    public void readSpawnData(ByteBuf buf)
    {
        setType(ByteBufUtils.readUTF8String(buf));
        hangingPosition = BlockPos.fromLong(buf.readLong());
        func_174859_a(EnumFacing.getHorizontal(buf.readByte()));
    }
}