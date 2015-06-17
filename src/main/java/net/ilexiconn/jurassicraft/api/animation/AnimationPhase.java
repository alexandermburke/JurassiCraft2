package net.ilexiconn.jurassicraft.api.animation;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import net.minecraft.client.model.ModelRenderer;

import java.util.HashMap;

public class AnimationPhase {

    private final Function<Float, Float> easing;
    private final long offset;
    private final long duration;
    private final HashMap<ModelRenderer, Transform> startTransforms;
    private final HashMap<ModelRenderer, Transform> destTransforms;
    private boolean looping;

    public AnimationPhase(long duration, long offset, Function<Float, Float> easing) {
        this.duration = duration;
        this.offset = offset;
        this.easing = easing;
        startTransforms = Maps.newHashMap();
        destTransforms = Maps.newHashMap();
        looping = true;
    }

    public long getDuration() {
        return duration;
    }

    public Function<Float, Float> getEasing() {
        return easing;
    }

    public long getOffset() {
        return offset;
    }

    public Transform getTransform(ModelRenderer part) {
        if(!destTransforms.containsKey(part)) {
            Transform start = new Transform();
            start.rotOffsetX = part.rotationPointX;
            start.rotOffsetY = part.rotationPointY;
            start.rotOffsetZ = part.rotationPointZ;
            start.rotX = part.rotateAngleX;
            start.rotY = part.rotateAngleY;
            start.rotZ = part.rotateAngleZ;
            start.trX = part.offsetX;
            start.trY = part.offsetY;
            start.trZ = part.offsetZ;
            startTransforms.put(part, start);
            destTransforms.put(part, start.copy());
        }
        return destTransforms.get(part);
    }

    public HashMap<ModelRenderer, Transform> getStartTransforms() {
        return startTransforms;
    }

    public HashMap<ModelRenderer, Transform> getDestTransforms() {
        return destTransforms;
    }

    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }
}
