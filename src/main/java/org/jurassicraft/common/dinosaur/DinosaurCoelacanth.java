package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCoelacanth;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCoelacanth extends Dinosaur
{
    public DinosaurCoelacanth()
    {
        super();

        this.setName("Coelacanth");
        this.setDinosaurClass(EntityCoelacanth.class);
        this.setTimePeriod(EnumTimePeriod.DEVONIAN);
        this.setEggColorMale(0x3C4B65, 0x737E96);
        this.setEggColorFemale(0x4C4A3A, 0x7C775E);
        this.setHealth(5, 10);
        this.setStrength(1, 5);
        this.setSpeed(0.62, 0.50);
        this.setMaximumAge(fromDays(30));
        this.setEyeHeight(0.2F, 0.6F);
        this.setSizeX(0.3F, 1.2F);
        this.setSizeY(0.3F, 1.0F);
        this.setMarineAnimal(true);
        this.setStorage(18);
        this.setDiet(EnumDiet.PISCIVORE);
        this.setBones("skull", "teeth", "spine", "second_dorsal_fin", "pelvic_fin_bones", "pectoral_fin_bones", "first_dorsal_fin", "caudal_fin", "anal_fin");
    }
}
