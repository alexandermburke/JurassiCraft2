package net.ilexiconn.jurassicraft.entity;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.entity.multipart.IEntityMultiPart;
import net.ilexiconn.llibrary.json.JsonHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityDinosaur extends EntityCreature implements IEntityMultiPart, IEntityAdditionalSpawnData
{
    private EntityPart[] parts;
    protected Creature creature;

    private boolean gender;

    public EntityDinosaur(World world)
    {
        super(world);
        parts = JsonHelper.parseHitboxList(this, creature.getHitboxList());
        gender = rand.nextBoolean();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        creature = JCEntityRegistry.getCreatureFromClass(getClass());
    }

    public Creature getCreature()
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