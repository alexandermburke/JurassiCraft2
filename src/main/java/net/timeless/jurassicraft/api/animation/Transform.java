package net.timeless.jurassicraft.api.animation;

import net.minecraft.client.model.ModelRenderer;

public class Transform
{

    public float rotX;
    public float rotY;
    public float rotZ;
    public float trX;
    public float trY;
    public float trZ;

    public float rotOffsetX;
    public float rotOffsetY;
    public float rotOffsetZ;

    public Transform()
    {

    }

    public Transform(ModelRenderer m)
    {
        rotX = m.rotateAngleX;
        rotY = m.rotateAngleY;
        rotZ = m.rotateAngleZ;
        trX = m.offsetX;
        trY = m.offsetY;
        trZ = m.offsetZ;
        rotOffsetX = m.rotationPointX;
        rotOffsetY = m.rotationPointY;
        rotOffsetZ = m.rotationPointZ;
    }

    public Transform copy()
    {
        Transform tr = new Transform();
        tr.rotX = rotX;
        tr.rotY = rotY;
        tr.rotZ = rotZ;
        tr.trX = trX;
        tr.trY = trY;
        tr.trZ = trZ;

        tr.rotOffsetX = rotOffsetX;
        tr.rotOffsetY = rotOffsetY;
        tr.rotOffsetZ = rotOffsetZ;
        return tr;
    }

    public void add(Transform o)
    {
        rotX += o.rotX;
        rotY += o.rotY;
        rotZ += o.rotZ;
        trX += o.trX;
        trY += o.trY;
        trZ += o.trZ;
        rotOffsetX += o.rotOffsetX;
        rotOffsetY += o.rotOffsetY;
        rotOffsetZ += o.rotOffsetZ;
    }
}
