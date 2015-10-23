package org.jurassicraft.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.data.JCPlayerData;
import org.jurassicraft.common.handler.JCGuiHandler;

public class ItemPaleoPad extends Item
{
    public ItemPaleoPad()
    {
        super();

        this.setUnlocalizedName("paleo_pad");
        this.setMaxStackSize(1);

        this.setCreativeTab(JCCreativeTabs.items);
    }

    /**
     * Called when a Block is right-clicked with this Item
     *
     * @param pos
     *            The block being right-clicked
     * @param side
     *            The side being right-clicked
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.getBlockState(pos).getBlock() == JCBlockRegistry.security_camera)
        {
            JCPlayerData data = JCPlayerData.getPlayerData(player);
            data.addCamera(pos);

            return true;
        }

        return false;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        JCGuiHandler.openPaleoPad(player);

        return stack;
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target)
    {
        if (target instanceof EntityDinosaur)
        {
            EntityDinosaur dino = (EntityDinosaur) target;

            if (player.worldObj.isRemote)
            {
                player.addChatMessage(new ChatComponentText("Days Existed: " + dino.getDaysExisted())); // TODO view entity (size / screensize)
            }

            return true;
        }

        return false;
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and update it's contents.
     */
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (entityIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entityIn;

            setString(stack, "LastOwner", player.getUniqueID().toString());
        }
    }

    public void setString(ItemStack stack, String key, String value)
    {
        NBTTagCompound nbt = getNBT(stack);

        nbt.setString(key, value);

        stack.setTagCompound(nbt);
    }

    private NBTTagCompound getNBT(ItemStack stack)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
        }

        return nbt;
    }
}
