package net.timeless.jurassicraft.client.model.animation;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.AnimID;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.JabelarAnimationHelper;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.dinosaur.DinosaurTherizinosaurus;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationTyrannosaurusRex implements IModelAnimator
{
    /*
     * Change the following fields for your custom dinosaur
     */
    protected static final Dinosaur theDinosaur = new DinosaurTherizinosaurus(); // do I need to get specific instance, I don't think so

    // Tell the code where your tabula model assets are
    // the first one must be your "default" pose (i.e one that is used at spawn time)
    protected static final String[] modelAssetPathArray = new String[] {
            "/assets/jurassicraft/models/entities/tyrannosaurus/tyrannosaurus_default",
            "/assets/jurassicraft/models/entities/tyrannosaurus/tyrannosaurus_look_left",
            "/assets/jurassicraft/models/entities/tyrannosaurus/tyrannosaurus_look_right",
            "/assets/jurassicraft/models/entities/tyrannosaurus/tyrannosaurus_rearing_1",
            "/assets/jurassicraft/models/entities/tyrannosaurus/tyrannosaurus_rearing_2"
    };

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
            {0, 200}
        };

        arrayOfSequences[AnimID.LOOKING_LEFT] = new int[][] {
            {1, 100}, {1, 80}, {0, 100}, {0, 200}
        };
        
        arrayOfSequences[AnimID.LOOKING_RIGHT] = new int[][] {
            {2, 100}, {2, 80}, {0, 100}, {0, 200}
        };
        
        arrayOfSequences[AnimID.SNIFFING] = new int[][] {
            {3, 40}, {4, 100}, {4, 80}, {0, 100}, {0, 200}
        };
    }

    /*
     * The first element in this array must be the "default" (idle) animation sequence,
     * for other sequences, if you have random sequence enabled, you can make a sequence more
     * likely to happen by including it multiple times in the array.
     */
