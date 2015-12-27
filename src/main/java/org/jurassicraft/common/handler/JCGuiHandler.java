package org.jurassicraft.common.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.gui.GuiCleaningStation;
import org.jurassicraft.client.gui.GuiCultivate;
import org.jurassicraft.client.gui.GuiCultivateProcess;
import org.jurassicraft.client.gui.GuiDNACombinator;
import org.jurassicraft.client.gui.GuiDNAExtractor;
import org.jurassicraft.client.gui.GuiDNAHybridizer;
import org.jurassicraft.client.gui.GuiDNASequencer;
import org.jurassicraft.client.gui.GuiDNASynthesizer;
import org.jurassicraft.client.gui.GuiEmbryoCalcificationMachine;
import org.jurassicraft.client.gui.GuiEmbryonicMachine;
import org.jurassicraft.client.gui.GuiFossilGrinder;
import org.jurassicraft.client.gui.GuiIncubator;
import org.jurassicraft.client.gui.GuiPaleoPad;
import org.jurassicraft.client.gui.GuiPaleoPadViewDinosaur;
import org.jurassicraft.client.gui.GuiSelectDino;
import org.jurassicraft.common.container.ContainerCarnivoreFeeder;
import org.jurassicraft.common.container.ContainerCleaningStation;
import org.jurassicraft.common.container.ContainerCultivate;
import org.jurassicraft.common.container.ContainerDNACombinator;
import org.jurassicraft.common.container.ContainerDNAExtractor;
import org.jurassicraft.common.container.ContainerDNAHybridizer;
import org.jurassicraft.common.container.ContainerDNASequencer;
import org.jurassicraft.common.container.ContainerDnaSynthesizer;
import org.jurassicraft.common.container.ContainerEmbryoCalcificationMachine;
import org.jurassicraft.common.container.ContainerEmbryonicMachine;
import org.jurassicraft.common.container.ContainerFossilGrinder;
import org.jurassicraft.common.container.ContainerIncubator;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.tileentity.TileCarnivoreFeeder;
import org.jurassicraft.common.tileentity.TileCleaningStation;
import org.jurassicraft.common.tileentity.TileCultivate;
import org.jurassicraft.common.tileentity.TileDNACombinator;
import org.jurassicraft.common.tileentity.TileDNAExtractor;
import org.jurassicraft.common.tileentity.TileDNAHybridizer;
import org.jurassicraft.common.tileentity.TileDnaSequencer;
import org.jurassicraft.common.tileentity.TileDnaSynthesizer;
import org.jurassicraft.common.tileentity.TileEmbryoCalcificationMachine;
import org.jurassicraft.common.tileentity.TileEmbryonicMachine;
import org.jurassicraft.common.tileentity.TileFossilGrinder;
import org.jurassicraft.common.tileentity.TileIncubator;

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
            else if (tileEntity instanceof TileDNAHybridizer && id == 7)
            {
                return new ContainerDNAHybridizer(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof TileDNACombinator && id == 8)
            {
                return new ContainerDNACombinator(player.inventory, (TileDNACombinator) tileEntity);
            }
            else if (tileEntity instanceof TileDNAExtractor && id == 9)
            {
                return new ContainerDNAExtractor(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof TileCultivate && id == 10)
            {
                return new ContainerCultivate(player.inventory, tileEntity);
            }
            else if (tileEntity instanceof TileCarnivoreFeeder && id == 11)
            {
                return new ContainerCarnivoreFeeder();
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
            else if (tileEntity instanceof TileDNAHybridizer && id == 7)
            {
                return new GuiDNAHybridizer(player.inventory, (TileDNAHybridizer) tileEntity);
            }
            else if (tileEntity instanceof TileDNACombinator && id == 8)
            {
                return new GuiDNACombinator(player.inventory, (TileDNACombinator) tileEntity);
            }
            else if (tileEntity instanceof TileDNAExtractor && id == 9)
            {
                return new GuiDNAExtractor(player.inventory, (TileDNAExtractor) tileEntity);
            }
            else if (tileEntity instanceof TileCultivate && id == 10)
            {
                if (((TileCultivate) tileEntity).isCultivating())
                {
                    return new GuiCultivateProcess((TileCultivate) tileEntity);
                }
                else
                {
                    return new GuiCultivate(player.inventory, (TileCultivate) tileEntity);
                }
            }
        }

        return null;
    }

    public static void openPaleoPad(EntityPlayer player)
    {
        if (player.worldObj.isRemote)
        {
            displayPaleoPadGUIClient();
        }
        // else
        // JurassiCraft.networkManager.networkWrapper.sendTo(new MessageSyncPaleoPad(player), (EntityPlayerMP) player);
    }

    public static void openSelectDino(EntityPlayer player, BlockPos pos, EnumFacing facing)
    {
        if (player.worldObj.isRemote)
        {
            displayOpenSelectDino(pos, facing);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void displayPaleoPadGUIClient()
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiPaleoPad());
    }

    @SideOnly(Side.CLIENT)
    private static void displayOpenSelectDino(BlockPos pos, EnumFacing facing)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiSelectDino(pos, facing));
    }

    public static void openViewDinosaur(EntityDinosaur dinosaur)
    {
        if (dinosaur.worldObj.isRemote)
        {
            displayViewDinosaurClient(dinosaur);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void displayViewDinosaurClient(EntityDinosaur dinosaur)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiPaleoPadViewDinosaur(dinosaur));
    }
}
