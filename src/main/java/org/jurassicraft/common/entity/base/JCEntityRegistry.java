package org.jurassicraft.common.entity.base;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.api.IHybrid;
import org.jurassicraft.common.configuration.JCConfigurations;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.dinosaur.DinosaurAchillobator;
import org.jurassicraft.common.dinosaur.DinosaurAnkylosaurus;
import org.jurassicraft.common.dinosaur.DinosaurApatosaurus;
import org.jurassicraft.common.dinosaur.DinosaurBaryonyx;
import org.jurassicraft.common.dinosaur.DinosaurBrachiosaurus;
import org.jurassicraft.common.dinosaur.DinosaurCarnotaurus;
import org.jurassicraft.common.dinosaur.DinosaurCearadactylus;
import org.jurassicraft.common.dinosaur.DinosaurChasmosaurus;
import org.jurassicraft.common.dinosaur.DinosaurCoelacanth;
import org.jurassicraft.common.dinosaur.DinosaurCompsognathus;
import org.jurassicraft.common.dinosaur.DinosaurCorythosaurus;
import org.jurassicraft.common.dinosaur.DinosaurDilophosaurus;
import org.jurassicraft.common.dinosaur.DinosaurDimorphodon;
import org.jurassicraft.common.dinosaur.DinosaurDodo;
import org.jurassicraft.common.dinosaur.DinosaurDunkleosteus;
import org.jurassicraft.common.dinosaur.DinosaurEdmontosaurus;
import org.jurassicraft.common.dinosaur.DinosaurGallimimus;
import org.jurassicraft.common.dinosaur.DinosaurGiganotosaurus;
import org.jurassicraft.common.dinosaur.DinosaurHerrerasaurus;
import org.jurassicraft.common.dinosaur.DinosaurHypsilophodon;
import org.jurassicraft.common.dinosaur.DinosaurIndominus;
import org.jurassicraft.common.dinosaur.DinosaurLambeosaurus;
import org.jurassicraft.common.dinosaur.DinosaurLeaellynasaura;
import org.jurassicraft.common.dinosaur.DinosaurLeptictidium;
import org.jurassicraft.common.dinosaur.DinosaurLudodactylus;
import org.jurassicraft.common.dinosaur.DinosaurMajungasaurus;
import org.jurassicraft.common.dinosaur.DinosaurMamenchisaurus;
import org.jurassicraft.common.dinosaur.DinosaurMegapiranha;
import org.jurassicraft.common.dinosaur.DinosaurMetriacanthosaurus;
import org.jurassicraft.common.dinosaur.DinosaurMicroceratus;
import org.jurassicraft.common.dinosaur.DinosaurMoganopterus;
import org.jurassicraft.common.dinosaur.DinosaurOrnithomimus;
import org.jurassicraft.common.dinosaur.DinosaurOthnielia;
import org.jurassicraft.common.dinosaur.DinosaurOviraptor;
import org.jurassicraft.common.dinosaur.DinosaurPachycephalosaurus;
import org.jurassicraft.common.dinosaur.DinosaurParasaurolophus;
import org.jurassicraft.common.dinosaur.DinosaurProtoceratops;
import org.jurassicraft.common.dinosaur.DinosaurPteranodon;
import org.jurassicraft.common.dinosaur.DinosaurRugops;
import org.jurassicraft.common.dinosaur.DinosaurSegisaurus;
import org.jurassicraft.common.dinosaur.DinosaurSpinosaurus;
import org.jurassicraft.common.dinosaur.DinosaurStegosaurus;
import org.jurassicraft.common.dinosaur.DinosaurTherizinosaurus;
import org.jurassicraft.common.dinosaur.DinosaurTriceratops;
import org.jurassicraft.common.dinosaur.DinosaurTroodon;
import org.jurassicraft.common.dinosaur.DinosaurTropeognathus;
import org.jurassicraft.common.dinosaur.DinosaurTylosaurus;
import org.jurassicraft.common.dinosaur.DinosaurTyrannosaurus;
import org.jurassicraft.common.dinosaur.DinosaurVelociraptor;
import org.jurassicraft.common.dinosaur.DinosaurVelociraptorBlue;
import org.jurassicraft.common.dinosaur.DinosaurVelociraptorCharlie;
import org.jurassicraft.common.dinosaur.DinosaurVelociraptorDelta;
import org.jurassicraft.common.dinosaur.DinosaurVelociraptorEcho;
import org.jurassicraft.common.dinosaur.DinosaurZhenyuanopterus;
import org.jurassicraft.common.entity.item.EntityBluePrint;
import org.jurassicraft.common.entity.item.EntityCageSmall;
import org.jurassicraft.common.entity.item.EntityJurassiCraftSign;
import org.jurassicraft.common.entity.item.EntityPaddockSign;
import org.jurassicraft.common.period.EnumTimePeriod;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;
import org.jurassicraft.common.vehicles.helicopter.modules.EntityHelicopterSeat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JCEntityRegistry
{
    private static final List<Dinosaur> dinosaurs = new ArrayList<>();
    private static final HashMap<EnumTimePeriod, List<Dinosaur>> dinosaursFromPeriod = new HashMap<>();

    public static final Dinosaur dodo = new DinosaurDodo();
    public static final Dinosaur achillobator = new DinosaurAchillobator();
    public static final Dinosaur anklyosaurus = new DinosaurAnkylosaurus();
    public static final Dinosaur brachiosaurus = new DinosaurBrachiosaurus();
    public static final Dinosaur carnotaurus = new DinosaurCarnotaurus();
    public static final Dinosaur coelacanth = new DinosaurCoelacanth();
    public static final Dinosaur compsognathus = new DinosaurCompsognathus();
    public static final Dinosaur dilophosaurus = new DinosaurDilophosaurus();
    public static final Dinosaur dunkleosteus = new DinosaurDunkleosteus();
    public static final Dinosaur gallimimus = new DinosaurGallimimus();
    public static final Dinosaur giganotosaurus = new DinosaurGiganotosaurus();
    public static final Dinosaur hypsilophodon = new DinosaurHypsilophodon();
    public static final Dinosaur indominus = new DinosaurIndominus();
    public static final Dinosaur majungasaurus = new DinosaurMajungasaurus();
    public static final Dinosaur parasaurolophus = new DinosaurParasaurolophus();
    public static final Dinosaur pteranodon = new DinosaurPteranodon();
    public static final Dinosaur rugops = new DinosaurRugops();
    public static final Dinosaur segisaurus = new DinosaurSegisaurus();
    public static final Dinosaur spinosaurus = new DinosaurSpinosaurus();
    public static final Dinosaur stegosaurus = new DinosaurStegosaurus();
    public static final Dinosaur triceratops = new DinosaurTriceratops();
    public static final Dinosaur tyrannosaurus = new DinosaurTyrannosaurus();
    public static final Dinosaur velociraptor = new DinosaurVelociraptor();
    public static final Dinosaur leptictidium = new DinosaurLeptictidium();
    public static final Dinosaur microceratus = new DinosaurMicroceratus();
    public static final Dinosaur oviraptor = new DinosaurOviraptor();
    public static final Dinosaur apatosaurus = new DinosaurApatosaurus();
    public static final Dinosaur othnielia = new DinosaurOthnielia();
    public static final Dinosaur dimorphodon = new DinosaurDimorphodon();
    public static final Dinosaur tylosaurus = new DinosaurTylosaurus();
    public static final Dinosaur ludodactylus = new DinosaurLudodactylus();
    public static final Dinosaur protoceratops = new DinosaurProtoceratops();
    public static final Dinosaur tropeognathus = new DinosaurTropeognathus();
    public static final Dinosaur leaellynasaura = new DinosaurLeaellynasaura();
    public static final Dinosaur herrerasaurus = new DinosaurHerrerasaurus();
    public static final Dinosaur velociraptor_blue = new DinosaurVelociraptorBlue();
    public static final Dinosaur velociraptor_delta = new DinosaurVelociraptorDelta();
    public static final Dinosaur velociraptor_charlie = new DinosaurVelociraptorCharlie();
    public static final Dinosaur velociraptor_echo = new DinosaurVelociraptorEcho();
    public static final Dinosaur therizinosaurus = new DinosaurTherizinosaurus();
    public static final Dinosaur megapiranha = new DinosaurMegapiranha();
    public static final Dinosaur baryonyx = new DinosaurBaryonyx();
    public static final Dinosaur cearadactylus = new DinosaurCearadactylus();
    public static final Dinosaur mamenchisaurus = new DinosaurMamenchisaurus();
    public static final Dinosaur chasmosaurus = new DinosaurChasmosaurus();
    public static final Dinosaur corythosaurus = new DinosaurCorythosaurus();
    public static final Dinosaur edmontosaurus = new DinosaurEdmontosaurus();
    public static final Dinosaur lambeosaurus = new DinosaurLambeosaurus();
    public static final Dinosaur metriacanthosaurus = new DinosaurMetriacanthosaurus();
    public static final Dinosaur moganopterus = new DinosaurMoganopterus();
    public static final Dinosaur ornithomimus = new DinosaurOrnithomimus();
    public static final Dinosaur zhenyuanopterus = new DinosaurZhenyuanopterus();
    public static final Dinosaur troodon = new DinosaurTroodon();
    public static final Dinosaur pachycephalosaurus = new DinosaurPachycephalosaurus();

    private int entityId;

    public static List<Dinosaur> getDinosaursFromSeaLampreys()
    {
        List<Dinosaur> marineDinos = new ArrayList<>();

        for (Dinosaur dino : getRegisteredDinosaurs())
        {
            if (dino.isMarineAnimal() && !(dino instanceof IHybrid))
            {
                marineDinos.add(dino);
            }
        }

        return marineDinos;
    }

    public void register()
    {
        registerDinosaurType(velociraptor);
        registerDinosaurType(achillobator);
        registerDinosaurType(anklyosaurus);
        registerDinosaurType(brachiosaurus);
        registerDinosaurType(carnotaurus);
        registerDinosaurType(coelacanth);
        registerDinosaurType(compsognathus);
        registerDinosaurType(dilophosaurus);
        registerDinosaurType(dunkleosteus);
        registerDinosaurType(gallimimus);
        registerDinosaurType(giganotosaurus);
        registerDinosaurType(indominus);
        registerDinosaurType(majungasaurus);
        registerDinosaurType(parasaurolophus);
        registerDinosaurType(pteranodon);
        registerDinosaurType(rugops);
        registerDinosaurType(segisaurus);
        registerDinosaurType(spinosaurus);
        registerDinosaurType(stegosaurus);
        registerDinosaurType(triceratops);
        registerDinosaurType(tyrannosaurus);
        registerDinosaurType(hypsilophodon);
        registerDinosaurType(dodo);
        registerDinosaurType(leptictidium);
        registerDinosaurType(microceratus);
        registerDinosaurType(oviraptor);
        registerDinosaurType(apatosaurus);
        registerDinosaurType(othnielia);
        registerDinosaurType(dimorphodon);
        registerDinosaurType(tylosaurus);
        registerDinosaurType(ludodactylus);
        registerDinosaurType(protoceratops);
        registerDinosaurType(tropeognathus);
        registerDinosaurType(leaellynasaura);
        registerDinosaurType(herrerasaurus);
        registerDinosaurType(velociraptor_blue);
        registerDinosaurType(velociraptor_charlie);
        registerDinosaurType(velociraptor_delta);
        registerDinosaurType(velociraptor_echo);
        registerDinosaurType(therizinosaurus);
        registerDinosaurType(megapiranha);
        registerDinosaurType(baryonyx);
        registerDinosaurType(cearadactylus);
        registerDinosaurType(mamenchisaurus);
        registerDinosaurType(chasmosaurus);
        registerDinosaurType(corythosaurus);
        registerDinosaurType(edmontosaurus);
        registerDinosaurType(lambeosaurus);
        registerDinosaurType(metriacanthosaurus);
        registerDinosaurType(moganopterus);
        registerDinosaurType(ornithomimus);
        registerDinosaurType(zhenyuanopterus);
        registerDinosaurType(troodon);
        registerDinosaurType(pachycephalosaurus);

        registerEntity(EntityBluePrint.class, "Blueprint");
        registerEntity(EntityJurassiCraftSign.class, "JurassiCraft Sign");
        registerEntity(EntityCageSmall.class, "Small Dinosaur Cage");
        registerEntity(EntityPaddockSign.class, "Paddock Sign");

        registerEntity(EntityHelicopterBase.class, "Helicopter base");
        registerEntity(EntityHelicopterSeat.class, "Helicopter seat Do not spawn please, like really don't");

        for (Dinosaur dinosaur : dinosaurs)
        {
            registerDinosaur(dinosaur);
        }
    }

    private void registerDinosaur(Dinosaur dinosaur)
    {
        if (dinosaur.shouldRegister())
        {
            Class<? extends EntityDinosaur> clazz = dinosaur.getDinosaurClass();

            registerEntity(clazz, dinosaur.getName());

            if (dinosaur.shouldRegister() && !(dinosaur instanceof IHybrid) && JCConfigurations.spawnJurassiCraftMobsNaturally())
            {
                if (dinosaur.isMarineAnimal())
                {
                    EntityRegistry.addSpawn(clazz, 5, 1, 2,  EnumCreatureType.WATER_CREATURE, BiomeGenBase.ocean, BiomeGenBase.deepOcean, BiomeGenBase.river);
                }
                else
                {
                    EntityRegistry.addSpawn(clazz, 5, 1, 2, EnumCreatureType.CREATURE, Iterators.toArray(Iterators.filter(Iterators.forArray(BiomeGenBase.getBiomeGenArray()), Predicates.notNull()), BiomeGenBase.class));
                }
            }
        }
    }

    private void registerEntity(Class<? extends Entity> entity, String name)
    {
        String formattedName = name.toLowerCase().replaceAll(" ", "_");

        EntityRegistry.registerModEntity(entity, formattedName, entityId++, JurassiCraft.instance, 1024, 1, true);
    }

    private static void registerDinosaurType(Dinosaur dinosaur)
    {
        dinosaur.init();

        dinosaurs.add(dinosaur);

        if (!(dinosaur instanceof IHybrid) && dinosaur.shouldRegister())
        {
            EnumTimePeriod period = dinosaur.getPeriod();

            List<Dinosaur> dinoList = dinosaursFromPeriod.get(period);

            if (dinoList != null)
            {
                dinoList.add(dinosaur);

                dinosaursFromPeriod.remove(period);
                dinosaursFromPeriod.put(period, dinoList);
            }
            else
            {
                List<Dinosaur> newDinoList = new ArrayList<>();
                newDinoList.add(dinosaur);

                dinosaursFromPeriod.put(period, newDinoList);
            }
        }
    }

    public static Dinosaur getDinosaurById(int id)
    {
        if (id >= dinosaurs.size() || id < 0)
        {
            return null;
        }

        return dinosaurs.get(id);
    }

    public static int getDinosaurId(Dinosaur dinosaur)
    {
        return dinosaurs.indexOf(dinosaur);
    }

    public static List<Dinosaur> getDinosaursFromAmber()
    {
        List<Dinosaur> amberDinos = new ArrayList<>();

        for (Dinosaur dino : getRegisteredDinosaurs())
        {
            if (!dino.isMarineAnimal() && !(dino instanceof IHybrid))
            {
                amberDinos.add(dino);
            }
        }

        return amberDinos;
    }

    public static List<Dinosaur> getDinosaurs()
    {
        return dinosaurs;
    }

    public static List<Dinosaur> getRegisteredDinosaurs()
    {
        List<Dinosaur> reg = new ArrayList<>();

        for (Dinosaur dino : dinosaurs)
        {
            if (dino.shouldRegister())
            {
                reg.add(dino);
            }
        }

        return reg;
    }

    public static List<Dinosaur> getDinosaursFromPeriod(EnumTimePeriod period)
    {
        return dinosaursFromPeriod.get(period);
    }

    public static Dinosaur getDinosaurByClass(Class<? extends EntityDinosaur> clazz)
    {
        for (Dinosaur dino : dinosaurs)
        {
            if (dino.getDinosaurClass().equals(clazz))
            {
                return dino;
            }
        }

        return null;
    }
}
