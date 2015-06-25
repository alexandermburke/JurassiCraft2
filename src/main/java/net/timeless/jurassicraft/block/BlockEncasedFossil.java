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
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.api.ISubBlocksBlock;
import net.timeless.jurassicraft.item.ItemEncasedFossil;

public class BlockEncasedFossil extends Block implements ISubBlocksBlock
{
    public static final PropertyEnum PROPERTYPERIOD = PropertyEnum.create("period", BlockEncasedFossil.EnumTimePeriod.class);

    public BlockEncasedFossil()
    {
        super(Material.rock);
        this.setUnlocalizedName("encased_fossil");
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockEncasedFossil.PROPERTYPERIOD, BlockEncasedFossil.EnumTimePeriod.CRETACEOUS));
        this.setHardness(2.0F);
        this.setResistance(8.0F);
        this.setStepSound(Block.soundTypeStone);
    }
    
    @Override
    public Class<? extends ItemBlock> getItemBlockClass()
    {
        return ItemEncasedFossil.class;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        BlockEncasedFossil.EnumTimePeriod timePeriod = (BlockEncasedFossil.EnumTimePeriod) state.getValue(BlockEncasedFossil.PROPERTYPERIOD);
        return timePeriod.getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        BlockEncasedFossil.EnumTimePeriod[] timePeriods = BlockEncasedFossil.EnumTimePeriod.values();

        for (BlockEncasedFossil.EnumTimePeriod timePeriod : timePeriods)
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
        return this.getDefaultState().withProperty(BlockEncasedFossil.PROPERTYPERIOD, BlockEncasedFossil.EnumTimePeriod.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockEncasedFossil.EnumTimePeriod) state.getValue(BlockEncasedFossil.PROPERTYPERIOD)).getMetadata();
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
        BlockEncasedFossil.EnumTimePeriod timePeriod = BlockEncasedFossil.EnumTimePeriod.byMetadata(meta);
        return this.getDefaultState().withProperty(BlockEncasedFossil.PROPERTYPERIOD, timePeriod);
    }

    /**
     * PropertyEnum used create fossil blocks from different periods in time.
     */
    public enum EnumTimePeriod implements IStringSerializable
    {
        QUATERNARY(0, "quaternary", 2.588F, 0.0F, false), NEOGENE(1, "neogene", 23.03F, 2.589F, false), PALEOGENE(2, "paleogene", 66.0F, 23.04F, false), CRETACEOUS(3, "cretaceous", 145.5F, 66.1F, true), JURASSIC(4, "jurassic", 201.3F, 145.6F, false), TRIASSIC(5, "triassic", 252.17F, 201.4F, false), PERMIAN(6, "permian", 298.9F, 252.18F, false), CARBONIFEROUS(7, "carboniferous", 358.9F, 299.0F, false), DEVONIAN(8, "devonian", 419.2F, 359.0F, false), SILURIAN(9, "silurian", 443.4F, 419.3F, false), ORDOVICIAN(10, "ordovician", 485.4F, 443.5F, false), CAMBRIAN(11, "cambrian", 541.0F, 485.5F, false);

        /**
         * Returns the metadata of this time period.
         */
        public int getMetadata()
        {
            return this.meta;
        }

        @Override
        public String toString()
        {
            return this.name;
        }

        /**
         * Returns the time period from its metadata.
         */
        public static EnumTimePeriod byMetadata(int meta)
        {
            if (meta < 0 || meta >= BlockEncasedFossil.EnumTimePeriod.META_LOOKUP.length)
                meta = 0;
            return BlockEncasedFossil.EnumTimePeriod.META_LOOKUP[meta];
        }

        /**
         * Returns the name of this time period.
         */
        public String getName()
        {
            return this.name;
        }

        /**
         * Returns if this time period should be implemented.
         */
        public float getStartTime()
        {
            return this.startTime;
        }

        /**
         * Returns if this time period should be implemented.
         */
        public float getEndTime()
        {
            return this.endTime;
        }

        /**
         * Returns the tooltip of this itemBlock.
         */
        public String getNameForDisplay()
        {
            return this.name;
        }

        /**
         * Returns if this time period should be implemented.
         */
        public boolean shouldBeImplement()
        {
            return this.shouldImplement;
        }

        /**
         * Name of this period.
         */
        private final String name;
        /**
         * ID of this period.
         */
        private final int meta;
        /**
         * Start time of this period.
         */
        private final float startTime;
        /**
         * Final time of this period.
         */
        private final float endTime;
        /**
         * Tells if this period should be used.
         */
        private final boolean shouldImplement;
        /**
         * META_LOOKUP array.
         */
        private static final EnumTimePeriod[] META_LOOKUP = new EnumTimePeriod[values().length];

        EnumTimePeriod(int meta, String name, float startTime, float endTime, boolean shouldImplement)
        {
            this.name = name;
            this.meta = meta;
            this.startTime = startTime;
            this.endTime = endTime;
            this.shouldImplement = shouldImplement;
        }

        static
        {
            for (EnumTimePeriod timePeriod : values())
            {
                BlockEncasedFossil.EnumTimePeriod.META_LOOKUP[timePeriod.getMetadata()] = timePeriod;
            }
        }
    }
}
