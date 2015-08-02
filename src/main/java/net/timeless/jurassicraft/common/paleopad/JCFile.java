package net.timeless.jurassicraft.common.paleopad;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;

import java.util.ArrayList;
import java.util.List;

public class JCFile
{
    private boolean directory;
    private String name;

    private List<JCFile> children = new ArrayList<>();
    private JCFile parent;

    private EntityPlayer player;

    private NBTTagCompound nbt;

    public JCFile(EntityPlayer player, JCFile parent, String name)
    {
        this.name = name;
        this.parent = parent;
        this.player = player;
        this.directory = true;
    }

    public boolean isDirectory()
    {
        return directory;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setString("Name", name);

        if(isDirectory())
        {
            NBTTagList childrenList = new NBTTagList();

            for (JCFile child : children)
            {
                if(!child.equals(this))
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
            nbt.setTag("Data", this.nbt);
        }

        nbt.setBoolean("Directory", directory);
    }

    public static JCFile readFromNBT(NBTTagCompound nbt, EntityPlayer player, JCFile parent)
    {
        JCFile file = new JCFile(player, parent, nbt.getString("Name"));

        file.directory = nbt.getBoolean("Directory");

        if(file.directory)
        {
            NBTTagList childrenList = nbt.getTagList("Children", 10);

            for (int i = 0; i < childrenList.tagCount(); i++)
            {
                NBTTagCompound childNBT = childrenList.getCompoundTagAt(i);

                file.children.add(readFromNBT(childNBT, player, file));
            }

            file.createDirectory();
        }
        else
        {
            file.createFile(nbt.getCompoundTag("Data"));
        }

        return parent;
    }

    public JCFile getParent()
    {
        return parent;
    }

    public void remove()
    {
        JCPlayerData.getPlayerData(player).remove(this);
    }

    public void addChild(JCFile file)
    {
        if(!children.contains(file))
            children.add(file);
    }

    public void createFile(NBTTagCompound nbt)
    {
        this.directory = false;
        this.nbt = nbt;

        JCPlayerData playerData = JCPlayerData.getPlayerData(player);

        parent = playerData.getFile(parent.toString());

        if(parent != null)
            parent.addChild(this);
        else
            playerData.createFile(this);
    }

    public void createDirectory()
    {
        this.directory = true;

        if(parent != null)
            parent.addChild(this);
        else
            JCPlayerData.getPlayerData(player).createDirectory(this);
    }

    public String toString()
    {
        String parentString = parent != null ? parent.toString() + "/" : "";

        return parentString + name;
    }

    public boolean equals(Object obj)
    {
        return obj != null && ((this.toString().equals(obj.toString())) || (obj == this));
    }
}
