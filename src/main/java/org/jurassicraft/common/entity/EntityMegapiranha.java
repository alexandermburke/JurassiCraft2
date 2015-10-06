package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;

public class EntityMegapiranha extends EntityDinosaurSwimmingAggressive
{
    public EntityMegapiranha(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 0;
    }
}
