package net.timeless.jurassicraft.common.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.packet.PacketAnim;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.event.CommonEventHandler;
import net.timeless.jurassicraft.common.handler.JCGuiHandler;
import net.timeless.jurassicraft.common.world.WorldGenerator;

public class CommonProxy
{
    public void preInit()
    {
        JurassiCraft.entityRegistry.register();
        JurassiCraft.plantRegistry.register();
        JurassiCraft.creativeTabRegistry.register();
        JurassiCraft.itemRegistry.register();
        JurassiCraft.blockRegistry.register();
        JurassiCraft.recipeRegistry.register();
        JurassiCraft.networkManager.register();
        JurassiCraft.appRegistry.register();
        JurassiCraft.achievements.register();
        JurassiCraft.storageTypeRegistry.register();

        GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);

        NetworkRegistry.INSTANCE.registerGuiHandler(JurassiCraft.instance, new JCGuiHandler());

        CommonEventHandler eventHandler = new CommonEventHandler();

        FMLCommonHandler.instance().bus().register(eventHandler);
        MinecraftForge.EVENT_BUS.register(eventHandler);
    }

    public void postInit()
    {

    }

    public void init()
    {

    }

    public EntityPlayer getPlayer()
    {
        return null;
    }
    
    /**
     * Returns a side-appropriate EntityPlayer for use during message handling
     */
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) 
    {
        return ctx.getServerHandler().playerEntity;
    }

	public void sendAnimPacket(IAnimatedEntity parEntity, int parAnimID) 
	{
		parEntity.setAnimID(parAnimID);
        AnimationAPI.wrapper.sendToAll(new PacketAnim((byte)parAnimID, ((Entity) parEntity).getEntityId()));		
	}
	
	public void sendAnimFinishedPacket(IAnimatedEntity parEntity, int parAnimID) 
	{
		parEntity.setAnimID(parAnimID);
	}
}
