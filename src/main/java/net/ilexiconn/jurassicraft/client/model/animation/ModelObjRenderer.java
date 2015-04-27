package net.ilexiconn.jurassicraft.client.model.animation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelObjRenderer extends ModelRenderer
{
    public IModelCustom model;
    private float theScale;

    private int displayList;
    private boolean compiled;

    public ModelObjRenderer(ModelBase bass)
    {
        this(bass, null, 1f);
    }

    public ModelObjRenderer(ModelBase bass, IModelCustom shape)
    {
        this(bass, shape, 1f);
    }

    public ModelObjRenderer(ModelBase bass, IModelCustom shape, float scale)
    {
        super(bass);
        theScale = scale;
        model = shape;
    }

    public void setScale(float s)
    {
        theScale = s;
    }

    public void render(float scale)
    {
        if (!isHidden)
        {
            if (showModel)
            {
                if (!compiled) compileDisplayList();

                GL11.glTranslatef(offsetX, offsetY, offsetZ);
                int i;

                if (rotateAngleX == 0f && rotateAngleY == 0f && rotateAngleZ == 0f)
                {
                    if (rotationPointX == 0f && rotationPointY == 0f && rotationPointZ == 0f)
                    {
                        GL11.glPushMatrix();
                        GL11.glScalef(theScale, theScale, theScale);
                        GL11.glCallList(displayList);
                        GL11.glPopMatrix();

                        if (childModels != null) for (i = 0; i < childModels.size(); ++i) ((ModelRenderer) childModels.get(i)).render(scale);
                    }
                    else
                    {
                        GL11.glTranslatef(rotationPointX * scale, rotationPointY * scale, rotationPointZ * scale);
                        GL11.glPushMatrix();
                        GL11.glScalef(theScale, theScale, theScale);
                        GL11.glCallList(displayList);
                        GL11.glPopMatrix();

                        if (childModels != null) for (i = 0; i < childModels.size(); ++i) ((ModelRenderer) childModels.get(i)).render(scale);

                        GL11.glTranslatef(-rotationPointX * scale, -rotationPointY * scale, -rotationPointZ * scale);
                    }
                }
                else
                {
                    GL11.glPushMatrix();
                    GL11.glTranslatef(rotationPointX * scale, rotationPointY * scale, rotationPointZ * scale);

                    if (rotateAngleZ != 0f) GL11.glRotatef(rotateAngleZ * (180f / (float) Math.PI), 0f, 0f, 1f);
                    if (rotateAngleY != 0f) GL11.glRotatef(rotateAngleY * (180f / (float) Math.PI), 0f, 1f, 0f);
                    if (rotateAngleX != 0f) GL11.glRotatef(rotateAngleX * (180f / (float) Math.PI), 1f, 0f, 0f);

                    GL11.glPushMatrix();
                    GL11.glScalef(theScale, theScale, theScale);
                    GL11.glCallList(displayList);
                    GL11.glPopMatrix();

                    if (childModels != null) for (i = 0; i < childModels.size(); ++i) ((ModelRenderer) childModels.get(i)).render(scale);

                    GL11.glPopMatrix();
                }

                GL11.glTranslatef(-offsetX, -offsetY, -offsetZ);
            }
        }
    }

    public void renderWithRotation(float scale)
    {
        if (!isHidden)
        {
            if (showModel)
            {
                if (!compiled) compileDisplayList();

                GL11.glPushMatrix();
                GL11.glTranslatef(rotationPointX * scale, rotationPointY * scale, rotationPointZ * scale);

                if (rotateAngleY != 0f) GL11.glRotatef(rotateAngleY * (180f / (float) Math.PI), 0f, 1f, 0f);
                if (rotateAngleX != 0f) GL11.glRotatef(rotateAngleX * (180f / (float) Math.PI), 1f, 0f, 0f);
                if (rotateAngleZ != 0f) GL11.glRotatef(rotateAngleZ * (180f / (float) Math.PI), 0f, 0f, 1f);

                GL11.glPushMatrix();
                GL11.glScalef(theScale, theScale, theScale);
                GL11.glCallList(displayList);
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }
        }
    }

    protected void compileDisplayList()
    {
        displayList = GLAllocation.generateDisplayLists(1);
        GL11.glNewList(displayList, GL11.GL_COMPILE);

        GL11.glPushMatrix();
        GL11.glScalef(0.76f, 0.76f, 0.76f);
        GL11.glRotatef(180f, 1f, 0f, 0f);
        model.renderAll();
        GL11.glPopMatrix();

        GL11.glEndList();
        compiled = true;
    }
}
