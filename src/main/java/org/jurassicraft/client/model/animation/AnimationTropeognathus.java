package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityTropeognathus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationTropeognathus extends DinosaurAnimator
{
    public AnimationTropeognathus()
    {
        super(JCEntityRegistry.tropeognathus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityTropeognathus entity = (EntityTropeognathus) parEntity;

        MowzieModelRenderer leftThigh = model.getCube("Left thigh");
        MowzieModelRenderer leftCalf = model.getCube("Left calf");
        MowzieModelRenderer leftUpperFoot = model.getCube("Left upper foot");
        MowzieModelRenderer leftFoot = model.getCube("Left foot");
        MowzieModelRenderer rightThigh = model.getCube("Right thigh");
        MowzieModelRenderer rightCalf = model.getCube("right calf");
        MowzieModelRenderer rightUpperFoot = model.getCube("Right upper foot");
        MowzieModelRenderer rightFoot = model.getCube("Right foot");
        MowzieModelRenderer jaw = model.getCube("Lower jaw 1");
        MowzieModelRenderer head = model.getCube("Head");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer body3 = model.getCube("Body 3");
        MowzieModelRenderer body2 = model.getCube("Body 2");
        MowzieModelRenderer body1 = model.getCube("Body 1");
        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer leftArm1 = model.getCube("Left Arm 1");
        MowzieModelRenderer leftArm2 = model.getCube("Left Arm 2");
        MowzieModelRenderer leftArm3 = model.getCube("Left Arm 3");
        MowzieModelRenderer leftArm4 = model.getCube("Left Arm 4");
        MowzieModelRenderer rightArm1 = model.getCube("Right Arm 1");
        MowzieModelRenderer rightArm2 = model.getCube("Right Arm 2");
        MowzieModelRenderer rightArm3 = model.getCube("Right Arm 3");
        MowzieModelRenderer rightArm4 = model.getCube("Right Arm 4");

        MowzieModelRenderer[] neck = new MowzieModelRenderer[] { head, neck3, neck2, neck1 };
        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail1, tail2, tail3 };
        MowzieModelRenderer[] wingLeft = new MowzieModelRenderer[] { leftArm4, leftArm3, leftArm2, leftArm1 };
        MowzieModelRenderer[] wingRight = new MowzieModelRenderer[] { rightArm4, rightArm3, rightArm2, rightArm1 };
        MowzieModelRenderer[] legLeft = new MowzieModelRenderer[] { leftThigh, leftCalf, leftUpperFoot, leftFoot };
        MowzieModelRenderer[] legRight = new MowzieModelRenderer[] { rightThigh, rightCalf, rightUpperFoot, rightFoot };

        float globalSpeed = 0.45F;
        float globalDegree = 2F;
        float globalHeight = 2F;
        float frontOffset = -1.35f;

        if (!entity.isFlying())
        {
            model.bob(body1, 1 * globalSpeed, 1 * globalHeight, false, f, f1);
            model.bob(leftThigh, 1 * globalSpeed, 1 * globalHeight, false, f, f1);
            model.bob(rightThigh, 1 * globalSpeed, 1 * globalHeight, false, f, f1);
            model.walk(body1, 1 * globalSpeed, -0.08f * globalHeight, false, 0, 0.1f, f, f1);
            model.walk(leftArm1, 1 * globalSpeed, -0.08f * globalHeight, true, 0, 0, f, f1);
            model.walk(rightArm1, 1 * globalSpeed, -0.08f * globalHeight, true, 0, 0, f, f1);
            model.chainWave(neck, 1 * globalSpeed, -0.15f * globalHeight, 4, f, f1);
            model.chainWave(tail, 1 * globalSpeed, 0.1f * globalHeight, 1, f, f1);

            model.walk(leftThigh, 0.5F * globalSpeed, 0.7F * globalDegree, false, 3.14F, 0.2F, f, f1);
            model.walk(leftCalf, 0.5F * globalSpeed, 0.6F * globalDegree, false, 1.5F, 0.3F, f, f1);
            model.walk(leftUpperFoot, 0.5F * globalSpeed, 0.8F * globalDegree, false, -2F, -0.4F, f, f1);
            model.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, -2F, 2.3F, f, f1);

            model.walk(rightThigh, 0.5F * globalSpeed, 0.7F * globalDegree, true, 3.14F, 0.2F, f, f1);
            model.walk(rightCalf, 0.5F * globalSpeed, 0.6F * globalDegree, true, 1.5F, 0.3F, f, f1);
            model.walk(rightUpperFoot, 0.5F * globalSpeed, 0.8F * globalDegree, true, -2F, -0.4F, f, f1);
            model.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, -2F, 2.3F, f, f1);

            model.walk(leftArm1, 0.5F * globalSpeed, 0.5F * globalDegree, true, -3.14F + frontOffset, 0.5F, f, f1);
            model.walk(leftArm2, 0.5F * globalSpeed, 0.4F * globalDegree, true, -1.5F + frontOffset, -0.3F, f, f1);
            model.walk(leftArm3, 0.5F * globalSpeed, 0.7F * globalDegree, true, 2F + frontOffset, 0.4F, f, f1);

            model.walk(rightArm1, 0.5F * globalSpeed, 0.5F * globalDegree, false, -3.14F + frontOffset, 0.5F, f, f1);
            model.walk(rightArm2, 0.5F * globalSpeed, 0.4F * globalDegree, false, -1.5F + frontOffset, -0.3F, f, f1);
            model.walk(rightArm3, 0.5F * globalSpeed, 0.7F * globalDegree, false, 2F + frontOffset, 0.4F, f, f1);
        }
        else
        {
            f = entity.getWingFlap();
            f1 = 1.0f;

            body1.rotateAngleX += 0.3;
            neck1.rotateAngleX -= 0.1;
            leftThigh.rotateAngleX += 0.8;
            rightThigh.rotateAngleX += 0.8;
            leftCalf.rotateAngleX += 0.7;
            rightCalf.rotateAngleX += 0.7;
            leftUpperFoot.rotateAngleX -= 0.3;
            rightUpperFoot.rotateAngleX -= 0.3;
            leftFoot.rotateAngleX += 2;
            rightFoot.rotateAngleX += 2;
            leftArm1.rotateAngleZ -= 1;
            leftArm2.rotateAngleZ -= 0.4;
            leftArm3.rotateAngleZ -= 0.1;
            leftArm4.rotateAngleZ += 3.3;
            leftArm4.rotateAngleY += 2.6;
            leftArm4.rotateAngleX += 1.2;
            rightArm1.rotateAngleZ += 1;
            rightArm2.rotateAngleZ += 0.4;
            rightArm3.rotateAngleZ += 0.1;
            rightArm4.rotateAngleZ -= 3.3;
            rightArm4.rotateAngleY -= 2.6;
            rightArm4.rotateAngleX += 1.2;

            model.bob(body1, 0.3f, 7, false, f, f1);
            model.bob(leftThigh, 0.3f, 7, false, f, f1);
            model.bob(rightThigh, 0.3f, 7, false, f, f1);
            model.walk(body1, 0.3f, 0.2f, true, 1, 0, f, f1);
            model.swing(leftArm1, 0.3f, 0.2f, false, 1, 0, f, f1);
            model.swing(leftArm2, 0.3f, 0.2f, false, 1, 0, f, f1);
            model.walk(neck1, 0.3f, 0.2f, false, 1, 0.2f, f, f1);
            model.walk(head, 0.3f, 0.2f, true, 1, -0.4f, f, f1);

            model.chainFlap(wingLeft, 0.3f, 0.8f, 2, f, f1);
            model.walk(leftArm1, 0.3f, 0.6f, false, -1f, -0.2f, f, f1);
            model.walk(leftArm2, 0.3f, 1.2f, true, -1f, 0, f, f1);
            model.walk(leftArm3, 0.3f, 0.7f, false, -1f, 0.2f, f, f1);
            model.chainFlap(wingRight, 0.3f, -0.8f, 2, f, f1);
            model.walk(rightArm1, 0.3f, 0.6f, false, -1f, -0.2f, f, f1);
            model.walk(rightArm2, 0.3f, 1.2f, true, -1f, 0, f, f1);
            model.walk(rightArm3, 0.3f, 0.7f, false, -1f, 0.2f, f, f1);
            model.chainWave(legLeft, 0.3f, 0.2f, -3, f, f1);
            model.chainWave(legRight, 0.3f, 0.2f, -3, f, f1);
            model.chainWave(tail, 0.3f, 0.2f, 1, f, f1);
            model.chainWave(neck, 0.3f, 0.4f, 4, f, f1);
        }

        int frame = entity.ticksExisted;
        model.walk(body1, 0.08f, -0.05f, false, 0, 0, frame, 1);
        model.chainWave(neck, 0.08f, 0.03f, 2, frame, 1);
        model.walk(leftArm1, 0.08f, 0.1f, false, 0, 0, frame, 1);
        model.walk(rightArm1, 0.08f, 0.1f, false, 0, 0, frame, 1);
        model.walk(leftArm2, 0.08f, 0.1f, false, 0, 0, frame, 1);
        model.walk(rightArm2, 0.08f, 0.1f, false, 0, 0, frame, 1);
        model.walk(leftArm3, 0.08f, 0.2f, true, 0, 0, frame, 1);
        model.walk(rightArm3, 0.08f, 0.2f, true, 0, 0, frame, 1);
        model.flap(leftArm1, 0.08f, 0.03f, false, 0, 0, frame, 1);
        model.flap(rightArm1, 0.08f, 0.03f, true, 0, 0, frame, 1);
        leftArm1.rotationPointZ -= 1 * Math.cos(frame * 0.08);
        rightArm1.rotationPointZ -= 1 * Math.cos(frame * 0.08);
    }
}
