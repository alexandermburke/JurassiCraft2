package org.jurassicraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.lang.AdvLang;
import org.jurassicraft.common.paleopad.App;
import org.jurassicraft.common.paleopad.AppRegistry;
import org.lwjgl.opengl.GL11;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiPaleoPadViewDinosaur extends GuiScreen
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/paleo_pad.png");

    private final EntityDinosaur dinosaur;

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

        drawCenteredScaledText(new AdvLang("paleopad.time.name").withProperty("hours", hoursStr).withProperty("minutes", minutesStr).build(), -10, 1.0F, 0xFFFFFF);
        Dinosaur dinoDef = this.dinosaur.getDinosaur();
        drawScaledText("Viewing: " + this.dinosaur.getName(), 5, 5, this.dinosaur.isMale() ? dinoDef.getEggPrimaryColorMale() : dinoDef.getEggPrimaryColorFemale());
        drawScaledText("Age: " + this.dinosaur.getDaysExisted() + " days", 5, 20, 0x808080);
        drawScaledRect(0, 0, 458, 2, 0.5F, 0x404040);

        drawScaledText(StatCollector.translateToLocal("paleopad.os.name"), 2, -10, 0xFFFFFF);

        this.zLevel = -100;
        drawEntityOnScreen(140, (int) (70 / dinoDef.getAdultSizeY()), this.dinosaur);

        EnumDiet diet = dinoDef.getDiet();
        drawScaledEndText((this.dinosaur.isMale() ? "MALE" : "FEMALE"), 5, 1.0F, 0xFFFF00);
        drawScaledEndText(diet.toString(), 35, 1.0F, diet.getColor());
        drawScaledEndText(dinoDef.getSleepingSchedule().toString(), 20, 1.0F, 0xAAAAAA);
        drawScaledEndText(dinosaur.getGrowthStage().toString(), 50, 1.0F, 0xB200FF);

        if (dinoDef.isMarineAnimal())
        {
            drawScaledEndText("MARINE", 138, 1.0F, 0x0080FF);
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
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double) (x), (double) (y + height), (double) this.zLevel).tex((double) ((float) (textureX) * f), (double) ((float) (textureY + height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) (y + height), (double) this.zLevel).tex((double) ((float) (textureX + width) * f), (double) ((float) (textureY + height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) (y), (double) this.zLevel).tex((double) ((float) (textureX + width) * f), (double) ((float) (textureY) * f1)).endVertex();
        worldrenderer.pos((double) (x), (double) (y), (double) this.zLevel).tex((double) ((float) (textureX) * f), (double) ((float) (textureY) * f1)).endVertex();
        tessellator.draw();

        GL11.glColor3f(1.0F, 1.0F, 1.0F);

        GL11.glPopMatrix();
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
     */
    private void drawScaledRect(int x, int y, int width, int height, float scale, int colour)
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
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double) (x), (double) (y + height), (double) this.zLevel).tex((double) (0), (double) ((float) (height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) (y + height), (double) this.zLevel).tex((double) ((float) (width) * f), (double) ((float) (height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) (y), (double) this.zLevel).tex((double) ((float) (width) * f), (double) ((float) 0)).endVertex();
        worldrenderer.pos((double) (x), (double) (y), (double) this.zLevel).tex((double) ((float) 0), (double) ((float) 0)).endVertex();
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

    private void drawCenteredScaledText(String text, int y, float scale, int colour)
    {
        GL11.glPushMatrix();

        ScaledResolution dimensions = new ScaledResolution(mc);
        115 += dimensions.getScaledWidth() / 2 - 115;
        y += 65;

        GL11.glScalef(1.0F, 1.0F, 1.0F);

        115 /= 1.0F;
        y /= 1.0F;

        drawCenteredString(fontRendererObj, text, 115, y, 0xFFFFFF);

        GL11.glPopMatrix();
    }

    private void drawScaledText(String text, int x, int y, int colour)
    {
        GL11.glPushMatrix();

        ScaledResolution dimensions = new ScaledResolution(mc);
        x += dimensions.getScaledWidth() / 2 - 115;
        y += 65;

        GL11.glScalef(1.0F, 1.0F, 1.0F);

        x /= 1.0F;
        y /= 1.0F;

        drawString(fontRendererObj, text, x, y, colour);

        GL11.glPopMatrix();
    }

    private void drawScaledEndText(String text, int y, float scale, int colour)
    {
        GL11.glPushMatrix();

        ScaledResolution dimensions = new ScaledResolution(mc);
        225 += dimensions.getScaledWidth() / 2 - 115;
        y += 65;

        GL11.glScalef(1.0F, 1.0F, 1.0F);

        225 /= 1.0F;
        y /= 1.0F;

        drawString(fontRendererObj, text, 225 - (fontRendererObj.getStringWidth(text)), y, colour);

        GL11.glPopMatrix();
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {

    }

    private void drawEntityOnScreen(int posY, int scale, EntityLivingBase ent)
    {
        ScaledResolution dimensions = new ScaledResolution(mc);
        115 += dimensions.getScaledWidth() / 2 - 115;
        140 += 65;

        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) 115, (float) 140, 50.0F);
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
