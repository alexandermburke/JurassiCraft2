package net.timeless.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.timeless.jurassicraft.api.animation.Animator;

public class AnimationDilophosaurus implements IModelAnimator
{
    private final Animator animator;

    public AnimationDilophosaurus()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        boolean scary = false;

        MowzieModelRenderer frillLeftBottom = model.getCube("frill4");
        MowzieModelRenderer frillLeftTop = model.getCube("frill2");

        MowzieModelRenderer frillRightBottom = model.getCube("frill3");
        MowzieModelRenderer frillRightTop = model.getCube("frill1");

        frillLeftBottom.showModel = scary;
        frillLeftTop.showModel = scary;
        frillRightBottom.showModel = scary;
        frillRightTop.showModel = scary;

        frillLeftTop.rotateAngleY = (float) Math.toRadians(180);
        frillLeftTop.rotationPointX += 10F;

        frillLeftBottom.rotateAngleY = (float) Math.toRadians(180);
        frillLeftBottom.rotationPointX += 10F;

        float globalSpeed = 0.8F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;

        // f = entity.ticksExisted;
        // f1 = 1F;

        MowzieModelRenderer head = model.getCube("head");

        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");

        MowzieModelRenderer body1 = model.getCube("body1");
        MowzieModelRenderer body2 = model.getCube("body2");
        MowzieModelRenderer body3 = model.getCube("body3");

        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");

        MowzieModelRenderer rightThigh = model.getCube("thigh1");
        MowzieModelRenderer leftThigh = model.getCube("thigh2");

        MowzieModelRenderer lowerRightLeg = model.getCube("leg1");
        MowzieModelRenderer lowerLeftLeg = model.getCube("leg2");

        MowzieModelRenderer upperRightFoot = model.getCube("upperfoot1");
        MowzieModelRenderer upperLeftFoot = model.getCube("upperfoot2");

        MowzieModelRenderer rightFoot = model.getCube("foot1");
        MowzieModelRenderer leftFoot = model.getCube("foot2");

        MowzieModelRenderer upperArmRight = model.getCube("arm1");
        MowzieModelRenderer upperArmLeft = model.getCube("arm2");

        MowzieModelRenderer lowerArmRight = model.getCube("forearm1");
        MowzieModelRenderer lowerArmLeft = model.getCube("forearm2");

        MowzieModelRenderer handRight = model.getCube("hand1");
        MowzieModelRenderer handLeft = model.getCube("hand2");

        // MowzieModelRenderer upperJaw = model.getCube("upperjaw");
        // MowzieModelRenderer lowerJaw = model.getCube("down_jaw");

        MowzieModelRenderer[] body = new MowzieModelRenderer[] { head, neck4, neck3, neck2, neck1, body1, body2, body3 };
        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail5, tail4, tail3, tail2, tail1 };

        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { handRight, lowerArmRight, upperArmRight };
        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { handLeft, lowerArmLeft, upperArmLeft };

        model.bob(body3, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.bob(leftThigh, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(rightThigh, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.chainWave(body, globalSpeed * 1.0F, globalHeight * 0.02F, 3, f, f1);
        model.chainWave(tail, globalSpeed * 1.0F, globalHeight * 0.05F, 2, f, f1);

        model.walk(rightThigh, globalSpeed * 0.5F, globalDegree * 0.8F, true, -0.3F, 0.2F, f, f1);
        model.walk(leftThigh, globalSpeed * 0.5F, globalDegree * 0.8F, false, -0.3F, 0.2F, f, f1);

        model.walk(lowerLeftLeg, globalSpeed * 0.5F, globalDegree * 1F, false, -1.3F, 0.4F, f, f1);
        model.walk(lowerRightLeg, globalSpeed * 0.5F, globalDegree * 1F, true, -1.3F, 0.4F, f, f1);

        model.walk(upperLeftFoot, globalSpeed * 0.5F, globalDegree * 1F, true, -0.3F, 0F, f, f1);
        model.walk(upperRightFoot, globalSpeed * 0.5F, globalDegree * 1F, false, -0.3F, 0F, f, f1);

        model.walk(leftFoot, globalSpeed * 0.5F, globalDegree * 1.5F, false, -0.8F, 0.3F, f, f1);
        model.walk(rightFoot, globalSpeed * 0.5F, globalDegree * 1.5F, true, -0.8F, 0.3F, f, f1);

        model.chainWave(armRight, globalSpeed * 1F, globalHeight * 0.25F, 3, f, f1);
        model.chainWave(armLeft, globalSpeed * 1F, globalHeight * 0.25F, 3, f, f1);

        model.walk(neck1, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck2, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck3, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck4, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);

        leftThigh.rotationPointY -= 2F * f1 * Math.cos(f * 0.5F * globalSpeed);
        rightThigh.rotationPointY -= 2F * f1 * Math.cos(f * 0.5F * globalSpeed);

        int ticksExisted = entity.ticksExisted;

        model.chainWave(tail, 0.1F, 0.05F, 2, ticksExisted, 1F);
        model.chainWave(body, 0.1F, 0.03F, 5, ticksExisted, 1F);
        model.chainWave(armRight, 0.1F, 0.1F, 4, ticksExisted, 1F);
        model.chainWave(armLeft, 0.1F, 0.1F, 4, ticksExisted, 1F);

        model.faceTarget(head, 2, rotationYaw, rotationPitch);
        model.faceTarget(neck2, 2, rotationYaw, rotationPitch);
    }
}
