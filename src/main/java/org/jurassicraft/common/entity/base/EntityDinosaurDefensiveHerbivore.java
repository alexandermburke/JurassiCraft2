package org.jurassicraft.common.entity.base;

import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;
import org.jurassicraft.common.entity.ai.EntityAIJCPanic;
import org.jurassicraft.common.entity.ai.metabolism.EntityAIFindPlant;

public abstract class EntityDinosaurDefensiveHerbivore extends EntityDinosaur implements IMob
{
    private final EntityAIJCPanic entityAIPanic = new EntityAIJCPanic(this, 1.25D);
    private final EntityAIFindPlant entityAIFindPlant = new EntityAIFindPlant(this);

    public EntityDinosaurDefensiveHerbivore(World world)
    {
        super(world);
        // tasks.addTask(5, entityAIEatGrass);
        tasks.addTask(1, entityAIPanic);
        tasks.addTask(2, entityAIFindPlant);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        updateArmSwingProgress();
    }
}
