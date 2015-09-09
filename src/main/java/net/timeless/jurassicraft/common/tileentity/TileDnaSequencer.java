package net.timeless.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.container.ContainerDNASequencer;
import net.timeless.jurassicraft.common.genetics.DinoDNA;
import net.timeless.jurassicraft.common.genetics.GeneticsHelper;
import net.timeless.jurassicraft.common.item.ItemSoftTissue;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

import java.util.Random;

public class TileDnaSequencer extends TileMachineBase
{
    private int[] inputs = new int[]{0, 1, 2, 3, 4, 5};
    private int[] outputs = new int[]{6, 7, 8};

    private ItemStack[] slots = new ItemStack[9];

    @Override
    protected int getProcess(int slot)
    {
        return (int) Math.floor(slot / 2);
    }

    @Override
    protected boolean canProcess(int process)
    {
        int tissue = process * 2;

        ItemStack input = slots[tissue];
        ItemStack storage = slots[tissue + 1];

        if (input != null && input.getItem() instanceof ItemSoftTissue)
        {
            if (storage != null && storage.getItem() == JCItemRegistry.storage_disc)
            {
                ItemStack output = new ItemStack(JCItemRegistry.storage_disc, 1, input.getItemDamage());
                output.setTagCompound(input.getTagCompound());

                if (slots[process + 6] == null || ItemStack.areItemsEqual(slots[process + 6], output) && ItemStack.areItemStackTagsEqual(slots[process + 6], output))
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
            Random rand = new Random();

            int tissue = process * 2;

//            EntityPlayer player = worldObj.getPlayerEntityByUUID(UUID.fromString(slots[1].getTagCompound().getString("LastOwner")));

            int quality = rand.nextInt(25) + 1;

            if (rand.nextDouble() < 0.10)
            {
                quality += 25;

                if (rand.nextDouble() < 0.10)
                {
                    quality += 25;

                    if (rand.nextDouble() < 0.10)
                    {
                        quality += 25;
                    }
                }
            }

            NBTTagCompound nbt = slots[tissue + 1].getTagCompound();

            if (nbt == null)
            {
                nbt = new NBTTagCompound();
            }

            DinoDNA dna = new DinoDNA(quality, GeneticsHelper.randomGenetics(rand, slots[tissue].getItemDamage(), quality).toString());
            dna.writeToNBT(nbt);

            ItemStack output = new ItemStack(JCItemRegistry.storage_disc, 1, slots[tissue].getItemDamage());
            output.setItemDamage(dna.getContainer().getDinosaur());
            output.setTagCompound(nbt);

//            JCPlayerData.getPlayerData(player).addSequencedDNA(new DinoDNA(quality, GeneticsHelper.randomGenetics(rand, slots[0].getItemDamage(), quality).toString()));

            mergeStack(process + 6, output);

            decreaseStackSize(tissue);
            decreaseStackSize(tissue + 1);
        }
    }

    @Override
    protected int getMainInput(int process)
    {
        return process * 2;
    }

    @Override
    protected int getMainOutput(int process)
    {
        return (process * 2) + 1;
    }

    @Override
    protected int getStackProcessTime(ItemStack stack)
    {
        return 2000;
    }

    @Override
    protected int getProcessCount()
    {
        return 3;
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
        return new ContainerDNASequencer(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.modid + ":dna_sequencer";
    }

    public String getName()
    {
        return hasCustomName() ? customName : "container.dna_sequencer";
    }

    public String getCommandSenderName() //Forge Version compatibility, keep both getName and getCommandSenderName
    {
        return getName();
    }
}