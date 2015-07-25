package net.timeless.unilib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BaseBlock extends Block {

    public BaseBlock(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabs.tabBlock);
    }

}
