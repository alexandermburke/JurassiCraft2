package net.reuxertz.ecocraft.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ecocraft.client.renderer.TileEntityDroppedItemRenderer;
import net.reuxertz.ecocraft.tileentity.TileEntityDroppedItem;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    @Override
    public void registerTileEntities()
    {

    }
}