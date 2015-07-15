package net.timeless.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.timeless.jurassicraft.common.tileentity.TileFossilGrinder;

public class ContainerFossilGrinder extends Container
{

    private TileFossilGrinder fossilGrinder;

    public ContainerFossilGrinder(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.fossilGrinder = (TileFossilGrinder) tileEntity;
        this.addSlotToContainer(new Slot(fossilGrinder, 0, 29, 29));

        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                this.addSlotToContainer(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 106 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 164));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        if (!player.worldObj.isRemote)
            fossilGrinder.closeInventory(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return fossilGrinder.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
