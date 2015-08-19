package net.timeless.jurassicraft.common.block.plant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

public class BlockSmallRoyalFern extends BlockBush
{
    public BlockSmallRoyalFern()
    {
        super();
        this.setUnlocalizedName("small_royal_fern");
        this.setCreativeTab(JCCreativeTabs.plants);

        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);

        this.setStepSound(Block.soundTypeGrass);
    }
}
