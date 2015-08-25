package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityCompsognathus;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationCompsognathus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

        MowzieModelRenderer abdomen = model.getCube("abdomen");
        MowzieModelRenderer upperBody = model.getCube("Upper body");

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");
        MowzieModelRenderer neck5 = model.getCube("Neck 5");
        MowzieModelRenderer neck6 = model.getCube("Neck 6");
        MowzieModelRenderer neck7 = model.getCube("Neck 7");

        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");

        MowzieModelRenderer leftThigh = model.getCube("Left thigh");
        MowzieModelRenderer leftMidLeg = model.getCube("Left mid leg");
        MowzieModelRenderer leftShin = model.getCube("Left shin");
        MowzieModelRenderer leftFoot = model.getCube("Left foot");

        MowzieModelRenderer rightThigh = model.getCube("Right thigh");
        MowzieModelRenderer rightMidLeg = model.getCube("Right mid leg");
        MowzieModelRenderer rightShin = model.getCube("Right shin");
        MowzieModelRenderer rightFoot = model.getCube("Right foot");

        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");

        MowzieModelRenderer leftArm = model.getCube("Left arm");
        MowzieModelRenderer leftForeArm = model.getCube("Left forearm");
        MowzieModelRenderer leftHand = model.getCube("Left hand");

        MowzieModelRenderer rightArm = model.getCube("Right arm");
        MowzieModelRenderer rightForeArm = model.getCube("Right forearm");
        MowzieModelRenderer rightHand = model.getCube("Right hand");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] neck = new MowzieModelRenderer[]{head, neck7, neck6, neck5, neck4, neck3, neck2, neck1, upperBody};

