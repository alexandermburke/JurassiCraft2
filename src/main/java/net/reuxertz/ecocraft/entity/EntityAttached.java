package net.reuxertz.ecocraft.entity;

import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.apache.commons.lang3.Validate;

public abstract class EntityAttached extends EntityItemFrame {

    public EntityAttached(World worldIn)
    {
        super(worldIn);
    }

    public EntityAttached(World worldIn, BlockPos p_i45853_2_, EnumFacing p_i45852_3_)
    {
        super(worldIn, p_i45853_2_, p_i45852_3_);
    }

    @Override
    protected void func_174859_a(EnumFacing p_174859_1_)
    {
        Validate.notNull(p_174859_1_);
        //Validate.isTrue(p_174859_1_.getAxis().isHorizontal());
        this.field_174860_b = p_174859_1_;
        this.prevRotationYaw = this.rotationYaw = (float)(this.field_174860_b.getHorizontalIndex() * 90);
        this.func_174856_o();
    }

    protected double func_174858_a(int p_174858_1_)
    {
        return p_174858_1_ % 32 == 0 ? 0.5D : 0.0D;
    }
    protected void func_174856_o()
    {
        if (this.field_174860_b != null)
        {
            double d0 = (double)this.hangingPosition.getX() + 0.5D;
            double d1 = (double)this.hangingPosition.getY() + 0.5D;
            double d2 = (double)this.hangingPosition.getZ() + 0.5D;
            double d3 = 0.46875D;
            double d4 = this.func_174858_a(this.getWidthPixels());
            double d5 = this.func_174858_a(this.getHeightPixels());
            d0 -= (double)this.field_174860_b.getFrontOffsetX() * 0.46875D;
            d2 -= (double)this.field_174860_b.getFrontOffsetZ() * 0.46875D;
            d1 += d5;
            EnumFacing enumfacing = this.field_174860_b.rotateYCCW();
            d0 += d4 * (double)enumfacing.getFrontOffsetX();
            d2 += d4 * (double)enumfacing.getFrontOffsetZ();
            this.posX = d0;
            this.posY = d1;
            this.posZ = d2;
            double d6 = (double)this.getWidthPixels();
            double d7 = (double)this.getHeightPixels();
            double d8 = (double)this.getWidthPixels();

            if (this.field_174860_b.getAxis() == EnumFacing.Axis.Z)
            {
                d8 = 1.0D;
            }
            else
            {
                d6 = 1.0D;
            }

            d6 /= 32.0D;
            d7 /= 32.0D;
            d8 /= 32.0D;
            this.setEntityBoundingBox(new AxisAlignedBB(d0 - d6, d1 - d7, d2 - d8, d0 + d6, d1 + d7, d2 + d8));
        }
    }
}
