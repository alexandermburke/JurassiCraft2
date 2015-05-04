package net.ilexiconn.jurassicraft.entity;

import net.ilexiconn.jurassicraft.json.JsonCreature;
import net.ilexiconn.llibrary.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.entity.multipart.IEntityMultiPart;
import net.ilexiconn.llibrary.json.JsonHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityDinosaur extends EntityLiving implements IEntityMultiPart
{
    private EntityPart[] parts;
    private JsonCreature creature;

    private boolean gender;
    
    public EntityDinosaur(World world)
    {
        super(world);
        creature = JCEntityRegistry.getCreatureFromClass(getClass());
        parts = JsonHelper.parseHitboxList(this, creature.getHitboxList());
        gender = rand.nextBoolean();
    }

    public JsonCreature getCreature()
    {
        return creature;
    }

    public EntityPart[] getParts()
    {
        return parts;
    }

    public boolean isMale()
    {
        return gender;
    }
}