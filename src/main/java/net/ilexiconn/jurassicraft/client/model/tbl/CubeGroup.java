package net.ilexiconn.jurassicraft.client.model.tbl;

import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;

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

