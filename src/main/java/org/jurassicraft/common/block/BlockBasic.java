package org.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.jurassicraft.common.creativetab.JCCreativeTabs;

class BlockBasic extends Block
{
    public BlockBasic()
    {
        super(Material.rock);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }
}
