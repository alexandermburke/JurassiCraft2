package net.ilexiconn.jurassicraft.client.model.entity;

import net.ilexiconn.jurassicraft.client.model.tabula.CubeInfo;
import net.ilexiconn.jurassicraft.json.container.JsonTabulaModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelJson extends ModelBase
{
    private JsonTabulaModel tabulaModel;
    private ModelRenderer[] cubes;

    public ModelJson(JsonTabulaModel model)
    {
        tabulaModel = model;

        cubes = new ModelRenderer[model.getCubeCount()]; //TODO CubeGroups

        textureWidth = model.getTextureWidth();
        textureHeight = model.getTextureHeight();

        for (int i = 0; i < cubes.length; i++)
        {
            CubeInfo cubeInfo = model.getCubes().get(i);

            ModelRenderer cube = new ModelRenderer(this, cubeInfo.txOffset[0], cubeInfo.txOffset[1]);
            cube.setRotationPoint((float) cubeInfo.position[0], (float) cubeInfo.position[1], (float) cubeInfo.position[2]);
            cube.addBox((float) cubeInfo.offset[0], (float) cubeInfo.offset[1], (float) cubeInfo.offset[2], cubeInfo.dimensions[0], cubeInfo.dimensions[1], cubeInfo.dimensions[2], 0f);
            cube.rotateAngleX = (float) cubeInfo.rotation[0]; //TODO Conversion?
            cube.rotateAngleY = (float) cubeInfo.rotation[1];
            cube.rotateAngleZ = (float) cubeInfo.rotation[2];

            cubes[i] = cube;
        }
    }
}
