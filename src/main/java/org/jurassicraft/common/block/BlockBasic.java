package org.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.jurassicraft.common.creativetab.JCCreativeTabs;

public class BlockBasic extends Block
{
    public BlockBasic(Material material)
    {
        super(material);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }
}
