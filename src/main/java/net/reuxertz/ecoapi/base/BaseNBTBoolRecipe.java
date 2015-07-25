package net.reuxertz.ecoapi.base;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;

import java.util.ArrayList;
import java.util.List;

public class BaseNBTBoolRecipe extends BaseNBTRecipe
{
    protected List<Boolean> booleans = new ArrayList<Boolean>();

    public static BaseNBTBoolRecipe ShapelessBoolRecipe(ItemStack output, Object... inputList)
    {
        List<Object> objs = new ArrayList<Object>();
        List<Boolean> bools = new ArrayList<Boolean>();

        for (int i = 0; i < inputList.length; i += 2)
        {
            objs.add(inputList[i]);
            bools.add((Boolean) inputList[i + 1]);
        }

        IRecipe rec = new ShapelessRecipes(output, objs);

        BaseNBTBoolRecipe rrec = new BaseNBTBoolRecipe(rec, bools);

        return rrec;

    }

    protected BaseNBTBoolRecipe(IRecipe recipe, List<Boolean> bools)
    {
        super(recipe);

        this.booleans = bools;
    }
}
