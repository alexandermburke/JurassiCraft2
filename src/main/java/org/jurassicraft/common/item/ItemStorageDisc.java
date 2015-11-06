package org.jurassicraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.storagedisc.IStorageType;
import org.jurassicraft.common.storagedisc.StorageTypeRegistry;

import java.util.List;

public class ItemStorageDisc extends Item
{
    public ItemStorageDisc()
    {
        super();
        this.setUnlocalizedName("storage_disc");
        this.setCreativeTab(JCCreativeTabs.items);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     *
     * @param tooltip  All lines to display in the Item's tooltip. This is a List of Strings.
     * @param advanced Whether the setting "Advanced tooltips" is enabled
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt != null)
        {
            String storageId = nbt.getString("StorageId");
            IStorageType type = StorageTypeRegistry.getStorageType(storageId);

            if (type != null)
            {
                type.readFromNBT(nbt);
                type.addInformation(stack, tooltip);
            }
        }
        else
        {
            tooltip.add(EnumChatFormatting.RED + StatCollector.translateToLocal("cage.empty.name"));
        }
    }

}
