package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityDodo;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurDodo extends Dinosaur
{
    public DinosaurDodo()
    {
        super();

        this.setName("Dodo");
        this.setDinosaurClass(EntityDodo.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0xA2996E, 0x545338);
        this.setEggColorFemale(0x908B80, 0x665C51);
        this.setHealth(5, 15);
        this.setSpeed(0.35, 0.30);
        this.setStrength(1, 5);
        this.setMaximumAge(fromDays(20));
        this.setEyeHeight(0.35F, 0.95F);
        this.setSizeX(0.25F, 0.5F);
        this.setSizeY(0.35F, 0.95F);
        this.setStorage(9);
        this.setDiet(EnumDiet.HERBIVORE);
        this.setBones("skull");
    }
}
