package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCearadactylus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCearadactylus extends Dinosaur
{
    public DinosaurCearadactylus()
    {
        super();

        this.setName("Cearadactylus");
        this.setDinosaurClass(EntityCearadactylus.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0x64A0B3, 0x3B3937);
        this.setEggColorFemale(0xB55252, 0x4C423A);
        this.setHealth(10, 20);
        this.setSpeed(0.46, 0.30);
        this.setStrength(1, 10);
        this.setMaximumAge(fromDays(50));
        this.setEyeHeight(0.45F, 1.45F);
        this.setSizeX(0.35F, 1.5F);
        this.setSizeY(0.45F, 1.55F);
        this.setStorage(27);
        this.setDiet(EnumDiet.CARNIVORE);
        this.setBones("skull", "teeth", "wing_bones", "tail_vertebrae", "leg_bones", "pelvis");
    }
}
