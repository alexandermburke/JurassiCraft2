package net.reuxertz.ecoapi.base;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseEcoTallCrop extends BaseEcoCrop
{
    public int getMaxCropHeight(World w, BlockPos bp)
    {
        return 2;
    }
    public boolean canCropGrowTop(World w, BlockPos bp)
    {
        int height = 0;
        while (w.getBlockState(bp).getBlock() == this)
        {
            height++;
            bp = bp.down();
        }

        return height < this.getMaxCropHeight(w, bp);
    }
    public boolean isCropTooHigh(World w, BlockPos bp)
    {
        int height = 0;
        while (w.getBlockState(bp).getBlock() == this)
        {
            height++;
            bp = bp.down();
        }

        return height > this.getMaxCropHeight(w, bp);
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos);
        int curAge = ((Integer)state.getValue(AGE)).intValue();
        int curHeight = ((Integer)state.getValue(HEIGHT)).intValue();
        float h = (Math.min(5, curAge + 1) * (16.0f / 5.0f)) / 16f;
        float f = 0f;

        return new AxisAlignedBB(   (double)pos.getX() + f, (double)pos.getY() + 0, (double)pos.getZ() + f,
                                    (double)pos.getX() + (1 - f), (double)pos.getY() + h, (double)pos.getZ() + (1 - f));
    }

    public BaseEcoTallCrop()
    {
        super();
        this.setDefaultState(this.getDefaultState().withProperty(HEIGHT, Integer.valueOf(0)));
        return;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (((Integer)state.getValue(AGE)).intValue() == 7)
        {
            if (!worldIn.isRemote)
                worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX(), pos.getY() + .5, pos.getZ(), new ItemStack(this.crop)));

            worldIn.setBlockState(pos, state
                    .withProperty(AGE, ((Integer) state.getValue(AGE)).intValue() - 3)
                    .withProperty(HEIGHT, ((Integer) state.getValue(HEIGHT)).intValue()), 2);

            return true;
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (((Integer)state.getValue(HEIGHT)).intValue() == 1 && worldIn.getBlockState(pos.up()).getBlock() != this)
            worldIn.setBlockState(pos, state.withProperty(AGE, ((Integer)state.getValue(AGE)).intValue()).withProperty(HEIGHT, 0), 2);

        this.checkForDrop(worldIn, pos, state);
    }

    protected final boolean checkForDrop(World worldIn, BlockPos p_176353_2_, IBlockState state)
    {
        if (this.canBlockStay(worldIn, p_176353_2_, state))
        {
            return true;
        }
        else
        {
            this.dropBlockAsItem(worldIn, p_176353_2_, state, 0);
            worldIn.setBlockToAir(p_176353_2_);
            return false;
        }
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.isCropTooHigh(worldIn, pos))
            return false;

        if (super.canBlockStay(worldIn, pos, state))
            return true;

        return worldIn.getBlockState(pos.down()).getBlock() == this;

    }

    @Override
    public BlockState createBlockState()
    {
        return new BlockState(this, AGE, HEIGHT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta % 8)).withProperty(HEIGHT, Integer.valueOf((int)((meta * 1.0f) / 8.0f)));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue() + 8 * ((Integer)state.getValue(HEIGHT)).intValue();
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return ((Integer)state.getValue(AGE)).intValue() < 7;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);

        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = ((Integer)state.getValue(AGE)).intValue();

            if (i < 7)
            {
                float f = getGrowthChance(this, worldIn, pos);

                if (rand.nextInt((int)(25.0F / f) + 1) == 0)
                {
                    this.grow(worldIn, pos, state);
                }
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
        int dAge = 1;//MathHelper.getRandomIntegerInRange(worldIn.rand, 1, 2);
        int baseAge = ((Integer)state.getValue(AGE)).intValue();
        int curHeight = ((Integer)state.getValue(HEIGHT)).intValue();
        int heightAbove = 0, curDY = 1;
        List<BlockPos> aboveBlocks = new ArrayList<BlockPos>();

        //if (worldIn.getBlockState(pos.down()).getFillBlock() == this)
        //    return;

        if (baseAge >= 7)
            return;

        /*while (worldIn.getBlockState(pos.add(0, curDY, 0)).getFillBlock() == state.getFillBlock())
        {
            aboveBlocks.add(pos.add(0, curDY, 0));
            heightAbove++;
            curDY++;
        }

        for (int i = 0; i < heightAbove; i++)
        {
            IBlockState curState = worldIn.getBlockState(aboveBlocks.get(i));
            int curAge = ((Integer)curState.getValue(AGE)).intValue();
            if (curAge < baseAge)
            {
                //worldIn.setBlockState(aboveBlocks.get(i), curState.withProperty(AGE, curAge + 1).withProperty(HEIGHT, 1), 2);
                this.handleGrow(worldIn, aboveBlocks.get(i), curState, i + 1);
                return;
            }
        }*/

        this.handleGrow(worldIn, pos, state, 0);

        /*
        int curHeight = ((Integer)state.getValue(HEIGHT)).intValue();
        if (baseAge == 4)
        {

        }
        else if (baseAge < 4)
        {
            worldIn.setBlockState(pos, state.withProperty(AGE, baseAge + 1).withProperty(HEIGHT, 0), 2);
        }
        else
        {

        }

        if (baseAge < 5)
        {
        }
        else
        {
            worldIn.setBlockState()
        }
*/
        IBlockState bs = worldIn.getBlockState(pos);
        return;
    }

    protected void handleGrow(World w, BlockPos bp, IBlockState state, int height)
    {
        int curAge = ((Integer)state.getValue(AGE)).intValue();
        int curHeight = ((Integer)state.getValue(HEIGHT)).intValue();

        boolean canGrow = this.canCropGrowTop(w, bp);

        //Transition from
        if (canGrow && curHeight == 0 && curAge >= 4)
        {
            w.setBlockState(bp, state.withProperty(AGE, curAge).withProperty(HEIGHT, 1), 2);
            w.setBlockState(bp.add(0, 1, 0), state.withProperty(AGE, 0).withProperty(HEIGHT, 0), 2);
            return;
        }

        if (curAge >= 7)
            return;

        //if (curAge < 4)
        //{
            w.setBlockState(bp, state.withProperty(AGE, curAge + 1).withProperty(HEIGHT, curHeight), 2);
        //}
    }
}
