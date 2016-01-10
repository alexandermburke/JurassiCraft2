package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityDilophosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurDilophosaurus extends Dinosaur
{
    public DinosaurDilophosaurus()
    {
        super();

        this.setName("Dilophosaurus");
        this.setDinosaurClass(EntityDilophosaurus.class);
        this.setTimePeriod(EnumTimePeriod.JURASSIC);
        this.setEggColorMale(0x62702B, 0x26292A);
        this.setEggColorFemale(0x5E6E2B, 0x2D221A);
        this.setHealth(10, 30);
        this.setSpeed(0.42, 0.40);
        this.setStrength(5, 20);
        this.setMaximumAge(fromDays(40));
        this.setEyeHeight(0.45F, 2.15F);
        this.setSizeX(0.3F, 1.25F);
        this.setSizeY(0.5F, 2.5F);
        this.setStorage(27);
        this.setDiet(EnumDiet.CARNIVORE);
        this.setSleepingSchedule(EnumSleepingSchedule.CREPUSCULAR);
        this.setBones("skull", "tooth", "arm_bones", "leg_bones", "neck", "pelvis", "ribcage", "shoulder", "tail_vertebrae");
    }
}
