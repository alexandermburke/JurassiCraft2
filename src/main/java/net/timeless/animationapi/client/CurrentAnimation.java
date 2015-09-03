package net.timeless.animationapi.client;

import net.timeless.unilib.client.model.tools.MowzieModelRenderer;


/**
 * @author jabelar
 *
 * This class is used to hold per-entity animation variables for use with
 * Jabelar's animation tweening system.
 */
public class CurrentAnimation
{
    protected String[] modelAssetPathArray;
    protected String[] partNameArray;
    protected int[][] animationSequenceArray;
    
    public float[][] rotationArray ;
    public float[][] positionArray ;
    public float[][] offsetArray ;

    protected int numParts;
    protected int currentSequenceStep;
    protected int currentSequenceStepModifier; // this is used to desync entities of same type
    public int numStepsInSequence;
    public int targetModelIndex; 
    public int stepsInTween;
    public int currentTweenStep;
    public boolean finishedTween = false;
    
    public CurrentAnimation(String[] parAssetPathArray, String[] parPartNameArray, int[][] parAnimSequenceArray)
    {
        modelAssetPathArray = parAssetPathArray;
        partNameArray = parPartNameArray;
        animationSequenceArray = parAnimSequenceArray;
        
        numParts = parPartNameArray.length;
        
        rotationArray = new float[numParts][3];
        positionArray = new float[numParts][3];
        offsetArray = new float[numParts][3];
        currentSequenceStep = 0;
        currentSequenceStepModifier = 0; // this is used to desync entities of same type
        numStepsInSequence = 1;
        targetModelIndex = 1; // 0 is default, so 1 is first custom pose
        stepsInTween = 0;
        currentTweenStep = 0;
        finishedTween = false;
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
    /*
     * Chainable methods
     */
    public CurrentAnimation setNumStepsInSequence(int parNumStepsInSequence)
    {
        // DEBUG
        System.out.println("Setting number of sequence steps to "+parNumStepsInSequence);
        numStepsInSequence = parNumStepsInSequence;
        return this;
    }
    
    public CurrentAnimation setRandomSequenceStepModifier()
    {
        if (numStepsInSequence < 1) return this;
        
        currentSequenceStepModifier = ((int)Math.floor(Math.random()*numStepsInSequence));
        return this;
    }
    
    // boolean returned indicates if sequence was finished
    public boolean incrementSequenceStep()
    {
        // increment current sequence step
        currentSequenceStep++;
        // check if finished sequence
        if (currentSequenceStep >= numStepsInSequence)
        {
            currentSequenceStep = 0;
            return true;
        }
//        // DEBUG
//        System.out.println("Next pose is sequence step = "+currentSequenceStep);
        return false;
    }
    
    // boolean returned indicates if tween was finished
    public boolean incrementTweenStep()
    {
        currentTweenStep++;
//      // DEBUG
//      System.out.println("current tween step = "+currentTweenStep);
        if (currentTweenStep >= stepsInTween)
        {
            finishedTween = true;
        }
        return finishedTween;
    }
    
    // this getter method includes any sequence step modifier
    public int getSequenceStep()
    {
        return currentSequenceStep;
//        return (currentSequenceStep + currentSequenceStepModifier)%numStepsInSequence;
    }
}
