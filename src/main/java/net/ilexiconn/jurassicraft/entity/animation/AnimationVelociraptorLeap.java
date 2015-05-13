package net.ilexiconn.jurassicraft.entity.animation;

public class AnimationVelociraptorLeap implements IEntityAnimation
{
    @Override
    public float getSpeed()
    {
        return 0.1F;
    }

    @Override
    public float getLength()
    {
        return 2F;
    }
}
