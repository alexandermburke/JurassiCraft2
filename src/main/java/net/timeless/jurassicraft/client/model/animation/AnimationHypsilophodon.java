package net.timeless.jurassicraft.client.model.animation;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.jurassicraft.common.entity.EntityHypsilophodon;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

@SideOnly(Side.CLIENT)
public class AnimationHypsilophodon implements IModelAnimator
{
    @Override
    public void setRotationAngles(ModelJson modelJson, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur model = (ModelDinosaur) modelJson;
        Animator animator = model.animator;
        
        int ticksExisted = entity.ticksExisted;
        
        float scaleFactor = 0.6F;
        float height = 12F * f1;

        MowzieModelRenderer upperlegright = model.getCube("Leg UPPER RIGHT");
        MowzieModelRenderer upperlegleft = model.getCube("Leg UPPER LEFT");
        
        MowzieModelRenderer midlegright = model.getCube("Leg MIDDLE RIGHT");
        MowzieModelRenderer midlegleft = model.getCube("Leg MIDDLE LEFT");
        
        MowzieModelRenderer lowerlegright = model.getCube("Leg LOWER RIGHT");
        MowzieModelRenderer lowerlegleft = model.getCube("Leg LOWER LEFT");
        
        MowzieModelRenderer feetright = model.getCube("Foot RIGHT");
        MowzieModelRenderer feetleft = model.getCube("Foot LEFT");
        
        MowzieModelRenderer head = model.getCube("Head ");
        MowzieModelRenderer neck = model.getCube("Neck BASE");
        MowzieModelRenderer neck2 = model.getCube("Neck 2");
        MowzieModelRenderer neck3 = model.getCube("Neck 3");
        
        MowzieModelRenderer body2 = model.getCube("Body REAR");
        MowzieModelRenderer body1 = model.getCube("Body FRONT");
        
        MowzieModelRenderer tail1 = model.getCube("Tail BASE");
        MowzieModelRenderer tail2 = model.getCube("Tail 2");
        MowzieModelRenderer tail3 = model.getCube("Tail 3");
        MowzieModelRenderer tail4 = model.getCube("Tail 4");
        MowzieModelRenderer tail5 = model.getCube("Tail 5");
        MowzieModelRenderer tail6 = model.getCube("Tail 6");
        
        MowzieModelRenderer shoulderright = model.getCube("Arm UPPER RIGHT");
        MowzieModelRenderer shoulderleft = model.getCube("Arm UPPER LEFT");
        
        MowzieModelRenderer armright = model.getCube("Arm MIDDLE RIGHT");
        MowzieModelRenderer armleft = model.getCube("Arm MIDDLE LEFT");
        
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail6, tail5, tail4, tail3, tail2, tail1 };
        
        model.bob(body2, 0.5F * scaleFactor, height, true, f, f1);
        model.bob(upperlegright, 0.5F * scaleFactor, height, true, f, f1);
        model.bob(upperlegleft, 0.5F * scaleFactor, height, true, f, f1);

        model.walk(upperlegleft, 1F * scaleFactor, 0.75F, true, 1F, 0.25F, f, f1);
        model.walk(upperlegright, 1F * scaleFactor, 0.75F, true, 0.5F, 0.25F, f, f1);
        model.walk(midlegleft, 1F * scaleFactor, 0.75F, false, 1.5F, 0F, f, f1);
        model.walk(midlegright, 1F * scaleFactor, 0.75F, false, 1F, 0F, f, f1);
        model.walk(lowerlegright, 1F * scaleFactor, 0.75F, true, 0.5F, 0F, f, f1);
        model.walk(lowerlegleft, 1F * scaleFactor, 0.75F, true, 1F, 0F, f, f1);
        model.walk(feetleft, 1F * scaleFactor, 0.5F, true, 1F, 0.75F, f, f1);
        model.walk(feetright, 1F * scaleFactor, 0.5F, true, 0.5F, 0.75F, f, f1);

        model.walk(body2, 1F * scaleFactor, 0.3F, false, 0.5F, 0F, f, f1);
        model.walk(body1, 1F * scaleFactor, 0.5F, true, 1.0F, 0F, f, f1);
        model.walk(neck, 1F * scaleFactor, 0.3F, true, 0.25F, 0.3F, f, f1);
        model.walk(head, 1F * scaleFactor, 0.3F, false, 0.25F, -0.3F, f, f1);

