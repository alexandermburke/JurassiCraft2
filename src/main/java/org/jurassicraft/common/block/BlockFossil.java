package org.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.api.ISubBlocksBlock;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.item.itemblock.ItemFossilBlock;

import java.util.List;

public class BlockFossil extends Block implements ISubBlocksBlock
{
    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 15);

    private int start;

    public BlockFossil(int start)
    {
        super(Material.rock);
        this.setUnlocalizedName("fossil_block");
        this.setHardness(2.0F);
        this.setResistance(8.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(JCCreativeTabs.fossils);

        this.start = start;

        this.setDefaultState(blockState.getBaseState().withProperty(VARIANT, 0));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, meta);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (Integer) state.getValue(VARIANT);
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
    }

    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        List<Dinosaur> dinosaurs = JCEntityRegistry.getDinosaurs();

        for (int i = 0; i < 16; i++)
        {
            if (i + start > dinosaurs.size())
            {
                break;
            }

            list.add(new ItemStack(this, 1, i));
        }
    }

    public Dinosaur getDinosaur(int metadata)
    {
        return JCEntityRegistry.getDinosaurById(start + metadata);
    }

    /**
     * Queries the class of tool required to harvest this block, if null is returned we assume that anything can harvest this block.
     */
    public String getHarvestTool(IBlockState state)
    {
        return "pickaxe";
    }

    /**
     * Queries the harvest level of this item stack for the specified tool class, Returns -1 if this tool is not of the specified type
     *
     * @return Harvest level, or -1 if not the specified tool type.
     */
    public int getHarvestLevel(IBlockState state)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return true;
    }

    @Override
    public boolean isFullCube()
    {
        return true;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlockClass()
    {
        return ItemFossilBlock.class;
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
        return false;
    }
}
