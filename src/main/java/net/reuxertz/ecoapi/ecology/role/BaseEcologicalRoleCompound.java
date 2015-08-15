package net.reuxertz.ecoapi.ecology.role;

import net.minecraft.item.ItemStack;
import net.reuxertz.ecoapi.item.BaseItem;

import java.util.ArrayList;
import java.util.List;

public class BaseEcologicalRoleCompound extends BaseEcologicalRole
{
    protected List<IEcologicalRole> roles = new ArrayList<IEcologicalRole>();
    protected String name;

    public BaseEcologicalRoleCompound(String name, List<IEcologicalRole> roles)
    {
        this.name = name;
        for (int i = 0; i < roles.size(); i++)
        {
            boolean cnt = false;
            for (int j = 0; j < this.roles.size(); j++)
            {
                if (this.roles.get(j).getName().equalsIgnoreCase(roles.get(i).getName()))
                {
                    cnt = true;
                    break;
                }
            }

            if (cnt)
                continue;

            this.roles.add(roles.get(i));
        }
    }

    public String getName()
    {
        return this.name;
    }

    public List<ItemStack> getFoodItems()
    {
        List<ItemStack> r = new ArrayList<ItemStack>();

        for (IEcologicalRole role : roles)
        {
            for (ItemStack i : role.getFoodItems())
            {
                boolean add = true;
                for (ItemStack is : r)
                    if (BaseItem.itemsEqual(is, i))
                        add = false;

                if (add)
                    r.add(i);
            }
        }

        return r;
    }
}
