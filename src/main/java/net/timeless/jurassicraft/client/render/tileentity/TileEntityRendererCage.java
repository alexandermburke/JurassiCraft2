package net.timeless.jurassicraft.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.timeless.jurassicraft.common.entity.*;
import net.timeless.jurassicraft.common.tileentity.TileCage;

public class TileEntityRendererCage extends TileEntitySpecialRenderer
{
    private static final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void renderTileEntityAt(TileEntity tile, double posX, double posY, double posZ, float partialTicks, int p_180535_9_)
    {
        GlStateManager.pushMatrix();

        TileCage cage = (TileCage) tile;

        Entity entity = cage.getEntity();

        if(entity == null)
        {
            entity = new EntityGallimimus(tile.getWorld());
            cage.setEntity(entity);
        }

        GlStateManager.translate((float)posX + 0.5F, (float)posY, (float)posZ + 0.5F);

        float f1 = 0.5F;
        GlStateManager.translate(0.0F, 0.4F, 0.0F);
        EnumFacing front = EnumFacing.getFront(tile.getBlockMetadata());

        if(front == EnumFacing.EAST)
            front = EnumFacing.WEST;
        else if(front == EnumFacing.WEST)
            front = EnumFacing.EAST;

        System.out.println(front);
        GlStateManager.rotate((front.getHorizontalIndex()) * 90, 0, 1, 0);
        GlStateManager.translate(0.0F, -0.4F, 0.0F);
        entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
        Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);

        GlStateManager.popMatrix();
    }
}
