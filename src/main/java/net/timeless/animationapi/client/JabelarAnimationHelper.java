package net.timeless.animationapi.client;

import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityTherizinosaurus;
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
    protected String[] modelAssetPathArray;
    protected String[] partNameArray;
    protected int[][][] arrayOfSequences;
    
    protected MowzieModelRenderer[][] posesArray;
    protected MowzieModelRenderer[] passedInModelRendererArray;
    protected MowzieModelRenderer[] nextPose;

    public float[][] currentRotationArray ;
    public float[][] currentPositionArray ;
    public float[][] currentOffsetArray ;

    protected int numParts;
    protected int currentSequence; 
    public int numPosesInSequence;
    protected boolean randomSequence = false;
    protected int currentPose;
    public int numTicksInTween;
    public int currentTickInTween;
    public boolean finishedPose = false;
        
    public JabelarAnimationHelper(ModelDinosaur parModel, String[] parAssetPathArray, String[] parPartNameArray, int[][][] parArrayOfSequences, boolean parRandomInitialSequence, boolean parRandomSequence)
    {
        // transfer static animation info from constructor parameters to instance
        modelAssetPathArray = parAssetPathArray;
        partNameArray = parPartNameArray;
        arrayOfSequences = parArrayOfSequences;
        randomSequence = parRandomSequence;
        
        numParts = parPartNameArray.length;

        // initialize custom poseArray (first index is model, second is part within model)
        posesArray = new MowzieModelRenderer[modelAssetPathArray.length][numParts];
        for (int modelIndex = 0; modelIndex < modelAssetPathArray.length; modelIndex++)
        {
            posesArray[modelIndex] = new MowzieModelRenderer[numParts];
            for (int partIndex = 0; partIndex < numParts; partIndex++) 
            {
                // DEBUG
                System.out.println("attempting to load part "+partIndex+" from pose "+modelIndex);
                posesArray[modelIndex][partIndex] = getTabulaModel(modelAssetPathArray[modelIndex], 0).getCube(partNameArray[partIndex]);
            }
        }
        
        // initialize sequence index
        if (parRandomInitialSequence)
        {
            setRandomSequence();
        }
        else
        {
            currentSequence = 0;
        }
        numPosesInSequence = arrayOfSequences[currentSequence].length;

        // initialize next pose
        nextPose = posesArray[arrayOfSequences[currentSequence][currentPose][0]];
        
        // initialize tween index
        numTicksInTween = arrayOfSequences[currentSequence][currentPose][1];
        currentTickInTween = 0;
        finishedPose = false;

        // copy passed in model into a model renderer array
        // NOTE: this is the array you will actually animate
        passedInModelRendererArray = convertPassedInModelToModelRendererArray(parModel);

        // initialize arrays that are used to remember the current actual positions
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
        copyModelRendererArrayToCurrent(passedInModelRendererArray);

    }
    
    public void copyModelRendererArrayToCurrent(MowzieModelRenderer[] parModelRendererArray)
    {
        for (int i = 0; i < numParts; i++)
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
    
    public void performJabelarAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityTherizinosaurus parEntity)
    {
        convertPassedInModelToModelRendererArray(parModel);
        
        initIfNeeded(parEntity, passedInModelRendererArray);
        
        performNextTweenTick(parEntity, passedInModelRendererArray, true);
    }
    
    protected MowzieModelRenderer[] convertPassedInModelToModelRendererArray(ModelDinosaur parModel)
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
    
    protected void initIfNeeded(EntityDinosaur parEntity, MowzieModelRenderer[] passedInModelRendererArray)
    {
	    // initialize current pose arrays if first tick
	    if (parEntity.ticksExisted <= 10)
	    {
//	    	// DEBUG
//	    	System.out.println("Initializing current model array for new enitity with passed in value like "+passedInModelRendererArray[0].rotateAngleX);
	        copyModelRendererArrayToCurrent(passedInModelRendererArray);
	        setNextPose(parEntity);
	    }
    }

    protected void setNextPose(EntityDinosaur parEntity)
    {              
        nextPose = posesArray[arrayOfSequences[currentSequence][getCurrentPose()][0]];
        numTicksInTween = arrayOfSequences[currentSequence][getCurrentPose()][1];
        currentTickInTween = 0;
//        // DEBUG
//        System.out.println("set next tween for entity id = "+parEntity.getEntityId()+" steps in tween = "+currentAnimation.get(parEntity.getEntityId()).stepsInTween);
    }
   
    protected void performNextTweenTick(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, boolean parInertial)
    {
        // tween the passed in model towards target pose
        for (int i = 0; i < numParts; i++)
        {
            if (parInertial)
            {
                nextInertialTweenRotations(parEntity, parPassedInModelRendererArray, i);
                nextInertialTweenPositions(parEntity, parPassedInModelRendererArray, i);
                nextInertialTweenOffsets(parEntity, parPassedInModelRendererArray, i);  
            }
            else
            {
                nextTweenRotations(parEntity, parPassedInModelRendererArray, i);
                nextTweenPositions(parEntity, parPassedInModelRendererArray, i);
                nextTweenOffsets(parEntity, parPassedInModelRendererArray, i);  
            }
        }
        
        // update current position tracking arrays
        copyModelRendererArrayToCurrent(parPassedInModelRendererArray);
        
        incrementTweenTick(); 
               
        // check for end of animation and set next pose in sequence
        if (finishedPose) 
        {
            handleFinishedPose(parEntity);
        }

    }

    /*
     * Linear tween of the rotateAngles between current angles and target angles
     */
    protected void nextTweenRotations(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int parPartIndex)
    {  
        parPassedInModelRendererArray[parPartIndex].rotateAngleX =
                currentRotationArray[parPartIndex][0] + 
                (nextPose[parPartIndex].rotateAngleX - currentRotationArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotateAngleY =
                currentRotationArray[parPartIndex][1] + 
                (nextPose[parPartIndex].rotateAngleY - currentRotationArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotateAngleZ =
                currentRotationArray[parPartIndex][2] + 
                (nextPose[parPartIndex].rotateAngleZ - currentRotationArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Linear tween of the rotatePoints between current positions and target positions
     */
    protected void nextTweenPositions(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int parPartIndex)
    {
        parPassedInModelRendererArray[parPartIndex].rotationPointX =
                currentPositionArray[parPartIndex][0] + 
                (nextPose[parPartIndex].rotationPointX - currentPositionArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotationPointY =
                currentPositionArray[parPartIndex][1] + 
                (nextPose[parPartIndex].rotationPointY - currentPositionArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotationPointZ = 
                currentPositionArray[parPartIndex][2] + 
                (nextPose[parPartIndex].rotationPointZ - currentPositionArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Linear tween of the offsets between current offsets and target offsets
     */
    protected void nextTweenOffsets(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int partPartIndex)
    {
        parPassedInModelRendererArray[partPartIndex].offsetX =
                currentOffsetArray[partPartIndex][0] + 
                (nextPose[partPartIndex].offsetX - currentOffsetArray[partPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[partPartIndex].offsetY =
                currentOffsetArray[partPartIndex][1] + 
                (nextPose[partPartIndex].offsetY - currentOffsetArray[partPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[partPartIndex].offsetZ =
                currentOffsetArray[partPartIndex][2] + 
                (nextPose[partPartIndex].offsetZ - currentOffsetArray[partPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }
    /*
     * Inertial tween of the rotateAngles between current angles and target angles
     */
    protected void nextInertialTweenRotations(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int parPartIndex)
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
    
        parPassedInModelRendererArray[parPartIndex].rotateAngleX =
                currentRotationArray[parPartIndex][0] + inertialFactor *
                (nextPose[parPartIndex].rotateAngleX - currentRotationArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotateAngleY =
                currentRotationArray[parPartIndex][1] + inertialFactor *
                (nextPose[parPartIndex].rotateAngleY - currentRotationArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotateAngleZ =
                currentRotationArray[parPartIndex][2] + inertialFactor *
                (nextPose[parPartIndex].rotateAngleZ - currentRotationArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Inertial tween of the rotatePoints between current positions and target positions
     */
    protected void nextInertialTweenPositions(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int parPartIndex)
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
        
        parPassedInModelRendererArray[parPartIndex].rotationPointX =
                currentPositionArray[parPartIndex][0] + inertialFactor *
                (nextPose[parPartIndex].rotationPointX - currentPositionArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotationPointY =
                currentPositionArray[parPartIndex][1] + inertialFactor *
                (nextPose[parPartIndex].rotationPointY - currentPositionArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotationPointZ = 
                currentPositionArray[parPartIndex][2] + inertialFactor *
                (nextPose[parPartIndex].rotationPointZ - currentPositionArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Inertial tween of the offsets between current offsets and target offsets
     */
    protected void nextInertialTweenOffsets(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int partPartIndex)
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

        parPassedInModelRendererArray[partPartIndex].offsetX =
                currentOffsetArray[partPartIndex][0] + inertialFactor *
                (nextPose[partPartIndex].offsetX - currentOffsetArray[partPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[partPartIndex].offsetY =
                currentOffsetArray[partPartIndex][1] + inertialFactor *
                (nextPose[partPartIndex].offsetY - currentOffsetArray[partPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[partPartIndex].offsetZ =
                currentOffsetArray[partPartIndex][2] + inertialFactor *
                (nextPose[partPartIndex].offsetZ - currentOffsetArray[partPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }
    
    protected void handleFinishedPose(EntityDinosaur parEntity)
    {
        if(incrementCurrentPose()) // increments pose and returns true if finished sequence
        {
        	setNextSequence();
        }
        
//        // DEBUG
//        System.out.println("finished tween, next pose = "+currentPose);

        finishedPose = false;
        
        setNextPose(parEntity);
    }    
    
    // boolean returned indicates if tween was finished
    public boolean incrementTweenTick()
    {
        currentTickInTween++;
//      // DEBUG
//      System.out.println("current tween step = "+currentTweenStep);
        if (currentTickInTween >= numTicksInTween)
        {
            finishedPose = true;
        }
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

    protected void setNextSequence()
    {
    	if (randomSequence)
    	{
    		currentSequence = ((int) Math.floor(Math.random() * arrayOfSequences.length));
    	}
    	else
    	{
	    	currentSequence++;
	    	
	    	if (currentSequence >= arrayOfSequences.length)
	    	{
	    		currentSequence = 0;
	    	}
    	}
    	
    	numPosesInSequence = arrayOfSequences[currentSequence].length;
    	
    	// DEBUG
    	System.out.println("Next sequence = "+currentSequence);
    }
    
    // this getter method includes any sequence step modifier
    public int getCurrentPose()
    {
        return currentPose;
//        return (currentSequenceStep + currentSequenceStepModifier)%numStepsInSequence;
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

    public void setNumPosesInSequence(int parNumPosesInSequence)
    {
        // DEBUG
        System.out.println("Setting number of poses in sequence "+parNumPosesInSequence);
        numPosesInSequence = parNumPosesInSequence;
    }
    
    public void setRandomSequence()
    {
        currentPose = ((int)Math.floor(Math.random()*arrayOfSequences.length));
    }
}
