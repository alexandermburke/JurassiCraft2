package net.reuxertz.ecoapi;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.reuxertz.ecoapi.ecology.EcoFauna;
import net.reuxertz.ecoapi.ecology.genetics.BaseGene;
import net.reuxertz.ecoapi.ecology.genetics.GeneEntitySkin;
import net.reuxertz.ecoapi.ecology.genetics.IGene;
import net.reuxertz.ecoapi.ecology.role.IEcologicalRole;
import net.reuxertz.ecoapi.ecology.role.RoleCarnivore;
import net.reuxertz.ecoapi.ecology.role.RoleHerbivore;

import java.awt.*;
import java.util.Random;

public class EcoAPI
{
    //Commonly used Fields
    public static final Random RND = new Random();
    public static final IEcologicalRole carnivore = new RoleCarnivore(), herbivore = new RoleHerbivore();

    //Entity Functions
    public static void registerEntityClassDropItems(Class entityClass, ItemStack[] dropItems)
    {
        EcoFauna.addToClassDropRegistry(entityClass, dropItems);
    }
    public static void registerEcologicalRoleFoodItem(IEcologicalRole role, ItemStack[] stacks)
    {
        for (ItemStack stack: stacks)
            role.addFoodItem(stack);
    }

    //Genes
    public static GeneEntitySkin registerEntitySkinGene(Class entityClass, ModelResourceLocation rl, Color c, GeneEntitySkin.SkinType skinType, BaseGene.SexLinked sexLinked, int dominanceIndex, int alphaIndex)
    {
        return EcoAPI.registerEntitySkinGene(entityClass, rl, c, skinType, sexLinked, dominanceIndex, alphaIndex, false);
    }
    public static GeneEntitySkin registerEntitySkinGene(Class entityClass, ModelResourceLocation rl, Color c, GeneEntitySkin.SkinType skinType, BaseGene.SexLinked sexLinked, int dominanceIndex, int alphaIndex, boolean required)
    {
        GeneEntitySkin ng = new GeneEntitySkin(entityClass, rl, c, skinType, sexLinked, dominanceIndex, alphaIndex);
        //EcoAPI.registerEntityGeneToPool(entityClass, sexLinked, dominanceIndex, alphaIndex, required);
        return ng;
    }
    public static void registerEntityGeneToPool(Class entityClass, IGene gene)
    {

    }
}
