package net.timeless.jurassicraft.common.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityIndominusRex extends EntityDinosaurAggressive implements ICarnivore, IEntityAICreature
{
    public ChainBuffer tailBuffer = new ChainBuffer(7);

    private float[] newSkinColor = new float[3];
    private float[] skinColor = new float[3];

    public EntityIndominusRex(World world)
    {
        super(world);
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
