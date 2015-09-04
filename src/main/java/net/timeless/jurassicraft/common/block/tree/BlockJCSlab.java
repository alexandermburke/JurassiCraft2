package net.timeless.jurassicraft.common.block.tree;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

public abstract class BlockJCSlab extends BlockSlab
{
    public static final PropertyBool SEAMLESS = PropertyBool.create("seamless");

    public BlockJCSlab(IBlockState state)
    {
        super(state.getBlock().getMaterial());
        IBlockState iblockstate = this.blockState.getBaseState();

        if (this.isDouble())
        {
            iblockstate = iblockstate.withProperty(SEAMLESS, Boolean.valueOf(false));
        }
        else
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate);
        this.setCreativeTab(JCCreativeTabs.plants);
    }

//    /**
//     * Get the Item that this Block should drop when harvested.
//     *
//     * @param fortune the level of the Fortune enchantment on the player's tool
//     */
//    public Item getItemDropped(IBlockState state, Random rand, int fortune)
//    {
//        return Item.getItemFromBlock(Blocks.stone_slab);
//    }
//
//    @SideOnly(Side.CLIENT)
//    public Item getItem(World worldIn, BlockPos pos)
//    {
//        return Item.getItemFromBlock(Blocks.stone_slab);
//    }

    @Override
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName();
    }

    public IProperty getVariantProperty()
    {
        return null;
    }

    public Object getVariant(ItemStack stack)
    {
        return null;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState();

        if (this.isDouble())
        {
            state = state.withProperty(SEAMLESS, Boolean.valueOf((meta & 8) != 0));
        }
        else
        {
            state = state.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return state;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (this.isDouble())
        {
            if (((Boolean)state.getValue(SEAMLESS)).booleanValue())
            {
                i |= 8;
            }
        }
        else if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    protected BlockState createBlockState()
    {
        return this.isDouble() ? new BlockState(this, new IProperty[] {SEAMLESS}): new BlockState(this, new IProperty[] {HALF});
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
}