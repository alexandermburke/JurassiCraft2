package net.timeless.jurassicraft.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.api.ISubBlocksBlock;
import net.timeless.jurassicraft.common.item.ItemBlockCultivate;
import net.timeless.jurassicraft.common.tileentity.TileCultivate;

import java.util.List;

public class BlockCultivate extends BlockContainer implements ISubBlocksBlock
{
    public static final PropertyEnum COLOR = PropertyEnum.create("color", EnumDyeColor.class);

    public BlockCultivate(String position)
    {
        super(Material.iron);
        this.setUnlocalizedName("cultivator_" + position);
        this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
        this.setHardness(2.0F);
        this.setResistance(5.0F);
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }

    public void dropItems(World world, BlockPos pos)
    {
        if(world.getBlockState(pos).getBlock() == JCBlockRegistry.cultivate_top)
        {
            pos.add(0, -1, 0);
        }

        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileCultivate)
        {
            InventoryHelper.dropInventoryItems(world, pos, (TileCultivate) tile);
        }
    }

    /**
     * returns a subtypes of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List subtypes)
    {
        EnumDyeColor[] colors = EnumDyeColor.values();

        for (int j = 0; j < colors.length; ++j)
        {
            EnumDyeColor color = colors[j];
            subtypes.add(new ItemStack(item, 1, color.getMetadata()));
        }
    }

    @Override
    public Class<? extends ItemBlock> getItemBlockClass()
    {
        return ItemBlockCultivate.class;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileCultivate();
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(COLOR)).getMapColor();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumDyeColor)state.getValue(COLOR)).getMetadata();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {COLOR});
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }
}
