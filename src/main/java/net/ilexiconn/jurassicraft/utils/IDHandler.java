package net.ilexiconn.jurassicraft.utils;


import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class IDHandler
{
    private static Random random = new Random();
    //Fields
    public static final int MinLength = 6, MaxLength = 20;
    public static final String IDAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$&?";
    public static final String IdField = "ainow_id";

    //ID Functions
    public static String GenerateNewID(int length)
    {
        String s = "";
        for (int i = 0; i < length; i++)
            s += IDHandler.IDAlphabet.substring(random.nextInt(IDHandler.IDAlphabet.length()), 1);

        return s;
    }

    //NBT Functions
    public static void AppendIDToTag(NBTTagCompound nbt, Instance inst)
    {
        nbt.setString(IdField, inst.GetID());
    }

    public Instance ReadIDFromTag(NBTTagCompound nbt)
    {
        return new Instance(nbt);
    }

    //Constructors
    public class Instance
    {
        protected String _id;

        public String GetID()
        {
            return this._id;
        }

        public Instance(NBTTagCompound nbt)
        {
            this._id = nbt.getString(IdField);
        }

        public Instance(String id)
        {
            this._id = id;
        }
    }
}

