package net.reuxertz.ecocraft.core.registry;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.creativetab.CreativeTabs;
import net.reuxertz.ecocraft.api.BaseItemRegistry;
import net.reuxertz.ecocraft.item.document.ItemDocSetEntityHome;
import net.reuxertz.ecocraft.item.ore.DiamondShard;
import net.reuxertz.ecocraft.item.ore.EmeraldShard;
import net.reuxertz.ecocraft.item.ore.IronNugget;
import net.reuxertz.ecocraft.item.tools.*;

public class ECItemRegistry
        extends BaseItemRegistry
{
    //Documents
    public static ItemDocSetEntityHome documentSetEntityHome = new ItemDocSetEntityHome();

    //Sub-Item
    public static IronNugget ironNugget = new IronNugget();
    public static EmeraldShard emeraldShard = new EmeraldShard();
    public static DiamondShard diamondShard = new DiamondShard();

    //Flint Tools
    public static FlintAxe flintAxe = new FlintAxe();
    public static FlintPickaxe flintPickaxe = new FlintPickaxe();
    public static FlintShovel flintShovel = new FlintShovel();
    public static FlintHoe flintHoe = new FlintHoe();

    //Iron Tools
    public static IronChisel ironChisel = new IronChisel();
    public static IronHammer ironHammer = new IronHammer();
    public static IronKnife ironKnife = new IronKnife();

    public void init()
    {
        //Documents
        documentSetEntityHome.setUnlocalizedName("itemDocumentSetEntityHome");

        //Sub-Items
        ironNugget.setUnlocalizedName("iron_nugget");
        emeraldShard.setUnlocalizedName("emerald_shard");
        diamondShard.setUnlocalizedName("diamond_shard");

        //Tools
        flintAxe.setUnlocalizedName("flint_axe");
        flintPickaxe.setUnlocalizedName("flint_pickaxe");
        flintShovel.setUnlocalizedName("flint_shovel");
        flintHoe.setUnlocalizedName("flint_hoe");
        ironChisel.setUnlocalizedName("iron_chisel");
        ironHammer.setUnlocalizedName("iron_hammer");
        ironKnife.setUnlocalizedName("iron_knife");

    }

    public void postinit(ItemModelMesher itemModelMesher)
    {
        //Documents
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.documentSetEntityHome, "ecocraft:itemDocumentSetEntityHome", "inventory");

        //Sub-Items
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.ironNugget, "ecocraft:iron_nugget", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.emeraldShard, "ecocraft:emerald_shard", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.diamondShard, "ecocraft:diamond_shard", "inventory");

        //Tools
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.flintAxe, "ecocraft:flint_axe", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.flintPickaxe, "ecocraft:flint_pickaxe", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.flintShovel, "ecocraft:flint_shovel", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.flintHoe, "ecocraft:flint_hoe", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.ironChisel, "ecocraft:iron_chisel", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.ironHammer, "ecocraft:iron_hammer", "inventory");
        this.registerItemRenderer(itemModelMesher, ECItemRegistry.ironKnife, "ecocraft:iron_knife", "inventory");
    }

    public void initCreativeTabs()
    {
        //Documents

        //SubItems
        ironNugget.setCreativeTab(CreativeTabs.tabMaterials);
        emeraldShard.setCreativeTab(CreativeTabs.tabMaterials);
        diamondShard.setCreativeTab(CreativeTabs.tabMaterials);

        //Tools
        flintAxe.setCreativeTab(CreativeTabs.tabTools);
        flintPickaxe.setCreativeTab(CreativeTabs.tabTools);
        flintShovel.setCreativeTab(CreativeTabs.tabTools);
        flintHoe.setCreativeTab(CreativeTabs.tabTools);
        ironChisel.setCreativeTab(CreativeTabs.tabTools);
        ironHammer.setCreativeTab(CreativeTabs.tabTools);
        ironKnife.setCreativeTab(CreativeTabs.tabTools);
    }
    public void initLanguage()
    {
    }
}
