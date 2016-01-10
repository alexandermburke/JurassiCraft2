package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityGiganotosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurGiganotosaurus extends Dinosaur
{
    public DinosaurGiganotosaurus()
    {
        super();

        this.setName("Giganotosaurus");
        this.setDinosaurClass(EntityGiganotosaurus.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0x4F3F33, 0x4F3F33);
        this.setEggColorFemale(0x756E54, 0x4B474A);
        this.setHealth(20, 95);
        this.setSpeed(0.52, 0.40);
        this.setStrength(6, 36);
        this.setEyeHeight(0.6F, 4.8F);
        this.setSizeX(0.5F, 4.0F);
        this.setSizeY(0.8F, 5.8F);
        this.setStorage(54);
        this.setDiet(EnumDiet.CARNIVORE);
        this.setBones("skull", "tooth");
    }
}
