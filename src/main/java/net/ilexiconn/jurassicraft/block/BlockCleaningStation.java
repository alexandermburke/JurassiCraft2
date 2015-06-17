package net.ilexiconn.jurassicraft.block;

import java.util.Random;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.tileentity.TileCleaningStation;
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

public class BlockCleaningStation extends BlockOriented
{
    public BlockCleaningStation()
    {
        super(Material.wood);
        this.setUnlocalizedName("block_cleaning_station");
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeWood);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileCleaningStation)
                ((TileCleaningStation) tileentity).setCustomInventoryName(stack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileCleaningStation)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileCleaningStation) tileentity);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(JCBlockRegistry.cleaning_station);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(JCBlockRegistry.cleaning_station);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            JurassiCraft.instance.getLogger().debug("Hello0");
            return true;
        }
        else if (!player.isSneaking())
        {
            JurassiCraft.instance.getLogger().debug("Hello1");
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileCleaningStation)
            {
                JurassiCraft.instance.getLogger().debug("Hello2");
                TileCleaningStation cleaningStation = (TileCleaningStation) tileEntity;
                if (cleaningStation.isUseableByPlayer(player))
                {
                    JurassiCraft.instance.getLogger().debug("Hello3");
                    player.openGui(JurassiCraft.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        try
        {
            return new TileCleaningStation();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
