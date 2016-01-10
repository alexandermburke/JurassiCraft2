package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.common.animation.Animator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityPachycephalosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationPachycephalosaurus extends DinosaurAnimator
{
    public AnimationPachycephalosaurus()
    {
        super(JCEntityRegistry.pachycephalosaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityPachycephalosaurus entity = (EntityPachycephalosaurus) parEntity;
        Animator animator = model.animator;

        MowzieModelRenderer waist = model.getCube("Body Rear");
        MowzieModelRenderer chest = model.getCube("Body Middle");
        MowzieModelRenderer shoulders = model.getCube("Body Front");

        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");
        MowzieModelRenderer tail7 = model.getCube("Tail 7");

        MowzieModelRenderer upperLegLeft = model.getCube("Upper Leg Left");
        MowzieModelRenderer upperLegRight = model.getCube("Upper Leg Right");

        MowzieModelRenderer lowerLegLeft = model.getCube("Middle Leg Left");
        MowzieModelRenderer lowerLegRight = model.getCube("Middle Leg Right");

        MowzieModelRenderer upperFootLeft = model.getCube("Lower Leg Left");
        MowzieModelRenderer upperFootRight = model.getCube("Lower Leg Right");

        MowzieModelRenderer footLeft = model.getCube("Foot Left");
        MowzieModelRenderer footRight = model.getCube("Foot Right");

        MowzieModelRenderer upperArmLeft = model.getCube("Upper Arm Left");
        MowzieModelRenderer upperArmRight = model.getCube("Upper Arm Right");

        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm Left");
        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");

        MowzieModelRenderer handLeft = model.getCube("Hand Left");
        MowzieModelRenderer handRight = model.getCube("Hand Right");

        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[] { handRight, lowerArmRight, upperArmRight };
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[] { handLeft, lowerArmLeft, upperArmLeft };
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail7, tail6, tail5, tail4, tail3, tail2, tail1 };
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[] { waist, chest, shoulders, neck2, neck1, head };

        float globalSpeed = 0.8F;
        float globalDegree = 0.5F;
        float globalHeight = 1.0F;

        model.bob(waist, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(upperLegLeft, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(upperLegRight, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.walk(upperLegLeft, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.2F, f, f1);
        model.walk(lowerLegLeft, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        model.walk(upperFootLeft, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        model.walk(footLeft, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.1F, f, f1);

        model.walk(upperLegRight, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.2F, f, f1);
        model.walk(lowerLegRight, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        model.walk(upperFootRight, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        model.walk(footRight, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.1F, f, f1);

        model.chainWave(tailParts, globalSpeed * 1.0F, globalHeight * 0.05F, 2, f, f1);
        model.chainWave(bodyParts, globalSpeed * 1.0F, globalHeight * 0.025F, 1, f, f1);

        model.chainWave(rightArmParts, 1F * globalSpeed, -0.3F, 4, f, f1);
        model.chainWave(leftArmParts, 1F * globalSpeed, -0.3F, 4, f, f1);

        waist.rotateAngleX += f1 * 0.075F;
        shoulders.rotateAngleX += f1 * 0.05F;
        neck1.rotateAngleX += f1 * 0.05F;
        neck2.rotateAngleX += f1 * 0.05F;
        head.rotateAngleX += f1 * 0.075F;

        int ticksExisted = entity.ticksExisted;

        model.chainWave(tailParts, 0.1F, 0.025F, 2, parEntity.ticksExisted, 1F);
        model.chainWave(bodyParts, 0.1F, -0.03F, 4, parEntity.ticksExisted, 1F);
        model.chainWave(rightArmParts, 0.1F, -0.1F, 4, parEntity.ticksExisted, 1F);
        model.chainWave(leftArmParts, 0.1F, -0.1F, 4, parEntity.ticksExisted, 1F);

        parEntity.tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
