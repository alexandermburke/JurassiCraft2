package net.ilexiconn.jurassicraft.client.model.tbl;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import org.apache.commons.lang3.RandomStringUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

    private ArrayList<CubeInfo> children = new ArrayList<CubeInfo>();
    public String parentIdentifier;

    public String identifier;
}

