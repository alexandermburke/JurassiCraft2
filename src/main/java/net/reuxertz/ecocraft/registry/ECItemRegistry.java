package net.reuxertz.ecocraft.registry;

import net.minecraft.client.renderer.ItemModelMesher;
import net.reuxertz.ecocraft.api.BaseItemRegistry;
import net.reuxertz.ecocraft.item.document.ItemDocSetEntityHome;
import net.reuxertz.ecocraft.ore.EmeraldShard;
import net.reuxertz.ecocraft.ore.IronNugget;

public class ECItemRegistry
        extends BaseItemRegistry
{
    public static ItemDocSetEntityHome documentSetEntityHome = new ItemDocSetEntityHome();
    public static IronNugget ironNugget = new IronNugget();
    public static EmeraldShard emeraldShard = new EmeraldShard();

    public void init()
    {
        documentSetEntityHome.setUnlocalizedName("itemDocumentSetEntityHome");
        ironNugget.setUnlocalizedName("iron_nugget");
        emeraldShard.setUnlocalizedName("emerald_shard");
    }

    public void postinit(ItemModelMesher itemModelMesher)
    {
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.documentSetEntityHome, "ecocraft:itemDocumentSetEntityHome", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.documentSetEntityHome, "ecocraft:itemDocumentSetEntityHome", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.documentSetEntityHome, "ecocraft:itemDocumentSetEntityHome", "inventory");
    }

    public void initCreativeTabs()
    {
    }
}
