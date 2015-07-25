package net.reuxertz.ecoapi.ecology;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.reuxertz.ecoai.ai.modules.AIGather;
import net.reuxertz.ecoai.ai.modules.AIHunt;
import net.reuxertz.ecoapi.EcoAPI;
import net.reuxertz.ecoapi.ecology.role.*;
import net.reuxertz.ecoapi.item.BaseItem;

import java.util.*;

public class EcoFauna
{
    //Ecology
    private static HashMap<Class, IEcologicalRole> ecoClassRoleRegistry = new HashMap<Class, IEcologicalRole>();
    private static HashMap<Class, List<Class>> ecoClassAIRegistry = new HashMap<Class, List<Class>>();

    //Entity Resource Registry
    private static final HashMap<Class, List<ItemStack>> classToDropRegistry = Maps.newHashMap();
    static
    {        //Create primary classes
        IEcologicalRole roleCarnivore = EcoAPI.carnivore;
        IEcologicalRole roleHerbivore = EcoAPI.herbivore;

        EcoAPI.registerEntityClassDropItems(EntityRabbit.class,     new ItemStack[]     { new ItemStack(Items.rabbit)   });
        EcoAPI.registerEntityClassDropItems(EntityChicken.class,    new ItemStack[]     { new ItemStack(Items.chicken)  });
        EcoAPI.registerEntityClassDropItems(EntitySheep.class,      new ItemStack[]     { new ItemStack(Items.mutton)   });
        EcoAPI.registerEntityClassDropItems(EntityPig.class,        new ItemStack[]     { new ItemStack(Items.porkchop) });
        EcoAPI.registerEntityClassDropItems(EntityCow.class,        new ItemStack[]     { new ItemStack(Items.beef)     });

        roleCarnivore.addFoodItem(new ItemStack(Items.rabbit));
        roleCarnivore.addFoodItem(new ItemStack(Items.chicken));
        roleCarnivore.addFoodItem(new ItemStack(Items.mutton));
        roleCarnivore.addFoodItem(new ItemStack(Items.porkchop));
        roleCarnivore.addFoodItem(new ItemStack(Items.beef));

        List<IEcologicalRole> omnivoreSubRoles = new ArrayList<IEcologicalRole>();
        omnivoreSubRoles.add(roleCarnivore);
        omnivoreSubRoles.add(roleHerbivore);
        BaseEcologicalRoleCompound roleOmnivore = new BaseEcologicalRoleCompound("omnivore",  omnivoreSubRoles);

        //Add Primary Ecological Roles
        List<IEcologicalRole> r = Lists.newArrayList();
        r.add(roleCarnivore);
        r.add(roleHerbivore);
        r.add(roleOmnivore);

        //Assign Interfaces to roles
        EcoFauna.registerEntityClassToRole(ICarnivore.class, roleCarnivore);
        EcoFauna.registerEntityClassToRole(IHerbivore.class, roleHerbivore);
        EcoFauna.registerEntityClassToRole(IOmnivore.class, roleOmnivore);

        //Assign Interfaces to AIs
        List<Class> carn = new ArrayList<Class>();
        carn.add(AIHunt.class);
        EcoFauna.registerClassToAI(ICarnivore.class, carn);

        List<Class> herb = new ArrayList<Class>();
        herb.add(AIGather.class);
        EcoFauna.registerClassToAI(IOmnivore.class, herb);

        List<Class> omn = new ArrayList<Class>();
        omn.add(AIHunt.class);
        omn.add(AIGather.class);

        EcoFauna.registerClassToAI(IOmnivore.class, omn);
        //EcoFauna.registerClassToAI(EntityHuman.class, Arrays.asList(AILumberjack.class));
    }

