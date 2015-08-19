package net.timeless.jurassicraft.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.lang.AdvLang;
import net.timeless.jurassicraft.common.plant.JCPlantRegistry;
import net.timeless.jurassicraft.common.plant.Plant;

import java.util.*;

public class ItemPlantDNA extends ItemDnaContainer
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
            plant = JCPlantRegistry.small_royal_fern;

        return plant;
    }

    public int getContainerId(ItemStack stack)
    {
        return JCPlantRegistry.getPlantId(getPlant(stack));
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
                subtypes.add(new ItemStack(item, 1, ids.get(plant)));
        }
    }
}
