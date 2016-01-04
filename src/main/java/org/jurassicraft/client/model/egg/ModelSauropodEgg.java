package org.jurassicraft.client.model.egg;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

class ModelSauropodEgg extends ModelBase
{
    private final ModelRenderer bottom;
    private final ModelRenderer main1;
    private final ModelRenderer top;
    private final ModelRenderer side1;
    private final ModelRenderer side2;
    private final ModelRenderer side3;
    private final ModelRenderer side4;

    public ModelSauropodEgg()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.top = new ModelRenderer(this, 0, 20);
        this.top.setRotationPoint(0.0F, -1.5F, 0.0F);
        this.top.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.side3 = new ModelRenderer(this, 19, 16);
        this.side3.setRotationPoint(2.0F, 1.5F, 0.0F);
        this.side3.addBox(0.0F, -2.0F, -2.0F, 1, 4, 4, 0.0F);
        this.main1 = new ModelRenderer(this, 0, 9);
        this.main1.setRotationPoint(0.0F, -2.5F, 0.0F);
        this.main1.addBox(-2.5F, 0.0F, -2.5F, 5, 3, 5, 0.0F);
        this.side1 = new ModelRenderer(this, 19, 0);
        this.side1.setRotationPoint(0.0F, 1.5F, 2.0F);
        this.side1.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 1, 0.0F);
        this.bottom = new ModelRenderer(this, 0, 0);
        this.bottom.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.bottom.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4, 0.0F);
        this.side4 = new ModelRenderer(this, 31, 0);
        this.side4.setRotationPoint(-2.0F, 1.5F, 0.0F);
        this.side4.addBox(-1.0F, -2.0F, -2.0F, 1, 4, 4, 0.0F);
        this.side2 = new ModelRenderer(this, 19, 8);
        this.side2.setRotationPoint(0.0F, 1.5F, -2.0F);
        this.side2.addBox(-2.0F, -2.0F, -1.0F, 4, 4, 1, 0.0F);

        this.main1.addChild(this.top);
        this.main1.addChild(this.side3);
        this.bottom.addChild(this.main1);
        this.main1.addChild(this.side1);
        this.main1.addChild(this.side4);
        this.main1.addChild(this.side2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.bottom.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
