package org.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.api.IHybrid;
import org.jurassicraft.common.container.ContainerDNAHybridizer;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.genetics.DinoDNA;
import org.jurassicraft.common.genetics.GeneticsContainer;
import org.jurassicraft.common.item.ItemStorageDisc;
import org.jurassicraft.common.item.JCItemRegistry;

public class TileDNAHybridizer extends TileMachineBase
{
    private int[] inputs = new int[] { 0, 1, 2, 3, 4, 5 };
    private int[] outputs = new int[] { 6 };

    private ItemStack[] slots = new ItemStack[7];

    @Override
    protected int getProcess(int slot)
    {
        return 0;
    }

    private Dinosaur getHybrid()
    {
        return getHybrid(slots[0], slots[1], slots[2], slots[3], slots[4], slots[5]);
    }

    private Dinosaur getHybrid(ItemStack baseDino1Disc, ItemStack baseDino2Disc, ItemStack extraDNA1Disc, ItemStack extraDNA2Disc, ItemStack extraDNA3Disc, ItemStack extraDNA4Disc)
    {
        Dinosaur hybrid = null;

        Dinosaur baseGene1 = getDino(baseDino1Disc);
        Dinosaur baseGene2 = getDino(baseDino2Disc);

        Dinosaur extraGene1 = getDino(extraDNA1Disc);
        Dinosaur extraGene2 = getDino(extraDNA2Disc);
        Dinosaur extraGene3 = getDino(extraDNA3Disc);
        Dinosaur extraGene4 = getDino(extraDNA4Disc);

        Dinosaur[] baseGenes = new Dinosaur[] { baseGene1, baseGene2 };

        Dinosaur[] extraGenes = new Dinosaur[] { extraGene1, extraGene2, extraGene3, extraGene4 };

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            if (dino instanceof IHybrid)
            {
                IHybrid dinoHybrid = (IHybrid) dino;

                int baseCount = 0;

                for (Class combo : dinoHybrid.getBaseGenes())
                {
                    if (combo.isInstance(baseGene1) || combo.isInstance(baseGene2))
                    {
                        baseCount++;
                    }
                }

                int count = 0;
                boolean extra = false;

                for (Dinosaur combo : extraGenes)
                {
                    Class match = null;

                    for (Class clazz : dinoHybrid.getExtraGenes())
                    {
                        if (clazz.isInstance(combo))
                        {
                            match = clazz;
                        }
                    }

                    if (match != null && match.isInstance(combo))
                    {
                        count++;
                    }
                    else if (combo != null)
                    {
                        extra = true;
                    }
                }

                boolean hasBases = baseCount == dinoHybrid.getBaseGenes().length;
                boolean hasExtras = !extra && count == dinoHybrid.getExtraGenes().length;

                if (hasBases && hasExtras)
                {
                    hybrid = dino;

                    break;
                }
            }
        }

        return hybrid;
    }

    private Dinosaur getDino(ItemStack disc)
    {
        if (disc != null)
        {
            DinoDNA data = DinoDNA.readFromNBT(disc.getTagCompound());

            return data.getDNAQuality() == 100 ? JCEntityRegistry.getDinosaurById(data.getContainer().getDinosaur()) : null;
        }
        else
        {
            return null;
        }
    }

    @Override
    protected boolean canProcess(int process)
    {
        if (this.slots[0] != null && this.slots[0].getItem() instanceof ItemStorageDisc && this.slots[1] != null && this.slots[1].getItem() instanceof ItemStorageDisc)
        {
            return getHybrid() != null;
        }

        return false;
    }

    @Override
    protected void processItem(int process)
    {
        if (this.canProcess(process))
        {
            Dinosaur hybrid = getHybrid();

            NBTTagCompound nbt = new NBTTagCompound();

            int dinosaurId = JCEntityRegistry.getDinosaurId(hybrid);

            GeneticsContainer container = new GeneticsContainer(slots[0].getTagCompound().getString("Genetics"));
            container.set(GeneticsContainer.DINOSAUR, dinosaurId);

            DinoDNA dna = new DinoDNA(100, container.toString());
            dna.writeToNBT(nbt);

            ItemStack output = new ItemStack(JCItemRegistry.storage_disc, 1, dinosaurId);
            output.setItemDamage(dna.getContainer().getDinosaur());
            output.setTagCompound(nbt);

            mergeStack(6, output);

            for (int i = 0; i < 6; i++)
            {
                decreaseStackSize(i);
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
        return 6;
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
        return new ContainerDNAHybridizer(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.MODID + ":dna_hybridizer";
    }

    public String getName()
    {
        return hasCustomName() ? customName : "container.dna_hybridizer";
    }

    public String getCommandSenderName() // Forge Version compatibility, keep both getName and getCommandSenderName
    {
        return getName();
    }
}
