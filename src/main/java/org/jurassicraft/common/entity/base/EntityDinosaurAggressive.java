package org.jurassicraft.common.entity.base;

import net.minecraft.entity.monster.IMob;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.ai.metabolism.EntityAIEatMeat;

public abstract class EntityDinosaurAggressive extends EntityDinosaur implements IMob
{
    public EntityDinosaurAggressive(World world)
    {
        super(world);
        this.tasks.addTask(2, new EntityAIEatMeat(this));
    }
}
