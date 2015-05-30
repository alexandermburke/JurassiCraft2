package net.reuxertz.ecocraft.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.reuxertz.ecocraft.tileentity.TileEntityDroppedItem;
import org.lwjgl.opengl.GL11;

public class TileEntityDroppedItemRenderer extends TileEntitySpecialRenderer
{
    ItemStack stack = null;
    EntityItem entItem = null;//new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);
    RenderManager renderManager;
    RenderItem renderItem;

    public TileEntityDroppedItemRenderer()
    {
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int n)
    {
        if (stack == null)
        {
            stack = ((TileEntityDroppedItem) te).getDroppedItem();
            entItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);
        }

        float xOffset = 0.0F;
        float zOffset = 0.0F;
        GL11.glPushMatrix();
        entItem.hoverStart = 0.0F;
        GL11.glTranslatef((float) x + 0.5F + xOffset, (float) y + 0.05F, (float) z + 0.3F + zOffset);
        GL11.glRotatef(1 * -90F, 0, 1, 0);
        GL11.glRotatef(180, 0, 1, 1);
        Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.075D, 0.0F, 0.0F);
        GL11.glPopMatrix();
    }

}
