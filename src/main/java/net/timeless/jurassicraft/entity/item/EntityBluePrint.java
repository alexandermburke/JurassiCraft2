package net.timeless.jurassicraft.entity.item;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.ArrayList;

public class EntityBluePrint extends EntityHanging
{
    public EnumBluePrint art;
    private static final String __OBFID = "CL_00001556";

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
            this.art = (EntityBluePrint.EnumBluePrint)arraylist.get(this.rand.nextInt(arraylist.size()));
        }

        this.func_174859_a(enumFacing);
    }

    @SideOnly(Side.CLIENT)
    public EntityBluePrint(World world, BlockPos pos, EnumFacing enumFacing, String titleName)
    {
        this(world, pos, enumFacing);
        EntityBluePrint.EnumBluePrint[] aenumart = EntityBluePrint.EnumBluePrint.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j)
        {
            EntityBluePrint.EnumBluePrint enumart = aenumart[j];

            if (enumart.title.equals(titleName))
            {
                this.art = enumart;
                break;
            }
        }

        this.func_174859_a(enumFacing);
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
        EntityBluePrint.EnumBluePrint[] aenumart = EntityBluePrint.EnumBluePrint.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j)
        {
            EntityBluePrint.EnumBluePrint enumart = aenumart[j];

            if (this.art.title.equals(s))
            {
                this.art = enumart;
            }
        }

        if (this.art == null)
        {
            this.art = EnumBluePrint.Tyrannosaur;
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
                EntityPlayer entityplayer = (EntityPlayer)p_110128_1_;

                if (entityplayer.capabilities.isCreativeMode)
                {
                    return;
                }
            }

            this.entityDropItem(new ItemStack(Items.painting), 0.0F);
        }
    }

    /**
     * Sets the location and Yaw/Pitch of an entity in the world
     */
    public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch)
    {
        BlockPos blockpos = new BlockPos(x - this.posX, y - this.posY, z - this.posZ);
        BlockPos blockpos1 = this.hangingPosition.add(blockpos);
        this.setPosition((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
    }

    public enum EnumBluePrint
    {
        Tyrannosaur("Savage", 32, 16, 0, 0);

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
        BlockPos blockpos = new BlockPos(p_180426_1_ - this.posX, p_180426_3_ - this.posY, p_180426_5_ - this.posZ);
        BlockPos blockpos1 = this.hangingPosition.add(blockpos);
        this.setPosition((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
    }
}