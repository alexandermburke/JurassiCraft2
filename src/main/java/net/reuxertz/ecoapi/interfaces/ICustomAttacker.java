package net.reuxertz.ecoapi.interfaces;

import net.minecraft.entity.Entity;

public interface ICustomAttacker //TODO implementation
{
    void attack(Entity entity);

    boolean shouldAttack(Entity entity);
}
