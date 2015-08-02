package net.timeless.jurassicraft.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.lang.AdvLang;

import java.util.*;

public class ItemFossil extends Item
{
    private String type;

    public ItemFossil(String type)
    {
        this.type = type.toLowerCase().replaceAll(" ", "_");

        this.setUnlocalizedName("fossil_" + this.type);
        this.setHasSubtypes(true);

        this.setCreativeTab(JCCreativeTabs.items);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        Dinosaur dinosaur = this.getDinosaur(stack);

        if (dinosaur != null)
            return new AdvLang("item." + type + ".name").withProperty("dino", "entity." + dinosaur.getName(0).replace(" ", "_").toLowerCase() + ".name").build();

        return super.getItemStackDisplayName(stack);
    }

    public Dinosaur getDinosaur(ItemStack stack)
    {
        return JCEntityRegistry.getDinosaurById(stack.getItemDamage());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subtypes)
    {
        List<Dinosaur> dinosaurs = new ArrayList<>(JCEntityRegistry.getDinosaurs());

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
                subtypes.add(new ItemStack(item, 1, ids.get(dino)));
        }
    }
}
