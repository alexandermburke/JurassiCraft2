package net.timeless.jurassicraft.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.block.BlockCage;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.tileentity.TileCage;

public class ItemCage extends ItemBlock
{
    public ItemCage(BlockCage cage, String size)
    {
        super(cage);
        this.setUnlocalizedName("cage_" + size);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    /**
     * Called when a Block is right-clicked with this Item
     *
     * @param pos The block being right-clicked
     * @param side The side being right-clicked
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        pos = pos.offset(side);

        if(playerIn.canPlayerEdit(pos, side, stack))
        {
            worldIn.setBlockState(pos, block.getDefaultState());

            TileCage cage = (TileCage) worldIn.getTileEntity(pos);

            cage.setEntity(getEntity(playerIn, stack));
        }

        return true;
    }

    public Entity getEntity(EntityPlayer player, ItemStack stack)
    {
        NBTTagCompound nbt = getNBT(stack);

        int entityID = nbt.getInteger("EntityID");

        if(entityID != -1)
        {
            NBTTagCompound entityTag = nbt.getCompoundTag("Entity");

            Entity entity = EntityList.createEntityByID(entityID, player.worldObj);
            entity.readFromNBT(entityTag);

            return entity;
        }

        return null;
    }

    public NBTTagCompound getNBT(ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound();

        if(tag == null)
        {
            tag = new NBTTagCompound();
        }

        stack.setTagCompound(tag);

        return tag;
    }
}
