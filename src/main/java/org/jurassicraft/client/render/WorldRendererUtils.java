package org.jurassicraft.client.render;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class WorldRendererUtils
{
    private WorldRenderer worldRenderer;

    public WorldRendererUtils(WorldRenderer worldRenderer)
    {
        this.worldRenderer = worldRenderer;
    }

    public void setNormal(float normal1, float normal2, float normal3)
    {
        worldRenderer.setNormal(normal1, normal2, normal3);
    }

    public void startDrawingQuads()
    {
        worldRenderer.startDrawingQuads();
    }

    public void addVertexWithUV(double d1, double d2, double d3, double u, double v)
    {
        worldRenderer.addVertexWithUV(d1, d2, d3, u, v);
    }
}
