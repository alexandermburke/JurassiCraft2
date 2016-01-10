package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityOthnielia;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationOthnielia extends DinosaurAnimator
{
    public AnimationOthnielia()
    {
        super(JCEntityRegistry.othnielia);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityOthnielia entity = (EntityOthnielia) parEntity;

        int ticksExisted = entity.ticksExisted;

        float speed = 0.8F;
        float height = 12F * f1;

        MowzieModelRenderer bodyRear = model.getCube("Body REAR");
        MowzieModelRenderer bodyFront = model.getCube("Body FRONT");
        MowzieModelRenderer neck1 = model.getCube("Neck BASE");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer head = model.getCube("Head ");

        MowzieModelRenderer tail1 = model.getCube("Tail BASE");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer thighLeft = model.getCube("Leg UPPER LEFT");
        MowzieModelRenderer thighRight = model.getCube("Leg UPPER RIGHT");
        MowzieModelRenderer lowerThighLeft = model.getCube("Leg MIDDLE LEFT");
        MowzieModelRenderer lowerThighRight = model.getCube("Leg MIDDLE RIGHT");
        MowzieModelRenderer upperFootLeft = model.getCube("Leg LOWER LEFT");
        MowzieModelRenderer upperFootRight = model.getCube("Leg LOWER RIGHT");
        MowzieModelRenderer footLeft = model.getCube("Foot LEFT");
        MowzieModelRenderer footRight = model.getCube("Foot RIGHT");

        MowzieModelRenderer upperArmLeft = model.getCube("Arm UPPER LEFT");
        MowzieModelRenderer upperArmRight = model.getCube("Arm UPPER RIGHT");

        MowzieModelRenderer lowerArmLeft = model.getCube("Arm MIDDLE LEFT");
        MowzieModelRenderer lowerArmRight = model.getCube("Arm MIDDLE RIGHT");

        MowzieModelRenderer handLeft = model.getCube("Arm HAND LEFT");
        MowzieModelRenderer handRight = model.getCube("Arm HAND RIGHT");

        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };

        model.bob(bodyRear, 0.5F * speed, height, true, f, f1);
        model.bob(thighRight, 0.5F * speed, height, true, f, f1);
        model.bob(thighLeft, 0.5F * speed, height, true, f, f1);

        model.walk(thighLeft, 1F * speed, 0.75F, true, 1F, 0.25F, f, f1);
        model.walk(thighRight, 1F * speed, 0.75F, true, 0.5F, 0.25F, f, f1);
        model.walk(lowerThighLeft, 1F * speed, 0.75F, false, 1.5F, 0F, f, f1);
        model.walk(lowerThighRight, 1F * speed, 0.75F, false, 1F, 0F, f, f1);
        model.walk(upperFootRight, 1F * speed, 0.75F, true, 0.5F, 0F, f, f1);
        model.walk(upperFootLeft, 1F * speed, 0.75F, true, 1F, 0F, f, f1);
        model.walk(footLeft, 1F * speed, 0.5F, true, 1F, 0.75F, f, f1);
        model.walk(footRight, 1F * speed, 0.5F, true, 0.5F, 0.75F, f, f1);

        model.walk(bodyRear, 1F * speed, 0.3F, false, 0.5F, 0F, f, f1);
        model.walk(bodyFront, 1F * speed, 0.5F, true, 1.0F, 0F, f, f1);
        model.walk(neck1, 1F * speed, 0.3F, true, 0.25F, 0.3F, f, f1);
        model.walk(head, 1F * speed, 0.3F, false, 0.25F, -0.3F, f, f1);

        model.walk(upperArmRight, 1 * speed, 0.3F, true, 1, 0.2F, f, f1);
        model.walk(upperArmLeft, 1 * speed, 0.3F, true, 1, 0.2F, f, f1);
        model.walk(lowerArmRight, 1 * speed, 0.3F, false, 1, -0.2F, f, f1);
        model.walk(lowerArmLeft, 1 * speed, 0.3F, false, 1, -0.2F, f, f1);

        // parModel.faceTarget(head, 1, rotationYaw, rotationPitch);

        // Idling
        model.chainWave(tailParts, 0.2F, -0.05F, 2, ticksExisted, 1F);
        model.walk(neck1, 0.2F, 0.1F, false, -1F, 0F, ticksExisted, 1F);
        model.walk(head, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(bodyFront, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(bodyRear, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);
        model.walk(upperArmRight, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(upperArmLeft, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(lowerArmRight, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);
        model.walk(lowerArmLeft, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);

        model.chainWave(tailParts, 1F * speed, 0.15F, 2, f, f1);

        entity.tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
