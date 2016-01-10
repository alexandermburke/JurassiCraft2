package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.common.animation.Animator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityGallimimus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationGallimimus extends DinosaurAnimator
{
    public AnimationGallimimus()
    {
        super(JCEntityRegistry.gallimimus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = model.animator;

        // f = entity.ticksExisted;
        // f1 = 1F;

        float globalSpeed = 0.8F;
        float globalDegree = 1.0F;
        float globalHeight = 1.0F;

        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");
        MowzieModelRenderer neck5 = model.getCube("neck5");

        // TODO
        MowzieModelRenderer throat = model.getCube("Throat");

        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");
        MowzieModelRenderer tail6 = model.getCube("tail6");

        MowzieModelRenderer body1 = model.getCube("body1");
        MowzieModelRenderer body2 = model.getCube("body2");
        MowzieModelRenderer body3 = model.getCube("body3");

        MowzieModelRenderer head = model.getCube("Head Base");

        MowzieModelRenderer rightThigh = model.getCube("thigh right");
        MowzieModelRenderer leftThigh = model.getCube("thigh left");

        MowzieModelRenderer rightCalf1 = model.getCube("leg right");
        MowzieModelRenderer leftCalf1 = model.getCube("leg left");

        MowzieModelRenderer rightCalf2 = model.getCube("upperfoot right");
        MowzieModelRenderer leftCalf2 = model.getCube("upperfoot left");

        MowzieModelRenderer rightFoot = model.getCube("foot right");
        MowzieModelRenderer leftFoot = model.getCube("foot left");

        MowzieModelRenderer upperArmLeft = model.getCube("Arm UPPER Left");
        MowzieModelRenderer upperArmRight = model.getCube("Arm UPPER Right");

        MowzieModelRenderer lowerArmRight = model.getCube("Arm Mid Right");
        MowzieModelRenderer lowerArmLeft = model.getCube("Arm Mid Left");

        MowzieModelRenderer handRight = model.getCube("Hand RIGHT");
        MowzieModelRenderer handLeft = model.getCube("Hand LEFT");

        MowzieModelRenderer[] body = new MowzieModelRenderer[] { head, neck5, neck4, neck3, neck2, neck1, body1, body2, body3 };

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { handLeft, lowerArmLeft, upperArmLeft };
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { handRight, lowerArmRight, upperArmRight };

        model.bob(body3, 1F * globalSpeed, globalHeight, false, f, f1);
        model.bob(leftThigh, 1F * globalSpeed, globalHeight, false, f, f1);
        model.bob(rightThigh, 1F * globalSpeed, globalHeight, false, f, f1);
        body3.rotationPointX += -f1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);
        rightThigh.rotationPointX += -f1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);
        leftThigh.rotationPointX += -f1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);

        body1.rotateAngleZ += f1 * 0.1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);
        head.rotateAngleZ -= f1 * 0.1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);

        model.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F + 0.1F, 0.2F, f, f1);
        model.walk(leftCalf1, 0.5F * globalSpeed, 0.7F * globalDegree, true, 2F + 0.1F, 0F, f, f1);
        model.walk(leftCalf2, 0.5F * globalSpeed, 0.5F * globalDegree, false, 3F + 0.1F, 0F, f, f1);
        model.walk(leftFoot, 0.5F * globalSpeed, 0.5F * globalDegree, true, 1.5F + 0.1F, 1F, f, f1);

        model.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F + 0.1F, 0.2F, f, f1);
        model.walk(rightCalf1, 0.5F * globalSpeed, 0.7F * globalDegree, false, 2F + 0.1F, 0F, f, f1);
        model.walk(rightCalf2, 0.5F * globalSpeed, 0.5F * globalDegree, true, 3F + 0.1F, 0F, f, f1);
        model.walk(rightFoot, 0.5F * globalSpeed, 0.5F * globalDegree, false, 1.5F + 0.1F, 1F, f, f1);

        model.walk(upperArmRight, 1 * globalSpeed, 0.3F, true, 0.3F, -0.3F, f, f1);
        model.walk(upperArmLeft, 1 * globalSpeed, 0.3F, true, 0.3F, -0.3F, f, f1);
        model.walk(lowerArmRight, 1 * globalSpeed, 0.3F, true, 0.6F, -0.7F, f, f1);
        model.walk(lowerArmLeft, 1 * globalSpeed, 0.3F, true, 0.6F, -0.7F, f, f1);
        model.walk(handLeft, 1 * globalSpeed, 0.3F, true, 0.9F, 1F, f, f1);
        model.walk(handRight, 1 * globalSpeed, 0.3F, true, 0.9F, 1F, f, f1);

        model.walk(body1, globalSpeed, 0.1F, true, 1.5F, -0.2F, f, f1);
        model.walk(neck1, globalSpeed, 0.1F, true, 1.5F, -0.4F, f, f1);
        model.walk(neck2, globalSpeed, 0.1F, true, 1.5F, -0.5F, f, f1);
        model.walk(neck3, globalSpeed, 0.1F, false, 1.5F, 0.4F, f, f1);
        model.walk(neck4, globalSpeed, 0.1F, false, 1.5F, 0.3F, f, f1);
        model.walk(neck5, globalSpeed, 0.1F, false, 1.5F, 0.3F, f, f1);
        body1.rotationPointZ += 1.1 * f1;
        neck1.rotationPointZ += 1.6 * f1;
        neck2.rotationPointZ += 1.4 * f1;
        throat.rotationPointZ -= 3 * f1;

        model.chainWave(tail, 1 * globalSpeed, -0.05F, 1, f, f1);
        model.chainSwing(tail, 0.5F * globalSpeed, 0.1F, 2, f, f1);

        int frame = parEntity.ticksExisted;

        model.chainWave(tail, 0.1F, 0.05F, 1, frame, 1.0F);
        model.chainWave(body, 0.1F, -0.05F, 4, frame, 1.0F);
        model.chainWave(armRight, 0.1F, -0.15F, 4, frame, 1.0F);
        model.chainWave(armLeft, 0.1F, -0.15F, 4, frame, 1.0F);

        ((EntityGallimimus) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
