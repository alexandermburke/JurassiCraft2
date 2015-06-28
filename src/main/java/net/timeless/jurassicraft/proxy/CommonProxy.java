package net.timeless.jurassicraft.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;
import net.timeless.jurassicraft.packets.JCMessageRegistry;
import net.timeless.jurassicraft.recipe.JCRecipeRegistry;

public abstract class CommonProxy
{
    public void preInit()
    {
        JCEntityRegistry.preInitCommon();
        JCCreativeTabs.preInitCommon();
        JCBlockRegistry.preInitCommon();
        JCItemRegistry.preInitCommon();
        JCRecipeRegistry.preInitCommon();
        JCMessageRegistry.preInitCommon();
    }

    public void init()
    {
        JCEntityRegistry.initCommon();
        JCCreativeTabs.initCommon();
        JCBlockRegistry.initCommon();
        JCItemRegistry.initCommon();
        JCRecipeRegistry.initCommon();
        JCMessageRegistry.initCommon();
    }

    public void postInit()
    {
        JCEntityRegistry.postInitCommon();
        JCCreativeTabs.postInitCommon();
        JCBlockRegistry.postInitCommon();
        JCItemRegistry.postInitCommon();
        JCRecipeRegistry.postInitCommon();
        JCMessageRegistry.postInitCommon();
    }

    abstract public boolean playerIsInCreativeMode(EntityPlayer player);
}
