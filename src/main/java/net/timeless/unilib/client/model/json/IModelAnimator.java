package net.timeless.unilib.client.model.json;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Interface for animating Tabula models.
 * <p/>
 * This can be used for Living animations.
 *
 * @author Gegy1000
 *         TAKEN FROM LLIBRARY
 */
@SideOnly(Side.CLIENT)
public interface IModelAnimator
{
    /**
     * Set the rotation angles for the shapes. Called every tick.
     */
    void setRotationAngles(ModelJson model, float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, float partialTicks, Entity entity);
}
