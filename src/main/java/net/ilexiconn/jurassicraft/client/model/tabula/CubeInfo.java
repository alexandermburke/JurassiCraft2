package net.ilexiconn.jurassicraft.client.model.tabula;

import java.util.ArrayList;

/**
 * @author iChun
 */
public class CubeInfo
{
    public String name;
    public int[] dimensions = new int[3];

    public double[] position = new double[3];
    public double[] offset = new double[3];
    public double[] rotation = new double[3];

    public double[] scale = new double[3];

    public int[] txOffset = new int[2];
    public boolean txMirror = false;

    public double mcScale = 0.0D;

    public double opacity = 100D;

    public boolean hidden = false;

    public ArrayList<CubeInfo> children = new ArrayList<CubeInfo>();
    public String parentIdentifier;

    public String identifier;
}

