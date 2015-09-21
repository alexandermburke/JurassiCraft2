package net.timeless.animationapi.client;

/**
 * @author jabelar
 */
public enum AnimID
{
    IDLE,
    ATTACKING,
    INJURED,
    HEAD_COCKING,
    CALLING,
    HISSING,
    POUNCING,
    SNIFFING,
    EATING,
    DRINKING,
    MATING,
    SLEEPING,
    RESTING,
    FLYING,
    ROARING,
    SCRATCHING,
    LOOKING_LEFT,
    LOOKING_RIGHT,
    BEGGING;

    public static final int NUM_IDS = AnimID.values().length;

    // named according to the Trello task list of animations
    /*
     * These must each be unique!
     */
    public static final int IDLE_ = AnimID.IDLE.ordinal();
    public static final int ATTACKING_ = AnimID.ATTACKING.ordinal();
    public static final int INJURED_ = AnimID.INJURED.ordinal();
    public static final int HEAD_COCKING_ = AnimID.HEAD_COCKING.ordinal();
    public static final int CALLING_ = AnimID.CALLING.ordinal();
    public static final int HISSING_ = AnimID.HISSING.ordinal();
    public static final int POUNCING_ = AnimID.POUNCING.ordinal();
    public static final int SNIFFING_ = AnimID.SNIFFING.ordinal();
    public static final int EATING_ = AnimID.EATING.ordinal();
    public static final int DRINKING_ = AnimID.DRINKING.ordinal();
    public static final int MATING_ = AnimID.MATING.ordinal();
    public static final int SLEEPING_ = AnimID.SLEEPING.ordinal();
    public static final int RESTING_ = AnimID.RESTING.ordinal();
    public static final int FLYING_ = AnimID.FLYING.ordinal();
    public static final int ROARING_ = AnimID.ROARING.ordinal();
    public static final int SCRATCHING_ = AnimID.SCRATCHING.ordinal();
    public static final int LOOKING_LEFT_ = AnimID.LOOKING_LEFT.ordinal();
    public static final int LOOKING_RIGHT_ = AnimID.LOOKING_RIGHT.ordinal();
    public static final int BEGGING_ = AnimID.BEGGING.ordinal();
}
