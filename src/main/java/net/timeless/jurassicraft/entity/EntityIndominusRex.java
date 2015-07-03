package net.timeless.jurassicraft.entity;

import net.ilexiconn.llibrary.client.model.modelbase.ChainBuffer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.api.animation.IAnimatedEntity;
import net.timeless.jurassicraft.entity.ai.EntityAIJCWander;
import net.timeless.jurassicraft.entity.base.EntityDinosaurAggressive;

public class EntityIndominusRex extends EntityDinosaurAggressive implements IAnimatedEntity
{
    private int animationId = -1;

    public ChainBuffer tailBuffer = new ChainBuffer(7);

    private float[] newSkinColor = new float[3];
    private float[] skinColor = new float[3];

    public EntityIndominusRex(World world)
    {
        super(world);

        // Placeholder AI
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPlayer.class, dinosaur.getAttackSpeed(), false));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        this.tasks.addTask(0, new EntityAIAttackOnCollide(this, EntityPig.class, dinosaur.getAttackSpeed(), false));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPig.class, false));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));

        this.tasks.addTask(6, new EntityAIJCWander(this, 40));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    public void setAnimID(int id)
    {
        this.animationId = id;
    }

    @Override
    public int getAnimID()
    {
        return animationId;
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        this.changeSkinColor();
        super.onUpdate();
    }

    public boolean isCamouflaging()
    {
        return false;
    }
    
    public void changeSkinColor()
    {
        BlockPos pos = new BlockPos(this).offset(EnumFacing.DOWN);
        IBlockState state = this.worldObj.getBlockState(pos);
        int color = state.getBlock().colorMultiplier(this.worldObj, pos);

        if (color == 16777215)
        {
            color = state.getBlock().getMapColor(state).colorValue;
        }

        if(color == 0)
        {
            color = 0xFFFFFF;
        }
        
        if (color != 0)
        {
            this.newSkinColor[0] = color >> 16 & 255;
            this.newSkinColor[1] = color >> 8 & 255;
            this.newSkinColor[2] = color & 255;

            if (this.skinColor[0] == 0 && this.skinColor[1] == 0 && this.skinColor[2] == 0)
            {
                this.skinColor[0] = this.newSkinColor[0];
                this.skinColor[1] = this.newSkinColor[1];
                this.skinColor[2] = this.newSkinColor[2];
            }
        }
        
        for (int i = 0; i < 3; ++i)
        {
            if (this.skinColor[i] < this.newSkinColor[i])
            {
                ++this.skinColor[i];
            }

            if (this.skinColor[i] > this.newSkinColor[i])
            {
                --this.skinColor[i];
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public float[] getSkinColor()
    {
        return new float[] {(float)this.skinColor[0] / 255.0F, (float)this.skinColor[1] / 255.0F, (float)this.skinColor[2] / 255.0F};
    }
}
