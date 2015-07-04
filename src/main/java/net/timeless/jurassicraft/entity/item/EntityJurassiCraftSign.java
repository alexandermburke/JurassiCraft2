package net.timeless.jurassicraft.entity.item;

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
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.item.JCItemRegistry;

import com.google.common.collect.Lists;

public class EntityJurassiCraftSign extends EntityHanging implements IEntityAdditionalSpawnData
{
    public EnumArt art;

    public EntityJurassiCraftSign(World worldIn)
    {
        super(worldIn);
    }

    public EntityJurassiCraftSign(World worldIn, BlockPos p_i45849_2_, EnumFacing p_i45849_3_)
    {
        super(worldIn, p_i45849_2_);
        ArrayList arraylist = Lists.newArrayList();
        EntityJurassiCraftSign.EnumArt[] aenumart = EntityJurassiCraftSign.EnumArt.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j)
        {
            EntityJurassiCraftSign.EnumArt enumart = aenumart[j];
            this.art = enumart;
            this.func_174859_a(p_i45849_3_);

            if (this.onValidSurface())
            {
                arraylist.add(enumart);
            }
        }

        if (!arraylist.isEmpty())
        {
            this.art = (EntityJurassiCraftSign.EnumArt) arraylist.get(this.rand.nextInt(arraylist.size()));
        }

        this.func_174859_a(p_i45849_3_);
    }

    @SideOnly(Side.CLIENT)
    public EntityJurassiCraftSign(World worldIn, BlockPos p_i45850_2_, EnumFacing p_i45850_3_, String p_i45850_4_)
    {
        this(worldIn, p_i45850_2_, p_i45850_3_);
        EntityJurassiCraftSign.EnumArt[] aenumart = EntityJurassiCraftSign.EnumArt.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j)
        {
            EntityJurassiCraftSign.EnumArt enumart = aenumart[j];

            if (enumart.title.equals(p_i45850_4_))
            {
                this.art = enumart;
                break;
            }
        }

        this.func_174859_a(p_i45850_3_);
    }

    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
    {
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
        EntityJurassiCraftSign.EnumArt[] aenumart = EntityJurassiCraftSign.EnumArt.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j)
        {
            EntityJurassiCraftSign.EnumArt enumart = aenumart[j];

            if (enumart.title.equals(s))
            {
                this.art = enumart;
            }
        }

        if (this.art == null)
        {
            this.art = EntityJurassiCraftSign.EnumArt.GENTLE_GIANTS;
        }

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
    public void onBroken(Entity p_110128_1_)
    {
        if (this.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        {
            if (p_110128_1_ instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer) p_110128_1_;

                if (entityplayer.capabilities.isCreativeMode)
                {
                    return;
                }
            }

            this.entityDropItem(new ItemStack(JCItemRegistry.gentle_giants_sign), 0.0F);
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
    public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_)
    {
        BlockPos blockpos = new BlockPos(p_180426_1_ - this.posX, p_180426_3_ - this.posY, p_180426_5_ - this.posZ);
        BlockPos blockpos1 = this.hangingPosition.add(blockpos);
        this.setPosition((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
    }

    public static enum EnumArt
    {
        GENTLE_GIANTS("Gentle Giants", 16, 16, 0, 0);

        /** Painting Title. */
        public final String title;
        public final int sizeX;
        public final int sizeY;
        public final int offsetX;
        public final int offsetY;

        private EnumArt(String title, int sizeX, int sizeY, int offsetX, int offsetY)
        {
            this.title = title;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        int index = 0;

        EnumArt[] artValues = EnumArt.values();

        for (int i = 0; i < artValues.length; i++)
        {
            if (artValues[i].equals(art))
            {
                index = i;
                break;
            }
        }

        buffer.writeInt(index);

        index = 0;

        EnumFacing[] facingValues = EnumFacing.values();

        for (int i = 0; i < facingValues.length; i++)
        {
            if (facingValues[i].equals(field_174860_b))
            {
                index = i;
                break;
            }
        }

        buffer.writeInt(index);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        art = EnumArt.values()[additionalData.readInt()];
        field_174860_b = EnumFacing.values()[additionalData.readInt()];
    }
}