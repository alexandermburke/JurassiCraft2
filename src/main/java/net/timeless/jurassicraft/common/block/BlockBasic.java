package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

public class BlockBasic extends Block
{
    public BlockBasic(Material material, String name)
    {
        super(material);
        this.setUnlocalizedName(name.toLowerCase().replaceAll(" ", "_"));
        this.setCreativeTab(JCCreativeTabs.blocks);
    }
}
