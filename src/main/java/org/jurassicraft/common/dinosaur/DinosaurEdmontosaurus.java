package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurEdmontosaurus extends Dinosaur
{
    public DinosaurEdmontosaurus()
    {
        super();

        this.setName("Edmontosaurus");
        this.setTimePeriod(EnumTimePeriod.CRETACEOUS);
        this.setEggColorMale(0xB97840, 0x644329);
        this.setEggColorFemale(0x8F8039, 0x615A30);
        this.setHealth(10, 40);
        this.setStrength(5, 20);
        this.setSpeed(0.46, 0.41);
        this.setMaximumAge(fromDays(50));
        this.setEyeHeight(0.55F, 3.45F);
        this.setSizeX(0.5F, 2.8F);
        this.setSizeY(0.8F, 4.25F);
        this.setStorage(45);
        this.setDiet(EnumDiet.HERBIVORE);
        this.setBones("cheek_teeth");
    }
}
