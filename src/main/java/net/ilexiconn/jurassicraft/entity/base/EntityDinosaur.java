package net.ilexiconn.jurassicraft.entity.base;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.jurassicraft.dinosaur.Dinosaur;
import net.ilexiconn.llibrary.entity.multipart.EntityPart;
import net.ilexiconn.llibrary.entity.multipart.IEntityMultiPart;
import net.ilexiconn.llibrary.json.JsonHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.reuxertz.civnow.entity.EntityAICreature;

public class EntityDinosaur extends EntityAICreature implements IEntityMultiPart, IEntityAdditionalSpawnData
{
    protected EntityPart[] parts;
    protected Dinosaur dinosaur;

    protected boolean gender;

    public EntityDinosaur(World world)
    {
        super(world);
        parts = JsonHelper.parseHitboxList(this, dinosaur.getHitBoxList());
        gender = rand.nextBoolean();
    }

    public void entityInit()
    {
        super.entityInit();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        dinosaur = JCEntityRegistry.getDinosaurByClass(getClass());
    }

    public Dinosaur getDinosaur()
    {
        return dinosaur;
    }

    public EntityPart[] getParts()
    {
        return parts;
    }

    public void onUpdate()
    {
        super.onUpdate();
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