package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityStegosaurus;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationStegosaurus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;

        float globalSpeed = 0.5F;
        float globalDegree = 0.4F;
        float globalHeight = 1.2F;
        float frontOffset = 0.84F;

        //        f = entity.ticksExisted;
        //        f1 = 1F;

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer upperJaw = model.getCube("Upper Jaw");
        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");

        MowzieModelRenderer neck1 = model.getCube("Neck");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");

        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer rearBody = model.getCube("Body REAR");
        MowzieModelRenderer hips = model.getCube("Body hips");
        MowzieModelRenderer bodyFrontBase = model.getCube("Body MAIN");
        MowzieModelRenderer shoulders = model.getCube("Body shoulders");
        MowzieModelRenderer bodyFront = model.getCube("Body FRONT");

        MowzieModelRenderer rearLegR = model.getCube("RearLeg Upper Right");
        MowzieModelRenderer rearLegL = model.getCube("RearLeg Upper Left");

        MowzieModelRenderer rearLegLowerR = model.getCube("RearLeg Middle Right");
        MowzieModelRenderer rearLegLowerL = model.getCube("RearLeg Middle Left");

        MowzieModelRenderer rearFootR = model.getCube("RearLeg Foot Right");
        MowzieModelRenderer rearFootL = model.getCube("RearLeg Foot Left");

        MowzieModelRenderer frontLegR = model.getCube("FrontLeg Upper Right");
        MowzieModelRenderer frontLegL = model.getCube("FrontLeg Upper Left");

        MowzieModelRenderer frontLegLowerR = model.getCube("FrontLeg MID Right");
        MowzieModelRenderer frontLegLowerL = model.getCube("FrontLeg MID Left");

        MowzieModelRenderer frontFootR = model.getCube("FrontLeg FOOT Right");
        MowzieModelRenderer frontFootL = model.getCube("FrontLeg FOOT Left");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        model.bob(hips, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(rearLegR, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(rearLegL, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(frontLegR, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(frontLegL, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.walk(hips, 1 * globalSpeed, 0.05F * globalHeight, true, -1.5F, 0.05F, f, f1);
        model.walk(bodyFrontBase, 1 * globalSpeed, 0.035F * globalHeight, true, -2F, 0.05F, f, f1);

        model.walk(rearLegL, 1F * globalSpeed, 0.7F * globalDegree, false, 0F, -0.4F, f, f1);
        model.walk(rearLegLowerL, 1F * globalSpeed, 0.6F * globalDegree, true, 1F, 0.5F, f, f1);
        model.walk(rearFootL, 1F * globalSpeed, 0.6F * globalDegree, false, -1.5F, 0.85F, f, f1);

        model.walk(rearLegR, 1F * globalSpeed, 0.7F * globalDegree, true, 0F, -0.4F, f, f1);
        model.walk(rearLegLowerR, 1F * globalSpeed, 0.6F * globalDegree, false, 1F, 0.5F, f, f1);
        model.walk(rearFootR, 1F * globalSpeed, 0.6F * globalDegree, true, -1.5F, 0.85F, f, f1);

        model.walk(frontLegL, 1F * globalSpeed, 0.7F * globalDegree, true, frontOffset + 0F, -0.2F, f, f1);
        model.walk(frontLegLowerL, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 1F, -0.2F, f, f1);
        model.walk(frontFootL, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 2F, 0.8F, f, f1);

        model.walk(frontLegR, 1F * globalSpeed, 0.7F * globalDegree, false, frontOffset + 0F, -0.2F, f, f1);
        model.walk(frontLegLowerR, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 1F, -0.2F, f, f1);
        model.walk(frontFootR, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 2F, 0.8F, f, f1);

        model.chainWave(tail, 1.0F * globalSpeed, -0.12F, 2, f, f1);
        model.chainSwing(tail, 0.5F * globalSpeed, 0.3F, 3, f, f1);

        int ticksExisted = entity.ticksExisted;

        // Idle
        model.walk(neck1, 0.1F, 0.05F, false, -1F, 0F, ticksExisted, 1F);
        model.walk(head, 0.1F, 0.05F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(hips, 0.1F, 0.025F, false, 0F, 0F, ticksExisted, 1F);

        model.chainSwing(tail, 0.1F, 0.05F, 2, ticksExisted, 1F);
        model.chainWave(tail, 0.1F, -0.05F, 1, ticksExisted, 1F);

        model.faceTarget(head, 2, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 2, rotationYaw, rotationPitch);

        ((EntityStegosaurus) entity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
