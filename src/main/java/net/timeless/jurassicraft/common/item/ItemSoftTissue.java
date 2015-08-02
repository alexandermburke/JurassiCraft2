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

public class ItemSoftTissue extends Item
{
    public ItemSoftTissue()
    {
        this.setUnlocalizedName("soft_tissue");
        this.setHasSubtypes(true);

        this.setCreativeTab(JCCreativeTabs.dna);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        String dinoName = getDinosaur(stack).getName(0).toLowerCase().replaceAll(" ", "_");

        return new AdvLang("item.soft_tissue.name").withProperty("dino", "entity." + dinoName + ".name").build();
    }

    public Dinosaur getDinosaur(ItemStack stack)
    {
        Dinosaur dinosaur = JCEntityRegistry.getDinosaurById(stack.getItemDamage());

        if (dinosaur == null)
            dinosaur = JCEntityRegistry.achillobator;

        return dinosaur;
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
