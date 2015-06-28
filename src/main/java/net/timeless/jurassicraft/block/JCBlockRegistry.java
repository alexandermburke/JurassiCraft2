package net.timeless.jurassicraft.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.item.ItemEncasedFossil;
import net.timeless.jurassicraft.period.EnumTimePeriod;
import net.timeless.jurassicraft.tileentity.*;

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

    public static void preInitCommon()
    {
        fossil = new BlockFossil();
        registerBlock(fossil);

        encased_fossil = new BlockEncasedFossil();
        registerMultiBlock(encased_fossil, ItemEncasedFossil.class);

        cleaning_station = new BlockCleaningStation();
        registerBlockAndTileEntity(cleaning_station, TileCleaningStation.class);

        fossil_grinder = new BlockFossilGrinder();
        registerBlockAndTileEntity(fossil_grinder, TileFossilGrinder.class);

        dna_sequencer = new BlockDnaSequencer();
        registerBlockAndTileEntity(dna_sequencer, TileDnaSequencer.class);

        dna_synthesizer = new BlockDnaSynthesizer();
        registerBlockAndTileEntity(dna_synthesizer, TileDnaSynthesizer.class);

        embryonic_machine = new BlockEmbryonicMachine();
        registerBlockAndTileEntity(embryonic_machine, TileEmbryonicMachine.class);

        embryo_insemination_machine = new BlockEmbryoInseminationMachine();
        registerBlockAndTileEntity(embryo_insemination_machine, TileEmbryoInseminationMachine.class);
    }

    public static void initCommon()
    {

    }

    public static void postInitCommon()
    {

    }

    public static void preInitClientOnly()
    {
        registerEncasedFossilVariantNames();
    }

    public static void initClientOnly()
    {
        registerBlockRender(fossil);

        registerEncasedFossilRender();

        registerBlockRender(cleaning_station);

        registerBlockRender(fossil_grinder);

        registerBlockRender(dna_sequencer);

        registerBlockRender(dna_synthesizer);

        registerBlockRender(embryonic_machine);

        registerBlockRender(embryo_insemination_machine);
    }

    public static void postInitClientOnly()
    {

    }

    /**
     * Registers a simple block.
     */
    private static void registerBlock(Block block)
    {
        String blockItemUnlocalizedName = block.getUnlocalizedName().substring(5);
        GameRegistry.registerBlock(block, blockItemUnlocalizedName);
    }

    /**
     * Registers a multi block.
     */
    private static void registerMultiBlock(Block block, Class clazz)
    {
        String blockItemUnlocalizedName = block.getUnlocalizedName().substring(5);
        GameRegistry.registerBlock(block, clazz, blockItemUnlocalizedName);
    }

    /**
     * Registers a simple block and tile entity.
     */
    private static void registerBlockAndTileEntity(Block block, Class clazz)
    {
        String blockItemUnlocalizedName = block.getUnlocalizedName().substring(5);
        GameRegistry.registerBlock(block, blockItemUnlocalizedName);
        GameRegistry.registerTileEntity(clazz, blockItemUnlocalizedName);
    }

    /**
     * Registers a multi block and tile entity.
     */
    private static void registerMultiBlockAndTileEntity(Block block, Class clazz)
    {
        String blockItemUnlocalizedName = block.getUnlocalizedName().substring(5);
        GameRegistry.registerBlock(block, clazz, blockItemUnlocalizedName);
        GameRegistry.registerTileEntity(clazz, blockItemUnlocalizedName);
    }

    /**
     * Registers a simple block render.
     */
    private static void registerBlockRender(Block block)
    {
        String blockItemUnlocalizedName = block.getUnlocalizedName().substring(5);
        Item blockItem = GameRegistry.findItem(JurassiCraft.MODID, blockItemUnlocalizedName);
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(JurassiCraft.prependModID(blockItemUnlocalizedName), "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(blockItem, 0, itemModelResourceLocation);
    }

    /**
     * Registers the variant names of the encased fossil block.
     */
    private static void registerEncasedFossilVariantNames()
    {
        String blockItemUnlocalizedName = encased_fossil.getUnlocalizedName().substring(5);
        Item blockItem = GameRegistry.findItem(JurassiCraft.MODID, blockItemUnlocalizedName);

        for (EnumTimePeriod period : EnumTimePeriod.values())
            if (period.shouldBeImplement())
                ModelBakery.addVariantName(blockItem, JurassiCraft.prependModID(blockItemUnlocalizedName + "_" + period.getName()));
    }

    /**
     * Registers the encased fossil render.
     */
    private static void registerEncasedFossilRender()
    {
        String blockItemUnlocalizedName = encased_fossil.getUnlocalizedName().substring(5);
        Item blockItem = GameRegistry.findItem(JurassiCraft.MODID, blockItemUnlocalizedName);
        for (EnumTimePeriod period : EnumTimePeriod.values())
        {
            if (period.shouldBeImplement())
            {
                ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(JurassiCraft.prependModID(blockItemUnlocalizedName + "_" + period.getName()), "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(blockItem, period.getMetadata(), itemModelResourceLocation);
            }
        }
    }
}
