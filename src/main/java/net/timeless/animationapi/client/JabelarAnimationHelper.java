package net.timeless.animationapi.client;

import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.client.model.json.TabulaModelHelper;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;

/**
 * @author jabelar
 *         This class is used to hold per-entity animation variables for use with
 *         Jabelar's animation tweening system.
 */
@SideOnly(Side.CLIENT)
public class JabelarAnimationHelper
{
    private final EntityDinosaur theEntity;
    private IBlockState theBloodIBlockState;

    private final Minecraft mc;

    private final Map<AnimID, int[][]> mapOfSequences;

    private final MowzieModelRenderer[][] arrayOfPoses;
    private MowzieModelRenderer[] theModelRendererArray;
    private MowzieModelRenderer[] nextPose;

    private float[][] currentRotationArray;
    private float[][] currentPositionArray;
    private float[][] currentOffsetArray;

    private float[][] rotationIncrementArray;
    private float[][] positionIncrementArray;
    private float[][] offsetIncrementArray;

    private final int numParts;

    private AnimID currentSequence;
    private int numPosesInSequence;
    private int currentPose;
    private int numTicksInTween;
    private int currentTickInTween;
    private float partialTicks;

    private int lastTicksExisted;

    private final boolean inertialTweens;
    private final float baseInertiaFactor;

    /**
     * @param parEntity         the entity to animate from
     * @param parModel          the model to animate
     * @param parNumParts
     * @param parArrayOfPoses   for each pose(-index) an array of posed Renderers
     * @param parMapOfSequences maps from an {@link AnimID} to the sequence of (pose-index, tween-length)
     * @param parInertialTweens
     * @param parInertiaFactor
     */
    public JabelarAnimationHelper(EntityDinosaur parEntity, ModelDinosaur parModel, int parNumParts,
                                  MowzieModelRenderer[][] parArrayOfPoses, Map<AnimID, int[][]> parMapOfSequences,
                                  boolean parInertialTweens, float parInertiaFactor)
    {
        // transfer static animation info from constructor parameters to instance
        theEntity = parEntity;
        numParts = parNumParts;
        arrayOfPoses = parArrayOfPoses;
        mapOfSequences = parMapOfSequences;
        inertialTweens = parInertialTweens;
        baseInertiaFactor = parInertiaFactor;

        lastTicksExisted = theEntity.ticksExisted;

        partialTicks = 0.0F;

        mc = Minecraft.getMinecraft();

        init(parModel);
        initBloodParticles();

        JurassiCraft.instance.getLogger().debug("Finished JabelarAnimation constructor");
    }

    public void performJabelarAnimations(float parPartialTicks)
    {

        JurassiCraft.instance.getLogger().info("FPS = " + Minecraft.getDebugFPS() + " and current sequence = " +
                currentSequence + " and current pose = " + this.currentPose + " and current tick = " +
                this.currentTickInTween + " out of " + numTicksInTween + " and entity ticks existed = " +
                theEntity.ticksExisted + " and partial ticks = " + partialTicks);

        performBloodSpurt();

        // Allow interruption of the animation if it is a new animation and not currently dying
        if (theEntity.getAnimID() != currentSequence && currentSequence != AnimID.DYING)
        {
            setNextSequence(theEntity.getAnimID());
        }
        performNextTweenTick();

        partialTicks = parPartialTicks; // need to update this after the call because entity ticks are updated one call after partial ticks
}

    private void init(ModelDinosaur parModel)
    {
        theModelRendererArray = convertPassedInModelToModelRendererArray(parModel);
        setNextSequence(theEntity.getAnimID());
        JurassiCraft.instance.getLogger().info("Initializing to animation sequence = " + theEntity.getAnimID());
        initPose(); // sets the target pose based on sequence
        initTweenTicks();

        // copy passed in model into a model renderer array
        // NOTE: this is the array you will actually animate
        theModelRendererArray = convertPassedInModelToModelRendererArray(parModel);

        // initialize the current pose arrays to match the model renderer array
        currentRotationArray = new float[numParts][3];
        currentPositionArray = new float[numParts][3];
        currentOffsetArray = new float[numParts][3];
        updateCurrentPoseArrays();

        // initialize the increment arrays to match difference between current and next pose
        rotationIncrementArray = new float[numParts][3];
        positionIncrementArray = new float[numParts][3];
        offsetIncrementArray = new float[numParts][3];
        updateIncrementArrays();
    }

