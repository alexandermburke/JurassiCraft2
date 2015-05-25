package net.reuxertz.ainow.api;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.reuxertz.ainow.core.AINow;
import net.reuxertz.ainow.item.ItemDocSetEntityHome;

import java.util.HashMap;

public class ItemRegistry
{
    public static ItemDocSetEntityHome DocumentSetEntityHome = new ItemDocSetEntityHome();
    private static HashMap<String, Item> _renderQueve = new HashMap<String, Item>();

    public static void RegisterItems()
    {
        DocumentSetEntityHome = new ItemDocSetEntityHome();
        DocumentSetEntityHome.setUnlocalizedName("itemDocumentSetEntityHome");
        GameRegistry.registerItem(DocumentSetEntityHome, "Set Entity Home Document", "AINow");
        LanguageRegistry.addName(DocumentSetEntityHome, "Set Entity Home Document");

        ItemRegistry._renderQueve.put("ainow:document_setEntityHome", DocumentSetEntityHome);
    }

    public static void registerItemRenderers()
    {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ItemModelMesher itemModelMesher = renderItem.getItemModelMesher();
        while (ItemRegistry._renderQueve.size() > 0) {
            String name = ItemRegistry._renderQueve.keySet().iterator().next();
            AINow.proxy.RegisterItemRenderer(itemModelMesher, ItemRegistry._renderQueve.get(name), name, "inventory");
            ItemRegistry._renderQueve.remove(name);
        }
    }
}
