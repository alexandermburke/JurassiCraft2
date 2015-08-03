package net.timeless.jurassicraft.common.paleopad;

import net.minecraft.nbt.NBTTagCompound;

public class AppBrowser extends App
{
    private String path;

    @Override
    public String getName()
    {
        return "Browser";
    }

    @Override
    public void update()
    {

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        if(path != null && path.length() != 0)
        {
            nbt.setString("Path", path);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        path = nbt.getString("Path");

        if(path == null)
        {
            path = "";
        }
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }

    @Override
    public void init()
    {

    }
}
