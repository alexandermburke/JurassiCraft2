package net.timeless.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.timeless.jurassicraft.common.container.slot.SlotDNAHolder;
import net.timeless.jurassicraft.common.container.slot.SlotStorage;
import net.timeless.jurassicraft.common.tileentity.TileDnaSequencer;

public class ContainerDNASequencer extends Container
{
    private TileDnaSequencer dnaSequencer;

    public ContainerDNASequencer(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.dnaSequencer = (TileDnaSequencer) tileEntity;

        this.addSlotToContainer(new SlotDNAHolder(dnaSequencer, 0, 53, 35));
        this.addSlotToContainer(new SlotStorage(dnaSequencer, 1, 100, 35));

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
