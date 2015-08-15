package net.reuxertz.ecoapi.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

//Constructors
public class IdObject
{
    //Fields
    public static final int idLength = 25;
    public static final String idAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final String idField = "ainow_id";
    private static Random random = new Random();
    protected String id;

    protected IdObject(NBTTagCompound nbt)
    {
        id = nbt.getString(idField);
    }

    protected IdObject(String id)
    {
        this.id = id;
    }

    //Static Functions
    public static EntityCreature getEntity(World w, String id)
    {
        for (Object e : w.getLoadedEntityList())
        {
            if (!EntityCreature.class.isInstance(e))
                continue;

            if (IdObject.getId((EntityCreature) e, true).equalsIgnoreCase(id))
                return (EntityCreature) e;
        }

        return null;
    }

    public static String getId(Entity e, boolean validate)
    {
        IdObject idobj = IdObject.validateIdObj(e, validate);

        if (idobj == null)
            return null;

        return idobj.getId();
    }

    public static String generateNewId(int length)
    {
        String s = "";
        for (int i = 0; i < length; i++)
        {
            int si = random.nextInt(IdObject.idAlphabet.length());
            String ss = IdObject.idAlphabet.substring(si, si + 1);
            s += ss;
        }

        return s;
    }

    public static IdObject initializeNewId(Entity e)
    {
        NBTTagCompound nbt = e.getEntityData();
        IdObject idobj = new IdObject(IdObject.generateNewId(idLength));
        IdObject.writeIDObjToNbt(nbt, idobj);

        return idobj;
    }

    public static IdObject validateIdObj(Entity e, boolean createNew)
    {
        if (!e.getEntityData().hasKey((idField)))
        {
            if (createNew)
                return IdObject.initializeNewId(e);
            else
                return null;
        }

        return new IdObject(e.getEntityData());
    }

    //NBT Functions
    public static boolean hasNBTKey(NBTTagCompound nbt)
    {
        return nbt.hasKey(idField);
    }

    public static void writeIDObjToNbt(NBTTagCompound nbt, IdObject inst)
    {
        nbt.setString(idField, inst.getId());
    }

    public static void writeIDToNbt(NBTTagCompound nbt, String id)
    {
        nbt.setString(idField, id);
    }

    public static String readIDFromNbt(NBTTagCompound nbt)
    {
        if (!nbt.hasKey(idField))
            return null;

        return (new IdObject(nbt)).getId();
    }

    public static IdObject readIDObjFromNbt(NBTTagCompound nbt)
    {
        if (!nbt.hasKey(idField))
            return null;

        return new IdObject(nbt);
    }

    public void writeToNbt(NBTTagCompound nbt)
    {
        IdObject.writeIDObjToNbt(nbt, this);
    }

    public String getId()
    {
        return id;
    }

}
