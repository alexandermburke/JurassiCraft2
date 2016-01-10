package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityCarnotaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationCarnotaurus extends DinosaurAnimator
{
    public AnimationCarnotaurus()
    {
        super(JCEntityRegistry.carnotaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur entity)
    {
        // f = entity.ticksExisted / 2;
        // f1 = 1F;

        float globalSpeed = 0.8F;
        float globalDegree = 0.6F;
        float globalHeight = 1.0F;

        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer shoulders = model.getCube("Body shoulders");
        MowzieModelRenderer hips = model.getCube("Body hips");
        MowzieModelRenderer waist = model.getCube("Body waist");

        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");

        MowzieModelRenderer leftCalf1 = model.getCube("Left Calf 1");
        MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");

        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");

        MowzieModelRenderer leftFoot = model.getCube("Foot Left");
        MowzieModelRenderer rightFoot = model.getCube("Foot Right");

        MowzieModelRenderer upperArmLeft = model.getCube("Upper Arm LEFT");
        MowzieModelRenderer upperArmRight = model.getCube("Upper Arm Right");

        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm LEFT");
        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");

        MowzieModelRenderer handLeft = model.getCube("Hand LEFT");
        MowzieModelRenderer handRight = model.getCube("Hand Right");

        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");
        MowzieModelRenderer upperJaw = model.getCube("Upper Jaw");

        MowzieModelRenderer[] body = new MowzieModelRenderer[] { head, neck4, neck3, neck2, neck1, shoulders, waist, hips };
        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { handRight, lowerArmRight, upperArmRight };
        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { handLeft, lowerArmLeft, upperArmLeft };

        model.bob(hips, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.bob(leftThigh, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(rightThigh, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.chainWave(body, globalSpeed * 1.0F, globalHeight * 0.02F, 3, f, f1);
        model.chainWave(tail, globalSpeed * 1.0F, globalHeight * -0.05F, 2, f, f1);
        model.chainSwing(tail, globalSpeed * 0.5F, globalHeight * 0.05F, 2, f, f1);

        model.walk(rightThigh, globalSpeed * 0.5F, globalDegree * 0.8F, true, -0.3F, 0.2F, f, f1);
        model.walk(leftThigh, globalSpeed * 0.5F, globalDegree * 0.8F, false, -0.3F, 0.2F, f, f1);

        model.walk(leftCalf1, globalSpeed * 0.5F, globalDegree * 1F, false, -1.3F, 0.4F, f, f1);
        model.walk(rightCalf1, globalSpeed * 0.5F, globalDegree * 1F, true, -1.3F, 0.4F, f, f1);

        model.walk(leftCalf2, globalSpeed * 0.5F, globalDegree * 1F, true, -1.6F, 0F, f, f1);
        model.walk(rightCalf2, globalSpeed * 0.5F, globalDegree * 1F, false, -1.6F, 0F, f, f1);

        model.walk(leftFoot, globalSpeed * 0.5F, globalDegree * 1.5F, false, -0.8F, 0.3F, f, f1);
        model.walk(rightFoot, globalSpeed * 0.5F, globalDegree * 1.5F, true, -0.8F, 0.3F, f, f1);

        model.chainWave(armRight, globalSpeed * 1F, globalHeight * 0.25F, 3, f, f1);
        model.chainWave(armLeft, globalSpeed * 1F, globalHeight * 0.25F, 3, f, f1);

        model.walk(head, 1F * globalSpeed, 0.15F, true, 0F, -0.2F, f, f1);
        model.walk(neck1, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck2, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck3, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck4, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);

        leftThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5F * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5F * globalSpeed);

        int ticksExisted = entity.ticksExisted;

        model.chainWave(tail, 0.1F, -0.05F, 2, ticksExisted, 1F);
        model.chainWave(body, 0.1F, 0.03F, 5, ticksExisted, 1F);
        model.chainWave(armRight, 0.1F, 0.1F, 4, ticksExisted, 1F);
        model.chainWave(armLeft, 0.1F, 0.1F, 4, ticksExisted, 1F);

        ((EntityCarnotaurus) entity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
