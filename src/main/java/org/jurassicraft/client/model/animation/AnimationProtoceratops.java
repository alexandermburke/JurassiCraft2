package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityProtoceratops;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationProtoceratops extends DinosaurAnimator
{
    public AnimationProtoceratops()
    {
        super(JCEntityRegistry.protoceratops);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        float globalSpeed = 0.5F;
        float globalHeight = 0.5F;
        float globalDegree = 0.5F;

        float frontOffset = 1.0F;

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer lowerJaw = model.getCube("Lower jaw");

        MowzieModelRenderer neck1 = model.getCube("Neck #1");
        MowzieModelRenderer neck2 = model.getCube("Neck #2");
        MowzieModelRenderer neck3 = model.getCube("Neck #3");

        MowzieModelRenderer body = model.getCube("Body MAIN");

        MowzieModelRenderer tail1 = model.getCube("Tail #1");
        MowzieModelRenderer tail2 = model.getCube("Tail #2");
        MowzieModelRenderer tail3 = model.getCube("Tail #3");
        MowzieModelRenderer tail4 = model.getCube("Tail #4");

        MowzieModelRenderer armUpperRight = model.getCube("Arm Top RIGHT");
        MowzieModelRenderer armLowerRight = model.getCube("Arm Mid RIGHT");
        MowzieModelRenderer handRight = model.getCube("Arm Hand RIGHT");

        MowzieModelRenderer armUpperLeft = model.getCube("Arm Top LEFT");
        MowzieModelRenderer armLowerLeft = model.getCube("Arm Mid LEFT");
        MowzieModelRenderer handLeft = model.getCube("Arm Hand LEFT");

        MowzieModelRenderer thighLeft = model.getCube("Leg Top LEFT");
        MowzieModelRenderer thighRight = model.getCube("Leg Top RIGHT");

        MowzieModelRenderer thighLowerLeft = model.getCube("Leg Mid LEFT");
        MowzieModelRenderer thighLowerRight = model.getCube("Leg Mid RIGHT");

        MowzieModelRenderer footUpperLeft = model.getCube("Leg Bot LEFT");
        MowzieModelRenderer footUpperRight = model.getCube("Leg Bot RIGHT");

        MowzieModelRenderer footLeft = model.getCube("Leg Foot LEFT");
        MowzieModelRenderer footRight = model.getCube("Leg Foot RIGHT");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail4, tail3, tail2, tail1 };
        MowzieModelRenderer[] neck = new MowzieModelRenderer[] { head, neck3, neck2, neck1 };

        model.bob(body, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(thighLeft, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(thighRight, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.chainWave(tail, globalSpeed * 1.0F, globalHeight * 0.25F, 3, f, f1);
        model.chainSwing(tail, globalSpeed * 0.5F, globalHeight * 0.25F, 3, f, f1);
        model.chainWave(neck, globalSpeed * 1.0F, globalHeight * 0.25F, -3, f, f1);

        model.walk(thighLeft, 1F * globalSpeed, 0.7F * globalDegree, false, 0F, -0.4F, f, f1);
        model.walk(thighLowerLeft, 1F * globalSpeed, 0.6F * globalDegree, true, 1F, 0.5F, f, f1);
        model.walk(footUpperLeft, 1F * globalSpeed, 0.6F * globalDegree, false, -1.5F, 0.85F, f, f1);

        model.walk(thighRight, 1F * globalSpeed, 0.7F * globalDegree, true, 0F, -0.4F, f, f1);
        model.walk(thighLowerRight, 1F * globalSpeed, 0.6F * globalDegree, false, 1F, 0.5F, f, f1);
        model.walk(footUpperRight, 1F * globalSpeed, 0.6F * globalDegree, true, -1.5F, 0.85F, f, f1);

        model.walk(armUpperLeft, 1F * globalSpeed, 0.7F * globalDegree, true, frontOffset + 0F, -0.2F, f, f1);
        model.walk(armLowerLeft, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 1F, -0.2F, f, f1);
        model.walk(handLeft, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 2F, 0.8F, f, f1);

        model.walk(armUpperRight, 1F * globalSpeed, 0.7F * globalDegree, false, frontOffset + 0F, -0.2F, f, f1);
        model.walk(armLowerRight, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 1F, -0.2F, f, f1);
        model.walk(handRight, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 2F, 0.8F, f, f1);

        int ticksExisted = parEntity.ticksExisted;

        model.chainWave(tail, globalSpeed * 0.25F, globalHeight * 1.0F, 3, ticksExisted, 0.1F);
        model.chainWave(neck, globalSpeed * 0.25F, globalHeight * 1.0F, -3, ticksExisted, 0.1F);

        ((EntityProtoceratops) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
