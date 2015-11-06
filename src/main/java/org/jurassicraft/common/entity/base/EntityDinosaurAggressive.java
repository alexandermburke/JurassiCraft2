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

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate()
    {
        updateArmSwingProgress();

        super.onLivingUpdate();
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && super.getCanSpawnHere();
    }

    @Override
    protected boolean canDropLoot()
    {
        return true;
    }
}
