package org.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.container.slot.SlotStorage;
import org.jurassicraft.common.item.itemblock.ItemEncasedFossil;
import org.jurassicraft.common.tileentity.TileCleaningStation;

public class ContainerDNACombinator extends Container
{
    private final IInventory tileCombinator;
    private int field_178152_f;
    private int field_178153_g;
    private int field_178154_h;
    private int field_178155_i;

    public ContainerDNACombinator(InventoryPlayer invPlayer, IInventory combinator)
    {
        this.tileCombinator = combinator;
        this.addSlotToContainer(new SlotStorage(combinator, 0, 55, 13, true));
        this.addSlotToContainer(new SlotStorage(combinator, 1, 105, 13, true));
        this.addSlotToContainer(new SlotStorage(combinator, 2, 81, 60, true));

        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
        }
    }

    /**
     * Add the given Listener to the list of Listeners. Method name is for legacy.
     */
    public void onCraftGuiOpened(ICrafting listener)
    {
        super.onCraftGuiOpened(listener);
        listener.sendAllWindowProperties(this, this.tileCombinator);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (ICrafting icrafting : this.crafters)
        {
            if (this.field_178152_f != this.tileCombinator.getField(2))
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileCombinator.getField(2));
            }

            if (this.field_178154_h != this.tileCombinator.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileCombinator.getField(0));
            }

            if (this.field_178155_i != this.tileCombinator.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileCombinator.getField(1));
            }

            if (this.field_178153_g != this.tileCombinator.getField(3))
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileCombinator.getField(3));
            }
        }

        this.field_178152_f = this.tileCombinator.getField(2);
        this.field_178154_h = this.tileCombinator.getField(0);
        this.field_178155_i = this.tileCombinator.getField(1);
        this.field_178153_g = this.tileCombinator.getField(3);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileCombinator.setField(id, data);
    }

    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileCombinator.isUseableByPlayer(playerIn);
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack transferFrom = slot.getStack();
            itemstack = transferFrom.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(transferFrom, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(transferFrom, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (transferFrom.getItem() instanceof ItemEncasedFossil)
                {
                    if (!this.mergeItemStack(transferFrom, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileCleaningStation.isItemFuel(transferFrom))
                {
                    if (!this.mergeItemStack(transferFrom, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(transferFrom, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(transferFrom, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(transferFrom, 3, 39, false))
            {
                return null;
            }

            if (transferFrom.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (transferFrom.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, transferFrom);
        }

        return itemstack;
    }
}
