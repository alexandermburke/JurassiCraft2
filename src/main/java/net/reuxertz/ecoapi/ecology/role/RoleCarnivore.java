package net.reuxertz.ecoapi.ecology.role;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RoleCarnivore extends BaseEcologicalRole
{
    public String getName()
    {
        return "carnivore";
    }

    public RoleCarnivore()
    {
        this.addFoodItem(new ItemStack(Items.fish));
        this.addFoodItem(new ItemStack(Items.rabbit));
        this.addFoodItem(new ItemStack(Items.chicken));
        this.addFoodItem(new ItemStack(Items.mutton));
        this.addFoodItem(new ItemStack(Items.porkchop));
        this.addFoodItem(new ItemStack(Items.beef));

        this.addFoodItem(new ItemStack(Items.cooked_fish));
        this.addFoodItem(new ItemStack(Items.cooked_rabbit));
        this.addFoodItem(new ItemStack(Items.cooked_chicken));
        this.addFoodItem(new ItemStack(Items.cooked_mutton));
        this.addFoodItem(new ItemStack(Items.cooked_porkchop));
        this.addFoodItem(new ItemStack(Items.cooked_beef));
    }

}