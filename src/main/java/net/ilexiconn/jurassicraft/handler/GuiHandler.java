package net.ilexiconn.jurassicraft.handler;

import net.ilexiconn.jurassicraft.client.gui.GuiCleaningStation;
import net.ilexiconn.jurassicraft.container.ContainerCleaningStation;
import net.ilexiconn.jurassicraft.tileentity.TileCleaningStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null)
        {
            if (tileEntity instanceof TileCleaningStation)
            {
                return new ContainerCleaningStation(player.inventory, tileEntity);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity != null)
        {
            if (tileEntity instanceof TileCleaningStation)
            {
                return new GuiCleaningStation(player.inventory, tileEntity);
            }
        }
        return null;
    }

}
