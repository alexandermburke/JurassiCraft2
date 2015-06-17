package net.ilexiconn.jurassicraft.api.animation;

import java.util.HashMap;
import java.util.Map;

import net.ilexiconn.jurassicraft.api.animation.AnimationTree.AnimNode;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelRenderer;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

public class Animator
{

    private final AnimationTree animTree;
    private final HashMap<IAnimatedEntity, Integer> prevAnims;
    private final HashMap<IAnimatedEntity, Float> ticks;
    private final HashMap<ModelRenderer, Transform> globalStart;
    private IAnimatedEntity currentEntity;
    private AnimationPhase currentPhase;
    private ModelJson model;
    private boolean initialized;
    private HashMap<ModelRenderer, Transform> initPose;

    public Animator()
    {
        this.animTree = new AnimationTree();
        prevAnims = Maps.newHashMap();
        ticks = Maps.newHashMap();
        globalStart = Maps.newHashMap();
    }

    /**
     * Starts animation definition
     * 
     * @param entity
     *            The entity to animate
     * @param partialTick
     *            The partial tick given by the MC renderer
     */
    public void begin(IAnimatedEntity entity, float partialTick)
    {
        this.currentEntity = entity;
        int oldAnimID = getPreviousAnim(entity);
        setTick(entity, getTick(entity) + partialTick);
        if (oldAnimID != entity.getAnimID())
        {
            reset(entity);
        }
        animTree.clear();
        currentPhase = null;
    }

    private int getPreviousAnim(IAnimatedEntity entity)
    {
        if (!prevAnims.containsKey(entity))
        {
            return -1;
        }
        return prevAnims.get(entity);
    }

    private void setPrevAnim(IAnimatedEntity ent, int anim)
    {
        prevAnims.put(ent, anim);
    }

    /**
     * Resets the entity to its init pose and clear tick counter
     * 
     * @param ent
     *            The entity to reset
     */
    public void reset(IAnimatedEntity ent)
    {
        setPrevAnim(ent, -1);
        setTick(ent, 0f);

        for (ModelRenderer r : initPose.keySet())
        {
            Transform tr = initPose.get(r);
            r.rotateAngleX = tr.rotX;
            r.rotateAngleY = tr.rotY;
            r.rotateAngleZ = tr.rotZ;

            r.offsetX = tr.trX;
            r.offsetY = tr.trY;
            r.offsetZ = tr.trZ;

            r.rotationPointX = tr.rotOffsetX;
            r.rotationPointY = tr.rotOffsetY;
            r.rotationPointZ = tr.rotOffsetZ;
        }
    }

    private float getTick(IAnimatedEntity entity)
    {
        if (!ticks.containsKey(entity))
        {
            return -1;
        }
        return ticks.get(entity);
    }

    private void setTick(IAnimatedEntity ent, float tick)
    {
        ticks.put(ent, tick);
    }

    /**
     * Ends the animation definition
     */
    public void end()
    {
        apply(animTree.getRoot());
        this.currentEntity = null;
    }

    /**
     * Applies the current node's phase + applies its children's
     * 
     * @param node
     */
    private void apply(AnimNode node)
    {
        if (node.value != null)
        {
            AnimationPhase phase = node.value;
            float totalDuration = phase.getDuration();
            float tick = getTick(currentEntity) - phase.getOffset();
            Map<ModelRenderer, Transform> start = phase.getStartTransforms();
            Map<ModelRenderer, Transform> dest = phase.getDestTransforms();
            if (tick >= 0 && tick < totalDuration)
            {
                float progress = tick / totalDuration;

                float alpha = phase.getEasing().apply(progress);
                float invA = 1f - alpha;
                for (ModelRenderer part : start.keySet())
                {
                    Transform startTransform = start.get(part);
                    Transform destTransform = dest.get(part);
                    part.offsetX = startTransform.trX * invA + destTransform.trX * alpha;
                    part.offsetY = startTransform.trY * invA + destTransform.trY * alpha;
                    part.offsetZ = startTransform.trZ * invA + destTransform.trZ * alpha;

                    part.rotateAngleX = startTransform.rotX * invA + destTransform.rotX * alpha;
                    part.rotateAngleY = startTransform.rotY * invA + destTransform.rotY * alpha;
                    part.rotateAngleZ = startTransform.rotZ * invA + destTransform.rotZ * alpha;

                    part.rotationPointX = startTransform.rotOffsetX * invA + destTransform.rotOffsetX * alpha;
                    part.rotationPointY = startTransform.rotOffsetY * invA + destTransform.rotOffsetY * alpha;
                    part.rotationPointZ = startTransform.rotOffsetZ * invA + destTransform.rotOffsetZ * alpha;
                }
            }
        }
        for (AnimNode c : node.children)
        {
            apply(c);
        }
    }

    /**
     * Creates an animation phase for a given duration
     * 
     * @param duration
     *            The duration to play the phase, in ticks
     * @return The newly created phase
     */
    public AnimationPhase startPhase(long duration)
    {
        return startPhase(duration, EasingEquations.none);
    }

