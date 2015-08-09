package net.timeless.jurassicraft.common.entity.base;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDinosaurDefensiveHerbivore extends EntityDinosaur implements IMob
{

    private EntityAIEatGrass entityAIEatGrass = new EntityAIEatGrass(this);
    private int eatTimer;

    public EntityDinosaurDefensiveHerbivore(World world)
    {
        super(world);
        this.tasks.addTask(5, this.entityAIEatGrass);

    }

    protected void updateAITasks()
    {
        super.updateAITasks();
        this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (this.worldObj.isRemote)
        {
            this.eatTimer = Math.max(0, this.eatTimer - 1);
        }
        this.updateArmSwingProgress();
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_)
    {
        if (p_70103_1_ == 10)
        {
            this.eatTimer = 40;
        }
        else
        {
            super.handleHealthUpdate(p_70103_1_);
        }
    }

    public void eatGrassBonus()
    {
        if (this.isChild())
        {
            this.setAge((int) (dinosaurAge + dinosaurAge * 0.05));
        }
        this.setHealth((float) (this.getHealth() + this.getHealth() * 0.15));
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

            this.func_174815_a(this, entity);
        }

        return attacked;
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

    protected boolean func_146066_aG()
    {
        return true;
    }
}
