package net.timeless.jurassicraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemPaleoPad extends Item
{
    public ItemPaleoPad()
    {
        super();

        this.setUnlocalizedName("paleo_pad");
        this.setMaxStackSize(1);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        String name = StatCollector.translateToLocal("item.paleo_pad.name");
        
        NBTTagCompound tagCompound = stack.getTagCompound();
        
        if(tagCompound != null)
        {
            if(tagCompound.getString("owner") != null && tagCompound.getString("owner") != "")
            {
                name = tagCompound.getString("username") + StatCollector.translateToLocal("misc.ownership.name") + " " + name;
            }
        }
        
        return name;
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        ItemStack registeredPaleoPad = null;

        for (ItemStack item : player.inventory.mainInventory)
        {
            if(item != null)
            {
                NBTTagCompound tagCompound = item.getTagCompound();

                if(item.getItem() instanceof ItemPaleoPad && tagCompound != null)
                {
                    if(tagCompound.getString("owner") != null && tagCompound.getString("owner") != "")
                    {
                        registeredPaleoPad = item;

                        break;
                    }
                }
            }
        }

        if(registeredPaleoPad != null)
        {
            NBTTagCompound nbt = registeredPaleoPad.getTagCompound();

            if(player.isSneaking())
            {
                if((nbt.getString("owner") != null && nbt.getString("owner") != "") || nbt.getString("owner").equals(player.getUniqueID().toString()))
                {
                    if(stack.getTagCompound() != null && nbt.getString("owner").equals(stack.getTagCompound().getString("owner")))
                    {
                        if(world.isRemote)
                            player.addChatMessage(new ChatComponentText("This is already your Paleo Pad!"));
                    }
                    else
                    {
                        registerToPlayer(stack, player);
                        
                        nbt.setString("owner", "");
                        nbt.setString("username", "");
                        
                        if(world.isRemote)
                            player.addChatMessage(new ChatComponentText("Transferring data to new paleo pad... "));
                    }
                }
                else
                {
                    if(world.isRemote)
                        player.addChatMessage(new ChatComponentText("This is not your Paleo Pad!"));
                }
            }
            else
            {
                if(nbt.getString("owner").equals(player.getUniqueID().toString()))
                    nbt.setString("username", player.getName());

                registeredPaleoPad.setTagCompound(nbt);

                if(world.isRemote)
                    player.addChatMessage(new ChatComponentText("There is already a paleo pad registered to: " + nbt.getString("username")));
            }
        }
        else
        {
            if(world.isRemote)
                player.addChatMessage(new ChatComponentText("Registering Paleo Pad to you!"));

            //TODO GUI, Translation, boolean determining whether you have registered (set to false on item entity death)
            registerToPlayer(stack, player);
        }

        return stack;
    }

    private void registerToPlayer(ItemStack stack, EntityPlayer player)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        if(nbt == null)
            nbt = new NBTTagCompound();

        nbt.setString("owner", player.getUniqueID().toString());
        nbt.setString("username", player.getName());

        stack.setTagCompound(nbt);
    }
}
