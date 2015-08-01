package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationCompsognathus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

        float globalSpeed = 1.0F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer neck1 = model.getCube("Neck 1");

        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");

        MowzieModelRenderer leftThigh = model.getCube("Left thigh");
        MowzieModelRenderer leftMidLeg = model.getCube("Left mid leg");
        MowzieModelRenderer leftShin = model.getCube("Left shin");
        MowzieModelRenderer leftFoot = model.getCube("Left foot");

        MowzieModelRenderer rightThigh = model.getCube("Right thigh");
        MowzieModelRenderer rightMidLeg = model.getCube("Right mid leg");
        MowzieModelRenderer rightShin = model.getCube("Right shin");
        MowzieModelRenderer rightFoot = model.getCube("Right foot");

        model.faceTarget(head, 2, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 2, rotationYaw, rotationPitch);

        //beg animation
        animator.setAnim(1);
        animator.startPhase(20);
        animator.rotate(head, 0, 0, 0);
        animator.move(head, 0, 1.3F, 0);
        animator.endPhase();
        animator.startPhase(10);
        animator.rotate(lowerJaw, 0, 1.3F, 0);
        animator.endPhase();
        animator.resetPhase(15);
    }
}
