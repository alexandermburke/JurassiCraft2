package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.dinosaur.DinosaurTherizinosaurus;
import net.timeless.jurassicraft.common.entity.EntityTherizinosaurus;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.json.TabulaModelHelper;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationTherizinosaurus implements IModelAnimator
{
    protected static final Dinosaur theDinosaur = new DinosaurTherizinosaurus(); // do I need to get specific instance, I don't think so

    protected int stepsInAnim = 0;
    protected int currentAnimStep = 0;
    protected boolean finishedAnim = false;
    
    protected final String[] partNameArray = new String[] {
        "Body main", "Body main 1", "Body hips", 
        "Right Thigh", "Right Calf 1", "Right Calf 2", "Foot Right",
        "Left Thigh", "Left Calf 1", "Left Calf 2", "Foot Left",
        "Tail 1", "Tail 2", "Tail 2 feathers", 
        "Tail 3", "Tail 3 feathers", "Tail 3 feathers r", "Tail 3 feathers l",
        "Tail 4", "Tail 4 feathers", "Tail 4 feathers r", "Tail 4 feathers l",
        "Tail 5", "Tail 5 feathers", "Tail 5 feathers r", "Tail 5 feathers l",
        "Tail 6", "Tail 6 feathers", "Tail 6 feathers r", "Tail 6 feathers l",
        "Neck base", "Neck 1", "Neck 2", "Neck 3", 
        "Neck 4", "Neck 4 feathers", "Neck 4 feathers r", "Neck 4 feathers l",
        "Neck 5", "Neck 5 feathers", "Neck 5 feathers r", "Neck 5 feathers l",
        "Neck 6", "Neck 6 feathers", "Neck 6 feathers r", "Neck 6 feathers l",
        "Neck 7", "Neck 7 feathers", "Neck 7 feathers r", "Neck 7 feathers l",
        "Head", "Snout", "Snout roof", "Lower Jaw", "Upper Jaw", 
        "Body shoulders", "Lower Arm Right", "Lower Arm Right 1",
        "Right hand", "Arm right feathers", "Right finger 1", "RF1 mid", "RF1 end", 
        "Right finger 2", "RF2 mid", "RF2 end", "Right finger 3", "RF3 mid", "RF3 end",
        "Lower Arm LEFT", "Lower Arm LEFT 1", 
        "Left hand", "Arm left feathers", "Left finger 1", "LF1 mid", "LF1 end", 
        "Left finger 2", "LF1 end", "LF2 end", "Left finger 3", "LF3 mid", "LF3 mid"
    };
    
    protected int numParts = partNameArray.length;

    protected ModelDinosaur modelDefault = getTabulaModel("/assets/jurassicraft/models/entities/therizinosaurus", 0); 
    protected ModelDinosaur modelCurrent = getTabulaModel("/assets/jurassicraft/models/entities/therizinosaurus", 0);
    protected ModelDinosaur modelTarget = getTabulaModel("/assets/jurassicraft/models/entities/therizinosaurus", 0);
   
    protected ModelDinosaur modelPose1 = getTabulaModel("/assets/jurassicraft/models/entities/therizinosaurus_pose1", 0);

    protected MowzieModelRenderer[] passedInModelRendererArray = new MowzieModelRenderer[partNameArray.length];
    protected MowzieModelRenderer[] currentModelRendererArray = new MowzieModelRenderer[partNameArray.length];
    protected MowzieModelRenderer[] targetModelRendererArray = new MowzieModelRenderer[partNameArray.length];
    protected MowzieModelRenderer[] pose1ModelRendererArray = new MowzieModelRenderer[partNameArray.length];
    
    public ModelDinosaur getTabulaModel(String tabulaModel, int geneticVariant) 
    {
        // catch the exception so you can call method with implicit superconstructor
        try
        {
            return new ModelDinosaur(TabulaModelHelper.parseModel(tabulaModel), this);
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ModelDinosaur getTabulaModel(String tabulaModel)
    {
        return getTabulaModel(tabulaModel, 0);
    }

    public Dinosaur getDinosaur()
    {
        return theDinosaur;
    }

    @Override
    public void setRotationAngles(ModelJson parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity parEntity)
    {
        ModelDinosaur model = (ModelDinosaur) parModel;
        EntityTherizinosaurus entity = (EntityTherizinosaurus) parEntity;
        Animator animator = model.animator;
               
        for (int i = 0; i < numParts; i++) 
        {
            passedInModelRendererArray[i] = parModel.getCube(partNameArray[i]); 
            // DEBUG
            if (passedInModelRendererArray[i] == null) 
            {
                System.out.println(partNameArray[i]+" parsed as null");
            }
            currentModelRendererArray[i] = modelCurrent.getCube(partNameArray[i]);
            targetModelRendererArray[i] = modelTarget.getCube(partNameArray[i]);

            pose1ModelRendererArray[i] = modelPose1.getCube(partNameArray[i]);
        }

        nextTween(passedInModelRendererArray, 60);
        
//        int frame = entity.ticksExisted;
//
//        float globalSpeed = 0.05F;
//        float globalDegree = 0.06F;
//        float globalHeight = 2F;
//        float frontOffset = -1.35f;

//      MowzieModelRenderer bodyMain = model.getCube("Body main");
//      MowzieModelRenderer bodyMain1 = model.getCube("Body main_1");
//      MowzieModelRenderer bodyHips = model.getCube("Body hips");
//      MowzieModelRenderer rightThigh = model.getCube("Right Thigh");
//      MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");
//      MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
//      MowzieModelRenderer rightFoot = model.getCube("Foot Right");
//      MowzieModelRenderer leftThigh = model.getCube("Left Thigh");
//      MowzieModelRenderer leftCalf1 = model.getCube("Left Calf 1");
//      MowzieModelRenderer leftCalf2 = model.getCube("Left Calf 2");
//      MowzieModelRenderer leftFoot = model.getCube("Foot Left");
//      MowzieModelRenderer tail1 = model.getCube("Tail 1");
//      MowzieModelRenderer tail2 = model.getCube("Tail 2");
//      MowzieModelRenderer tail2Feathers = model.getCube("Tail 2 feathers");
//      MowzieModelRenderer tail3 = model.getCube("Tail 3");
//      MowzieModelRenderer tail3Feathers = model.getCube("Tail 3 feathers");
//      MowzieModelRenderer tail3FeathersR = model.getCube("Tail 3 feathers r");
//      MowzieModelRenderer tail3FeathersL = model.getCube("Tail 3 feathers l");
//      MowzieModelRenderer tail4 = model.getCube("Tail 4");
//      MowzieModelRenderer tail4Feathers = model.getCube("Tail 4 feathers");
//      MowzieModelRenderer tail4FeathersR = model.getCube("Tail 4 feathers r");
//      MowzieModelRenderer tail4FeathersL = model.getCube("Tail 4 feathers l");
//      MowzieModelRenderer tail5 = model.getCube("Tail 5");
//      MowzieModelRenderer tail5Feathers = model.getCube("Tail 5 feathers");
//      MowzieModelRenderer tail5FeathersR = model.getCube("Tail 5 feathers r");
//      MowzieModelRenderer tail5FeathersL = model.getCube("Tail 5 feathers l");
//      MowzieModelRenderer tail6 = model.getCube("Tail 6");
//      MowzieModelRenderer tail6Feathers = model.getCube("Tail 6 feathers");
//      MowzieModelRenderer tail6FeathersR = model.getCube("Tail 6 feathers r");
//      MowzieModelRenderer tail6FeathersL = model.getCube("Tail 6 feathers l");
//      MowzieModelRenderer neckBase = model.getCube("Neck base");
//      MowzieModelRenderer neck1 = model.getCube("Neck 1");
//      MowzieModelRenderer neck2 = model.getCube("Neck 2");
//      MowzieModelRenderer neck3 = model.getCube("Neck 3");
//      MowzieModelRenderer neck4 = model.getCube("Neck 4");
//      MowzieModelRenderer neck4feathers = model.getCube("Neck 4 feathers");
//      MowzieModelRenderer neck4FeathersR = model.getCube("Neck 4 feathers r");
//      MowzieModelRenderer neck4FeathersL = model.getCube("Neck 4 feathers l");
//      MowzieModelRenderer neck5 = model.getCube("Neck 5");
//      MowzieModelRenderer neck5Feathers = model.getCube("Neck 5 feathers");
//      MowzieModelRenderer neck5FeathersR = model.getCube("Neck 5 feathers r");
//      MowzieModelRenderer neck5FeathersL = model.getCube("Neck 5 feathers l");
//      MowzieModelRenderer neck6 = model.getCube("Neck 6");
//      MowzieModelRenderer neck6Feathers = model.getCube("Neck 6 feathers");
//      MowzieModelRenderer neck6FeathersR = model.getCube("Neck 6 feathers r");
//      MowzieModelRenderer neck6FeathersL = model.getCube("Neck 6 feathers l");
//      MowzieModelRenderer neck7 = model.getCube("Neck 7");
//      MowzieModelRenderer neck7Feathers = model.getCube("Neck 7 feathers");
//      MowzieModelRenderer neck7FeathersR = model.getCube("Neck 7 feathers r");
//      MowzieModelRenderer neck7FeathersL = model.getCube("Neck 7 feathers l");
//      MowzieModelRenderer head = model.getCube("Head");
//      MowzieModelRenderer snout = model.getCube("Snout");
//      MowzieModelRenderer snoutRoof = model.getCube("Snout roof");
//      MowzieModelRenderer lowerJaw = model.getCube("Lower Jaw");
//      MowzieModelRenderer upperJaw = model.getCube("Upper Jaw");
//      MowzieModelRenderer bodyShoulders = model.getCube("Body shoulders");
//      MowzieModelRenderer lowerArmRight = model.getCube("Lower Arm Right");
//      MowzieModelRenderer LowerArmRight1 = model.getCube("Lower Arm Right_1");
//      MowzieModelRenderer RightHand = model.getCube("Right hand");
//      MowzieModelRenderer ArmRightFeathers = model.getCube("Arm right feathers");
//      MowzieModelRenderer RightFinger1 = model.getCube("Right finger 1");
//      MowzieModelRenderer RF1mid = model.getCube("RF1 mid");
//      MowzieModelRenderer RF1end = model.getCube("RF1 end");
//      MowzieModelRenderer RightFinger2 = model.getCube("Right finger 2");
//      MowzieModelRenderer RF2mid = model.getCube("RF2 mid");
//      MowzieModelRenderer RF2end = model.getCube("RF2 end");
//      MowzieModelRenderer RightFinger3 = model.getCube("Right finger 3");
//      MowzieModelRenderer RF3mid = model.getCube("RF3 mid");
//      MowzieModelRenderer RF3end = model.getCube("RF3 end");
//      MowzieModelRenderer lowerArmLeft = model.getCube("Lower Arm LEFT");
//      MowzieModelRenderer LowerArmLeft1 = model.getCube("Lower Arm LEFT_1");
//      MowzieModelRenderer Lefthand = model.getCube("Left hand");
//      MowzieModelRenderer ArmLeftFeathers = model.getCube("Arm left feathers");
//      MowzieModelRenderer leftFinger1 = model.getCube("Left finger 1");
//      MowzieModelRenderer leftFinger1Mid = model.getCube("LF1 mid");
//      MowzieModelRenderer leftFinger1End = model.getCube("LF1 end");
//      MowzieModelRenderer leftFinger2 = model.getCube("Left finger 2");
//      MowzieModelRenderer leftFinger2Mid = model.getCube("LF1 end");
//      MowzieModelRenderer leftFinger2End = model.getCube("LF2 end");
//      MowzieModelRenderer leftFinger3 = model.getCube("Left finger 3");
//      MowzieModelRenderer leftFinger3Mid = model.getCube("LF3 mid");
//      MowzieModelRenderer leftFinger3End = model.getCube("LF3 mid");

//      MowzieModelRenderer[] neck = new MowzieModelRenderer[] { head, neck7, neck6, neck5, neck4, neck3, neck2, neck1 };
//      MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail1, tail2, tail3, tail4, tail5, tail6 };
//      MowzieModelRenderer[] leftFinger3Chain = new MowzieModelRenderer[] { leftFinger3, leftFinger3Mid, leftFinger3End };
//      MowzieModelRenderer[] leftFinger2Chain = new MowzieModelRenderer[] { leftFinger2, leftFinger2Mid, leftFinger2End };
//      MowzieModelRenderer[] leftFinger1Chain = new MowzieModelRenderer[] { leftFinger1, leftFinger1Mid, leftFinger1End };
//      MowzieModelRenderer[] legRight = new MowzieModelRenderer[] { rightThigh, rightCalf, rightUpperFoot, rightFoot };

//        // DEBUG
//        System.out.println(f);
//        model.chainWave(tail, globalSpeed, globalDegree/1.5F, 1, frame, 1);
//        model.chainWave(neck, globalSpeed, globalDegree*1.5F, 4, frame, 1);
//        float leftFingerInitAngle = leftFinger1.rotateAngleZ;
//        model.chainFlap(leftFinger1Chain, globalSpeed, globalDegree*10.0F, 4, frame, 1);
//        float leftFingerCurl = leftFinger1.rotateAngleZ - leftFingerInitAngle; 
//        leftFinger2.rotateAngleZ += leftFingerCurl;
//        leftFinger2Mid.rotateAngleZ += leftFingerCurl;
//        leftFinger2End.rotateAngleZ += leftFingerCurl;
//
//        float initialAngleRad = rightThigh.rotateAngleX;
//        model.walk(rightThigh, 0.28F, degToRad(40.0F), false, 0.0F, 0.0F, f, f1);
//        float walkCyclePercent = (rightThigh.rotateAngleX-initialAngleRad)/degToRad(20.0F);
//        // DEBUG
//        System.out.println(radToDeg(rightThigh.rotateAngleX-initialAngleRad));
//        rightCalf1.rotateAngleX += walkCyclePercent * degToRad(5.0F);
//        rightCalf2.rotateAngleX += walkCyclePercent * degToRad(15.0F);
//        rightFoot.rotateAngleX += walkCyclePercent * degToRad(30.0F);
////        model.walk(rightCalf1, 0.28F, degToRad(20.0F), false, 0.0F, 0.0F, f, f1);
////        model.walk(rightCalf2, 0.28F, degToRad(60.0F), false, 0.0F, 0.0F, f, f1);
////        model.walk(rightFoot, 0.28F, degToRad(120.0F), true, 0.0F, 0.0F, f*2, f1);
//        model.walk(leftThigh, 0.28F, degToRad(40.0F), true, 0.0F, 0.0F, f, f1);
//        leftCalf1.rotateAngleX -= walkCyclePercent * degToRad(5.0F);
//        leftCalf2.rotateAngleX -= walkCyclePercent * degToRad(15.0F);
//        leftFoot.rotateAngleX -= walkCyclePercent * degToRad(30.0F);
////        model.walk(leftCalf1, 0.28F, degToRad(20.0F), true, 0.0F, 0.0F, f, f1);
////        model.walk(leftCalf2, 0.28F, degToRad(60.0F), true, 0.0F, 0.0F, f, f1);
////        model.walk(leftFoot, 0.28F, degToRad(120.0F), false, 0.0F, 0.0F, f*2, f1);
//        model.walk(lowerArmRight, 0.28F, degToRad(80.0F), true, 0.0F, 0.0F, f, f1);
//        model.walk(lowerArmLeft, 0.28F, degToRad(80.0F), false, 0.0F, 0.0F, f, f1);
//        bodyHips.rotateAngleZ += walkCyclePercent * degToRad(18.0F);
//        bodyMain.rotateAngleZ -= walkCyclePercent * degToRad(9.0F);
//        model.bob(bodyMain, globalSpeed, 1.0F, false, frame, f1);
//        bodyShoulders.rotateAngleZ -= walkCyclePercent * degToRad(9.0F);
//        model.flap(tail4FeathersR, globalSpeed*10, degToRad(30.0F), false, 0.0F, 0.0F, f, f1);
//        model.flap(tail4FeathersL, globalSpeed*10, degToRad(30.0F), true, 0.0F, 0.0F, f, f1);
//        model.flap(tail5FeathersR, globalSpeed*10, degToRad(30.0F), false, 0.0F, 0.0F, f, f1);
//        model.flap(tail5FeathersL, globalSpeed*10, degToRad(30.0F), true, 0.0F, 0.0F, f, f1);
//        model.flap(tail6FeathersR, globalSpeed*10, degToRad(30.0F), false, 0.0F, 0.0F, f, f1);
//        model.flap(tail6FeathersL, globalSpeed*10, degToRad(30.0F), true, 0.0F, 0.0F, f, f1);

    }
    
    private void nextTween(MowzieModelRenderer[] parPassedInModelRendererArray, int parAnimSteps)
    {
        for (int i = 0; i < numParts; i++)
        {
            currentModelRendererArray[i].rotateAngleX = parPassedInModelRendererArray[i].rotateAngleX 
                    = pose1ModelRendererArray[i].rotateAngleX;
            currentModelRendererArray[i].rotateAngleY = parPassedInModelRendererArray[i].rotateAngleY 
                    = pose1ModelRendererArray[i].rotateAngleY;
            currentModelRendererArray[i].rotateAngleZ = parPassedInModelRendererArray[i].rotateAngleZ 
                    = pose1ModelRendererArray[i].rotateAngleZ;
        }
        
    }

    private float degToRad(float degrees)
    {
        return (float) (Math.PI / 180.0F * degrees);
    }
    
    private float radToDeg(float rads)
    {
        return (float) (180.0F / Math.PI * rads);
    }
}