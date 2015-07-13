package net.timeless.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;
import net.timeless.jurassicraft.api.animation.Animator;

public class AnimationTriceratops implements IModelAnimator
{
    private final Animator animator;

    public AnimationTriceratops()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        MowzieModelRenderer head = model.getCube("Head");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck1 = model.getCube("Neck 1");
        MowzieModelRenderer shoulders = model.getCube("Body shoulders");
        MowzieModelRenderer stomach = model.getCube("Body MAIN");
        MowzieModelRenderer waist = model.getCube("Body hips");
        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");
        MowzieModelRenderer armUpperLeft = model.getCube("FrontLeg Upper Left");
        MowzieModelRenderer armLowerLeft = model.getCube("FrontLeg MID Left");
        MowzieModelRenderer handLeft = model.getCube("FrontLeg FOOT Left");
        MowzieModelRenderer armUpperRight = model.getCube("FrontLeg Upper Right");
        MowzieModelRenderer armLowerRight = model.getCube("FrontLeg MID Right");
        MowzieModelRenderer handRight = model.getCube("FrontLeg FOOT Right");
        MowzieModelRenderer thighLeft = model.getCube("RearLeg Upper Left");
        MowzieModelRenderer calfLeft = model.getCube("RearLeg Middle Left");
        MowzieModelRenderer footLeft = model.getCube("RearLeg Foot Left");
        MowzieModelRenderer thighRight = model.getCube("RearLeg Upper Right");
        MowzieModelRenderer calfRight = model.getCube("RearLeg Middle Right");
        MowzieModelRenderer footRight = model.getCube("RearLeg Foot Right");
        MowzieModelRenderer jaw = model.getCube("Jaw LOWER");

        f = entity.ticksExisted;
        f1 = 0.3F;

        float globalSpeed = 0.1F;
        float globalDegree = 0.8F;
        float height = 1.2F;
        float frontOffset = 0.84F;

        model.faceTarget(head, 4, rotationYaw, rotationPitch);
        model.faceTarget(neck3, 4, rotationYaw, rotationPitch);
        model.faceTarget(neck2, 4, rotationYaw, rotationPitch);
        model.faceTarget(neck1, 4, rotationYaw, rotationPitch);

        model.bob(waist, 1 * globalSpeed, height, false, f, f1);
        model.walk(waist, 1 * globalSpeed, 0.1F * height, true, -1.5F, 0.05F, f, f1);

    }
}
