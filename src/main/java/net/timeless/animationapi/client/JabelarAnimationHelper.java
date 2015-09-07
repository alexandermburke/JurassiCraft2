package net.timeless.animationapi.client;

import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.unilib.client.model.json.TabulaModelHelper;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;


/**
 * @author jabelar
 *
 * This class is used to hold per-entity animation variables for use with
 * Jabelar's animation tweening system.
 */
public class JabelarAnimationHelper
{
    private final EntityDinosaur theEntity;
    
    private final String[] modelAssetPathArray;
    private final String[] partNameArray;
    private final int[][][] arrayOfSequences;
    
    private MowzieModelRenderer[][] posesArray;
    private MowzieModelRenderer[] passedInModelRendererArray;
    private MowzieModelRenderer[] nextPose;

    private float[][] currentRotationArray ;
    private float[][] currentPositionArray ;
    private float[][] currentOffsetArray ;

    private final int numParts;
    private final int numSequencesInArray;
    private int currentSequence; 
    private boolean shouldRandomizeSequence = false;
    private int chanceNotIdle = 0;
    private int numPosesInSequence;
    private int currentPose;
    private int numTicksInTween;
    private int currentTickInTween;
    private boolean finishedPose = false;
        
    public JabelarAnimationHelper(EntityDinosaur parEntity, ModelDinosaur parModel, String[] parAssetPathArray, 
            String[] parPartNameArray, int[][][] parArrayOfSequences, boolean parRandomInitialSequence, 
            boolean parShouldRandomizeSequence, int parChanceNotIdle)
    {
        // transfer static animation info from constructor parameters to instance
        theEntity = parEntity;
        modelAssetPathArray = parAssetPathArray;
        partNameArray = parPartNameArray;
        arrayOfSequences = parArrayOfSequences;
        shouldRandomizeSequence = parShouldRandomizeSequence;
        chanceNotIdle = parChanceNotIdle;
         
        numParts = parPartNameArray.length;
        numSequencesInArray = arrayOfSequences.length;

        init(parModel, parRandomInitialSequence);
                
        // DEBUG
        System.out.println("Finished JabelarAnimation constructor");
    }
    
