package org.jurassicraft.client.render;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.app.GuiAppRegistry;
import org.jurassicraft.client.model.animation.AnimationAchillobator;
import org.jurassicraft.client.model.animation.AnimationAnkylosaurus;
import org.jurassicraft.client.model.animation.AnimationApatosaurus;
import org.jurassicraft.client.model.animation.AnimationBaryonyx;
import org.jurassicraft.client.model.animation.AnimationBrachiosaurus;
import org.jurassicraft.client.model.animation.AnimationCarnotaurus;
import org.jurassicraft.client.model.animation.AnimationCearadactylus;
import org.jurassicraft.client.model.animation.AnimationChasmosaurus;
import org.jurassicraft.client.model.animation.AnimationCoelacanth;
import org.jurassicraft.client.model.animation.AnimationCompsognathus;
import org.jurassicraft.client.model.animation.AnimationCorythosaurus;
import org.jurassicraft.client.model.animation.AnimationDilophosaurus;
import org.jurassicraft.client.model.animation.AnimationDimorphodon;
import org.jurassicraft.client.model.animation.AnimationDodo;
import org.jurassicraft.client.model.animation.AnimationDunkleosteus;
import org.jurassicraft.client.model.animation.AnimationEdmontosaurus;
import org.jurassicraft.client.model.animation.AnimationGallimimus;
import org.jurassicraft.client.model.animation.AnimationGiganotosaurus;
import org.jurassicraft.client.model.animation.AnimationHerrerasaurus;
import org.jurassicraft.client.model.animation.AnimationHypsilophodon;
import org.jurassicraft.client.model.animation.AnimationLambeosaurus;
import org.jurassicraft.client.model.animation.AnimationLeaellynasaura;
import org.jurassicraft.client.model.animation.AnimationLeptictidium;
import org.jurassicraft.client.model.animation.AnimationLudodactylus;
import org.jurassicraft.client.model.animation.AnimationMajungasaurus;
import org.jurassicraft.client.model.animation.AnimationMamenchisaurus;
import org.jurassicraft.client.model.animation.AnimationMegapiranha;
import org.jurassicraft.client.model.animation.AnimationMetriacanthosaurus;
import org.jurassicraft.client.model.animation.AnimationMicroceratus;
import org.jurassicraft.client.model.animation.AnimationMoganopterus;
import org.jurassicraft.client.model.animation.AnimationOrnithomimus;
import org.jurassicraft.client.model.animation.AnimationOthnielia;
import org.jurassicraft.client.model.animation.AnimationOviraptor;
import org.jurassicraft.client.model.animation.AnimationPachycephalosaurus;
import org.jurassicraft.client.model.animation.AnimationParasaurolophus;
import org.jurassicraft.client.model.animation.AnimationProtoceratops;
import org.jurassicraft.client.model.animation.AnimationPteranodon;
import org.jurassicraft.client.model.animation.AnimationRugops;
import org.jurassicraft.client.model.animation.AnimationSegisaurus;
import org.jurassicraft.client.model.animation.AnimationSpinosaurus;
import org.jurassicraft.client.model.animation.AnimationStegosaurus;
import org.jurassicraft.client.model.animation.AnimationTherizinosaurus;
import org.jurassicraft.client.model.animation.AnimationTriceratops;
import org.jurassicraft.client.model.animation.AnimationTroodon;
import org.jurassicraft.client.model.animation.AnimationTropeognathus;
import org.jurassicraft.client.model.animation.AnimationTylosaurus;
import org.jurassicraft.client.model.animation.AnimationTyrannosaurus;
import org.jurassicraft.client.model.animation.AnimationVelociraptor;
import org.jurassicraft.client.model.animation.AnimationZhenyuanopterus;
import org.jurassicraft.client.model.animation.raptorsquad.AnimationVelociraptorBlue;
import org.jurassicraft.client.model.animation.raptorsquad.AnimationVelociraptorCharlie;
import org.jurassicraft.client.model.animation.raptorsquad.AnimationVelociraptorDelta;
import org.jurassicraft.client.model.animation.raptorsquad.AnimationVelociraptorEcho;
import org.jurassicraft.client.render.block.SpecialRendererActionFigure;
import org.jurassicraft.client.render.block.SpecialRendererDNAExtractor;
import org.jurassicraft.client.render.entity.RenderBluePrint;
import org.jurassicraft.client.render.entity.RenderCageSmall;
import org.jurassicraft.client.render.entity.RenderHelicopter;
import org.jurassicraft.client.render.entity.RenderJurassiCraftSign;
import org.jurassicraft.client.render.entity.RenderPaddockSign;
import org.jurassicraft.client.render.renderdef.RenderDefIndominus;
import org.jurassicraft.client.render.renderdef.RenderDinosaurDefinition;
import org.jurassicraft.common.block.BlockEncasedFossil;
import org.jurassicraft.common.block.BlockFossil;
import org.jurassicraft.common.block.BlockMeta;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.block.tree.EnumType;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.entity.item.EntityBluePrint;
import org.jurassicraft.common.entity.item.EntityCageSmall;
import org.jurassicraft.common.entity.item.EntityJurassiCraftSign;
import org.jurassicraft.common.entity.item.EntityPaddockSign;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.item.bones.ItemFossil;
import org.jurassicraft.common.plant.JCPlantRegistry;
import org.jurassicraft.common.plant.Plant;
import org.jurassicraft.common.tileentity.TileActionFigure;
import org.jurassicraft.common.tileentity.TileDNAExtractor;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class JCRenderingRegistry
{
    private static Map<Dinosaur, RenderDinosaurDefinition> renderDefs = Maps.newHashMap();

    public void preInit()
    {
        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            String dinoName = dino.getName().toLowerCase().replaceAll(" ", "_");

            for (Map.Entry<String, ItemFossil> entry : JCItemRegistry.fossils.entrySet())
            {
                List<Dinosaur> dinosaursForType = JCItemRegistry.fossilDinosaurs.get(entry.getKey());

                if (dinosaursForType.contains(dino))
                {
                    ModelBakery.registerItemVariants(entry.getValue(), new ResourceLocation("jurassicraft:bones/" + dinoName + "_" + entry.getKey()));
                }
            }

            ModelBakery.registerItemVariants(JCItemRegistry.dna, new ResourceLocation("jurassicraft:dna/dna_" + dinoName));
            ModelBakery.registerItemVariants(JCItemRegistry.egg, new ResourceLocation("jurassicraft:egg/egg_" + dinoName));
            ModelBakery.registerItemVariants(JCItemRegistry.dino_meat, new ResourceLocation("jurassicraft:meat/meat_" + dinoName));
            ModelBakery.registerItemVariants(JCItemRegistry.dino_steak, new ResourceLocation("jurassicraft:meat/steak_" + dinoName));
            ModelBakery.registerItemVariants(JCItemRegistry.soft_tissue, new ResourceLocation("jurassicraft:soft_tissue/soft_tissue_" + dinoName));
            ModelBakery.registerItemVariants(JCItemRegistry.syringe, new ResourceLocation("jurassicraft:syringe/syringe_" + dinoName));
            ModelBakery.registerItemVariants(JCItemRegistry.action_figure, new ResourceLocation("jurassicraft:action_figure/action_figure_" + dinoName));
        }

        for (Plant plant : JCPlantRegistry.getPlants())
        {
            String name = plant.getName().toLowerCase().replaceAll(" ", "_");

            ModelBakery.registerItemVariants(JCItemRegistry.plant_dna, new ResourceLocation("jurassicraft:dna/plants/dna_" + name));
        }

        for (EnumDyeColor color : EnumDyeColor.values())
        {
            ModelBakery.registerItemVariants(Item.getItemFromBlock(JCBlockRegistry.cultivate_bottom), new ResourceLocation("jurassicraft:cultivate/cultivate_bottom_" + color.getName().toLowerCase()));
        }

        ModelBakery.registerItemVariants(JCItemRegistry.amber, new ResourceLocation("jurassicraft:amber_aphid"), new ResourceLocation("jurassicraft:amber_mosquito"));

        ModelBakery.registerItemVariants(JCItemRegistry.cage_small, new ResourceLocation("jurassicraft:cage_small"), new ResourceLocation("jurassicraft:cage_small_marine"));
    }

    public void init()
    {
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.achillobator, new AnimationAchillobator(), 1.1F, 0.325F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.anklyosaurus, new AnimationAnkylosaurus(), 2.3F, 0.45F, 0.85F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.brachiosaurus, new AnimationBrachiosaurus(), 2.5F, 0.3F, 1.5F, 0.0F, 0.0F, 1.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.carnotaurus, new AnimationCarnotaurus(), 1.3F, 0.25F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.coelacanth, new AnimationCoelacanth(), 2.1F, 0.55F, 0.35F, 0.0F, 1.0F, -0.25F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.compsognathus, new AnimationCompsognathus(), 0.1F, 0.04F, 1.8F, 0.0F, -12.0F, -0.8F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.dilophosaurus, new AnimationDilophosaurus(), 0.95F, 0.22F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.dunkleosteus, new AnimationDunkleosteus(), 2.1F, 0.55F, 0.35F, 0.0F, 1.0F, -0.25F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.gallimimus, new AnimationGallimimus(), 1.2F, 0.25F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.giganotosaurus, new AnimationGiganotosaurus(), 2.37F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.hypsilophodon, new AnimationHypsilophodon(), 0.65F, 0.2F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDefIndominus(3.4F, 0.4F, 0.65F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.majungasaurus, new AnimationMajungasaurus(), 1.6F, 0.4F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.parasaurolophus, new AnimationParasaurolophus(), 1.9F, 0.4F, 0.65F, 0.0F, 0.75F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.pteranodon, new AnimationPteranodon(), 1.2F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.rugops, new AnimationRugops(), 1.0F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.segisaurus, new AnimationSegisaurus(), 0.55F, 0.2F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.spinosaurus, new AnimationSpinosaurus(), 2.37F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.stegosaurus, new AnimationStegosaurus(), 2.55F, 0.35F, 0.65F, 0.0F, 0.775F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.triceratops, new AnimationTriceratops(), 1.8F, 0.325F, 0.65F, 0.0F, 0.75F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.tyrannosaurus, new AnimationTyrannosaurus(), 2.4F, 0.35F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.velociraptor, new AnimationVelociraptor(), 1.0F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.dodo, new AnimationDodo(), 0.8F, 0.3F, 0.5F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.leptictidium, new AnimationLeptictidium(), 0.6F, 0.25F, 0.45F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.microceratus, new AnimationMicroceratus(), 0.5F, 0.18F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.oviraptor, new AnimationOviraptor(), 0.65F, 0.18F, 0.55F, 0.0F, 0.0F, -0.4F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.apatosaurus, new AnimationApatosaurus(), 2.0F, 0.25F, 1.5F, 0.0F, 0.0F, 0.1F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.othnielia, new AnimationOthnielia(), 0.35F, 0.15F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.dimorphodon, new AnimationDimorphodon(), 0.5F, 0.15F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.tylosaurus, new AnimationTylosaurus(), 2.2F, 0.45F, 0.85F, 0.0F, 0.0F, 1.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.ludodactylus, new AnimationLudodactylus(), 0.8F, 0.35F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.protoceratops, new AnimationProtoceratops(), 1.2F, 0.35F, 0.55F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.tropeognathus, new AnimationTropeognathus(), 1.15F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.leaellynasaura, new AnimationLeaellynasaura(), 0.7F, 0.25F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.herrerasaurus, new AnimationHerrerasaurus(), 1.3F, 0.25F, 0.75F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.velociraptor_blue, new AnimationVelociraptorBlue(), 1.0F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.velociraptor_charlie, new AnimationVelociraptorCharlie(), 1.0F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.velociraptor_delta, new AnimationVelociraptorDelta(), 1.0F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.velociraptor_echo, new AnimationVelociraptorEcho(), 1.0F, 0.3F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.therizinosaurus, new AnimationTherizinosaurus(), 3.5F, 0.55F, 0.55F, 0.0F, 1.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.megapiranha, new AnimationMegapiranha(), 1.0F, 0.15F, 0.65F, 0.0F, 0.65F, -0.25F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.baryonyx, new AnimationBaryonyx(), 1.3F, 0.25F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.cearadactylus, new AnimationCearadactylus(), 1.0F, 0.45F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.mamenchisaurus, new AnimationMamenchisaurus(), 1.8F, 0.2F, 1.5F, 0.0F, 0.0F, -0.5F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.chasmosaurus, new AnimationChasmosaurus(), 1.55F, 0.3F, 0.85F, 0.0F, 0.775F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.corythosaurus, new AnimationCorythosaurus(), 1.75F, 0.35F, 0.65F, 0.0F, 0.775F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.edmontosaurus, new AnimationEdmontosaurus(), 2.65F, 0.45F, 0.65F, 0.0F, 0.775F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.lambeosaurus, new AnimationLambeosaurus(), 2.5F, 0.45F, 0.65F, 0.0F, 0.775F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.metriacanthosaurus, new AnimationMetriacanthosaurus(), 1.0F, 0.25F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.moganopterus, new AnimationMoganopterus(), 0.725F, 0.2F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.ornithomimus, new AnimationOrnithomimus(), 0.9F, 0.25F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.zhenyuanopterus, new AnimationZhenyuanopterus(), 0.7F, 0.25F, 0.65F, 0.0F, 0.0F, 0.0F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.troodon, new AnimationTroodon(), 0.75F, 0.25F, 0.65F, 0.0F, 0.0F, 0.5F));
        registerRenderDef(new RenderDinosaurDefinition(JCEntityRegistry.pachycephalosaurus, new AnimationPachycephalosaurus(), 0.9F, 0.2F, 0.65F, 0.0F, 0.0F, 0.0F));

        GuiAppRegistry.register();

        // Blocks
        ItemModelMesher modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

        int i = 0;

        for (BlockEncasedFossil fossil : JCBlockRegistry.encased_fossils)
        {
            this.registerBlockRenderer(modelMesher, fossil, "encased_fossil_" + i, "inventory");

            i++;
        }

        i = 0;

        for (BlockFossil fossil : JCBlockRegistry.fossils)
        {
            this.registerBlockRenderer(modelMesher, fossil, "fossil_block_" + i, "inventory");

            i++;
        }

        for (i = 0; i < JCBlockRegistry.numOfTrees; i++)
        {
            String name = EnumType.getMetaLookup()[i].getName();
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.leaves[i], name + "_leaves", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.saplings[i], name + "_sapling", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.planks[i], name + "_planks", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.woods[i], name + "_log", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.stairs[i], name + "_stairs", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.slabs[i], name + "_slab", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.doubleSlabs[i], name + "_double_sab", "inventory");
        }

        for (EnumDyeColor color : EnumDyeColor.values())
        {
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.cultivate_bottom, color.ordinal(), "cultivate/cultivate_bottom_" + color.getName().toLowerCase(), "inventory");
        }

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.scaly_tree_fern, "scaly_tree_fern", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.small_royal_fern, "small_royal_fern", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.small_chain_fern, "small_chain_fern", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.small_cycad, "small_cycad", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.bennettitalean_cycadeoidea, "bennettitalean_cycadeoidea", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cry_pansy, "cry_pansy", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cycad_zamites, "cycad_zamites", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dicksonia, "dicksonia", "inventory");

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.reinforced_stone, "reinforced_stone", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.reinforced_bricks, "reinforced_bricks", "inventory");

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cultivate_bottom, "cultivate_bottom", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cultivate_top, "cultivate_top", "inventory");

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.amber_ore, "amber_ore", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.ice_shard, "ice_shard", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cleaning_station, "cleaning_station", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.fossil_grinder, "fossil_grinder", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_sequencer, "dna_sequencer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_synthesizer, "dna_synthesizer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryonic_machine, "embryonic_machine", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryo_calcification_machine, "embryo_calcification_machine", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.incubator, "incubator", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_extractor, "dna_extractor", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.gypsum_stone, "gypsum_stone", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.gypsum_cobblestone, "gypsum_cobblestone", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.gypsum_bricks, "gypsum_bricks", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.action_figure, "action_figure_block", "inventory");


