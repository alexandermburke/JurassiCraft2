package net.timeless.jurassicraft.common.entity.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.timeless.jurassicraft.common.dna.DNA;
import net.timeless.jurassicraft.common.paleopad.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JCPlayerData implements IExtendedEntityProperties
{
    private List<DNA> sequencedDNA = new ArrayList<DNA>();

    public static final String identifier = "JurassiCraftPlayerData";

    public static JCPlayerData getPlayerData(EntityPlayer player)
    {
        return FMLCommonHandler.instance().getEffectiveSide().isClient() ? getPlayerDataClient() : (JCPlayerData) player.getExtendedProperties(JCPlayerData.identifier);
    }

    private static JCPlayerData getPlayerDataClient()
    {
        return JCPlayerDataClient.getPlayerData();
    }

    private Map<String, NBTTagCompound> appdata = new HashMap<>();
    private List<App> openApps = new ArrayList<>();

    public static void setPlayerData(EntityPlayer player, NBTTagCompound nbt)
    {
        if(FMLCommonHandler.instance().getEffectiveSide().isClient())
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

        NBTTagList sequencedDNAList = new NBTTagList();

        for (DNA dna : sequencedDNA)
        {
            NBTTagCompound dnaTag = new NBTTagCompound();
            dna.writeToNBT(dnaTag);

            sequencedDNAList.appendTag(dnaTag);
        }

        nbt.setTag("SequencedDNA", sequencedDNAList);

        NBTTagList appDataList = new NBTTagList();

        for (Map.Entry<String, NBTTagCompound> data : appdata.entrySet())
        {
            NBTTagCompound appData = new NBTTagCompound();

            appData.setString("Name", data.getKey());
            appData.setTag("Data", data.getValue());

            appDataList.appendTag(appData);
        }

        nbt.setTag("JCAppData", appDataList);

        playerData.setTag("PaleoPadData", nbt);
    }

    @Override
    public void loadNBTData(NBTTagCompound playerData)
    {
        NBTTagCompound nbt = playerData.getCompoundTag("PaleoPadData");

        NBTTagList sequencedDNAList = nbt.getTagList("SequencedDNA", 10);

        for (int i = 0; i < sequencedDNAList.tagCount(); i++)
        {
            NBTTagCompound dnaTag = (NBTTagCompound) sequencedDNAList.get(i);

            sequencedDNA.add(DNA.readFromNBT(dnaTag));
        }

        NBTTagList appDataList = nbt.getTagList("JCAppData", 10);

        for (int i = 0; i < appDataList.tagCount(); i++)
        {
            NBTTagCompound appData = (NBTTagCompound) appDataList.get(i);

            this.appdata.put(appData.getString("Name"), appData.getCompoundTag("Data"));
        }
    }

    public void openApp(App app)
    {
        if(appdata.containsKey(app.getName()))
            app.readFromNBT(appdata.get(app.getName()));

        app.init();

        openApps.add(app);
    }

    public void closeApp(App app)
    {
        NBTTagCompound data = new NBTTagCompound();
        app.writeToNBT(data);

        appdata.put(app.getName(), data);
        openApps.remove(app);
    }

    public void addSequencedDNA(DNA dna)
    {
        this.sequencedDNA.add(dna);
    }

    @Override
    public void init(Entity entity, World world)
    {
    }
}
