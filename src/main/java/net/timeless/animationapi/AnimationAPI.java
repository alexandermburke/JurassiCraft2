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
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.timeless.animationapi.client.AnimID;
import net.timeless.animationapi.client.CommandForceAnimation;
import net.timeless.animationapi.packet.PacketAnim;
import org.jurassicraft.JurassiCraft;

@Mod(modid = "jcanimationapi", name = "JurassiCraft AnimationAPI", version = "1.2.5")
public class AnimationAPI
{
    @Mod.Instance("AnimationAPI")
    public static AnimationAPI instance;

    @SidedProxy(clientSide = "net.timeless.animationapi.client.ClientProxy", serverSide = "net.timeless.animationapi.CommonProxy")
    public static CommonProxy proxy;

    public static final String[] fTimer;

    static
    {
        fTimer = new String[] { "field_71428_T", "S", "timer" };
    }

    public static SimpleNetworkWrapper networkWrapper;
    private static int packetId = 0;

    public static CommonProxy getProxy()
    {
        return proxy;
    }

    public static String[] getFTimer()
    {
        return fTimer;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("animapi");

        registerPacket(PacketAnim.Handler.class, PacketAnim.class);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {
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

    private static <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType)
    {
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId, Side.CLIENT);
        networkWrapper.registerMessage(messageHandler, requestMessageType, packetId, Side.SERVER);
        packetId++;
    }

    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getSide().isClient();
    }

    public static boolean isEffectiveClient()
    {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static void sendAnimPacket(IAnimatedEntity animatedEntity, AnimID animID)
    {
        animatedEntity.setAnimID(animID);

        Entity entity = (Entity) animatedEntity;

        if (!entity.worldObj.isRemote)
        {
            networkWrapper.sendToAll(new PacketAnim(entity));
        }
    }
}
