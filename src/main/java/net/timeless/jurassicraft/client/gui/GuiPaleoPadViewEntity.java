package net.timeless.jurassicraft.client.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDinosaurDefinition;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.proxy.ClientProxy;

@SideOnly(Side.CLIENT)
public class GuiPaleoPadViewEntity extends GuiScreen
{
    private EntityDinosaur entity;
    private Dinosaur dino;
    private RenderDinosaurDefinition renderDef;

    public GuiPaleoPadViewEntity(EntityDinosaur entity, Dinosaur dino)
    {
        this.entity = entity;
        this.dino = dino;
        this.renderDef = ClientProxy.renderingRegistry.getRenderDef(dino);
    }

    @Override
    public void initGui()
    {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        ScaledResolution scaledResolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        drawDinosaur(scaledResolution.getScaledWidth() / 2, scaledResolution.getScaledHeight() / 2 + 50, (55.0F / dino.getAdultSizeY()) * (0.4F + 0.6F * dino.getAdultSizeY() / dino.getAdultSizeY() / scaledResolution.getScaledHeight()) * 8, entity);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {

    }

    /**
     * Draws the entity to the screen. Args: xPos, yPos, scale, mouseX, mouseY, entityLiving
     */
    private void drawDinosaur(int xPos, int yPos, float scale, EntityLivingBase entity)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) xPos, (float) yPos - scale, 50.0F);
        GlStateManager.scale(-scale, scale, scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.ticksExisted % 360, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
