package org.jurassicraft.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.IAnimatedEntity;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.model.json.IModelAnimator;
import net.timeless.animationapi.client.model.json.JsonTabulaModel;
import net.timeless.animationapi.client.model.json.ModelJson;
import net.timeless.animationapi.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.List;

@SideOnly(Side.CLIENT)
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
        {
            animator.update((IAnimatedEntity) entity);
        }

        EntityDinosaur dinosaur = (EntityDinosaur) entity;

        this.setMovementScale(dinosaur.isSleeping() ? 0.5F : 1.0F);

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
