package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurLeaellynasaura;
import org.jurassicraft.common.entity.EntityLeaellynasaura;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationLeaellynasaura extends DinosaurAnimator
{
    public AnimationLeaellynasaura()
    {
        super(new DinosaurLeaellynasaura());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityLeaellynasaura entity = (EntityLeaellynasaura) parEntity;

//        f = entity.ticksExisted;
//        f1 = 0.4F;

        float globalSpeed = 0.4F;
        float globalHeight = 0.8F;
        float globalDegree = 0.8F;

        MowzieModelRenderer head = parModel.getCube("Head ");

        MowzieModelRenderer neck1 = parModel.getCube("Neck BASE");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer neck4 = parModel.getCube("Neck 4");
        MowzieModelRenderer neck5 = parModel.getCube("Neck 5");

        MowzieModelRenderer bodyFront = parModel.getCube("Body FRONT");
        MowzieModelRenderer bodyRear = parModel.getCube("Body REAR");

        MowzieModelRenderer tail1 = parModel.getCube("Tail BASE");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");

        MowzieModelRenderer thighLeft = parModel.getCube("Leg UPPER LEFT");
        MowzieModelRenderer thighRight = parModel.getCube("Leg UPPER RIGHT");

        MowzieModelRenderer calf1Left = parModel.getCube("Leg MIDDLE LEFT");
        MowzieModelRenderer calf1Right = parModel.getCube("Leg MIDDLE RIGHT");

        MowzieModelRenderer calf2Left = parModel.getCube("Leg LOWER LEFT");
        MowzieModelRenderer calf2Right = parModel.getCube("Leg LOWER RIGHT");

        MowzieModelRenderer footLeft = parModel.getCube("Foot LEFT");
        MowzieModelRenderer footRight = parModel.getCube("Foot RIGHT");

        MowzieModelRenderer armUpperLeft = parModel.getCube("Arm UPPER LEFT");
        MowzieModelRenderer armUpperRight = parModel.getCube("Arm UPPER RIGHT");

        MowzieModelRenderer armLowerLeft = parModel.getCube("Arm MIDDLE LEFT");
        MowzieModelRenderer armLowerRight = parModel.getCube("Arm MIDDLE RIGHT");

        MowzieModelRenderer handLeft = parModel.getCube("Arm HAND LEFT");
        MowzieModelRenderer handRight = parModel.getCube("Arm HAND RIGHT");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] neck = new MowzieModelRenderer[]{head, neck5, neck4, neck3, neck2, neck1, bodyFront};

        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[]{handLeft, armLowerLeft, armUpperLeft};
        MowzieModelRenderer[] armRight = new MowzieModelRenderer[]{handRight, armLowerRight, armUpperRight};

        parModel.bob(bodyRear, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        parModel.bob(thighLeft, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        parModel.bob(thighRight, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        parModel.chainWave(tail, globalSpeed * 1.0F, globalHeight * 0.2F, 3, f, f1);
        parModel.chainSwing(tail, globalSpeed * 0.5F, globalHeight * 0.4F, 3, f, f1);

        parModel.chainWave(neck, globalSpeed * 1.0F, globalHeight * 0.1F, -3, f, f1);

        parModel.chainWave(armLeft, globalSpeed * 1.0F, globalHeight * 0.4F, -2, f, f1);
        parModel.chainWave(armRight, globalSpeed * 1.0F, globalHeight * 0.4F, -2, f, f1);

        parModel.walk(thighLeft, 1.0F * globalSpeed, globalDegree * 0.8F, false, 0F, 0.4F, f, f1);
        parModel.walk(calf1Left, 1.0F * globalSpeed, globalDegree * 0.5F, true, 1F, 0F, f, f1);
        parModel.walk(calf2Left, 1.0F * globalSpeed, globalDegree * 0.5F, false, 0F, 0F, f, f1);
        parModel.walk(footLeft, 1.0F * globalSpeed, globalDegree * 1.5F, true, 0.5F, 1F, f, f1);

        parModel.walk(thighRight, 1.0F * globalSpeed, globalDegree * 0.8F, true, 0F, 0.4F, f, f1);
        parModel.walk(calf1Right, 1.0F * globalSpeed, globalDegree * 0.5F, false, 1F, 0F, f, f1);
        parModel.walk(calf2Right, 1.0F * globalSpeed, globalDegree * 0.5F, true, 0F, 0F, f, f1);
        parModel.walk(footRight, 1.0F * globalSpeed, globalDegree * 1.5F, false, 0.5F, 1F, f, f1);

        int ticksExisted = entity.ticksExisted;

        parModel.chainWave(tail, globalSpeed * 0.5F, globalHeight * 0.8F, 3, ticksExisted, 0.1F);
        parModel.chainWave(neck, globalSpeed * 0.5F, globalHeight * 0.4F, -3, ticksExisted, 0.1F);
    }
}
