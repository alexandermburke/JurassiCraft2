package org.jurassicraft.common.entity.base;

import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;

public abstract class EntityDinosaurAggressive extends EntityDinosaur implements IMob
{
    protected EntityDinosaurAggressive(World world)
    {
        super(world);
    }
}
