package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityBrachiosaurus;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurBrachiosaurus extends Dinosaur
{
    public DinosaurBrachiosaurus()
    {
        super();

        this.setName("Brachiosaurus");
        this.setDinosaurClass(EntityBrachiosaurus.class);
        this.setTimePeriod(EnumTimePeriod.JURASSIC);
        this.setEggColorMale(0x87987F, 0x607343);
        this.setEggColorFemale(0xAA987D, 0x4F4538);
        this.setHealth(20, 100);
        this.setSpeed(0.32, 0.25);
        this.setStrength(10, 20);
        this.setMaximumAge(fromDays(85));
        this.setEyeHeight(2.2F, 18.4F);
        this.setSizeX(0.9F, 6.5F);
        this.setSizeY(1.5F, 10.8F);
        this.setStorage(54);
        this.setDiet(EnumDiet.HERBIVORE);
        this.setBones("skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "hind_leg_bones", "front_leg_bones");
    }
}