    public void performJabelarAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        passedInModelRendererArray = convertPassedInModelToModelRendererArray(parModel);
        initIfNeeded(parEntity);
        performNextTweenTick(true);
    }
    
    private void init(ModelDinosaur parModel, boolean parRandomInitialSequence)
    {
        initCustomPoseArray();
        initSequence(parRandomInitialSequence);
        initPose(); // sets the target pose based on sequence
        initTween();
        
        // copy passed in model into a model renderer array
        // NOTE: this is the array you will actually animate
        passedInModelRendererArray = convertPassedInModelToModelRendererArray(parModel);

        initCurrentPoseArrays();
    }
    
    // initialize custom poseArray (first index is model, second is part within model)
    private void initCustomPoseArray()
    {
        posesArray = new MowzieModelRenderer[modelAssetPathArray.length][numParts];
        for (int modelIndex = 0; modelIndex < modelAssetPathArray.length; modelIndex++)
        {
            posesArray[modelIndex] = new MowzieModelRenderer[numParts];
            for (int partIndex = 0; partIndex < numParts; partIndex++) 
            {
                posesArray[modelIndex][partIndex] = getTabulaModel(modelAssetPathArray[modelIndex], 0).getCube(partNameArray[partIndex]);
            }
        }
    }
    
    private void initSequence(boolean parRandomInitialSequence)
    {
        if (parRandomInitialSequence)
        {
            setRandomSequence();
        }
        else
        {
            currentSequence = 0;
        }
    }
    
    private void initPose()
    {
        numPosesInSequence = arrayOfSequences[currentSequence].length;

        // initialize first pose
        currentPose = 0;
        nextPose = posesArray[arrayOfSequences[currentSequence][currentPose][0]];
    }
    
    private void initTween()
    {
        numTicksInTween = arrayOfSequences[currentSequence][currentPose][1];
        currentTickInTween = 0;
        finishedPose = false;
    }
    
    private void initCurrentPoseArrays()
    {
        currentRotationArray = new float[numParts][3];
        currentPositionArray = new float[numParts][3];
        currentOffsetArray = new float[numParts][3];
        
        for (int partIndex = 0; partIndex < numParts; partIndex++)
        {
            for (int axis = 0; axis < 3; axis++) // X, Y, and Z
            {
                currentRotationArray[partIndex][axis] = 0.0F;
                currentPositionArray[partIndex][axis] = 0.0F;
                currentOffsetArray[partIndex][axis] = 0.0F;
            }
        }
        
        copyModelRendererArrayToCurrent();
    }
    
    private void copyModelRendererArrayToCurrent()
    {
        for (int i = 0; i < numParts; i++)
        {
            currentRotationArray[i][0] = passedInModelRendererArray[i].rotateAngleX;
            currentRotationArray[i][1] = passedInModelRendererArray[i].rotateAngleY;
            currentRotationArray[i][2] = passedInModelRendererArray[i].rotateAngleZ;
            currentPositionArray[i][0] = passedInModelRendererArray[i].rotationPointX;
            currentPositionArray[i][1] = passedInModelRendererArray[i].rotationPointY;
            currentPositionArray[i][2] = passedInModelRendererArray[i].rotationPointZ;
            currentOffsetArray[i][0] = passedInModelRendererArray[i].offsetX;
            currentOffsetArray[i][1] = passedInModelRendererArray[i].offsetY;
            currentOffsetArray[i][2] = passedInModelRendererArray[i].offsetZ;
        }           
    }
    
    private MowzieModelRenderer[] convertPassedInModelToModelRendererArray(ModelDinosaur parModel)
    {
        MowzieModelRenderer[] modelRendererArray = new MowzieModelRenderer[numParts];
        for (int i = 0; i < numParts; i++) 
        {
            modelRendererArray[i] = parModel.getCube(partNameArray[i]); 

            // DEBUG
            if (modelRendererArray[i] == null) 
            {
                System.out.println(partNameArray[i]+" parsed as null");
            }
        }
        return modelRendererArray;
    }
    
    private void initIfNeeded(EntityDinosaur parEntity)
    {
	    // initialize current pose arrays if first tick
	    if (parEntity.ticksExisted <= 10)
	    {
//	    	// DEBUG
//	    	System.out.println("Initializing current model array for new entity with passed in value like "+passedInModelRendererArray[0].rotateAngleX);
	        copyModelRendererArrayToCurrent();
	        setNextPose();
	    }
    }

    private void setNextPose()
    {  
        nextPose = posesArray[arrayOfSequences[currentSequence][currentPose][0]];
        numTicksInTween = arrayOfSequences[currentSequence][currentPose][1];
        currentTickInTween = 0;
        // DEBUG
        System.out.println("current sequence "+currentSequence+" out of "+arrayOfSequences.length+
                " and current pose "+currentPose+" out of "+arrayOfSequences[currentSequence].length
                +" with "+numTicksInTween+" ticks in tween");
    }
   
    private void performNextTweenTick(boolean parInertial)
    {
        // tween the passed in model towards target pose
        for (int i = 0; i < numParts; i++)
        {
            if (parInertial)
            {
                nextInertialTweenRotations(i);
                nextInertialTweenPositions(i);
                nextInertialTweenOffsets(i);  
            }
            else
            {
                nextTweenRotations(i);
                nextTweenPositions(i);
                nextTweenOffsets(i);  
            }
        }
        
        // update current position tracking arrays
        copyModelRendererArrayToCurrent();
        
        incrementTweenTick(); 
               
        // check for end of animation and set next pose in sequence
        if (finishedPose) 
        {
            handleFinishedPose();
        }

    }

    /*
     * Linear tween of the rotateAngles between current angles and target angles
     */
    private void nextTweenRotations(int parPartIndex)
    {  
        passedInModelRendererArray[parPartIndex].rotateAngleX =
                currentRotationArray[parPartIndex][0] + 
                (nextPose[parPartIndex].rotateAngleX - currentRotationArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[parPartIndex].rotateAngleY =
                currentRotationArray[parPartIndex][1] + 
                (nextPose[parPartIndex].rotateAngleY - currentRotationArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[parPartIndex].rotateAngleZ =
                currentRotationArray[parPartIndex][2] + 
                (nextPose[parPartIndex].rotateAngleZ - currentRotationArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Linear tween of the rotatePoints between current positions and target positions
     */
    private void nextTweenPositions(int parPartIndex)
    {
        passedInModelRendererArray[parPartIndex].rotationPointX =
                currentPositionArray[parPartIndex][0] + 
                (nextPose[parPartIndex].rotationPointX - currentPositionArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[parPartIndex].rotationPointY =
                currentPositionArray[parPartIndex][1] + 
                (nextPose[parPartIndex].rotationPointY - currentPositionArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[parPartIndex].rotationPointZ = 
                currentPositionArray[parPartIndex][2] + 
                (nextPose[parPartIndex].rotationPointZ - currentPositionArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Linear tween of the offsets between current offsets and target offsets
     */
    private void nextTweenOffsets(int partPartIndex)
    {
        passedInModelRendererArray[partPartIndex].offsetX =
                currentOffsetArray[partPartIndex][0] + 
                (nextPose[partPartIndex].offsetX - currentOffsetArray[partPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[partPartIndex].offsetY =
                currentOffsetArray[partPartIndex][1] + 
                
                (nextPose[partPartIndex].offsetY - currentOffsetArray[partPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[partPartIndex].offsetZ =
                currentOffsetArray[partPartIndex][2] + 
                (nextPose[partPartIndex].offsetZ - currentOffsetArray[partPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }
    /*
     * Inertial tween of the rotateAngles between current angles and target angles
     */
    private void nextInertialTweenRotations(int parPartIndex)
    {      
        float inertialFactor = 1.0F;
        if ((currentTickInTween < (numTicksInTween * 0.25F)) && (currentTickInTween >= (numTicksInTween - numTicksInTween * 0.25F)))
        {
            inertialFactor = 0.5F;
        }
        else
        {
            inertialFactor = 1.5F;
        }
    
        passedInModelRendererArray[parPartIndex].rotateAngleX =
                currentRotationArray[parPartIndex][0] + inertialFactor *
                (nextPose[parPartIndex].rotateAngleX - currentRotationArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[parPartIndex].rotateAngleY =
                currentRotationArray[parPartIndex][1] + inertialFactor *
                (nextPose[parPartIndex].rotateAngleY - currentRotationArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[parPartIndex].rotateAngleZ =
                currentRotationArray[parPartIndex][2] + inertialFactor *
                (nextPose[parPartIndex].rotateAngleZ - currentRotationArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Inertial tween of the rotatePoints between current positions and target positions
     */
    private void nextInertialTweenPositions(int parPartIndex)
    {
        
        float inertialFactor = 1.0F;
        if ((currentTickInTween < (numTicksInTween * 0.25F)) && (currentTickInTween >= (numTicksInTween - numTicksInTween * 0.25F)))
        {
            inertialFactor = 0.5F;
        }
        else
        {
            inertialFactor = 1.5F;
        }
        
        passedInModelRendererArray[parPartIndex].rotationPointX =
                currentPositionArray[parPartIndex][0] + inertialFactor *
                (nextPose[parPartIndex].rotationPointX - currentPositionArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[parPartIndex].rotationPointY =
                currentPositionArray[parPartIndex][1] + inertialFactor *
                (nextPose[parPartIndex].rotationPointY - currentPositionArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[parPartIndex].rotationPointZ = 
                currentPositionArray[parPartIndex][2] + inertialFactor *
                (nextPose[parPartIndex].rotationPointZ - currentPositionArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Inertial tween of the offsets between current offsets and target offsets
     */
    private void nextInertialTweenOffsets(int partPartIndex)
    {
        
        float inertialFactor = 1.0F;
        if ((currentTickInTween < (numTicksInTween * 0.25F)) && (currentTickInTween >= (numTicksInTween - numTicksInTween * 0.25F)))
        {
            inertialFactor = 0.5F;
        }
        else
        {
            inertialFactor = 1.5F;
        }

        passedInModelRendererArray[partPartIndex].offsetX =
                currentOffsetArray[partPartIndex][0] + inertialFactor *
                (nextPose[partPartIndex].offsetX - currentOffsetArray[partPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[partPartIndex].offsetY =
                currentOffsetArray[partPartIndex][1] + inertialFactor *
                (nextPose[partPartIndex].offsetY - currentOffsetArray[partPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        passedInModelRendererArray[partPartIndex].offsetZ =
                currentOffsetArray[partPartIndex][2] + inertialFactor *
                (nextPose[partPartIndex].offsetZ - currentOffsetArray[partPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }
    
    private void handleFinishedPose()
    {
        if(incrementCurrentPose()) // increments pose and returns true if finished sequence
        {
        	setNextSequence();
        }

        finishedPose = false;
        
        setNextPose();
    }    
    
    // boolean returned indicates if tween was finished
    public boolean incrementTweenTick()
    {
        currentTickInTween++;
        if (currentTickInTween >= numTicksInTween)
        {
            finishedPose = true;
        }
        // DEBUG
        System.out.println("current tween step = "+currentTickInTween);
        return finishedPose;
    }
    
    // boolean returned indicates if sequence was finished
    public boolean incrementCurrentPose()
    {       
        // increment current sequence step
        currentPose++;
        // check if finished sequence
        if (currentPose >= numPosesInSequence)
        {
            currentPose = 0;
            return true;
        }
//        // DEBUG
//        System.out.println("Next pose is sequence step = "+currentSequenceStep);
        return false;
    }

    private void setNextSequence()
    {
    	if (shouldRandomizeSequence)
    	{
    		if (theEntity.getRNG().nextInt(100) < chanceNotIdle)
    		{
    		    setRandomSequence();
    		}
    		else
    		{
    		   currentSequence = 0;
    		}
    	}
    	else
    	{
	    	currentSequence++;
	    	
	    	if (currentSequence >= numSequencesInArray)
	    	{
	    		currentSequence = 0;
	    	}
    	}
    	
    	numPosesInSequence = arrayOfSequences[currentSequence].length;
    	
//    	// DEBUG
//    	System.out.println("Next sequence = "+currentSequence);
    }
    
    // this getter method includes any sequence step modifier
    public int getCurrentPose()
    {
        return currentPose;
//        return (currentSequenceStep + currentSequenceStepModifier)%numStepsInSequence;
    }
    
    public void setRandomSequence()
    {
        currentSequence = theEntity.getRNG().nextInt(numSequencesInArray);
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

    /*
     * Utility functions
     */
    private float degToRad(float degrees)
    {
        return (float) (Math.PI / 180.0F * degrees);
    }
    
    private float radToDeg(float rads)
    {
        return (float) (180.0F / Math.PI * rads);
    }

    /*
     * useful when debugging errors in the name array
     */
    private int partIndexFromName(String parName)
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