        model.walk(shoulderright, 1 * scaleFactor, 0.3F, true, 1, 0.2F, f, f1);
        model.walk(shoulderleft, 1 * scaleFactor, 0.3F, true, 1, 0.2F, f, f1);
        model.walk(armright, 1 * scaleFactor, 0.3F, false, 1, -0.2F, f, f1);
        model.walk(armleft, 1 * scaleFactor, 0.3F, false, 1, -0.2F, f, f1);
        
        model.faceTarget(head, 1, rotationYaw, rotationPitch);

        // Idling
        model.chainWave(tailParts, 0.2F, -0.05F, 2, ticksExisted, 1F);
        model.walk(neck, 0.2F, 0.1F, false, -1F, 0F, ticksExisted, 1F);
        model.walk(head, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(body1, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(body2, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);
        model.walk(shoulderright, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(shoulderleft, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(armright, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);
        model.walk(armleft, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);
        
        model.chainWave(tailParts, 1F * scaleFactor, 0.15F, 2, f, f1);

        EntityHypsilophodon hysilophodon = (EntityHypsilophodon) entity;

        if (hysilophodon.getAnimID() == EntityHypsilophodon.SCRATCH)
        {
            animator.setAnim(EntityHypsilophodon.SCRATCH);
            animator.startPhase(5);
            animator.rotate(neck, 0.9F, -0.4F, 0);
            animator.rotate(head, -0.8F, -0.25F, 0.7F);
            animator.rotate(shoulderleft, -2.6F, 0, 0);
            animator.rotate(armleft, 0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(2);
            animator.rotate(neck, 0.9F, -0.35F, 0);
            animator.rotate(head, -0.8F, -0.15F, 0.7F);
            animator.rotate(shoulderleft, -0.5F, 0, 0);
            animator.rotate(armleft, -0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(3);
            animator.rotate(neck, 0.9F, -0.4F, 0);
            animator.rotate(head, -0.8F, -0.25F, 0.7F);
            animator.rotate(shoulderleft, -2.6F, 0, 0);
            animator.rotate(armleft, 0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(2);
            animator.rotate(neck, 0.9F, -0.35F, 0);
            animator.rotate(head, -0.8F, -0.15F, 0.7F);
            animator.rotate(shoulderleft, -0.5F, 0, 0);
            animator.rotate(armleft, -0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(3);
            animator.rotate(neck, 0.9F, -0.4F, 0);
            animator.rotate(head, -0.8F, -0.25F, 0.7F);
            animator.rotate(shoulderleft, -2.6F, 0, 0);
            animator.rotate(armleft, 0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(2);
            animator.rotate(neck, 0.9F, -0.35F, 0);
            animator.rotate(head, -0.8F, -0.15F, 0.7F);
            animator.rotate(shoulderleft, -0.5F, 0, 0);
            animator.rotate(armleft, -0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(3);
            animator.rotate(neck, 0.9F, -0.4F, 0);
            animator.rotate(head, -0.8F, -0.25F, 0.7F);
            animator.rotate(shoulderleft, -2.6F, 0, 0);
            animator.rotate(armleft, 0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(2);
            animator.rotate(neck, 0.9F, -0.35F, 0);
            animator.rotate(head, -0.8F, -0.15F, 0.7F);
            animator.rotate(shoulderleft, -0.5F, 0, 0);
            animator.rotate(armleft, -0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(3);
            animator.rotate(neck, 0.9F, -0.4F, 0);
            animator.rotate(head, -0.8F, -0.25F, 0.7F);
            animator.rotate(shoulderleft, -2.6F, 0, 0);
            animator.rotate(armleft, 0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(2);
            animator.rotate(neck, 0.9F, -0.35F, 0);
            animator.rotate(head, -0.8F, -0.15F, 0.7F);
            animator.rotate(shoulderleft, -0.5F, 0, 0);
            animator.rotate(armleft, -0.5F, 0, 0);
            animator.endPhase();
            animator.startPhase(3);
            animator.rotate(neck, 0.9F, -0.4F, 0);
            animator.rotate(head, -0.8F, -0.25F, 0.7F);
            animator.rotate(shoulderleft, -2.6F, 0, 0);
            animator.rotate(armleft, 0.5F, 0, 0);
            animator.endPhase();
            animator.resetPhase(5);
        }
        
        ((EntityHypsilophodon) entity).tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
