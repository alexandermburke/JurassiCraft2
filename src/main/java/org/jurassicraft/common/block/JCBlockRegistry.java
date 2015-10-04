package org.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.jurassicraft.common.api.ISubBlocksBlock;
import org.jurassicraft.common.block.plant.*;
import org.jurassicraft.common.block.tree.*;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.item.ItemBlockMeta;
import org.jurassicraft.common.item.ItemJCSlab;
import org.jurassicraft.common.paleopad.dinopedia.DinoPediaRegistry;
import org.jurassicraft.common.tileentity.*;
import org.jurassicraft.common.world.jurdstrees.algorythms.TreeCompendium;

import java.util.ArrayList;
import java.util.List;

public class JCBlockRegistry
{
    public static final int numOfTrees = 2;

    // tree blocks
    public static Block[] planks;
    public static Block[] woods;
    public static Block[] leaves;
    public static Block[] saplings;

    // wood products
    public static Block[] doors;
    public static Block[] fences;
    public static Block[] slabs;
    public static Block[] doubleSlabs;
    public static Block[] stairs;

    public static List<BlockFossil> fossils;
    public static List<BlockEncasedFossil> encased_fossils;

    public static Block cleaning_station;
    public static Block fossil_grinder;
    public static Block dna_sequencer;
    public static Block dna_synthesizer;
    public static Block embryonic_machine;
    public static Block embryo_calcification_machine;
    public static Block incubator;
    public static Block dna_extractor;

    public static Block amber_ore;
    public static Block ice_shard;

    public static Block gypsum_cobblestone;
    public static Block gypsum_stone;
    public static Block gypsum_bricks;
    public static Block dna_hybridizer;
    public static Block dna_combinator;

    public static Block reinforced_stone;
    public static Block reinforced_bricks;

    public static Block small_royal_fern;
    public static Block small_chain_fern;
    public static Block small_cycad;

    public static Block cultivate_top;
    public static Block cultivate_bottom;

    public static Block carnivore_feeder;
    public static Block bennettitalean_cycadeoidea;
    public static Block cry_pansy;
    public static Block scaly_tree_fern;
    public static Block cycad_zamites;
    public static Block dicksonia;

    public static Block bPlanks;

