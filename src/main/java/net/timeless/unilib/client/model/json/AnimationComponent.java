package net.timeless.unilib.client.model.json;

import java.util.ArrayList;

/**
 * Container for Tabula animation components.
 * 
 * @author Gegy1000
 * TAKEN FROM LLIBRARY
 */
public class AnimationComponent
{
    public double[] posChange = new double[3];
    public double[] rotChange = new double[3];
    public double[] scaleChange = new double[3];
    public double opacityChange = 0.0D;

    public double[] posOffset = new double[3];
    public double[] rotOffset = new double[3];
    public double[] scaleOffset = new double[3];
    public double opacityOffset = 0.0D;

    public ArrayList<double[]> progressionCoords;

    public String name;

    public int length;
    public int startKey;

    public boolean hidden;

    public String identifier;
}
