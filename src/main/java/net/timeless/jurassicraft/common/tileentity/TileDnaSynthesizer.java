package net.timeless.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.container.ContainerDnaSynthesizer;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class TileDnaSynthesizer extends TileMachineBase
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
        ItemStack storage = slots[0];
        ItemStack testTube = slots[1];
        ItemStack baseMaterial = slots[2];

        if (storage != null && storage.getItem() == JCItemRegistry.storage_disc && testTube != null && testTube.getItem() == JCItemRegistry.empty_test_tube && baseMaterial != null && baseMaterial.getItem() == JCItemRegistry.dna_base && (storage.getTagCompound() != null && storage.getTagCompound().hasKey("DNAQuality")))
        {
            ItemStack output = null;

            if (storage.getTagCompound().getInteger("DNAQuality") == 100)
            {
                if (storage.getTagCompound().getString("StorageId") == "DinoDNA")
                {
                    output = new ItemStack(JCItemRegistry.dna, 1, storage.getItemDamage());
                    output.setTagCompound(storage.getTagCompound());
                }
                else
                {
                    output = new ItemStack(JCItemRegistry.plant_dna, 1, storage.getItemDamage());
                    output.setTagCompound(storage.getTagCompound());
                }
            }

            return output != null && hasOutputSlot(output);
        }

        return false;
    }

    @Override
    protected void processItem(int process)
    {
        if (this.canProcess(process))
        {
            ItemStack output = new ItemStack(slots[0].getTagCompound().getString("StorageId") == "DinoDNA" ? JCItemRegistry.dna : JCItemRegistry.plant_dna, 1, slots[0].getItemDamage());
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
        return 1000;
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
        return new ContainerDnaSynthesizer(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.modid + ":dna_synthesizer";
    }

    public String getName()
    {
        return hasCustomName() ? customName : "container.dna_synthesizer";
    }

    public String getCommandSenderName() //Forge Version compatibility, keep both getName and getCommandSenderName
    {
        return getName();
    }
}