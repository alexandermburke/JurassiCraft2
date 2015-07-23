package net.timeless.jurassicraft.common.entity.data;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.timeless.jurassicraft.common.dna.DNA;

public class JCPlayerData implements IExtendedEntityProperties
{
    private List<DNA> sequencedDNA = new ArrayList<DNA>();

    public static final String identifier = "JurassiCraftPlayerData";

    public static JCPlayerData getPlayerData(EntityPlayer player)
    {
        return (JCPlayerData) player.getExtendedProperties(JCPlayerData.identifier);
    }

    @Override
    public void saveNBTData(NBTTagCompound nbt)
    {
        NBTTagList sequencedDNAList = new NBTTagList();

        for (DNA dna : sequencedDNA)
        {
            NBTTagCompound dnaTag = new NBTTagCompound();
            dna.writeToNBT(dnaTag);

            sequencedDNAList.appendTag(dnaTag);
        }

        nbt.setTag("SequencedDNA", sequencedDNAList);
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt)
    {
        NBTTagList sequencedDNAList = nbt.getTagList("SequencedDNA", 10);

        for (int i = 0; i < sequencedDNAList.tagCount(); i++)
        {
            NBTTagCompound dnaTag = (NBTTagCompound) sequencedDNAList.get(i);

            sequencedDNA.add(DNA.readFromNBT(dnaTag));
        }
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
