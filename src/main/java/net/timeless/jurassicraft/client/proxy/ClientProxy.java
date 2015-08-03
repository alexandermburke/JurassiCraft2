package net.timeless.jurassicraft.client.proxy;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.event.ClientEventHandler;
import net.timeless.jurassicraft.client.render.JCRenderingRegistry;
import net.timeless.jurassicraft.common.proxy.CommonProxy;

import java.util.ArrayList;
import java.util.UUID;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    public static JCRenderingRegistry renderingRegistry = new JCRenderingRegistry();

    private static ArrayList<String> uuids = Lists.newArrayList();

    static
    {
        uuids.add("119fdbf2-a86b-4dfc-aaf2-a59e332b6e8c");
        uuids.add("54201149-1f1f-498b-98ca-50f66951a68f");
        uuids.add("6e1f5ca8-bad8-4ee5-8114-c5dae4e83b22");
        uuids.add("a6888946-9303-45d9-8c9e-15f4b25aa283");
        uuids.add("40e85e42-21f6-46b6-b5b3-6aeb07f3e3fd");
        uuids.add("dc35774d-1883-48ca-b4db-eb6ab26350ab");
        uuids.add("c3ed4d52-fb4f-4964-ba1b-9cda2453741e");
        uuids.add("487a286b-25a6-44d0-aaa0-f6b87fee6bfb");
        uuids.add("a02ae44b-fff6-4d7d-8d49-d53006a820e4");
        uuids.add("a43845fa-b76e-42fe-803f-e2edf58c23a1");
        uuids.add("71ec2c00-7ab3-437b-bf3f-aac35c530813");
    }

    @Override
    public void preInit()
    {
        super.preInit();

        boolean matchFound = false;

        UUID id = Minecraft.getMinecraft().getSession().getProfile().getId();

        for(String uuid : uuids)
        {
            if(id.toString().equalsIgnoreCase(uuid))
            {
                matchFound = true;
                break;
            }
        }

        if(!matchFound && !JurassiCraft.instance.isDebugging())
        {
            FMLCommonHandler.instance().exitJava(-1, false);
        }

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
}