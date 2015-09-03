package net.timeless.jurassicraft.client.model.animation;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.CurrentAnimation;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.dinosaur.DinosaurTherizinosaurus;
import net.timeless.jurassicraft.common.entity.EntityTherizinosaurus;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
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
            "/assets/jurassicraft/models/entities/therizinosaurus/therizinosaurus_pose1",
            "/assets/jurassicraft/models/entities/therizinosaurus/therizinosaurus_pose2",
            "/assets/jurassicraft/models/entities/therizinosaurus/therizinosaurus_pose3",
            "/assets/jurassicraft/models/entities/therizinosaurus/therizinosaurus_pose4",
            "/assets/jurassicraft/models/entities/therizinosaurus/therizinosaurus_pose5",
            "/assets/jurassicraft/models/entities/therizinosaurus/therizinosaurus_pose6",
            "/assets/jurassicraft/models/entities/therizinosaurus/therizinosaurus_pose7",
            "/assets/jurassicraft/models/entities/therizinosaurus/therizinosaurus_pose8",
            "/assets/jurassicraft/models/entities/therizinosaurus/therizinosaurus_pose9"
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
        {0, 500}, {1, 100}, {1, 80}, {0, 100}, // look left
        {0, 200}, {4, 100}, {4, 40}, {0, 100}, // look right
        {0, 500}, {2, 100}, {2, 20}, {0, 50}, // head bob
        {0, 580}, {9, 40}, {0, 20}, {9, 20}, {0, 20}, {9, 20}, {0, 20}, // flapping "dance"
        {9, 20}, {0, 20}, {9, 20}, {0, 20}, {9, 20}, {0, 20}, {9, 20}, {0, 20}, 
        {9, 20}, {0, 20}, {9, 20}, {0, 20}, {9, 20}, {0, 20}, {9, 20}, {0, 100},    
        {0, 500}, {2, 100}, {2, 20}, {0, 50}, // head bob
        };
            
    /*
     * Do NOT change any of the following field initializations
     */

    protected static int numParts = partNameArray.length;

    // maps each entity with its current animation 
    protected HashMap<Integer, CurrentAnimation> currentAnimation = new HashMap<Integer, CurrentAnimation>();
    
    // maps each entity with its current pose model renderer array rotations, positions, and offsets
    protected HashMap<Integer, CurrentPoseArray> currentPose = new HashMap<Integer, CurrentPoseArray>();

    class CurrentPoseArray
    {
        protected float[][] rotationArray ;
        protected float[][] positionArray ;
        protected float[][] offsetArray ;
        
        public CurrentPoseArray()
        {
            rotationArray = new float[numParts][3];
            positionArray = new float[numParts][3];
            offsetArray = new float[numParts][3];
        }

        public void copyModelRendererArrayToCurrent(MowzieModelRenderer[] parModelRendererArray)
        {
            for (int i = 0; i < parModelRendererArray.length; i++)
            {
                rotationArray[i][0] = parModelRendererArray[i].rotateAngleX;
                rotationArray[i][1] = parModelRendererArray[i].rotateAngleY;
                rotationArray[i][2] = parModelRendererArray[i].rotateAngleZ;
                positionArray[i][0] = parModelRendererArray[i].rotationPointX;
                positionArray[i][1] = parModelRendererArray[i].rotationPointY;
                positionArray[i][2] = parModelRendererArray[i].rotationPointZ;
                offsetArray[i][0] = parModelRendererArray[i].offsetX;
                offsetArray[i][1] = parModelRendererArray[i].offsetY;
                offsetArray[i][2] = parModelRendererArray[i].offsetZ;
            }           
        }
    }
    
    protected HashMap<Integer, MowzieModelRenderer[]> targetPose = new HashMap<Integer, MowzieModelRenderer[]>();

    protected CurrentAnimation theAnimation;
    protected CurrentPoseArray theCurrentPose;
    protected MowzieModelRenderer[] theTargetPose;

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
//    protected MowzieModelRenderer[] targetModelRendererArray = new MowzieModelRenderer[numParts];

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
        updateCurrentAnimationIfNewEntity(parEntity);

        theAnimation = currentAnimation.get(parEntity.getEntityId());
        theCurrentPose = currentPose.get(parEntity.getEntityId());
        theTargetPose = targetPose.get(parEntity.getEntityId());

        performJabelarAnimations(parModel, f, f1, rotation, rotationYaw, rotationPitch, partialTicks, parEntity);

        // you can still add chain, walk, bob, etc.
        performMowzieAnimations(parModel, f, f1, rotation, rotationYaw, rotationPitch, partialTicks, parEntity);
    }
    
    protected void updateCurrentAnimationIfNewEntity(EntityDinosaur parEntity)
    {
        // add entry to hashmap if new entity
        if (!currentAnimation.containsKey(parEntity.getEntityId()))
        {
            // DEBUG
            System.out.println("Adding entity to hashmap with id = "+parEntity.getEntityId());
            currentAnimation.put(parEntity.getEntityId(), (new CurrentAnimation()).setNumStepsInSequence(animationSequence.length));
            currentPose.put(parEntity.getEntityId(), new CurrentPoseArray());
            targetPose.put(parEntity.getEntityId(), passedInModelRendererArray);
        }
    }
    
    /*
     * You should not need to modify anything below this point
     */

    protected void performJabelarAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityTherizinosaurus parEntity)
    {
        convertPassedInModelToModelRendererArray(parModel);
        
        // initialize current pose arrays if first tick
        if (parEntity.ticksExisted <= 10)
        {
            theCurrentPose.copyModelRendererArrayToCurrent(passedInModelRendererArray);
            setNextTween(parEntity);
        }
        
        performNextTweenStep(parEntity, passedInModelRendererArray);
        
        // check for end of animation and set next target in sequence
        if (theAnimation.finishedTween) 
        {
            handleFinishedTween(parEntity);
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

    protected void setNextTween(EntityDinosaur parEntity)
    {              
        theAnimation.targetModelIndex = 
                animationSequence[theAnimation.getSequenceStep()][0];
        targetPose.put(parEntity.getEntityId(), modelRendererArray[theAnimation.targetModelIndex]);
        theAnimation.stepsInTween = animationSequence[theAnimation.getSequenceStep()][1];
        theAnimation.currentTweenStep = 0;
//        // DEBUG
//        System.out.println("set next tween for entity id = "+parEntity.getEntityId()+" steps in tween = "+currentAnimation.get(parEntity.getEntityId()).stepsInTween);
    }
   
    protected void performNextTweenStep(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray)
    {
        // tween the passed in model towards target pose
        for (int i = 0; i < numParts; i++)
        {
            nextTweenRotations(parEntity, parPassedInModelRendererArray, i);
            nextTweenPositions(parEntity, parPassedInModelRendererArray, i);
            nextTweenOffsets(parEntity, parPassedInModelRendererArray, i);
            // DEBUG
            if (i == this.partIndexFromName("neck 4"))
            {
                System.out.println("Entity ID "+parEntity.getEntityId()+" has neck 4 (part index = "+i+" rotateAngleY = "+parPassedInModelRendererArray[i].rotateAngleY);
            }
       
        }
        
        // update current position tracking arrays
        theCurrentPose.copyModelRendererArrayToCurrent(parPassedInModelRendererArray);
        
        theAnimation.incrementTweenStep();        
    }

    /*
     * Linear tween of the rotateAngles between current angles and target angles
     */
    protected void nextTweenRotations(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int parPartIndex)
    {
        // DEBUG
        if (theCurrentPose == null)
        {
            System.out.println("it's null for some reason");
        }
        if ((theAnimation.stepsInTween - theAnimation.currentTweenStep) <= 0.0F)
        {
            System.out.println("Divde by zero!");
        }
        System.out.println("theCP.rotationArray = "+theCurrentPose.rotationArray[parPartIndex][0]+" theTP.rotateAngleX = "+theTargetPose[parPartIndex].rotateAngleX);
        parPassedInModelRendererArray[parPartIndex].rotateAngleX =
                theCurrentPose.rotationArray[parPartIndex][0] + 
                (theTargetPose[parPartIndex].rotateAngleX - theCurrentPose.rotationArray[parPartIndex][0])
                / (theAnimation.stepsInTween - theAnimation.currentTweenStep);
        parPassedInModelRendererArray[parPartIndex].rotateAngleY =
                theCurrentPose.rotationArray[parPartIndex][1] + 
                (theTargetPose[parPartIndex].rotateAngleY - theCurrentPose.rotationArray[parPartIndex][1])
                / (theAnimation.stepsInTween - theAnimation.currentTweenStep);
        parPassedInModelRendererArray[parPartIndex].rotateAngleZ =
                theCurrentPose.rotationArray[parPartIndex][2] + 
                (theTargetPose[parPartIndex].rotateAngleZ - theCurrentPose.rotationArray[parPartIndex][2])
                / (theAnimation.stepsInTween - theAnimation.currentTweenStep);
    }

    /*
     * Linear tween of the rotatePoints between current positions and target positions
     */
    protected void nextTweenPositions(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int parPartIndex)
    {
        parPassedInModelRendererArray[parPartIndex].rotationPointX =
                theCurrentPose.positionArray[parPartIndex][0] + 
                (theTargetPose[parPartIndex].rotationPointX - theCurrentPose.positionArray[parPartIndex][0])
                / (theAnimation.stepsInTween - theAnimation.currentTweenStep);
        parPassedInModelRendererArray[parPartIndex].rotationPointY =
                theCurrentPose.positionArray[parPartIndex][1] + 
                (theTargetPose[parPartIndex].rotationPointY - theCurrentPose.positionArray[parPartIndex][1])
                / (theAnimation.stepsInTween - theAnimation.currentTweenStep);
        parPassedInModelRendererArray[parPartIndex].rotationPointZ = 
                theCurrentPose.positionArray[parPartIndex][2] + 
                (theTargetPose[parPartIndex].rotationPointZ - theCurrentPose.positionArray[parPartIndex][2])
                / (theAnimation.stepsInTween - theAnimation.currentTweenStep);
    }

    /*
     * Linear tween of the offsets between current offsets and target offsets
     */
    protected void nextTweenOffsets(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int partPartIndex)
    {
        parPassedInModelRendererArray[partPartIndex].offsetX =
                theCurrentPose.offsetArray[partPartIndex][0] + 
                (theTargetPose[partPartIndex].offsetX - theCurrentPose.offsetArray[partPartIndex][0])
                / (theAnimation.stepsInTween - theAnimation.currentTweenStep);
        parPassedInModelRendererArray[partPartIndex].offsetY =
                currentPose.get(parEntity.getEntityId()).offsetArray[partPartIndex][1] + 
                (targetPose.get(parEntity.getEntityId())[partPartIndex].offsetY - currentPose.get(parEntity.getEntityId()).offsetArray[partPartIndex][1])
                / (theAnimation.stepsInTween - theAnimation.currentTweenStep);
        parPassedInModelRendererArray[partPartIndex].offsetZ =
                currentPose.get(parEntity.getEntityId()).offsetArray[partPartIndex][2] + 
                (targetPose.get(parEntity.getEntityId())[partPartIndex].offsetZ - currentPose.get(parEntity.getEntityId()).offsetArray[partPartIndex][2])
                / (theAnimation.stepsInTween - theAnimation.currentTweenStep);
    }
    
    protected void handleFinishedTween(EntityDinosaur parEntity)
    {
//        // DEBUG
//        System.out.println("finished tween");
        
        theAnimation.incrementSequenceStep();

        theAnimation.finishedTween = false;
        setNextTween(parEntity);
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
    
 protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityTherizinosaurus parEntity)
 {
     Animator animator = parModel.animator;

     MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");
     MowzieModelRenderer bodyhips = parModel.getCube("Body hips");
     MowzieModelRenderer rightCalf1 = parModel.getCube("Right Calf 1");
     MowzieModelRenderer rightCalf2 = parModel.getCube("Right Calf 2");
     MowzieModelRenderer footRight = parModel.getCube("Foot Right");
     MowzieModelRenderer tail1 = parModel.getCube("Tail 1");
     MowzieModelRenderer bodyMain = parModel.getCube("Body main");
     MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
     MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
     MowzieModelRenderer tail2Feathers = parModel.getCube("Tail 2 feathers");
     MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
     MowzieModelRenderer tail3Feathers = parModel.getCube("Tail 3 feathers");
     MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
     MowzieModelRenderer tail4Feathers = parModel.getCube("Tail 4 feathers");
     MowzieModelRenderer tail6 = parModel.getCube("Tail 6");
     MowzieModelRenderer tail5Feathers = parModel.getCube("Tail 5 feathers");
     MowzieModelRenderer tail6Feathers = parModel.getCube("Tail 6 feathers");
     MowzieModelRenderer tail6FeathersR = parModel.getCube("Tail 6 feathers r");
     MowzieModelRenderer tail6FeathersL = parModel.getCube("Tail 6 feathers l");
     MowzieModelRenderer tail5FeathersR = parModel.getCube("Tail 5 feathers r");
     MowzieModelRenderer tail5FeathersL = parModel.getCube("Tail 5 feathers l");
     MowzieModelRenderer tail4FeathersR = parModel.getCube("Tail 4 feathers r");
     MowzieModelRenderer tail4FeathersL = parModel.getCube("Tail 4 feathers l");
     MowzieModelRenderer tail3FeathersR = parModel.getCube("Tail 3 feathers r");
     MowzieModelRenderer tail3FeathersL = parModel.getCube("Tail 3 feathers l");
     MowzieModelRenderer bodyShoulders = parModel.getCube("Body shoulders");
     MowzieModelRenderer bodyMain1 = parModel.getCube("Body main 1");
     MowzieModelRenderer neckBase = parModel.getCube("Neck base");
     MowzieModelRenderer neck1 = parModel.getCube("Neck 1");
     MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
     MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
     MowzieModelRenderer neck4 = parModel.getCube("Neck 4");
     MowzieModelRenderer neck5 = parModel.getCube("Neck 5");
     MowzieModelRenderer neck4feathers = parModel.getCube("Neck 4 feathers");
     MowzieModelRenderer neck6 = parModel.getCube("Neck 6");
     MowzieModelRenderer neck5Feathers = parModel.getCube("Neck 5 feathers");
     MowzieModelRenderer neck7 = parModel.getCube("Neck 7");
     MowzieModelRenderer neck6Feathers = parModel.getCube("Neck 6 feathers");
     MowzieModelRenderer head = parModel.getCube("Head");
     MowzieModelRenderer neck7Feathers = parModel.getCube("Neck 7 feathers");
     MowzieModelRenderer snout = parModel.getCube("Snout");
     MowzieModelRenderer lowerJaw = parModel.getCube("Lower Jaw");
     MowzieModelRenderer snoutRoof = parModel.getCube("Snout roof");
     MowzieModelRenderer upperJaw = parModel.getCube("Upper Jaw");
     MowzieModelRenderer neck7FeathersR = parModel.getCube("Neck 7 feathers r");
     MowzieModelRenderer neck7FeathersL = parModel.getCube("Neck 7 feathers l");
     MowzieModelRenderer neck6FeathersR = parModel.getCube("Neck 6 feathers r");
     MowzieModelRenderer neck6FeathersL = parModel.getCube("Neck 6 feathers l");
     MowzieModelRenderer neck5FeathersR = parModel.getCube("Neck 5 feathers r");
     MowzieModelRenderer neck5FeathersL = parModel.getCube("Neck 5 feathers l");
     MowzieModelRenderer neck4FeathersR = parModel.getCube("Neck 4 feathers r");
     MowzieModelRenderer neck4FeathersL = parModel.getCube("Neck 4 feathers l");
     MowzieModelRenderer lowerArmRight = parModel.getCube("Lower Arm Right");
     MowzieModelRenderer lowerArmRight1 = parModel.getCube("Lower Arm Right 1");
     MowzieModelRenderer rightHand = parModel.getCube("Right hand");
     MowzieModelRenderer armRightFeathers = parModel.getCube("Arm right feathers");
     MowzieModelRenderer rightFinger1 = parModel.getCube("Right finger 1");
     MowzieModelRenderer rightFinger2 = parModel.getCube("Right finger 2");
     MowzieModelRenderer righFinger3 = parModel.getCube("Right finger 3");
     MowzieModelRenderer rF1mid = parModel.getCube("RF1 mid");
     MowzieModelRenderer rF1end = parModel.getCube("RF1 end");
     MowzieModelRenderer rF2mid = parModel.getCube("RF2 mid");
     MowzieModelRenderer rF2end = parModel.getCube("RF2 end");
     MowzieModelRenderer rF3mid = parModel.getCube("RF3 mid");
     MowzieModelRenderer rF3end = parModel.getCube("RF3 end");
     MowzieModelRenderer lowerArmLeft = parModel.getCube("Lower Arm LEFT");
     MowzieModelRenderer lowerArmLeft1 = parModel.getCube("Lower Arm LEFT 1");
     MowzieModelRenderer leftHand = parModel.getCube("Left hand");
     MowzieModelRenderer armLeftFeathers = parModel.getCube("Arm left feathers");
     MowzieModelRenderer leftfinger1 = parModel.getCube("Left finger 1");
     MowzieModelRenderer leftfinger2 = parModel.getCube("Left finger 2");
     MowzieModelRenderer leftfinger3 = parModel.getCube("Left finger 3");
     MowzieModelRenderer lF1mid = parModel.getCube("LF1 mid");
     MowzieModelRenderer lF1end = parModel.getCube("LF1 end");
     MowzieModelRenderer lF2mid = parModel.getCube("LF1 end");
     MowzieModelRenderer lF2end = parModel.getCube("LF2 end");
     MowzieModelRenderer lF3mid = parModel.getCube("LF3 mid");
     MowzieModelRenderer lF3end = parModel.getCube("LF3 mid");
     MowzieModelRenderer leftThigh = parModel.getCube("Left Thigh");
     MowzieModelRenderer leftCalf1 = parModel.getCube("Left Calf 1");
     MowzieModelRenderer leftCalf2 = parModel.getCube("Left Calf 2");
     MowzieModelRenderer footLeft = parModel.getCube("Foot Left");

     MowzieModelRenderer[] neck = new MowzieModelRenderer[] { head, neck7, neck6, neck5, neck4, neck3, neck2, neck1, neckBase, bodyShoulders };
     MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail1, tail2, tail3, tail4, tail5, tail6 };
     MowzieModelRenderer[] armLeft = new MowzieModelRenderer[] { lowerArmLeft, lowerArmLeft1, leftHand};
     MowzieModelRenderer[] armRight = new MowzieModelRenderer[] { lowerArmRight, lowerArmRight1, rightHand };

     int frame = parEntity.ticksExisted;

     float globalSpeed = 0.05F;
     float globalDegree = 0.06F;
//     float globalHeight = 2F;
//     float frontOffset = -1.35f;

     //The tail must always be up when the neck is down
     float speed = 0.75F;
     float height = 2F * f1;

     parModel.bob(bodyhips, 1F * speed, height, false, f, f1);
     parModel.bob(leftThigh, 1F * speed, height, false, f, f1);
     parModel.bob(rightThigh, 1F * speed, height, false, f, f1);
     parModel.walk(bodyShoulders, 1F * speed, 0.2F, true, 1, 0, f, f1);
     parModel.walk(bodyMain1, 1F * speed, 0.2F, false, 0.5F, 0, f, f1);

     parModel.walk(leftThigh, 0.5F * speed, 0.7F, false, 3.14F, 0.2F, f, f1);
     parModel.walk(leftCalf1, 0.5F * speed, 0.6F, false, 1.5F, 0.3F, f, f1);
     parModel.walk(leftCalf2, 0.5F * speed, 0.8F, false, -1F, -0.1F, f, f1);
     parModel.walk(footLeft, 0.5F * speed, 1.5F, true, -1F, 1F, f, f1);

     parModel.walk(rightThigh, 0.5F * speed, 0.7F, true, 3.14F, 0.2F, f, f1);
     parModel.walk(rightCalf1, 0.5F * speed, 0.6F, true, 1.5F, 0.3F, f, f1);
     parModel.walk(rightCalf2, 0.5F * speed, 0.8F, true, -1F, -0.1F, f, f1);
     parModel.walk(footRight, 0.5F * speed, 1.5F, false, -1F, 1F, f, f1);

     parModel.chainSwing(tail, 0.5F * speed, -0.1F, 2, f, f1);
     parModel.chainWave(tail, 1F * speed, -0.1F, 2.5F, f, f1);
//     parModel.chainWave(bodyParts, 1F * speed, -0.1F, 4, f, f1);

     parModel.chainWave(armRight, 1F * speed, -0.3F, 4, f, f1);
     parModel.chainWave(armLeft, 1F * speed, -0.3F, 4, f, f1);

     // Idling
     parModel.chainWave(tail, 0.1F, 0.05F, 2, frame, 1F);
//     parModel.chainWave(bodyParts, 0.1F, -0.03F, 5, frame, 1F);
     parModel.chainWave(armRight, 0.1F, -0.1F, 4, frame, 1F);
     parModel.chainWave(armLeft, 0.1F, -0.1F, 4, frame, 1F);
     
     parEntity.tailBuffer.applyChainSwingBuffer(tail);

     }
}