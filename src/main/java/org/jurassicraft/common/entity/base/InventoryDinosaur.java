package org.jurassicraft.common.entity.base;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import org.jurassicraft.common.item.JCItemRegistry;

import java.util.Random;

public class InventoryDinosaur implements IInventory
{
    private EntityDinosaur entity;

    private ItemStack[] inventory;

    public InventoryDinosaur(EntityDinosaur entity)
    {
        this.entity = entity;
        this.inventory = new ItemStack[entity.getDinosaur().getStorage()];
    }

    public boolean checkForTracker()
    {
        boolean hasTracker = false;

        for (int i = 0; i < getSizeInventory(); i++)
        {
            ItemStack stack = getStackInSlot(i);

            if (stack != null && stack.getItem() == JCItemRegistry.tracker)
            {
                hasTracker = true;
            }
        }

        entity.setHasTracker(hasTracker);

        return hasTracker;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < inventory.length; ++i)
        {
            if (inventory[i] != null)
            {
                NBTTagCompound slotTag = new NBTTagCompound();
                slotTag.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(slotTag);
                nbttaglist.appendTag(slotTag);
            }
        }

        nbt.setTag("Items", nbttaglist);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        inventory = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound slotTag = nbttaglist.getCompoundTagAt(i);
            int j = slotTag.getByte("Slot") & 255;

            if (j >= 0 && j < inventory.length)
            {
                setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(slotTag));
            }
        }
    }


    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if (inventory[index] != null)
        {
            ItemStack itemstack;

            if (inventory[index].stackSize <= count)
            {
                itemstack = inventory[index];
                setInventorySlotContents(index, null);
                return itemstack;
            }
            else
            {
                itemstack = inventory[index].splitStack(count);

                if (inventory[index].stackSize == 0)
                {
                    setInventorySlotContents(index, null);
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (inventory[index] != null)
        {
            ItemStack itemstack = inventory[index];
            setInventorySlotContents(index, null);
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        inventory[index] = stack;

        checkForTracker();

        if (stack != null && stack.stackSize > getInventoryStackLimit())
        {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void markDirty()
    {
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return !entity.isDead && player.getDistanceSqToEntity(entity) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return true;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < getSizeInventory(); i++)
        {
            setInventorySlotContents(i, null);
        }
    }

    @Override
    public String getName()
    {
        return entity.getName();
    }

    @Override
    public boolean hasCustomName()
    {
        return entity.hasCustomName();
    }

    @Override
    public IChatComponent getDisplayName()
    {
        return entity.getDisplayName();
    }

    public void dropItems(World worldObj, Random rand)
    {
        for (int i = 0; i < getSizeInventory(); ++i)
        {
            ItemStack itemstack = getStackInSlot(i);

            if (itemstack != null)
            {
                float offsetX = rand.nextFloat() * 0.8F + 0.1F;
                float offsetY = rand.nextFloat() * 0.8F + 0.1F;
                float offsetZ = rand.nextFloat() * 0.8F + 0.1F;

                while (itemstack.stackSize > 0)
                {
                    int j = rand.nextInt(21) + 10;

                    if (j > itemstack.stackSize)
                    {
                        j = itemstack.stackSize;
                    }

                    itemstack.stackSize -= j;
                    EntityItem itemEntity = new EntityItem(worldObj, entity.posX + offsetX, entity.posY + offsetY, entity.posZ + offsetZ, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
                    float multiplier = 0.05F;
                    itemEntity.motionX = (float) rand.nextGaussian() * multiplier;
                    itemEntity.motionY = (float) rand.nextGaussian() * multiplier + 0.2F;
                    itemEntity.motionZ = (float) rand.nextGaussian() * multiplier;
                    worldObj.spawnEntityInWorld(itemEntity);
                }
            }
        }
    }
}
