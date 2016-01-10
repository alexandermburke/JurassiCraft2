package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityTherizinosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationTherizinosaurus extends DinosaurAnimator
{

    public AnimationTherizinosaurus()
    {
        super(JCEntityRegistry.therizinosaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");
        MowzieModelRenderer bodyHips = model.getCube("Body hips");
        MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
        MowzieModelRenderer footRight = model.getCube("Foot Right");
        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer bodyMain = model.getCube("Body main");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail2Feathers = model.getCube("Tail 2 feathers");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail3Feathers = model.getCube("Tail 3 feathers");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail4Feathers = model.getCube("Tail 4 feathers");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");
        MowzieModelRenderer tail5Feathers = model.getCube("Tail 5 feathers");
        MowzieModelRenderer tail6Feathers = model.getCube("Tail 6 feathers");
        MowzieModelRenderer tail6FeathersR = model.getCube("Tail 6 feathers r");
        MowzieModelRenderer tail6FeathersL = model.getCube("Tail 6 feathers l");
        MowzieModelRenderer tail5FeathersR = model.getCube("Tail 5 feathers r");
        MowzieModelRenderer tail5FeathersL = model.getCube("Tail 5 feathers l");
        MowzieModelRenderer tail4FeathersR = model.getCube("Tail 4 feathers r");
        MowzieModelRenderer tail4FeathersL = model.getCube("Tail 4 feathers l");
        MowzieModelRenderer tail3FeathersR = model.getCube("Tail 3 feathers r");
        MowzieModelRenderer tail3FeathersL = model.getCube("Tail 3 feathers l");
        MowzieModelRenderer bodyShoulders = model.getCube("Body shoulders");
        MowzieModelRenderer bodyMain1 = model.getCube("Body main 1");
        MowzieModelRenderer neckBase = model.getCube("Neck base");
        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");
        MowzieModelRenderer neck5 = model.getCube("Neck 5");
        MowzieModelRenderer neck4feathers = model.getCube("Neck 4 feathers");
        MowzieModelRenderer neck6 = model.getCube("Neck 6");
        MowzieModelRenderer neck5Feathers = model.getCube("Neck 5 feathers");
        MowzieModelRenderer neck7 = model.getCube("Neck 7");
        MowzieModelRenderer neck6Feathers = model.getCube("Neck 6 feathers");
        MowzieModelRenderer head = model.getCube("Head");
        MowzieModelRenderer neck7Feathers = model.getCube("Neck 7 feathers");
        MowzieModelRenderer snout = model.getCube("Snout");
        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");
        MowzieModelRenderer snoutRoof = model.getCube("Snout roof");
        MowzieModelRenderer upperJaw = model.getCube("Upper Jaw");
        MowzieModelRenderer neck7FeathersR = model.getCube("Neck 7 feathers r");
        MowzieModelRenderer neck7FeathersL = model.getCube("Neck 7 feathers l");
        MowzieModelRenderer neck6FeathersR = model.getCube("Neck 6 feathers r");
        MowzieModelRenderer neck6FeathersL = model.getCube("Neck 6 feathers l");
        MowzieModelRenderer neck5FeathersR = model.getCube("Neck 5 feathers r");
        MowzieModelRenderer neck5FeathersL = model.getCube("Neck 5 feathers l");
        MowzieModelRenderer neck4FeathersR = model.getCube("Neck 4 feathers r");
        MowzieModelRenderer neck4FeathersL = model.getCube("Neck 4 feathers l");
        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
        MowzieModelRenderer lowerArmRight1 = model.getCube("Lower Arm Right 1");
        MowzieModelRenderer rightHand = model.getCube("Right hand");
        MowzieModelRenderer armRightFeathers = model.getCube("Arm right feathers");
        MowzieModelRenderer rightFinger1 = model.getCube("Right finger 1");
        MowzieModelRenderer rightFinger2 = model.getCube("Right finger 2");
        MowzieModelRenderer righFinger3 = model.getCube("Right finger 3");
        MowzieModelRenderer rF1mid = model.getCube("RF1 mid");
        MowzieModelRenderer rF1end = model.getCube("RF1 end");
        MowzieModelRenderer rF2mid = model.getCube("RF2 mid");
        MowzieModelRenderer rF2end = model.getCube("RF2 end");
        MowzieModelRenderer rF3mid = model.getCube("RF3 mid");
        MowzieModelRenderer rF3end = model.getCube("RF3 end");
        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm LEFT");
        MowzieModelRenderer lowerArmLeft1 = model.getCube("Lower Arm LEFT 1");
        MowzieModelRenderer leftHand = model.getCube("Left hand");
        MowzieModelRenderer armLeftFeathers = model.getCube("Arm left feathers");
        MowzieModelRenderer leftfinger1 = model.getCube("Left finger 1");
        MowzieModelRenderer leftfinger2 = model.getCube("Left finger 2");
        MowzieModelRenderer leftfinger3 = model.getCube("Left finger 3");
        MowzieModelRenderer lF1mid = model.getCube("LF1 mid");
        MowzieModelRenderer lF1end = model.getCube("LF1 end");
        MowzieModelRenderer lF2mid = model.getCube("LF1 end");
        MowzieModelRenderer lF2end = model.getCube("LF2 end");
        MowzieModelRenderer lF3mid = model.getCube("LF3 mid");
        MowzieModelRenderer lF3end = model.getCube("LF3 mid");
        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer leftCalf1 = model.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");
        MowzieModelRenderer footLeft = model.getCube("Foot Left");

        MowzieModelRenderer[] neck = new MowzieModelRenderer[] { head, neck7, neck6, neck5, neck4, neck3, neck2, neck1, neckBase, bodyShoulders };
        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail1, tail2, tail3, tail4, tail5, tail6 };
        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { lowerArmLeft, lowerArmLeft1, leftHand };
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { lowerArmRight, lowerArmRight1, rightHand };

        int frame = parEntity.ticksExisted;

        // float globalHeight = 2F;
        // float frontOffset = -1.35f;

        // The tail must always be up when the neck is down
        float speed = 0.75F;
        float height = 3F;

        model.bob(bodyHips, 1F * speed, height, false, f, f1);
        model.flap(bodyHips, 0.5F * speed, 0.5F, false, 0, 0, f, f1);
        model.flap(bodyMain, 0.5F * speed, 0.1F, true, 0, 0, f, f1);
        model.flap(bodyShoulders, 0.5F * speed, 0.4F, true, 0, 0, f, f1);
        model.flap(tail1, 0.5F * speed, 0.2F, true, 0, 0, f, f1);
        model.flap(tail3, 0.5F * speed, 0.2F, true, 0, 0, f, f1);
        model.flap(tail5, 0.5F * speed, 0.1F, true, 0, 0, f, f1);
        model.bob(leftThigh, 1F * speed, height, false, f, f1);
        model.bob(rightThigh, 1F * speed, height, false, f, f1);
        model.walk(bodyShoulders, 1F * speed, 0.2F, true, 1, 0, f, f1);
        model.walk(bodyMain1, 1F * speed, 0.2F, false, 0.5F, 0, f, f1);

        model.walk(leftThigh, 0.5F * speed, 0.7F, false, 3.14F, 0.2F, f, f1);
        model.walk(leftCalf1, 0.5F * speed, 0.6F, false, 1.5F, 0.3F, f, f1);
        model.walk(leftCalf2, 0.5F * speed, 0.8F, false, -1F, -0.1F, f, f1);
        model.walk(footLeft, 0.5F * speed, 1.5F, true, -1F, 1F, f, f1);

        model.walk(rightThigh, 0.5F * speed, 0.7F, true, 3.14F, 0.2F, f, f1);
        model.walk(rightCalf1, 0.5F * speed, 0.6F, true, 1.5F, 0.3F, f, f1);
        model.walk(rightCalf2, 0.5F * speed, 0.8F, true, -1F, -0.1F, f, f1);
        model.walk(footRight, 0.5F * speed, 1.5F, false, -1F, 1F, f, f1);

        model.chainSwing(tail, 0.5F * speed, -0.02F, 2, f, f1);
        model.chainWave(tail, 1F * speed, -0.02F, 2.5F, f, f1);
        model.chainSwing(neck, 0.5F * speed, 0.02F, 2, f, f1);
        model.chainWave(neck, 1.0F * speed, 0.02F, 0.5F, f, f1);
        // model.chainWave(bodyParts, 1F * speed, -0.1F, 4, f, f1);

        model.chainWave(armRight, 1F * speed, -0.3F, 4, f, f1);
        model.chainWave(armLeft, 1F * speed, -0.3F, 4, f, f1);

        // Idling
        model.chainWave(tail, 0.1F, 0.02F, 2, frame, 1F);
        model.chainWave(neck, 0.1F, 0.02F, 2, frame, 1F);
        // model.chainWave(bodyParts, 0.1F, -0.03F, 5, frame, 1F);
        model.chainWave(armRight, 0.1F, -0.1F, 4, frame, 1F);
        model.chainWave(armLeft, 0.1F, -0.1F, 4, frame, 1F);

        ((EntityTherizinosaurus) parEntity).tailBuffer.applyChainSwingBuffer(tail);

    }
}
