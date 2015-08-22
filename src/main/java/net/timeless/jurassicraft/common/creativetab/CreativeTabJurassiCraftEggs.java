package net.timeless.jurassicraft.common.creativetab;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class CreativeTabJurassiCraftEggs extends CreativeTabs
{
    private int[] metas;

    private long tick;
    private int item;

    public CreativeTabJurassiCraftEggs(String label)
    {
        super(label);
        this.metas = new int[JCEntityRegistry.getRegisteredDinosaurs().size()];

        int id = 0;
        int i = 0;

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            if(dino.shouldRegister())
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
        tick++;

        if(tick % 64 == 0)
        {
            item++;

            if(item >= metas.length)
            {
                item = 0;
            }
        }

        return new ItemStack(getTabIconItem(), 1, metas[item]);
    }

    @Override
    public Item getTabIconItem()
    {
        return JCItemRegistry.egg;
    }

    public void setTab(Block... blocks)
    {
        for (Block block : blocks)
            if (block != null)
                block.setCreativeTab(this);
    }

    public void setTab(Item... items)
    {
        for (Item item : items)
            if (item != null)
                item.setCreativeTab(this);
    }
}
