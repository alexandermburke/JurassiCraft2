package org.jurassicraft.common.api;

import org.jurassicraft.common.dinosaur.Dinosaur;

public interface IHybrid
{
    Class<? extends Dinosaur>[] getBaseGenes();

    Class<? extends Dinosaur>[] getExtraGenes();
}
