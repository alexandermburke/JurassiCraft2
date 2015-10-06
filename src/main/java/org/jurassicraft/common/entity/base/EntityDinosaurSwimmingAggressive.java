package org.jurassicraft.common.entity.base;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityDinosaurSwimmingAggressive extends EntityDinosaurAggressive
{
    private float field_175482_b;
    private float field_175484_c;
    private float field_175483_bk;
    private float field_175485_bl;
    private float field_175486_bm;
    private boolean field_175480_bp;
    private EntityAIWander wander;

    public EntityDinosaurSwimmingAggressive(World world)
    {
        super(world);
        this.tasks.addTask(7, this.wander = new EntityAIWander(this, 1.0D, 150));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityDinosaur.class, 12.0F, 0.01F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.wander.setMutexBits(3);
        this.moveHelper = new EntityDinosaurSwimmingAggressive.SwimmingMoveHelper();
        this.field_175484_c = this.field_175482_b = this.rand.nextFloat();
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

    private void func_175476_l(boolean p_175476_1_)
    {
        this.func_175473_a(2, p_175476_1_);
    }

    protected PathNavigate func_175447_b(World worldIn)
    {
        return new PathNavigateSwimmer(this, worldIn);
    }

    public void entityInit()
    {
        super.entityInit();

        this.dataWatcher.addObject(16, Integer.valueOf(0));
    }

    public boolean func_175472_n()
    {
        return this.func_175468_a(2);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (this.worldObj.isRemote)
        {
            this.field_175484_c = this.field_175482_b;

            if (!this.isInWater())
            {
                this.field_175483_bk = 2.0F;

                if (this.motionY > 0.0D && this.field_175480_bp && !this.isSilent())
                {
                    this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.guardian.flop", 1.0F, 1.0F, false);
                }

                this.field_175480_bp = this.motionY < 0.0D && this.worldObj.isBlockNormalCube((new BlockPos(this)).down(), false);
            }
            else if (this.func_175472_n())
            {
                if (this.field_175483_bk < 0.5F)
                {
                    this.field_175483_bk = 4.0F;
                }
                else
                {
                    this.field_175483_bk += (0.5F - this.field_175483_bk) * 0.1F;
                }
            }
            else
            {
                this.field_175483_bk += (0.125F - this.field_175483_bk) * 0.2F;
            }

            this.field_175482_b += this.field_175483_bk;
            this.field_175486_bm = this.field_175485_bl;

            if (!this.isInWater())
            {
                this.field_175485_bl = this.rand.nextFloat();
            }
            else if (this.func_175472_n())
            {
                this.field_175485_bl += (0.0F - this.field_175485_bl) * 0.25F;
            }
            else
            {
                this.field_175485_bl += (1.0F - this.field_175485_bl) * 0.06F;
            }
        }

        if (this.inWater)
        {
            this.setAir(300);
        }
        else if (this.onGround)
        {
            this.motionY += 0.5D;
            this.motionX += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
            this.motionZ += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
            this.rotationYaw = this.rand.nextFloat() * 360.0F;
            this.onGround = false;
            this.isAirBorne = true;
        }

        super.onLivingUpdate();
    }

    private boolean func_175468_a(int p_175468_1_)
    {
        return (this.dataWatcher.getWatchableObjectInt(16) & p_175468_1_) != 0;
    }

    private void func_175473_a(int p_175473_1_, boolean p_175473_2_)
    {
        int j = this.dataWatcher.getWatchableObjectInt(16);

        if (p_175473_2_)
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(j | p_175473_1_));
        }
        else
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(j & ~p_175473_1_));
        }
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

    class SwimmingMoveHelper extends EntityMoveHelper
    {
        private EntityDinosaurSwimmingAggressive dino = EntityDinosaurSwimmingAggressive.this;

        public SwimmingMoveHelper()
        {
            super(EntityDinosaurSwimmingAggressive.this);
        }

        public void onUpdateMoveHelper()
        {
            if (this.update && !this.dino.getNavigator().noPath())
            {
                double xDist = this.posX - this.dino.posX;
                double yDist = this.posY - this.dino.posY;
                double zDist = this.posZ - this.dino.posZ;
                double dist = xDist * xDist + yDist * yDist + zDist * zDist;
                dist = (double) MathHelper.sqrt_double(dist);
                yDist /= dist;
                float f = (float) (Math.atan2(zDist, xDist) * 180.0D / Math.PI) - 90.0F;
                this.dino.rotationYaw = this.limitAngle(this.dino.rotationYaw, f, 30.0F);
                this.dino.renderYawOffset = this.dino.rotationYaw;
                float f1 = (float) (this.speed * this.dino.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                this.dino.setAIMoveSpeed(this.dino.getAIMoveSpeed() + (f1 - this.dino.getAIMoveSpeed()) * 0.125F);
                double d4 = Math.sin((double) (this.dino.ticksExisted + this.dino.getEntityId()) * 0.5D) * 0.05D;
                double d5 = Math.cos((double) (this.dino.rotationYaw * (float) Math.PI / 180.0F));
                double d6 = Math.sin((double) (this.dino.rotationYaw * (float) Math.PI / 180.0F));
                this.dino.motionX += d4 * d5;
                this.dino.motionZ += d4 * d6;
                d4 = Math.sin((double) (this.dino.ticksExisted + this.dino.getEntityId()) * 0.75D) * 0.05D;
                this.dino.motionY += d4 * (d6 + d5) * 0.25D;
                this.dino.motionY += (double) this.dino.getAIMoveSpeed() * yDist * 0.1D;
                EntityLookHelper lookHelper = this.dino.getLookHelper();
                double d7 = this.dino.posX + xDist / dist * 2.0D;
                double d8 = (double) this.dino.getEyeHeight() + this.dino.posY + yDist / dist * 1.0D;
                double d9 = this.dino.posZ + zDist / dist * 2.0D;
                double d10 = lookHelper.getLookPosX();
                double d11 = lookHelper.getLookPosY();
                double d12 = lookHelper.getLookPosZ();

                if (!lookHelper.getIsLooking())
                {
                    d10 = d7;
                    d11 = d8;
                    d12 = d9;
                }

                this.dino.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
                this.dino.func_175476_l(true);
            }
            else
            {
                this.dino.setAIMoveSpeed(0.0F);
                this.dino.func_175476_l(false);
            }
        }
    }
}
