package org.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.jurassicraft.common.container.slot.SlotDNAHolder;
import org.jurassicraft.common.container.slot.SlotStorage;
import org.jurassicraft.common.tileentity.TileDnaSequencer;

public class ContainerDNASequencer extends Container
{
    private TileDnaSequencer dnaSequencer;

    public ContainerDNASequencer(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.dnaSequencer = (TileDnaSequencer) tileEntity;

        this.addSlotToContainer(new SlotDNAHolder(dnaSequencer, 0, 44, 16));
        this.addSlotToContainer(new SlotStorage(dnaSequencer, 1, 66, 16, false));
        this.addSlotToContainer(new SlotDNAHolder(dnaSequencer, 2, 44, 36));
        this.addSlotToContainer(new SlotStorage(dnaSequencer, 3, 66, 36, false));
        this.addSlotToContainer(new SlotDNAHolder(dnaSequencer, 4, 44, 56));
        this.addSlotToContainer(new SlotStorage(dnaSequencer, 5, 66, 56, false));

        this.addSlotToContainer(new SlotStorage(dnaSequencer, 6, 113, 16, true));
        this.addSlotToContainer(new SlotStorage(dnaSequencer, 7, 113, 36, true));
        this.addSlotToContainer(new SlotStorage(dnaSequencer, 8, 113, 56, true));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);

        if (!player.worldObj.isRemote)
            dnaSequencer.closeInventory(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return dnaSequencer.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
