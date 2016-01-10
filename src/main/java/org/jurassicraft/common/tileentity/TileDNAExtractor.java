package org.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.container.ContainerDNAExtractor;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.genetics.DinoDNA;
import org.jurassicraft.common.genetics.GeneticsHelper;
import org.jurassicraft.common.genetics.PlantDNA;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.plant.JCPlantRegistry;
import org.jurassicraft.common.plant.Plant;

import java.util.List;
import java.util.Random;

public class TileDNAExtractor extends TileMachineBase
{
    private int[] inputs = new int[] { 0, 1 };
    private int[] outputs = new int[] { 2, 3, 4, 5 };

    private ItemStack[] slots = new ItemStack[6];

    @Override
    protected int getProcess(int slot)
    {
        return 0;
    }

    @Override
    protected boolean canProcess(int process)
    {
        ItemStack extraction = slots[0];
        ItemStack storage = slots[1];

        if (storage != null && storage.getItem() == JCItemRegistry.storage_disc && extraction != null && (extraction.getItem() == JCItemRegistry.amber || extraction.getItem() == JCItemRegistry.sea_lamprey || extraction.getItem() == JCItemRegistry.dino_meat) && (storage.getTagCompound() == null || !storage.getTagCompound().hasKey("Genetics")))
        {
            for (int i = 2; i < 6; i++)
            {
                if (slots[i] == null)
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected void processItem(int process)
    {
        if (this.canProcess(process))
        {
            Random rand = worldObj.rand;
            ItemStack input = slots[0];

            ItemStack disc = null;

            Item item = input.getItem();

            if (item == JCItemRegistry.amber || item == JCItemRegistry.sea_lamprey)
            {
                if (input.getItemDamage() == 0)
                {
                    List<Dinosaur> possibleDinos = item == JCItemRegistry.amber ? JCEntityRegistry.getDinosaursFromAmber() : JCEntityRegistry.getDinosaursFromSeaLampreys();

                    Dinosaur dino = possibleDinos.get(rand.nextInt(possibleDinos.size()));

                    int dinosaurId = JCEntityRegistry.getDinosaurId(dino);

                    disc = new ItemStack(JCItemRegistry.storage_disc, 1, dinosaurId);

                    int quality = rand.nextInt(50);

                    if (rand.nextDouble() < 0.1)
                    {
                        quality += 50;
                    }

                    DinoDNA dna = new DinoDNA(quality, GeneticsHelper.randomGenetics(rand, dinosaurId, quality).toString());

                    NBTTagCompound nbt = new NBTTagCompound();
                    dna.writeToNBT(nbt);

                    disc.setTagCompound(nbt);
                }
                else if (input.getItemDamage() == 1)
                {
                    List<Plant> possiblePlants = JCPlantRegistry.getPlants();
                    Plant plant = possiblePlants.get(rand.nextInt(possiblePlants.size()));

                    int plantId = JCPlantRegistry.getPlantId(plant);

                    disc = new ItemStack(JCItemRegistry.storage_disc, 1, plantId);

                    int quality = rand.nextInt(50);

                    if (rand.nextDouble() < 0.1)
                    {
                        quality += 50;
                    }

                    PlantDNA dna = new PlantDNA(plantId, quality);

                    NBTTagCompound nbt = new NBTTagCompound();
                    dna.writeToNBT(nbt);

                    disc.setTagCompound(nbt);
                }
            }
            else if (item == JCItemRegistry.dino_meat)
            {
                disc = new ItemStack(JCItemRegistry.storage_disc, 1, input.getItemDamage());

                disc.setTagCompound(input.getTagCompound());
            }

            int empty = getOutputSlot(disc);

            mergeStack(empty, disc);

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
        return 2000;
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
        return new ContainerDNAExtractor(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.MODID + ":dna_extractor";
    }

    public String getName()
    {
        return hasCustomName() ? customName : "container.dna_extractor";
    }
}
