package net.timeless.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.timeless.jurassicraft.api.animation.Animator;

public class AnimationGallimimus implements IModelAnimator
{
    private final Animator animator;

    public AnimationGallimimus()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        // f = entity.ticksExisted;
        // f1 = 1F;

        float globalSpeed = 1.0F;
        float globalDegree = 0.4F;
        float globalHeight = 1.0F;

        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");
        MowzieModelRenderer neck5 = model.getCube("neck5");

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

        model.bob(body3, globalSpeed * 0.5F, globalHeight * 1.0F, false, f, f1);
        model.bob(leftThigh, globalSpeed * 0.5F, globalHeight * 1.0F, false, f, f1);
        model.bob(rightThigh, globalSpeed * 0.5F, globalHeight * 1.0F, false, f, f1);

        model.chainWave(body, globalSpeed * 0.5F, globalHeight * 0.025F, 3, f, f1);
        model.chainWave(tail, globalSpeed * 0.5F, globalHeight * 0.1F, 2, f, f1);

        model.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.2F, f, f1);
        model.walk(leftCalf1, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        model.walk(leftCalf2, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        model.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.1F, f, f1);

        model.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.2F, f, f1);
        model.walk(rightCalf1, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        model.walk(rightCalf2, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        model.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.1F, f, f1);

        leftThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.15F * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.15F * globalSpeed);

        model.chainWave(armRight, globalSpeed * 0.25F, globalHeight * 0.25F, 3, f, f1);
        model.chainWave(armLeft, globalSpeed * 0.25F, globalHeight * 0.25F, 3, f, f1);

        int ticksExisted = entity.ticksExisted;

        model.chainWave(tail, 0.1F, 0.05F, 2, ticksExisted, 1F);
        model.chainWave(body, 0.1F, 0.03F, 5, ticksExisted, 1F);
        model.chainWave(armRight, 0.1F, 0.1F, 4, ticksExisted, 1F);
        model.chainWave(armLeft, 0.1F, 0.1F, 4, ticksExisted, 1F);

        model.faceTarget(head, 4, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 4, rotationYaw, rotationPitch);
        model.faceTarget(neck2, 4, rotationYaw, rotationPitch);
        model.faceTarget(neck3, 4, rotationYaw, rotationPitch);
    }
}
