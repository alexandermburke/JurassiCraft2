package net.timeless.jurassicraft.client.model.animation;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.timeless.animationapi.client.AnimID;
import net.timeless.animationapi.client.JabelarAnimationHelper;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

public class AnimationTherizinosaurus implements IModelAnimator
{
    /*
     * Change the following fields for your custom dinosaur
     */
	protected static Dinosaur theDinosaur = JCEntityRegistry.therizinosaurus;
	    
    // Tell the code where your tabula model assets are
	protected static final String dinoName = theDinosaur.getName(0).toLowerCase(); // this should match name of your resource package and files
    protected static final String modelAssetPath = "/assets/jurassicraft/models/entities/"+dinoName+"/";
    protected static final String[] modelAssetFileNameArray = new String[] {
            "idle",
            "head_cock_left",
            "head_cock_right",
            "hissing",
            "mating",
            "eating_1",
            "eating_1b",
            "eating_2",
            "eating_3",
            "eating_4",
            "eating_4b",
            "eating_5",
            "eating_6",
            "eating_6b",
            "drinking_1",
            "drinking_2",
            "drinking_3",
            "drinking_4",
            "resting",
            "sleeping",
            "calling_1",
            "calling_2",
            "calling_3",
            "calling_4",
            "attacking_1",
            "attacking_2",
            "attacking_3",
            "sniffing_1",
            "sniffing_2",
            "pouncing_1",
            "pouncing_2",
            "pouncing_3",
            "pouncing_4",
            "pouncing_5",
            "pouncing_6",
            "pouncing_7",
            "pouncing_8",
            "pouncing_9"
    };

    private static int getPoseID(String parPose)
    {
        int index = -1;
        if (modelAssetFileNameArray == null)
        {
            System.err.println("Trying to get animation pose from null model array");
        }
        else
        {
            for (int assetPathIndex = 0; assetPathIndex < modelAssetFileNameArray.length; assetPathIndex++)
            {
                if (modelAssetFileNameArray[assetPathIndex].contains(parPose.toLowerCase()))
                {
                    index = assetPathIndex;
                    break;
                }
            }
        }
        return index;
    }
    
