package net.timeless.jurassicraft.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.entity.item.EntityCageSmall;

import java.util.List;

public class ItemCage extends Item
{
    public ItemCage()
    {
        super();
        this.setUnlocalizedName("cage_small");
        this.setHasSubtypes(true);
        this.setCreativeTab(JCCreativeTabs.items);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     *
     * @param subItems The List of sub-items. This is a List of ItemStacks.
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     *
     * @param tooltip All lines to display in the Item's tooltip. This is a List of Strings.
     * @param advanced Whether the setting "Advanced tooltips" is enabled
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
        int caged = getCaged(stack);
        NBTTagCompound data = getData(stack);

        if(caged != -1)
        {
            tooltip.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("entity." + EntityList.classToStringMapping.get(EntityList.idToClassMapping.get(caged)) + ".name"));

            if(data != null)
            {
                tooltip.add(EnumChatFormatting.RED + StatCollector.translateToLocal("gender." + (data.getBoolean("IsMale") ? "male" : "female") + ".name"));
            }
        }
        else
        {
            tooltip.add(EnumChatFormatting.RED + StatCollector.translateToLocal("cage.empty.name"));
        }
    }

    /**
     * This is called when the item is used, before the block is activated.
     * @param stack The Item Stack
     * @param player The Player that used the item
     * @param world The Current World
     * @param pos Target position
     * @param side The side of the target hit
     * @return Return true to prevent any further processing.
     */
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        pos = pos.offset(side);

        if(player.canPlayerEdit(pos, side, stack) && !world.isRemote)
        {
            EntityCageSmall cage = new EntityCageSmall(world, stack.getMetadata() == 1);
            cage.setEntity(getCaged(stack));
            cage.setEntityData(getData(stack));
            cage.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

            world.spawnEntityInWorld(cage);

            if(!player.capabilities.isCreativeMode)
            {
                stack.stackSize--;
            }

            return true;
        }

        return false;
    }

    private int getCaged(ItemStack stack)
    {
        if(stack.getTagCompound() != null)
        {
            return stack.getTagCompound().getInteger("CagedID");
        }

        return -1;
    }

    private NBTTagCompound getData(ItemStack stack)
    {
        if (stack.getTagCompound() != null)
        {
            return stack.getTagCompound().getCompoundTag("Entity");
        }

        return null;
    }
}
