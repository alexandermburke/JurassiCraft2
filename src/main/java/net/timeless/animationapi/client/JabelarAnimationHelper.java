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
    
    private final int[][][] arrayOfSequences;
    
    private final MowzieModelRenderer[][] arrayOfPoses;
    private MowzieModelRenderer[] passedInModelRendererArray;
    private MowzieModelRenderer[] nextPose;

    private float[][] currentRotationArray ;
    private float[][] currentPositionArray ;
    private float[][] currentOffsetArray ;

    private static int numParts;
    private final int numSequencesInArray;
    private int currentSequence; 
    private boolean shouldRandomizeSequence = false;
    private int chanceNotIdle = 0;
    private int numPosesInSequence;
    private int currentPose;
    private int numTicksInTween;
    private int currentTickInTween;
    
    private final boolean inertialTweens;
    private final float baseInertiaFactor;
        
    public JabelarAnimationHelper(
    		EntityDinosaur parEntity, 
    		ModelDinosaur parModel, int parNumParts,
    		MowzieModelRenderer[][] parArrayOfPoses,
            int[][][] parArrayOfSequences, boolean parRandomInitialSequence, 
            boolean parShouldRandomizeSequence, int parChanceNotIdle, 
            boolean parInertialTweens, float parInertiaFactor)
    {
        // transfer static animation info from constructor parameters to instance
        theEntity = parEntity;
        numParts = parNumParts;
        arrayOfPoses = parArrayOfPoses;
        arrayOfSequences = parArrayOfSequences;
        shouldRandomizeSequence = parShouldRandomizeSequence;
        chanceNotIdle = parChanceNotIdle;
        inertialTweens = parInertialTweens;
        baseInertiaFactor = parInertiaFactor;
         
        numSequencesInArray = arrayOfSequences.length;

        init(parModel, parRandomInitialSequence);

        // DEBUG
        System.out.println("Finished JabelarAnimation constructor");
    }
    
    public void performJabelarAnimations(ModelDinosaur parModel)
    {
        passedInModelRendererArray = convertPassedInModelToModelRendererArray(parModel);
        if (theEntity.getAnimID() != currentSequence)
        {
            setNextSequence(theEntity.getAnimID());
        }
        performNextTweenTick();
    }
    
    private void init(ModelDinosaur parModel, boolean parRandomInitialSequence)
    {
        initSequence(parRandomInitialSequence);
        initPose(); // sets the target pose based on sequence
        initTween();
        
        // copy passed in model into a model renderer array
        // NOTE: this is the array you will actually animate
        passedInModelRendererArray = convertPassedInModelToModelRendererArray(parModel);

        initCurrentPoseArrays();
    }
    
    
    private void initSequence(boolean parRandomInitialSequence)
    {
        if (parRandomInitialSequence)
        {
            setRandomSequence();
        }
        else
        {
            currentSequence = theEntity.getAnimID();
        }
    }
    
    private void initPose()
    {
        numPosesInSequence = arrayOfSequences[currentSequence].length;

        // initialize first pose
        currentPose = 0;
        nextPose = arrayOfPoses[arrayOfSequences[currentSequence][currentPose][0]];
    }
    
    private void initTween()
    {
        numTicksInTween = arrayOfSequences[currentSequence][currentPose][1];
        currentTickInTween = 0;
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
        
        copyTweenToCurrent();
    }
    
    private void copyTweenToCurrent()
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
        String[] partNameArray = parModel.getCubeNamesArray();
        
        for (int i = 0; i < numParts; i++) 
        {
            modelRendererArray[i] = parModel.getCube(partNameArray[i]); 
        }
        
        return modelRendererArray;
    }
 
    private void setNextPose()
    {  
        nextPose = arrayOfPoses[arrayOfSequences[currentSequence][currentPose][0]];
        numTicksInTween = arrayOfSequences[currentSequence][currentPose][1];
        currentTickInTween = 0;
        // DEBUG
        System.out.println("current sequence "+currentSequence+" out of "+arrayOfSequences.length+
                " and current pose "+currentPose+" out of "+arrayOfSequences[currentSequence].length
                +" with "+numTicksInTween+" ticks in tween");
    }
   
    private void performNextTweenTick()
    {
        // update the passed in model
        tween();
        copyTweenToCurrent();

        if (incrementTweenTick()) // increments tween tick and returns true if finished pose 
        {
            handleFinishedPose();
        }
    }
    
    private void tween()
    {
        float inertiaFactor = calculateInertiaFactor();
        
        for (int partIndex = 0; partIndex < numParts; partIndex++)
        {
            nextTweenRotations(partIndex, inertiaFactor);
            nextTweenPositions(partIndex, inertiaFactor);
            nextTweenOffsets(partIndex, inertiaFactor);  
        }
    }
    
    private float calculateInertiaFactor()
    {
        float inertiaFactor = baseInertiaFactor;
        if (inertialTweens)
        {
            if ((currentTickInTween < (numTicksInTween * 0.25F)) && (currentTickInTween >= (numTicksInTween - numTicksInTween * 0.25F)))
            {
                inertiaFactor *= 0.5F;
            }
            else
            {
                inertiaFactor *= 1.5F;
            }
        }
        
        return inertiaFactor;
    }

    /*
     * Tween of the rotateAngles between current angles and target angles.
     * The tween is linear if inertialTweens = false
     */
    private void nextTweenRotations(int parPartIndex, float inertiaFactor)
    {        
        passedInModelRendererArray[parPartIndex].rotateAngleX =
                currentRotationArray[parPartIndex][0] + inertiaFactor *
                (nextPose[parPartIndex].rotateAngleX - currentRotationArray[parPartIndex][0])
                / ticksRemaining();
        passedInModelRendererArray[parPartIndex].rotateAngleY =
                currentRotationArray[parPartIndex][1] + inertiaFactor *
                (nextPose[parPartIndex].rotateAngleY - currentRotationArray[parPartIndex][1])
                / ticksRemaining();
        passedInModelRendererArray[parPartIndex].rotateAngleZ =
                currentRotationArray[parPartIndex][2] + inertiaFactor *
                (nextPose[parPartIndex].rotateAngleZ - currentRotationArray[parPartIndex][2])
                / ticksRemaining();
    }

    /*
     * Tween of the rotatePoints between current positions and target positions.
     * The tween is linear if inertialTweens is false.
     */
    private void nextTweenPositions(int parPartIndex, float inertiaFactor)
    {        
        passedInModelRendererArray[parPartIndex].rotationPointX =
                currentPositionArray[parPartIndex][0] + inertiaFactor *
                (nextPose[parPartIndex].rotationPointX - currentPositionArray[parPartIndex][0])
                / ticksRemaining();
        passedInModelRendererArray[parPartIndex].rotationPointY =
                currentPositionArray[parPartIndex][1] + inertiaFactor *
                (nextPose[parPartIndex].rotationPointY - currentPositionArray[parPartIndex][1])
                / ticksRemaining();
        passedInModelRendererArray[parPartIndex].rotationPointZ = 
                currentPositionArray[parPartIndex][2] + inertiaFactor *
                (nextPose[parPartIndex].rotationPointZ - currentPositionArray[parPartIndex][2])
                / ticksRemaining();
    }

    /*
     * Tween of the offsets between current offsets and target offsets.
     * The tween is linear if inertialTweens is false.
     */
    private void nextTweenOffsets(int partPartIndex, float inertiaFactor)
    {        
        passedInModelRendererArray[partPartIndex].offsetX =
                currentOffsetArray[partPartIndex][0] + inertiaFactor *
                (nextPose[partPartIndex].offsetX - currentOffsetArray[partPartIndex][0])
                / ticksRemaining();
        passedInModelRendererArray[partPartIndex].offsetY =
                currentOffsetArray[partPartIndex][1] + inertiaFactor *
                (nextPose[partPartIndex].offsetY - currentOffsetArray[partPartIndex][1])
                / ticksRemaining();
        passedInModelRendererArray[partPartIndex].offsetZ =
                currentOffsetArray[partPartIndex][2] + inertiaFactor *
                (nextPose[partPartIndex].offsetZ - currentOffsetArray[partPartIndex][2])
                / ticksRemaining();
    }
    
    private int ticksRemaining()
    {
        return (numTicksInTween - currentTickInTween);
    }
    
    private void handleFinishedPose()
    {
        if(incrementCurrentPose()) // increments pose and returns true if finished sequence
        {
        	setNextSequence(theEntity.getAnimID());
        }
        
        setNextPose();
    }    
    
    // boolean returned indicates if tween was finished
    public boolean incrementTweenTick()
    {
        currentTickInTween++;
        if (currentTickInTween >= numTicksInTween)
        {
            return true;
        }
//        // DEBUG
//        System.out.println("current tween step = "+currentTickInTween);
        return false;
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

    private void setNextSequence(int parSequenceIndex)
    {
        // TODO
        // Should control here which animations are interruptible, in which priority
        // I.e. could reject certain changes depending on what current animation is playing
    	
    	if (currentSequence == parSequenceIndex) // finished sequence but no new sequence set
    	{
    		currentSequence = AnimID.IDLE;
    	}
    	else
    	{
    		currentSequence = parSequenceIndex;
    	}
    	
    	// handle case where animation sequence isn't available
    	if (arrayOfSequences[currentSequence] == null)
    	{
    		currentSequence = AnimID.IDLE;
    	}
    	
        numPosesInSequence = arrayOfSequences[currentSequence].length;
        currentPose = 0;
        currentTickInTween = 0;
    }
    
    private void setNextSequence()
    {
    	if (shouldRandomizeSequence)
    	{
    		if (theEntity.getRNG().nextInt(100) < chanceNotIdle)
    		{
    		    setRandomSpecialSequence();
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
    }
 
    // this sets random sequence from any possible, including default idle sequence
    public void setRandomSequence()
    {
        currentSequence = theEntity.getRNG().nextInt(numSequencesInArray);
    }

    // this sets random sequence that cannot be the default idle sequence
    public void setRandomSpecialSequence()
    {
        currentSequence = theEntity.getRNG().nextInt(numSequencesInArray-1)+1;
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
}
