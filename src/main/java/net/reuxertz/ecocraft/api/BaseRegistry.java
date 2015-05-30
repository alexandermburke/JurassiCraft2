package net.reuxertz.ecocraft.api;

import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.reuxertz.ecocraft.core.EcoCraft;

import java.lang.reflect.Field;

public abstract class BaseRegistry implements IContentHandler
{
    protected String _modID = null;

    public BaseRegistry()
    {
    }

    public BaseRegistry(String modID)
    {
        this._modID = modID;
    }

    public abstract void init();

    public abstract void postinit();

    public abstract void initCreativeTabs();

    public abstract void initLanguage();

    public void AutoInit(Object o)
    {

    }

    public void register()
    {
        try
        {
            this.init();
            this.gameRegistry();
        }
        catch (Exception ex)
        {
            return;
        }
    }

    public void gameRegistry() throws Exception
    {
        initCreativeTabs();
        initLanguage();
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
                else if (obj instanceof Block)
                    registerBlock((Block) obj);
                else if (obj instanceof Block[])
                    for (Block block : (Block[]) obj)
                        registerBlock(block);
                else if (obj instanceof Entity)
                    registerEntity((Entity) obj);
            }
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void registerEntity(Entity ent)
    {
        Class entityClass = ent.getClass();

        int entityId = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, ent.getName(), entityId);
        EntityRegistry.registerModEntity(entityClass, ent.getName(), entityId, EcoCraft.Instance, 64, 1, true);
    }

    private void registerItem(Item item)
    {
        String name = item.getUnlocalizedName();
        String[] strings = name.split("\\.");
        name = strings[strings.length - 1];
        GameRegistry.registerItem(item, name, this._modID);
    }

    protected void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, final String path, final String type)
    {
        itemModelMesher.register(item, new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(path, type);
            }
        });
    }

    private void registerBlock(Block block)
    {
        String name = block.getUnlocalizedName();
        String[] strings = name.split("\\.");

        if (block instanceof ISubBlocksBlock)
            GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
        else
            GameRegistry.registerBlock(block, strings[strings.length - 1]);
    }

}
