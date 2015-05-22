package net.reuxertz.ainow.api.registry;


import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.reuxertz.ainow.item.creative.ItemDocSetEntityHome;

public class ItemRegistry {

    //Creative Items
    public static ItemDocSetEntityHome DocumentSetEntityHome = new ItemDocSetEntityHome();

    public static void RegisterCreativeItems()
    {
        ItemRegistry.DocumentSetEntityHome = new ItemDocSetEntityHome();
        ItemRegistry.DocumentSetEntityHome.setUnlocalizedName("itemDocumentSetEntityHome");
        GameRegistry.registerItem(ItemRegistry.DocumentSetEntityHome, "Set Entity Home Document", "AINow");
        LanguageRegistry.addName(ItemRegistry.DocumentSetEntityHome, "Set Entity Home Document");
        //inst.addRecipe(new ItemStack(ItemRegistry.DocumentSetEntityHome), new Object[]{new String[]{"Y", "#", "#"}, '#', Items.stick, 'Y', ItemRegistry.IronNugget });
        /*
        if(Minecraft.getMinecraft().theWorld.isRemote)
        {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

            renderItem.getItemModelMesher().register(ItemRegistry.DocumentSetEntityHome, 0,
                    new ModelResourceLocation(AINow.ModID + ":" + ItemRegistry.DocumentSetEntityHome.getUnlocalizedName(), "inventory"));*/

            /*RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            ItemModelMesher itemModelMesher = renderItem.getItemModelMesher();
            itemModelMesher.register(ItemRegistry.DocumentSetEntityHome, new ItemMeshDefinition()
            {
                public ModelResourceLocation getModelLocation(ItemStack stack)
                {
                    return new ModelResourceLocation(AINow.ModID + ":" + ItemRegistry.DocumentSetEntityHome.getUnlocalizedName(), "inventory");
                }
            });
        }*/
    }

}
