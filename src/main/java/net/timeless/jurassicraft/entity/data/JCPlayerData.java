package net.timeless.jurassicraft.entity.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class JCPlayerData implements IExtendedEntityProperties
{
    // Store all Paleo Pad data

    public static final String identifier = "JurassiCraftPlayerData";

    public static JCPlayerData getPlayerData(EntityPlayer player)
    {
        return (JCPlayerData) player.getExtendedProperties(JCPlayerData.identifier);
    }

    @Override
    public void saveNBTData(NBTTagCompound nbt)
    {

    }

    @Override
    public void loadNBTData(NBTTagCompound nbt)
    {

    }

    @Override
    public void init(Entity entity, World world)
    {

    }
}
