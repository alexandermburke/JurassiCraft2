package org.jurassicraft.common.entity.ai.metabolism;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.animationapi.client.Animations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jurassicraft.common.entity.ai.util.BlockBreaker;
import org.jurassicraft.common.entity.ai.util.OnionTraverser;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.MetabolismContainer;

/**
 * This piece of AI use used to find a plant and eat it. Should be titled "graze".
 */
public class EntityAIFindPlant extends EntityAIBase
{
    // We always want to eat if below this.
    public static final double MUST_EAT_THRESHOLD = 0.25;

    // If we are awake, then we want to eat below this threshold
    public static final double SHOULD_EAT_THRESHOLD = 0.82;

    // How far to eat the thing
    public static final int EAT_RADIUS = 25;

    // This is how fast we are to break grass
    public static final double EAT_BREAK_SPEED = 1.0;

    // The minimum time to eat.
    public static final double MIN_BREAK_TIME_SEC = 3.0;

    // How many block away the critter will look for plants.
    // TODO: Add eyesight/smell attribute for finding plants.
    public static final int LOOK_RADIUS = 16;

    // The dinosaur we are tracking for.
    protected final EntityDinosaur _dinosaur;

    // The target block to feed on, other null if currently not targeting anything
    protected BlockPos _target = null;

    // Used to animate block breaking
    protected BlockBreaker _breaker = null;

    public EntityAIFindPlant(EntityDinosaur dinosaur)
    {
        _dinosaur = dinosaur;
    }

    @Override
    public boolean shouldExecute()
    {
        MetabolismContainer metabolism = _dinosaur.getMetabolism();

        // We don't want to eat if we are dead, too often, or not supposed to
        if (_dinosaur.isDead ||
                _dinosaur.isCarcass() ||
                (_dinosaur.ticksExisted & 0x0F) != 0 ||
                !_dinosaur.worldObj.getGameRules().getBoolean("dinoMetabolism"))
        {
            return false;
        }

        // Now, let's see if we are hungry
        double food = metabolism.getFood();
        int maxFood = metabolism.getMaxFood();

        return ((food < (maxFood * MUST_EAT_THRESHOLD)) ||
                ((food < (maxFood * SHOULD_EAT_THRESHOLD)) &&
                        _dinosaur.getDinosaur().getSleepingSchedule()
                                .isWithinEatingTime(_dinosaur.getDinosaurTime(), _dinosaur.getRNG())));
    }

    @Override
    public void startExecuting()
    {
        // This gets called once to initiate.  Here's where we find the plant and start movement
        BlockPos head = getHeadPos();

        World world = _dinosaur.worldObj;

        // Look in increasing layers (e.g. boxes) around the head.
        OnionTraverser traverser = new OnionTraverser(head, LOOK_RADIUS);
        _target = null;
        for (BlockPos pos : traverser)
        {
            Block block = world.getBlockState(pos).getBlock();

            // TODO: Use FoodHelper and diet
            // TODO: Maybe user block drops
            if (block instanceof BlockBush || block instanceof BlockLeaves)
            {
                _target = pos;
                break;
            }
        }

        if (_target != null)
        {
//            LOGGER.info("Found plant food pos=" + _target);
            _dinosaur.getNavigator().tryMoveToXYZ(_target.getX(), _target.getY(), _target.getZ(), 1.0);
        }
    }

    @Override
    public void updateTask()
    {
        if (_breaker != null)
        {
            if (_breaker.tickUpdate())
            {
                if (_dinosaur.worldObj.getGameRules().getBoolean("mobGriefing"))
                {
                    _dinosaur.worldObj.destroyBlock(_target, false);
                }

                // TODO:  Add food value & food heal value to food helper
                _dinosaur.getMetabolism().increaseFood(2000);
                _dinosaur.heal(4.0F);
                _breaker = null;
                _target = null;

                // Now that we have finished stop the animation
                Animation.sendAnimationPacket(_dinosaur, Animations.IDLE.get());
            }
            return;
        }

        if (_dinosaur.getNavigator().noPath())
        {
            // No path.  If close enough, start the breaker
            // TODO: Head is above ground, so we need to compute this differently.  Ideally it can bend down
//            if (getHeadPos().distanceSq(_target) < EAT_RADIUS)
            {
                // Start the animation
                Animation.sendAnimationPacket(_dinosaur, Animations.EATING.get());

                // Eating grass is really slow
                _breaker = new BlockBreaker(_dinosaur, EAT_BREAK_SPEED, _target, MIN_BREAK_TIME_SEC);
                _dinosaur.getLookHelper().setLookPosition(_target.getX(), _target.getY(), _target.getZ(),
                        0, _dinosaur.getVerticalFaceSpeed());

//                LOGGER.info("Started breaker: " + _breaker);
            }
//            else
//            {
//                // We got near our target but not close enough.  Try again.
//                _target = null;
//            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting()
    {
        return _target != null;
    }

    // ================================

    private BlockPos getHeadPos()
    {
        // Current we use head height when we really want length.
        // TODO:  Move this method to EntityDinosaur
        return _dinosaur.getPosition().
                offset(_dinosaur.getHorizontalFacing(), (int) _dinosaur.width).
                offset(EnumFacing.UP, (int) _dinosaur.getEyeHeight());
    }

    private static final Logger LOGGER = LogManager.getLogger();


}
