package net.reuxertz.ainow.api;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.reuxertz.ainow.item.ItemDocSetEntityHome;

public class ItemRegistry
{
    public static ItemDocSetEntityHome DocumentSetEntityHome = new ItemDocSetEntityHome();

    public static void RegisterCreativeItems()
    {
        DocumentSetEntityHome = new ItemDocSetEntityHome();
        DocumentSetEntityHome.setUnlocalizedName("itemDocumentSetEntityHome");
        GameRegistry.registerItem(DocumentSetEntityHome, "Set Entity Home Document", "AINow");
        LanguageRegistry.addName(DocumentSetEntityHome, "Set Entity Home Document");
    }
}
