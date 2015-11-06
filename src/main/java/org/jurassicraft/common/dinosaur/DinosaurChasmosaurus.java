package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityChasmosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurChasmosaurus extends Dinosaur
{
    @Override
    public String getName()
    {
        return "Chasmosaurus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityChasmosaurus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xB6B293;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x85563E;
    }

    @Override
    public double getBabyHealth()
    {
        return 15;
    }

    @Override
    public double getAdultHealth()
    {
        return 55;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.37;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.35;
    }

    @Override
    public double getBabyStrength()
    {
        return 6;
    }

    @Override
    public double getAdultStrength()
    {
        return 36;
    }

    @Override
    public double getBabyKnockback()
    {
        return 0.3;
    }

    @Override
    public double getAdultKnockback()
    {
        return 0.6;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(40);
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.3F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 1.35F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.35F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.45F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 1.75F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 2.35F;
    }

    @Override
    public int getStorage()
    {
        return 27;
    }
}
