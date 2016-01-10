package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityLeaellynasaura;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationLeaellynasaura extends DinosaurAnimator
{
    public AnimationLeaellynasaura()
    {
        super(JCEntityRegistry.leaellynasaura);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityLeaellynasaura entity = (EntityLeaellynasaura) parEntity;

        // f = entity.ticksExisted;
        // f1 = 0.4F;

        float globalSpeed = 0.4F;
        float globalHeight = 0.8F;
        float globalDegree = 0.8F;

        MowzieModelRenderer head = model.getCube("Head ");

        MowzieModelRenderer neck1 = model.getCube("Neck BASE");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");
        MowzieModelRenderer neck5 = model.getCube("Neck 5");

        MowzieModelRenderer bodyFront = model.getCube("Body FRONT");
        MowzieModelRenderer bodyRear = model.getCube("Body REAR");

        MowzieModelRenderer tail1 = model.getCube("Tail BASE");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");

        MowzieModelRenderer thighLeft = model.getCube("Leg UPPER LEFT");
        MowzieModelRenderer thighRight = model.getCube("Leg UPPER RIGHT");

        MowzieModelRenderer calf1Left = model.getCube("Leg MIDDLE LEFT");
        MowzieModelRenderer calf1Right = model.getCube("Leg MIDDLE RIGHT");

        MowzieModelRenderer calf2Left = model.getCube("Leg LOWER LEFT");
        MowzieModelRenderer calf2Right = model.getCube("Leg LOWER RIGHT");

        MowzieModelRenderer footLeft = model.getCube("Foot LEFT");
        MowzieModelRenderer footRight = model.getCube("Foot RIGHT");

        MowzieModelRenderer armUpperLeft = model.getCube("Arm UPPER LEFT");
        MowzieModelRenderer armUpperRight = model.getCube("Arm UPPER RIGHT");

        MowzieModelRenderer armLowerLeft = model.getCube("Arm MIDDLE LEFT");
        MowzieModelRenderer armLowerRight = model.getCube("Arm MIDDLE RIGHT");

        MowzieModelRenderer handLeft = model.getCube("Arm HAND LEFT");
        MowzieModelRenderer handRight = model.getCube("Arm HAND RIGHT");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };
        MowzieModelRenderer[] neck = new MowzieModelRenderer[] { head, neck5, neck4, neck3, neck2, neck1, bodyFront };

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { handLeft, armLowerLeft, armUpperLeft };
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { handRight, armLowerRight, armUpperRight };

        model.bob(bodyRear, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(thighLeft, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(thighRight, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.chainWave(tail, globalSpeed * 1.0F, globalHeight * 0.2F, 3, f, f1);
        model.chainSwing(tail, globalSpeed * 0.5F, globalHeight * 0.4F, 3, f, f1);

        model.chainWave(neck, globalSpeed * 1.0F, globalHeight * 0.1F, -3, f, f1);

        model.chainWave(armLeft, globalSpeed * 1.0F, globalHeight * 0.4F, -2, f, f1);
        model.chainWave(armRight, globalSpeed * 1.0F, globalHeight * 0.4F, -2, f, f1);

        model.walk(thighLeft, 1.0F * globalSpeed, globalDegree * 0.8F, false, 0F, 0.4F, f, f1);
        model.walk(calf1Left, 1.0F * globalSpeed, globalDegree * 0.5F, true, 1F, 0F, f, f1);
        model.walk(calf2Left, 1.0F * globalSpeed, globalDegree * 0.5F, false, 0F, 0F, f, f1);
        model.walk(footLeft, 1.0F * globalSpeed, globalDegree * 1.5F, true, 0.5F, 1F, f, f1);

        model.walk(thighRight, 1.0F * globalSpeed, globalDegree * 0.8F, true, 0F, 0.4F, f, f1);
        model.walk(calf1Right, 1.0F * globalSpeed, globalDegree * 0.5F, false, 1F, 0F, f, f1);
        model.walk(calf2Right, 1.0F * globalSpeed, globalDegree * 0.5F, true, 0F, 0F, f, f1);
        model.walk(footRight, 1.0F * globalSpeed, globalDegree * 1.5F, false, 0.5F, 1F, f, f1);

        int ticksExisted = entity.ticksExisted;

        model.chainWave(tail, globalSpeed * 0.5F, globalHeight * 0.8F, 3, ticksExisted, 0.1F);
        model.chainWave(neck, globalSpeed * 0.5F, globalHeight * 0.4F, -3, ticksExisted, 0.1F);
    }
}
