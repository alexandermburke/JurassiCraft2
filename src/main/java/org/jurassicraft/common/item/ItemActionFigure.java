package org.jurassicraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.lang.AdvLang;
import org.jurassicraft.common.tileentity.TileActionFigure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemActionFigure extends Item
{
    public ItemActionFigure()
    {
        super();

        this.setUnlocalizedName("action_figure");

        this.setCreativeTab(JCCreativeTabs.merchandise);
        this.setHasSubtypes(true);
    }

    /**
     * Called when a Block is right-clicked with this Item
     *
     * @param pos  The block being right-clicked
     * @param side The side being right-clicked
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        pos = pos.offset(side);

        if (playerIn.canPlayerEdit(pos, side, stack))
        {
            Block block = JCBlockRegistry.action_figure;

            if (block.canPlaceBlockAt(worldIn, pos))
            {
                IBlockState state = block.getDefaultState();
                worldIn.setBlockState(pos, block.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, 0, playerIn));
                block.onBlockPlacedBy(worldIn, pos, state, playerIn, stack);

                TileActionFigure tile = (TileActionFigure) worldIn.getTileEntity(pos);
                tile.setDinosaur(stack.getItemDamage());

                if (!playerIn.capabilities.isCreativeMode)
                {
                    stack.stackSize--;
                }

                return true;
            }
        }

        return false;
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        String dinoName = getDinosaur(stack).getName().toLowerCase().replaceAll(" ", "_");

        return new AdvLang("item.action_figure.name").withProperty("dino", "entity." + dinoName + ".name").build();
    }

    public Dinosaur getDinosaur(ItemStack stack)
    {
        return JCEntityRegistry.getDinosaurById(stack.getMetadata());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subtypes)
    {
        List<Dinosaur> dinosaurs = new ArrayList<Dinosaur>(JCEntityRegistry.getDinosaurs());

        Map<Dinosaur, Integer> ids = new HashMap<Dinosaur, Integer>();

        int id = 0;

        for (Dinosaur dino : dinosaurs)
        {
            ids.put(dino, id);

            id++;
        }

        Collections.sort(dinosaurs);

        for (Dinosaur dino : dinosaurs)
        {
            if (dino.shouldRegister())
            {
                subtypes.add(new ItemStack(item, 1, ids.get(dino)));
            }
        }
    }
}
