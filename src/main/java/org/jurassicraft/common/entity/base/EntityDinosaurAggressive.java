package org.jurassicraft.common.entity.base;

import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.ai.metabolism.EntityAIEatFoodItem;

public abstract class EntityDinosaurAggressive extends EntityDinosaur implements IMob
{
    public EntityDinosaurAggressive(World world)
    {
        super(world);
    }
}
