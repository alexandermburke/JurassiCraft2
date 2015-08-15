package net.reuxertz.ecoapi.base;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class BaseNBTRecipe implements IRecipe
{
    protected IRecipe recipe;
    protected Boolean[] removeOnCraft;
    protected ShapelessRecipes shapelessRecipe;
    protected ShapedRecipes shapedRecipe;

    public ItemStack handleOutput(InventoryCrafting inv, ItemStack output)
    {
        return null;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        return recipe.matches(inv, worldIn);
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        ItemStack output = recipe.getCraftingResult(inv);

        ItemStack out = this.handleOutput(inv, output);
        if (out != null)
            return out;
        else
            return output;
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return recipe.getRecipeSize();
    }

    public ItemStack getRecipeOutput()
    {
        return recipe.getRecipeOutput();
    }

    public ItemStack[] getRemainingItems(InventoryCrafting p_179532_1_)
    {
        return recipe.getRemainingItems(p_179532_1_);
    }

    public BaseNBTRecipe(IRecipe recipe)
    {
        this.recipe = recipe;

        if (recipe instanceof ShapelessRecipes)
            this.shapelessRecipe = (ShapelessRecipes) recipe;
        if (recipe instanceof ShapedRecipes)
            this.shapedRecipe = (ShapedRecipes) recipe;
    }
}
