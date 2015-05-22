package net.reuxertz.ainow.item;


import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.jurassicraft.item.ItemDinosaurSpawnEgg;
import net.ilexiconn.jurassicraft.item.ItemPlasterAndBandage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ainow.core.AINow;
import net.reuxertz.ainow.item.creative.ItemDocSetEntityHome;

import java.lang.reflect.Field;

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
