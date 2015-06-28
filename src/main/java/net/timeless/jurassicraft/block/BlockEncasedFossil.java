package net.timeless.jurassicraft.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.api.ISubBlocksBlock;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.item.ItemEncasedFossil;
import net.timeless.jurassicraft.period.EnumTimePeriod;

public class BlockEncasedFossil extends Block implements ISubBlocksBlock
{
    public static final PropertyEnum PROPERTYPERIOD = PropertyEnum.create("period", EnumTimePeriod.class);

    public BlockEncasedFossil()
    {
        super(Material.rock);
        this.setUnlocalizedName("encased_fossil");
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockEncasedFossil.PROPERTYPERIOD, EnumTimePeriod.CRETACEOUS));
        this.setHardness(2.0F);
        this.setResistance(8.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    @Override
    public Class<? extends ItemBlock> getItemBlockClass()
    {
        return ItemEncasedFossil.class;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        EnumTimePeriod timePeriod = (EnumTimePeriod) state.getValue(BlockEncasedFossil.PROPERTYPERIOD);
        return timePeriod.getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        EnumTimePeriod[] timePeriods = EnumTimePeriod.values();

        for (EnumTimePeriod timePeriod : timePeriods)
        {
            if (timePeriod.shouldBeImplement())
            {
                list.add(new ItemStack(item, 1, timePeriod.getMetadata()));
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(BlockEncasedFossil.PROPERTYPERIOD, EnumTimePeriod.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumTimePeriod) state.getValue(BlockEncasedFossil.PROPERTYPERIOD)).getMetadata();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, BlockEncasedFossil.PROPERTYPERIOD);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state;
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        EnumTimePeriod timePeriod = EnumTimePeriod.byMetadata(meta);
        return this.getDefaultState().withProperty(BlockEncasedFossil.PROPERTYPERIOD, timePeriod);
    }
}
