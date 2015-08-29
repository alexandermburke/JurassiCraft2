package net.timeless.jurassicraft.common.block.plant;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

import java.util.Random;

public class BlockScalyTreeFern extends BlockBush
{
    public static final PropertyEnum HALF = PropertyEnum.create("half", BlockScalyTreeFern.EnumBlockHalf.class);

    public BlockScalyTreeFern()
    {
        super(Material.vine);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, BlockScalyTreeFern.EnumBlockHalf.LOWER));
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.setUnlocalizedName("scaly_tree_fern");
        this.setCreativeTab(JCCreativeTabs.plants);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }

    /**
     * Whether this Block can be replaced directly by other blocks (true for e.g. tall grass)
     */
    public boolean isReplaceable(World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        return iblockstate.getBlock() != this;
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            boolean flag = state.getValue(HALF) == BlockScalyTreeFern.EnumBlockHalf.UPPER;
            BlockPos blockpos1 = flag ? pos : pos.up();
            BlockPos blockpos2 = flag ? pos.down() : pos;
            Object object = flag ? this : worldIn.getBlockState(blockpos1).getBlock();
            Object object1 = flag ? worldIn.getBlockState(blockpos2).getBlock() : this;

            if (!flag) this.dropBlockAsItem(worldIn, pos, state, 0); //Forge move above the setting to air.

            if (object == this)
            {
                worldIn.setBlockState(blockpos1, Blocks.air.getDefaultState(), 3);
            }

            if (object1 == this)
            {
                worldIn.setBlockState(blockpos2, Blocks.air.getDefaultState(), 3);
            }
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() != this) return super.canBlockStay(worldIn, pos, state); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        if (state.getValue(HALF) == BlockScalyTreeFern.EnumBlockHalf.UPPER)
        {
            return worldIn.getBlockState(pos.down()).getBlock() == this;
        }
        else
        {
            IBlockState iblockstate1 = worldIn.getBlockState(pos.up());
            return iblockstate1.getBlock() == this && super.canBlockStay(worldIn, pos, iblockstate1);
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     *
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(HALF) == BlockScalyTreeFern.EnumBlockHalf.UPPER)
        {
            return null;
        }
        else
        {
            return super.getItemDropped(state, rand, fortune);
        }
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(HALF, EnumBlockHalf.LOWER), 2);
        worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, BlockScalyTreeFern.EnumBlockHalf.UPPER), 2);
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (state.getValue(HALF) == BlockScalyTreeFern.EnumBlockHalf.UPPER)
        {
            if (worldIn.getBlockState(pos.down()).getBlock() == this)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    IBlockState iblockstate1 = worldIn.getBlockState(pos.down());

                    if (iblockstate1.getBlock() == this)
                    {
                        worldIn.destroyBlock(pos.down(), true);
                    }
                }
                else
                {
                    worldIn.setBlockToAir(pos.down());
                }
            }
        }
        else if (player.capabilities.isCreativeMode && worldIn.getBlockState(pos.up()).getBlock() == this)
        {
            worldIn.setBlockState(pos.up(), Blocks.air.getDefaultState(), 2);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    public int getDamageValue(World worldIn, BlockPos pos)
    {
        return getMetaFromState(worldIn.getBlockState(pos));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(HALF, BlockScalyTreeFern.EnumBlockHalf.values()[meta]);
    }

    /**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     */
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockScalyTreeFern.EnumBlockHalf)state.getValue(HALF)).ordinal();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {HALF});
    }

    @Override
    public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        //Forge: Break both parts on the client to prevent the top part flickering as default type for a few frames.
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() ==  this && state.getValue(HALF) == EnumBlockHalf.LOWER && world.getBlockState(pos.up()).getBlock() == this)
            world.setBlockToAir(pos.up());
        return world.setBlockToAir(pos);
    }

    static enum EnumBlockHalf implements IStringSerializable
    {
        UPPER,
        LOWER;

        public String toString()
        {
            return this.getName();
        }

        public String getName()
        {
            return this == UPPER ? "upper" : "lower";
        }
    }
}