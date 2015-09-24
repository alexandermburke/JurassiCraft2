package net.timeless.animationapi;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.timeless.animationapi.client.AnimID;
import net.timeless.animationapi.client.CommandForceAnimation;
import net.timeless.animationapi.packet.PacketAnim;

import org.jurassicraft.JurassiCraft;

@Mod(modid = "JCAnimationAPI", name = "JurassiCraft AnimationAPI", version = "1.2.5")
public class AnimationAPI
{

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("AnimAPI");
        int discriminator = 0;
        wrapper.registerMessage(PacketAnim.Handler.class, PacketAnim.class, discriminator++, Side.CLIENT);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.initTimer();
    }
    
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        // register server commands

        event.registerServerCommand(new CommandForceAnimation());
    }

    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getSide().isClient();
    }

    public static boolean isEffectiveClient()
    {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static void sendAnimPacket(IAnimatedEntity entity, AnimID animID)
    {
        entity.setAnimID(animID);
        if (!((Entity) entity).worldObj.isRemote)
        {
            JurassiCraft.instance.getLogger().debug("sending Anim Packet for entity " + ((Entity) entity).getEntityId());

            wrapper.sendToAll(new PacketAnim(animID, ((Entity) entity).getEntityId()));
        }
    }

    @Mod.Instance("AnimationAPI")
    public static AnimationAPI instance;
    @SidedProxy(clientSide = "net.timeless.animationapi.client.ClientProxy",
            serverSide = "net.timeless.animationapi.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper wrapper;

    public static final String[] fTimer;

    static
    {
        fTimer = new String[]{"field_71428_T", "S", "timer"};
    }

    public static CommonProxy getProxy()
    {
        return proxy;
    }

    public static String[] getFTimer()
    {
        return fTimer;
    }
}
