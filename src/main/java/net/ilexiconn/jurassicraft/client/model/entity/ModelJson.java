package net.ilexiconn.jurassicraft.client.model.entity;

import java.util.Map;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import net.ilexiconn.jurassicraft.client.model.tabula.CubeGroup;
import net.ilexiconn.jurassicraft.client.model.tabula.CubeInfo;
import net.ilexiconn.jurassicraft.json.container.JsonTabulaModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.google.common.collect.Maps;

public class ModelJson extends ModelBase
{
    private JsonTabulaModel model;
    private Map<ModelRenderer, ModelRenderer> cubes = Maps.newHashMap();
    
    public ModelJson(JsonTabulaModel model)
    {
        this.model = model;
        
        this.textureWidth = model.getTextureWidth();
        this.textureHeight = model.getTextureHeight();
        
        for (CubeInfo c : model.getCubes())
        {
            cube(c, null);
        }
        
        for (CubeGroup g : model.getCubeGroups())
        {
            cubeGroup(g);
        }
        
        //        for (int i = 0; i < cubes.length; i++)
        //        {
        //            CubeInfo cubeInfo = model.cubes.get(i);
        //            
        //            ModelRenderer cube = new ModelRenderer(this, cubeInfo.txOffset[0], cubeInfo.txOffset[1]);
        //            cube.setRotationPoint((float) cubeInfo.position[0], (float) cubeInfo.position[1], (float) cubeInfo.position[2]);
        //            cube.addBox((float) cubeInfo.offset[0], (float) cubeInfo.offset[1], (float) cubeInfo.offset[2], cubeInfo.dimensions[0], cubeInfo.dimensions[1], cubeInfo.dimensions[2], 0.0F);
        //            cube.rotateAngleX = (float) cubeInfo.rotation[0]; //TODO Conversion?
        //            cube.rotateAngleY = (float) cubeInfo.rotation[1];
        //            cube.rotateAngleZ = (float) cubeInfo.rotation[2];
        //            
        //            this.cubes[i] = cube;
        //        }
    }
    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float partialTicks)
    {
        super.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, partialTicks, entity);
        
        double[] scale = model.getScale();
        GL11.glScaled(scale[0], scale[1], scale[2]);
        
        for (Entry<ModelRenderer, ModelRenderer> cube : cubes.entrySet())
        {
            if(cube.getValue() == null)
            {
                cube.getKey().render(partialTicks);
            }
        }
    }
    
    public void cubeGroup(CubeGroup group)
    {
        for (CubeInfo cube : group.cubes)
        {
            cube(cube, null);
        }
        
        for (CubeGroup c : group.cubeGroups)
        {
            cubeGroup(c);
        }
    }
    
    public void cube(CubeInfo cube, ModelRenderer parent)
    {
        ModelRenderer modelRenderer = createModelRenderer(cube);
        
        cubes.put(modelRenderer, parent);
        
        if(parent != null)
        {
            parent.addChild(modelRenderer);
        }
        
        for (CubeInfo c : cube.children)
        {
            cube(c, modelRenderer);
        }
    }
    
    public ModelRenderer createModelRenderer(CubeInfo cubeInfo)
    {
        ModelRenderer cube = new ModelRenderer(this, cubeInfo.txOffset[0], cubeInfo.txOffset[1]);
        cube.setRotationPoint((float) cubeInfo.position[0], (float) cubeInfo.position[1], (float) cubeInfo.position[2]);
        cube.addBox((float) cubeInfo.offset[0], (float) cubeInfo.offset[1], (float) cubeInfo.offset[2], cubeInfo.dimensions[0], cubeInfo.dimensions[1], cubeInfo.dimensions[2], 0.0F);
        cube.rotateAngleX = (float) Math.toRadians((float) cubeInfo.rotation[0]); //TODO Conversion?
        cube.rotateAngleY = (float) Math.toRadians((float) cubeInfo.rotation[1]);
        cube.rotateAngleZ = (float) Math.toRadians((float) cubeInfo.rotation[2]);
        
        return cube;
    }
}
