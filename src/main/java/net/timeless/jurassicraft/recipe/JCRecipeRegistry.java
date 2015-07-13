package net.timeless.jurassicraft.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.timeless.jurassicraft.dinosaur.Dinosaur;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;

public class JCRecipeRegistry
{
    public void register()
    {
        for (Dinosaur dinosaur : JCEntityRegistry.getDinosaurs())
        {
            if (dinosaur.shouldRegister())
            {
                int meta = JCEntityRegistry.getDinosaurId(dinosaur);

                GameRegistry.addSmelting(new ItemStack(JCItemRegistry.dino_meat, 1, meta), new ItemStack(JCItemRegistry.dino_steak, 1, meta), 5F);
            }
        }
    }
}
