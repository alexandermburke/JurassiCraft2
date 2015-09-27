package org.jurassicraft.common.entity.base;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.ai.EntityAIEatMeat;

public class EntityDinosaurAggressive extends EntityDinosaur implements IMob
{
    public EntityDinosaurAggressive(World world)
    {
        super(world);
        this.tasks.addTask(2, new EntityAIEatMeat(this));
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.updateArmSwingProgress();

        super.onLivingUpdate();
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (super.attackEntityFrom(source, amount))
        {
            Entity entity = source.getEntity();
            return this.riddenByEntity != entity && this.ridingEntity != entity ? true : true;
        }
        else
        {
            return false;
        }
    }

    public boolean attackEntityAsMob(Entity entity)
    {
        float damage = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int knockback = 0;

        if (entity instanceof EntityLivingBase)
        {
            damage += EnchantmentHelper.func_152377_a(this.getHeldItem(), ((EntityLivingBase) entity).getCreatureAttribute());
            knockback += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean attacked = entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

        if (attacked)
        {
            if (knockback > 0)
            {
                entity.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * (float) knockback * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * (float) knockback * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            this.applyEnchantments(this, entity);
        }

        return attacked;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && super.getCanSpawnHere();
    }

    protected void applyEntityAttributes()
    {
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        super.applyEntityAttributes();
    }

    public void updateCreatureData()
    {
        super.updateCreatureData();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(transitionFromAge(dinosaur.getBabyStrength(), dinosaur.getAdultStrength()));
    }

    protected boolean canDropLoot()
    {
        return true;
    }
}
