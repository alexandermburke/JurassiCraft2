package net.ilexiconn.jurassicraft.entity;

import net.ilexiconn.jurassicraft.json.JsonHelper;
import net.ilexiconn.llibrary.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.entity.multipart.IEntityMultiPart;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityTest extends EntityLiving implements IEntityMultiPart
{
    public EntityPart[] parts;

    public EntityTest(World world)
    {
        super(world);
        parts = JsonHelper.parseHitboxList(this, JCEntityRegistry.getCreatureFromClass(getClass()).getHitboxList());
    }

    public EntityPart[] getParts()
    {
        return parts;
    }
}
