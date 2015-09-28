package org.jurassicraft.common.block.plant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import org.jurassicraft.common.creativetab.JCCreativeTabs;

public class BlockCryPansy extends BlockBush
{
    public BlockCryPansy()
    {
        super();
        this.setCreativeTab(JCCreativeTabs.plants);

        this.setStepSound(Block.soundTypeGrass);
    }
}
