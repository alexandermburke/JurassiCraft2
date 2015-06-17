package net.ilexiconn.jurassicraft.api.animation;

import com.google.common.base.Function;

import javax.annotation.Nullable;

public final class EasingEquations {

    public static final Function<Float, Float> none = new Function<Float, Float>() {
        @Nullable
        @Override
        public Float apply(Float input) {
            return input;
        }
    };
}
