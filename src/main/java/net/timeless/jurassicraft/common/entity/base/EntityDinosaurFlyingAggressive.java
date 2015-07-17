package net.timeless.jurassicraft.common.entity.base;

import net.minecraft.world.World;

public class EntityDinosaurFlyingAggressive extends EntityDinosaurAggressive
{
    public EntityDinosaurFlyingAggressive(World world)
    {
        super(world);
    }

    public void fall(float f, float damageMultiplier)
    {
        // TODO slow itself down when landing, if falling too fast, take damage
    }
}
