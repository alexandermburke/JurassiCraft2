package org.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityMicroceratus;

@SideOnly(Side.CLIENT)
public class AnimationMicroceratus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

        MowzieModelRenderer body = model.getCube("Body MAIN");

        MowzieModelRenderer tail1 = model.getCube("Tail #1");
        MowzieModelRenderer tail2 = model.getCube("Tail #2");
        MowzieModelRenderer tail3 = model.getCube("Tail #3");
        MowzieModelRenderer tail4 = model.getCube("Tail #4");
        MowzieModelRenderer tail5 = model.getCube("Tail #5");

        MowzieModelRenderer neck1 = model.getCube("Neck #1");
        MowzieModelRenderer neck2 = model.getCube("Neck #2");

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer thighLeft = model.getCube("Leg Top LEFT");
        MowzieModelRenderer thighRight = model.getCube("Leg Top RIGHT");

        MowzieModelRenderer thighMidLeft = model.getCube("Leg Mid LEFT");
        MowzieModelRenderer thighMidRight = model.getCube("Leg Mid RIGHT");

        MowzieModelRenderer upperFootLeft = model.getCube("Leg Bot LEFT");
        MowzieModelRenderer upperFootRight = model.getCube("Leg Bot RIGHT");

        MowzieModelRenderer footLeft = model.getCube("Leg Foot LEFT");
        MowzieModelRenderer footRight = model.getCube("Leg Foot RIGHT");

        MowzieModelRenderer armTopLeft = model.getCube("Arm Top LEFT");
        MowzieModelRenderer armTopRight = model.getCube("Arm Top RIGHT");

        MowzieModelRenderer armMidLeft = model.getCube("Arm Mid LEFT");
        MowzieModelRenderer armMidRight = model.getCube("Arm Mid RIGHT");

        MowzieModelRenderer handLeft = model.getCube("Arm Hand LEFT");
        MowzieModelRenderer handRight = model.getCube("Arm Hand RIGHT");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] neck = new MowzieModelRenderer[]{head, neck2, neck1, body};

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[]{handLeft, armMidLeft, armTopLeft};
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[]{handRight, armMidRight, armTopRight};

//        f = entity.ticksExisted;
//        f1 = 0.5F;

        float globalSpeed = 0.5F;
        float globalDegree = 0.5F;
        float globalHeight = 1.0F;

        model.bob(body, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(thighLeft, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(thighRight, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.chainWave(tail, globalSpeed * 1.0F, globalHeight * 0.1F, 2, f, f1);
        model.chainWave(neck, globalSpeed * 1.0F, globalHeight * 0.1F, 3, f, f1);

        model.chainWave(armLeft, globalSpeed * 1.0F, globalHeight * 0.2F, 3, f, f1);
        model.chainWave(armRight, globalSpeed * 1.0F, globalHeight * -0.2F, 3, f, f1);

        model.walk(thighLeft, globalSpeed * 1.0F, globalDegree * 1.0F, true, 0.0F, 0.0F, f, f1);
        model.walk(thighMidLeft, globalSpeed * 1.0F, globalDegree * 1.0F, true, 1.0F, 0.2F, f, f1);
        model.walk(footLeft, globalSpeed * 1.0F, globalDegree * 1.0F, false, -0.25F, -0.2F, f, f1);

        model.walk(thighRight, globalSpeed * 1.0F, globalDegree * 1.0F, false, 0.0F, 0.0F, f, f1);
        model.walk(thighMidRight, globalSpeed * 1.0F, globalDegree * 1.0F, false, 1.0F, 0.2F, f, f1);
        model.walk(footRight, globalSpeed * 1.0F, globalDegree * 1.0F, true, -0.25F, -0.2F, f, f1);

        int frame = entity.ticksExisted;

        model.chainWave(tail, globalSpeed * 0.2F, globalHeight * 0.05F, 2, frame, 1.0F);
        model.chainWave(neck, globalSpeed * 0.2F, globalHeight * 0.05F, 3, frame, 1.0F);

        ((EntityMicroceratus) entity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
