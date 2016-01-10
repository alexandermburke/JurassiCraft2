package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityCompsognathus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurCompsognathus extends Dinosaur
{
    public DinosaurCompsognathus()
    {
        super();

        this.setName("Compsognathus");
        this.setDinosaurClass(EntityCompsognathus.class);
        this.setTimePeriod(EnumTimePeriod.JURASSIC);
        this.setEggColorMale(0x7B8042, 0x454B3B);
        this.setEggColorFemale(0x7D734A, 0x484A3D);
        this.setHealth(2, 5);
        this.setSpeed(0.3, 0.2);
        this.setStrength(1, 3);
        this.setMaximumAge(fromDays(20));
        this.setEyeHeight(0.2F, 0.5F);
        this.setSizeX(0.1F, 0.3F);
        this.setSizeY(0.25F, 0.55F);
        this.setStorage(9);
        this.setDiet(EnumDiet.CARNIVORE);
        this.setBones("skull", "teeth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "leg_bones", "arm_bones");
    }
}
