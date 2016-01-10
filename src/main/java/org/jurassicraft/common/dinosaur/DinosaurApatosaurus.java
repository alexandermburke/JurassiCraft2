package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityApatosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurApatosaurus extends Dinosaur
{
    public DinosaurApatosaurus()
    {
        super();

        this.setName("Apatosaurus");
        this.setDinosaurClass(EntityApatosaurus.class);
        this.setTimePeriod(EnumTimePeriod.JURASSIC);
        this.setEggColorMale(0xA79F93, 0x987664);
        this.setEggColorFemale(0x7E7D70, 0x30343E);
        this.setHealth(10, 60);
        this.setStrength(5, 20);
        this.setSpeed(0.32, 0.25);
        this.setMaximumAge(fromDays(80));
        this.setEyeHeight(0.9F, 6.8F);
        this.setSizeX(0.9F, 6.5F);
        this.setSizeY(1.0F, 6.8F);
        this.setStorage(54);
        this.setDiet(EnumDiet.HERBIVORE);
        this.setBones("skull", "tooth");
    }
}
