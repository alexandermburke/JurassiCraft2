package net.ilexiconn.jurassicraft.block;

import net.ilexiconn.jurassicraft.api.ISubBlocksBlock;
import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;

public class JCBlockRegistry implements IContentHandler
{
    public static BlockFossil fossil;
    public static BlockEncasedFossil encased_fossil;
    public static BlockCleaningStation cleaning_station;
    public static BlockFossilGrinder fossil_grinder;
    public static BlockDnaSequencer dna_sequencer;
    public static BlockDnaSynthesizer dna_synthesizer;
    public static BlockEmbryonicMachine embryonic_machine;
    public static BlockEmbryoInseminationMachine embryo_insemination_machine;

    @Override
    public void init()
    {
        fossil = new BlockFossil();
        encased_fossil = new BlockEncasedFossil();
        cleaning_station = new BlockCleaningStation();
        fossil_grinder = new BlockFossilGrinder();
        dna_sequencer = new BlockDnaSequencer();
        dna_synthesizer = new BlockDnaSynthesizer();
        embryonic_machine = new BlockEmbryonicMachine();
        embryo_insemination_machine = new BlockEmbryoInseminationMachine();
    }

    public void initCreativeTabs()
    {
        fossil.setCreativeTab(JCCreativeTabs.blocks);
        encased_fossil.setCreativeTab(JCCreativeTabs.blocks);
        cleaning_station.setCreativeTab(JCCreativeTabs.blocks);
        fossil_grinder.setCreativeTab(JCCreativeTabs.blocks);
        dna_sequencer.setCreativeTab(JCCreativeTabs.blocks);
        dna_synthesizer.setCreativeTab(JCCreativeTabs.blocks);
        embryonic_machine.setCreativeTab(JCCreativeTabs.blocks);
        embryo_insemination_machine.setCreativeTab(JCCreativeTabs.blocks);
    }

    @Override
    public void gameRegistry() throws Exception
    {
        initCreativeTabs();
        try
        {
            for (Field f : JCBlockRegistry.class.getDeclaredFields())
            {
                Object obj = f.get(null);
                if (obj instanceof Block)
                    registerBlock((Block) obj);
                else if (obj instanceof Block[])
                    for (Block block : (Block[]) obj)
                        registerBlock(block);
            }
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void registerBlock(Block block)
    {
        String name = block.getUnlocalizedName();
        String[] strings = name.split("\\.");

        if (block instanceof ISubBlocksBlock)
            GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
        else
            GameRegistry.registerBlock(block, strings[strings.length - 1]);
    }
}
