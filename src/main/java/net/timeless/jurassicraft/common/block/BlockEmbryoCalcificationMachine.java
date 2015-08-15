package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.tileentity.TileEmbryoCalcificationMachine;

import java.util.Random;

public class BlockEmbryoCalcificationMachine extends BlockOriented
{
    public BlockEmbryoCalcificationMachine()
    {
        super(Material.iron);
        this.setUnlocalizedName("embryo_calcification_machine");
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEmbryoCalcificationMachine)
                ((TileEmbryoCalcificationMachine) tileentity).setCustomInventoryName(stack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEmbryoCalcificationMachine)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileEmbryoCalcificationMachine) tileentity);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(JCBlockRegistry.embryo_calcification_machine);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(JCBlockRegistry.embryo_calcification_machine);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.isSneaking())
        {
            TileEntity tileEntity = world.getTileEntity(pos);

            if (tileEntity instanceof TileEmbryoCalcificationMachine)
            {
                TileEmbryoCalcificationMachine dnaSequencer = (TileEmbryoCalcificationMachine) tileEntity;

                if (dnaSequencer.isUseableByPlayer(player))
                {
                    player.openGui(JurassiCraft.instance, 4, world, pos.getX(), pos.getY(), pos.getZ());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEmbryoCalcificationMachine();
    }
}
