package net.timeless.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.timeless.jurassicraft.common.container.slot.SlotCustom;
import net.timeless.jurassicraft.common.container.slot.SlotStorage;
import net.timeless.jurassicraft.common.item.JCItemRegistry;
import net.timeless.jurassicraft.common.tileentity.TileDNAExtractor;

public class ContainerDNAExtractor extends Container
{
    private TileDNAExtractor extractor;

    public ContainerDNAExtractor(InventoryPlayer playerInventory, TileEntity tileEntity)
    {
        this.extractor = (TileDNAExtractor) tileEntity;
        this.addSlotToContainer(new SlotStorage(extractor, 1, 55, 47, false));
        this.addSlotToContainer(new SlotCustom(extractor, 0, 55, 26, JCItemRegistry.amber));
        this.addSlotToContainer(new SlotStorage(extractor, 2, 108, 28, true));
        this.addSlotToContainer(new SlotStorage(extractor, 3, 126, 28, true));
        this.addSlotToContainer(new SlotStorage(extractor, 4, 108, 46, true));
        this.addSlotToContainer(new SlotStorage(extractor, 5, 126, 46, true));

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
            extractor.closeInventory(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return extractor.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        return null;
    }
}
