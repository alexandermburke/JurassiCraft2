package net.reuxertz.ecocraft.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.reuxertz.ecocraft.api.BaseRegistry;
import net.reuxertz.ecocraft.block.BlockDroppedItem;
import net.reuxertz.ecocraft.block.BlockStack;
import net.reuxertz.ecocraft.item.document.DocSetEntityHome;
import net.reuxertz.ecocraft.item.ore.DiamondShard;
import net.reuxertz.ecocraft.item.ore.EmeraldShard;
import net.reuxertz.ecocraft.item.ore.IronNugget;
import net.reuxertz.ecocraft.item.tools.*;

public class EcoRegistry
        extends BaseRegistry
{
    //Documents
    public static DocSetEntityHome documentSetEntityHome = new DocSetEntityHome();

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

    //Ores
    public static Block copperOre = new BlockOre();
    public static Block tinOre = new BlockOre();
    public static Block silverOre = new BlockOre();

    //Environment Blocks
    public static BlockDroppedItem blockDroppedItem = new BlockDroppedItem();
    public static BlockStack blockStack = new BlockStack();

    //Item Entities
    public void init()
    {
        //Documents
        documentSetEntityHome.setUnlocalizedName("doc_setEntityHome");

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

        //Environment Blocks
        blockDroppedItem.setUnlocalizedName("dropped_item");
        blockStack.setUnlocalizedName("block_stack");


        //Ores
        copperOre.setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("copper_ore");
        tinOre.setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("tin_ore");
        silverOre.setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("silver_ore");

    }

    public void postinit()
    {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ItemModelMesher itemModelMesher = renderItem.getItemModelMesher();

        //Documents
        this.registerItemRenderer(itemModelMesher, EcoRegistry.documentSetEntityHome, "ecocraft:doc_setEntityHome", "inventory");

        //Sub-Items
        this.registerItemRenderer(itemModelMesher, EcoRegistry.ironNugget, "ecocraft:iron_nugget", "inventory");
        this.registerItemRenderer(itemModelMesher, EcoRegistry.emeraldShard, "ecocraft:emerald_shard", "inventory");
        this.registerItemRenderer(itemModelMesher, EcoRegistry.diamondShard, "ecocraft:diamond_shard", "inventory");

        //Tools
        this.registerItemRenderer(itemModelMesher, EcoRegistry.flintAxe, "ecocraft:flint_axe", "inventory");
        this.registerItemRenderer(itemModelMesher, EcoRegistry.flintPickaxe, "ecocraft:flint_pickaxe", "inventory");
        this.registerItemRenderer(itemModelMesher, EcoRegistry.flintShovel, "ecocraft:flint_shovel", "inventory");
        this.registerItemRenderer(itemModelMesher, EcoRegistry.flintHoe, "ecocraft:flint_hoe", "inventory");
        this.registerItemRenderer(itemModelMesher, EcoRegistry.ironChisel, "ecocraft:iron_chisel", "inventory");
        this.registerItemRenderer(itemModelMesher, EcoRegistry.ironHammer, "ecocraft:iron_hammer", "inventory");
        this.registerItemRenderer(itemModelMesher, EcoRegistry.ironKnife, "ecocraft:iron_knife", "inventory");

        //EcoItems
        this.registerItemRenderer(itemModelMesher, Item.getItemFromBlock(EcoRegistry.copperOre), "ecocraft:dropped_item", "inventory");
        this.registerItemRenderer(itemModelMesher, Item.getItemFromBlock(EcoRegistry.copperOre), "ecocraft:block_stack", "inventory");

        //Ores
        this.registerItemRenderer(itemModelMesher, Item.getItemFromBlock(EcoRegistry.copperOre), "ecocraft:copper_ore", "inventory");
        this.registerItemRenderer(itemModelMesher, Item.getItemFromBlock(EcoRegistry.tinOre), "ecocraft:tin_ore", "inventory");
        this.registerItemRenderer(itemModelMesher, Item.getItemFromBlock(EcoRegistry.silverOre), "ecocraft:silver_ore", "inventory");
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
