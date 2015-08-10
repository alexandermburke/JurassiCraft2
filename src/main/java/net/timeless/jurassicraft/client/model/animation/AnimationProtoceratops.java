package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.jurassicraft.common.entity.EntityProtoceratops;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationProtoceratops implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity e)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
		EntityProtoceratops entity = (EntityProtoceratops) e;
        Animator animator = model.animator;

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
    }
}
