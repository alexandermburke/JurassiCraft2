package net.reuxertz.ecoapi.base;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ecocraft.common.EcoBlocks;
import net.reuxertz.ecocraft.common.generator.EcoCraftGenerators;

import java.util.List;
import java.util.Random;

public class BaseSapling extends BlockBush implements IGrowable {

    private EcoBlocks.EnumTypeTree treeType;

    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    public BaseSapling(EcoBlocks.EnumTypeTree type) {
        super();
        treeType = type;
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));

        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);

    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn, pos, state, rand);
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (((Integer) state.getValue(STAGE)).intValue() == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            this.generateTree(worldIn, pos, state, rand);
        }
    }

    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        WorldGenAbstractTree gen;

        switch (treeType) {
            case APPLE:
                gen = EcoCraftGenerators.appleSapling;
                break;
            default:
                return;
        }

        boolean flag = gen.generate(worldIn, rand, pos);

        // if tree is not in legal position, reset sapling.
        if (!flag)
            worldIn.setBlockState(pos, state);
    }

    public int damageDropped(IBlockState state) {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        list.add(new ItemStack(itemIn, 1, 0));
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.grow(worldIn, pos, state, rand);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i |= ((Integer) state.getValue(STAGE)).intValue() << 3;
        return i;
    }

    protected BlockState createBlockState() {
        return new BlockState(this, STAGE);
    }

}
