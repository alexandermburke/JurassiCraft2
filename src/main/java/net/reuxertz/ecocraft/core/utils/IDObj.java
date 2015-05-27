package net.reuxertz.ecocraft.core.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.reuxertz.ecocraft.core.EcoCraft;

//Constructors
public class IDObj
{
    //Static Functions
    public static EntityCreature GetEntity(World w, String id)
    {
        for (Object e: w.getLoadedEntityList()) {

            if (!EntityCreature.class.isInstance(e))
                continue;

            if (IDObj.GetID((EntityCreature)e, true).equalsIgnoreCase(id))
                return (EntityCreature)e;
        }

        return null;
    }
    public static String GetID(Entity e, boolean validate)
    {
        IDObj idobj = IDObj.ValidateIDObj(e, validate);

        if (idobj == null)
            return null;

        return idobj.GetID();
    }
    public static IDObj GenerateNewID(int length)
    {
        String s = "";
        for (int i = 0; i < length; i++) {
            int si = EcoCraft.RND.nextInt(IDObj.IDAlphabet.length());
            String ss = IDObj.IDAlphabet.substring(si, si + 1);
            s += ss;
        }

        return new IDObj(s);
    }
    public static IDObj InitializeNewID(Entity e)
    {
        NBTTagCompound nbt = e.getEntityData();
        IDObj idobj = IDObj.GenerateNewID(IDLength);
        IDObj.WriteToNBT(nbt, idobj);

        return idobj;
    }
    public static IDObj ValidateIDObj(Entity e, boolean createNew)
    {
        if (!e.getEntityData().hasKey((IdField))) {
            if (createNew)
                return IDObj.InitializeNewID(e);
            else
                return null;
        }

        return new IDObj(e.getEntityData());
    }

    //Fields
    public static final int IDLength = 25;
    public static final String IDAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final String IdField = "ainow_id";

    //NBT Functions
    public static void WriteToNBT(NBTTagCompound nbt, IDObj inst)
    {
        nbt.setString(IdField, inst.GetID());
    }
    public void WriteToNBT(NBTTagCompound nbt)
    {
        IDObj.WriteToNBT(nbt, this);
    }
    public static IDObj ReadIDFromNBT(NBTTagCompound nbt)
    {
        if (!nbt.hasKey(IdField))
            return null;

        return new IDObj(nbt);
    }

    protected String _id;

    public String GetID()
    {
        return this._id;
    }
    protected IDObj(NBTTagCompound nbt)
    {
        this._id = nbt.getString(IdField);
    }
    protected IDObj(String id)
    {
        this._id = id;
    }

}