    private void initPose()
    {
        numPosesInSequence = mapOfSequences.get(currentSequence).length;

        // initialize first pose
        currentPose = 0;
        nextPose = arrayOfPoses[mapOfSequences.get(currentSequence)[currentPose][0]];
    }

    private void initTweenTicks()
    {
        numTicksInTween = mapOfSequences.get(currentSequence)[currentPose][1];
        // filter out illegal values in array
        if (numTicksInTween < 1)
        {
            System.err.println("Array of sequences has sequence with num ticks illegal value (< 1)");
            numTicksInTween = 1;
        }
        currentTickInTween = 0;
    }

    private void updateIncrementArrays()
    {

        for (int partIndex = 0; partIndex < numParts; partIndex++)
        {
            rotationIncrementArray[partIndex][0] = (nextPose[partIndex].rotateAngleX - currentRotationArray[partIndex][0]) / numTicksInTween;
            rotationIncrementArray[partIndex][1] = (nextPose[partIndex].rotateAngleY - currentRotationArray[partIndex][1]) / numTicksInTween;
            rotationIncrementArray[partIndex][2] = (nextPose[partIndex].rotateAngleZ - currentRotationArray[partIndex][2]) / numTicksInTween;
            positionIncrementArray[partIndex][0] = (nextPose[partIndex].rotationPointX - currentPositionArray[partIndex][0]) / numTicksInTween;
            positionIncrementArray[partIndex][1] = (nextPose[partIndex].rotationPointY - currentPositionArray[partIndex][1]) / numTicksInTween;
            positionIncrementArray[partIndex][2] = (nextPose[partIndex].rotationPointZ - currentPositionArray[partIndex][2]) / numTicksInTween;
            offsetIncrementArray[partIndex][0] = (nextPose[partIndex].offsetX - currentOffsetArray[partIndex][0]) / numTicksInTween;
            offsetIncrementArray[partIndex][1] = (nextPose[partIndex].offsetY - currentOffsetArray[partIndex][1]) / numTicksInTween;
            offsetIncrementArray[partIndex][2] = (nextPose[partIndex].offsetZ - currentOffsetArray[partIndex][2]) / numTicksInTween;
        }

    }

    private void updateCurrentPoseArrays()
    {
        for (int partIndex = 0; partIndex < numParts; partIndex++)
        {
            currentRotationArray[partIndex][0] = theModelRendererArray[partIndex].rotateAngleX;
            currentRotationArray[partIndex][1] = theModelRendererArray[partIndex].rotateAngleY;
            currentRotationArray[partIndex][2] = theModelRendererArray[partIndex].rotateAngleZ;
            currentPositionArray[partIndex][0] = theModelRendererArray[partIndex].rotationPointX;
            currentPositionArray[partIndex][1] = theModelRendererArray[partIndex].rotationPointY;
            currentPositionArray[partIndex][2] = theModelRendererArray[partIndex].rotationPointZ;
            currentOffsetArray[partIndex][0] = theModelRendererArray[partIndex].offsetX;
            currentOffsetArray[partIndex][1] = theModelRendererArray[partIndex].offsetY;
            currentOffsetArray[partIndex][2] = theModelRendererArray[partIndex].offsetZ;
        }
    }

    private MowzieModelRenderer[] convertPassedInModelToModelRendererArray(ModelDinosaur parModel)
    {
        String[] partNameArray = parModel.getCubeNamesArray();

        MowzieModelRenderer[] modelRendererArray = new MowzieModelRenderer[numParts];

        for (int i = 0; i < numParts; i++)
        {
            modelRendererArray[i] = parModel.getCube(partNameArray[i]);
        }

        return modelRendererArray;
    }

    private void setNextPose()
    {
        nextPose = arrayOfPoses[mapOfSequences.get(currentSequence)[currentPose][0]];
        numTicksInTween = mapOfSequences.get(currentSequence)[currentPose][1];
        currentTickInTween = 0;
    }

