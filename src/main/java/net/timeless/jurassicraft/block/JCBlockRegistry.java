package net.timeless.jurassicraft.block;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.timeless.jurassicraft.api.ISubBlocksBlock;
import net.timeless.jurassicraft.tileentity.TileCleaningStation;
import net.timeless.jurassicraft.tileentity.TileDnaSequencer;
import net.timeless.jurassicraft.tileentity.TileDnaSynthesizer;
import net.timeless.jurassicraft.tileentity.TileEmbryoInseminationMachine;
import net.timeless.jurassicraft.tileentity.TileEmbryonicMachine;
import net.timeless.jurassicraft.tileentity.TileFossilGrinder;

public class JCBlockRegistry
{
    public static BlockFossil fossil;
    public static BlockEncasedFossil encased_fossil;
    public static BlockCleaningStation cleaning_station;
    public static BlockFossilGrinder fossil_grinder;
    public static BlockDnaSequencer dna_sequencer;
    public static BlockDnaSynthesizer dna_synthesizer;
    public static BlockEmbryonicMachine embryonic_machine;
    public static BlockEmbryoInseminationMachine embryo_insemination_machine;

    public void register()
    {
        fossil = new BlockFossil();
        encased_fossil = new BlockEncasedFossil();
        cleaning_station = new BlockCleaningStation();
        fossil_grinder = new BlockFossilGrinder();
        dna_sequencer = new BlockDnaSequencer();
        dna_synthesizer = new BlockDnaSynthesizer();
        embryonic_machine = new BlockEmbryonicMachine();
        embryo_insemination_machine = new BlockEmbryoInseminationMachine();

        registerBlock(fossil, "Fossil Block");
        registerBlock(encased_fossil, "Encased Fossil");

        registerBlockTileEntity(TileCleaningStation.class, cleaning_station, "Cleaning Station");
        registerBlockTileEntity(TileDnaSequencer.class, dna_sequencer, "DNA Sequencer");
        registerBlockTileEntity(TileDnaSynthesizer.class, dna_synthesizer, "DNA Synthesizer");
        registerBlockTileEntity(TileEmbryoInseminationMachine.class, embryo_insemination_machine, "Embryo Insemination Machine");
        registerBlockTileEntity(TileEmbryonicMachine.class, embryonic_machine, "Embryonic Machine");
        registerBlockTileEntity(TileFossilGrinder.class, fossil_grinder, "Fossil Grinder");
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
