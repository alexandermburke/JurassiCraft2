package net.timeless.jurassicraft.common.block.plant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

public class BlockCryPansy extends BlockBush
{
    public BlockCryPansy()
    {
        super();
        this.setUnlocalizedName("cry_pansy");
        this.setCreativeTab(JCCreativeTabs.plants);

        this.setStepSound(Block.soundTypeGrass);
    }
}
