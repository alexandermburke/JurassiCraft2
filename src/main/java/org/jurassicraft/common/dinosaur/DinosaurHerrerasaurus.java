package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityHerrerasaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurHerrerasaurus extends Dinosaur
{
    public DinosaurHerrerasaurus()
    {
        super();

        this.setName("Herrerasaurus");
        this.setDinosaurClass(EntityHerrerasaurus.class);
        this.setTimePeriod(EnumTimePeriod.TRIASSIC);
        this.setEggColorMale(0x2B1919, 0x932C23);
        this.setEggColorFemale(0x7C6F44, 0x2B2721);
        this.setHealth(16, 65);
        this.setSpeed(0.45, 0.40);
        this.setStrength(6, 36);
        this.setMaximumAge(fromDays(45));
        this.setEyeHeight(0.45F, 2.25F);
        this.setSizeX(0.4F, 1.8F);
        this.setSizeY(0.55F, 2.55F);
        this.setStorage(36);
        this.setDiet(EnumDiet.CARNIVORE);
        this.setBones("skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "leg_bones", "foot_bones", "arm_bones");
    }
}
