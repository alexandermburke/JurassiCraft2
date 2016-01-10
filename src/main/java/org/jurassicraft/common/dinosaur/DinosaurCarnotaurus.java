package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCarnotaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCarnotaurus extends Dinosaur
{
    public DinosaurCarnotaurus()
    {
        super();

        this.setName("Carnotaurus");
        this.setDinosaurClass(EntityCarnotaurus.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0xA2996E, 0x545338);
        this.setEggColorFemale(0x9C8E6A, 0x635639);
        this.setHealth(10, 30);
        this.setSpeed(0.42, 0.30);
        this.setStrength(5, 20);
        this.setMaximumAge(fromDays(45));
        this.setEyeHeight(0.4F, 2.4F);
        this.setSizeX(0.45F, 2.25F);
        this.setSizeY(0.6F, 2.8F);
        this.setStorage(36);
        this.setDiet(EnumDiet.CARNIVORE);
        this.setSleepingSchedule(EnumSleepingSchedule.CREPUSCULAR);
        this.setBones("skull", "tooth");
    }
}
