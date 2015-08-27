package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityTherizinosaurus;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationTherizinosaurus implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity e)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
		EntityTherizinosaurus entity = (EntityTherizinosaurus) e;
        Animator animator = model.animator;
        
        MowzieModelRenderer rightThigh = model.getCube("Right Thigh");
        MowzieModelRenderer bodyhips = model.getCube("Body hips");
        MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
        MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
        MowzieModelRenderer footRight = model.getCube("Foot Right");
        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer bodyMain = model.getCube("Body main");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail2Feathers = model.getCube("Tail 2 feathers");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail3Feathers = model.getCube("Tail 3 feathers");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail4Feathers = model.getCube("Tail 4 feathers");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");
        MowzieModelRenderer tail5Feathers = model.getCube("Tail 5 feathers");
        MowzieModelRenderer tail6Feathers = model.getCube("Tail 6 feathers");
        MowzieModelRenderer tail6FeathersR = model.getCube("Tail 6 feathers r");
        MowzieModelRenderer tail6FeathersL = model.getCube("Tail 6 feathers l");
        MowzieModelRenderer tail5FeathersR = model.getCube("Tail 5 feathers r");
        MowzieModelRenderer tail5FeathersL = model.getCube("Tail 5 feathers l");
        MowzieModelRenderer tail4FeathersR = model.getCube("Tail 4 feathers r");
        MowzieModelRenderer tail4FeathersL = model.getCube("Tail 4 feathers l");
        MowzieModelRenderer tail3FeathersR = model.getCube("Tail 3 feathers r");
        MowzieModelRenderer tail3FeathersL = model.getCube("Tail 3 feathers l");
        MowzieModelRenderer bodyShoulders = model.getCube("Body shoulders");
        MowzieModelRenderer bodyMain1 = model.getCube("Body main_1");
        MowzieModelRenderer neckBase = model.getCube("Neck base");
        MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
        MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm LEFT");
        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");
        MowzieModelRenderer neck5 = model.getCube("Neck 5");
        MowzieModelRenderer neck4feathers = model.getCube("Neck 4 feathers");
        MowzieModelRenderer neck6 = model.getCube("Neck 6");
        MowzieModelRenderer neck5Feathers = model.getCube("Neck 5 feathers");
        MowzieModelRenderer neck7 = model.getCube("Neck 7");
        MowzieModelRenderer neck6Feathers = model.getCube("Neck 6 feathers");
        MowzieModelRenderer head = model.getCube("Head");
        MowzieModelRenderer neck7Feathers = model.getCube("Neck 7 feathers");
        MowzieModelRenderer snout = model.getCube("Snout");
        MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");
        MowzieModelRenderer snoutRoof = model.getCube("Snout roof");
        MowzieModelRenderer upperJaw = model.getCube("Upper Jaw");
        MowzieModelRenderer neck7FeathersR = model.getCube("Neck 7 feathers r");
        MowzieModelRenderer neck7FeathersL = model.getCube("Neck 7 feathers l");
        MowzieModelRenderer neck6FeathersR = model.getCube("Neck 6 feathers r");
        MowzieModelRenderer neck6FeathersL = model.getCube("Neck 6 feathers l");
        MowzieModelRenderer neck5FeathersR = model.getCube("Neck 5 feathers r");
        MowzieModelRenderer neck5FeathersL = model.getCube("Neck 5 feathers l");
        MowzieModelRenderer neck4FeathersR = model.getCube("Neck 4 feathers r");
        MowzieModelRenderer neck4FeathersL = model.getCube("Neck 4 feathers l");
        MowzieModelRenderer LowerArmRight1 = model.getCube("Lower Arm Right_1");
        MowzieModelRenderer RightHand = model.getCube("Right hand");
        MowzieModelRenderer ArmRightFeathers = model.getCube("Arm right feathers");
        MowzieModelRenderer RightFinger1 = model.getCube("Right finger 1");
        MowzieModelRenderer RightFinger2 = model.getCube("Right finger 2");
        MowzieModelRenderer RighFinger3 = model.getCube("Right finger 3");
        MowzieModelRenderer RF1mid = model.getCube("RF1 mid");
        MowzieModelRenderer RF1end = model.getCube("RF1 end");
        MowzieModelRenderer RF2mid = model.getCube("RF2 mid");
        MowzieModelRenderer RF2end = model.getCube("RF2 end");
        MowzieModelRenderer RF3mid = model.getCube("RF3 mid");
        MowzieModelRenderer RF3end = model.getCube("RF3 end");
        MowzieModelRenderer LowerArmLEFT_1 = model.getCube("Lower Arm LEFT_1");
        MowzieModelRenderer Lefthand = model.getCube("Left hand");
        MowzieModelRenderer ArmLeftFeathers = model.getCube("Arm left feathers");
        MowzieModelRenderer Leftfinger1 = model.getCube("Left finger 1");
        MowzieModelRenderer Leftfinger2 = model.getCube("Left finger 2");
        MowzieModelRenderer Leftfinger3 = model.getCube("Left finger 3");
        MowzieModelRenderer LF1mid = model.getCube("LF1 mid");
        MowzieModelRenderer LF1end = model.getCube("LF1 end");
        MowzieModelRenderer LF2mid = model.getCube("LF1 end");
        MowzieModelRenderer LF2end = model.getCube("LF2 end");
        MowzieModelRenderer LF3mid = model.getCube("LF3 mid");
        MowzieModelRenderer LF3end = model.getCube("LF3 mid");
        MowzieModelRenderer LeftCalf1 = model.getCube("Left Calf 1");
        MowzieModelRenderer LeftCalf2 = model.getCube("Left Calf 2");
        MowzieModelRenderer FootLeft = model.getCube("Foot Left");

        MowzieModelRenderer[] neck = new MowzieModelRenderer[] { head, neck7, neck6, neck5, neck4, neck3, neck2, neck1 };
        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail1, tail2, tail3, tail4, tail5, tail6 };
//        MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { leftArm4, leftArm3, leftArm2, leftArm1 };
//        MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { rightArm4, rightArm3, rightArm2, rightArm1 };
//        MowzieModelRenderer[] legLeft = new MowzieModelRenderer[] { leftThigh, leftCalf, leftUpperFoot, leftFoot };
//        MowzieModelRenderer[] legRight = new MowzieModelRenderer[] { rightThigh, rightCalf, rightUpperFoot, rightFoot };

        int frame = entity.ticksExisted;

        float globalSpeed = 0.05F;
        float globalDegree = 0.06F;
//        float globalHeight = 2F;
//        float frontOffset = -1.35f;

        // DEBUG
        System.out.println(f);
        model.chainWave(tail, globalSpeed, globalDegree/1.5F, 1, frame, 1);
        model.chainWave(neck, globalSpeed, globalDegree*1.5F, 4, frame, 1);
        model.walk(rightThigh, 0.28F, degToRad(40.0F), false, 0.0F, 0.0F, f, f1);
        model.walk(leftThigh, 0.28F, degToRad(40.0F), true, 0.0F, 0.0F, f, f1);

    }
    
    private float degToRad(float degrees)
    {
        return (float) (Math.PI / 180 * degrees);
    }
}