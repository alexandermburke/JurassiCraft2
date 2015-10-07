package org.jurassicraft.common.entity;

import net.minecraft.world.World;
import org.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityLeaellynasaura extends EntityDinosaurDefensiveHerbivore  //implements IEntityAICreature, IHerbivore
{
    public EntityLeaellynasaura(World world)
    {
        super(world);
    }

    @Override
    public int getTailBoxCount()
    {
        return 6;
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);

        super.onUpdate();
    }
}
