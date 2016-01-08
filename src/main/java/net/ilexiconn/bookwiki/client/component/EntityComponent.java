package net.ilexiconn.bookwiki.client.component;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.api.IComponent;
import net.ilexiconn.bookwiki.client.gui.BookWikiGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * @author iLexiconn
 */
@SideOnly(Side.CLIENT)
public class EntityComponent extends Gui implements IComponent {
    @Override
    public char getID() {
        return 'e';
    }

    @Override
    public String init(String string, String arg, String group) {
        return string.replace(group, group + "\n\n\n\n\n\n\n");
    }

    @Override
    public void render(Minecraft mc, BookWiki bookWiki, String arg, int x, int y, int mouseX, int mouseY) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("id", arg);
        Entity entity = EntityList.createEntityFromNBT(nbt, mc.theWorld);
        mc.getTextureManager().bindTexture(BookWikiGui.TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        x += 14;
        y += mc.fontRendererObj.FONT_HEIGHT;
        Gui.drawModalRectWithCustomSizedTexture(x, y, 346, 23, 54, 54, 512.0F, 512.0F);
        startGlScissor(mc, x, y, 54, 54);
        GuiInventory.drawEntityOnScreen(x + 27, y + 47, 20, (float) (x + 27) - mouseX, (float) (y + 31) - mouseY, (EntityLivingBase) entity);
        endGlScissor();
        if (mouseX + 1 >= x + 1 && mouseY + 1 >= y + 1 && mouseX + 1 < x + 1 + 54 && mouseY + 1 < y + 1 + 54) {
            drawHoveringText(entity.getName(), mouseX, mouseY, mc.fontRendererObj);
        }
    }

    @SideOnly(Side.CLIENT)
    public void startGlScissor(Minecraft mc, int x, int y, int width, int height) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        double scaleW = (double) mc.displayWidth / scaledResolution.getScaledWidth_double();
        double scaleH = (double) mc.displayHeight / scaledResolution.getScaledHeight_double();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) Math.floor((double) x * scaleW), (int) Math.floor((double) mc.displayHeight - ((double) (y + height) * scaleH)), (int) Math.floor((double) (x + width) * scaleW) - (int) Math.floor((double) x * scaleW), (int) Math.floor((double) mc.displayHeight - ((double) y * scaleH)) - (int) Math.floor((double) mc.displayHeight - ((double) (y + height) * scaleH)));
    }

    @SideOnly(Side.CLIENT)
    public void endGlScissor() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    @SideOnly(Side.CLIENT)
    public void drawHoveringText(String text, int x, int y, FontRenderer font) {
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        int i = font.getStringWidth(text);

        int l1 = x + 12;
        int i2 = y - 12;
        int k = 8;

        zLevel = 300.0F;
        int l = -267386864;
        this.drawGradientRect(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, l, l);
        this.drawGradientRect(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, l, l);
        this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, l, l);
        this.drawGradientRect(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, l, l);
        this.drawGradientRect(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, l, l);
        int i1 = 1347420415;
        int j1 = (i1 & 16711422) >> 1 | i1 & -16777216;
        this.drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, i1, j1);
        this.drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, i1, j1);
        this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, i1, i1);
        this.drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, j1, j1);
        zLevel = 0.0F;

        font.drawStringWithShadow(text, (float) l1, (float) i2, -1);

        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }
}
