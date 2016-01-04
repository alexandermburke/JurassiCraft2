package org.jurassicraft.common.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.jurassicraft.common.creativetab.JCCreativeTabs;

public class BlockJCPlanks extends Block
{
    private EnumType treeType;

    public BlockJCPlanks(EnumType type, String treeName)
    {
        super(Material.wood);
        setHardness(2.0F);
        setResistance(0.5F);
        setStepSound(Block.soundTypeWood);
        setUnlocalizedName(treeName + "_planks");

        this.setCreativeTab(JCCreativeTabs.plants);

        treeType = type;
    }
}
