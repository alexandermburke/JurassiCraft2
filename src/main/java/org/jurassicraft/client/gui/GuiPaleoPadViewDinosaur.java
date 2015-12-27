package org.jurassicraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.app.GuiApp;
import org.jurassicraft.client.gui.app.GuiAppRegistry;
import org.jurassicraft.client.proxy.ClientProxy;
import org.jurassicraft.client.render.WorldRendererUtils;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.data.JCPlayerDataClient;
import org.jurassicraft.common.lang.AdvLang;
import org.jurassicraft.common.paleopad.App;
import org.jurassicraft.common.paleopad.AppRegistry;
import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiPaleoPadViewDinosaur extends GuiScreen
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/paleo_pad.png");

    private EntityDinosaur dinosaur;

    public GuiPaleoPadViewDinosaur(EntityDinosaur dinosaur)
    {
        this.dinosaur = dinosaur;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(texture);

        ScaledResolution dimensions = new ScaledResolution(mc);
        int scaledWidth = dimensions.getScaledWidth();
        int scaledHeight = dimensions.getScaledHeight();
        drawTexturedModalRect(scaledWidth / 2 - 128, 40, 0, 0, 256, 256);

        List<App> apps = AppRegistry.getApps();

        double worldTime = mc.theWorld.getWorldTime() + 6000 % 24000;

        double hours = worldTime / 1000;
        double minutes = hours * 60;

        String hoursStr = "" + (int) hours % 24;

        while (hoursStr.length() < 2)
        {
            hoursStr = "0" + hoursStr;
        }

        String minutesStr = "" + (int) minutes % 60;

        while (minutesStr.length() < 2)
        {
            minutesStr = "0" + minutesStr;
        }

        drawCenteredScaledText(new AdvLang("paleopad.time.name").withProperty("hours", hoursStr).withProperty("minutes", minutesStr).build(), 115, -10, 1.0F, 0xFFFFFF);
        Dinosaur dinoDef = this.dinosaur.getDinosaur();
        drawScaledText("Viewing: " + this.dinosaur.getCommandSenderName(), 5, 5, 1.0F, this.dinosaur.isMale() ? dinoDef.getEggPrimaryColorMale() : dinoDef.getEggPrimaryColorFemale());
        drawScaledText("Age: " + this.dinosaur.getDaysExisted() + " days", 5, 20, 1.0F, 0x808080);
        drawScaledRect(0, 0, 458, 2, 0.5F, 0x404040);

        drawScaledText(StatCollector.translateToLocal("paleopad.os.name"), 2, -10, 1.0F, 0xFFFFFF);

        this.zLevel = -100;
        drawEntityOnScreen(115, 140, (int) (70 / dinoDef.getAdultSizeY()), this.dinosaur);

        EnumDiet diet = dinoDef.getDiet();
        drawScaledEndText((this.dinosaur.isMale() ? "MALE" : "FEMALE"), 225, 5, 1.0F, 0xFFFF00);
        drawScaledEndText(diet.toString(), 225, 35, 1.0F, diet.getColor());
        drawScaledEndText(dinoDef.getSleepingSchedule().toString(), 225, 20, 1.0F, 0xAAAAAA);
        drawScaledEndText(dinosaur.getGrowthStage().toString(), 225, 50, 1.0F, 0xB200FF);

        if (dinoDef.isMarineAnimal())
        {
            drawScaledEndText("MARINE", 225, 138, 1.0F, 0x0080FF);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
     */
    public void drawScaledTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height, int textureWidth, int textureHeight, float scale)
    {
        GL11.glPushMatrix();

        ScaledResolution dimensions = new ScaledResolution(mc);
        x += dimensions.getScaledWidth() / 2 - 115;
        y += 65;

        x /= scale;
        y /= scale;

        GL11.glScalef(scale, scale, scale);

        float f = 1.0F / (float) textureWidth;
        float f1 = 1.0F / (float) textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        WorldRendererUtils rendererUtils = new WorldRendererUtils(worldrenderer);
        rendererUtils.startDrawingQuads();
        rendererUtils.addVertexWithUV((double) (x), (double) (y + height), (double) this.zLevel, (double) ((float) (textureX) * f), (double) ((float) (textureY + height) * f1));
        rendererUtils.addVertexWithUV((double) (x + width), (double) (y + height), (double) this.zLevel, (double) ((float) (textureX + width) * f), (double) ((float) (textureY + height) * f1));
        rendererUtils.addVertexWithUV((double) (x + width), (double) (y), (double) this.zLevel, (double) ((float) (textureX + width) * f), (double) ((float) (textureY) * f1));
        rendererUtils.addVertexWithUV((double) (x), (double) (y), (double) this.zLevel, (double) ((float) (textureX) * f), (double) ((float) (textureY) * f1));
        tessellator.draw();

        GL11.glColor3f(1.0F, 1.0F, 1.0F);

        GL11.glPopMatrix();
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
     */
    public void drawScaledRect(int x, int y, int width, int height, float scale, int colour)
    {
        GL11.glPushMatrix();

        ScaledResolution dimensions = new ScaledResolution(mc);
        x += dimensions.getScaledWidth() / 2 - 115;
        y += 65;

        x /= scale;
        y /= scale;

        GL11.glScalef(scale, scale, scale);

        GL11.glDisable(GL11.GL_TEXTURE_2D);

        float red = (float) (colour >> 16 & 255) / 255.0F;
        float green = (float) (colour >> 8 & 255) / 255.0F;
        float blue = (float) (colour & 255) / 255.0F;

        GL11.glColor3f(red, green, blue);

        float f = 1.0F / (float) width;
        float f1 = 1.0F / (float) height;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        WorldRendererUtils rendererUtils = new WorldRendererUtils(worldrenderer);
        rendererUtils.startDrawingQuads();
        rendererUtils.addVertexWithUV((double) (x), (double) (y + height), (double) this.zLevel, (double) (0), (double) ((float) (height) * f1));
        rendererUtils.addVertexWithUV((double) (x + width), (double) (y + height), (double) this.zLevel, (double) ((float) (width) * f), (double) ((float) (height) * f1));
        rendererUtils.addVertexWithUV((double) (x + width), (double) (y), (double) this.zLevel, (double) ((float) (width) * f), (double) ((float) 0));
        rendererUtils.addVertexWithUV((double) (x), (double) (y), (double) this.zLevel, (double) ((float) 0), (double) ((float) 0));
        tessellator.draw();

        GL11.glColor3f(1.0F, 1.0F, 1.0F);

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glPopMatrix();
    }

    public void drawBoxOutline(int x, int y, int sizeX, int sizeY, int borderSize, float scale, int colour)
    {
        GL11.glPushMatrix();

        drawScaledRect(x, y, sizeX, borderSize, scale, colour);
        drawScaledRect(x + sizeX, y, borderSize, sizeY + borderSize, scale, colour);
        drawScaledRect(x, y, borderSize, sizeY + borderSize, scale, colour);
        drawScaledRect(x, y + sizeY, sizeX, borderSize, scale, colour);

        GL11.glPopMatrix();
    }

    public void drawCenteredScaledText(String text, int x, int y, float scale, int colour)
    {
        GL11.glPushMatrix();

        ScaledResolution dimensions = new ScaledResolution(mc);
        x += dimensions.getScaledWidth() / 2 - 115;
        y += 65;

        GL11.glScalef(scale, scale, scale);

        x /= scale;
        y /= scale;

        drawCenteredString(fontRendererObj, text, x, y, colour);

        GL11.glPopMatrix();
    }

    public void drawScaledText(String text, int x, int y, float scale, int colour)
    {
        GL11.glPushMatrix();

        ScaledResolution dimensions = new ScaledResolution(mc);
        x += dimensions.getScaledWidth() / 2 - 115;
        y += 65;

        GL11.glScalef(scale, scale, scale);

        x /= scale;
        y /= scale;

        drawString(fontRendererObj, text, x, y, colour);

        GL11.glPopMatrix();
    }

    public void drawScaledEndText(String text, int x, int y, float scale, int colour)
    {
        GL11.glPushMatrix();

        ScaledResolution dimensions = new ScaledResolution(mc);
        x += dimensions.getScaledWidth() / 2 - 115;
        y += 65;

        GL11.glScalef(scale, scale, scale);

        x /= scale;
        y /= scale;

        drawString(fontRendererObj, text, x - (fontRendererObj.getStringWidth(text)), y, colour);

        GL11.glPopMatrix();
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {

    }

    public void drawEntityOnScreen(int posX, int posY, int scale, EntityLivingBase ent)
    {
        ScaledResolution dimensions = new ScaledResolution(mc);
        posX += dimensions.getScaledWidth() / 2 - 115;
        posY += 65;

        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        int rot = ent.ticksExisted % 360;
        ent.renderYawOffset = rot;
        ent.rotationYaw = rot;
        ent.rotationPitch = 0;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
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
