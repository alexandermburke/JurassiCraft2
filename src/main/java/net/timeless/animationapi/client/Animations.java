package net.timeless.animationapi.client;

import net.ilexiconn.llibrary.common.animation.Animation;
import net.timeless.animationapi.AnimationAPI;

/**
 * @author jabelar
 */
public enum Animations
{
    // These are all those animations defined in our Trello to-do list
    IDLE, ATTACKING, INJURED, HEAD_COCKING, CALLING, HISSING, POUNCING, SNIFFING, EATING, DRINKING, MATING, SLEEPING(true), RESTING, FLYING, ROARING, LIVING_SOUND,
    // This wasn't in Trello list but was used in one of the dinos
    SCRATCHING,
    // These weren't in Trello list but are an example of how we could have random animations during idle
    LOOKING_LEFT, LOOKING_RIGHT, BEGGING,
    // I was requested by Gegy to add dying animations
    DYING(true);

    private Animation animation;
    private boolean hold;

    Animations(boolean hold)
    {
        this.hold = hold;
    }

    Animations()
    {
        this(false);
    }

    public Animation get() {
        if (animation == null) {
            animation = new Animation(ordinal(), -1);
        }
        return animation;
    }

    public static Animation[] getAnimations() {
        Animation[] animations = new Animation[values().length];
        for (int i = 0; i < animations.length; i++) {
            animations[i] = values()[i].get();
        }
        return animations;
    }

    public static Animations getAnimation(Animation animation)
    {
        for (Animations anim : values())
        {
            if (animation.animationId == anim.get().animationId)
            {
                return anim;
            }
        }

        return null;
    }

    public boolean shouldHold()
    {
        return hold;
    }
}
