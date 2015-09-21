/**
 * Copyright (C) 2015 by jabelar
 * This file is part of jabelar's Minecraft Forge modding examples; as such,
 * you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * For a copy of the GNU General Public License see <http://www.gnu.org/licenses/>.
 */

package net.timeless.jurassicraft.common.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.AnimID;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * @author jabelar
 */
public class EntityAIJCEatGrass extends EntityAIBase
{
    private static final Predicate<IBlockState> field_179505_b = BlockStateHelper.forBlock(Blocks.tallgrass)
            .where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
    /** The entity owner of this AITask */
    private final EntityLiving grassEaterEntity;
    /** The world the grass eater entity is eating from */
    private final World entityWorld;
    /** Number of ticks since the entity started to eat grass */
    int eatingGrassTimer;

    public EntityAIJCEatGrass(EntityLiving grassEaterEntityIn)
    {
        grassEaterEntity = grassEaterEntityIn;
        entityWorld = grassEaterEntityIn.worldObj;
        setMutexBits(7);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        if (grassEaterEntity.getRNG().nextInt(grassEaterEntity.isChild() ? 50 : 1000) != 0)
        {
            return false;
        } else
        {
            BlockPos blockpos = new BlockPos(grassEaterEntity.posX, grassEaterEntity.posY, grassEaterEntity.posZ);
            return field_179505_b.apply(entityWorld.getBlockState(blockpos)) ? true : entityWorld
                    .getBlockState(blockpos.down()).getBlock() == Blocks.grass;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        eatingGrassTimer = 40;
        entityWorld.setEntityState(grassEaterEntity, (byte) 10);
        grassEaterEntity.getNavigator().clearPathEntity();

        // DEBUG
        System.out.println("Starting eating AI for entity " + grassEaterEntity.getEntityId());
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask()
    {
        eatingGrassTimer = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return eatingGrassTimer > 0;
    }

    /**
     * Number of ticks since the entity started to eat grass
     */
    public int getEatingGrassTimer()
    {
        return eatingGrassTimer;
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask()
    {
        if (((IAnimatedEntity) grassEaterEntity).getAnimID() != AnimID.EATING
                && grassEaterEntity instanceof IAnimatedEntity)
        {
            AnimationAPI.sendAnimPacket((IAnimatedEntity) grassEaterEntity, AnimID.EATING);
        }

        eatingGrassTimer = Math.max(0, eatingGrassTimer - 1);

        if (eatingGrassTimer == 4)
        {
            BlockPos blockpos = new BlockPos(grassEaterEntity.posX, grassEaterEntity.posY, grassEaterEntity.posZ);

            if (field_179505_b.apply(entityWorld.getBlockState(blockpos)))
            {
                if (entityWorld.getGameRules().getGameRuleBooleanValue("mobGriefing"))
                {
                    entityWorld.destroyBlock(blockpos, false);
                }

                grassEaterEntity.eatGrassBonus();
            } else
            {
                BlockPos blockpos1 = blockpos.down();

                if (entityWorld.getBlockState(blockpos1).getBlock() == Blocks.grass)
                {
                    if (entityWorld.getGameRules().getGameRuleBooleanValue("mobGriefing"))
                    {
                        entityWorld.playAuxSFX(2001, blockpos1, Block.getIdFromBlock(Blocks.grass));
                        entityWorld.setBlockState(blockpos1, Blocks.dirt.getDefaultState(), 2);
                    }

                    grassEaterEntity.eatGrassBonus();
                }
            }
        }
    }
}