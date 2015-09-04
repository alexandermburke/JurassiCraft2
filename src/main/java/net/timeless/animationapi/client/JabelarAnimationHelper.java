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
    
    protected ModelDinosaur modelTarget;

    protected ModelDinosaur[] modelArray; 

    protected MowzieModelRenderer[][] modelRendererArray;
    protected MowzieModelRenderer[] passedInModelRendererArray;
    protected MowzieModelRenderer[] targetPose;

    public float[][] rotationArray ;
    public float[][] positionArray ;
    public float[][] offsetArray ;

    protected int numParts;
    protected int currentSequence; // which sequence
    protected int currentPose;
    public int numTweensInSequence;
    public int targetModelIndex; 
    public int numTicksInTween;
    public int currentTickInTween;
    public boolean finishedPose = false;
    protected boolean randomSequence = false;
    
    public JabelarAnimationHelper(ModelDinosaur parModel, String[] parAssetPathArray, String[] parPartNameArray, int[][][] parArrayOfSequences, boolean parRandomInitialSequence, boolean parRandomSequence)
    {
        // transfer static animation info to instance
        modelAssetPathArray = parAssetPathArray;
        partNameArray = parPartNameArray;
        arrayOfSequences = parArrayOfSequences;
        randomSequence = parRandomSequence;

        modelTarget = getTabulaModel(modelAssetPathArray[0], 0);
        
        numParts = parPartNameArray.length;
        currentSequence = 0;
        setNumTweensInSequence(arrayOfSequences[currentSequence].length);
        
        passedInModelRendererArray = new MowzieModelRenderer[numParts];
        convertPassedInModelToModelRendererArray(parModel);

        // initialize custom models array
        modelArray = new ModelDinosaur[modelAssetPathArray.length];
        for (int i = 0; i < modelAssetPathArray.length; i++)
        {
            modelArray[i] = getTabulaModel(modelAssetPathArray[i], 0);
        }

        // initialize custom model renderer arrays (first index is model, second is part within model)
        modelRendererArray = new MowzieModelRenderer[modelAssetPathArray.length][numParts];
        for (int i = 0; i < modelAssetPathArray.length; i++)
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
        
        rotationArray = new float[numParts][3];
        positionArray = new float[numParts][3];
        offsetArray = new float[numParts][3];
        for (int i = 0; i < numParts; i++)
        {
        	for (int j = 0; j < 3; j++)
        	{
        		rotationArray[i][j] = 0.0F;
        		positionArray[i][j] = 0.0F;
        		offsetArray[i][j] = 0.0F;
        	}
        }
        copyModelRendererArrayToCurrent(passedInModelRendererArray);
 
        if (parRandomInitialSequence)
        {
            setRandomSequenceStep();
        }
        else
        {
            currentPose = 0;
        }
        targetModelIndex = arrayOfSequences[currentSequence][currentPose][0]; 
        numTweensInSequence = arrayOfSequences[currentSequence].length;
        numTicksInTween = arrayOfSequences[currentSequence][currentPose][1];
        currentTickInTween = 0;
        finishedPose = false;

        targetPose = modelRendererArray[targetModelIndex];
    }
    
    public void copyModelRendererArrayToCurrent(MowzieModelRenderer[] parModelRendererArray)
    {
        for (int i = 0; i < numParts; i++)
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
    
    public void performJabelarAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityTherizinosaurus parEntity)
    {
        convertPassedInModelToModelRendererArray(parModel);
        
        initIfNeeded(parEntity, passedInModelRendererArray);
        
        performNextTweenTick(parEntity, passedInModelRendererArray, true);
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
    
    protected void initIfNeeded(EntityDinosaur parEntity, MowzieModelRenderer[] passedInModelRendererArray)
    {
	    // initialize current pose arrays if first tick
	    if (parEntity.ticksExisted <= 10)
	    {
	    	// DEBUG
	    	System.out.println("Initializing current model array for new enitity with passed in value like "+passedInModelRendererArray[0].rotateAngleX);
	        copyModelRendererArrayToCurrent(passedInModelRendererArray);
	        setNextPose(parEntity);
	    }
    }


    protected void setNextPose(EntityDinosaur parEntity)
    {              
        targetModelIndex = 
        		arrayOfSequences[currentSequence][getSequenceStep()][0];
        targetPose = modelRendererArray[targetModelIndex];
        numTicksInTween = arrayOfSequences[currentSequence][getSequenceStep()][1];
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
                rotationArray[parPartIndex][0] + 
                (targetPose[parPartIndex].rotateAngleX - rotationArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotateAngleY =
                rotationArray[parPartIndex][1] + 
                (targetPose[parPartIndex].rotateAngleY - rotationArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotateAngleZ =
                rotationArray[parPartIndex][2] + 
                (targetPose[parPartIndex].rotateAngleZ - rotationArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Linear tween of the rotatePoints between current positions and target positions
     */
    protected void nextTweenPositions(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int parPartIndex)
    {
        parPassedInModelRendererArray[parPartIndex].rotationPointX =
                positionArray[parPartIndex][0] + 
                (targetPose[parPartIndex].rotationPointX - positionArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotationPointY =
                positionArray[parPartIndex][1] + 
                (targetPose[parPartIndex].rotationPointY - positionArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotationPointZ = 
                positionArray[parPartIndex][2] + 
                (targetPose[parPartIndex].rotationPointZ - positionArray[parPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }

    /*
     * Linear tween of the offsets between current offsets and target offsets
     */
    protected void nextTweenOffsets(EntityDinosaur parEntity, MowzieModelRenderer[] parPassedInModelRendererArray, int partPartIndex)
    {
        parPassedInModelRendererArray[partPartIndex].offsetX =
                offsetArray[partPartIndex][0] + 
                (targetPose[partPartIndex].offsetX - offsetArray[partPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[partPartIndex].offsetY =
                offsetArray[partPartIndex][1] + 
                (targetPose[partPartIndex].offsetY - offsetArray[partPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[partPartIndex].offsetZ =
                offsetArray[partPartIndex][2] + 
                (targetPose[partPartIndex].offsetZ - offsetArray[partPartIndex][2])
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
                rotationArray[parPartIndex][0] + inertialFactor *
                (targetPose[parPartIndex].rotateAngleX - rotationArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotateAngleY =
                rotationArray[parPartIndex][1] + inertialFactor *
                (targetPose[parPartIndex].rotateAngleY - rotationArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotateAngleZ =
                rotationArray[parPartIndex][2] + inertialFactor *
                (targetPose[parPartIndex].rotateAngleZ - rotationArray[parPartIndex][2])
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
                positionArray[parPartIndex][0] + inertialFactor *
                (targetPose[parPartIndex].rotationPointX - positionArray[parPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotationPointY =
                positionArray[parPartIndex][1] + inertialFactor *
                (targetPose[parPartIndex].rotationPointY - positionArray[parPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[parPartIndex].rotationPointZ = 
                positionArray[parPartIndex][2] + inertialFactor *
                (targetPose[parPartIndex].rotationPointZ - positionArray[parPartIndex][2])
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
                offsetArray[partPartIndex][0] + inertialFactor *
                (targetPose[partPartIndex].offsetX - offsetArray[partPartIndex][0])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[partPartIndex].offsetY =
                offsetArray[partPartIndex][1] + inertialFactor *
                (targetPose[partPartIndex].offsetY - offsetArray[partPartIndex][1])
                / (numTicksInTween - currentTickInTween);
        parPassedInModelRendererArray[partPartIndex].offsetZ =
                offsetArray[partPartIndex][2] + inertialFactor *
                (targetPose[partPartIndex].offsetZ - offsetArray[partPartIndex][2])
                / (numTicksInTween - currentTickInTween);
    }
    
    protected void handleFinishedPose(EntityDinosaur parEntity)
    {
        if(incrementCurrentPose()) // increments pose and returns true if finished sequence
        {
        	setNextSequence();
        }
        
        // DEBUG
        System.out.println("finished tween, next pose = "+currentPose);

        finishedPose = false;
        
        setNextPose(parEntity);
    }

    protected void setNextSequence()
    {
    	currentSequence++;
    	
    	if (currentSequence >= arrayOfSequences.length)
    	{
    		currentSequence = 0;
    	}
    	
    	// DEBUG
    	System.out.println("Next sequence = "+currentSequence);
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

    /*
     * Chainable methods
     */
    public JabelarAnimationHelper setNumTweensInSequence(int parNumStepsInSequence)
    {
        // DEBUG
        System.out.println("Setting number of sequence steps to "+parNumStepsInSequence);
        numTweensInSequence = parNumStepsInSequence;
        return this;
    }
    
    public void setRandomSequenceStep()
    {
        currentPose = ((int)Math.floor(Math.random()*numTweensInSequence));
    }
    
    // boolean returned indicates if sequence was finished
    public boolean incrementCurrentPose()
    {       
        // increment current sequence step
        currentPose++;
        // check if finished sequence
        if (currentPose >= numTweensInSequence)
        {
            currentPose = 0;
            return true;
        }
//        // DEBUG
//        System.out.println("Next pose is sequence step = "+currentSequenceStep);
        return false;
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
    
    // this getter method includes any sequence step modifier
    public int getSequenceStep()
    {
        return currentPose;
//        return (currentSequenceStep + currentSequenceStepModifier)%numStepsInSequence;
    }
}
