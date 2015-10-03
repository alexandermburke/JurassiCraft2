package org.jurassicraft.common.vehicles.helicopter.modules;

import com.google.common.base.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class HelicopterSeat extends HelicopterModule
{
    protected HelicopterSeat()
    {
        super("helicopter_seat");
    }

    @Override
    protected Collection<Class<? extends HelicopterModule>> createSupportedModuleList()
    {
        return Collections.emptyList();
    }

    @Override
    public float getBaseRotationAngle()
    {
        return 0;
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
        List list = worldObj.getEntities(EntityHelicopterSeat.class, new Predicate()
        {
            @Override
            public boolean apply(Object input)
            {
                if (input instanceof EntityHelicopterSeat)
                {
                    EntityHelicopterSeat helicopter = (EntityHelicopterSeat) input;
                    return helicopter.getUniqueID().equals(id);
                }
                return false;
            }
        });
        if (list.isEmpty())
            return null;
        return (EntityHelicopterSeat) list.get(0);
    }

    @Override
    public void onAdded(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {
        EntityHelicopterBase helicopter = m.getHelicopter();
        if(!m.getHelicopter().worldObj.isRemote)
        {
            if(!m.getModuleData(this).hasKey("entityID"))
            {
                EntityHelicopterSeat pilotSeat = new EntityHelicopterSeat(1.3f, m.getPosition().ordinal(), m.getHelicopter());
                pilotSeat.setPosition(helicopter.posX, helicopter.posY, helicopter.posZ);
                helicopter.worldObj.spawnEntityInWorld(pilotSeat);
                m.getModuleData(this).setString("entityID", pilotSeat.getUniqueID().toString());
            }
        }
    }

    @Override
    public void onRemoved(HelicopterModuleSpot m, EntityPlayer player, Vec3 vec)
    {

    }
}
