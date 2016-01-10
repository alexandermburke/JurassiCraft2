package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityChasmosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurChasmosaurus extends Dinosaur
{
    public DinosaurChasmosaurus()
    {
        super();

        this.setName("Chasmosaurus");
        this.setDinosaurClass(EntityChasmosaurus.class);
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0xB6B293, 0x85563E);
        this.setEggColorFemale(0xB9B597, 0x323232);
        this.setHealth(20, 40);
        this.setSpeed(0.37, 0.35);
        this.setStrength(5, 15);
        this.setMaximumAge(fromDays(40));
        this.setEyeHeight(0.3F, 1.35F);
        this.setSizeX(0.35F, 1.75F);
        this.setSizeY(0.45F, 2.35F);
        this.setStorage(27);
        this.setDiet(EnumDiet.HERBIVORE);
        this.setBones("skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "hind_leg_bones", "front_leg_bones");
    }
}
