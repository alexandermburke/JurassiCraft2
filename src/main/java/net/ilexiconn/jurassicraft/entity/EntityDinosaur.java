package net.ilexiconn.jurassicraft.entity;

import net.ilexiconn.jurassicraft.entity.json.JsonCreature;
import net.ilexiconn.jurassicraft.json.JsonHelper;
import net.ilexiconn.llibrary.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.entity.multipart.IEntityMultiPart;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityDinosaur extends EntityLiving implements IEntityMultiPart
{
    private EntityPart[] parts;
    private JsonCreature creature;
    
    public EntityDinosaur(World world)
    {
        super(world);
        creature = JCEntityRegistry.getCreatureFromClass(getClass());
        parts = JsonHelper.parseHitboxList(this, creature.getHitboxList());
    }

    public JsonCreature getCreature()
    {
        return creature;
    }
    
    public EntityPart[] getParts()
    {
        return parts;
    }
}
