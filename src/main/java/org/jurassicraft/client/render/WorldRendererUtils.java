package org.jurassicraft.client.render;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class WorldRendererUtils
{
    private float normal1, normal2, normal3;
    private boolean setNormal = false;
    private WorldRenderer worldRenderer;

    public WorldRendererUtils(WorldRenderer worldRenderer)
    {
        this.worldRenderer = worldRenderer;
    }

    public void setNormal(float normal1, float normal2, float normal3)
    {
        this.normal1 = normal1;
        this.normal2 = normal2;
        this.normal3 = normal3;
        this.setNormal = true;
    }

    public void startDrawingQuads()
    {
        worldRenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
    }

    public void addVertexWithUV(double d1, double d2, double d3, double u, double v)
    {
        WorldRenderer worldRenderer = this.worldRenderer.func_181662_b(d1, d2, d3).func_181673_a(u, v); //First: addVertex Second: addUV

        if (setNormal)
        {
            worldRenderer.func_181663_c(normal1, normal2, normal3);
        }

        worldRenderer.func_181675_d(); //Add
    }
}