//    protected static int[][][] arrayOfSequences = new int[][][] {
//        sequenceIdle,
//        sequenceSniffing,
//        sequenceLookLeft,
//        sequenceLookRight,
//        sequenceLookLeft,
//        sequenceLookRight,
//        sequenceLookLeft,
//        sequenceLookRight,
//        sequenceLookLeft,
//        sequenceLookRight,
//    };

    // maps each entity id with its current animation 
    protected HashMap<Integer, JabelarAnimationHelper> animationInstanceToEntityMap = new HashMap<Integer, JabelarAnimationHelper>();

    private static MowzieModelRenderer[][] arrayOfPoses;
    
    private static int numParts;

    // load tabula models once per game during implicit constructor static initialization
    static
    {
    	String[] partNameArray = JabelarAnimationHelper.getTabulaModel(modelAssetPathArray[0], 0).getCubeNamesArray();
        numParts = partNameArray.length;        
        
        arrayOfPoses = new MowzieModelRenderer[modelAssetPathArray.length][numParts];
        
        for (int modelIndex = 0; modelIndex < modelAssetPathArray.length; modelIndex++)
        {
            arrayOfPoses[modelIndex] = new MowzieModelRenderer[numParts];
            ModelDinosaur theModel = JabelarAnimationHelper.getTabulaModel(modelAssetPathArray[modelIndex], 0);
            
            for (int partIndex = 0; partIndex < numParts; partIndex++) 
            {
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
            animationInstanceToEntityMap.put(parEntity.getEntityId(), new JabelarAnimationHelper(theEntity, theModel, numParts, arrayOfPoses, arrayOfSequences, true, true,30, true, 1.0F));
        }

        animationInstanceToEntityMap.get(theEntity.getEntityId()).performJabelarAnimations(theModel);

        // you can still add chain, walk, bob, etc.
        performMowzieAnimations(theModel, f, f1, rotation, rotationYaw, rotationPitch, partialTicks, theEntity);
    }
    
    private void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
    	Animator animator = parModel.animator;
        // f = entity.ticksExisted;
        // f1 = (float) Math.cos(f/20)*0.25F + 0.5F;
        // f1 = 0.5F;

        // Walking-dependent animation
        float globalSpeed = 0.45F;
        float globalDegree = 0.5F;
        float height = 1.0F;

        MowzieModelRenderer stomach = parModel.getCube("Body 2");
        MowzieModelRenderer chest = parModel.getCube("Body 3");
        MowzieModelRenderer head = parModel.getCube("Head");
        MowzieModelRenderer waist = parModel.getCube("Body 1");

        MowzieModelRenderer neck1 = parModel.getCube("Neck 1");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");
        MowzieModelRenderer neck4 = parModel.getCube("Neck 4");
        MowzieModelRenderer neck5 = parModel.getCube("Neck 5");

        MowzieModelRenderer tail1 = parModel.getCube("Tail 1");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");

        MowzieModelRenderer throat1 = parModel.getCube("Throat 1");
        MowzieModelRenderer throat2 = parModel.getCube("Throat 2");
        MowzieModelRenderer throat3 = parModel.getCube("Throat 3");

        MowzieModelRenderer lowerJaw = parModel.getCube("Lower Jaw");

        MowzieModelRenderer handLeft = parModel.getCube("Hand Left");
        MowzieModelRenderer lowerArmLeft = parModel.getCube("Lower Arm Left");
        MowzieModelRenderer upperArmLeft = parModel.getCube("Upper Arm Left");

        MowzieModelRenderer handRight = parModel.getCube("Hand Right");
        MowzieModelRenderer lowerArmRight = parModel.getCube("Lower Arm Right");
        MowzieModelRenderer upperArmRight = parModel.getCube("Upper Arm Right");

        MowzieModelRenderer leftThigh = parModel.getCube("Left Thigh");
        MowzieModelRenderer rightThigh = parModel.getCube("Right Thigh");

        MowzieModelRenderer leftCalf1 = parModel.getCube("Left Calf 1");
        MowzieModelRenderer leftCalf2 = parModel.getCube("Left Calf 2");
        MowzieModelRenderer leftFoot = parModel.getCube("Foot Left");

        MowzieModelRenderer rightCalf1 = parModel.getCube("Right Calf 1");
        MowzieModelRenderer rightCalf2 = parModel.getCube("Right Calf 2");
        MowzieModelRenderer rightFoot = parModel.getCube("Foot Right");

        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};
        MowzieModelRenderer[] bodyParts = new MowzieModelRenderer[]{head, neck5, neck4, neck3, neck2, neck1, chest, stomach, waist};
        MowzieModelRenderer[] leftArmParts = new MowzieModelRenderer[]{handLeft, lowerArmLeft, upperArmLeft};
        MowzieModelRenderer[] rightArmParts = new MowzieModelRenderer[]{handRight, lowerArmRight, upperArmRight};

        parModel.faceTarget(stomach, 6.0F, rotationYaw, rotationPitch);
        parModel.faceTarget(chest, 6.0F, rotationYaw, rotationPitch);
        parModel.faceTarget(head, 3.0F, rotationYaw, rotationPitch);
        parModel.faceTarget(neck1, 3.0F, rotationYaw, rotationPitch);

        parModel.bob(waist, 1F * globalSpeed, height, false, f, f1);
        parModel.bob(leftThigh, 1F * globalSpeed, height, false, f, f1);
        parModel.bob(rightThigh, 1F * globalSpeed, height, false, f, f1);
        leftThigh.rotationPointY -= -2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        rightThigh.rotationPointY -= 2 * f1 * Math.cos(f * 0.5 * globalSpeed);
        parModel.walk(neck1, 1F * globalSpeed, 0.15F, false, 0F, 0.2F, f, f1);
        parModel.walk(head, 1F * globalSpeed, 0.15F, true, 0F, -0.2F, f, f1);

        parModel.walk(leftThigh, 0.5F * globalSpeed, 0.8F * globalDegree, false, 0F, 0.4F, f, f1);
        parModel.walk(leftCalf1, 0.5F * globalSpeed, 1F * globalDegree, true, 1F, 0.4F, f, f1);
        parModel.walk(leftCalf2, 0.5F * globalSpeed, 1F * globalDegree, false, 0F, 0F, f, f1);
        parModel.walk(leftFoot, 0.5F * globalSpeed, 1.5F * globalDegree, true, 0.5F, 0.3F, f, f1);

        parModel.walk(rightThigh, 0.5F * globalSpeed, 0.8F * globalDegree, true, 0F, 0.4F, f, f1);
        parModel.walk(rightCalf1, 0.5F * globalSpeed, 1F * globalDegree, false, 1F, 0.4F, f, f1);
        parModel.walk(rightCalf2, 0.5F * globalSpeed, 1F * globalDegree, true, 0F, 0F, f, f1);
        parModel.walk(rightFoot, 0.5F * globalSpeed, 1.5F * globalDegree, false, 0.5F, 0.3F, f, f1);

        parModel.chainWave(tailParts, 1F * globalSpeed, 0.05F, 2, f, f1);
        parModel.chainWave(bodyParts, 1F * globalSpeed, 0.05F, 3, f, f1);
        parModel.chainWave(leftArmParts, 1F * globalSpeed, 0.2F, 1, f, f1);
        parModel.chainWave(rightArmParts, 1F * globalSpeed, 0.2F, 1, f, f1);

        // Idling
        parModel.chainWave(bodyParts, 0.1F, -0.03F, 3, parEntity.ticksExisted, 1.0F);
        parModel.chainWave(rightArmParts, -0.1F, 0.2F, 4, parEntity.ticksExisted, 1.0F);
        parModel.chainWave(leftArmParts, -0.1F, 0.2F, 4, parEntity.ticksExisted, 1.0F);

        parModel.chainSwing(tailParts, 0.1F, 0.05F - (0.05F), 1, parEntity.ticksExisted, 1.0F - 0.6F);
        parModel.chainWave(tailParts, 0.1F, -0.1F, 2, parEntity.ticksExisted, 1.0F - 0.6F);

