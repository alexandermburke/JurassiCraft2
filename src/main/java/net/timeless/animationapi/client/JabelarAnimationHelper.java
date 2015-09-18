package net.timeless.animationapi.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
@SideOnly(Side.CLIENT)
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

    private float[][] lastRotationArray ;
    private float[][] lastPositionArray ;
    private float[][] lastOffsetArray ;

    private static int numParts;
    private final int numSequencesInArray;
    private int currentSequence; 
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
            int[][][] parArrayOfSequences, boolean parShouldRandomizeSequence, 
            boolean parInertialTweens, float parInertiaFactor)
    {
        // transfer static animation info from constructor parameters to instance
        theEntity = parEntity;
        numParts = parNumParts;
        arrayOfPoses = parArrayOfPoses;
        arrayOfSequences = parArrayOfSequences;
        inertialTweens = parInertialTweens;
        baseInertiaFactor = parInertiaFactor;
         
        numSequencesInArray = arrayOfSequences.length;

        init(parModel);

        // DEBUG
        System.out.println("Finished JabelarAnimation constructor");
    }
    
    public void performJabelarAnimations(ModelDinosaur parModel)
    {
        passedInModelRendererArray = convertPassedInModelToModelRendererArray(parModel);
//        if (theEntity.getAnimID() != currentSequence)
//        {
//            setNextSequence(theEntity.getAnimID());
//        }
        performNextTweenTick();
    }
    
    private void init(ModelDinosaur parModel)
    {
        currentSequence = theEntity.getAnimID();
        initPose(); // sets the target pose based on sequence
        initTween();
        
        // copy passed in model into a model renderer array
        // NOTE: this is the array you will actually animate
        passedInModelRendererArray = convertPassedInModelToModelRendererArray(parModel);

        initCurrentPoseArrays();
        copyCurrentToLast();
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
        // filter out illegal values in array
        if (numTicksInTween < 1)
        {
        	System.err.println("Array of sequences has sequence with num ticks illegal value (< 1)");
        	numTicksInTween = 1;
        }
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
    
    private void copyCurrentToLast()
    {  
        lastRotationArray = currentRotationArray;
        lastPositionArray = currentPositionArray;
        lastOffsetArray = currentOffsetArray;
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
        copyCurrentToLast();
        nextPose = arrayOfPoses[arrayOfSequences[currentSequence][currentPose][0]];
        numTicksInTween = arrayOfSequences[currentSequence][currentPose][1];
        currentTickInTween = 0;

        // DEBUG
        if (currentSequence != AnimID.IDLE) System.out.println("current sequence for entity ID "+theEntity.getEntityId()+" is "+currentSequence+" out of "+arrayOfSequences.length+
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
            if (nextPose == null)
            {
                System.err.println("Trying to tween to a null next pose array");
            }
            else if (nextPose[partIndex] == null)
            {
                System.err.println("The part index "+partIndex+" in next pose is null");
            }
            else if (currentRotationArray == null)
            {
                System.err.println("Trying to tween from a null current rotation array");
            }
            else
            {
                nextTweenRotations(partIndex, inertiaFactor);
                nextTweenPositions(partIndex, inertiaFactor);
                nextTweenOffsets(partIndex, inertiaFactor);  
            }
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
//    private void nextTweenRotations(int parPartIndex, float inertiaFactor)
//    {   
//        passedInModelRendererArray[parPartIndex].rotateAngleX =
//                currentRotationArray[parPartIndex][0] + inertiaFactor *
//                (nextPose[parPartIndex].rotateAngleX - lastRotationArray[parPartIndex][0])
//                / numTicksInTween;
//        passedInModelRendererArray[parPartIndex].rotateAngleY =
//                currentRotationArray[parPartIndex][1] + inertiaFactor *
//                (nextPose[parPartIndex].rotateAngleY - lastRotationArray[parPartIndex][1])
//                / numTicksInTween;
//        passedInModelRendererArray[parPartIndex].rotateAngleZ =
//                currentRotationArray[parPartIndex][2] + inertiaFactor *
//                (nextPose[parPartIndex].rotateAngleZ - lastRotationArray[parPartIndex][2])
//                / numTicksInTween;
//    }

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
    private void nextTweenOffsets(int parPartIndex, float inertiaFactor)
    {        
        passedInModelRendererArray[parPartIndex].offsetX =
                currentOffsetArray[parPartIndex][0] + inertiaFactor *
                (nextPose[parPartIndex].offsetX - currentOffsetArray[parPartIndex][0])
                / ticksRemaining();
        passedInModelRendererArray[parPartIndex].offsetY =
                currentOffsetArray[parPartIndex][1] + inertiaFactor *
                (nextPose[parPartIndex].offsetY - currentOffsetArray[parPartIndex][1])
                / ticksRemaining();
        passedInModelRendererArray[parPartIndex].offsetZ =
                currentOffsetArray[parPartIndex][2] + inertiaFactor *
                (nextPose[parPartIndex].offsetZ - currentOffsetArray[parPartIndex][2])
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
    	
    	if (currentSequence != AnimID.IDLE && currentSequence == parSequenceIndex) // finished sequence but no new sequence set
    	{
    		// DEBUG
    		System.out.println("Reverting to idle sequence");
    		currentSequence = AnimID.IDLE;
    		theEntity.setAnimID(AnimID.IDLE);
    	}
    	else
    	{
    		currentSequence = parSequenceIndex;
    	}
    	
    	// handle case where animation sequence isn't available
    	if (arrayOfSequences[currentSequence] == null)
    	{
    		currentSequence = AnimID.IDLE;
            theEntity.setAnimID(AnimID.IDLE);
    	}
    	
        numPosesInSequence = arrayOfSequences[currentSequence].length;
        currentPose = 0;
        currentTickInTween = 0;     
    }
    
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
        // catch the exception so you can call method without further catching
        try
        {
            return new ModelDinosaur(TabulaModelHelper.parseModel(tabulaModel), null); // okay to use null for animator parameter as we get animator from passed-in model
        } catch (Exception e)
        {
            System.err.println("Could not load Tabula model = "+tabulaModel);
            e.printStackTrace();
        }
                
        return null;
    }

    public ModelDinosaur getTabulaModel(String tabulaModel)
    {
        return getTabulaModel(tabulaModel, 0);
    }
}