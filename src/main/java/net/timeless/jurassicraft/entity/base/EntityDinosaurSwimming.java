package net.timeless.jurassicraft.entity.base;

import net.minecraft.block.material.Material;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDinosaurSwimming extends EntityDinosaur
{
    public EntityDinosaurSwimming(World world)
    {
        super(world);
    }
    
    public boolean isInWater()
    {
        return this.worldObj.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.water, this);
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (this.isInWater())
            this.motionY *= 0.1D;
    }
    
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    public void onEntityUpdate()
    {
        int air = this.getAir();

        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater())
        {
            --air;
            this.setAir(air);

            if (this.getAir() == -10)
            {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.drown, 2.0F);
            }
        }
        else
            this.setAir(300);
    }

    public boolean getCanSpawnHere()
    {
        return this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox());
    }
}
