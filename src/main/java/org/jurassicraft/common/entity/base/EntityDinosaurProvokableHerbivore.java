package org.jurassicraft.common.entity.base;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.ai.EntityAIFindPlant;

public abstract class EntityDinosaurProvokableHerbivore extends EntityDinosaurProvokable
{
    public EntityDinosaurProvokableHerbivore(World world)
    {
        super(world);
        tasks.addTask(1, new EntityAIFindPlant(this));
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
