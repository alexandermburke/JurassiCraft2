package org.jurassicraft.client.model.animation;

import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.common.animation.Animator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.entity.EntityAnkylosaurus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;

@SideOnly(Side.CLIENT)
public class AnimationAnkylosaurus extends DinosaurAnimator
{
    public AnimationAnkylosaurus()
    {
        super(JCEntityRegistry.anklyosaurus);
    }

    @Override
    protected void performMowzieLandAnimations(ModelDinosaur model, float f, float f1, float rotation, float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur entity)
    {
        Animator animator = model.animator;

        MowzieModelRenderer head = model.getCube("head ");
        MowzieModelRenderer headback = model.getCube("head back");

        MowzieModelRenderer neck1 = model.getCube("neck 1");

        MowzieModelRenderer waist = model.getCube("Body");
        MowzieModelRenderer chest = model.getCube("body 2");

        MowzieModelRenderer tail1 = model.getCube("tail 1");
        MowzieModelRenderer tail2 = model.getCube("tail 2");
        MowzieModelRenderer tail3 = model.getCube("tail 3");
        MowzieModelRenderer tail4 = model.getCube("tail 4");
        MowzieModelRenderer tail5 = model.getCube("tail end");

        MowzieModelRenderer[] tail = new MowzieModelRenderer[] { tail5, tail4, tail3, tail2, tail1 };

        MowzieModelRenderer legleftthigh = model.getCube("leg left 1");
        MowzieModelRenderer legleftcalf = model.getCube("leg left 2");
        MowzieModelRenderer legleftfoot = model.getCube("leg left back 3");

        MowzieModelRenderer legrightthigh = model.getCube("leg right 1");
        MowzieModelRenderer legrightcalf = model.getCube("leg right 2");
        MowzieModelRenderer legrightfoot = model.getCube("leg right back 3");

        MowzieModelRenderer armleftthigh = model.getCube("arm left 2");
        MowzieModelRenderer armleftcalf = model.getCube("arm left 1");
        MowzieModelRenderer armleftfoot = model.getCube("leg left front 3");

        MowzieModelRenderer armrightthigh = model.getCube("arm right 2");
        MowzieModelRenderer armrightcalf = model.getCube("arm right 1");
        MowzieModelRenderer armrightfoot = model.getCube("leg right front 3");

        float globalSpeed = 0.3F;
        float globalDegree = 0.8F;
        float height = 1.2F;
        float frontOffset = 0.84F;

        model.bob(waist, 2 * globalSpeed, height, false, f, f1);
        // bob(legleftthigh, 2 * globalSpeed, height, false, f, f1);
        // bob(legrightthigh, 2 * globalSpeed, height, false, f, f1);
        model.walk(waist, 2 * globalSpeed, 0.1F * height, true, -1.5F, 0.05F, f, f1);
        model.walk(chest, 2 * globalSpeed, 0.07F * height, true, -2F, 0.05F, f, f1);
        // model.walk(tail1, 2 * globalSpeed, 0.1F * height, false, -1.5F, 0.05F, f, f1);
        model.walk(neck1, 2 * globalSpeed, 0.03F * height, false, 0.5F, -0.1F, f, f1);
        model.walk(headback, 2 * globalSpeed, 0.03F * height, true, 1F, 0F, f, f1);
        model.walk(head, 2 * globalSpeed, 0.15F * height, true, 1.5F, 0F, f, f1);

        model.walk(legleftthigh, 1F * globalSpeed, 0.7F * globalDegree, false, 0F, -0.4F, f, f1);
        model.walk(legleftcalf, 1F * globalSpeed, 0.6F * globalDegree, true, 1F, 0.5F, f, f1);
        model.walk(legleftfoot, 1F * globalSpeed, 0.6F * globalDegree, false, -1.5F, 0.85F, f, f1);

        model.walk(legrightthigh, 1F * globalSpeed, 0.7F * globalDegree, true, 0F, -0.4F, f, f1);
        model.walk(legrightcalf, 1F * globalSpeed, 0.6F * globalDegree, false, 1F, 0.5F, f, f1);
        model.walk(legrightfoot, 1F * globalSpeed, 0.6F * globalDegree, true, -1.5F, 0.85F, f, f1);

        model.walk(armleftthigh, 1F * globalSpeed, 0.7F * globalDegree, true, frontOffset + 0F, -0.2F, f, f1);
        model.walk(armleftcalf, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 1F, -0.2F, f, f1);
        model.walk(armleftfoot, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 2F, 0.8F, f, f1);

        model.walk(armrightthigh, 1F * globalSpeed, 0.7F * globalDegree, false, frontOffset + 0F, -0.2F, f, f1);
        model.walk(armrightcalf, 1F * globalSpeed, 0.6F * globalDegree, false, frontOffset + 1F, -0.2F, f, f1);
        model.walk(armrightfoot, 1F * globalSpeed, 0.6F * globalDegree, true, frontOffset + 2F, 0.8F, f, f1);

        model.chainWave(tail, 2 * globalSpeed, -0.12F, 2, f, f1);
        model.chainSwing(tail, 1 * globalSpeed, 0.3F, 3, f, f1);

        // Idle

        int ticksExisted = entity.ticksExisted;

        model.walk(neck1, 0.1F, 0.05F, false, -1F, 0F, ticksExisted, 1F);
        model.walk(headback, 0.1F, 0.05F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(waist, 0.1F, 0.025F, false, 0F, 0F, ticksExisted, 1F);
        model.walk(legleftthigh, 0.1F, 0.025F, true, 0F, 0F, ticksExisted, 1F);
        model.walk(legrightthigh, 0.1F, 0.025F, true, 0F, 0F, ticksExisted, 1F);

        float inverseKinematicsConstant = 0.4F;
        model.walk(armleftthigh, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        model.walk(armleftcalf, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticksExisted, 1F);
        model.walk(armleftfoot, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        model.walk(armrightthigh, 0.1F, 0.1F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        model.walk(armrightcalf, 0.1F, 0.3F * inverseKinematicsConstant, true, 0F, 0F, ticksExisted, 1F);
        model.walk(armrightfoot, 0.1F, 0.175F * inverseKinematicsConstant, false, 0F, 0F, ticksExisted, 1F);
        armleftthigh.rotationPointZ -= 0.5 * inverseKinematicsConstant * Math.cos(ticksExisted * 0.1F);
        armrightthigh.rotationPointZ -= 0.5 * inverseKinematicsConstant * Math.cos(ticksExisted * 0.1F);

        model.chainSwing(tail, 0.1F, 0.05F, 2, ticksExisted, 1F);
        model.chainWave(tail, 0.1F, -0.05F, 1, ticksExisted, 1F);

        ((EntityAnkylosaurus) entity).tailBuffer.applyChainSwingBuffer(tail);
    }
}
