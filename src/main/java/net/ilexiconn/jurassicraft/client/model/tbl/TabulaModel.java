package net.ilexiconn.jurassicraft.client.model.tbl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.client.model.entity.ModelJson;
import net.ilexiconn.jurassicraft.entity.json.JsonCreature;
import net.ilexiconn.jurassicraft.json.JsonFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;

import com.google.gson.Gson;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabulaModel
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
    
    public static TabulaModel fromJson(String file)
    {
        return JsonFactory.getGson().fromJson(new InputStreamReader(JurassiCraft.class.getResourceAsStream(file)), TabulaModel.class);
    }
}