//        parEntity.tailBuffer.applyChainSwingBuffer(tailParts);
//
//        animator.setAnim(1);
//        animator.startPhase(15);
//        animator.move(waist, 0, -3, -5);
//        animator.move(rightThigh, 0, -3, -5);
//        animator.move(leftThigh, 0, -3, -5);
//        animator.rotate(waist, -0.3F, 0, 0);
//        animator.rotate(head, 0.3F, 0, 0);
//        animator.rotate(rightThigh, 0.3F, 0, 0);
//        animator.rotate(rightCalf1, -0.4F, 0, 0);
//        animator.rotate(rightCalf2, 0.4F, 0, 0);
//        animator.rotate(rightFoot, -0.3F, 0, 0);
//        animator.rotate(leftThigh, -0.7F, 0, 0);
//        animator.rotate(leftCalf1, 0.7F, 0, 0);
//        animator.rotate(leftCalf2, -0.5F, 0, 0);
//        animator.rotate(leftFoot, 0.7F, 0, 0);
//        animator.endPhase();
//        animator.startPhase(10);
//        animator.move(waist, 0, 3, -10);
//        animator.move(rightThigh, 0, 3, -10);
//        animator.move(leftThigh, 0, 3, -10);
//        animator.move(head, 0, 1, 2);
//        animator.move(lowerJaw, 0, 0, 1);
//        animator.rotate(waist, 0.2F, 0, 0);
//        animator.rotate(neck1, 0.2F, 0, 0);
//        animator.rotate(neck2, 0.2F, 0, 0);
//        animator.rotate(neck3, -0.2F, 0, 0);
//        animator.rotate(neck4, -0.1F, 0, 0);
//        animator.rotate(neck5, -0.1F, 0, 0);
//        animator.move(neck5, 0, 0, 1);
//        animator.move(throat1, 0, -0.5F, 0);
//        animator.move(throat2, 0, -1, 0);
//        animator.move(throat3, 0, -1, 0);
//        animator.rotate(head, -0.5F, 0, 0);
//        animator.move(head, 0, 1, 0);
//        animator.rotate(lowerJaw, 0.9F, 0, 0);
//        animator.rotate(rightThigh, 0.6F, 0, 0);
//        animator.rotate(rightCalf1, 0.05F, 0, 0);
//        animator.rotate(rightCalf2, -0.3F, 0, 0);
//        animator.rotate(rightFoot, -0.3F, 0, 0);
//        animator.rotate(leftThigh, -0.3F, 0, 0);
//        animator.rotate(leftCalf1, 0.2F, 0, 0);
//        animator.rotate(leftCalf2, -0.2F, 0, 0);
//        animator.rotate(leftFoot, 0.3F, 0, 0);
//        animator.endPhase();
//        animator.setStationaryPhase(35);
//        animator.resetPhase(15);
//
//        animator.setAnim(2);
//        animator.startPhase(15);
//        animator.rotate(waist, -0.2F, 0, 0);
//        animator.rotate(stomach, -0.1F, 0, 0);
//        animator.rotate(chest, 0.1F, 0, 0);
//        animator.rotate(neck1, -0.1F, 0, 0);
//        animator.rotate(neck2, -0.1F, 0, 0);
//        animator.rotate(neck3, 0.1F, 0, 0);
//        animator.rotate(neck4, 0.1F, 0, 0);
//        animator.rotate(neck5, 0.1F, 0, 0);
//        animator.rotate(head, 0.3F, 0, 0);
//        animator.endPhase();
//        animator.startPhase(10);
//        animator.rotate(waist, 0.1F, 0, 0);
//        animator.rotate(neck1, 0.2F, 0, 0);
//        animator.rotate(neck2, 0.2F, 0, 0);
//        animator.rotate(neck3, 0.1F, 0, 0);
//        animator.rotate(neck4, -0.2F, 0, 0);
//        animator.rotate(neck5, -0.2F, 0, 0);
//        animator.move(throat1, 0, 0, 0);
//        animator.move(throat2, 0, -1, -3.5F);
//        animator.move(throat3, 0, -1.5F, 0);
//        animator.rotate(head, -0.4F, 0, 0);
//        animator.move(head, 0, 1, 2F);
//        animator.rotate(lowerJaw, 0.8F, 0, 0);
//        animator.endPhase();
//        animator.setStationaryPhase(35);
//        animator.resetPhase(15);
//
//        if (entity.getAnimationId() == JurassiCraftAnimationIDs.EATING.animID())
//        {
//            float shakeProgress = ((EntityTyrannosaurus) entity).shakePrey.getAnimationProgressSinSqrt();
//            chainSwing(bodyParts, 0.6F, 0.2F * shakeProgress, 1, ((EntityTyrannosaurus) entity).frame, 1F);
//            chainSwing(tailParts, 0.6F, -0.2F * shakeProgress, 3, ((EntityTyrannosaurus) entity).frame, 1F);
//            waist.rotateAngleX += 0.3 * shakeProgress;
//            head.rotateAngleX -= 0.3 * shakeProgress;
//            animator.setAnimation(JurassiCraftAnimationIDs.EATING.animID());
//            animator.startPhase(0);
//            animator.rotate(lowerJaw, 0.3F, 0.0F, 0.0F);
//            animator.endPhase();
//            animator.setStationaryPhase(30);
//            animator.startPhase(7);
//            animator.rotate(lowerJaw, 0.4F, 0.0F, 0.0F);
//            animator.rotate(neck1, -0.4F, 0.0F, 0.0F);
//            animator.rotate(head, -0.4F, 0.0F, 0.0F);
//            animator.endPhase();
//            animator.setStationaryPhase(3);
//            animator.resetPhase(10);
//        }
//
//        animator.setAnim(3);
//        animator.startPhase(6);
//        animator.rotate(neck1, -0.1F, -0.2F, 0);
//        animator.rotate(head, -0.2F, -0.3F, 0);
//        animator.rotate(waist, -0.1F, -0.2F, 0);
//        animator.rotate(lowerJaw, 1F, 0, 0);
//        animator.endPhase();
//        animator.setStationaryPhase(1);
//        animator.startPhase(3);
//        animator.rotate(neck1, 0.2F, 0.1F, 0);
//        animator.rotate(neck2, 0.2F, 0.1F, 0);
//        animator.rotate(neck3, 0.1F, 0.1F, 0);
//        animator.rotate(neck4, -0.2F, 0.1F, 0);
//        animator.rotate(neck5, -0.2F, 0.1F, 0);
//        animator.move(throat2, 0, 0, -2.7F);
//        animator.move(throat3, 0, 0, 1.5F);
//        animator.rotate(head, -0.2F, 0.4F, 0);
//        animator.rotate(waist, 0.2F, 0.2F, 0);
//        animator.endPhase();
//        animator.setStationaryPhase(2);
//        animator.resetPhase(8);
//
//        EntityTyrannosaurus trex = parEntity;
//
//        head.rotateAngleZ += Math.cos(parEntity.ticksExisted / 3) * trex.roarTiltDegree.value / 3;
//        lowerJaw.rotateAngleX += Math.cos(parEntity.ticksExisted) * trex.roarTiltDegree.value / 7;
    }
}