    //Roles
    public static IEcologicalRole getRole(EntityCreature e)
    {
        Set k = EcoFauna.ecoClassRoleRegistry.keySet();
        for (Object o: k)
            if (((Class)o).isInstance(e))
                return EcoFauna.ecoClassRoleRegistry.get(o);

        return null;
    }
    public static List<Class> getDefaultAIObjs(EntityCreature e)
    {
        Set k = EcoFauna.ecoClassAIRegistry.keySet();
        List<Class> r = new ArrayList<Class>();
        boolean allowInterface = false;
        while (true)
        {
            for (Object o : k)
            {
                Class c = (Class) o;
                //r.addAll(EcoFauna.ecoClassAIRegistry.get(o));
                if (allowInterface)
                {
                    if (c.isInterface() && c.isInstance(e))
                        r.addAll(EcoFauna.ecoClassAIRegistry.get(o));
                }
                else
                {
                    if (!c.isInterface() && c.isInstance(e))
                        r.addAll(EcoFauna.ecoClassAIRegistry.get(o));
                }
            }

            if (!allowInterface)
            {
                if (r.size() > 0)
                    break;

                allowInterface = true;
                continue;
            }

            break;
        }

        return r;
    }
    public static void registerEntityClassToRole(Class c, IEcologicalRole role)
    {
        if (role == null || c == null)
            return;

        if (EcoFauna.ecoClassRoleRegistry.containsKey(c))
            EcoFauna.ecoClassRoleRegistry.remove(c);

        EcoFauna.ecoClassRoleRegistry.put(c, role);
    }
    public static void registerClassToAI(Class c, List<Class> objs)
    {
        if (objs == null || objs.size() == 0 || c == null)
            return;

        if (EcoFauna.ecoClassAIRegistry.containsKey(c))
            EcoFauna.ecoClassAIRegistry.remove(c);

        EcoFauna.ecoClassAIRegistry.put(c, objs);
    }

    //Entity Drop Registry
    public static void addToClassDropRegistry(Class c, ItemStack item, boolean preClearClass)
    {
        if (preClearClass)
            EcoFauna.classToDropRegistry.remove(c);

        List<ItemStack> nl;

        if (EcoFauna.classToDropRegistry.containsKey(c))
            nl = EcoFauna.classToDropRegistry.get(c);
        else
            nl = new ArrayList<ItemStack>();

        for (ItemStack s : nl)
            if (BaseItem.itemsEqual(s, item))
                return;

        nl.add(item);
        EcoFauna.classToDropRegistry.put(c, nl);
    }

    public static void addToClassDropRegistry(Class c, ItemStack[] item, boolean preClearClass)
    {
        if (preClearClass)
            EcoFauna.classToDropRegistry.remove(c);

        for (ItemStack i : item) EcoFauna.addToClassDropRegistry(c, i, false);
    }

    public static void addToClassDropRegistry(Class c, ItemStack[] item)
    {
        EcoFauna.addToClassDropRegistry(c, item, false);
    }

    public static List<ItemStack> getDropItemsByEntity(Entity e)
    {
        List<ItemStack> r = new ArrayList<ItemStack>();//EcoFauna.classToDropRegistry.get(c);
        Set<Class> s = EcoFauna.classToDropRegistry.keySet();
        for (Class ci: s)
        {
            if (ci.isInstance(e))
                r.addAll(EcoFauna.classToDropRegistry.get(ci));
        }

        if (r != null && r.size() != 0)
            return r;
        else
            return null;
    }
    public static List<ItemStack> getDropItemsByItemClass(Class itemType)
    {
        List<ItemStack> r = new ArrayList<>();//EcoFauna.classToDropRegistry.get(c);
        Collection<List<ItemStack>> v = EcoFauna.classToDropRegistry.values();
        for (int i = 0; v.iterator().hasNext(); i++)
        {
            List<ItemStack> ci = v.iterator().next();
            for (ItemStack is: ci)
            {
                if (itemType.isInstance(is.getItem()))
                    r.add(is);
            }
        }

        if (r != null && r.size() != 0)
            return r;
        else
            return null;
    }

}
