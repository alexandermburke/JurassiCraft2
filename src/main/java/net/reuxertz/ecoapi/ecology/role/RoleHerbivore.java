package net.reuxertz.ecoapi.ecology.role;

import com.google.common.collect.Lists;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.List;

public class RoleHerbivore extends BaseEcologicalRole
{
    public String getName()
    {
        return "herbivore";
    }

    public List<ItemStack> getFoodItems()
    {
        return Lists.newArrayList(new ItemStack(Items.wheat), new ItemStack(Items.wheat_seeds), new ItemStack(Items.apple));
    }
}
