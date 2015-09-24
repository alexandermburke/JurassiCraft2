package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurTherizinosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationTherizinosaurus extends DinosaurAnimator
{

    public AnimationTherizinosaurus()
    {
        super(new DinosaurTherizinosaurus());
    }

    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");
        MowzieModelRenderer bodyHips = parModel.getCube("Body hips");
        MowzieModelRenderer rightCalf1 = parModel.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = parModel.getCube("Right Calf 2");
        MowzieModelRenderer footRight = parModel.getCube("Foot Right");
        MowzieModelRenderer tail1 = parModel.getCube("Tail 1");
        MowzieModelRenderer bodyMain = parModel.getCube("Body main");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail2Feathers = parModel.getCube("Tail 2 feathers");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail3Feathers = parModel.getCube("Tail 3 feathers");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail4Feathers = parModel.getCube("Tail 4 feathers");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");
        MowzieModelRenderer tail5Feathers = parModel.getCube("Tail 5 feathers");
        MowzieModelRenderer tail6Feathers = parModel.getCube("Tail 6 feathers");
        MowzieModelRenderer tail6FeathersR = parModel.getCube("Tail 6 feathers r");
        MowzieModelRenderer tail6FeathersL = parModel.getCube("Tail 6 feathers l");
        MowzieModelRenderer tail5FeathersR = parModel.getCube("Tail 5 feathers r");
        MowzieModelRenderer tail5FeathersL = parModel.getCube("Tail 5 feathers l");
        MowzieModelRenderer tail4FeathersR = parModel.getCube("Tail 4 feathers r");
        MowzieModelRenderer tail4FeathersL = parModel.getCube("Tail 4 feathers l");
        MowzieModelRenderer tail3FeathersR = parModel.getCube("Tail 3 feathers r");
        MowzieModelRenderer tail3FeathersL = parModel.getCube("Tail 3 feathers l");
        MowzieModelRenderer bodyShoulders = parModel.getCube("Body shoulders");
        MowzieModelRenderer bodyMain1 = parModel.getCube("Body main 1");
        MowzieModelRenderer neckBase = parModel.getCube("Neck base");
        MowzieModelRenderer neck1 = parModel.getCube("Neck 1");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer neck4 = parModel.getCube("Neck 4");
        MowzieModelRenderer neck5 = parModel.getCube("Neck 5");
        MowzieModelRenderer neck4feathers = parModel.getCube("Neck 4 feathers");
        MowzieModelRenderer neck6 = parModel.getCube("Neck 6");
        MowzieModelRenderer neck5Feathers = parModel.getCube("Neck 5 feathers");
        MowzieModelRenderer neck7 = parModel.getCube("Neck 7");
        MowzieModelRenderer neck6Feathers = parModel.getCube("Neck 6 feathers");
        MowzieModelRenderer head = parModel.getCube("Head");
        MowzieModelRenderer neck7Feathers = parModel.getCube("Neck 7 feathers");
        MowzieModelRenderer snout = parModel.getCube("Snout");
        MowzieModelRenderer lowerJaw = parModel.getCube("Lower Jaw");
        MowzieModelRenderer snoutRoof = parModel.getCube("Snout roof");
        MowzieModelRenderer upperJaw = parModel.getCube("Upper Jaw");
        MowzieModelRenderer neck7FeathersR = parModel.getCube("Neck 7 feathers r");
        MowzieModelRenderer neck7FeathersL = parModel.getCube("Neck 7 feathers l");
        MowzieModelRenderer neck6FeathersR = parModel.getCube("Neck 6 feathers r");
        MowzieModelRenderer neck6FeathersL = parModel.getCube("Neck 6 feathers l");
        MowzieModelRenderer neck5FeathersR = parModel.getCube("Neck 5 feathers r");
        MowzieModelRenderer neck5FeathersL = parModel.getCube("Neck 5 feathers l");
        MowzieModelRenderer neck4FeathersR = parModel.getCube("Neck 4 feathers r");
        MowzieModelRenderer neck4FeathersL = parModel.getCube("Neck 4 feathers l");
        MowzieModelRenderer lowerArmRight = parModel.getCube("Lower Arm Right");
        MowzieModelRenderer lowerArmRight1 = parModel.getCube("Lower Arm Right 1");
        MowzieModelRenderer rightHand = parModel.getCube("Right hand");
        MowzieModelRenderer armRightFeathers = parModel.getCube("Arm right feathers");
        MowzieModelRenderer rightFinger1 = parModel.getCube("Right finger 1");
        MowzieModelRenderer rightFinger2 = parModel.getCube("Right finger 2");
        MowzieModelRenderer righFinger3 = parModel.getCube("Right finger 3");
        MowzieModelRenderer rF1mid = parModel.getCube("RF1 mid");
        MowzieModelRenderer rF1end = parModel.getCube("RF1 end");
        MowzieModelRenderer rF2mid = parModel.getCube("RF2 mid");
        MowzieModelRenderer rF2end = parModel.getCube("RF2 end");
        MowzieModelRenderer rF3mid = parModel.getCube("RF3 mid");
        MowzieModelRenderer rF3end = parModel.getCube("RF3 end");
        MowzieModelRenderer lowerArmLeft = parModel.getCube("Lower Arm LEFT");
        MowzieModelRenderer lowerArmLeft1 = parModel.getCube("Lower Arm LEFT 1");
        MowzieModelRenderer leftHand = parModel.getCube("Left hand");
        MowzieModelRenderer armLeftFeathers = parModel.getCube("Arm left feathers");
        MowzieModelRenderer leftfinger1 = parModel.getCube("Left finger 1");
        MowzieModelRenderer leftfinger2 = parModel.getCube("Left finger 2");
        MowzieModelRenderer leftfinger3 = parModel.getCube("Left finger 3");
        MowzieModelRenderer lF1mid = parModel.getCube("LF1 mid");
        MowzieModelRenderer lF1end = parModel.getCube("LF1 end");
        MowzieModelRenderer lF2mid = parModel.getCube("LF1 end");
        MowzieModelRenderer lF2end = parModel.getCube("LF2 end");
        MowzieModelRenderer lF3mid = parModel.getCube("LF3 mid");
        MowzieModelRenderer lF3end = parModel.getCube("LF3 mid");
        MowzieModelRenderer leftThigh = parModel.getCube("Left Thigh");
        MowzieModelRenderer leftCalf1 = parModel.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = parModel.getCube("Left Calf 2");
        MowzieModelRenderer footLeft = parModel.getCube("Foot Left");

        MowzieModelRenderer[] neck = new MowzieModelRenderer[]{head, neck7, neck6, neck5, neck4, neck3, neck2, neck1, neckBase, bodyShoulders};
        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail1, tail2, tail3, tail4, tail5, tail6};
        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[]{lowerArmLeft, lowerArmLeft1, leftHand};
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[]{lowerArmRight, lowerArmRight1, rightHand};

        int frame = parEntity.ticksExisted;

