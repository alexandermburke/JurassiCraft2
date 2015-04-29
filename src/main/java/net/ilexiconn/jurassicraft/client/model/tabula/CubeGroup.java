package net.ilexiconn.jurassicraft.client.model.tabula;

import java.util.ArrayList;

/**
 * @author iChun
 */
public class CubeGroup
{
    public ArrayList<CubeInfo> cubes = new ArrayList<CubeInfo>();
    public ArrayList<CubeGroup> cubeGroups = new ArrayList<CubeGroup>();

    public String name;

    public boolean txMirror = false;

    public boolean hidden = false;

    public String identifier;
}

