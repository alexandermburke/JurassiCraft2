package net.timeless.jurassicraft.common.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.entity.ai.animations.JCAutoAnimBase;
import net.timeless.jurassicraft.common.entity.ai.animations.JCNonAutoAnimBase;
import net.timeless.jurassicraft.common.entity.ai.animations.JCNonAutoAnimSoundBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityIndominusRex extends EntityDinosaurAggressive  //implements ICarnivore, IEntityAICreature
{
    public ChainBuffer tailBuffer = new ChainBuffer(7);

    private static final String[] hurtSounds = new String[]{"indominus_hurt_1"};
    private static final String[] livingSounds = new String[]{"indominus_living_1"};
    private static final String[] deathSounds = new String[]{"indominus_death_1"};
    private static final String[] breathSounds = new String[]{"indominus_breath"};

    @SideOnly(Side.CLIENT)
    private float[] newSkinColor = new float[3];

    @SideOnly(Side.CLIENT)
    private float[] skinColor = new float[3];

    private int stepCount = 0;

    public EntityIndominusRex(World world)
    {
        super(world);
        tasks.addTask(2, new JCNonAutoAnimSoundBase(this, 75, 1, 750, breathSounds[0], 1.5F));
        this.attackCreature(EntityDinosaur.class, 0);
        this.defendFromAttacker(EntityDinosaur.class, 0);
    }

    public void onUpdate()
    {
        super.onUpdate();

        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);

        if (this.ticksExisted % 62 == 0)
            this.playSound(randomSound(breathSounds), this.getSoundVolume(), this.getSoundPitch());

        /** Step Sound */
        if (this.moveForward > 0 && this.stepCount <= 0)
        {
            this.playSound("jurassicraft:stomp", this.getSoundVolume() + 0.5F, this.getSoundPitch());
            stepCount = 65;
        }

        this.stepCount -= this.moveForward * 9.5;

        if(worldObj.isRemote)
        {
            changeSkinColor();
        }
//        if (getAnimID() == 0)
//            AnimationAPI.sendAnimPacket(this, 1);
    }

    @Override
    public float getSoundVolume()
    {
        return (float) transitionFromAge(0.9F, 1.6F) + ((rand.nextFloat() - 0.5F) * 0.125F);
    }

    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
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

        // if(color == 0)
        // {
        // color = 0xFFFFFF;
        // }

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
        return new float[]{this.skinColor[0] / 255.0F, this.skinColor[1] / 255.0F, this.skinColor[2] / 255.0F};
    }
}
