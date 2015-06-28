package net.timeless.jurassicraft.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;
import net.timeless.jurassicraft.packets.JCMessageRegistry;
import net.timeless.jurassicraft.recipe.JCRecipeRegistry;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        super.preInit();
        JCEntityRegistry.preInitClientOnly();
        JCCreativeTabs.preInitClientOnly();
        JCBlockRegistry.preInitClientOnly();
        JCItemRegistry.preInitClientOnly();
        JCRecipeRegistry.preInitClientOnly();
        JCMessageRegistry.preInitClientOnly();
    }

    @Override
    public void init()
    {
        super.init();
        JCEntityRegistry.initClientOnly();
        JCCreativeTabs.initClientOnly();
        JCBlockRegistry.initClientOnly();
        JCItemRegistry.initClientOnly();
        JCRecipeRegistry.initClientOnly();
        JCMessageRegistry.initClientOnly();
    }

    @Override
    public void postInit()
    {
        super.postInit();
        JCEntityRegistry.postInitClientOnly();
        JCCreativeTabs.postInitClientOnly();
        JCBlockRegistry.postInitClientOnly();
        JCItemRegistry.postInitClientOnly();
        JCRecipeRegistry.postInitClientOnly();
        JCMessageRegistry.postInitClientOnly();
    }

    @Override
    public boolean playerIsInCreativeMode(EntityPlayer player)
    {
        if (player instanceof EntityPlayerMP)
        {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
            return entityPlayerMP.theItemInWorldManager.isCreative();
        }
        else if (player instanceof EntityPlayerSP)
        {
            return Minecraft.getMinecraft().playerController.isInCreativeMode();
        }
        return false;
    }
}