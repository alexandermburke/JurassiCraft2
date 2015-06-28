package net.timeless.jurassicraft.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.timeless.jurassicraft.container.slot.SlotFossil;
import net.timeless.jurassicraft.tileentity.TileCleaningStation;

public class ContainerCleaningStation extends Container
{

    private TileCleaningStation cleaningStation;

    public ContainerCleaningStation(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.cleaningStation = (TileCleaningStation) tileEntity;
        this.addSlotToContainer(new Slot(cleaningStation, 0, 29, 29));

        this.addSlotToContainer(new SlotFossil(cleaningStation, 1, 29, 29));
        this.addSlotToContainer(new SlotFossil(cleaningStation, 2, 39, 29));
        this.addSlotToContainer(new SlotFossil(cleaningStation, 3, 49, 29));
        this.addSlotToContainer(new SlotFossil(cleaningStation, 4, 59, 29));
        this.addSlotToContainer(new SlotFossil(cleaningStation, 5, 69, 29));
        this.addSlotToContainer(new SlotFossil(cleaningStation, 6, 79, 29));

        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 9; k++)
                this.addSlotToContainer(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 106 + i * 18));

        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 164));
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        if (!player.worldObj.isRemote)
            cleaningStation.closeInventory(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return cleaningStation.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