    public void register()
    {
        fossils = new ArrayList<BlockFossil>();
        encased_fossils = new ArrayList<BlockEncasedFossil>();

        carnivore_feeder = new BlockCarnivoreFeeder();
        cleaning_station = new BlockCleaningStation();
        fossil_grinder = new BlockFossilGrinder();
        dna_sequencer = new BlockDnaSequencer();
        dna_synthesizer = new BlockDnaSynthesizer();
        embryonic_machine = new BlockEmbryonicMachine();
        embryo_calcification_machine = new BlockEmbryoCalcificationMachine();
        incubator = new BlockIncubator();
        dna_extractor = new BlockDNAExtractor();
        dna_hybridizer = new BlockDNAHybridizer();
        dna_combinator = new BlockDNACombinator();
        cultivate_bottom = new BlockCultivateBottom();
        cultivate_top = new BlockCultivateTop();

        amber_ore = addBlock(new BlockAmber(), "amber_ore");
        ice_shard = addBlock(new BlockIceShard(), "ice_shard");

        gypsum_stone = addBlock(new BlockGypsumStone().setHardness(1.5F).setResistance(10.0F), "gypsum_stone");
        gypsum_cobblestone = addBlock(new BlockBasic(Material.rock).setHardness(1.5F).setResistance(10.0F), "gypsum_cobblestone");
        gypsum_bricks = addBlock(new BlockBasic(Material.rock).setHardness(1.5F).setResistance(10.0F), "gypsum_bricks");
        reinforced_stone = addBlock(new BlockBasic(Material.rock).setHardness(2.0F).setResistance(15.0F), "reinforced_stone");
        reinforced_bricks = addBlock(new BlockBasic(Material.rock).setHardness(2.0F).setResistance(15.0F), "reinforced_bricks");

        small_royal_fern = addBlock(new BlockSmallRoyalFern(), "small_royal_fern");
        small_chain_fern = addBlock(new BlockSmallChainFern(), "small_chain_fern");
        small_cycad = addBlock(new BlockSmallCycad(), "small_cycad");
        bennettitalean_cycadeoidea = addBlock(new BlockBennettitaleanCycadeoidea(), "bennettitalean_cycadeoidea");
        cry_pansy = addBlock(new BlockCryPansy(), "cry_pansy");
        scaly_tree_fern = addBlock(new BlockScalyTreeFern(), "scaly_tree_fern");
        cycad_zamites = addBlock(new BlockCycadZamites(), "cycad_zamites");
        dicksonia = addBlock(new BlockDicksonia(), "dicksonia");

        bPlanks = addBlock(new BlockMeta(Material.wood, "planks", 8).setCreativeTab(JCCreativeTabs.plants), "planks");

        List<Dinosaur> dinosaurs = JCEntityRegistry.getDinosaurs();

        int blocksToCreate = (int) (Math.ceil(((float) dinosaurs.size()) / 16.0F));

        for (int i = 0; i < blocksToCreate; i++)
        {
            BlockFossil fossil = new BlockFossil(i * 16);
            BlockEncasedFossil encasedFossil = new BlockEncasedFossil(i * 16);

            OreDictionary.registerOre("fossil", fossil);

            fossils.add(fossil);
            encased_fossils.add(encasedFossil);

            registerBlock(fossil, "Fossil Block " + i);
            registerBlock(encasedFossil, "Encased Fossil " + i);
        }

        // initialize EnumType meta lookup
        EnumType.GINKGO.setMetaLookup();
        EnumType.CALAMITES.setMetaLookup();

        // register Tree Variables
        TreeCompendium.addShapesToCompendium();
        TreeCompendium.registerTrees();

        // initialize arrays
        planks = new Block[numOfTrees];
        woods = new Block[numOfTrees];
        leaves = new Block[numOfTrees];
        saplings = new Block[numOfTrees];
        doors = new Block[numOfTrees];
        fences = new Block[numOfTrees];
        slabs = new Block[numOfTrees];
        doubleSlabs = new Block[numOfTrees];
        stairs = new Block[numOfTrees];

        // initialize blocks within arrays
        for (int i = 0; i < numOfTrees; i++)
        {
            EnumType type = EnumType.getMetaLookup()[i];
            String typeName = type.getName();

            planks[i] = new BlockJCPlanks(type, typeName);
            woods[i] = new BlockJCLog(type, typeName);
            leaves[i] = new BlockJCLeaves(type, typeName);
            saplings[i] = new BlockJCSapling(type, typeName);
            stairs[i] = new BlockJCStairs(typeName, planks[i].getDefaultState());
            slabs[i] = new BlockJCSlabHalf(typeName, planks[i].getDefaultState());
            doubleSlabs[i] = new BlockJCSlabDouble(typeName, slabs[i], planks[i].getDefaultState());

            GameRegistry.registerBlock(planks[i], typeName + "_planks");
            GameRegistry.registerBlock(woods[i], typeName + "_log");
            GameRegistry.registerBlock(leaves[i], typeName + "_leaves");
            GameRegistry.registerBlock(saplings[i], typeName + "_sapling");
            GameRegistry.registerBlock(stairs[i], typeName + "_stairs");
            GameRegistry.registerBlock(slabs[i], ItemJCSlab.class, typeName + "_slab", new Object[]{slabs[i], doubleSlabs[i]});
            GameRegistry.registerBlock(doubleSlabs[i], ItemJCSlab.class, typeName + "_double_slab", new Object[]{slabs[i], doubleSlabs[i]});

            OreDictionary.registerOre("logWood", woods[i]);
            OreDictionary.registerOre("plankWood", planks[i]);
            OreDictionary.registerOre("treeLeaves", leaves[i]);
            OreDictionary.registerOre("treeSapling", saplings[i]);
            OreDictionary.registerOre("slabWood", slabs[i]);
            OreDictionary.registerOre("stairkWood", stairs[i]);

            Blocks.fire.setFireInfo(leaves[i], 30, 60);
            Blocks.fire.setFireInfo(planks[i], 5, 20);
            Blocks.fire.setFireInfo(woods[i], 5, 5);
            Blocks.fire.setFireInfo(doubleSlabs[i], 5, 20);
            Blocks.fire.setFireInfo(slabs[i], 5, 20);
            Blocks.fire.setFireInfo(stairs[i], 5, 20);
        }

        registerBlockTileEntity(TileCultivate.class, cultivate_bottom, "Cultivate Bottom");
        registerBlock(cultivate_top, "Cultivate Top");
        registerBlockTileEntity(TileCarnivoreFeeder.class, carnivore_feeder, "Carnivore Feeder");
        registerBlockTileEntity(TileCleaningStation.class, cleaning_station, "Cleaning Station");
        registerBlockTileEntity(TileFossilGrinder.class, fossil_grinder, "Fossil Grinder");
        registerBlockTileEntity(TileDnaSequencer.class, dna_sequencer, "DNA Sequencer");
        registerBlockTileEntity(TileDnaSynthesizer.class, dna_synthesizer, "DNA Synthesizer");
        registerBlockTileEntity(TileEmbryonicMachine.class, embryonic_machine, "Embryonic Machine");
        registerBlockTileEntity(TileEmbryoCalcificationMachine.class, embryo_calcification_machine, "Embryo Calcification Machine");
        registerBlockTileEntity(TileDNAExtractor.class, dna_extractor, "DNA Extractor");
        registerBlockTileEntity(TileDNAHybridizer.class, dna_hybridizer, "DNA Hybridizer");
        registerBlockTileEntity(TileDNACombinator.class, dna_combinator, "DNA Combinator");
        registerBlockTileEntity(TileIncubator.class, incubator, "Incubator");
    }

