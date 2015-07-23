package net.timeless.jurassicraft.client.model;

import java.util.List;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.common.json.container.JsonTabulaModel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.Animator;

public class ModelDinosaur extends ModelJson
{
    public Animator animator;

    public ModelDinosaur(JsonTabulaModel model)
    {
        super(model);
        this.animator = new Animator(this);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        if (!Minecraft.getMinecraft().isGamePaused())
            animator.update((IAnimatedEntity) entity);

        super.setRotationAngles(limbSwing, limbSwingAmount, rotation, rotationYaw, rotationPitch, partialTicks, entity);
    }

    public ModelDinosaur(JsonTabulaModel model, IModelAnimator animator)
    {
        super(model, animator);
        this.animator = new Animator(this);
    }

    public List<MowzieModelRenderer> getParts()
    {
        return super.parts;
    }
}