//        f = entity.ticksExisted;
//        f1 = 0.4F;

        float globalSpeed = 1.2F;
        float globalDegree = 1.0F;
        float globalHeight = 1.0F;

        model.bob(abdomen, globalSpeed * 0.5F, globalHeight * 1.0F, false, f, f1);
        model.bob(leftThigh, globalSpeed * 0.5F, globalHeight * 1.0F, false, f, f1);
        model.bob(rightThigh, globalSpeed * 0.5F, globalHeight * 1.0F, false, f, f1);
        model.chainWave(tail, globalSpeed * 0.5F, globalHeight * 0.125F, 2, f, f1);
        model.chainWave(neck, globalSpeed * 0.5F, globalHeight * 0.0625F, -2, f, f1);

        model.walk(leftThigh, 0.5F * globalSpeed, globalDegree * 0.7F, false, 3.14F, 0.2F, f, f1);
        model.walk(leftMidLeg, 0.5F * globalSpeed, globalDegree * 0.6F, false, 1.5F, 0.3F, f, f1);
        model.walk(leftShin, 0.5F * globalSpeed, globalDegree * 0.8F, false, -1F, -0.1F, f, f1);
        model.walk(leftFoot, 0.5F * globalSpeed, globalDegree * 1.5F, true, -1F, 1F, f, f1);

        model.walk(rightThigh, 0.5F * globalSpeed, globalDegree * 0.7F, true, 3.14F, 0.2F, f, f1);
        model.walk(rightMidLeg, 0.5F * globalSpeed, globalDegree * 0.6F, true, 1.5F, 0.3F, f, f1);
        model.walk(rightShin, 0.5F * globalSpeed, globalDegree * 0.8F, true, -1F, -0.1F, f, f1);
        model.walk(rightFoot, 0.5F * globalSpeed, globalDegree * 1.5F, false, -1F, 1F, f, f1);

        int ticksExisted = entity.ticksExisted;

        model.chainWave(tail, 0.125F * globalSpeed, globalHeight * 0.125F, 2, ticksExisted, 0.25F);
        model.chainWave(neck, 0.125F * globalSpeed, globalHeight * 0.125F, -2, ticksExisted, 0.25F);

        model.faceTarget(head, 2, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 2, rotationYaw, rotationPitch);

        ((EntityCompsognathus) entity).tailBuffer.applyChainSwingBuffer(tail);

        //beg animation
        // 60 ticks
        // Squat phase
        animator.setAnim(13);
        animator.startPhase(5);
        animator.rotate(leftThigh, -0.3F, 0, 0);
        animator.move(leftThigh, 0, 3F, 0);
        animator.rotate(leftMidLeg, 0.8F, 0, 0);
        animator.rotate(leftShin, -0.5F, 0, 0);
        animator.rotate(leftFoot, -0.1F, 0, 0);
        animator.rotate(rightThigh, -0.3F, 0, 0);
        animator.move(rightThigh, 0, 3F, 0);
        animator.rotate(rightMidLeg, 0.8F, 0, 0);
        animator.rotate(rightShin, -0.4F, 0, 0);
        animator.rotate(rightFoot, -0.1F, 0, 0);
        animator.rotate(head, -0.4F, 0, 0);
        animator.rotate(lowerJaw, 0.3F, 0, 0);
        animator.rotate(abdomen, -0.1F, 0, 0);
        animator.move(abdomen, 0, 3F, 0);
        animator.rotate(tail1, 0.1F, 0, 0);
        animator.rotate(tail2, 0.1F, 0, 0);
        animator.rotate(tail3, 0.2F, 0, 0);
        animator.rotate(tail4, 0.1F, 0, 0);
        animator.rotate(tail5, 0.1F, 0, 0);
        animator.endPhase();

        // jump phase
        animator.startPhase(7);
        animator.rotate(leftThigh, 0, 0, 0);
        animator.move(leftThigh, 0, -20F, 0);
        animator.rotate(leftMidLeg, -0.1F, 0, 0);
        animator.rotate(leftShin, 0, 0, 0);
        animator.rotate(leftFoot, 0.6F, 0, 0);
        animator.rotate(rightThigh, 0, 0, 0);
        animator.move(rightThigh, 0, -20F, 0);
        animator.rotate(rightMidLeg, -0.1F, 0, 0);
        animator.rotate(rightShin, -0.1F, 0, 0);
        animator.rotate(rightFoot, 0.6F, 0, 0);
        animator.rotate(lowerJaw, 0.1F, 0, 0);
        animator.rotate(head, -0.7F, 0, 0);
        animator.rotate(abdomen, -0.8F, 0, 0);
        animator.move(abdomen, 0, -20F, 0);
        animator.rotate(lowerJaw, 0.4F, 0, 0);
        animator.rotate(tail1, -0.1F, 0, 0);
        animator.rotate(tail2, -0.1F, 0, 0);
        animator.rotate(tail3, -0.1F, 0, 0);
        animator.rotate(tail4, -0.1F, 0, 0);
        animator.rotate(tail5, -0.1F, 0, 0);
        animator.rotate(neck1, 0.6F, 0, 0);
        animator.rotate(neck2, 0.1F, 0, 0);
        animator.rotate(neck5, -0.2F, 0, 0);
        animator.rotate(neck6, -0.2F, 0, 0);
        animator.rotate(neck7, -0.2F, 0, 0);
        animator.rotate(leftArm, -1.7F, 0, 0);
        animator.rotate(leftForeArm, 0.6F, 0, 0);
        animator.rotate(leftHand, -1.6F, 0, 0);
        animator.rotate(rightArm, -1.5F, 0, 0);
        animator.rotate(rightForeArm, 0.6F, 0, 0);
        animator.rotate(rightHand, -1.5F, 0, 0);
        animator.endPhase();

        // land phase
        animator.startPhase(5);
        animator.rotate(leftThigh, -0.4F, 0, 0);
        animator.move(leftThigh, 0, 3F, 0);
        animator.rotate(leftMidLeg, 0.9F, 0, 0);
        animator.rotate(leftShin, -0.6F, 0, 0);
        animator.rotate(leftFoot, -0.1F, 0, 0);
        animator.rotate(rightThigh, -0.4F, 0, 0);
        animator.move(rightThigh, 0, 3F, 0);
        animator.rotate(rightMidLeg, 0.9F, 0, 0);
        animator.rotate(rightShin, -0.5F, 0, 0);
        animator.rotate(rightFoot, -0.1F, 0, 0);
        animator.rotate(head, -0.4F, 0, 0);
        animator.rotate(lowerJaw, 0.3F, 0, 0);
        animator.rotate(abdomen, -0.1F, 0, 0);
        animator.rotate(neck4, 0.2F, 0, 0);
        animator.rotate(neck5, 0.2F, 0, 0);
        animator.rotate(neck6, 0.2F, 0, 0);
        animator.rotate(neck7, 0.2F, 0, 0);
        animator.move(abdomen, 0, 3F, 0);
        animator.rotate(tail4, 0.1F, 0, 0);
        animator.rotate(tail5, 0.1F, 0, 0);
        animator.endPhase();
        animator.resetPhase(8);
    }
}
