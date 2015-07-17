package net.timeless.jurassicraft.common.entity.base;

import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.dinosaur.DinosaurAchillobator;
import net.timeless.jurassicraft.common.dinosaur.DinosaurAnkylosaurus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurBrachiosaurus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurCarnotaurus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurCoelacanth;
import net.timeless.jurassicraft.common.dinosaur.DinosaurCompsognathus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurDilophosaurus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurDunkleosteus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurGallimimus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurGiganotosaurus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurIndominusRex;
import net.timeless.jurassicraft.common.dinosaur.DinosaurMajungasaurus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurParasaurolophus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurPteranodon;
import net.timeless.jurassicraft.common.dinosaur.DinosaurRugops;
import net.timeless.jurassicraft.common.dinosaur.DinosaurSegisaurus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurSpinosaurus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurStegosaurus;
import net.timeless.jurassicraft.common.dinosaur.DinosaurTriceratops;
import net.timeless.jurassicraft.common.dinosaur.DinosaurTyrannosaurusRex;
import net.timeless.jurassicraft.common.dinosaur.DinosaurVelociraptor;
import net.timeless.jurassicraft.common.entity.item.EntityBluePrint;
import net.timeless.jurassicraft.common.entity.item.EntityJurassiCraftSign;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

import com.google.common.collect.Lists;

public class JCEntityRegistry
{
    private static List<Dinosaur> dinosaurs = Lists.newArrayList();
    private static HashMap<EnumTimePeriod, List<Dinosaur>> dinosaursFromPeriod = new HashMap<>();

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
    public static final Dinosaur indominus_rex = new DinosaurIndominusRex();
    public static final Dinosaur majungasaurus = new DinosaurMajungasaurus();
    public static final Dinosaur parasaurolophus = new DinosaurParasaurolophus();
    public static final Dinosaur pteranodon = new DinosaurPteranodon();
    public static final Dinosaur rugops = new DinosaurRugops();
    public static final Dinosaur segisaurus = new DinosaurSegisaurus();
    public static final Dinosaur spinosaurus = new DinosaurSpinosaurus();
    public static final Dinosaur stegosaurus = new DinosaurStegosaurus();
    public static final Dinosaur triceratops = new DinosaurTriceratops();
    public static final Dinosaur tyrannosaurus_rex = new DinosaurTyrannosaurusRex();
    public static final Dinosaur velociraptor = new DinosaurVelociraptor();

    public void register()
    {
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
        registerDinosaurType(indominus_rex);
        registerDinosaurType(majungasaurus);
        registerDinosaurType(parasaurolophus);
        registerDinosaurType(pteranodon);
        registerDinosaurType(rugops);
        registerDinosaurType(segisaurus);
        registerDinosaurType(spinosaurus);
        registerDinosaurType(stegosaurus);
        registerDinosaurType(triceratops);
        registerDinosaurType(tyrannosaurus_rex);
        registerDinosaurType(velociraptor);

        registerEntity(EntityBluePrint.class, "Blueprint");
        registerEntity(EntityJurassiCraftSign.class, "JurassiCraft Sign");
        registerEntity(EntityCarcass.class, "Dinosaur Carcass");
        // Always register a new dinosaur after last one in list, otherwise all
        // items with metadata will be shifted by one (all dinosaurs will change
        // form D:) (UNLESS it is before the release of JC2) I want to change the way IDs work so this will not be the case.

        for (Dinosaur dinosaur : dinosaurs)
            registerDinosaur(dinosaur);
    }

    public void registerDinosaur(Dinosaur dinosaur)
    {
        if (dinosaur.shouldRegister())
            registerEntity(dinosaur.getDinosaurClass(), dinosaur.getName());
    }

    private void registerEntity(Class<? extends Entity> entity, String name)
    {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();

        String formattedName = name.toLowerCase().replaceAll(" ", "_");

        EntityRegistry.registerGlobalEntityID(entity, formattedName, entityId);
        EntityRegistry.registerModEntity(entity, formattedName, entityId, JurassiCraft.instance, 1024, 1, true);
    }

    public static void registerDinosaurType(Dinosaur dinosaur)
    {
        dinosaurs.add(dinosaur);
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
            List<Dinosaur> newDinoList = Lists.newArrayList();
            newDinoList.add(dinosaur);

            dinosaursFromPeriod.put(period, newDinoList);
        }
    }

    public static Dinosaur getDinosaurById(int id)
    {
        if (id > dinosaurs.size() - 1)
            id = 0;

        return dinosaurs.get(id);
    }

    public static int getDinosaurId(Dinosaur dinosaur)
    {
        return dinosaurs.indexOf(dinosaur);
    }

    public static List<Dinosaur> getDinosaurs()
    {
        return dinosaurs;
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
