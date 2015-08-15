package net.timeless.jurassicraft.common.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.gui.*;
import net.timeless.jurassicraft.common.container.*;
import net.timeless.jurassicraft.common.tileentity.*;

public class JCGuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity != null)
        {
            if (tileEntity instanceof TileCleaningStation && id == 0)
            {
                return new ContainerCleaningStation(player.inventory, (TileCleaningStation) tileEntity);
            }
            else if (tileEntity instanceof TileFossilGrinder && id == 1)
            {
                return new ContainerFossilGrinder(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof TileDnaSequencer && id == 2)
            {
                return new ContainerDNASequencer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof TileEmbryonicMachine && id == 3)
            {
                return new ContainerEmbryonicMachine(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof TileEmbryoCalcificationMachine && id == 4)
            {
                return new ContainerEmbryoCalcificationMachine(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof TileDnaSynthesizer && id == 5)
            {
                return new ContainerDnaSynthesizer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof TileIncubator && id == 6)
            {
                return new ContainerIncubator(player.inventory, tileEntity);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity != null)
        {
            if (tileEntity instanceof TileCleaningStation && id == 0)
            {
                return new GuiCleaningStation(player.inventory, (TileCleaningStation) tileEntity);
            }
            else if (tileEntity instanceof TileFossilGrinder && id == 1)
            {
                return new GuiFossilGrinder(player.inventory, (TileFossilGrinder) tileEntity);
            }
            else if (tileEntity instanceof TileDnaSequencer && id == 2)
            {
                return new GuiDNASequencer(player.inventory, (TileDnaSequencer) tileEntity);
            }
            else if (tileEntity instanceof TileEmbryonicMachine && id == 3)
            {
                return new GuiEmbryonicMachine(player.inventory, (TileEmbryonicMachine) tileEntity);
            }
            else if (tileEntity instanceof TileEmbryoCalcificationMachine && id == 4)
            {
                return new GuiEmbryoCalcificationMachine(player.inventory, (TileEmbryoCalcificationMachine) tileEntity);
            }
            else if (tileEntity instanceof TileDnaSynthesizer && id == 5)
            {
                return new GuiDNASynthesizer(player.inventory, (TileDnaSynthesizer) tileEntity);
            }
            else if (tileEntity instanceof TileIncubator && id == 6)
            {
                return new GuiIncubator(player.inventory, (TileIncubator) tileEntity);
            }
        }

        return null;
    }

    public static void openPaleoPad(EntityPlayer player)
    {
        if (player.worldObj.isRemote)
           displayPaleoPadGUIClient();
//        else
//            JurassiCraft.networkManager.networkWrapper.sendTo(new MessageSyncPaleoPad(player), (EntityPlayerMP) player);
    }

    @SideOnly(Side.CLIENT)
    private static void displayPaleoPadGUIClient()
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiPaleoTab());
    }
}
