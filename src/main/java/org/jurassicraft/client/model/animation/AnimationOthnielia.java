package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurOthnielia;
import org.jurassicraft.common.entity.EntityOthnielia;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationOthnielia extends DinosaurAnimator
{
    public AnimationOthnielia()
    {
        super(new DinosaurOthnielia());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityOthnielia entity = (EntityOthnielia) parEntity;

        int ticksExisted = entity.ticksExisted;

        float speed = 0.8F;
        float height = 12F * f1;

        MowzieModelRenderer bodyRear = parModel.getCube("Body REAR");
        MowzieModelRenderer bodyFront = parModel.getCube("Body FRONT");
        MowzieModelRenderer neck1 = parModel.getCube("Neck BASE");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer head = parModel.getCube("Head ");

        MowzieModelRenderer tail1 = parModel.getCube("Tail BASE");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");

        MowzieModelRenderer thighLeft = parModel.getCube("Leg UPPER LEFT");
        MowzieModelRenderer thighRight = parModel.getCube("Leg UPPER RIGHT");
        MowzieModelRenderer lowerThighLeft = parModel.getCube("Leg MIDDLE LEFT");
        MowzieModelRenderer lowerThighRight = parModel.getCube("Leg MIDDLE RIGHT");
        MowzieModelRenderer upperFootLeft = parModel.getCube("Leg LOWER LEFT");
        MowzieModelRenderer upperFootRight = parModel.getCube("Leg LOWER RIGHT");
        MowzieModelRenderer footLeft = parModel.getCube("Foot LEFT");
        MowzieModelRenderer footRight = parModel.getCube("Foot RIGHT");

        MowzieModelRenderer upperArmLeft = parModel.getCube("Arm UPPER LEFT");
        MowzieModelRenderer upperArmRight = parModel.getCube("Arm UPPER RIGHT");

        MowzieModelRenderer lowerArmLeft = parModel.getCube("Arm MIDDLE LEFT");
        MowzieModelRenderer lowerArmRight = parModel.getCube("Arm MIDDLE RIGHT");

        MowzieModelRenderer handLeft = parModel.getCube("Arm HAND LEFT");
        MowzieModelRenderer handRight = parModel.getCube("Arm HAND RIGHT");

        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};

        parModel.bob(bodyRear, 0.5F * speed, height, true, f, f1);
        parModel.bob(thighRight, 0.5F * speed, height, true, f, f1);
        parModel.bob(thighLeft, 0.5F * speed, height, true, f, f1);

        parModel.walk(thighLeft, 1F * speed, 0.75F, true, 1F, 0.25F, f, f1);
        parModel.walk(thighRight, 1F * speed, 0.75F, true, 0.5F, 0.25F, f, f1);
        parModel.walk(lowerThighLeft, 1F * speed, 0.75F, false, 1.5F, 0F, f, f1);
        parModel.walk(lowerThighRight, 1F * speed, 0.75F, false, 1F, 0F, f, f1);
        parModel.walk(upperFootRight, 1F * speed, 0.75F, true, 0.5F, 0F, f, f1);
        parModel.walk(upperFootLeft, 1F * speed, 0.75F, true, 1F, 0F, f, f1);
        parModel.walk(footLeft, 1F * speed, 0.5F, true, 1F, 0.75F, f, f1);
        parModel.walk(footRight, 1F * speed, 0.5F, true, 0.5F, 0.75F, f, f1);

        parModel.walk(bodyRear, 1F * speed, 0.3F, false, 0.5F, 0F, f, f1);
        parModel.walk(bodyFront, 1F * speed, 0.5F, true, 1.0F, 0F, f, f1);
        parModel.walk(neck1, 1F * speed, 0.3F, true, 0.25F, 0.3F, f, f1);
        parModel.walk(head, 1F * speed, 0.3F, false, 0.25F, -0.3F, f, f1);

        parModel.walk(upperArmRight, 1 * speed, 0.3F, true, 1, 0.2F, f, f1);
        parModel.walk(upperArmLeft, 1 * speed, 0.3F, true, 1, 0.2F, f, f1);
        parModel.walk(lowerArmRight, 1 * speed, 0.3F, false, 1, -0.2F, f, f1);
        parModel.walk(lowerArmLeft, 1 * speed, 0.3F, false, 1, -0.2F, f, f1);

//        parModel.faceTarget(head, 1, rotationYaw, rotationPitch);

        // Idling
        parModel.chainWave(tailParts, 0.2F, -0.05F, 2, ticksExisted, 1F);
        parModel.walk(neck1, 0.2F, 0.1F, false, -1F, 0F, ticksExisted, 1F);
        parModel.walk(head, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(bodyFront, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(bodyRear, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(upperArmRight, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(upperArmLeft, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(lowerArmRight, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(lowerArmLeft, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);

        parModel.chainWave(tailParts, 1F * speed, 0.15F, 2, f, f1);

        entity.tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