    private Block addBlock(Block block, String name)
    {
        block.setUnlocalizedName(name);

        DinoPediaRegistry.registerItem(new ItemStack(block));

        if (block instanceof BlockMeta)
        {
            GameRegistry.registerBlock(block, ItemBlockMeta.class, name);
        }
        else if (block instanceof ISubBlocksBlock)
        {
            GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), name);
        }
        else
        {
            GameRegistry.registerBlock(block, name);
        }

        return block;
    }

    public BlockFossil getFossilBlock(Dinosaur dinosaur)
    {
        return getFossilBlock(JCEntityRegistry.getDinosaurId(dinosaur));
    }

    private int getBlockId(int dinosaurId)
    {
        return (int) (Math.floor((((float) dinosaurId + 1.0F) / 16.0F) - 0.0625F));
    }

    public BlockEncasedFossil getEncasedFossil(Dinosaur dinosaur)
    {
        return getEncasedFossil(JCEntityRegistry.getDinosaurId(dinosaur));
    }

    public BlockEncasedFossil getEncasedFossil(int id)
    {
        return encased_fossils.get(getBlockId(id));
    }

    public BlockFossil getFossilBlock(int id)
    {
        return fossils.get(getBlockId(id));
    }

    public int getDinosaurId(BlockFossil fossil, int metadata)
    {
        return (fossils.indexOf(fossil) * 15) + metadata;
    }

    public int getDinosaurId(BlockEncasedFossil fossil, int metadata)
    {
        return (encased_fossils.indexOf(fossil) * 16) + metadata;
    }

    public int getMetadata(int id)
    {
        return id % 15;
    }

    public int getMetadata(Dinosaur dinosaur)
    {
        return getMetadata(JCEntityRegistry.getDinosaurId(dinosaur));
    }

    public void registerBlockTileEntity(Class<? extends TileEntity> tileEntity, Block block, String name)
    {
        registerBlock(block, name);

        GameRegistry.registerTileEntity(tileEntity, "jurassicraft:" + name.toLowerCase().replaceAll(" ", "_"));
    }

    public void registerBlock(Block block, String name)
    {
        name = name.toLowerCase().replaceAll(" ", "_");

        DinoPediaRegistry.registerItem(new ItemStack(block));

        if (block instanceof ISubBlocksBlock)
            GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), name);
        else
            GameRegistry.registerBlock(block, name);
    }
}
