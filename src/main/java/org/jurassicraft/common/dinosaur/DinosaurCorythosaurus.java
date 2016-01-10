package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCorythosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCorythosaurus extends Dinosaur
{
    public DinosaurCorythosaurus()
    {
        super();

        this.setName("Corythosaurus");
        this.setDinosaurClass(EntityCorythosaurus.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0xBAA87E, 0x5E7201);
        this.setEggColorFemale(0xB3A27D, 0xE9BF47);
        this.setHealth(10, 30);
        this.setSpeed(0.46, 0.41);
        this.setStrength(5, 15);
        this.setMaximumAge(fromDays(40));
        this.setEyeHeight(0.35F, 1.9F);
        this.setSizeX(0.4F, 1.8F);
        this.setSizeY(0.6F, 2.8F);
        this.setStorage(36);
        this.setDiet(EnumDiet.HERBIVORE);
        this.setBones("skull", "cheek_teeth", "front_leg_bones", "hind_leg_bones", "neck_vertebrae", "pelvis", "ribcage", "shoulder", "tail_vertebrae");
    }
}
