package net.timeless.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.container.ContainerDNACombinator;
import net.timeless.jurassicraft.common.genetics.DinoDNA;
import net.timeless.jurassicraft.common.genetics.PlantDNA;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class TileDNACombinator extends TileMachineBase
{
    private int[] inputs = new int[]{ 0, 1 };
    private int[] outputs = new int[]{ 2 };

    private ItemStack[] slots = new ItemStack[3];

    @Override
    protected int getProcess(int slot)
    {
        return 0;
    }

    @Override
    protected boolean canProcess(int process)
    {
        if (slots[0] != null && slots[0].getItem() == JCItemRegistry.storage_disc && slots[1] != null && slots[1].getItem() == JCItemRegistry.storage_disc)
        {
            if (slots[0].getTagCompound() != null && slots[1].getTagCompound() != null && slots[2] == null && slots[0].getItemDamage() == slots[1].getItemDamage() && slots[0].getTagCompound().getString("StorageId").equals(slots[1].getTagCompound().getString("StorageId")))
            {
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
            ItemStack output = new ItemStack(JCItemRegistry.storage_disc, 1, slots[0].getItemDamage());

            String storageId = slots[0].getTagCompound().getString("StorageId");

            if(storageId.equals("DinoDNA"))
            {
                DinoDNA dna1 = DinoDNA.readFromNBT(slots[0].getTagCompound());
                DinoDNA dna2 = DinoDNA.readFromNBT(slots[1].getTagCompound());

                int newQuality = dna1.getDNAQuality() + dna2.getDNAQuality();

                if (newQuality > 100)
                {
                    newQuality = 100;
                }

                DinoDNA newDNA = new DinoDNA(newQuality, dna1.toString());

                NBTTagCompound outputTag = new NBTTagCompound();
                newDNA.writeToNBT(outputTag);
                output.setTagCompound(outputTag);
            }
            else if(storageId.equals("PlantDNA"))
            {
                PlantDNA dna1 = PlantDNA.readFromNBT(slots[0].getTagCompound());
                PlantDNA dna2 = PlantDNA.readFromNBT(slots[1].getTagCompound());

                int newQuality = dna1.getDNAQuality() + dna2.getDNAQuality();

                if (newQuality > 100)
                {
                    newQuality = 100;
                }

                PlantDNA newDNA = new PlantDNA(dna1.getPlant(), newQuality);

                NBTTagCompound outputTag = new NBTTagCompound();
                newDNA.writeToNBT(outputTag);
                output.setTagCompound(outputTag);
            }

            mergeStack(2, output);

            decreaseStackSize(0);
            decreaseStackSize(1);
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
        return 2;
    }

    @Override
    protected int getStackProcessTime(ItemStack stack)
    {
        return 400;
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
        return new ContainerDNACombinator(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.modid + ":dna_combinator";
    }

    @Override
    public String getName()
    {
        return hasCustomName() ? customName : "container.dna_combinator";
    }
}