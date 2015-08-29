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
    /*
     * Change the following fields for your custom dinosaur
     */
    protected static final Dinosaur theDinosaur = new DinosaurTherizinosaurus(); // do I need to get specific instance, I don't think so

    // Tell the code where your tabula model assets are
    // the first one must be your "default" pose (i.e one that is used at spawn time)
    protected static final String[] modelAssetPaths = new String[] {
            "/assets/jurassicraft/models/entities/therizinosaurus",
            "/assets/jurassicraft/models/entities/therizinosaurus_pose1",
            "/assets/jurassicraft/models/entities/therizinosaurus_pose2",
            "/assets/jurassicraft/models/entities/therizinosaurus_pose3"
    };

    // Tell the code the names of all your tabula model parts
    // NOTE: all the models must use exactly same number and names of parts
    protected static final String[] partNameArray = new String[] {
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

    /*
     * Do NOT change any of the following field initializations
     */
    protected int targetModelIndex = 1; // 0 is default, so 1 is first custom pose
    protected int stepsInTween = 0;
    protected int currentTweenStep = 0;
    protected boolean finishedTween = false;

    protected static int numParts = partNameArray.length;

    // initialize non-static models
    protected ModelDinosaur modelDefault = getTabulaModel(modelAssetPaths[0], 0); 
    protected ModelDinosaur modelCurrent = getTabulaModel(modelAssetPaths[0], 0);
    protected ModelDinosaur modelTarget = getTabulaModel(modelAssetPaths[0], 0);

    // initialize custom models array
    protected static ModelDinosaur[] modelArray = new ModelDinosaur[modelAssetPaths.length];
    static {
        for (int i = 0; i < modelAssetPaths.length; i++)
        {
            modelArray[i] = getTabulaModel(modelAssetPaths[i], 0);
        }
    };

    // initialize model renderer arrays
    protected MowzieModelRenderer[] passedInModelRendererArray = new MowzieModelRenderer[partNameArray.length];
    protected MowzieModelRenderer[] defaultModelRendererArray = new MowzieModelRenderer[partNameArray.length];
    protected MowzieModelRenderer[] currentModelRendererArray = new MowzieModelRenderer[partNameArray.length];
    protected MowzieModelRenderer[] targetModelRendererArray = new MowzieModelRenderer[partNameArray.length];

    // initialize custom model renderer arrays (first index is model, second is part within model)
    protected static MowzieModelRenderer[][] modelRendererArray = new MowzieModelRenderer[modelAssetPaths.length][partNameArray.length];
    static {
        for (int i = 0; i < modelAssetPaths.length; i++)
        {
            // DEBUG
            if (partNameArray == null) System.out.println("Part name array is null");
            if (modelRendererArray[i] == null) System.out.println("model renderer array element i is null");
            if (modelRendererArray == null) System.out.println("model renderer array is null");
            modelRendererArray[i] = new MowzieModelRenderer[partNameArray.length];
            // fill in the model renderer arrays
            for (int j = 0; j < numParts; j++) 
            {
                // fill in custom pose arrays
                modelRendererArray[i][j] = modelArray[i].getCube(partNameArray[j]);
            }
        }
    }
    
    public static ModelDinosaur getTabulaModel(String tabulaModel, int geneticVariant) 
    {
        // catch the exception so you can call method with implicit superconstructor
        try
        {
            return new ModelDinosaur(TabulaModelHelper.parseModel(tabulaModel), null); // okay to use null for animator parameter as we get animator from passed-in model
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
        setRotationAngles((ModelDinosaur)parModel, f, f1, rotation, rotationYaw, rotationPitch, partialTicks, (EntityTherizinosaurus)parEntity);
    }
    
    protected void setRotationAngles(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityTherizinosaurus parEntity)    
    {
        Animator animator = parModel.animator;

        // fill in the model renderer arrays
        for (int i = 0; i < numParts; i++) 
        {
            passedInModelRendererArray[i] = parModel.getCube(partNameArray[i]); 
            // DEBUG
            if (passedInModelRendererArray[i] == null) 
            {
                System.out.println(partNameArray[i]+" parsed as null");
            }
            defaultModelRendererArray[i] = modelDefault.getCube(partNameArray[i]);
            currentModelRendererArray[i] = modelCurrent.getCube(partNameArray[i]);
        }
        
        // get target model renderer array from custom array
        targetModelRendererArray = modelRendererArray[targetModelIndex];
        
        // set initial target model and steps to get there
        if (stepsInTween == 0) // hasn't been set yet
        {
            // DEBUG
            System.out.println("setting initial pose");
//            targetModelRendererArray = modelRendererArray[targetModelIndex];
            stepsInTween = 300;
        }

        nextTween(passedInModelRendererArray);

        // DEBUG
        int slowDownFactor = 5;
        
        // check for end of animation and set next target in sequence
        if (finishedTween)
        {
            // DEBUG
            System.out.println("finished tween");
            
            // reset tween step
            currentTweenStep = 0;
            finishedTween = false;
            
            // set next target
            if (targetModelIndex == 0)
            {
                // DEBUG
                System.out.println("setting target pose to pose1");
                targetModelIndex = 1;
                stepsInTween = slowDownFactor * 100;
            }
            else if (targetModelIndex == 1)
            {
                // DEBUG
                System.out.println("setting target pose to pose2");
                targetModelIndex = 2;
                stepsInTween = slowDownFactor * 100;
            }
            else if (targetModelIndex == 2)
            {
                // DEBUG
                System.out.println("setting target pose to pose3");
                targetModelIndex = 3;
                stepsInTween = slowDownFactor * 60;
            }
            else if (targetModelIndex == 3)
            {
                // DEBUG
                System.out.println("setting target pose to default");
                targetModelIndex = 0;
                stepsInTween = slowDownFactor * 100;
            }
        }

        // you can still add chain, walk, bob, etc in this method.
        performMowzieAnimations(parModel, f, f1);
    }
        
    
    protected void nextTween(MowzieModelRenderer[] parPassedInModelRendererArray)
    {
        if (currentTweenStep == stepsInTween)
        {
            parPassedInModelRendererArray = currentModelRendererArray;
            finishedTween = true;
            return;
        }
        
        // tween the passed in model towards target pose
        for (int i = 0; i < numParts; i++)
        {
            nextTweenRotations(parPassedInModelRendererArray, i);
            nextTweenPositions(parPassedInModelRendererArray, i);
            nextTweenOffsets(parPassedInModelRendererArray, i);
        }
        
        currentTweenStep++;
    }
    
    protected void nextTweenRotations(MowzieModelRenderer[] parPassedInModelRendererArray, int parTweenStep)
    {
        // tween the rotations
        currentModelRendererArray[parTweenStep].rotateAngleX = parPassedInModelRendererArray[parTweenStep].rotateAngleX 
                = currentModelRendererArray[parTweenStep].rotateAngleX + 
                (targetModelRendererArray[parTweenStep].rotateAngleX - currentModelRendererArray[parTweenStep].rotateAngleX)
                / (stepsInTween - currentTweenStep);
        currentModelRendererArray[parTweenStep].rotateAngleY = parPassedInModelRendererArray[parTweenStep].rotateAngleY 
                = currentModelRendererArray[parTweenStep].rotateAngleY + 
                (targetModelRendererArray[parTweenStep].rotateAngleY - currentModelRendererArray[parTweenStep].rotateAngleY)
                / (stepsInTween - currentTweenStep);
        currentModelRendererArray[parTweenStep].rotateAngleZ = parPassedInModelRendererArray[parTweenStep].rotateAngleZ 
                = currentModelRendererArray[parTweenStep].rotateAngleZ + 
                (targetModelRendererArray[parTweenStep].rotateAngleZ - currentModelRendererArray[parTweenStep].rotateAngleZ)
                / (stepsInTween - currentTweenStep);
    }

    protected void nextTweenPositions(MowzieModelRenderer[] parPassedInModelRendererArray, int parTweenStep)
    {
        // tween the positions
        currentModelRendererArray[parTweenStep].rotationPointX = parPassedInModelRendererArray[parTweenStep].rotationPointX 
                = currentModelRendererArray[parTweenStep].rotationPointX + 
                (targetModelRendererArray[parTweenStep].rotationPointX - currentModelRendererArray[parTweenStep].rotationPointX)
                / (stepsInTween - currentTweenStep);
        currentModelRendererArray[parTweenStep].rotationPointY = parPassedInModelRendererArray[parTweenStep].rotationPointY 
                = currentModelRendererArray[parTweenStep].rotationPointY + 
                (targetModelRendererArray[parTweenStep].rotationPointY - currentModelRendererArray[parTweenStep].rotationPointY)
                / (stepsInTween - currentTweenStep);
        currentModelRendererArray[parTweenStep].rotationPointZ = parPassedInModelRendererArray[parTweenStep].rotationPointZ 
                = currentModelRendererArray[parTweenStep].rotationPointZ + 
                (targetModelRendererArray[parTweenStep].rotationPointZ - currentModelRendererArray[parTweenStep].rotationPointZ)
                / (stepsInTween - currentTweenStep);
    }

    protected void nextTweenOffsets(MowzieModelRenderer[] parPassedInModelRendererArray, int parTweenStep)
    {
        // tween the offsets
        currentModelRendererArray[parTweenStep].offsetX = parPassedInModelRendererArray[parTweenStep].offsetX 
                = currentModelRendererArray[parTweenStep].offsetX + 
                (targetModelRendererArray[parTweenStep].offsetX - currentModelRendererArray[parTweenStep].offsetX)
                / (stepsInTween - currentTweenStep);
        currentModelRendererArray[parTweenStep].offsetY = parPassedInModelRendererArray[parTweenStep].offsetY 
                = currentModelRendererArray[parTweenStep].offsetY + 
                (targetModelRendererArray[parTweenStep].offsetY - currentModelRendererArray[parTweenStep].offsetY)
                / (stepsInTween - currentTweenStep);
        currentModelRendererArray[parTweenStep].offsetZ = parPassedInModelRendererArray[parTweenStep].offsetZ 
                = currentModelRendererArray[parTweenStep].offsetZ + 
                (targetModelRendererArray[parTweenStep].offsetZ - currentModelRendererArray[parTweenStep].offsetZ)
                / (stepsInTween - currentTweenStep);
    }
    
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1)
    {//        int frame = entity.ticksExisted;
//
        float globalSpeed = 0.05F;
        float globalDegree = 0.06F;
        float globalHeight = 2F;
        float frontOffset = -1.35f;

//      MowzieModelRenderer bodyMain = model.getCube("Body main");
//      MowzieModelRenderer bodyMain1 = model.getCube("Body main_1");
//      MowzieModelRenderer bodyHips = model.getCube("Body hips");
      MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");
//      MowzieModelRenderer rightCalf1 = model.getCube("Right Calf 1");
//      MowzieModelRenderer rightCalf2 = model.getCube("Right Calf 2");
//      MowzieModelRenderer rightFoot = model.getCube("Foot Right");
      MowzieModelRenderer leftThigh = parModel.getCube("Left Thigh");
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
      MowzieModelRenderer lowerArmRight = parModel.getCube("Lower Arm Right");
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
      MowzieModelRenderer lowerArmLeft = parModel.getCube("Lower Arm LEFT");
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

        parModel.walk(rightThigh, 0.28F, degToRad(40.0F), false, 0.0F, 0.0F, f, f1);
        parModel.walk(leftThigh, 0.28F, degToRad(40.0F), true, 0.0F, 0.0F, f, f1);
        parModel.walk(lowerArmRight, 0.28F, degToRad(80.0F), true, 0.0F, 0.0F, f, f1);
        parModel.walk(lowerArmLeft, 0.28F, degToRad(80.0F), false, 0.0F, 0.0F, f, f1);

    }

    protected float degToRad(float degrees)
    {
        return (float) (Math.PI / 180.0F * degrees);
    }
    
    protected float radToDeg(float rads)
    {
        return (float) (180.0F / Math.PI * rads);
    }
}