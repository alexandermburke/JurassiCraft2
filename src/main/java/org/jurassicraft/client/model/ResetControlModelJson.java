package org.jurassicraft.client.model;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.common.json.container.JsonTabulaModel;
import net.minecraft.entity.Entity;

public class ResetControlModelJson extends ModelJson
{
    private final IModelAnimator animator;
    private boolean resetAllowed;

    public ResetControlModelJson(JsonTabulaModel model, IModelAnimator animator)
    {
        super(model, animator);
        this.animator = animator;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        if (resetAllowed)
        {
            super.setRotationAngles(limbSwing, limbSwingAmount, rotation, rotationYaw, rotationPitch, partialTicks, entity);
        }
        else
        {
            if (this.animator != null)
            {
                this.animator.setRotationAngles(this, limbSwing, limbSwingAmount, rotation, rotationYaw, rotationPitch, partialTicks, entity);
            }
        }
    }

    public void setResetEachFrame(boolean reset)
    {
        resetAllowed = reset;
    }

}
