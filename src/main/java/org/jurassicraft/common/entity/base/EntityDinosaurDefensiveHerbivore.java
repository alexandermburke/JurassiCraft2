package org.jurassicraft.common.entity.base;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import org.jurassicraft.common.entity.ai.EntityAIFindPlant;
import org.jurassicraft.common.entity.ai.EntityAIJCPanic;

public class EntityDinosaurDefensiveHerbivore extends EntityDinosaur implements IMob
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
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        updateArmSwingProgress();
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (isEntityInvulnerable(source))
        {
            return false;
        }
        else if (super.attackEntityFrom(source, amount))
        {
            Entity entity = source.getEntity();
            return riddenByEntity != entity && ridingEntity != entity ? true : true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        AnimationAPI.sendAnimPacket(this, AnimID.ATTACKING);

        float damage = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int knockback = 0;

        if (entity instanceof EntityLivingBase)
        {
            damage += EnchantmentHelper.func_152377_a(getHeldItem(), ((EntityLivingBase) entity).getCreatureAttribute());
            knockback += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean attacked = entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);

        if (attacked)
        {
            if (knockback > 0)
            {
                entity.addVelocity(-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F, 0.1D, MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * knockback * 0.5F);
                motionX *= 0.6D;
                motionZ *= 0.6D;
            }

            applyEnchantments(this, entity);
        }

        return attacked;
    }

    @Override
    protected void applyEntityAttributes()
    {
        getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        super.applyEntityAttributes();
    }

    @Override
    public void updateCreatureData()
    {
        super.updateCreatureData();

        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(transitionFromAge(dinosaur.getBabyStrength(), dinosaur.getAdultStrength()));
    }
}
