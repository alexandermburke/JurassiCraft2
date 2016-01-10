package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityGiganotosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationGiganotosaurus extends DinosaurAnimator
{
    public AnimationGiganotosaurus()
    {
        super(JCEntityRegistry.giganotosaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        float globalSpeed = 0.45F;
        float globalDegree = 0.45F;
        float height = 1.0F;

        // f = entity.ticksExisted;
        // f1 = 1.0F;

        MowzieModelRenderer neck = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");

        MowzieModelRenderer throat = model.getCube("Throat 1");
        MowzieModelRenderer throat2 = model.getCube("Throat 2");

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer body1 = model.getCube("Body shoulders");
        MowzieModelRenderer body2 = model.getCube("Body waist");
        MowzieModelRenderer body3 = model.getCube("Body hips");

        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");
        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");

        MowzieModelRenderer rightFoot = model.getCube("Foot Right");
        MowzieModelRenderer leftFoot = model.getCube("Foot Left");

        MowzieModelRenderer rightCalf = model.getCube("Right Calf 1");
        MowzieModelRenderer leftCalf = model.getCube("Left Calf 1");

        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
        MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");

        MowzieModelRenderer lowerJaw = model.getCube("Lower jaw");

        MowzieModelRenderer[] body = new MowzieModelRenderer[] { head, neck4, neck3, neck2, neck, body1, body2, body3 };

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        // TODO:Arms

        int ticksExisted = parEntity.ticksExisted;

        head.rotateAngleX -= f1 * 0.35F;

        // body3.rotateAngleX += f1 * 0.15F;

        model.bob(body3, globalSpeed * 1.0F, height * 1.0F, false, f, f1);

        model.bob(leftThigh, globalSpeed * 1.0F, height * 1.0F, false, f, f1);
        model.bob(rightThigh, globalSpeed * 1.0F, height * 1.0F, false, f, f1);

        model.chainWave(body, globalSpeed * 1.0F, height * 0.02F, 3, f, f1);
        model.chainWave(tail, globalSpeed * 1.0F, height * 0.05F, 2, f, f1);

        model.walk(rightThigh, globalSpeed * 0.5F, globalDegree * 0.8F, true, 0, 0.2F, f, f1);
        model.walk(leftThigh, globalSpeed * 0.5F, globalDegree * 0.8F, false, 0, 0.2F, f, f1);

        model.walk(leftCalf, globalSpeed * 0.5F, globalDegree * 1F, false, -1.3F, 0.4F, f, f1);
        model.walk(rightCalf, globalSpeed * 0.5F, globalDegree * 1F, true, -1.3F, 0.4F, f, f1);

        model.walk(leftCalf2, globalSpeed * 0.5F, globalDegree * 1.1F, true, -2F, 0F, f, f1);
        model.walk(rightCalf2, globalSpeed * 0.5F, globalDegree * 1.1F, false, -2F, 0F, f, f1);

        model.walk(leftFoot, globalSpeed * 0.5F, globalDegree * 1.7F, false, -0.8F, 0.55F, f, f1);
        model.walk(rightFoot, globalSpeed * 0.5F, globalDegree * 1.7F, true, -0.8F, 0.55F, f, f1);

        model.walk(head, 1F * globalSpeed, 0.15F, true, 0F, 0.2F, f, f1);
        model.walk(neck, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck2, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck3, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);
        model.walk(neck4, 1F * globalSpeed, 0.03F, false, 0F, 0.04F, f, f1);

        leftThigh.rotationPointY += 2 * f1 * Math.cos(f * 0.5F * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5F * globalSpeed);

        model.chainWave(tail, 0.1F, -0.05F, 2, ticksExisted, 1F);
        model.chainWave(body, 0.1F, 0.03F, 4, ticksExisted, 1F);

        ((EntityGiganotosaurus) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
