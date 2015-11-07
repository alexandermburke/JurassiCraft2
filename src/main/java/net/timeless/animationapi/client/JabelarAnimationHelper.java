package net.timeless.animationapi.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.unilib.client.model.json.TabulaModelHelper;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.fx.EntityFXBlood;

import java.util.Map;

/**
 * @author jabelar This class is used to hold per-entity animation variables for use with Jabelar's animation tweening system.
 */
@SideOnly(Side.CLIENT)
public class JabelarAnimationHelper
{
    private final EntityDinosaur theEntity;

    private final Minecraft mc;

    private final Map<AnimID, int[][]> mapOfSequences;

    private final MowzieModelRenderer[][] arrayOfPoses;
    private MowzieModelRenderer[] theModelRendererArray;
    private MowzieModelRenderer[] nextPoseModel;

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

    /**
     * @param parEntity         the entity to animate from
     * @param parModel          the model to animate
     * @param parNumParts
     * @param parArrayOfPoses   for each pose(-index) an array of posed Renderers
     * @param parMapOfSequences maps from an {@link AnimID} to the sequence of (pose-index, tween-length)
     * @param parInertialTweens
     * @param parInertiaFactor
     */
    public JabelarAnimationHelper(
            EntityDinosaur parEntity,
            ModelDinosaur parModel,
            int parNumParts,
            MowzieModelRenderer[][] parArrayOfPoses,
            Map<AnimID, int[][]> parMapOfSequences,
            boolean parInertialTweens,
            float parInertiaFactor)
    {
        // transfer static animation info from constructor parameters to instance
        theEntity = parEntity;
        numParts = parNumParts;
        arrayOfPoses = parArrayOfPoses;
        mapOfSequences = parMapOfSequences;
        inertialTweens = parInertialTweens;

        lastTicksExisted = theEntity.ticksExisted;

        partialTicks = 0.0F;

        mc = Minecraft.getMinecraft();

        init(parModel);

        JurassiCraft.instance.getLogger().debug("Finished JabelarAnimation constructor");
    }

    public void performJabelarAnimations(float parPartialTicks)
    {

//        JurassiCraft.instance.getLogger().debug("FPS = " + Minecraft.getDebugFPS() + " and current sequence = " + currentSequence + " and current pose = " + this.currentPose + " and current tick = " + this.currentTickInTween + " out of " + numTicksInTween + " and entity ticks existed = " + theEntity.ticksExisted + " and partial ticks = " + partialTicks);

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
        initSequence(theEntity.getAnimID());
        JurassiCraft.instance.getLogger().info("Initializing to animation sequence = " + currentSequence);
        initPoseModel();
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

    private void initSequence(AnimID parSequenceIndex)
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
    }

    private void initPoseModel()
    {
        numPosesInSequence = mapOfSequences.get(currentSequence).length;

        // initialize first pose
        currentPose = theEntity.getCurrentPose();
        nextPoseModel = arrayOfPoses[mapOfSequences.get(currentSequence)[currentPose][0]];
    }

    private void setNextPoseModel(int parPose)
    {
        numPosesInSequence = mapOfSequences.get(currentSequence).length;

        // initialize first pose
        currentPose = parPose;
        theEntity.setCurrentPose(currentPose);
        nextPoseModel = arrayOfPoses[mapOfSequences.get(currentSequence)[currentPose][0]];
    }

    private void initTweenTicks()
    {
        numTicksInTween = mapOfSequences.get(currentSequence)[currentPose][1];
        // filter out illegal values in array
        if (numTicksInTween < 1)
        {
            JurassiCraft.instance.getLogger().error("Array of sequences has sequence with num ticks illegal value (< 1)");
            numTicksInTween = 1;
        }
        currentTickInTween = theEntity.getCurrentTickInTween();
    }

    private void startNextTween()
    {
        numTicksInTween = mapOfSequences.get(currentSequence)[currentPose][1];
        // filter out illegal values in array
        if (numTicksInTween < 1)
        {
            JurassiCraft.instance.getLogger().error("Array of sequences has sequence with num ticks illegal value (< 1)");
            numTicksInTween = 1;
        }
        currentTickInTween = 0;
        theEntity.setCurrentTickInTween(currentTickInTween);
    }

