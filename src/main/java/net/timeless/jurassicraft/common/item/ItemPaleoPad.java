package net.timeless.jurassicraft.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.handler.JCGuiHandler;

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
                player.addChatMessage(new ChatComponentText("Days Existed: " + dino.getDaysExisted())); //TODO view entity (size / screensize)
            }

            return true;
        }

        return false;
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
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
        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
        }

        nbt.setString(key, value);

        stack.setTagCompound(nbt);
    }
}
