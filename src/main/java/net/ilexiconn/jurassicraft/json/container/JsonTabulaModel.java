package net.ilexiconn.jurassicraft.json.container;

import net.ilexiconn.jurassicraft.client.model.tabula.CubeGroup;
import net.ilexiconn.jurassicraft.client.model.tabula.CubeInfo;

import java.util.ArrayList;

public class JsonTabulaModel
{
    public String modelName;
    public String authorName;
    public int projVersion;

    public ArrayList<String> metadata;

    public int textureWidth = 64;
    public int textureHeight = 32;

    public double[] scale = new double[] { 1.0D, 1.0D, 1.0D };

    public ArrayList<CubeGroup> cubeGroups;
    public ArrayList<CubeInfo> cubes;

    public int cubeCount;
}
