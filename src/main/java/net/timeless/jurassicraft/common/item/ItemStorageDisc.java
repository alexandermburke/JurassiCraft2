package net.timeless.jurassicraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.genetics.DNA;
import net.timeless.jurassicraft.common.genetics.GeneticsContainer;
import net.timeless.jurassicraft.common.lang.AdvLang;

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
     * @param tooltip All lines to display in the Item's tooltip. This is a List of Strings.
     * @param advanced Whether the setting "Advanced tooltips" is enabled
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        if(nbt != null)
        {
            DNA data = DNA.readFromNBT(nbt);
            GeneticsContainer container = data.getContainer();

            tooltip.add(EnumChatFormatting.DARK_AQUA + new AdvLang("lore.dinosaur.name").withProperty("dino", "entity." + JCEntityRegistry.getDinosaurById(container.getDinosaur()).getName(container.getGeneticVariation()).toLowerCase() + ".name").build());

            int quality = data.getDNAQuality();

            EnumChatFormatting colour;

            if (quality > 75)
                colour = EnumChatFormatting.GREEN;
            else if (quality > 50)
                colour = EnumChatFormatting.YELLOW;
            else if (quality > 25)
                colour = EnumChatFormatting.GOLD;
            else
                colour = EnumChatFormatting.RED;

            tooltip.add(colour + new AdvLang("lore.dna_quality.name").withProperty("quality", quality + "").build());
            tooltip.add(EnumChatFormatting.BLUE + new AdvLang("lore.genetic_code.name").withProperty("code", container.toString()).build());
        }
        else
        {
            tooltip.add(EnumChatFormatting.RED + StatCollector.translateToLocal("cage.empty.name"));
        }
    }

}
