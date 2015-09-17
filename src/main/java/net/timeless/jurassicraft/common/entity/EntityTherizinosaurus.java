package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.animationapi.client.AnimID;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntityTherizinosaurus extends EntityDinosaurDefensiveHerbivore // implements IEntityAICreature, IHerbivore
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntityTherizinosaurus(World world)
    {
        super(world);
    }
    
    @Override
    public void onUpdate()
    {
        tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
//        // DEBUG
//        System.out.println("onUpdate for entity "+getEntityId()+" which has anim id = "+getAnimID());
        if (ticksExisted%200 == 0)
        {
        	System.out.println("Changing the animation");
        	AnimationAPI.sendAnimPacket(this, AnimID.ATTACKING);
        }
        super.onUpdate();
    }
}
