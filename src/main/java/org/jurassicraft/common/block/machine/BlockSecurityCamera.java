package org.jurassicraft.common.block.machine;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.jurassicraft.common.creativetab.JCCreativeTabs;

public class BlockSecurityCamera extends Block
{
    public BlockSecurityCamera()
    {
        super(Material.iron);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }
}
