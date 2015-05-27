package net.ilexiconn.jurassicraft.item;

import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.reuxertz.ainow.item.ItemDocSetEntityHome;

import java.lang.reflect.Field;

public class JCItemRegistry implements IContentHandler
{
    public static ItemDinosaurSpawnEgg spawn_egg;
    public static ItemPlasterAndBandage plaster_and_bandage;

    public void init()
    {
        spawn_egg = new ItemDinosaurSpawnEgg();
        plaster_and_bandage = new ItemPlasterAndBandage();
    }

    public void initCreativeTabs()
    {
        spawn_egg.setCreativeTab(JCCreativeTabs.items);
        plaster_and_bandage.setCreativeTab(JCCreativeTabs.items);
    }

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

    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, final String path, final String type)
    {
        itemModelMesher.register(item, new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(path, type);
            }
        });
    }
}
