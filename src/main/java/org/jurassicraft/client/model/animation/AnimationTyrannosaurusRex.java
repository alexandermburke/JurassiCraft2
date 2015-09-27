package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurTyrannosaurus;
import org.jurassicraft.common.entity.EntityTyrannosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationTyrannosaurusRex extends DinosaurAnimator
{
    public AnimationTyrannosaurusRex()
    {
        super(new DinosaurTyrannosaurus());
    }

    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        // Walking-dependent animation
        float globalSpeed = 0.45F;
        float globalDegree = 0.5F;
        float height = 1.0F;

        MowzieModelRenderer stomach = parModel.getCube("Body 2");
        MowzieModelRenderer chest = parModel.getCube("Body 3");
        MowzieModelRenderer head = parModel.getCube("Head");
        MowzieModelRenderer waist = parModel.getCube("Body 1");

        MowzieModelRenderer neck1 = parModel.getCube("Neck 1");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer neck4 = parModel.getCube("Neck 4");
        MowzieModelRenderer neck5 = parModel.getCube("Neck 5");

        MowzieModelRenderer tail1 = parModel.getCube("Tail 1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");

        MowzieModelRenderer throat1 = parModel.getCube("Throat 1");
        MowzieModelRenderer throat2 = parModel.getCube("Throat 2");
        MowzieModelRenderer throat3 = parModel.getCube("Throat 3");

        MowzieModelRenderer lowerJaw = parModel.getCube("Lower Jaw");

        MowzieModelRenderer handLeft = parModel.getCube("Hand Left");
        MowzieModelRenderer lowerArmLeft = parModel.getCube("Lower Arm Left");
        MowzieModelRenderer upperArmLeft = parModel.getCube("Upper Arm Left");

        MowzieModelRenderer handRight = parModel.getCube("Hand Right");
        MowzieModelRenderer lowerArmRight = parModel.getCube("Lower Arm Right");
        MowzieModelRenderer upperArmRight = parModel.getCube("Upper Arm Right");

        MowzieModelRenderer leftThigh = parModel.getCube("Left Thigh");
        MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");

        MowzieModelRenderer leftCalf1 = parModel.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = parModel.getCube("Left Calf 2");
        MowzieModelRenderer leftFoot = parModel.getCube("Foot Left");

        MowzieModelRenderer rightCalf1 = parModel.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = parModel.getCube("Right Calf 2");
        MowzieModelRenderer rightFoot = parModel.getCube("Foot Right");

        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[]{head, neck5, neck4, neck3, neck2, neck1, chest, stomach, waist};
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[]{handLeft, lowerArmLeft, upperArmLeft};
        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[]{handRight, lowerArmRight, upperArmRight};

        parModel.bob(waist, 1F * globalSpeed, height, false, f, f1);
        parModel.bob(leftThigh, 1F * globalSpeed, height, false, f, f1);
        parModel.bob(rightThigh, 1F * globalSpeed, height, false, f, f1);
        leftThigh.rotationPointY -= -2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        parModel.walk(neck1, 1F * globalSpeed, 0.15F, false, 0F, 0.2F, f, f1);
        parModel.walk(head, 1F * globalSpeed, 0.15F, true, 0F, -0.2F, f, f1);

        parModel.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.4F, f, f1);
        parModel.walk(leftCalf1, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        parModel.walk(leftCalf2, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        parModel.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.3F, f, f1);

        parModel.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.4F, f, f1);
        parModel.walk(rightCalf1, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        parModel.walk(rightCalf2, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        parModel.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.3F, f, f1);

        parModel.chainWave(tailParts, 1F * globalSpeed, 0.05F, 2, f, f1);
        parModel.chainWave(bodyParts, 1F * globalSpeed, 0.05F, 3, f, f1);
        parModel.chainWave(leftArmParts, 1F * globalSpeed, 0.2F, 1, f, f1);
        parModel.chainWave(rightArmParts, 1F * globalSpeed, 0.2F, 1, f, f1);

        // Idling
        parModel.chainWave(bodyParts, 0.1F, -0.03F, 3, parEntity.ticksExisted, 1.0F);
        parModel.chainWave(rightArmParts, -0.1F, 0.2F, 4, parEntity.ticksExisted, 1.0F);
        parModel.chainWave(leftArmParts, -0.1F, 0.2F, 4, parEntity.ticksExisted, 1.0F);

        parModel.chainSwing(tailParts, 0.1F, 0.05F - (0.05F), 1, parEntity.ticksExisted, 1.0F - 0.6F);
        parModel.chainWave(tailParts, 0.1F, -0.1F, 2, parEntity.ticksExisted, 1.0F - 0.6F);

        ((EntityTyrannosaurus)parEntity).tailBuffer.applyChainSwingBuffer(tailParts);
    }
}

