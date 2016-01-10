package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityGallimimus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurGallimimus extends Dinosaur
{
    public DinosaurGallimimus()
    {
        super();

        this.setName("Gallimimus");
        this.setDinosaurClass(EntityGallimimus.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0xC57B5F, 0x985E54);
        this.setEggColorFemale(0xDAC0AC, 0x966943);
        this.setHealth(16, 65);
        this.setSpeed(0.52, 0.40);
        this.setStrength(6, 36);
        this.setMaximumAge(fromDays(35));
        this.setEyeHeight(0.58F, 3F);
        this.setSizeX(0.3F, 1.5F);
        this.setSizeY(0.65F, 3.25F);
        this.setStorage(27);
        this.setDiet(EnumDiet.HERBIVORE);
        this.setBones("skull", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "leg_bones", "foot_bones", "arm_bones");
    }
}
