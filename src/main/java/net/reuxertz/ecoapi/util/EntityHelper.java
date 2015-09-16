package net.reuxertz.ecoapi.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;
import java.util.UUID;

public class EntityHelper
{
    public static List<Entity> getEntitiesWithinDistance(Entity e, double xz, double y)
    {
        return e.worldObj.getEntitiesWithinAABBExcludingEntity(e, AxisAlignedBB.fromBounds(e.posX - xz, e.posY - y, e.posZ - xz, e.posX + xz, e.posY + y, e.posZ + xz));
    }

    public static void setEntitySpeedByFactor(EntityLivingBase e, float factor)
    {
        factor *= factor * factor * factor;
        //double nv = SharedMonsterAttributes.movementSpeed.getDefaultValue() * (factor);
        //e.getEntityAttribute(SharedMonsterAttributes.movementSpeed)

        //SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2

        AttributeModifier attributemodifier = new AttributeModifier(UUID.randomUUID(), "encumbered", factor - 1, 2);
        e.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(attributemodifier);

        /*
        .setBaseValue(nv);
        e.moveForward = factor;
        e.moveStrafing = factor;
        Potion p = new Potion(2, new ResourceLocation("slowness"), true, 5926017)).setPotionName("potion.moveSlowdown").setIconIndex(1, 0)
        .registerPotionAttributeModifier(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2);
        e.setAIMoveSpeed(factor);
        e.addPotionEffect(new PotionEffect());
        */
    }
    public static void resetEntitySpeed(EntityLivingBase e)
    {
        e.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
                .setBaseValue(SharedMonsterAttributes.movementSpeed.getDefaultValue());
    }
}
