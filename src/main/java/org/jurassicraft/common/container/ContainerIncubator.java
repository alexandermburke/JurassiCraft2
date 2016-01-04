package org.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.jurassicraft.common.container.slot.SlotCustom;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.tileentity.TileIncubator;

public class ContainerIncubator extends Container
{
    private TileIncubator incubator;

    public ContainerIncubator(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.incubator = (TileIncubator) tileEntity;
        this.addSlotToContainer(new SlotCustom(incubator, 0, 33, 28, JCItemRegistry.egg));
        this.addSlotToContainer(new SlotCustom(incubator, 1, 56, 21, JCItemRegistry.egg));
        this.addSlotToContainer(new SlotCustom(incubator, 2, 79, 14, JCItemRegistry.egg));
        this.addSlotToContainer(new SlotCustom(incubator, 3, 102, 21, JCItemRegistry.egg));
        this.addSlotToContainer(new SlotCustom(incubator, 4, 125, 28, JCItemRegistry.egg));

        this.addSlotToContainer(new Slot(incubator, 5, 79, 49));

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
        {
            incubator.closeInventory(player);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return incubator.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
