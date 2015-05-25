package net.ilexiconn.jurassicraft.item;

import net.ilexiconn.jurassicraft.api.BaseItemRegistry;
import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.client.renderer.ItemModelMesher;

public class JCItemRegistry
    extends BaseItemRegistry
    implements IContentHandler
{
    public static ItemDinosaurSpawnEgg spawn_egg;
    public static ItemPlasterAndBandage plaster_and_bandage;

    public void init()
    {
        spawn_egg = new ItemDinosaurSpawnEgg();
        plaster_and_bandage = new ItemPlasterAndBandage();
    }

    public void postinit(ItemModelMesher itemModelMesher)
    {
        this.registerItemRenderer(itemModelMesher, JCItemRegistry.plaster_and_bandage, "jurassicraft:plaster_and_bandage", "inventory");
        this.registerItemRenderer(itemModelMesher, JCItemRegistry.spawn_egg, "jurassicraft:dino_spawn_egg", "inventory");
    }

    public void initCreativeTabs()
    {
        spawn_egg.setCreativeTab(JCCreativeTabs.items);
        plaster_and_bandage.setCreativeTab(JCCreativeTabs.items);
    }
}
