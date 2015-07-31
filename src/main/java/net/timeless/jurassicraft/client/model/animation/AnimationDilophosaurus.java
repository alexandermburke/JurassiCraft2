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
public class AnimationDilophosaurus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

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

        float globalSpeed = 0.2F;
        float globalDegree = 0.77F;
        float globalHeight = 2F;

        //        f = entity.ticksExisted;
        //        f1 = 1F;

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

        model.faceTarget(head, 5, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 5, rotationYaw, rotationPitch);
        model.faceTarget(neck2, 5, rotationYaw, rotationPitch);
        model.faceTarget(neck3, 5, rotationYaw, rotationPitch);
        model.faceTarget(neck4, 5, rotationYaw, rotationPitch);

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
    }
}
