package org.jurassicraft.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.item.JCItemRegistry;

public class CreativeTabJurassiCraftEggs extends CreativeTabs
{
    private int[] metas;

    public CreativeTabJurassiCraftEggs(String label)
    {
        super(label);
        this.metas = new int[JCEntityRegistry.getRegisteredDinosaurs().size()];

        int id = 0;
        int i = 0;

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            if (dino.shouldRegister() && !(dino.isMammal()))
            {
                metas[i] = id;

                i++;
            }

            id++;
        }
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
        return new ItemStack(getTabIconItem(), 1, metas[((int) ((JurassiCraft.timer / 20) % metas.length))]);
    }

    @Override
    public Item getTabIconItem()
    {
        return JCItemRegistry.egg;
    }
}
