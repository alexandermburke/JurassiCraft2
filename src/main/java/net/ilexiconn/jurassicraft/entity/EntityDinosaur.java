package net.ilexiconn.jurassicraft.entity;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.ilexiconn.jurassicraft.json.JsonCreature;
import net.ilexiconn.llibrary.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.entity.multipart.IEntityMultiPart;
import net.ilexiconn.llibrary.json.JsonHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityDinosaur extends EntityLiving implements IEntityMultiPart, IEntityAdditionalSpawnData
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

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setBoolean("Gender", gender);
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        gender = nbt.getBoolean("Gender");
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        buffer.writeBoolean(gender);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        gender = additionalData.readBoolean();
    }
}