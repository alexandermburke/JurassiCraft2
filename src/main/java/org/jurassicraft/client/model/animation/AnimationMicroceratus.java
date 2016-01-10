package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityMicroceratus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationMicroceratus extends DinosaurAnimator
{
    public AnimationMicroceratus()
    {
        super(JCEntityRegistry.microceratus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        MowzieModelRenderer body = parModel.getCube("Body MAIN");

        MowzieModelRenderer tail1 = parModel.getCube("Tail #1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail #2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail #3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail #4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail #5");

        MowzieModelRenderer neck1 = parModel.getCube("Neck #1");
        MowzieModelRenderer neck2 = parModel.getCube("Neck #2");

        MowzieModelRenderer head = parModel.getCube("Head");

        MowzieModelRenderer thighLeft = parModel.getCube("Leg Top LEFT");
        MowzieModelRenderer thighRight = parModel.getCube("Leg Top RIGHT");

        MowzieModelRenderer thighMidLeft = parModel.getCube("Leg Mid LEFT");
        MowzieModelRenderer thighMidRight = parModel.getCube("Leg Mid RIGHT");

        MowzieModelRenderer upperFootLeft = parModel.getCube("Leg Bot LEFT");
        MowzieModelRenderer upperFootRight = parModel.getCube("Leg Bot RIGHT");

        MowzieModelRenderer footLeft = parModel.getCube("Leg Foot LEFT");
        MowzieModelRenderer footRight = parModel.getCube("Leg Foot RIGHT");

        MowzieModelRenderer armTopLeft = parModel.getCube("Arm Top LEFT");
        MowzieModelRenderer armTopRight = parModel.getCube("Arm Top RIGHT");

        MowzieModelRenderer armMidLeft = parModel.getCube("Arm Mid LEFT");
        MowzieModelRenderer armMidRight = parModel.getCube("Arm Mid RIGHT");

        MowzieModelRenderer handLeft = parModel.getCube("Arm Hand LEFT");
        MowzieModelRenderer handRight = parModel.getCube("Arm Hand RIGHT");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail5, tail4, tail3, tail2, tail1 };
        MowzieModelRenderer[] neck = new MowzieModelRenderer[] { head, neck2, neck1, body };

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { handLeft, armMidLeft, armTopLeft };
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { handRight, armMidRight, armTopRight };

        // f = entity.ticksExisted;
        // f1 = 0.5F;

        float globalSpeed = 0.8F;
        float globalDegree = 0.5F;
        float globalHeight = 1.0F;

        parModel.bob(body, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        parModel.bob(thighLeft, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        parModel.bob(thighRight, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        parModel.chainWave(tail, globalSpeed * 1.0F, globalHeight * 0.1F, 2, f, f1);
        parModel.chainWave(neck, globalSpeed * 1.0F, globalHeight * 0.1F, 3, f, f1);

        parModel.chainWave(armLeft, globalSpeed * 1.0F, globalHeight * 0.2F, 3, f, f1);
        parModel.chainWave(armRight, globalSpeed * 1.0F, globalHeight * -0.2F, 3, f, f1);

        parModel.walk(thighLeft, globalSpeed * 1.0F, globalDegree * 1.0F, true, 0.0F, 0.0F, f, f1);
        parModel.walk(thighMidLeft, globalSpeed * 1.0F, globalDegree * 1.0F, true, 1.0F, 0.2F, f, f1);
        parModel.walk(footLeft, globalSpeed * 1.0F, globalDegree * 1.0F, false, -0.25F, -0.2F, f, f1);

        parModel.walk(thighRight, globalSpeed * 1.0F, globalDegree * 1.0F, false, 0.0F, 0.0F, f, f1);
        parModel.walk(thighMidRight, globalSpeed * 1.0F, globalDegree * 1.0F, false, 1.0F, 0.2F, f, f1);
        parModel.walk(footRight, globalSpeed * 1.0F, globalDegree * 1.0F, true, -0.25F, -0.2F, f, f1);

        int frame = parEntity.ticksExisted;

        parModel.chainWave(tail, globalSpeed * 0.2F, globalHeight * 0.05F, 2, frame, 1.0F);
        parModel.chainWave(neck, globalSpeed * 0.2F, globalHeight * 0.05F, 3, frame, 1.0F);

        ((EntityMicroceratus) parEntity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
