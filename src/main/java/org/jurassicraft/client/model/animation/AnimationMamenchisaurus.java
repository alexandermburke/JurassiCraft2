package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.common.animation.Animator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityMamenchisaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationMamenchisaurus extends DinosaurAnimator
{
    public AnimationMamenchisaurus()
    {
        super(JCEntityRegistry.mamenchisaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur parEntity)
    {
        EntityMamenchisaurus entity = (EntityMamenchisaurus) parEntity;
        Animator animator = model.animator;

        MowzieModelRenderer head = model.getCube("Head");

        MowzieModelRenderer neck1 = model.getCube("neck1");
        MowzieModelRenderer neck2 = model.getCube("neck2");
        MowzieModelRenderer neck3 = model.getCube("neck3");
        MowzieModelRenderer neck4 = model.getCube("neck4");
        MowzieModelRenderer neck5 = model.getCube("neck5");
        MowzieModelRenderer neck6 = model.getCube("neck6");

        MowzieModelRenderer waist = model.getCube("hips");
        MowzieModelRenderer tail1 = model.getCube("tail1");
        MowzieModelRenderer tail2 = model.getCube("tail2");
        MowzieModelRenderer tail3 = model.getCube("tail3");
        MowzieModelRenderer tail4 = model.getCube("tail4");
        MowzieModelRenderer tail5 = model.getCube("tail5");

        MowzieModelRenderer thighLeft = model.getCube("top leg left");
        MowzieModelRenderer thighRight = model.getCube("top leg right");

        MowzieModelRenderer lowerThighLeft = model.getCube("bottom front left leg");
        MowzieModelRenderer lowerThighRight = model.getCube("bottom front right leg");

        MowzieModelRenderer footLeft = model.getCube("left back foot");
        MowzieModelRenderer footRight = model.getCube("left right foot");

        MowzieModelRenderer armRight = model.getCube("front right top leg");
        MowzieModelRenderer armLeft = model.getCube("front left top leg");

        MowzieModelRenderer lowerArmRight = model.getCube("bottom front right leg");
        MowzieModelRenderer lowerArmLeft = model.getCube("bottom front left leg");

        MowzieModelRenderer handRight = model.getCube("front right foot");
        MowzieModelRenderer handLeft = model.getCube("front left foot");

        MowzieModelRenderer stomach = model.getCube("Stomach");
        MowzieModelRenderer body = model.getCube("body");

        MowzieModelRenderer[] neckParts = new MowzieModelRenderer[] { head, neck6, neck5, neck4, neck3, neck2, neck1, body };
        MowzieModelRenderer[] tailParts = new MowzieModelRenderer[] { tail5, tail4, tail3, tail2, tail1 };

        float globalSpeed = 0.5F;
        float globalHeight = 0.5F;
        float globalDegree = 0.5F;

        float frontOffset = 1.0F;

        model.bob(waist, globalSpeed * 1.0F, globalHeight * 4.0F, false, f, f1);
        model.bob(thighLeft, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);
        model.bob(thighRight, globalSpeed * 1.0F, globalHeight * 1.0F, false, f, f1);

        model.chainWave(tailParts, globalSpeed * 1.0F, globalHeight * 0.25F, 3, f, f1);
        model.chainSwing(tailParts, globalSpeed * 0.5F, globalHeight * 0.5F, 3, f, f1);
        model.chainWave(neckParts, globalSpeed * 1.0F, globalHeight * 0.125F, -4, f, f1);

        model.walk(thighLeft, 1F * globalSpeed, 0.7F * globalDegree, false, 0F, -0.4F, f, f1);
        model.walk(lowerThighLeft, 1F * globalSpeed, 0.6F * globalDegree, true, 1F, 0.5F, f, f1);
        model.walk(footLeft, 1F * globalSpeed, 0.6F * globalDegree, false, -1.5F, 0.85F, f, f1);

        model.walk(thighRight, 1F * globalSpeed, 0.7F * globalDegree, true, 0F, -0.4F, f, f1);
        model.walk(lowerThighRight, 1F * globalSpeed, 0.6F * globalDegree, false, 1F, 0.5F, f, f1);
        model.walk(footRight, 1F * globalSpeed, 0.6F * globalDegree, true, -1.5F, 0.85F, f, f1);

        model.walk(armLeft, 1F * globalSpeed, 0.7F * globalDegree, true, frontOffset + 0F, -0.2F, f, f1);
        model.walk(lowerArmLeft, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 1F, -0.2F, f, f1);
        model.walk(handLeft, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 2F, 0.8F, f, f1);

        model.walk(armRight, 1F * globalSpeed, 0.7F * globalDegree, false, frontOffset + 0F, -0.2F, f, f1);
        model.walk(lowerArmRight, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 1F, -0.2F, f, f1);
        model.walk(handRight, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 2F, 0.8F, f, f1);

        int ticksExisted = parEntity.ticksExisted;

        model.chainWave(tailParts, globalSpeed * 0.25F, globalHeight * 1.0F, 3, ticksExisted, 0.1F);
        model.chainWave(neckParts, globalSpeed * 0.25F, globalHeight * 0.25F, -4, ticksExisted, 0.1F);

        parEntity.tailBuffer.applyChainSwingBuffer(tailParts);
    }
}
