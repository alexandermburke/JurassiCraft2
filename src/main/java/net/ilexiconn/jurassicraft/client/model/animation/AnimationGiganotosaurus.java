package net.ilexiconn.jurassicraft.client.model.animation;

import net.ilexiconn.jurassicraft.api.animation.Animator;
import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.entity.Entity;

public class AnimationGiganotosaurus implements IModelAnimator
{
    private final Animator animator;

    public AnimationGiganotosaurus()
    {
        this.animator = new Animator();
    }

    @Override
    public void setRotationAngles(ModelJson model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        float globalSpeed = 0.45F;
        float globalDegree = 0.45F;
        float height = 1.0F;
        
        MowzieModelRenderer neck = model.getCube("Neck 1");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        MowzieModelRenderer neck4 = model.getCube("Neck 4");
        MowzieModelRenderer head = model.getCube("Head");
        
        MowzieModelRenderer tail1 = model.getCube("Tail 1");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");
        
        MowzieModelRenderer body1 = model.getCube("Body shoulders");
        MowzieModelRenderer body2 = model.getCube("Body waist");
        MowzieModelRenderer body3 = model.getCube("Body hips");
        
        MowzieModelRenderer rightLeg = model.getCube("Right Thigh");
        MowzieModelRenderer leftLeg = model.getCube("Left Thigh");
        
        MowzieModelRenderer rightFoot = model.getCube("Foot Right");
        MowzieModelRenderer leftFoot = model.getCube("Foot Left");
        
        MowzieModelRenderer rightCalf = model.getCube("Right Calf 1");
        MowzieModelRenderer leftCalf = model.getCube("Left Calf 1");
        
        MowzieModelRenderer lowerJaw = model.getCube("Lower jaw");
        
        MowzieModelRenderer[] body = new MowzieModelRenderer[]{body1, body2, body3};
        
        MowzieModelRenderer[] tail = new MowzieModelRenderer[]{tail1, tail2, tail3, tail4, tail5, tail6};
        
        int ticksExisted = entity.ticksExisted;
        
        float breathSpeed = 0.05F;
        
        body3.rotateAngleX += f1 * 0.1F;
        
        model.walk(head, breathSpeed, 0.05F, true, 0F, -0.2F, ticksExisted, 1.0F);
        model.walk(neck, breathSpeed, 0.03F, false, 0F, 0.04F, ticksExisted, 1.0F);
        model.walk(neck2, breathSpeed, 0.03F, false, 0F, 0.04F, ticksExisted, 1.0F);
        model.walk(neck3, breathSpeed, 0.03F, false, 0F, 0.04F, ticksExisted, 1.0F);
        model.walk(neck4, breathSpeed, 0.03F, false, 0F, 0.04F, ticksExisted, 1.0F);
        
        model.walk(rightLeg, 1, 0.4F, false, 0F, 0.1F, f * 0.5F, f1);
        model.walk(leftLeg, 1, 0.4F, true, 0F, 0.1F, f * 0.5F, f1);
        
        model.walk(rightCalf, 1, 0.15F, true, 0F, 0.1F, f * 0.5F, f1);
        model.walk(leftCalf, 1, 0.15F, false, 0F, 0.1F, f * 0.5F, f1);
        
        model.walk(rightFoot, 1, 0.2F, true, -0.5F, 0.1F, f * 0.5F, f1);
        model.walk(leftFoot, 1, 0.2F, false, -0.5F, 0.1F, f * 0.5F, f1);
        
        model.walk(lowerJaw, breathSpeed, 0.04F, false, 0.1F, 0.12F, ticksExisted, 1.0F);
        
        model.chainWave(tail, breathSpeed, -0.03F, 2, ticksExisted, 1F);
        
        model.chainWave(body, breathSpeed, -0.03F, 2, ticksExisted, 1F);
    }
}
