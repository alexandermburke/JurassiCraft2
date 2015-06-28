package net.timeless.jurassicraft.item;

import java.lang.reflect.Field;

import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;

public class JCItemRegistry implements IContentHandler
{
    public static ItemPlasterAndBandage plaster_and_bandage;
    public static ItemDinosaurSpawnEgg spawn_egg;
    public static ItemFossil fossil;

    public static ItemDNA dna;
    public static ItemDinosaurEgg egg;
    public static ItemPaleoPad paleo_pad;
    
    @Override
    public void init()
    {
        plaster_and_bandage = new ItemPlasterAndBandage();
        spawn_egg = new ItemDinosaurSpawnEgg();
        fossil = new ItemFossil();
        dna = new ItemDNA();
        egg = new ItemDinosaurEgg();
        paleo_pad = new ItemPaleoPad();
    }

    public void initCreativeTabs()
    {
        plaster_and_bandage.setCreativeTab(JCCreativeTabs.items);
        spawn_egg.setCreativeTab(JCCreativeTabs.spawnEggs);
        fossil.setCreativeTab(JCCreativeTabs.items);
        egg.setCreativeTab(JCCreativeTabs.eggs);
        dna.setCreativeTab(JCCreativeTabs.dna);
        paleo_pad.setCreativeTab(JCCreativeTabs.items);
    }

    @Override
    public void gameRegistry() throws Exception
    {
        initCreativeTabs();

        try
        {
            for (Field f : this.getClass().getDeclaredFields())
            {
                Object obj = f.get(null);

                if (obj instanceof Item)
                    registerItem((Item) obj);
                else if (obj instanceof Item[])
                    for (Item item : (Item[]) obj)
                        registerItem(item);
            }
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void registerItem(Item item)
    {
        String name = item.getUnlocalizedName();
        String[] strings = name.split("\\.");
        name = strings[strings.length - 1];

        GameRegistry.registerItem(item, name);
    }
}
