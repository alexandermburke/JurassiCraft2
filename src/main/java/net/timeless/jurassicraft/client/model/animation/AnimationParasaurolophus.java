package net.timeless.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import net.minecraft.entity.Entity;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityParasaurolophus;

@SideOnly(Side.CLIENT)
public class AnimationParasaurolophus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

        float globalSpeed = 1.0F;
        float height = 1.2F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;

        //f = entity.ticksExisted;
        //f1 = 1F;

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer neck1 = model.getCube("Neck");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");

        // body parts
        MowzieModelRenderer stomach = model.getCube("Body 1");
        MowzieModelRenderer shoulders = model.getCube("Body 2");
        MowzieModelRenderer waist = model.getCube("Body 3");

        // tail parts
        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        // left foot
        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer leftCalf = model.getCube("Left Calf 1");
        MowzieModelRenderer leftUpperFoot = model.getCube("Left Upper Foot");
        MowzieModelRenderer leftFoot = model.getCube("Foot Left");

        // right foot
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");
        MowzieModelRenderer rightCalf = model.getCube("Right Calf 1");
        MowzieModelRenderer rightUpperFoot = model.getCube("Right Upper Foot");
        MowzieModelRenderer rightFoot = model.getCube("Foot Right");

        // right arm
        MowzieModelRenderer upperArmRight = model.getCube("Upper Arm Right");
        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
        MowzieModelRenderer rightHand = model.getCube("Right Hand");
        MowzieModelRenderer rightFingers = model.getCube("Right Fingers");

        // left arm
        MowzieModelRenderer upperArmLeft = model.getCube("Upper Arm Left");
        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm Left");
        MowzieModelRenderer leftHand = model.getCube("Left Hand");
        MowzieModelRenderer leftFingers = model.getCube("Left Fingers");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        model.faceTarget(head, 3, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 3, rotationYaw, rotationPitch);
        model.faceTarget(neck2, 3, rotationYaw, rotationPitch);

        int ticksExisted = entity.ticksExisted;

        model.chainWave(tail, 0.1F, -0.05F, 2, ticksExisted, 1F);

        model.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.2F, f, f1);
        model.walk(leftCalf, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        model.walk(leftUpperFoot, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        model.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, -0.1F, f, f1);

        model.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.2F, f, f1);
        model.walk(rightCalf, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        model.walk(rightUpperFoot, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        model.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, -0.1F, f, f1);

        model.walk(upperArmLeft, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.2F, f, f1);
        model.walk(lowerArmLeft, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        model.walk(leftHand, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        model.walk(leftFingers, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, -0.1F, f, f1);

        model.walk(upperArmRight, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.2F, f, f1);
        model.walk(lowerArmRight, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        model.walk(rightHand, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        model.walk(rightFingers, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, -0.1F, f, f1);

        ((EntityParasaurolophus) entity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
