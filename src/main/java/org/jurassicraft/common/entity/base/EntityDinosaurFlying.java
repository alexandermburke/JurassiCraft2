package org.jurassicraft.common.entity.base;

import net.minecraft.world.World;

public class EntityDinosaurFlying extends EntityDinosaur
{
    public EntityDinosaurFlying(World world)
    {
        super(world);
    }

    public void fall(float f, float damageMultiplier)
    {
        // TODO slow itself down when landing, if falling too fast, take damage
    }
}
