package net.reuxertz.ecoapi.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryHelper
{

    //Static Helpers
    public static int nextMatchingInventoryStackIndex(ItemStack stack, IInventory inv, int startIndex)
    {
        for (int i = startIndex; i < inv.getSizeInventory(); i++)
        {
            ItemStack slot = inv.getStackInSlot(i);

            //If both match null
            if (stack == null && slot == null)
                return i;

            //If only one null, since previous case occurred
            if (stack == null || slot == null)
                continue;

            //Handle Matching Items
            Item i1 = slot.getItem(), i2 = stack.getItem();
            if (Item.getIdFromItem(i1) == Item.getIdFromItem(i2) && slot.getItemDamage() == stack.getItemDamage())
                return i;
        }

        return -1;
    }

    public static int nextMatchingInventoryStackIndex(Class itemClass, int meta, IInventory inv, int startIndex)
    {
        if (inv == null)
            return -1;

        for (int i = startIndex; i < inv.getSizeInventory(); i++)
        {
            ItemStack slot = inv.getStackInSlot(i);

            //If both match null
            if (itemClass == null && slot == null)
                return i;

            //If only one null, since previous case occurred
            if (itemClass == null || slot == null)
                continue;

            //Handle Matching Items
            if (itemClass.isInstance(slot.getItem()) && (meta == -1 || meta == slot.getItemDamage()))
                return i;
        }

        return -1;
    }

    public static int nextMatchingInventoryStacksIndex(ItemStack[] stacks, IInventory inv, int startIndex)
    {
        for (int i = startIndex; i < inv.getSizeInventory(); i++)
        {
            ItemStack slot = inv.getStackInSlot(i);

            for (int j = 0; j < stacks.length; j++)
            {
                ItemStack stack = stacks[j];

                //If both match null
                if (stack == null && slot == null)
                    return i;

                //If only one null, since previous case occurred
                if (stack == null || slot == null)
                    continue;

                //Handle Matching Items
                Item i1 = slot.getItem(), i2 = stack.getItem();
                if (Item.getIdFromItem(i1) == Item.getIdFromItem(i2))
                    return i;
            }
        }

        return -1;
    }

    public static ItemStack nextMatchingInventoryStack(Class itemClass, int meta, IInventory inv)
    {
        int i = InventoryHelper.nextMatchingInventoryStackIndex(itemClass, meta, inv, 0);

        if (i == -1)
            return null;

        return inv.getStackInSlot(i);
    }

    public static boolean hasInventorySpaceFor(ItemStack stack, IInventory inv)
    {
        int index = InventoryHelper.nextMatchingInventoryStackIndex(stack, inv, 0);
        int remSize = stack.stackSize;

        while (index != -1)
        {
            int spaceAv = inv.getStackInSlot(index).getMaxStackSize() - inv.getStackInSlot(index).stackSize;

            if (spaceAv >= remSize)
                return true;

            if (spaceAv < remSize)
                remSize = remSize - spaceAv;

            index = InventoryHelper.nextMatchingInventoryStackIndex(stack, inv, index + 1);
        }

        index = InventoryHelper.nextMatchingInventoryStackIndex(null, inv, 0);

        while (index != -1)
        {
            int spaceAv = stack.getMaxStackSize();

            if (spaceAv >= remSize)
                return true;

            if (spaceAv < remSize)
                remSize = remSize - spaceAv;

            index = InventoryHelper.nextMatchingInventoryStackIndex(null, inv, index + 1);
        }

        return false;
    }

    public static boolean containsInventory(ItemStack stack, IInventory inv, int count)
    {
        int index = InventoryHelper.nextMatchingInventoryStackIndex(stack, inv, 0);
        int remSize = count;

        while (index != -1)
        {
            int curAmt = inv.getStackInSlot(index).stackSize;

            if (curAmt >= remSize)
                return true;

            if (curAmt < remSize)
                remSize = remSize - curAmt;

            index = InventoryHelper.nextMatchingInventoryStackIndex(stack, inv, index + 1);
        }

        return remSize <= 0;

    }

    public static boolean containsInventory(ItemStack stack, IInventory inv)
    {
        return InventoryHelper.containsInventory(stack, inv, stack.stackSize);
    }

    public static boolean addToInventory(ItemStack stack, IInventory inv)
    {
        int index = InventoryHelper.nextMatchingInventoryStackIndex(stack, inv, 0);
        int remSize = stack.stackSize;

        while (index != -1)
        {
            int spaceAv = inv.getStackInSlot(index).getMaxStackSize() - inv.getStackInSlot(index).stackSize;

            if (spaceAv >= remSize)
            {
                inv.getStackInSlot(index).stackSize += remSize;
                return true;
            }

            if (spaceAv < remSize)
            {
                remSize = remSize - spaceAv;
                inv.getStackInSlot(index).stackSize += spaceAv;
            }

            index = InventoryHelper.nextMatchingInventoryStackIndex(stack, inv, index + 1);
        }

        index = InventoryHelper.nextMatchingInventoryStackIndex(null, inv, 0);

        while (index != -1)
        {
            int spaceAv = stack.getMaxStackSize();

            if (spaceAv >= remSize)
            {
                ItemStack newStack = ItemStack.copyItemStack(stack);
                newStack.stackSize = remSize;
                inv.setInventorySlotContents(index, newStack);
                return true;
            }

            if (spaceAv < remSize)
            {
                remSize = remSize - spaceAv;
                ItemStack newStack = ItemStack.copyItemStack(stack);
                newStack.stackSize = spaceAv;
                inv.setInventorySlotContents(index, newStack);

            }

            index = InventoryHelper.nextMatchingInventoryStackIndex(null, inv, index + 1);
        }

        stack.stackSize = remSize;
        return false;


    }

    public static boolean removeFromInventory(ItemStack stack, IInventory inv)
    {
        int index = InventoryHelper.nextMatchingInventoryStackIndex(stack, inv, 0);
        List<Integer> indexes = new ArrayList<Integer>();
        int remSize = stack.stackSize;

        while (index != -1)
        {
            int curAmt = inv.getStackInSlot(index).stackSize;

            if (curAmt > remSize)
            {
                remSize = 0;
                indexes.add(index);
                break;
            }
            if (curAmt == remSize)
            {
                remSize -= inv.getStackInSlot(index).stackSize;
                indexes.add(index);
                break;
            }

            if (curAmt < remSize)
            {
                remSize -= curAmt;
                indexes.add(index);
            }

            index = InventoryHelper.nextMatchingInventoryStackIndex(stack, inv, index + 1);
        }

        if (remSize > 0)
            return false;

        remSize = stack.stackSize;
        for (int i = 0; i < indexes.size(); i++)
        {
            int curAmt = inv.getStackInSlot(indexes.get(i)).stackSize;

            if (curAmt > remSize)
            {
                inv.getStackInSlot(indexes.get(i)).stackSize -= remSize;
                remSize = 0;
                return true;
            }
            if (curAmt == remSize)
            {
                remSize -= inv.getStackInSlot(indexes.get(i)).stackSize;
                inv.setInventorySlotContents(indexes.get(i), null);
                return true;
            }

            if (curAmt < remSize)
            {
                remSize -= curAmt;
                inv.setInventorySlotContents(indexes.get(i), null);
            }
        }

        return remSize <= 0;

    }

    public static int totalInventoryContained(ItemStack stack, IInventory inv)
    {
        boolean cont = true;
        int count = 0;
        int i = 0;
        while (cont)
        {
            i = InventoryHelper.nextMatchingInventoryStackIndex(stack, inv, i);

            if (i == -1)
                break;

            if (stack != null)
                count += inv.getStackInSlot(i).stackSize;

            i++;
        }

        return count;
    }

    public static int totalEmptySlots(IInventory inv)
    {
        int sum = 0;
        for (int i = 0; i < inv.getSizeInventory(); i++)
            if (inv.getStackInSlot(i) == null)
                sum++;

        return sum;
    }

}