//     float globalHeight = 2F;
//     float frontOffset = -1.35f;

        //The tail must always be up when the neck is down
        float speed = 0.75F;
        float height = 3F;

        parModel.bob(bodyHips, 1F * speed, height, false, f, f1);
        parModel.flap(bodyHips, 0.5F * speed, 0.5F, false, 0, 0, f, f1);
        parModel.flap(bodyMain, 0.5F * speed, 0.1F, true, 0, 0, f, f1);
        parModel.flap(bodyShoulders, 0.5F * speed, 0.4F, true, 0, 0, f, f1);
        parModel.flap(tail1, 0.5F * speed, 0.2F, true, 0, 0, f, f1);
        parModel.flap(tail3, 0.5F * speed, 0.2F, true, 0, 0, f, f1);
        parModel.flap(tail5, 0.5F * speed, 0.1F, true, 0, 0, f, f1);
        parModel.bob(leftThigh, 1F * speed, height, false, f, f1);
        parModel.bob(rightThigh, 1F * speed, height, false, f, f1);
        parModel.walk(bodyShoulders, 1F * speed, 0.2F, true, 1, 0, f, f1);
        parModel.walk(bodyMain1, 1F * speed, 0.2F, false, 0.5F, 0, f, f1);

        parModel.walk(leftThigh, 0.5F * speed, 0.7F, false, 3.14F, 0.2F, f, f1);
        parModel.walk(leftCalf1, 0.5F * speed, 0.6F, false, 1.5F, 0.3F, f, f1);
        parModel.walk(leftCalf2, 0.5F * speed, 0.8F, false, -1F, -0.1F, f, f1);
        parModel.walk(footLeft, 0.5F * speed, 1.5F, true, -1F, 1F, f, f1);

        parModel.walk(rightThigh, 0.5F * speed, 0.7F, true, 3.14F, 0.2F, f, f1);
        parModel.walk(rightCalf1, 0.5F * speed, 0.6F, true, 1.5F, 0.3F, f, f1);
        parModel.walk(rightCalf2, 0.5F * speed, 0.8F, true, -1F, -0.1F, f, f1);
        parModel.walk(footRight, 0.5F * speed, 1.5F, false, -1F, 1F, f, f1);

        parModel.chainSwing(tail, 0.5F * speed, -0.02F, 2, f, f1);
        parModel.chainWave(tail, 1F * speed, -0.02F, 2.5F, f, f1);
        parModel.chainSwing(neck, 0.5F * speed, 0.02F, 2, f, f1);
        parModel.chainWave(neck, 1.0F * speed, 0.02F, 0.5F, f, f1);
//     parModel.chainWave(bodyParts, 1F * speed, -0.1F, 4, f, f1);

        parModel.chainWave(armRight, 1F * speed, -0.3F, 4, f, f1);
        parModel.chainWave(armLeft, 1F * speed, -0.3F, 4, f, f1);

        // Idling
        parModel.chainWave(tail, 0.1F, 0.02F, 2, frame, 1F);
        parModel.chainWave(neck, 0.1F, 0.02F, 2, frame, 1F);
//     parModel.chainWave(bodyParts, 0.1F, -0.03F, 5, frame, 1F);
        parModel.chainWave(armRight, 0.1F, -0.1F, 4, frame, 1F);
        parModel.chainWave(armLeft, 0.1F, -0.1F, 4, frame, 1F);

//     parEntity.tailBuffer.applyChainSwingBuffer(tail);

    }
}