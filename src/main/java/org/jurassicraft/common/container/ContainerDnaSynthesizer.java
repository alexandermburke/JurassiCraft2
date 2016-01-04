package org.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.jurassicraft.common.container.slot.SlotCustom;
import org.jurassicraft.common.container.slot.SlotStorage;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.tileentity.TileDnaSynthesizer;

public class ContainerDnaSynthesizer extends Container
{
    private final TileDnaSynthesizer dnaSynthesizer;

    public ContainerDnaSynthesizer(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.dnaSynthesizer = (TileDnaSynthesizer) tileEntity;
        this.addSlotToContainer(new SlotStorage(dnaSynthesizer, 0, 38, 22, true));
        this.addSlotToContainer(new SlotCustom(dnaSynthesizer, 1, 24, 49, JCItemRegistry.empty_test_tube));
        this.addSlotToContainer(new SlotCustom(dnaSynthesizer, 2, 50, 49, JCItemRegistry.dna_base));

        int i;

        for (i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                this.addSlotToContainer(new Slot(dnaSynthesizer, i + (j * 2) + 3, i * 18 + 119, j * 18 + 26));
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
        {
            dnaSynthesizer.closeInventory(player);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return dnaSynthesizer.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
