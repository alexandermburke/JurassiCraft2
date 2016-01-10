package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityDilophosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationDilophosaurus extends DinosaurAnimator
{
    public AnimationDilophosaurus()
    {
        super(JCEntityRegistry.dilophosaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur entity)
    {
        boolean scary = false;

        MowzieModelRenderer frillLeftBottom = model.getCube("Frill Lower Left");
        MowzieModelRenderer frillLeftTop = model.getCube("Frill Upper Left");

        MowzieModelRenderer frillRightBottom = model.getCube("Frill Lower Right");
        MowzieModelRenderer frillRightTop = model.getCube("Frill Upper Right");

        frillLeftBottom.showModel = scary;
        frillLeftTop.showModel = scary;
        frillRightBottom.showModel = scary;
        frillRightTop.showModel = scary;

        frillLeftTop.rotateAngleY = (float) Math.toRadians(180);
        frillLeftTop.rotationPointX += 10F;

        frillLeftBottom.rotateAngleY = (float) Math.toRadians(180);
        frillLeftBottom.rotationPointX += 10F;

        float globalSpeed = 0.6F;
        float globalDegree = 0.77F;
        float globalHeight = 2F;

        // f = entity.ticksExisted;
        // f1 = 1F;

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer neck1 = model.getCube("Neck Base");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");
        MowzieModelRenderer neck5 = model.getCube("Neck 5");
        MowzieModelRenderer neck6 = model.getCube("Neck 6");

        MowzieModelRenderer body1 = model.getCube("Body FRONT");
        MowzieModelRenderer body2 = model.getCube("Body MIDDLE");
        MowzieModelRenderer body3 = model.getCube("Body REAR");

        MowzieModelRenderer tail1 = model.getCube("Tail BASE");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer rightThigh = model.getCube("Leg Right UPPER");
        MowzieModelRenderer leftThigh = model.getCube("Leg Left UPPER");

        MowzieModelRenderer lowerRightLeg = model.getCube("Leg Right MID");
        MowzieModelRenderer lowerLeftLeg = model.getCube("Leg Left MID");

        MowzieModelRenderer upperRightFoot = model.getCube("Leg Right BOTTOM");
        MowzieModelRenderer upperLeftFoot = model.getCube("Leg Left BOTTOM");

        MowzieModelRenderer rightFoot = model.getCube("Foot Right");
        MowzieModelRenderer leftFoot = model.getCube("Foot Left");

        MowzieModelRenderer upperArmRight = model.getCube("Right arm");
        MowzieModelRenderer upperArmLeft = model.getCube("Left arm");

        MowzieModelRenderer lowerArmRight = model.getCube("Right forearm");
        MowzieModelRenderer lowerArmLeft = model.getCube("Left forearm");

        MowzieModelRenderer handRight = model.getCube("Right hand");
        MowzieModelRenderer handLeft = model.getCube("Left hand");

        // MowzieModelRenderer upperJaw = model.getCube("upperjaw");
        // MowzieModelRenderer lowerJaw = model.getCube("down_jaw");

        MowzieModelRenderer[] body = new MowzieModelRenderer[] { head, neck6, neck5, neck4, neck3, neck2, neck1, body1, body2, body3 };
        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { handRight, lowerArmRight, upperArmRight };
        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { handLeft, lowerArmLeft, upperArmLeft };

        neck4.rotateAngleZ += (rotationYaw / (180f / (float) Math.PI)) / 5;
        neck3.rotateAngleZ += (rotationYaw / (180f / (float) Math.PI)) / 5;
        head.rotateAngleZ += (rotationYaw / (180f / (float) Math.PI)) / 5;

        model.bob(body3, 1F * globalSpeed, globalHeight * 0.7F, false, f, f1);
        model.bob(leftThigh, 1F * globalSpeed, globalHeight * 0.7F, false, f, f1);
        model.bob(rightThigh, 1F * globalSpeed, globalHeight * 0.7F, false, f, f1);
        model.walk(body3, 1F * globalSpeed, globalHeight * 0.05F, true, 0.1F, 0F, f, f1);
        model.chainWave(body, 1F * globalSpeed, -0.03F * globalHeight, 3.5F, f, f1);

        model.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.4F, f, f1);
        model.walk(lowerLeftLeg, 0.5F * globalSpeed, 0.8F * globalDegree, true, 1F, 0F, f, f1);
        model.walk(upperLeftFoot, 0.5F * globalSpeed, 0.5F * globalDegree, false, 0F, 0F, f, f1);
        model.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.7F, f, f1);

        model.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.4F, f, f1);
        model.walk(lowerRightLeg, 0.5F * globalSpeed, 0.8F * globalDegree, false, 1F, 0F, f, f1);
        model.walk(upperRightFoot, 0.5F * globalSpeed, 0.5F * globalDegree, true, 0F, 0F, f, f1);
        model.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.7F, f, f1);

        model.chainSwing(tail, 0.5F * globalSpeed, -0.1F, 2, f, f1);
        model.chainWave(tail, 1F * globalSpeed, -0.05F, 2, f, f1);
        model.chainWave(armRight, 1F * globalSpeed, 0.2F, 3, f, f1);
        model.chainWave(armLeft, 1F * globalSpeed, 0.2F, 3, f, f1);

        int ticksExisted = entity.ticksExisted;

        model.chainWave(tail, 0.15F, -0.03F, 2, ticksExisted, 1.0F);
        model.chainWave(body, 0.15F, 0.03F, 3.5F, ticksExisted, 1.0F);
        model.chainWave(armRight, 0.15F, -0.1F, 4, ticksExisted, 1.0F);
        model.chainWave(armLeft, 0.15F, -0.1F, 4, ticksExisted, 1.0F);
        model.chainSwing(tail, 0.15F, -0.1F, 3, ticksExisted, 1.0F);

        ((EntityDilophosaurus) entity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
