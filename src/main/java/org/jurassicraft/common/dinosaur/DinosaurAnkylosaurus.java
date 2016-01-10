package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityAnkylosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurAnkylosaurus extends Dinosaur
{
    public DinosaurAnkylosaurus()
    {
        super();

        this.setName("Ankylosaurus");
        this.setDinosaurClass(EntityAnkylosaurus.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0xAB9B82, 0x7C6270);
        this.setEggColorFemale(0x554E45, 0x3F3935);
        this.setHealth(20, 120);
        this.setSpeed(0.52, 0.40);
        this.setStrength(5, 10);
        this.setMaximumAge(fromDays(50));
        this.setEyeHeight(0.4F, 2.0F);
        this.setSizeX(0.8F, 3.0F);
        this.setSizeY(0.6F, 3.0F);
        this.setStorage(27);
        this.setDiet(EnumDiet.HERBIVORE);
        this.setBones("skull", "tooth", "tail_vertebrae", "tail_club", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "hind_leg_bones", "front_leg_bones", "armor_plating");
    }
}
