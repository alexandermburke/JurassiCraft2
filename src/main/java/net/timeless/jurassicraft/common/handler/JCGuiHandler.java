package net.timeless.jurassicraft.common.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.timeless.jurassicraft.client.gui.GuiCleaningStation;
import net.timeless.jurassicraft.common.container.ContainerCleaningStation;
import net.timeless.jurassicraft.common.tileentity.TileCleaningStation;

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
        }

        return null;
    }

}
