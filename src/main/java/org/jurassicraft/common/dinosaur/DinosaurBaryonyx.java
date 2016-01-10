package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityBaryonyx;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurBaryonyx extends Dinosaur
{
    public DinosaurBaryonyx()
    {
        super();

        this.setName("Baryonyx");
        this.setDinosaurClass(EntityBaryonyx.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0x567F4F, 0x13270F);
        this.setEggColorFemale(0x9D9442, 0x2A2405);
        this.setHealth(5, 30);
        this.setSpeed(0.45, 0.40);
        this.setStrength(1, 10);
        this.setMaximumAge(fromDays(55));
        this.setEyeHeight(0.55F, 2.95F);
        this.setSizeX(0.35F, 1.5F);
        this.setSizeY(0.55F, 2.95F);
        this.setStorage(36);
        this.setDiet(EnumDiet.CARNIVORE);
        this.setBones("skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "leg_vertebrae", "leg_bones", "claw", "arm_bones");
    }
}
