package net.reuxertz.ecoapi.ecology.genetics;

import net.minecraft.client.resources.model.ModelResourceLocation;

import java.awt.*;

public class GeneEntitySkin extends BaseGene
{
    public enum SkinType
    {
        Body, Hair, Eyes, Mouth, Marking
    }

    protected SkinType skinType;
    protected ModelResourceLocation resourceLocation;
    protected Color resourceColor;
    protected int dominanceIndex, expressionIndex;

    public ModelResourceLocation getResourceLocation()
    {
        return this.resourceLocation;
    }

    public GeneEntitySkin cloneNewColor(Color c)
    {
        return new GeneEntitySkin(this.entitySpecie, this.resourceLocation, c, this.skinType, this.sexLinked, this.dominanceIndex, this.expressionIndex);
    }

    public boolean geneIsAnalog(IGene gene)
    {
        return super.geneIsAnalog(gene) && ((GeneEntitySkin) gene).skinType == this.skinType;
    }

    public GeneEntitySkin(Class entityClass, SkinType skinType)
    {
        super(entityClass);

        this.skinType = skinType;
    }

    public GeneEntitySkin(Class entityClass, ModelResourceLocation resourceLocation, Color color, SkinType skinType, SexLinked sexLinked, int dominanceIndex, int expressionIndex)
    {
        this(entityClass, skinType);

        this.dominanceIndex = dominanceIndex;
        this.expressionIndex = expressionIndex;
        this.resourceLocation = resourceLocation;
        this.resourceColor = color;
    }
}
