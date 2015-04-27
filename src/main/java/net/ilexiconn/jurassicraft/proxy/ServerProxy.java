package net.ilexiconn.jurassicraft.proxy;

import net.ilexiconn.jurassicraft.entity.json.JsonCreature;
import net.minecraft.entity.Entity;

public class ServerProxy
{
    public void init()
    {

    }

    public void registerEntityRenderer(Class<? extends Entity> clazz, JsonCreature creature)
    {

    }

    public float getPartialTick()
    {
        return 1f;
    }
}