    /**
     * Creates an animation phase for a given duration and easing equation
     * 
     * @param duration
     *            The duration to play the phase, in ticks
     * @param easing
     *            The easing equation to use
     * @return The newly created phase
     */
    public AnimationPhase startPhase(long duration, Function<Float/* Time */, Float/*  */> easing)
    {
        return startPhase(duration, 0, easing);
    }

    /**
     * Creates an animation phase for a given duration, starting with an offset
     * 
     * @param duration
     *            The duration to play the phase, in ticks
     * @param offset
     *            The duration before playing this phase, in ticks
     * @return The newly created phase
     */
    public AnimationPhase startPhase(long duration, long offset)
    {
        return startPhase(duration, offset, EasingEquations.none);
    }

    /**
     * Creates an animation phase for a given duration and easing equation
     * 
     * @param duration
     *            The duration to play the phase, in ticks
     * @param offset
     *            The duration before playing this phase, in ticks
     * @param easing
     *            The easing equation to use
     * @return The newly created phase
     */
    public AnimationPhase startPhase(long duration, long offset, Function<Float/* Time */, Float/*  */> easing)
    {
        AnimationPhase phase = new AnimationPhase(duration, offset, easing);
        animTree.leaf(currentPhase, phase);
        currentPhase = phase;
        return phase;
    }

    /**
     * Ends the current phase definition
     */
    public void endPhase()
    {
        AnimationPhase prevPhase = currentPhase;
        currentPhase = animTree.parentValue(currentPhase);
        if (currentPhase == null)
        { // Last phase was the main phase
            float totalDuration = prevPhase.getDuration();
            float tick = getTick(currentEntity) - prevPhase.getOffset();
            if (tick >= totalDuration && prevPhase.isLooping())
            { // Loop main phase
                reset(currentEntity);
            }
        }
    }

    /**
     * Translates a body part while performing the phase
     * 
     * @param part
     *            The part to move
     * @param offsetX
     *            Movement on X axis
     * @param offsetY
     *            Movement on Y axis
     * @param offsetZ
     *            Movement on Z axis
     */
    public void translate(ModelRenderer part, float offsetX, float offsetY, float offsetZ)
    {
        translate(part, offsetX, offsetY, offsetZ, false);
    }

    /**
     * Translates a body part while performing the phase
     * 
     * @param part
     *            The part to move
     * @param offsetX
     *            Movement on X axis
     * @param offsetY
     *            Movement on Y axis
     * @param offsetZ
     *            Movement on Z axis
     * @param keepRotationOffset
     *            Set it to <code>true</code> if you want the rotation offset to stay at the same position; set it to <code>false</code> if you want to move with the part
     */
    public void translate(ModelRenderer part, float offsetX, float offsetY, float offsetZ, boolean keepRotationOffset)
    {
        Transform tr = currentPhase.getTransform(part);
        tr.trX += offsetX;
        tr.trY += offsetY;
        tr.trZ += offsetZ;

        if (!keepRotationOffset)
        {
            tr.rotOffsetX += offsetX;
            tr.rotOffsetY += offsetY;
            tr.rotOffsetZ += offsetZ;
        }
    }

    /**
     * Translates the rotation point of given body part
     * 
     * @param part
     *            The part whose rotation point to translate
     * @param offsetX
     *            The translation on X axis
     * @param offsetY
     *            The translation on Y axis
     * @param offsetZ
     *            The translation on Z axis
     */
    public void translateRotOffset(ModelRenderer part, float offsetX, float offsetY, float offsetZ)
    {
        Transform tr = currentPhase.getTransform(part);
        tr.rotOffsetX += offsetX;
        tr.rotOffsetY += offsetY;
        tr.rotOffsetZ += offsetZ;
    }

    /**
     * Rotates a body part by given angles
     * 
     * @param part
     *            The part to rotate
     * @param x
     *            The angle around the X axis
     * @param y
     *            The angle around the Y axis
     * @param z
     *            The angle around the Z axis
     */
    public void rotate(ModelRenderer part, float x, float y, float z)
    {
        Transform tr = currentPhase.getTransform(part);
        tr.rotX += x;
        tr.rotY += y;
        tr.rotZ += z;
    }

    /**
     * Inits this animator for the given model
     * 
     * @param model
     */
    public void init(ModelJson model)
    {
        this.model = model;
        initPose = Maps.newHashMap();
        for (ModelRenderer p : model.parts)
        {
            Transform tr = new Transform(p);
            initPose.put(p, tr);
        }
        globalStart.putAll(initPose);
        initialized = true;
    }

    private void saveModel(Map<ModelRenderer, Transform> m)
    {
        for (ModelRenderer p : model.parts)
        {
            Transform tr = new Transform(p);
            m.put(p, tr);
        }
    }

    public boolean isInitialized()
    {
        return initialized;
    }
}
