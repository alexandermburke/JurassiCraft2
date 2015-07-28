package net.timeless.jurassicraft.common.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.api.ISubBlocksBlock;
import net.timeless.jurassicraft.common.block.tree.*;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.tileentity.TileCleaningStation;
import net.timeless.jurassicraft.common.tileentity.TileDnaSequencer;
import net.timeless.jurassicraft.common.tileentity.TileDnaSynthesizer;
import net.timeless.jurassicraft.common.tileentity.TileEmbryoInseminationMachine;
import net.timeless.jurassicraft.common.tileentity.TileEmbryonicMachine;
import net.timeless.jurassicraft.common.tileentity.TileFossilGrinder;

public class JCBlockRegistry
{
    public static final int numOfTrees = 1;

    //tree blocks
    public static Block[] planks;
    public static Block[] woods;
    public static Block[] leaves;
    public static Block[] saplings;

    //wood products
    public static Block[] doors;
    public static Block[] fences;
    public static Block[] slabs;
    public static Block[] doubleSlabs;
    public static Block[] stairs;

    public static List<BlockFossil> fossils;
    public static List<BlockEncasedFossil> encased_fossils;

    public static BlockCleaningStation cleaning_station;
    public static BlockFossilGrinder fossil_grinder;
    public static BlockDnaSequencer dna_sequencer;
    public static BlockDnaSynthesizer dna_synthesizer;
    public static BlockEmbryonicMachine embryonic_machine;
    public static BlockEmbryoInseminationMachine embryo_insemination_machine;

    public static SubBlockTutorial jc_log;

    public static BlockAmber amber_ore;

    public void register()
    {
        fossils = new ArrayList<BlockFossil>();
        encased_fossils = new ArrayList<BlockEncasedFossil>();

        cleaning_station = new BlockCleaningStation();
        fossil_grinder = new BlockFossilGrinder();
        dna_sequencer = new BlockDnaSequencer();
        dna_synthesizer = new BlockDnaSynthesizer();
        embryonic_machine = new BlockEmbryonicMachine();
        embryo_insemination_machine = new BlockEmbryoInseminationMachine();

        jc_log = new SubBlockTutorial();
        amber_ore = new BlockAmber();

        List<Dinosaur> dinosaurs = JCEntityRegistry.getDinosaurs();

        int blocksToCreate = (int) (Math.ceil(((float) dinosaurs.size()) / 16.0F));

        for (int i = 0; i < blocksToCreate; i++)
        {
            BlockFossil fossil = new BlockFossil(i * 16);
            BlockEncasedFossil encasedFossil = new BlockEncasedFossil(i * 16);

            fossils.add(fossil);
            encased_fossils.add(encasedFossil);

            registerBlock(fossil, "Fossil Block " + i);
            registerBlock(encasedFossil, "Encased Fossil " + i);
        }

        registerBlock(jc_log, "JC Log");
        registerBlock(amber_ore, "Amber Ore");

        //initialize EnumType meta lookup
        EnumType.GINKGO.setMetaLookup();

        //initialize arrays
        planks = new Block[numOfTrees];
        woods = new Block[numOfTrees];
        leaves = new Block[numOfTrees];
        saplings = new Block[numOfTrees];
        doors = new Block[numOfTrees];
        fences = new Block[numOfTrees];
        slabs = new Block[numOfTrees];
        doubleSlabs = new Block[numOfTrees];
        stairs = new Block[numOfTrees];

        //initialize blocks within arrays
        for (int i = 0; i < numOfTrees; i++)
        {
            EnumType type = EnumType.getMetaLookup()[i];
            String typeName = type.getName();

            planks[i] = new BlockJCPlanks(type, typeName);
            woods[i] = new BlockJCLog(type, typeName);
            leaves[i] = new BlockJCLeaves(type, typeName);
            saplings[i] = new BlockJCSapling(type, typeName);

            GameRegistry.registerBlock(planks[i], typeName + "_planks");
            GameRegistry.registerBlock(woods[i], typeName + "_log");
            GameRegistry.registerBlock(leaves[i], typeName + "_leaves");
            GameRegistry.registerBlock(saplings[i], typeName + "_sapling");

            OreDictionary.registerOre("logWood", woods[i]);
            OreDictionary.registerOre("plankWood", planks[i]);
            OreDictionary.registerOre("treeLeaves", leaves[i]);
            OreDictionary.registerOre("treeSapling", saplings[i]);

            Blocks.fire.setFireInfo(leaves[i], 30, 60);
            Blocks.fire.setFireInfo(planks[i], 5, 20);
            Blocks.fire.setFireInfo(woods[i], 5, 5);
        }

        registerBlockTileEntity(TileCleaningStation.class, cleaning_station, "Cleaning Station");
        registerBlockTileEntity(TileDnaSequencer.class, dna_sequencer, "DNA Sequencer");
        registerBlockTileEntity(TileDnaSynthesizer.class, dna_synthesizer, "DNA Synthesizer");
        registerBlockTileEntity(TileEmbryoInseminationMachine.class, embryo_insemination_machine, "Embryo Insemination Machine");
        registerBlockTileEntity(TileEmbryonicMachine.class, embryonic_machine, "Embryonic Machine");
        registerBlockTileEntity(TileFossilGrinder.class, fossil_grinder, "Fossil Grinder");
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
        return id % 16;
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

        if (block instanceof ISubBlocksBlock)
            GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), name);
        else
            GameRegistry.registerBlock(block, name);
    }
}
