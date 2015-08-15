package net.timeless.jurassicraft.common.entity.base;

import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDinosaurProvokableHerbivore extends EntityDinosaurProvokable
{

    private EntityAIEatGrass entityAIEatGrass = new EntityAIEatGrass(this);
    private int eatTimer;

    public EntityDinosaurProvokableHerbivore(World world)
    {
        super(world);
        this.tasks.addTask(5, this.entityAIEatGrass);

    }

    protected void updateAITasks()
    {
        this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
        super.updateAITasks();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (this.worldObj.isRemote)
        {
            this.eatTimer = Math.max(0, this.eatTimer - 1);
        }
        this.updateArmSwingProgress();

        super.onLivingUpdate();
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_)
    {
        if (p_70103_1_ == 10)
        {
            this.eatTimer = 40;
        } else
        {
            super.handleHealthUpdate(p_70103_1_);
        }
    }

    public void eatGrassBonus()
    {
        if (this.isChild())
        {
            this.setAge((int) (dinosaurAge + dinosaurAge * 0.05));
        } else
        {
            this.setHealth((float) (this.getHealth() + this.getHealth() * 0.15));
        }
    }
}