package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.api.ISubBlocksBlock;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.item.ItemJCLog;

import java.util.List;

public class SubBlockTutorial extends Block implements ISubBlocksBlock //We have to implement this to make a subblock. (It's one of our classes)
{
    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 15); //Metadata can only store a value from 0 - 15
    //PropertyInteger is a BlockState property that is a number. BlockStates use 'properties' and you can have multiple if you want

    public SubBlockTutorial()
    {
        super(Material.wood);
        this.setUnlocalizedName("jc_log");
        this.setHardness(1.0F);
        this.setResistance(1.0F); //Resistance is for explosions (tnt just for you)

        this.setCreativeTab(JCCreativeTabs.blocks);

        //getDefaultState returns whatever you do here
        this.setDefaultState(blockState.getBaseState().withProperty(VARIANT, 0)); //When you create a blockstate and you want to set a property you use withProperty, the property and then the value. Here it is 0 for the default state
    }

    public IBlockState getStateFromMeta(int meta) //This returns the state from the metadata. The word doesn't store the properties on their own, in the block class you have to convert your properties to metadata. (0 - 15)
    {
        return this.getDefaultState().withProperty(VARIANT, meta); //In this case, since we only have one integer property, we can just set our property to the metadata.
    }

    public int getMetaFromState(IBlockState state) //This does the inverse of getStateFromMeta. So it sets the metadata to whatever VARIANT is.
    {
        return (Integer) state.getValue(VARIANT);
    }

    protected BlockState createBlockState() //This creates a blockstate for this block. With all our properties. If we had more than one property we would just enter that into this array
    {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    protected ItemStack createStackedBlock(IBlockState state) //So this creates an itemstack from this blockstate. Because ItemStacks don't have properties we have to set the stacks damage value to our variant
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state)); //You see here we call getMetaFromState, because damage and meta are basically the same thing except one is for items and one is for blocks. Damage (items) can also hold a lot more data.
    }

    public int damageDropped(IBlockState state) //This returns the item damage to be dropped. I'm not sure why this is used since we do almost exactly the same thing above.
    {
        return getMetaFromState(state);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) //This is how you add multiple blocks to creative tabs
    {
        for (int i = 0; i < 2; i++) //There we replaced the last variable with i, which is the damage/metadata.
            list.add(new ItemStack(itemIn, 1, i)); //ItemStack here, first par is amount and second is damage/metadata. Damage is used for the variants.
    }

    //These are just some methods that specify that this block is solid.

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return true;
    }

    @Override
    public boolean isFullCube()
    {
        return true;
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlockClass()
    {
        return ItemJCLog.class; //Here we return the ItemBlock
    }
}
