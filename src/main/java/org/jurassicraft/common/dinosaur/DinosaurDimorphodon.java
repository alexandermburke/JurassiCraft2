package org.jurassicraft.common.dinosaur;

import org.jurassicraft.common.entity.EntityDimorphodon;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurDimorphodon extends Dinosaur
{
    public DinosaurDimorphodon()
    {
        super();

        this.setName("Dimorphodon");
        this.setDinosaurClass(EntityDimorphodon.class);
        this.setTimePeriod(EnumTimePeriod.JURASSIC);
        this.setEggColorMale(0xB2AC94, 0x636644);
        this.setEggColorFemale(0xBDB4A9, 0x726B57);
        this.setHealth(5, 10);
        this.setSpeed(0.35, 0.30);
        this.setStrength(1, 5);
        this.setMaximumAge(fromDays(35));
        this.setEyeHeight(0.25F, 0.7F);
        this.setSizeX(0.25F, 0.5F);
        this.setSizeY(0.25F, 0.75F);
        this.setStorage(9);
        this.setDiet(EnumDiet.CARNIVORE);
        this.setBones("skull", "teeth", "leg_bones", "neck", "ribs_and_spine", "shoulder_blade", "tail_vertebrae", "wing_bones");
    }
}
