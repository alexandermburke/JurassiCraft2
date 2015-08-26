package net.timeless.jurassicraft.common.vehicles.helicopter;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Base entity for the helicopter, also holds the {@link EntityHelicopterSeat Seat} entities and updates/handles them.
 */
public class EntityHelicopterBase extends Entity
{
    public EntityHelicopterBase(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected void entityInit()
    {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund)
    {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound)
    {

    }
}
