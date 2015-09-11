package net.timeless.jurassicraft.client.proxy;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.event.ClientEventHandler;
import net.timeless.jurassicraft.client.render.JCRenderingRegistry;
import net.timeless.jurassicraft.common.proxy.CommonProxy;

import com.google.common.collect.Lists;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    public static JCRenderingRegistry renderingRegistry = new JCRenderingRegistry();

    private static ArrayList<String> uuids = Lists.newArrayList();

    static
    {
        //Patrons

        uuids.add("119fdbf2-a86b-4dfc-aaf2-a59e332b6e8c"); //HavicDave
        uuids.add("6e1f5ca8-bad8-4ee5-8114-c5dae4e83b22"); //Ceetron_
        uuids.add("a6888946-9303-45d9-8c9e-15f4b25aa283"); //tachyon_cyber
        uuids.add("a43845fa-b76e-42fe-803f-e2edf58c23a1"); //Zukulemto
        uuids.add("37d86247-b864-4e8d-a1e1-21d97c41b602");

        //Team
        uuids.add("d0e40dd9-fda1-4dbf-a4fe-43d689f77fc6"); //Raptor20 (wysssy)
        uuids.add("2d8bdb70-030c-490f-a35d-b7d8fd8be605"); //PigeonAce (Peter)
        uuids.add("1d2b4b67-7d3a-415e-9a77-47b50f275a99"); //Harry26548 (Harry)
        uuids.add("c3ed4d52-fb4f-4964-ba1b-9cda2453741e"); //Gegy (gegy1000)
        uuids.add("54201149-1f1f-498b-98ca-50f66951a68f"); //JTGhawk137 (Jack)
        uuids.add("a02ae44b-fff6-4d7d-8d49-d53006a820e4"); //ma_rio11 (Mario)
        uuids.add("dc35774d-1883-48ca-b4db-eb6ab26350ab"); //reuxertz (Ryan)
        uuids.add("71ec2c00-7ab3-437b-bf3f-aac35c530813"); //TheDurpiDaedric (Harvey)
        uuids.add("8803f3ac-1ed0-412a-ab44-844981af6e8b"); //kashmoney (Kash)
        uuids.add("4e88e24d-f77e-436a-ac80-457365c8deaa"); //CarrotJet (Cyrano)
        uuids.add("a94683c5-aac9-464f-b064-67b1696237a5"); //TheLarsinator
        uuids.add("5909ec5a-54ae-480b-a95a-1d21a95948ab"); //CristianXtreme (Cristian)
        uuids.add("0d2e2d40-72c3-4b2d-b221-ab94a791d5bc"); //jglrxavpok (Zavier/Xavier)

//        uuids.add("487a286b-25a6-44d0-aaa0-f6b87fee6bfb"); //BobMowzie (Josh)
//        uuids.add("40e85e42-21f6-46b6-b5b3-6aeb07f3e3fd"); //iLexiconn (Lex)

        //TODO CarrotJet + Kash
    }

    @Override
    public void preInit()
    {
        super.preInit();

//        boolean matchFound = false;
//
//        UUID id = Minecraft.getMinecraft().getSession().getProfile().getId();
//
//        for(String uuid : uuids)
//        {
//            if(id.toString().equalsIgnoreCase(uuid))
//            {
//                matchFound = true;
//                break;
//            }
//        }
//
//        if(!matchFound && !JurassiCraft.instance.isDebugging())
//        {
//            FMLCommonHandler.instance().exitJava(-1, false);
//        }

        ClientEventHandler eventHandler = new ClientEventHandler();
        FMLCommonHandler.instance().bus().register(eventHandler);
        MinecraftForge.EVENT_BUS.register(eventHandler);

        renderingRegistry.preInit();
    }

    @Override
    public void init()
    {
        super.init();

        renderingRegistry.init();
    }

    @Override
    public void postInit()
    {
        super.postInit();

        renderingRegistry.postInit();
    }

    @Override
    public EntityPlayer getPlayer()
    {
        return Minecraft.getMinecraft().thePlayer;
    }
    
    /**
     * Returns a side-appropriate EntityPlayer for use during message handling
     */
    @Override
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx) 
    {
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work because you will be getting a client
        // player even when you are on the server! Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntityFromContext(ctx));
    }

}