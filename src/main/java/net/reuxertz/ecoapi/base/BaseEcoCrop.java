package net.reuxertz.ecoapi.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ecoapi.ecology.EcoTerra;

import java.util.Random;

public class BaseEcoCrop extends BlockCrops implements IGrowable {

    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
    public static final PropertyInteger HEIGHT = PropertyInteger.create("height", 0, 1);

    protected Item seed, crop;
    protected int isDouble;

    public BaseEcoCrop setSeedCrop(Item seed, Item crop)
    {
        this.seed = seed;
        this.crop = crop;

        return this;
    }
    public BaseEcoCrop setSeedCrop(Item seedCrop)
    {
        this.setSeedCrop(seedCrop, seedCrop);

        return this;
    }
    public BaseEcoCrop setRecipe(Item seedCrop, Item seed)
    {
        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(seed), new ItemStack(seedCrop));
        return this;
    }
    public BaseEcoCrop(Item seed, Item crop)
    {
        this();
        this.setSeedCrop(seed, crop);
    }
    public BaseEcoCrop()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab(null);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.disableStats();
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        IBlockState blockStateDown = worldIn.getBlockState(pos.down());
        Block blockDown = blockStateDown.getBlock();
        if (blockDown == Blocks.dirt || blockDown == Blocks.grass)
            return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);

        return super.colorMultiplier(worldIn, pos, renderPass);
    }
    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return ColorizerFoliage.getFoliageColorBasic();
    }
    @Override
    public boolean canPlaceBlockOn(Block ground)
    {
        boolean b = ground == Blocks.farmland || ground == Blocks.grass || ground == Blocks.dirt;
        return b;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        //super.updateTick(worldIn, pos, state, rand);
        this.checkAndDropBlock(worldIn, pos, state);

        IBlockState below = worldIn.getBlockState(pos.down());
        Block belowBlock = below.getBlock();

        if (belowBlock != Blocks.farmland && rand.nextDouble() > .5)
            return;

        if (belowBlock == Blocks.grass && rand.nextDouble() > .5)
            return;

        for (int x = -4; x <= 4; x++)
            for (int z = -4; z <= 4; z++)
                for (int y = -1; y <= 1; y++)
                {
                    IBlockState bs = worldIn.getBlockState(pos.down());
                    Block b = below.getBlock();

                    boolean hasFresh = b.getUnlocalizedName().toLowerCase().contains("freshwater") || b.getMaterial() == EcoTerra.materialFreshWater;

                    if (!hasFresh && rand.nextDouble() > .5)
                        return;
                }

        if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
        {
            int i = ((Integer)state.getValue(AGE)).intValue();

            if (i < 7)
            {
                float f = getGrowthChance(this, worldIn, pos);

                /*
                Iterator iterator = BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)).iterator();
                BlockPos.MutableBlockPos mutableblockpos;

                boolean r = false, brk = false;
                {
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            r = false;
                            brk  = true;
                            break;
                        }

                        mutableblockpos = (BlockPos.MutableBlockPos) iterator.next();

                        IBlockState state1 = worldIn.getBlockState(mutableblockpos);
                        Block b1 = state1.getBlock();
                        Material m = b1.getMaterial();

                        if (m == Material.water)
                        {
                            break;
                        }
                    }
                    while (worldIn.getBlockState(mutableblockpos).getBlock().getMaterial() != Material.water);

                    if (!brk)
                        r = true;
                }

                if (!r)
                {
                    int a  = 7;
                }*/

                if (rand.nextInt((int)(25.0F / f) + 1) == 0)
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 2);
                }
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = ((Integer)state.getValue(AGE)).intValue() + MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5);

        if (i > 7)
        {
            i = 7;
        }

        worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i)), 2);
    }

    public static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos)
    {
        float f = 1.0F;
        BlockPos blockpos1 = pos.down();

        for (int i = -1; i <= 1; ++i)
        {
            for (int j = -1; j <= 1; ++j)
            {
                float f1 = 0.0F;
                IBlockState iblockstate = worldIn.getBlockState(blockpos1.add(i, 0, j));

                if (iblockstate.getBlock().canSustainPlant(worldIn, blockpos1.add(i, 0, j), net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable)blockIn))
                {
                    f1 = 1.0F;

                    if (iblockstate.getBlock().isFertile(worldIn, blockpos1.add(i, 0, j)))
                    {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos2 = pos.north();
        BlockPos blockpos3 = pos.south();
        BlockPos blockpos4 = pos.west();
        BlockPos blockpos5 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos4).getBlock() || blockIn == worldIn.getBlockState(blockpos5).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos2).getBlock() || blockIn == worldIn.getBlockState(blockpos3).getBlock();

        if (flag && flag1)
        {
            f /= 2.0F;
        }
        else
        {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos5.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos5.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock();

            if (flag2)
            {
                f /= 2.0F;
            }
        }

        return f;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return (worldIn.getLight(pos) >= 8 || worldIn.canSeeSky(pos)) && worldIn.getBlockState(pos.down()).getBlock().canSustainPlant(worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }

    public Item getSeed(){
        return seed;
    }

    public Item getCrop(){
        return crop;
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ((Integer)state.getValue(AGE)).intValue() == 7 ? this.getCrop() : this.getSeed();
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return ((Integer)state.getValue(AGE)).intValue() < 7;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return this.getSeed();
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        this.grow(worldIn, pos, state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(AGE)).intValue();
    }

    @Override
    public BlockState createBlockState()
    {
        return new BlockState(this, AGE);
    }

    @Override
    public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
        int age = ((Integer)state.getValue(AGE)).intValue();
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (age >= 7)
        {
            int k = 3 + fortune;

            for (int i = 0; i < 3 + fortune; ++i)
            {
                if (rand.nextInt(15) <= age)
                {
                    ret.add(new ItemStack(this.getSeed(), 1, 0));
                }
            }
        }
        return ret;
    }
}