    private void performNextTweenTick()
    {
        // update the passed in model
        tween();
        if (theEntity.ticksExisted > lastTicksExisted)
        {
            lastTicksExisted = theEntity.ticksExisted;

            if (incrementTweenTick()) // increments tween tick and returns true if finished pose
            {
                handleFinishedPose();
            }
        }
    }

    private void tween()
    {
        JurassiCraft.instance.getLogger().info("current tween tick +  partial ticks = "+(currentTickInTween + partialTicks));

        for (int partIndex = 0; partIndex < numParts; partIndex++)
        {
            if (nextPose == null)
            {
                JurassiCraft.instance.getLogger().error("Trying to tween to a null next pose array");
            }
            else if (nextPose[partIndex] == null)
            {
                JurassiCraft.instance.getLogger().error("The part index " + partIndex + " in next pose is null");
            }
            else if (currentRotationArray == null)
            {
                JurassiCraft.instance.getLogger().error("Trying to tween from a null current rotation array");
            }
            else
            {
                float inertiaFactor = calculateInertiaFactor();
                nextTweenRotations(partIndex, inertiaFactor);
                nextTweenPositions(partIndex, inertiaFactor);
                nextTweenOffsets(partIndex, inertiaFactor);
            }
        }
    }

    private void nextTweenRotations(int parPartIndex, float parInertiaFactor)
    {
        theModelRendererArray[parPartIndex].rotateAngleX = currentRotationArray[parPartIndex][0] + rotationIncrementArray[parPartIndex][0] * (currentTickInTween + partialTicks) * parInertiaFactor;
        theModelRendererArray[parPartIndex].rotateAngleY = currentRotationArray[parPartIndex][1] + rotationIncrementArray[parPartIndex][1] * (currentTickInTween + partialTicks) * parInertiaFactor;
        theModelRendererArray[parPartIndex].rotateAngleZ = currentRotationArray[parPartIndex][2] + rotationIncrementArray[parPartIndex][2] * (currentTickInTween + partialTicks) * parInertiaFactor;
    }

    private void nextTweenPositions(int parPartIndex, float parInertiaFactor)
    {
        theModelRendererArray[parPartIndex].rotationPointX = currentPositionArray[parPartIndex][0] + positionIncrementArray[parPartIndex][0] * (currentTickInTween + partialTicks) * parInertiaFactor;
        theModelRendererArray[parPartIndex].rotationPointY = currentPositionArray[parPartIndex][1] + positionIncrementArray[parPartIndex][1] * (currentTickInTween + partialTicks) * parInertiaFactor;
        theModelRendererArray[parPartIndex].rotationPointZ = currentPositionArray[parPartIndex][2] + positionIncrementArray[parPartIndex][2] * (currentTickInTween + partialTicks) * parInertiaFactor;
    }

    private void nextTweenOffsets(int parPartIndex, float parInertiaFactor)
    {
        theModelRendererArray[parPartIndex].offsetX = currentOffsetArray[parPartIndex][0] + offsetIncrementArray[parPartIndex][0] * (currentTickInTween + partialTicks) * parInertiaFactor;
        theModelRendererArray[parPartIndex].offsetY = currentOffsetArray[parPartIndex][1] + offsetIncrementArray[parPartIndex][1] * (currentTickInTween + partialTicks) * parInertiaFactor;
        theModelRendererArray[parPartIndex].offsetZ = currentOffsetArray[parPartIndex][2] + offsetIncrementArray[parPartIndex][2] * (currentTickInTween + partialTicks) * parInertiaFactor;
    }

    private float calculateInertiaFactor()
    {
        double inertiaFactor = 1.0D;
        if (inertialTweens)
        {
            if (currentTickInTween < (numTicksInTween * 0.5F))
            {
                inertiaFactor = Math.pow(2.0D * currentTickInTween / numTicksInTween, 2) / 2.0D;
            }
            else
            {
                inertiaFactor = 1.0D - Math.pow((2.0D * currentTickInTween / numTicksInTween) - 2.0D, 2) / 2.0D;
            }
        }

        return (float) inertiaFactor;
    }

    private void handleFinishedPose()
    {
        if (incrementCurrentPose()) // increments pose and returns true if finished sequence
        {
            setNextSequence(theEntity.getAnimID());
        }

        updateCurrentPoseArrays();
        setNextPose();
        updateIncrementArrays();
    }

