package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurGallimimus;
import org.jurassicraft.common.entity.EntityGallimimus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationGallimimus extends DinosaurAnimator
{
    public AnimationGallimimus()
    {
        super(new DinosaurGallimimus());
    }

    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = parModel.animator;

        //        f = entity.ticksExisted;
        //        f1 = 1F;

        float globalSpeed = 0.8F;
        float globalDegree = 1.0F;
        float globalHeight = 1.0F;

        MowzieModelRenderer neck1 = parModel.getCube("neck1");
        MowzieModelRenderer neck2 = parModel.getCube("neck2");
        MowzieModelRenderer neck3 = parModel.getCube("neck3");
        MowzieModelRenderer neck4 = parModel.getCube("neck4");
        MowzieModelRenderer neck5 = parModel.getCube("neck5");

        //TODO
        MowzieModelRenderer throat = parModel.getCube("Throat");

        MowzieModelRenderer tail1 = parModel.getCube("tail1");
        MowzieModelRenderer tail2 = parModel.getCube("tail2");
        MowzieModelRenderer tail3 = parModel.getCube("tail3");
        MowzieModelRenderer tail4 = parModel.getCube("tail4");
        MowzieModelRenderer tail5 = parModel.getCube("tail5");
        MowzieModelRenderer tail6 = parModel.getCube("tail6");

        MowzieModelRenderer body1 = parModel.getCube("body1");
        MowzieModelRenderer body2 = parModel.getCube("body2");
        MowzieModelRenderer body3 = parModel.getCube("body3");

        MowzieModelRenderer head = parModel.getCube("Head Base");

        MowzieModelRenderer rightThigh = parModel.getCube("thigh right");
        MowzieModelRenderer leftThigh = parModel.getCube("thigh left");

        MowzieModelRenderer rightCalf1 = parModel.getCube("leg right");
        MowzieModelRenderer leftCalf1 = parModel.getCube("leg left");

        MowzieModelRenderer rightCalf2 = parModel.getCube("upperfoot right");
        MowzieModelRenderer leftCalf2 = parModel.getCube("upperfoot left");

        MowzieModelRenderer rightFoot = parModel.getCube("foot right");
        MowzieModelRenderer leftFoot = parModel.getCube("foot left");

        MowzieModelRenderer upperArmLeft = parModel.getCube("Arm UPPER Left");
        MowzieModelRenderer upperArmRight = parModel.getCube("Arm UPPER Right");

        MowzieModelRenderer lowerArmRight = parModel.getCube("Arm Mid Right");
        MowzieModelRenderer lowerArmLeft = parModel.getCube("Arm Mid Left");

        MowzieModelRenderer handRight = parModel.getCube("Hand RIGHT");
        MowzieModelRenderer handLeft = parModel.getCube("Hand LEFT");

        MowzieModelRenderer[] body = new MowzieModelRenderer[]{head, neck5, neck4, neck3, neck2, neck1, body1, body2, body3};

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[]{handLeft, lowerArmLeft, upperArmLeft};
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[]{handRight, lowerArmRight, upperArmRight};

        parModel.bob(body3, 1F * globalSpeed, globalHeight, false, f, f1);
        parModel.bob(leftThigh, 1F * globalSpeed, globalHeight, false, f, f1);
        parModel.bob(rightThigh, 1F * globalSpeed, globalHeight, false, f, f1);
        body3.rotationPointX += -f1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);
        rightThigh.rotationPointX += -f1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);
        leftThigh.rotationPointX += -f1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);

        body1.rotateAngleZ += f1 * 0.1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);
        head.rotateAngleZ -= f1 * 0.1 * globalHeight * Math.cos(f * 0.5 * globalSpeed);

        parModel.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F + 0.1F, 0.2F, f, f1);
        parModel.walk(leftCalf1, 0.5F * globalSpeed, 0.7F * globalDegree, true, 2F + 0.1F, 0F, f, f1);
        parModel.walk(leftCalf2, 0.5F * globalSpeed, 0.5F * globalDegree, false, 3F + 0.1F, 0F, f, f1);
        parModel.walk(leftFoot, 0.5F * globalSpeed, 0.5F * globalDegree, true, 1.5F + 0.1F, 1F, f, f1);

        parModel.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F + 0.1F, 0.2F, f, f1);
        parModel.walk(rightCalf1, 0.5F * globalSpeed, 0.7F * globalDegree, false, 2F + 0.1F, 0F, f, f1);
        parModel.walk(rightCalf2, 0.5F * globalSpeed, 0.5F * globalDegree, true, 3F + 0.1F, 0F, f, f1);
        parModel.walk(rightFoot, 0.5F * globalSpeed, 0.5F * globalDegree, false, 1.5F + 0.1F, 1F, f, f1);

        parModel.walk(upperArmRight, 1 * globalSpeed, 0.3F, true, 0.3F, -0.3F, f, f1);
        parModel.walk(upperArmLeft, 1 * globalSpeed, 0.3F, true, 0.3F, -0.3F, f, f1);
        parModel.walk(lowerArmRight, 1 * globalSpeed, 0.3F, true, 0.6F, -0.7F, f, f1);
        parModel.walk(lowerArmLeft, 1 * globalSpeed, 0.3F, true, 0.6F, -0.7F, f, f1);
        parModel.walk(handLeft, 1 * globalSpeed, 0.3F, true, 0.9F, 1F, f, f1);
        parModel.walk(handRight, 1 * globalSpeed, 0.3F, true, 0.9F, 1F, f, f1);

        parModel.walk(body1, globalSpeed, 0.1F, true, 1.5F, -0.2F, f, f1);
        parModel.walk(neck1, globalSpeed, 0.1F, true, 1.5F, -0.4F, f, f1);
        parModel.walk(neck2, globalSpeed, 0.1F, true, 1.5F, -0.5F, f, f1);
        parModel.walk(neck3, globalSpeed, 0.1F, false, 1.5F, 0.4F, f, f1);
        parModel.walk(neck4, globalSpeed, 0.1F, false, 1.5F, 0.3F, f, f1);
        parModel.walk(neck5, globalSpeed, 0.1F, false, 1.5F, 0.3F, f, f1);
        body1.rotationPointZ += 1.1 * f1;
        neck1.rotationPointZ += 1.6 * f1;
        neck2.rotationPointZ += 1.4 * f1;
        throat.rotationPointZ -= 3 * f1;

        parModel.chainWave(tail, 1 * globalSpeed, -0.05F, 1, f, f1);
        parModel.chainSwing(tail, 0.5F * globalSpeed, 0.1F, 2, f, f1);

        int frame = parEntity.ticksExisted;

        parModel.chainWave(tail, 0.1F, 0.05F, 1, frame, 1.0F);
        parModel.chainWave(body, 0.1F, -0.05F, 4, frame, 1.0F);
        parModel.chainWave(armRight, 0.1F, -0.15F, 4, frame, 1.0F);
        parModel.chainWave(armLeft, 0.1F, -0.15F, 4, frame, 1.0F);

        ((EntityGallimimus) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
