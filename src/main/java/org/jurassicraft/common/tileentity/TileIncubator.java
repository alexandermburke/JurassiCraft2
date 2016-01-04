package org.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.container.ContainerIncubator;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.entity.item.EntityCageSmall;
import org.jurassicraft.common.item.ItemDinosaurEgg;

import java.util.List;

public class TileIncubator extends TileMachineBase
{
    private int[] inputs = new int[] { 0, 1, 2, 3, 4 };
    private int[] other = new int[5];
    private int[] outputs = new int[0];
    private int[] temperature = new int[5];

    private ItemStack[] slots = new ItemStack[6];

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        for (int i = 0; i < getProcessCount(); i++)
        {
            temperature[i] = compound.getShort("Temperature" + i);
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        for (int i = 0; i < getProcessCount(); i++)
        {
            compound.setShort("Temperature" + i, (short) this.temperature[i]);
        }
    }

    @Override
    protected int getProcess(int slot)
    {
        return slot;
    }

    @Override
    protected boolean canProcess(int process)
    {
        return slots[process] != null && slots[process].stackSize > 0 && slots[process].getItem() instanceof ItemDinosaurEgg;
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? getInputs() : other;
    }

    @Override
    protected void processItem(int process)
    {
        if (this.canProcess(process) && !worldObj.isRemote)
        {
            ItemStack egg = slots[process];

            Dinosaur dinoInEgg = JCEntityRegistry.getDinosaurById(egg.getMetadata());

            if (dinoInEgg != null)
            {
                Class<? extends EntityDinosaur> dinoClass = dinoInEgg.getDinosaurClass();

                try
                {
                    EntityDinosaur dino = dinoClass.getConstructor(World.class).newInstance(worldObj);

                    dino.setDNAQuality(egg.getTagCompound().getInteger("DNAQuality"));
                    dino.setGenetics(egg.getTagCompound().getString("Genetics"));

                    dino.setMale(temperature[process] > 50);
                    dino.setAge(0);

                    int blockX = pos.getX();
                    int blockY = pos.getY();
                    int blockZ = pos.getZ();

                    List<EntityCageSmall> cages = worldObj.getEntitiesWithinAABB(EntityCageSmall.class, AxisAlignedBB.fromBounds(blockX - 2, blockY, blockZ - 2, blockX + 2, blockY + 1, blockZ + 2));

                    EntityCageSmall cage = null;

                    for (EntityCageSmall cCage : cages)
                    {
                        if (cCage.getEntity() == null)
                        {
                            cage = cCage;
                            break;
                        }
                    }

                    if (cage != null)
                    {
                        cage.setEntity(dino);
                    }
                    else
                    {
                        // TODO find valid spawn area
                        dino.setLocationAndAngles(blockX + 2, blockY + 0.5, blockZ + 2, MathHelper.wrapAngleTo180_float(worldObj.rand.nextFloat() * 360.0F), 0.0F);
                        dino.rotationYawHead = dino.rotationYaw;
                        dino.renderYawOffset = dino.rotationYaw;

                        worldObj.spawnEntityInWorld(dino);
                    }

                    decreaseStackSize(process);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected int getMainInput(int process)
    {
        return process;
    }

    @Override
    protected int getMainOutput(int process)
    {
        return 0;
    }

    @Override
    protected int getStackProcessTime(ItemStack stack)
    {
        return 8000;
    }

    @Override
    protected int getProcessCount()
    {
        return 5;
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
        return new ContainerIncubator(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return JurassiCraft.MODID + ":incubator";
    }

    public String getName()
    {
        return hasCustomName() ? customName : "container.incubator";
    }

    public int getField(int id)
    {
        if (id < 5)
        {
            return processTime[id];
        }
        else if (id < 10)
        {
            return totalProcessTime[id - 5];
        }
        else if (id < 15)
        {
            return temperature[id - 10];
        }

        return 0;
    }

    public void setField(int id, int value)
    {
        if (id < 5)
        {
            processTime[id] = value;
        }
        else if (id < 10)
        {
            totalProcessTime[id - 5] = value;
        }
        else if (id < 15)
        {
            temperature[id - 10] = value;
        }
    }

    public String getCommandSenderName() // Forge Version compatibility, keep both getName and getCommandSenderName
    {
        return getName();
    }
}