    // boolean returned indicates if tween was finished
    public boolean incrementTweenTick()
    {
//        JurassiCraft.instance.getLogger().info("current tween step = "+currentTickInTween);
        currentTickInTween++;
        if (currentTickInTween >= numTicksInTween)
        {
            return true;
        }
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
            if (theEntity.getAnimID() == AnimID.DYING) // hold last dying pose indefinitely
            {
                currentPose--;
            }
            else
            {
                currentPose = 0;
                return true;
            }
        }

        // JurassiCraft.instance.getLogger().debug("Next pose is sequence step = "+currentSequenceStep);
        return false;
    }

    private void setNextSequence(AnimID parSequenceIndex)
    {
        // TODO
        // Should control here which animations are interruptible, in which priority
        // I.e. could reject certain changes depending on what current animation is playing

        // handle case where animation sequence isn't available
        if (mapOfSequences.get(parSequenceIndex) == null)
        {
            JurassiCraft.instance.getLogger().error("Requested an anim id " + parSequenceIndex.toString() + " that doesn't have animation sequence in map for entity " + theEntity.getEntityId());
            currentSequence = AnimID.IDLE;
            theEntity.setAnimID(AnimID.IDLE);
        }
        else if (currentSequence != AnimID.IDLE && currentSequence == parSequenceIndex) // finished sequence but no new sequence set
        {
            JurassiCraft.instance.getLogger().debug("Reverting to idle sequence");
            currentSequence = AnimID.IDLE;
            theEntity.setAnimID(AnimID.IDLE);
        }
        else
        {
            JurassiCraft.instance.getLogger().debug("Setting new sequence to " + parSequenceIndex);
            currentSequence = parSequenceIndex;
        }

        currentPose = 0;
        initPose();
        initTweenTicks();
        if (currentSequence != AnimID.IDLE)
        {
            JurassiCraft.instance.getLogger().info("current sequence for entity ID " + theEntity.getEntityId() + " is " + currentSequence
                    + " out of " + mapOfSequences.size() + " and current pose " + currentPose + " out of "
                    + mapOfSequences.get(currentSequence).length + " with " + numTicksInTween + " ticks in tween");
        }
    }

    public int getCurrentPose()
    {
        return currentPose;
    }

    public static ModelDinosaur getTabulaModel(String tabulaModel, int geneticVariant)
    {
        // catch the exception so you can call method without further catching
        try
        {
            return new ModelDinosaur(TabulaModelHelper.parseModel(tabulaModel), null); // okay to use null for animator
            // parameter as we get animator
            // from passed-in model
        }
        catch (Exception e)
        {
            JurassiCraft.instance.getLogger().error("Could not load Tabula model = " + tabulaModel);
        }

        return null;
    }

    public ModelDinosaur getTabulaModel(String tabulaModel)
    {
        return getTabulaModel(tabulaModel, 0);
    }

    private void initBloodParticles()
    {
        theBloodIBlockState = Blocks.redstone_block.getDefaultState();
    }

    private void performBloodSpurt()
    {
        if (theEntity.hurtTime == theEntity.maxHurtTime - 1)
        {
            int i = 0;

            for (int x = (int) (theEntity.posX - theEntity.width - 1); x < (int) (theEntity.posX + theEntity.width - 1); x++)
            {
                for (int y = (int) (theEntity.posY - theEntity.height); y < (int) (theEntity.posY + theEntity.height); y++)
                {
                    for (int z = (int) (theEntity.posZ - theEntity.width - 1); z < (int) (theEntity.posZ + theEntity.width - 1); z++)
                    {
                        if (i % 10 == 0)
                        {
                            mc.effectRenderer.addBlockDestroyEffects(new BlockPos(x, y, z), theBloodIBlockState);
                        }

                        i++;
                    }
                }
            }

            mc.effectRenderer.addBlockDestroyEffects(theEntity.getPosition().up((int) Math.round(theEntity.height * 0.75)), theBloodIBlockState);
        }
        if (theEntity.deathTime > 0 && theEntity.deathTime < 70 && theEntity.deathTime % 30 == 0)
        {
            mc.effectRenderer.addBlockDestroyEffects(theEntity.getPosition().up(), theBloodIBlockState);
        }
    }
}