//        this.registerRenderSubBlock(JCBlockRegistry.bPlanks);
    }

    public void postInit()
    {
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            RenderingRegistry.registerEntityRenderingHandler(dino.getDinosaurClass(), renderDefs.get(dino).createRenderFor(renderManager));
        }

        RenderingRegistry.registerEntityRenderingHandler(EntityCageSmall.class, new RenderCageSmall().createRenderFor(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityBluePrint.class, new RenderBluePrint().createRenderFor(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityPaddockSign.class, new RenderPaddockSign().createRenderFor(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityJurassiCraftSign.class, new RenderJurassiCraftSign().createRenderFor(renderManager));
        RenderingRegistry.registerEntityRenderingHandler(EntityHelicopterBase.class, new RenderHelicopter().createRenderFor(renderManager));

        ClientRegistry.bindTileEntitySpecialRenderer(TileDNAExtractor.class, new SpecialRendererDNAExtractor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileActionFigure.class, new SpecialRendererActionFigure());

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ItemModelMesher modelMesher = renderItem.getItemModelMesher();

        // Items
        this.registerItemRenderer(modelMesher, JCItemRegistry.tracker, "tracker", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.plant_cells_petri_dish, "plant_cells_petri_dish", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.plant_cells, "plant_cells", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.plant_callus, "plant_callus", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.needle, "needle", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.growth_serum, "growth_serum", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.iron_rod, "iron_rod", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.iron_blades, "iron_blades", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.cage_small, 0, "cage_small", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.cage_small, 1, "cage_small_marine", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.petri_dish, "petri_dish", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.amber, "amber", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.plaster_and_bandage, "plaster_and_bandage", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.spawn_egg, "dino_spawn_egg", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.paleo_pad, "paleo_pad", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.blue_print, "blue_print", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.paddock_sign, "paddock_sign", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.jc_sign, "jurassicraft_sign", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.empty_test_tube, "empty_test_tube", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.empty_syringe, "empty_syringe", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.entityRemover, "entity_remover", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.storage_disc, "storage_disc", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.disc_reader, "disc_reader", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.laser, "laser", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.dna_base, "dna_base_material", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.sea_lamprey, "sea_lamprey", "inventory");

        this.registerItemRenderer(modelMesher, JCItemRegistry.amber, 0, "amber_mosquito", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.amber, 1, "amber_aphid", "inventory");

        this.registerItemRenderer(modelMesher, JCItemRegistry.helicopter_spawner, "helicopter_spawner", "inventory");

        this.registerItemRenderer(modelMesher, JCItemRegistry.disc_jurassicraft_theme, "disc_jurassicraft_theme", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.disc_dont_move_a_muscle, "disc_dont_move_a_muscle", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.disc_troodons_and_raptors, "disc_troodons_and_raptors", "inventory");

        this.registerItemRenderer(modelMesher, JCItemRegistry.amber_keychain, "amber_keychain", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.amber_cane, "amber_cane", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.mr_dna_keychain, "mr_dna_keychain", "inventory");

        this.registerItemRenderer(modelMesher, JCItemRegistry.dino_scanner, "dino_scanner", "inventory");

        this.registerItemRenderer(modelMesher, JCItemRegistry.basic_circuit, "basic_circuit", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.advanced_circuit, "advanced_circuit", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.iron_nugget, "iron_nugget", "inventory");

        int meta = 0;

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            String dinoName = dino.getName().toLowerCase().replaceAll(" ", "_");

            for (Map.Entry<String, ItemFossil> entry : JCItemRegistry.fossils.entrySet())
            {
                List<Dinosaur> dinosaursForType = JCItemRegistry.fossilDinosaurs.get(entry.getKey());

                if (dinosaursForType.contains(dino))
                {
                    this.registerItemRenderer(modelMesher, entry.getValue(), meta, "bones/" + dinoName + "_" + entry.getKey(), "inventory");
                }
            }

            this.registerItemRenderer(modelMesher, JCItemRegistry.dna, meta, "dna/dna_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.egg, meta, "egg/egg_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.dino_meat, meta, "meat/meat_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.dino_steak, meta, "meat/steak_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.soft_tissue, meta, "soft_tissue/soft_tissue_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.syringe, meta, "syringe/syringe_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.action_figure, meta, "action_figure/action_figure_" + dinoName, "inventory");

            meta++;
        }

        meta = 0;

        for (Plant plant : JCPlantRegistry.getPlants())
        {
            String name = plant.getName().toLowerCase().replaceAll(" ", "_");

            this.registerItemRenderer(modelMesher, JCItemRegistry.plant_dna, meta, "dna/plants/dna_" + name, "inventory");

            meta++;
        }
    }

    /**
     * Registers an item renderer
     */
    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, final String path, final String type)
    {
        itemModelMesher.register(item, new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(JurassiCraft.MODID + ":" + path, type);
            }
        });
    }

    /**
     * Registers an item renderer with metadata
     */
    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, int meta, String path, String type)
    {
        itemModelMesher.register(item, meta, new ModelResourceLocation(JurassiCraft.MODID + ":" + path, type));
    }

    /**
     * Registers an block renderer with metadata
     */
    public void registerBlockRenderer(ItemModelMesher itemModelMesher, Block block, int meta, String path, String type)
    {
        itemModelMesher.register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(JurassiCraft.MODID + ":" + path, type));
    }

    /**
     * Registers a block renderer
     */
    public void registerBlockRenderer(ItemModelMesher itemModelMesher, Block block, final String path, final String type)
    {
        itemModelMesher.register(Item.getItemFromBlock(block), new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(JurassiCraft.MODID + ":" + path, type);
            }
        });
    }

    public void registerRenderSubBlock(Block block)
    {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            Item item = Item.getItemFromBlock(block);
            BlockMeta bm = (BlockMeta) block;
            for (int i = 0; i < bm.getSubBlocks(); i++)
            {
                String path = JurassiCraft.MODID + ":metablock/" + bm.getPath() + "/" + bm.getUnlocalizedName().substring(5) + "_" + i;
                ModelBakery.registerItemVariants(item, new ResourceLocation(path));
                ModelResourceLocation mrs = new ModelResourceLocation(path, "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i, mrs);
            }
        }
    }

    private void registerRenderDef(RenderDinosaurDefinition renderDef)
    {
        renderDefs.put(renderDef.getDinosaur(), renderDef);
    }

    public RenderDinosaurDefinition getRenderDef(Dinosaur dino)
    {
        return renderDefs.get(dino);
    }
}
