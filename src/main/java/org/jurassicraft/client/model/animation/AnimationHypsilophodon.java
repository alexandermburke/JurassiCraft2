package org.jurassicraft.client.model.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.Animator;
import net.timeless.animationapi.client.DinosaurAnimator;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.DinosaurHypsilophodon;
import org.jurassicraft.common.entity.EntityHypsilophodon;
import org.jurassicraft.common.entity.base.EntityDinosaur;

@SideOnly(Side.CLIENT)
public class AnimationHypsilophodon extends DinosaurAnimator
{
    public AnimationHypsilophodon()
    {
        super(new DinosaurHypsilophodon());
    }
    
    @Override
    protected void performMowzieAnimations(ModelDinosaur parModel, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        Animator animator = parModel.animator;

        int ticksExisted = parEntity.ticksExisted;

        float scaleFactor = 0.5F;
        float height = 12F * f1;

        MowzieModelRenderer upperlegright = parModel.getCube("Leg UPPER RIGHT");
        MowzieModelRenderer upperlegleft = parModel.getCube("Leg UPPER LEFT");

        MowzieModelRenderer midlegright = parModel.getCube("Leg MIDDLE RIGHT");
        MowzieModelRenderer midlegleft = parModel.getCube("Leg MIDDLE LEFT");

        MowzieModelRenderer lowerlegright = parModel.getCube("Leg LOWER RIGHT");
        MowzieModelRenderer lowerlegleft = parModel.getCube("Leg LOWER LEFT");

        MowzieModelRenderer feetright = parModel.getCube("Foot RIGHT");
        MowzieModelRenderer feetleft = parModel.getCube("Foot LEFT");

        MowzieModelRenderer head = parModel.getCube("Head ");
        MowzieModelRenderer neck = parModel.getCube("Neck BASE");
        MowzieModelRenderer neck2 = parModel.getCube("Neck 2");
        MowzieModelRenderer neck3 = parModel.getCube("Neck 3");

        MowzieModelRenderer body2 = parModel.getCube("Body REAR");
        MowzieModelRenderer body1 = parModel.getCube("Body FRONT");

        MowzieModelRenderer tail1 = parModel.getCube("Tail BASE");
        MowzieModelRenderer tail2 = parModel.getCube("Tail 2");
        MowzieModelRenderer tail3 = parModel.getCube("Tail 3");
        MowzieModelRenderer tail4 = parModel.getCube("Tail 4");
        MowzieModelRenderer tail5 = parModel.getCube("Tail 5");
        MowzieModelRenderer tail6 = parModel.getCube("Tail 6");

        MowzieModelRenderer shoulderright = parModel.getCube("Arm UPPER RIGHT");
        MowzieModelRenderer shoulderleft = parModel.getCube("Arm UPPER LEFT");

        MowzieModelRenderer armright = parModel.getCube("Arm MIDDLE RIGHT");
        MowzieModelRenderer armleft = parModel.getCube("Arm MIDDLE LEFT");

        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[]{tail6, tail5, tail4, tail3, tail2, tail1};

        parModel.bob(body2, 0.5F * scaleFactor, height, true, f, f1);
        parModel.bob(upperlegright, 0.5F * scaleFactor, height, true, f, f1);
        parModel.bob(upperlegleft, 0.5F * scaleFactor, height, true, f, f1);

        parModel.walk(upperlegleft, 1F * scaleFactor, 0.75F, true, 1F, 0.25F, f, f1);
        parModel.walk(upperlegright, 1F * scaleFactor, 0.75F, true, 0.5F, 0.25F, f, f1);
        parModel.walk(midlegleft, 1F * scaleFactor, 0.75F, false, 1.5F, 0F, f, f1);
        parModel.walk(midlegright, 1F * scaleFactor, 0.75F, false, 1F, 0F, f, f1);
        parModel.walk(lowerlegright, 1F * scaleFactor, 0.75F, true, 0.5F, 0F, f, f1);
        parModel.walk(lowerlegleft, 1F * scaleFactor, 0.75F, true, 1F, 0F, f, f1);
        parModel.walk(feetleft, 1F * scaleFactor, 0.5F, true, 1F, 0.75F, f, f1);
        parModel.walk(feetright, 1F * scaleFactor, 0.5F, true, 0.5F, 0.75F, f, f1);

        parModel.walk(body2, 1F * scaleFactor, 0.3F, false, 0.5F, 0F, f, f1);
        parModel.walk(body1, 1F * scaleFactor, 0.5F, true, 1.0F, 0F, f, f1);
        parModel.walk(neck, 1F * scaleFactor, 0.3F, true, 0.25F, 0.3F, f, f1);
        parModel.walk(head, 1F * scaleFactor, 0.3F, false, 0.25F, -0.3F, f, f1);

        parModel.walk(shoulderright, 1 * scaleFactor, 0.3F, true, 1, 0.2F, f, f1);
        parModel.walk(shoulderleft, 1 * scaleFactor, 0.3F, true, 1, 0.2F, f, f1);
        parModel.walk(armright, 1 * scaleFactor, 0.3F, false, 1, -0.2F, f, f1);
        parModel.walk(armleft, 1 * scaleFactor, 0.3F, false, 1, -0.2F, f, f1);

        parModel.faceTarget(head, 1, rotationYaw, rotationPitch);

        // Idling
        parModel.chainWave(tailParts, 0.2F, -0.05F, 2, ticksExisted, 1F);
        parModel.walk(neck, 0.2F, 0.1F, false, -1F, 0F, ticksExisted, 1F);
        parModel.walk(head, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(body1, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(body2, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(shoulderright, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(shoulderleft, 0.2F, 0.1F, true, 0F, 0F, ticksExisted, 1F);
        parModel.walk(armright, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);
        parModel.walk(armleft, 0.2F, 0.1F, false, 0F, 0F, ticksExisted, 1F);

        parModel.chainWave(tailParts, 1F * scaleFactor, 0.15F, 2, f, f1);

//        EntityHypsilophodon hysilophodon = (EntityHypsilophodon) parEntity;
//
//        if (hysilophodon.getAnimID() == AnimID.SCRATCHING)
//        {
//            animator.setAnim(AnimID.SCRATCHING);
//            animator.startPhase(5);
//            animator.rotate(neck, 0.9F, -0.4F, 0);
//            animator.rotate(head, -0.8F, -0.25F, 0.7F);
//            animator.rotate(shoulderleft, -2.6F, 0, 0);
//            animator.rotate(armleft, 0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(2);
//            animator.rotate(neck, 0.9F, -0.35F, 0);
//            animator.rotate(head, -0.8F, -0.15F, 0.7F);
//            animator.rotate(shoulderleft, -0.5F, 0, 0);
//            animator.rotate(armleft, -0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(3);
//            animator.rotate(neck, 0.9F, -0.4F, 0);
//            animator.rotate(head, -0.8F, -0.25F, 0.7F);
//            animator.rotate(shoulderleft, -2.6F, 0, 0);
//            animator.rotate(armleft, 0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(2);
//            animator.rotate(neck, 0.9F, -0.35F, 0);
//            animator.rotate(head, -0.8F, -0.15F, 0.7F);
//            animator.rotate(shoulderleft, -0.5F, 0, 0);
//            animator.rotate(armleft, -0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(3);
//            animator.rotate(neck, 0.9F, -0.4F, 0);
//            animator.rotate(head, -0.8F, -0.25F, 0.7F);
//            animator.rotate(shoulderleft, -2.6F, 0, 0);
//            animator.rotate(armleft, 0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(2);
//            animator.rotate(neck, 0.9F, -0.35F, 0);
//            animator.rotate(head, -0.8F, -0.15F, 0.7F);
//            animator.rotate(shoulderleft, -0.5F, 0, 0);
//            animator.rotate(armleft, -0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(3);
//            animator.rotate(neck, 0.9F, -0.4F, 0);
//            animator.rotate(head, -0.8F, -0.25F, 0.7F);
//            animator.rotate(shoulderleft, -2.6F, 0, 0);
//            animator.rotate(armleft, 0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(2);
//            animator.rotate(neck, 0.9F, -0.35F, 0);
//            animator.rotate(head, -0.8F, -0.15F, 0.7F);
//            animator.rotate(shoulderleft, -0.5F, 0, 0);
//            animator.rotate(armleft, -0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(3);
//            animator.rotate(neck, 0.9F, -0.4F, 0);
//            animator.rotate(head, -0.8F, -0.25F, 0.7F);
//            animator.rotate(shoulderleft, -2.6F, 0, 0);
//            animator.rotate(armleft, 0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(2);
//            animator.rotate(neck, 0.9F, -0.35F, 0);
//            animator.rotate(head, -0.8F, -0.15F, 0.7F);
//            animator.rotate(shoulderleft, -0.5F, 0, 0);
//            animator.rotate(armleft, -0.5F, 0, 0);
//            animator.endPhase();
//            animator.startPhase(3);
//            animator.rotate(neck, 0.9F, -0.4F, 0);
//            animator.rotate(head, -0.8F, -0.25F, 0.7F);
//            animator.rotate(shoulderleft, -2.6F, 0, 0);
//            animator.rotate(armleft, 0.5F, 0, 0);
//            animator.endPhase();
//            animator.resetPhase(5);
//        }

        ((EntityHypsilophodon) parEntity).tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
