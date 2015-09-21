package org.jurassicraft.common.paleopad;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.jurassicraft.common.entity.data.JCPlayerData;

import java.util.ArrayList;
import java.util.List;

public class JCFile
{
    private boolean dir;
    private String name;

    private JCFile parent;

    private NBTTagCompound data;

    private JCPlayerData playerData;

    private List<JCFile> children = new ArrayList<JCFile>();

    public JCFile(String name, JCFile parent, EntityPlayer player, boolean dir)
    {
        this.name = name;
        this.parent = parent;

        this.playerData = JCPlayerData.getPlayerData(player);

        if (parent != null)
        {
            parent.addChild(this);
        }
        else
        {
            playerData.addRootFile(this);
        }

        this.dir = dir;
    }

    public NBTTagCompound getData()
    {
        return data;
    }

    public void addChild(JCFile file)
    {
        if (this.children.contains(file))
        {
            this.children.remove(file);
        }

        this.children.add(file);
    }

    public void removeChild(JCFile file)
    {
        this.children.remove(file);
    }

    public List<JCFile> getChildren()
    {
        return children;
    }

    public JCFile getParent()
    {
        return parent;
    }

    public void delete()
    {
        if (parent != null)
        {
            parent.removeChild(this);
        }

        playerData.remove(this);
    }

    public boolean isDirectory()
    {
        return dir;
    }

    public boolean isFile()
    {
        return !isDirectory();
    }

    public void setData(NBTTagCompound data)
    {
        this.data = data;
    }

    public String getPath()
    {
        return (parent != null ? parent.getPath() + "/" : "") + name;
    }

    public String getName()
    {
        return name;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setString("Name", name);

        if (isDirectory())
        {
            NBTTagList childrenList = new NBTTagList();

            for (JCFile child : children)
            {
                if (!child.equals(this))
                {
                    NBTTagCompound childNBT = new NBTTagCompound();
                    child.writeToNBT(childNBT);
                    childrenList.appendTag(childNBT);
                }
            }

            nbt.setTag("Children", childrenList);
        }
        else
        {
            nbt.setTag("Data", this.data);
        }
    }

    public static JCFile readFromNBT(NBTTagCompound nbt, EntityPlayer player, JCFile parent)
    {
        JCFile file = new JCFile(nbt.getString("Name"), parent, player, !nbt.hasKey("Data"));

        if (file.dir)
        {
            NBTTagList childrenList = nbt.getTagList("Children", 10);

            for (int i = 0; i < childrenList.tagCount(); i++)
            {
                NBTTagCompound childNBT = childrenList.getCompoundTagAt(i);

                file.children.add(readFromNBT(childNBT, player, file));
            }
        }
        else
        {
            file.setData(nbt.getCompoundTag("Data"));
        }

        return parent;
    }

    public String toString()
    {
        return getPath();
    }

    public boolean equals(Object obj)
    {
        return obj != null && ((this.toString().equals(obj.toString())) || (obj == this));
    }
}
