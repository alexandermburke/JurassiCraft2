package net.timeless.jurassicraft.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.json.TabulaModelHelper;

public class TileEntitySpecialRendererIncubator extends TileEntitySpecialRenderer
{
    private ModelJson model;
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/blocks/incubator.png");
    private static final Minecraft mc = Minecraft.getMinecraft();

    public TileEntitySpecialRendererIncubator()
    {
        try
        {
            this.model = new ModelJson(TabulaModelHelper.parseModel("/assets/jurassicraft/models/block/incubator"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_180535_8_, int p_180535_9_)
    {
        GlStateManager.pushMatrix();

        GlStateManager.color(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(posX + 0.5, posY + 1.6, posZ + 0.5);
        GlStateManager.scale(1.0F, -1.0F, 1.0F);

        mc.getTextureManager().bindTexture(texture);
        model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

        GlStateManager.popMatrix();
    }
}
