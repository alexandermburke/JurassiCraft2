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
     * Define your animation sequence here
     * First element is target pose model index (i.e. order of model assets listed in
     * modelAssetPaths array above),
     * Second element is the number of ticks it should take to tween to that pose
     */
    protected static int[][] animationSequence = new int[][] {
        {1, 300},
        {2, 300},
        {3, 300},
        {0, 100}        
        };
            
    /*
     * Do NOT change any of the following field initializations
     */
    protected int currentSequenceStep = 0;
    protected int targetModelIndex = 1; // 0 is default, so 1 is first custom pose
    protected int stepsInTween = 0;
    protected int currentTweenStep = 0;
    protected boolean finishedTween = false;

    protected static int numParts = partNameArray.length;

    // initialize non-static models
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
    protected MowzieModelRenderer[] passedInModelRendererArray = new MowzieModelRenderer[numParts];
    protected MowzieModelRenderer[] targetModelRendererArray = new MowzieModelRenderer[numParts];

    // initialize current pose tracking arrays
    protected float[][] currentRotationArray = new float[numParts][3];
    protected float[][] currentPositionArray = new float[numParts][3];
    protected float[][] currentOffsetArray = new float[numParts][3];

    // initialize custom model renderer arrays (first index is model, second is part within model)
    protected static MowzieModelRenderer[][] modelRendererArray = new MowzieModelRenderer[modelAssetPaths.length][numParts];
    static {
        for (int i = 0; i < modelAssetPaths.length; i++)
        {
            // DEBUG
            if (partNameArray == null) System.out.println("Part name array is null");
            if (modelRendererArray[i] == null) System.out.println("model renderer array element i is null");
            if (modelRendererArray == null) System.out.println("model renderer array is null");
            modelRendererArray[i] = new MowzieModelRenderer[numParts];
            // fill in the model renderer arrays
            for (int j = 0; j < numParts; j++) 
            {
                // fill in custom pose arrays
                modelRendererArray[i][j] = modelArray[i].getCube(partNameArray[j]);
            }
        }
    }

    protected void copyModelRendererArrayToCurrent(MowzieModelRenderer[] parModelRendererArray)
    {
        for (int i = 0; i < parModelRendererArray.length; i++)
        {
            currentRotationArray[i][0] = parModelRendererArray[i].rotateAngleX;
            currentRotationArray[i][1] = parModelRendererArray[i].rotateAngleY;
            currentRotationArray[i][2] = parModelRendererArray[i].rotateAngleZ;
            currentPositionArray[i][0] = parModelRendererArray[i].rotationPointX;
            currentPositionArray[i][1] = parModelRendererArray[i].rotationPointY;
            currentPositionArray[i][2] = parModelRendererArray[i].rotationPointZ;
            currentOffsetArray[i][0] = parModelRendererArray[i].offsetX;
            currentOffsetArray[i][1] = parModelRendererArray[i].offsetY;
            currentOffsetArray[i][2] = parModelRendererArray[i].offsetZ;
        }
        
    }

    // need boolean to indicate first tick run because need to copy between static and instance fields
    protected boolean isFirstTick = true;
    
    // cast model and entity to JurassiCraft2 classes
    @Override
    public void setRotationAngles(ModelJson parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity parEntity)
    {
        setRotationAngles((ModelDinosaur)parModel, f, f1, rotation, rotationYaw, rotationPitch, partialTicks, (EntityTherizinosaurus)parEntity);
    }

    /*
     * You should NOT change anything in this method! Models and tween sequences are defined
     * in the field initializations at the top of this class' code
     */
    protected void setRotationAngles(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityTherizinosaurus parEntity)    
    {
        performJabelarAnimations(parModel, f, f1);

        // you can still add chain, walk, bob, etc.
        performMowzieAnimations(parModel, f, f1);
    }

    protected void performJabelarAnimations(ModelDinosaur parModel, float f, float f1)
    {
        convertPassedInModelToModelRendererArray(parModel);
        
        // initialize current pose arrays if first tick
        if (isFirstTick)
        {
            isFirstTick = false;
            copyModelRendererArrayToCurrent(passedInModelRendererArray);
            setNextTween();
        }
        
        performNextTweenStep(passedInModelRendererArray);
        
        // DEBUG
        System.out.println("the rotation of neck = "+radToDeg(passedInModelRendererArray[partIndexFromName("neck 1")].rotateAngleX));

        // check for end of animation and set next target in sequence
        if (finishedTween)
        {
            handleFinishedTween();
        }
    }
    
    protected void convertPassedInModelToModelRendererArray(ModelDinosaur parModel)
    {
        for (int i = 0; i < numParts; i++) 
        {
            passedInModelRendererArray[i] = parModel.getCube(partNameArray[i]); 

            // DEBUG
            if (passedInModelRendererArray[i] == null) 
            {
                System.out.println(partNameArray[i]+" parsed as null");
            }
        }
    }
    
    protected void performNextTweenStep(MowzieModelRenderer[] parPassedInModelRendererArray)
    {
        // tween the passed in model towards target pose
        for (int i = 0; i < numParts; i++)
        {
            nextTweenRotations(parPassedInModelRendererArray, i);
            nextTweenPositions(parPassedInModelRendererArray, i);
            nextTweenOffsets(parPassedInModelRendererArray, i);
        }
        
        // update current position tracking arrays
        copyModelRendererArrayToCurrent(parPassedInModelRendererArray);
        
        currentTweenStep++;
//      // DEBUG
//      System.out.println("current tween step = "+currentTweenStep);
        if (currentTweenStep >= stepsInTween)
        {
            finishedTween = true;
        }
    }

    /*
     * Linear tween of the rotateAngles between current angles and target angles
     */
    protected void nextTweenRotations(MowzieModelRenderer[] parPassedInModelRendererArray, int parPartIndex)
    {
        parPassedInModelRendererArray[parPartIndex].rotateAngleX =
                currentRotationArray[parPartIndex][0] + 
                (targetModelRendererArray[parPartIndex].rotateAngleX - currentRotationArray[parPartIndex][0])
                / (stepsInTween - currentTweenStep);
        parPassedInModelRendererArray[parPartIndex].rotateAngleY =
                currentRotationArray[parPartIndex][1] + 
                (targetModelRendererArray[parPartIndex].rotateAngleY - currentRotationArray[parPartIndex][1])
                / (stepsInTween - currentTweenStep);
        parPassedInModelRendererArray[parPartIndex].rotateAngleZ =
                currentRotationArray[parPartIndex][2] + 
                (targetModelRendererArray[parPartIndex].rotateAngleZ - currentRotationArray[parPartIndex][2])
                / (stepsInTween - currentTweenStep);
        // DEBUG
        if (parPartIndex == partIndexFromName("neck 1"))
        {
            System.out.println("previous current value = "+radToDeg(currentRotationArray[parPartIndex][0])+", difference to target = "+ 
                    radToDeg((targetModelRendererArray[parPartIndex].rotateAngleX - currentRotationArray[parPartIndex][0]))+", remaining tween steps = "+
                    (stepsInTween - currentTweenStep));
        }
    }

    /*
     * Linear tween of the rotatePoints between current positions and target positions
     */
    protected void nextTweenPositions(MowzieModelRenderer[] parPassedInModelRendererArray, int parPartIndex)
    {
        parPassedInModelRendererArray[parPartIndex].rotationPointX =
                currentPositionArray[parPartIndex][0] + 
                (targetModelRendererArray[parPartIndex].rotationPointX - currentPositionArray[parPartIndex][0])
                / (stepsInTween - currentTweenStep);
        parPassedInModelRendererArray[parPartIndex].rotationPointY =
                currentPositionArray[parPartIndex][1] + 
                (targetModelRendererArray[parPartIndex].rotationPointY - currentPositionArray[parPartIndex][1])
                / (stepsInTween - currentTweenStep);
        parPassedInModelRendererArray[parPartIndex].rotationPointZ = 
                currentPositionArray[parPartIndex][2] + 
                (targetModelRendererArray[parPartIndex].rotationPointZ - currentPositionArray[parPartIndex][2])
                / (stepsInTween - currentTweenStep);
    }

    /*
     * Linear tween of the offsets between current offsets and target offsets
     */
    protected void nextTweenOffsets(MowzieModelRenderer[] parPassedInModelRendererArray, int partPartIndex)
    {
        parPassedInModelRendererArray[partPartIndex].offsetX =
                currentOffsetArray[partPartIndex][0] + 
                (targetModelRendererArray[partPartIndex].offsetX - currentOffsetArray[partPartIndex][0])
                / (stepsInTween - currentTweenStep);
        parPassedInModelRendererArray[partPartIndex].offsetY =
                currentOffsetArray[partPartIndex][1] + 
                (targetModelRendererArray[partPartIndex].offsetY - currentOffsetArray[partPartIndex][1])
                / (stepsInTween - currentTweenStep);
        parPassedInModelRendererArray[partPartIndex].offsetZ =
                currentOffsetArray[partPartIndex][2] + 
                (targetModelRendererArray[partPartIndex].offsetZ - currentOffsetArray[partPartIndex][2])
                / (stepsInTween - currentTweenStep);
    }
    
    protected void handleFinishedTween()
    {
        // DEBUG
        System.out.println("finished tween");

        // increment current sequence step
        currentSequenceStep++;
        // check if finished sequence
        if (currentSequenceStep >= animationSequence.length)
        {
            currentSequenceStep = 0;
        }
        // DEBUG
        System.out.println("Next pose is sequence step = "+currentSequenceStep);

        finishedTween = false;
        setNextTween();
    }

    protected void setNextTween()
    {
        targetModelIndex = animationSequence[currentSequenceStep][0];
        targetModelRendererArray = modelRendererArray[targetModelIndex];
        stepsInTween = animationSequence[currentSequenceStep][1];
        currentTweenStep = 0;
    }
       
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1)
    {
        Animator animator = parModel.animator;

        //        int frame = entity.ticksExisted;
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

    protected float degToRad(float degrees)
    {
        return (float) (Math.PI / 180.0F * degrees);
    }
    
    protected float radToDeg(float rads)
    {
        return (float) (180.0F / Math.PI * rads);
    }
    
    protected int partIndexFromName(String parName)
    {
        int theIndex = -1;
        for (int i = 0; i < partNameArray.length; i++)
        {
            if (partNameArray[i].toLowerCase().equals(parName.toLowerCase()))
            {
                theIndex = i;
            }
        }
        return theIndex;
    }
}