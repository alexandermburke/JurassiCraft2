package org.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockMeta extends Block
{

    public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 15);
    private String path;
    private int subBlocks;

    public BlockMeta(Material material, String path, int subBlocks)
    {
        super(material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 2));
        this.path = path;
        this.subBlocks = subBlocks;
    }

    public String getPath()
    {
        return path;
    }

    public int getSubBlocks()
    {
        return subBlocks;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return ((Integer) state.getValue(TYPE)).intValue();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        for (int i = 0; i < getSubBlocks(); i++)
        {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(TYPE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer) state.getValue(TYPE)).intValue();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { TYPE });
    }

}
