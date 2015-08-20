package net.timeless.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.timeless.jurassicraft.common.container.slot.SlotStorage;
import net.timeless.jurassicraft.common.tileentity.TileDNAHybridizer;
import net.timeless.jurassicraft.common.tileentity.TileDnaSynthesizer;

public class ContainerDNAHybridizer extends Container
{
    private TileDNAHybridizer dnaHybridizer;

    public ContainerDNAHybridizer(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.dnaHybridizer = (TileDNAHybridizer) tileEntity;
        this.addSlotToContainer(new SlotStorage(dnaHybridizer, 0, 65, 18, true));
        this.addSlotToContainer(new SlotStorage(dnaHybridizer, 1, 97, 18, true));
        this.addSlotToContainer(new SlotStorage(dnaHybridizer, 2, 37, 41, true));
        this.addSlotToContainer(new SlotStorage(dnaHybridizer, 3, 125, 41, true));
        this.addSlotToContainer(new SlotStorage(dnaHybridizer, 4, 58, 57, true));
        this.addSlotToContainer(new SlotStorage(dnaHybridizer, 5, 104, 57, true));
        this.addSlotToContainer(new SlotStorage(dnaHybridizer, 6, 81, 64, true));

        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        if (!player.worldObj.isRemote)
            dnaHybridizer.closeInventory(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return dnaHybridizer.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
