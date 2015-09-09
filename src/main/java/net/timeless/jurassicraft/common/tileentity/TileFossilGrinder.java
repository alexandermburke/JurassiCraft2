package net.timeless.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.container.ContainerFossilGrinder;
import net.timeless.jurassicraft.common.item.ItemFossil;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

import java.util.Random;

public class TileFossilGrinder extends TileMachineBase
{
    private int[] inputs = new int[]{0};
    private int[] outputs = new int[]{1, 2, 3, 4, 5, 6};

    private ItemStack[] slots = new ItemStack[7];

    @Override
    protected int getProcess(int slot)
    {
        return 0;
    }

    @Override
    protected boolean canProcess(int process)
    {
        if (slots[0] != null && this.slots[0].getItem() instanceof ItemFossil)
        {
            for (int i = 1; i < 7; i++)
            {
                if (slots[i] == null)
                    return true;
            }
        }

        return false;
    }

    @Override
    protected void processItem(int process)
    {
        if (this.canProcess(process))
        {
            ItemStack output;

            Random rand = new Random();

            int item = rand.nextInt(6);

            if (item < 3)
                output = new ItemStack(Items.dye, 1, 15);
            else if (item < 5)
                output = new ItemStack(Items.flint);
            else
                output = new ItemStack(JCItemRegistry.soft_tissue, 1, slots[0].getItemDamage());

            int emptySlot = getOutputSlot(output);

            if (emptySlot != -1)
            {
                mergeStack(emptySlot, output);
                decreaseStackSize(0);
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
        return new ContainerFossilGrinder(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.modid + ":fossil_grinder";
    }

    public String getName()
    {
        return hasCustomName() ? customName : "container.fossil_grinder";
    }

    public String getCommandSenderName() //Forge Version compatibility, keep both getName and getCommandSenderName
    {
        return getName();
    }
}