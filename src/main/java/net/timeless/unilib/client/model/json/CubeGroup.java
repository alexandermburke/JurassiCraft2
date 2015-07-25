package net.timeless.unilib.client.model.json;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @author iChun
 * TAKEN FROM LLIBRARY
 */
public class CubeGroup
{
    public ArrayList<CubeInfo> cubes = Lists.newArrayList();
    public ArrayList<CubeGroup> cubeGroups = Lists.newArrayList();

    public String name;

    public boolean txMirror = false;

    public boolean hidden = false;

    public String identifier;
}
