package org.jurassicraft.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.lang.AdvLang;

import java.util.*;

public class ItemActionFigure extends Item
{
    public ItemActionFigure()
    {
        super();

        this.setUnlocalizedName("action_figure");

        this.setCreativeTab(JCCreativeTabs.items);
        this.setHasSubtypes(true);
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
