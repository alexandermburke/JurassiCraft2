package net.timeless.jurassicraft.common.api.animation;

import javax.annotation.Nullable;

import com.google.common.base.Function;

public final class EasingEquations
{

    public static final Function<Float, Float> none = new Function<Float, Float>()
    {
        @Nullable
        @Override
        public Float apply(Float input)
        {
            return input;
        }
    };
}
