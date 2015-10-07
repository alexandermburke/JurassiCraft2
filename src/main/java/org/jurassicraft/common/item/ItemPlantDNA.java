package org.jurassicraft.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.lang.AdvLang;
import org.jurassicraft.common.plant.JCPlantRegistry;
import org.jurassicraft.common.plant.Plant;

import java.util.*;

public class ItemPlantDNA extends Item
{
    public ItemPlantDNA()
    {
        super();

        this.setUnlocalizedName("plant_dna");

        this.setCreativeTab(JCCreativeTabs.plants);
        this.setHasSubtypes(true);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        String plantName = getPlant(stack).getName().toLowerCase().replaceAll(" ", "_");

        return new AdvLang("item.plant_dna.name").withProperty("plant", "plants." + plantName + ".name").build();
    }

    public Plant getPlant(ItemStack stack)
    {
        Plant plant = JCPlantRegistry.getPlantById(stack.getItemDamage());

        if (plant == null)
        {
            plant = JCPlantRegistry.small_royal_fern;
        }

        return plant;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subtypes)
    {
        List<Plant> plants = new ArrayList<>(JCPlantRegistry.getPlants());

        Map<Plant, Integer> ids = new HashMap<>();

        int id = 0;

        for (Plant plant : plants)
        {
            ids.put(plant, id);

            id++;
        }

        Collections.sort(plants);

        for (Plant plant : plants)
        {
            if (plant.shouldRegister())
            {
                subtypes.add(new ItemStack(item, 1, ids.get(plant)));
            }
        }
    }

    public int getDNAQuality(EntityPlayer player, ItemStack stack)
    {
        int quality = player.capabilities.isCreativeMode ? 100 : 0;

        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
        }

        if (nbt.hasKey("DNAQuality"))
        {
            quality = nbt.getInteger("DNAQuality");
        }
        else
        {
            nbt.setInteger("DNAQuality", quality);
        }

        stack.setTagCompound(nbt);

        return quality;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List lore, boolean advanced)
    {
        int quality = getDNAQuality(player, stack);

        EnumChatFormatting colour;

        if (quality > 75)
        {
            colour = EnumChatFormatting.GREEN;
        }
        else if (quality > 50)
        {
            colour = EnumChatFormatting.YELLOW;
        }
        else if (quality > 25)
        {
            colour = EnumChatFormatting.GOLD;
        }
        else
        {
            colour = EnumChatFormatting.RED;
        }

        lore.add(colour + new AdvLang("lore.dna_quality.name").withProperty("quality", quality + "").build());
    }
}
