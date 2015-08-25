package net.timeless.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.container.ContainerEmbryonicMachine;
import net.timeless.jurassicraft.common.item.ItemDNA;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class TileEmbryonicMachine extends TileMachineBase
{
    private int[] inputs = new int[]{0, 1, 2};
    private int[] outputs = new int[]{3, 4, 5, 6};

    private ItemStack[] slots = new ItemStack[7];

    @Override
    protected int getProcess(int slot)
    {
        return 0;
    }

    @Override
    protected boolean canProcess(int process)
    {
        if (slots[0] != null && slots[0].getItem() instanceof ItemDNA && slots[1] != null && slots[1].getItem() == JCItemRegistry.petri_dish && slots[2] != null && slots[2].getItem() == JCItemRegistry.empty_syringe)
        {
            ItemStack output = new ItemStack(JCItemRegistry.syringe, 1, slots[0].getItemDamage());
            output.setTagCompound(slots[0].getTagCompound());

            return hasOutputSlot(output);
        }

        return false;
    }

    @Override
    protected void processItem(int process)
    {
        if (this.canProcess(process))
        {
            ItemStack output = new ItemStack(JCItemRegistry.syringe, 1, slots[0].getItemDamage());
            output.setTagCompound(slots[0].getTagCompound());

            int emptySlot = getOutputSlot(output);

            if (emptySlot != -1)
            {
                mergeStack(emptySlot, output);

                decreaseStackSize(0);
                decreaseStackSize(1);
                decreaseStackSize(2);
            }
        }
    }

    @Override
    protected int getMainInput(int process)
    {
        return 0;
    }

    @Override
    protected int getMainOutput(int process)
    {
        return 1;
    }

    @Override
    protected int getStackProcessTime(ItemStack stack)
    {
        return 200;
    }

    @Override
    protected int getProcessCount()
    {
        return 1;
    }

    @Override
    protected int[] getInputs()
    {
        return inputs;
    }

    @Override
    protected int[] getOutputs()
    {
        return outputs;
    }

    @Override
    protected ItemStack[] getSlots()
    {
        return slots;
    }

    @Override
    protected void setSlots(ItemStack[] slots)
    {
        this.slots = slots;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerEmbryonicMachine(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.modid + ":embryonic_machine";
    }

    @Override
    public String getName()
    {
        return hasCustomName() ? customName : "container.embryonic_machine";
    }
}