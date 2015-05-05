package net.ilexiconn.jurassicraft.entity;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.jurassicraft.json.JsonCreature;
import net.ilexiconn.llibrary.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.entity.multipart.IEntityMultiPart;
import net.ilexiconn.llibrary.json.JsonHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityDinosaur extends EntityCreature implements IEntityMultiPart, IEntityAdditionalSpawnData
{
    private EntityPart[] parts;
    private JsonCreature creature;

    private boolean gender;

    public EntityDinosaur(World world)
    {
        super(world);
        parts = JsonHelper.parseHitboxList(this, creature.getHitboxList());
        gender = rand.nextBoolean();

        if(creature.canSwim())
            tasks.addTask(0, new EntityAISwimming(this));

        tasks.addTask(1, new EntityAIWander(this, creature.getAdultSpeed()));
        tasks.addTask(1, new EntityAILookIdle(this));
        
        for (String attackTarget : creature.getAttackTargets())
        {
            try
            {
                Class targetClass = null;

                String[] parts = attackTarget.split("\\.");
                String creatureName = parts[parts.length - 1];

                if(creatureName.startsWith("Entity"))
                {
                    creatureName = creatureName.split("Entity")[1];
                }

                targetClass = JCEntityRegistry.getCreatureClassByName(creatureName);

                if(attackTarget.toLowerCase().contains("player"))
                {
                    targetClass = EntityPlayer.class;
                }

                if(targetClass == null && parts.length == 1)
                {
                    if(!attackTarget.startsWith("Entity"))
                    {
                        attackTarget = "Entity" + attackTarget;
                    }

                    targetClass = forName("net.ilexiconn.jurassicraft.entity." + attackTarget);

                    if(targetClass == null)
                    {     
                        targetClass = forName("net.minecraft.entity.passive." + attackTarget);

                        if(targetClass == null)
                        {     
                            targetClass = forName("net.minecraft.entity." + attackTarget);

                            if(targetClass == null)
                            {     
                                targetClass = forName("net.minecraft.entity.monster" + attackTarget);
                            }
                        }
                    }
                }

                if(targetClass == null)
                    targetClass = forName(attackTarget);

                if(targetClass != null)
                {
                    tasks.addTask(2, new EntityAIAttackOnCollide(this, targetClass, creature.getAdultSpeed(), false));
                    targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, targetClass, false));
                }
                else
                    System.err.println("No found class for attack target: " + attackTarget);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
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
        float attackDamage = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        double knockback = creature.getAdultKnockback();

        boolean canAttack = entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackDamage);

        if (canAttack)
        {
            if (knockback > 0)
            {
                entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)knockback * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)knockback * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            this.func_174815_a(this, entity);
        }

        return canAttack;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        creature = JCEntityRegistry.getCreatureFromClass(getClass());

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(creature.getAdultSpeed());
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(creature.getAdultStrength()); //TODO Change depending on age
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.updateArmSwingProgress();

        super.onLivingUpdate();
    }

    public JsonCreature getCreature()
    {
        return creature;
    }

    public EntityPart[] getParts()
    {
        return parts;
    }

    public boolean isMale()
    {
        return gender;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setBoolean("Gender", gender);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        gender = nbt.getBoolean("Gender");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeBoolean(gender);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        gender = additionalData.readBoolean();
    }

    private Class forName(String clazz)
    {
        try
        {
            return Class.forName(clazz);
        }
        catch (Exception e)
        {
        }

        return null;
    }
}