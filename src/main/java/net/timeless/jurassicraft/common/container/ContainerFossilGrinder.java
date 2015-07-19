package net.timeless.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.timeless.jurassicraft.common.container.slot.SlotFossil;
import net.timeless.jurassicraft.common.tileentity.TileFossilGrinder;

public class ContainerFossilGrinder extends Container
{
    private TileFossilGrinder fossilGrinder;

    public ContainerFossilGrinder(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.fossilGrinder = (TileFossilGrinder) tileEntity;
        this.addSlotToContainer(new SlotFossil(fossilGrinder, 0, 50, 35));

        int i;

        for (i = 0; i < 3; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                this.addSlotToContainer(new Slot(fossilGrinder, i + (j * 3) + 1, i * 18 + 93 + 15, j * 18 + 26));
            }
        }

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
