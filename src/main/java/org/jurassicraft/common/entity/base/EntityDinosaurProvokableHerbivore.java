package org.jurassicraft.common.entity.base;

import net.minecraft.world.World;

public abstract class EntityDinosaurProvokableHerbivore extends EntityDinosaurProvokable
{
    public EntityDinosaurProvokableHerbivore(World world)
    {
        super(world);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.updateArmSwingProgress();

        super.onLivingUpdate();
    }
}
