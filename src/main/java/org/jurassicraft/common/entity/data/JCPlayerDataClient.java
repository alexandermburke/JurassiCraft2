package org.jurassicraft.common.entity.data;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class JCPlayerDataClient
{
    private static final JCPlayerData playerData;

    private static final Minecraft mc = Minecraft.getMinecraft();

    static
    {
        playerData = new JCPlayerData();

        playerData.init(mc.thePlayer, mc.theWorld);
    }

    public static JCPlayerData getPlayerData()
    {
        return playerData;
    }

    public static void updatePlayerData(NBTTagCompound nbt)
    {
        playerData.loadNBTData(nbt);
    }
}
