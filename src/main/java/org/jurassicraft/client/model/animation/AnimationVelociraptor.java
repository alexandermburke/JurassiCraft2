package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityVelociraptor;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationVelociraptor extends DinosaurAnimator
{
    public AnimationVelociraptor()
    {
        super(JCEntityRegistry.velociraptor);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur entity)
    {
        EntityVelociraptor velociraptor = (EntityVelociraptor) entity;

        MowzieModelRenderer waist = model.getCube("body3");
        MowzieModelRenderer chest = model.getCube("body2");
        MowzieModelRenderer shoulders = model.getCube("body1");
        MowzieModelRenderer leftThigh = model.getCube("Left thigh");
        MowzieModelRenderer rightThigh = model.getCube("Right thigh");
        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");
        MowzieModelRenderer head = model.getCube("Head");
        MowzieModelRenderer jaw = model.getCube("down_jaw");
        MowzieModelRenderer leftShin = model.getCube("Left shin");
        MowzieModelRenderer rightShin = model.getCube("Right shin");
        MowzieModelRenderer leftUpperFoot = model.getCube("Left upper foot");
        MowzieModelRenderer leftFoot = model.getCube("Left foot");
        MowzieModelRenderer rightUpperFoot = model.getCube("Right upper foot");
        MowzieModelRenderer rightFoot = model.getCube("Right foot");
        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");
        MowzieModelRenderer tail6 = model.getCube("tail6");
        MowzieModelRenderer rightToe = model.getCube("Right toe");
        MowzieModelRenderer leftToe = model.getCube("Left toe");

        MowzieModelRenderer upperArmRight = model.getCube("Right arm");
        MowzieModelRenderer upperArmLeft = model.getCube("Left arm");
        MowzieModelRenderer lowerArmRight = model.getCube("Right forearm");
        MowzieModelRenderer lowerArmLeft = model.getCube("Left forearm");
        MowzieModelRenderer Hand_Right = model.getCube("Right hand");
        MowzieModelRenderer Hand_Left = model.getCube("Left hand");

        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[] { Hand_Right, lowerArmRight, upperArmRight };
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[] { Hand_Left, lowerArmLeft, upperArmLeft };
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[] { waist, chest, shoulders, neck4, neck3, neck2, neck1, head };

        // if (velociraptor.isCarcass()) //Death Animation
        // {
        // model.walk(head, 1.0F, 0.5F, false, 0, 0, velociraptor.hurtTime, 1.0F);
        // }
        // else
        // {
        int frame = velociraptor.ticksExisted;

        // f = entity.ticksExisted;
        // f1 = 1F;
        // f1 = (float) (Math.sin(velociraptor.ticksExisted * 0.01) *
        // Math.sin(velociraptor.ticksExisted * 0.01));

        // if (raptor.leaping)
        // limbSwingAmount = 0;
        // if (raptor.getAnimationId() == JurassiCraftAnimationIDs.LEAP.animID()
        // && raptor.getAnimationTick() >= 6)
        // limbSwingAmount = 0;
        float speed = 0.75F;
        float height = 2F * limbSwingAmount;

        float dontLeanProgress = velociraptor.dontLean.getAnimationProgressSinSqrt();

        model.bob(waist, 1F * speed, height, false, limbSwing, limbSwingAmount);
        model.bob(leftThigh, 1F * speed, height, false, limbSwing, limbSwingAmount);
        model.bob(rightThigh, 1F * speed, height, false, limbSwing, limbSwingAmount);
        model.walk(shoulders, 1F * speed, 0.2F, true, 1, 0, limbSwing, limbSwingAmount);
        model.walk(chest, 1F * speed, 0.2F, false, 0.5F, 0, limbSwing, limbSwingAmount);

        model.walk(leftThigh, 0.5F * speed, 0.7F, false, 3.14F, 0.2F, limbSwing, limbSwingAmount);
        model.walk(leftShin, 0.5F * speed, 0.6F, false, 1.5F, 0.3F, limbSwing, limbSwingAmount);
        model.walk(leftUpperFoot, 0.5F * speed, 0.8F, false, -1F, -0.1F, limbSwing, limbSwingAmount);
        model.walk(leftFoot, 0.5F * speed, 1.5F, true, -1F, 1F, limbSwing, limbSwingAmount);

        model.walk(rightThigh, 0.5F * speed, 0.7F, true, 3.14F, 0.2F, limbSwing, limbSwingAmount);
        model.walk(rightShin, 0.5F * speed, 0.6F, true, 1.5F, 0.3F, limbSwing, limbSwingAmount);
        model.walk(rightUpperFoot, 0.5F * speed, 0.8F, true, -1F, -0.1F, limbSwing, limbSwingAmount);
        model.walk(rightFoot, 0.5F * speed, 1.5F, false, -1F, 1F, limbSwing, limbSwingAmount);

        shoulders.rotationPointY -= 0.5 * limbSwingAmount * dontLeanProgress;
        shoulders.rotationPointZ -= 0.5 * limbSwingAmount * dontLeanProgress;
        shoulders.rotateAngleX += 0.6 * limbSwingAmount * dontLeanProgress;
        chest.rotateAngleX += 0.1 * limbSwingAmount * dontLeanProgress;
        neck1.rotateAngleX += 0.1 * limbSwingAmount * dontLeanProgress;
        neck2.rotateAngleX += 0.1 * limbSwingAmount * dontLeanProgress;
        neck3.rotateAngleX -= 0.2 * limbSwingAmount * dontLeanProgress;
        neck4.rotateAngleX -= 0.2 * limbSwingAmount * dontLeanProgress;
        head.rotateAngleX -= 0.3 * limbSwingAmount * dontLeanProgress;

        model.chainSwing(tailParts, 0.5F * speed, -0.1F, 2, limbSwing, limbSwingAmount);
        model.chainWave(tailParts, 1F * speed, -0.1F, 2.5F, limbSwing, limbSwingAmount);
        model.chainWave(bodyParts, 1F * speed, -0.1F, 4, limbSwing, limbSwingAmount);

        model.chainWave(rightArmParts, 1F * speed, -0.3F, 4, limbSwing, limbSwingAmount);
        model.chainWave(leftArmParts, 1F * speed, -0.3F, 4, limbSwing, limbSwingAmount);

        // Idling
        model.chainWave(tailParts, 0.1F, 0.05F, 2, entity.ticksExisted, 1F);
        model.chainWave(bodyParts, 0.1F, -0.03F, 5, entity.ticksExisted, 1F);
        model.chainWave(rightArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);
        model.chainWave(leftArmParts, 0.1F, -0.1F, 4, entity.ticksExisted, 1F);

        velociraptor.tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
