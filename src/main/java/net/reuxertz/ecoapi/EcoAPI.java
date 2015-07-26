package net.reuxertz.ecoapi;

import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.reuxertz.ecoapi.ecology.EcoFauna;
import net.reuxertz.ecoapi.ecology.role.IEcologicalRole;
import net.reuxertz.ecoapi.ecology.role.RoleCarnivore;
import net.reuxertz.ecoapi.ecology.role.RoleHerbivore;

public class EcoAPI
{
    //Commonly used Fields
    public static final IEcologicalRole carnivore = new RoleCarnivore(), herbivore = new RoleHerbivore();

    //Entity Functions
    public static void registerEntityClassDropItems(Class entityClass, ItemStack[] dropItems)
    {
        EcoFauna.addToClassDropRegistry(entityClass, dropItems);
    }

    public static void registerFoodItem(IEcologicalRole role, ItemStack stack)
    {
        EcoAPI.registerEntityClassDropItems(EntityRabbit.class,     new ItemStack[]     { stack.copy()   });
    }
}
