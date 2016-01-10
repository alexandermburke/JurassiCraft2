package org.jurassicraft.common.entity.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.jurassicraft.common.genetics.DinoDNA;
import org.jurassicraft.common.paleopad.App;
import org.jurassicraft.common.paleopad.JCFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class JCPlayerData implements IExtendedEntityProperties
{
    public static final String identifier = "JurassiCraftPlayerData";
    private boolean pressingSpace;

    public static JCPlayerData getPlayerData(EntityPlayer player)
    {
        return FMLCommonHandler.instance().getEffectiveSide().isClient() ? getPlayerDataClient() : (JCPlayerData) player.getExtendedProperties(JCPlayerData.identifier);
    }

    private static JCPlayerData getPlayerDataClient()
    {
        return JCPlayerDataClient.getPlayerData();
    }

    private Map<String, NBTTagCompound> appdata = new HashMap<String, NBTTagCompound>();
    private List<App> openApps = new ArrayList<App>();

    private List<JCFile> rootFiles = new ArrayList<JCFile>();
    private List<BlockPos> cameras = new ArrayList<BlockPos>();

    private EntityPlayer player;

    public static void setPlayerData(EntityPlayer player, NBTTagCompound nbt)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient())
        {
            setPlayerDataClient(nbt);
        }
        else
        {
            getPlayerData(player).loadNBTData(nbt);
        }
    }

    private static void setPlayerDataClient(NBTTagCompound nbt)
    {
        JCPlayerDataClient.updatePlayerData(nbt);
    }

    @Override
    public void saveNBTData(NBTTagCompound playerData)
    {
        NBTTagCompound nbt = new NBTTagCompound();

        NBTTagList files = new NBTTagList();

        for (JCFile file : rootFiles)
        {
            if (file != null)
            {
                NBTTagCompound fileNBT = new NBTTagCompound();
                file.writeToNBT(fileNBT);

                files.appendTag(fileNBT);
            }
        }

        nbt.setTag("Files", files);

        NBTTagList appDataList = new NBTTagList();

        for (Map.Entry<String, NBTTagCompound> data : appdata.entrySet())
        {
            NBTTagCompound appData = new NBTTagCompound();

            appData.setString("Name", data.getKey());
            appData.setTag("Data", data.getValue());

            appDataList.appendTag(appData);
        }

        nbt.setTag("JCAppData", appDataList);

        NBTTagList cameraList = new NBTTagList();

        for (BlockPos pos : cameras)
        {
            NBTTagCompound camera = new NBTTagCompound();

            camera.setInteger("CamX", pos.getX());
            camera.setInteger("CamY", pos.getY());
            camera.setInteger("CamZ", pos.getZ());

            cameraList.appendTag(camera);
        }

        nbt.setTag("Cameras", cameraList);

        playerData.setTag("PaleoPadData", nbt);
    }

    @Override
    public void loadNBTData(NBTTagCompound playerData)
    {
        rootFiles.clear();
        appdata.clear();

        if (playerData == null)
        {
            return;
        }

        NBTTagCompound nbt = playerData.getCompoundTag("PaleoPadData");

        NBTTagList filesList = nbt.getTagList("Files", 10);

        for (int i = 0; i < filesList.tagCount(); i++)
        {
            NBTTagCompound fileTag = (NBTTagCompound) filesList.get(i);

            rootFiles.add(JCFile.readFromNBT(fileTag, player, null));
        }

        NBTTagList appDataList = nbt.getTagList("JCAppData", 10);

        for (int i = 0; i < appDataList.tagCount(); i++)
        {
            NBTTagCompound appData = (NBTTagCompound) appDataList.get(i);

            this.appdata.put(appData.getString("Name"), appData.getCompoundTag("Data"));
        }

        NBTTagList cameraList = nbt.getTagList("Cameras", 10);

        for (int i = 0; i < cameraList.tagCount(); i++)
        {
            NBTTagCompound camera = (NBTTagCompound) cameraList.get(i);

            this.cameras.add(new BlockPos(camera.getInteger("CamX"), camera.getInteger("CamY"), camera.getInteger("CamZ")));
        }
    }

    public void openApp(App app)
    {
        if (appdata.containsKey(app.getName()))
        {
            app.readAppFromNBT(appdata.get(app.getName()));
        }

        app.init();
        app.open();

        openApps.add(app);
    }

    public void closeApp(App app)
    {
        NBTTagCompound data = new NBTTagCompound();
        app.writeAppToNBT(data);

        appdata.put(app.getName(), data);
        openApps.remove(app);
    }

    public void addSequencedDNA(DinoDNA dna)
    {
        JCFile sequencedDNADir = getFile("Sequenced DNA", true);

        JCFile dnaFile = new JCFile(dna.toString(), sequencedDNADir, player, false);

        NBTTagCompound data = new NBTTagCompound();
        dna.writeToNBT(data);

        dnaFile.setData(data);
    }

    public void addCamera(BlockPos pos)
    {
        if (!cameras.contains(pos))
        {
            cameras.add(pos);
        }
    }

    public List<BlockPos> getCameras()
    {
        return cameras;
    }

    @Override
    public void init(Entity entity, World world)
    {
        if (entity instanceof EntityPlayer)
        {
            player = (EntityPlayer) entity;
        }
    }

    public JCFile getFileFromPath(String path)
    {
        if (path.length() == 0)
        {
            return null;
        }

        String[] pathSplit = path.split(Pattern.quote("/"));

        if (pathSplit.length == 0)
        {
            pathSplit = new String[] { path };
        }

        return traversePath(pathSplit, 1, getFile(pathSplit[0], true));
    }

    public List<JCFile> getFilesAtPath(String path)
    {
        if (path == null || path.length() == 0)
        {
            return rootFiles;
        }
        else
        {
            JCFile fileFromPath = getFileFromPath(path);

            if (fileFromPath == null)
            {
                return null;
            }
            else
            {
                return fileFromPath.getChildren();
            }
        }
    }

    public JCFile traversePath(String[] path, int i, JCFile lastFile)
    {
        if (i == path.length)
        {
            return lastFile;
        }

        for (JCFile child : lastFile.getChildren())
        {
            if (child.getName().equals(path[i]))
            {
                return traversePath(path, i + 1, child);
            }
        }

        return null;
    }

    public JCFile getFile(String file, boolean dir)
    {
        JCFile jcFile = null;

        for (JCFile rFile : rootFiles)
        {
            if (rFile != null)
            {
                if (rFile.getName().equals(file))
                {
                    jcFile = rFile;

                    break;
                }
            }
        }

        if (jcFile == null)
        {
            jcFile = new JCFile(file, null, player, dir);
            addRootFile(jcFile);
        }

        return jcFile;
    }

    public void addRootFile(JCFile jcFile)
    {
        if (jcFile.getParent() == null)
        {
            if (rootFiles.contains(jcFile))
            {
                rootFiles.remove(jcFile);
            }

            rootFiles.add(jcFile);
        }
    }

    public void remove(JCFile file)
    {
        if (file.getParent() == null && rootFiles.contains(file))
        {
            rootFiles.remove(file);
        }
    }

    public void clearRootFiles()
    {
        rootFiles.clear();
    }
}
