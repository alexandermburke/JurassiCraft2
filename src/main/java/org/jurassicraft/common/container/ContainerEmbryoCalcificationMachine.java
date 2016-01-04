package org.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.jurassicraft.common.container.slot.SlotCustom;
import org.jurassicraft.common.container.slot.SlotSyringe;
import org.jurassicraft.common.tileentity.TileEmbryoCalcificationMachine;

public class ContainerEmbryoCalcificationMachine extends Container
{
    private final TileEmbryoCalcificationMachine calcificationMachine;

    public ContainerEmbryoCalcificationMachine(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.calcificationMachine = (TileEmbryoCalcificationMachine) tileEntity;

        this.addSlotToContainer(new SlotSyringe(calcificationMachine, 34, 14));
        this.addSlotToContainer(new SlotCustom(calcificationMachine, 1, 34, 50, Items.egg));

        this.addSlotToContainer(new Slot(calcificationMachine, 2, 97, 32));

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
        {
            calcificationMachine.closeInventory(player);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return calcificationMachine.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
