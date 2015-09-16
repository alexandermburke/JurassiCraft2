package net.reuxertz.ecoapi.ecology.role;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RoleHerbivore extends BaseEcologicalRole
{
    public String getName()
    {
        return "herbivore";
    }

    public RoleHerbivore()
    {
        this.addFoodItem(new ItemStack(Items.apple));
    }
}
