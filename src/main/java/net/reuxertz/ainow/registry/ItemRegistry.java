package net.reuxertz.ainow.registry;

import net.minecraft.client.renderer.ItemModelMesher;
import net.reuxertz.ainow.api.BaseItemRegistry;
import net.reuxertz.ainow.item.ItemDocSetEntityHome;

public class ItemRegistry
        extends BaseItemRegistry
{
    public static ItemDocSetEntityHome DocumentSetEntityHome = new ItemDocSetEntityHome();

    public void init()
    {
        DocumentSetEntityHome.setUnlocalizedName("itemDocumentSetEntityHome");
    }

    public void postinit(ItemModelMesher itemModelMesher)
    {
        this.registerItemRenderer(itemModelMesher, ItemRegistry.DocumentSetEntityHome, "ainow:itemDocumentSetEntityHome", "inventory");
    }

    public void initCreativeTabs()
    {
    }
}
