package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

import java.util.List;
import java.util.UUID;

public abstract class HelicopterRidableModule extends HelicopterModule
{
    protected HelicopterRidableModule(String id)
    {
        super(id);
    }

    @Override
    public boolean onClicked(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {
        UUID entityID = UUID.fromString(m.getModuleData(this).getString("entityID"));
        EntityHelicopterSeat seat = getSeatFromID(m.getHelicopter().worldObj, entityID);
        player.mountEntity(seat);
        return true;
    }

    public static EntityHelicopterSeat getSeatFromID(World worldObj, final UUID id)
    {
        List<EntityHelicopterSeat> list = worldObj.getEntities(EntityHelicopterSeat.class, new Predicate<Entity>()
        {
            @Override
            public boolean apply(Entity input)
            {
                System.out.println(">> "+input);
                return input.getUniqueID().equals(id);
            }
        });
        if (list.isEmpty())
        {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void onAdded(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {
        EntityHelicopterBase helicopter = m.getHelicopter();
        if (!m.getHelicopter().worldObj.isRemote)
        {
            if (!m.getModuleData(this).hasKey("entityID"))
            {
                EntityHelicopterSeat pilotSeat = new EntityHelicopterSeat(getDistanceFromCenter(), m.getPosition().ordinal(), m.getHelicopter(), shouldRiderSit());
                pilotSeat.setPosition(helicopter.posX, helicopter.posY, helicopter.posZ);
                helicopter.worldObj.spawnEntityInWorld(pilotSeat);
                String id = pilotSeat.getUniqueID().toString();
                m.getModuleData(this).setString("entityID", id);
                System.out.println(id);
            }
        }
    }

    public EntityHelicopterSeat getEntity(HelicopterModuleSpot spot)
    {
        String id = spot.getModuleData(this).getString("entityID");
        if(id.isEmpty())
            return null;
        //System.out.println(id);
        UUID entityID = UUID.fromString(id);
        EntityHelicopterSeat entity = getSeatFromID(spot.getHelicopter().worldObj, entityID);
      //  System.out.println(entity);
        return entity;
    }

    protected abstract float getDistanceFromCenter();

    protected abstract boolean shouldRiderSit();

    @Override
    public void onRemoved(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {

    }
}