    /* 
     * Define your animation sequence here
     * First element is target pose model index (i.e. order of model assets listed in
     * modelAssetPaths array above),
     * Second element is the number of ticks it should take to tween to that pose
     */
    protected static int[][][] arrayOfSequences = new int[AnimID.NUM_IDS][][];
    static
    {
        arrayOfSequences[AnimID.IDLE] = new int[][] {
            {getPoseID("idle"), 1} // for idle sequence good to set to duration 1 to allow maximum interruptibility
        };
    
        arrayOfSequences[AnimID.LOOKING_LEFT] = new int[][] {
            {getPoseID("head_cock_left"), 100}, {getPoseID("head_cock_left"), 80}, {getPoseID("idle"), 100}
        };
        
        arrayOfSequences[AnimID.LOOKING_RIGHT] = new int[][] {
            {getPoseID("head_cock_right"), 100}, {getPoseID("head_cock_right"), 80}, {getPoseID("idle"), 100}
        };
        
        arrayOfSequences[AnimID.HISSING] = new int[][] {
            {getPoseID("hissing"), 100}, {getPoseID("hissing"), 80}, {getPoseID("idle"), 100}
        };
        
        arrayOfSequences[AnimID.MATING] = new int[][] {
            {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10},
            {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, 
            {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("idle"), 100}
        };
        
        arrayOfSequences[AnimID.EATING] = new int[][] {
        	{getPoseID("eating_1"), 100}, {getPoseID("eating_1b"), 40}, {getPoseID("eating_1"), 40}, 
        	{getPoseID("eating_1b"), 40}, {getPoseID("eating_1"), 40}, {getPoseID("eating_1b"), 40}, 
        	{getPoseID("eating_1"), 40}, {getPoseID("eating_2"), 80}, {getPoseID("eating_3"), 80}, 
        	{getPoseID("eating_4"), 100}, {getPoseID("eating_4b"), 40}, {getPoseID("eating_4"), 40}, 
        	{getPoseID("eating_4b"), 40}, {getPoseID("eating_4"), 40}, {getPoseID("eating_4b"), 40}, 
        	{getPoseID("eating_4"), 40}, {getPoseID("eating_4b"), 40}, {getPoseID("eating_3"), 80}, 
        	{getPoseID("eating_5"), 160}, {getPoseID("eating_6"), 100}, {getPoseID("eating_6b"), 40}, 
        	{getPoseID("eating_6"), 40}, {getPoseID("eating_6b"), 40}, {getPoseID("eating_6"), 40}, 
        	{getPoseID("eating_6b"), 40}, {getPoseID("eating_6"), 40}, {getPoseID("eating_6b"), 40},
            {getPoseID("eating_5"), 80}, {getPoseID("idle"), 80}, {getPoseID("idle"), 200}
        };
        
        arrayOfSequences[AnimID.DRINKING]= new int[][] {
            {getPoseID("drinking_1"), 100}, {getPoseID("drinking_2"), 40}, {getPoseID("drinking_2"), 40}, 
            {getPoseID("drinking_3"), 20}, {getPoseID("drinking_4"), 40}, {getPoseID("drinking_4"), 100}, 
            {getPoseID("drinking_1"), 60}, {getPoseID("drinking_2"), 40}, {getPoseID("drinking_2"), 40}, 
            {getPoseID("drinking_3"), 20}, {getPoseID("drinking_4"), 40}, {getPoseID("drinking_4"), 100}, 
            {getPoseID("drinking_1"), 60}, {getPoseID("drinking_2"), 40}, {getPoseID("drinking_2"), 40}, 
            {getPoseID("drinking_3"), 20}, {getPoseID("drinking_4"), 40}, {getPoseID("drinking_4"), 100}, 
            {getPoseID("idle"), 100}
        };
        
        arrayOfSequences[AnimID.RESTING]= new int[][] {
            {getPoseID("resting"), 100}, {getPoseID("resting"), 800}, {getPoseID("idle"), 200}
        };
        
        arrayOfSequences[AnimID.SLEEPING] = new int[][] {
            {getPoseID("resting"), 100}, {getPoseID("sleeping"), 80}, {getPoseID("sleeping"), 800}, {getPoseID("resting"), 40}, {getPoseID("idle"), 200}
        };
        
        arrayOfSequences[AnimID.CALLING] = new int[][] {
            {getPoseID("calling_1"), 100}, {getPoseID("calling_2"), 60}, {getPoseID("calling_2"), 40}, {getPoseID("calling_3"), 40}, 
            {23, 80}, {getPoseID("calling_2"), 40}, {getPoseID("idle"), 200}  
        };
        
//        arrayOfSequences[AnimID.ATTACKING] = new int[][] {
//            {getPoseID("attacking_1"), 100}, {getPoseID("attacking_2"), 60}, {getPoseID("attacking_3"), 20}, 
//            {getPoseID("attacking_2"), 60}, {getPoseID("attacking_3"), 20}, {getPoseID("idle"), 200}
//        };

        arrayOfSequences[AnimID.ATTACKING]= new int[][] {
                {getPoseID("pouncing_1"), 40}, {getPoseID("pouncing_2"), 20}, // crouch
                {getPoseID("pouncing_3"), 40}, {getPoseID("pouncing_2"), 40}, // twitch tail
                {getPoseID("pouncing_3"), 40}, {getPoseID("pouncing_2"), 40}, // twitch tail
                {getPoseID("pouncing_4"), 40}, {getPoseID("pouncing_5"), 40},
                {getPoseID("pouncing_6"), 40}, {getPoseID("pouncing_7"), 80},
                {getPoseID("pouncing_8"), 40}, {getPoseID("pouncing_8"), 20}, {getPoseID("pouncing_9"), 60}, // strike with head
                {getPoseID("idle"), 40}
        };
        
        arrayOfSequences[AnimID.SNIFFING] = new int[][] {
            {getPoseID("sniffing_1"), 100}, {getPoseID("sniffing_2"), 20}, {getPoseID("sniffing_1"), 20}, 
            {getPoseID("sniffing_2"), 20}, {getPoseID("sniffing_1"), 20}, {getPoseID("idle"), 200}
        };
    
    }
    
    // maps each entity id with its current animation 
    protected HashMap<Integer, JabelarAnimationHelper> animationInstanceToEntityMap = new HashMap<Integer, JabelarAnimationHelper>();

    private static MowzieModelRenderer[][] arrayOfPoses;
    
    private static int numParts;
    
    public AnimationTherizinosaurus()
    {
        String[] partNameArray = JabelarAnimationHelper.getTabulaModel(modelAssetPath+dinoName+"_"+modelAssetFileNameArray[0], 0).getCubeNamesArray();
        numParts = partNameArray.length;        
        
        arrayOfPoses = new MowzieModelRenderer[modelAssetFileNameArray.length][numParts];
        
        for (int modelIndex = 0; modelIndex < modelAssetFileNameArray.length; modelIndex++)
        {
            arrayOfPoses[modelIndex] = new MowzieModelRenderer[numParts];
            ModelDinosaur theModel = JabelarAnimationHelper.getTabulaModel(modelAssetPath+dinoName+"_"+modelAssetFileNameArray[modelIndex], 0);
            
            for (int partIndex = 0; partIndex < numParts; partIndex++) 
            {
                if (theModel.getCube(partNameArray[partIndex]) == null)
                {
                    System.err.println("Could not retrieve cube "+partNameArray[partIndex]+" ("+partIndex+") from the model "+modelAssetFileNameArray[modelIndex]);
                }
                arrayOfPoses[modelIndex][partIndex] = theModel.getCube(partNameArray[partIndex]);
            }
        }
    }

    @Override
    public void setRotationAngles(ModelJson parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity parEntity)
    {
    	ModelDinosaur theModel = (ModelDinosaur)parModel;
    	EntityDinosaur theEntity= (EntityDinosaur)parEntity;
    	
        // add entry to hashmap if new entity
        if (!animationInstanceToEntityMap.containsKey(parEntity.getEntityId()))
        {
            // DEBUG
            System.out.println("Adding entity to hashmap with id = "+parEntity.getEntityId());
            animationInstanceToEntityMap.put(parEntity.getEntityId(), new JabelarAnimationHelper(theEntity, theModel, numParts, arrayOfPoses, arrayOfSequences, true, true, 1.0F));
        }

        animationInstanceToEntityMap.get(theEntity.getEntityId()).performJabelarAnimations(theModel);

        // you can still add chain, walk, bob, etc.
        performMowzieAnimations(theModel, f, f1, rotation, rotationYaw, rotationPitch, partialTicks, theEntity);
    }
    
 protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
 {
//     Animator animator = parModel.animator;

     MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");
     MowzieModelRenderer bodyHips = parModel.getCube("Body hips");
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

//     float globalHeight = 2F;
//     float frontOffset = -1.35f;

     //The tail must always be up when the neck is down
     float speed = 0.75F;
     float height = 3F;

     parModel.bob(bodyHips, 1F * speed, height, false, f, f1);
     parModel.flap(bodyHips, 0.5F*speed, 0.5F, false, 0, 0, f, f1);
     parModel.flap(bodyMain, 0.5F*speed, 0.1F, true, 0, 0, f, f1);
     parModel.flap(bodyShoulders, 0.5F*speed, 0.4F, true, 0, 0, f, f1);
     parModel.flap(tail1, 0.5F*speed, 0.2F, true, 0, 0, f, f1);
     parModel.flap(tail3, 0.5F*speed, 0.2F, true, 0, 0, f, f1);
     parModel.flap(tail5, 0.5F*speed, 0.1F, true, 0, 0, f, f1);
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

     parModel.chainSwing(tail, 0.5F * speed, -0.02F, 2, f, f1);
     parModel.chainWave(tail, 1F * speed, -0.02F, 2.5F, f, f1);
     parModel.chainSwing(neck, 0.5F * speed, 0.02F, 2, f, f1);
     parModel.chainWave(neck, 1.0F * speed, 0.02F, 0.5F, f, f1);
//     parModel.chainWave(bodyParts, 1F * speed, -0.1F, 4, f, f1);

     parModel.chainWave(armRight, 1F * speed, -0.3F, 4, f, f1);
     parModel.chainWave(armLeft, 1F * speed, -0.3F, 4, f, f1);

     // Idling
     parModel.chainWave(tail, 0.1F, 0.02F, 2, frame, 1F);
     parModel.chainWave(neck, 0.1F, 0.02F, 2, frame, 1F);
//     parModel.chainWave(bodyParts, 0.1F, -0.03F, 5, frame, 1F);
     parModel.chainWave(armRight, 0.1F, -0.1F, 4, frame, 1F);
     parModel.chainWave(armLeft, 0.1F, -0.1F, 4, frame, 1F);
     
//     parEntity.tailBuffer.applyChainSwingBuffer(tail);

     }
}