package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurAchillobator;
import org.jurassicraft.common.entity.EntityAchillobator;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationAchillobator extends DinosaurAnimator
{
    public AnimationAchillobator()
    {
        super(new DinosaurAchillobator());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        ModelDinosaur model = parModel;
        Animator animator = model.animator;

        //        f = entity.ticksExisted;
        //        f1 = 1F;

        float speed = 0.75F;
        float height = 2F * f1;

        MowzieModelRenderer waist = model.getCube("body3");
        MowzieModelRenderer chest = model.getCube("body2");
        MowzieModelRenderer shoulders = model.getCube("body1");
        MowzieModelRenderer leftThigh = model.getCube("Left thigh");
        MowzieModelRenderer rightThigh = model.getCube("Right thigh");
        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");
        MowzieModelRenderer head = model.getCube("head");
        MowzieModelRenderer leftShin = model.getCube("Left shin");
        MowzieModelRenderer rightShin = model.getCube("Right shin");
        MowzieModelRenderer leftUpperFoot = model.getCube("Left upper foot");
        MowzieModelRenderer leftFoot = model.getCube("Left foot");
        MowzieModelRenderer rightUpperFoot = model.getCube("Right upper foot");
        MowzieModelRenderer rightFoot = model.getCube("Right foot");
        MowzieModelRenderer upperArmRight = model.getCube("Right arm");
        MowzieModelRenderer upperArmLeft = model.getCube("Left arm");
        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");
        MowzieModelRenderer tail6 = model.getCube("tail6");
        // MowzieModelRenderer Right_Claw_1 = model.getCube("Right Claw 1");
        // MowzieModelRenderer Left_Claw_1 = model.getCube("Left Claw 1");
        // MowzieModelRenderer Lower_Jaw = model.getCube("down_jaw");

        MowzieModelRenderer Lower_Arm_Right = model.getCube("Right arm");
        MowzieModelRenderer Lower_Arm_Left = model.getCube("Left arm");
        MowzieModelRenderer Hand_Right = model.getCube("Right hand");
        MowzieModelRenderer Hand_Left = model.getCube("Left hand");

        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[]{Hand_Right, Lower_Arm_Right, upperArmRight};
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[]{Hand_Left, Lower_Arm_Left, upperArmLeft};
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[]{waist, chest, shoulders, neck4, neck3, neck2, neck1, head};

        model.bob(waist, 1F * speed, height, false, f, f1);
        model.bob(leftThigh, 1F * speed, height, false, f, f1);
        model.bob(rightThigh, 1F * speed, height, false, f, f1);
        model.walk(shoulders, 1F * speed, 0.2F, true, 1, 0, f, f1);
        model.walk(chest, 1F * speed, 0.2F, false, 0.5F, 0, f, f1);

        model.walk(leftThigh, 0.5F * speed, 0.7F, false, 3.14F, 0.2F, f, f1);
        model.walk(leftShin, 0.5F * speed, 0.6F, false, 1.5F, 0.3F, f, f1);
        model.walk(leftUpperFoot, 0.5F * speed, 0.8F, false, -1F, -0.1F, f, f1);
        model.walk(leftFoot, 0.5F * speed, 1.5F, true, -1F, 1F, f, f1);

        model.walk(rightThigh, 0.5F * speed, 0.7F, true, 3.14F, 0.2F, f, f1);
        model.walk(rightShin, 0.5F * speed, 0.6F, true, 1.5F, 0.3F, f, f1);
        model.walk(rightUpperFoot, 0.5F * speed, 0.8F, true, -1F, -0.1F, f, f1);
        model.walk(rightFoot, 0.5F * speed, 1.5F, false, -1F, 1F, f, f1);

        shoulders.rotationPointY -= 0.5 * f1;
        shoulders.rotationPointZ -= 0.5 * f1;
        shoulders.rotateAngleX += 0.5 * f1;
        chest.rotateAngleX += 0.1 * f1;
        neck1.rotateAngleX += 0.1 * f1;
        neck2.rotateAngleX += 0.1 * f1;
        neck3.rotateAngleX -= 0.3 * f1;
        neck4.rotateAngleX -= 0.2 * f1;
        head.rotateAngleX -= 0.3 * f1;

        model.chainSwing(tailParts, 0.5F * speed, -0.1F, 2, f, f1);
        model.chainWave(tailParts, 1F * speed, -0.1F, 2.5F, f, f1);
        model.chainWave(bodyParts, 1F * speed, -0.1F, 4, f, f1);

        model.chainWave(rightArmParts, 1F * speed, -0.3F, 4, f, f1);
        model.chainWave(leftArmParts, 1F * speed, -0.3F, 4, f, f1);

        // Idling
        model.chainWave(tailParts, 0.1F, 0.05F, 2, parEntity.ticksExisted, 1F);
        model.chainWave(bodyParts, 0.1F, -0.03F, 5, parEntity.ticksExisted, 1F);
        model.chainWave(rightArmParts, 0.1F, -0.1F, 4, parEntity.ticksExisted, 1F);
        model.chainWave(leftArmParts, 0.1F, -0.1F, 4, parEntity.ticksExisted, 1F);

        model.faceTarget(head, 2, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 2, rotationYaw, rotationPitch);

        ((EntityAchillobator) parEntity).tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
