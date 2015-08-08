package net.timeless.jurassicraft.client.render.block;

import eu.thog92.isbrh.registry.RenderRegistry;

public class JCBlockRenderingRegistry
{
    public static int cageID;

    public static void regisiter()
    {
        cageID = RenderRegistry.getNextAvailableRenderId();
        RenderRegistry.registerBlockHandler(new RenderCage());
    }
}
