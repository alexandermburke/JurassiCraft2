package net.timeless.jurassicraft.common.dna;

import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;

import java.util.Random;

public class GeneticsHelper
{
    public static GeneticsContainer randomGenetics(Random rand, Dinosaur dinosaur, int quality)
    {
        return randomGenetics(rand, JCEntityRegistry.getDinosaurId(dinosaur), quality);
    }

    public static GeneticsContainer randomGenetics(Random rand, int dinosaur, int quality)
    {
        return new GeneticsContainer(dinosaur, rand.nextBoolean(), rand(rand, 4, quality), rand(rand, 4, quality), rand(rand, 4, quality), rand(rand, 4, quality));
    }

    private static int rand(Random rand, int range, int quality)
    {
        return rand.nextInt(range) + ((100 - quality) / 10);
    }
}
