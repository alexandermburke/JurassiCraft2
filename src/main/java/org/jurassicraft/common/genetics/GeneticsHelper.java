package org.jurassicraft.common.genetics;

import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

import java.util.Random;

public class GeneticsHelper
{
    public static GeneticsContainer randomGenetics(Random rand, Dinosaur dinosaur, int quality)
    {
        return randomGenetics(rand, JCEntityRegistry.getDinosaurId(dinosaur), quality);
    }

    public static GeneticsContainer randomGenetics(Random rand, int dinosaur, int quality)
    {
        return new GeneticsContainer(dinosaur, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), getRandomOverlayTexture(rand, dinosaur), getRandomOverlayTexture(rand, dinosaur), getRandomOverlayTexture(rand, dinosaur)); //TODO
    }

    private static int getRandomOverlayTexture(Random rand, int dinosaurId)
    {
        Dinosaur dinosaur = JCEntityRegistry.getDinosaurById(dinosaurId);

        int overlayCount = dinosaur.getOverlayCount();

        if (rand.nextBoolean() && overlayCount > 0)
        {
            return rand.nextInt(overlayCount);
        }

        return 255;
    }

    private static int rand(Random rand, int range, int quality)
    {
        return rand.nextInt(range) + ((100 - quality) / 10);
    }
}
