package net.reuxertz.ecocraft.api;

import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;

public abstract class BaseItemRegistry implements IContentHandler
{
    protected String _modID = null;

    public BaseItemRegistry()    {
    }

    public BaseItemRegistry(String modID)
    {
        this._modID = modID;
    }

    public abstract void init();

    public abstract void postinit(ItemModelMesher itemModelMesher);

    public abstract void initCreativeTabs();

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
        GameRegistry.registerItem(item, name , this._modID);
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