    private void updateIncrementArrays()
    {

        for (int partIndex = 0; partIndex < numParts; partIndex++)
        {
            rotationIncrementArray[partIndex][0] = (nextPoseModel[partIndex].rotateAngleX - currentRotationArray[partIndex][0]) / numTicksInTween;
            rotationIncrementArray[partIndex][1] = (nextPoseModel[partIndex].rotateAngleY - currentRotationArray[partIndex][1]) / numTicksInTween;
            rotationIncrementArray[partIndex][2] = (nextPoseModel[partIndex].rotateAngleZ - currentRotationArray[partIndex][2]) / numTicksInTween;
            positionIncrementArray[partIndex][0] = (nextPoseModel[partIndex].rotationPointX - currentPositionArray[partIndex][0]) / numTicksInTween;
            positionIncrementArray[partIndex][1] = (nextPoseModel[partIndex].rotationPointY - currentPositionArray[partIndex][1]) / numTicksInTween;
            positionIncrementArray[partIndex][2] = (nextPoseModel[partIndex].rotationPointZ - currentPositionArray[partIndex][2]) / numTicksInTween;
            offsetIncrementArray[partIndex][0] = (nextPoseModel[partIndex].offsetX - currentOffsetArray[partIndex][0]) / numTicksInTween;
            offsetIncrementArray[partIndex][1] = (nextPoseModel[partIndex].offsetY - currentOffsetArray[partIndex][1]) / numTicksInTween;
            offsetIncrementArray[partIndex][2] = (nextPoseModel[partIndex].offsetZ - currentOffsetArray[partIndex][2]) / numTicksInTween;
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

    private void setNextPoseModel()
    {
        nextPoseModel = arrayOfPoses[mapOfSequences.get(currentSequence)[currentPose][0]];
        numTicksInTween = mapOfSequences.get(currentSequence)[currentPose][1];
        currentTickInTween = 0;
        theEntity.setCurrentTickInTween(currentTickInTween);
    }

    private void performNextTweenTick()
    {
        // update the passed in model
        tween();
        // since the method is called at rate of twice the display refresh rate
        // need to slow it down to only increment per tick.
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
//        JurassiCraft.instance.getLogger().debug("current tween tick +  partial ticks = " + (currentTickInTween + partialTicks));

        for (int partIndex = 0; partIndex < numParts; partIndex++)
        {
            if (nextPoseModel == null)
            {
                JurassiCraft.instance.getLogger().error("Trying to tween to a null next pose array");
            }
            else if (nextPoseModel[partIndex] == null)
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
        setNextPoseModel();
        updateIncrementArrays();
    }

    // boolean returned indicates if tween was finished
    public boolean incrementTweenTick()
    {
        // JurassiCraft.instance.getLogger().info("current tween step = "+currentTickInTween);
        currentTickInTween++;
        theEntity.setCurrentTickInTween(currentTickInTween);
        if (currentTickInTween >= numTicksInTween)
        {
            return true;
        }
        return false;
    }

    // boolean returned indicates if sequence was finished
    public boolean incrementCurrentPose()
    {
        boolean finishedSequence = false;

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
                theEntity.setCurrentPose(currentPose);
                finishedSequence = true;
            }
        }

        // JurassiCraft.instance.getLogger().debug("Next pose is sequence step = "+currentSequenceStep);
        theEntity.setCurrentPose(currentPose);
        return finishedSequence;
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
        }
        else if (currentSequence != AnimID.IDLE && currentSequence == parSequenceIndex) // finished sequence but no new sequence set
        {
            JurassiCraft.instance.getLogger().debug("Reverting to idle sequence");
            currentSequence = AnimID.IDLE;
        }
        else
        {
            JurassiCraft.instance.getLogger().debug("Setting new sequence to " + parSequenceIndex);
            currentSequence = parSequenceIndex;
        }

        theEntity.setAnimID(currentSequence);
        setNextPoseModel(0);
        startNextTween();

        if (currentSequence != AnimID.IDLE)
        {
            JurassiCraft.instance.getLogger().debug("current sequence for entity ID " + theEntity.getEntityId() + " is " + currentSequence + " out of " + mapOfSequences.size() + " and current pose " + currentPose + " out of " + mapOfSequences.get(currentSequence).length + " with " + numTicksInTween + " ticks in tween");
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

    private void performBloodSpurt()
    {
        double posX = theEntity.posX;
        double posY = theEntity.posY;
        double posZ = theEntity.posZ;

        World world = theEntity.worldObj;

        EffectRenderer effectRenderer = mc.effectRenderer;

        if (theEntity.hurtTime == theEntity.maxHurtTime - 1)
        {
            float entityWidth = theEntity.width;
            float entityHeight = theEntity.height;

            for (float x = 0; x < entityWidth; x++)
            {
                for (float y = 0; y < entityHeight; y++)
                {
                    for (float z = 0; z < entityWidth; z++)
                    {
                        addBloodEffect(world, effectRenderer, x + posX - (entityWidth / 2.0F), y + posY, z + posZ - (entityWidth / 2.0F));
                    }
                }
            }

//            addBloodEffect(world, effectRenderer, posX, posY + Math.round(theEntity.height * 0.75), posZ);
        }
    }

    private void addBloodEffect(World world, EffectRenderer effectRenderer, double x, double y, double z)
    {
        effectRenderer.addEffect((new EntityFXBlood(world, x + 0.5D, y + 0.5D, z + 0.5D, 0, 0, 0)).func_174846_a(new BlockPos(x, y, z)));
    }
}
