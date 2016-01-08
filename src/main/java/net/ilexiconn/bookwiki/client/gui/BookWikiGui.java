package net.ilexiconn.bookwiki.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.api.BookWikiAPI;
import net.ilexiconn.bookwiki.api.IComponent;
import net.ilexiconn.bookwiki.BookWikiContainer;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author iLexiconn
 */
@SideOnly(Side.CLIENT)
public class BookWikiGui extends GuiScreen {
    public static final ResourceLocation TEXTURE = new ResourceLocation("jurassicraft", "bookwiki/gui.png");
    private BookWiki bookWiki;
    private String currentCategory = "general";

    public BookWikiGui(BookWiki bookWiki) {
        this.bookWiki = bookWiki;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(BookWikiGui.TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(width / 2 - 292 / 2, height / 2 - 180 / 2, 0, 0, 292, 180, 512.0F, 512.0F);
        String hover = null;
        for (int i = 0; i < bookWiki.getContainer().getCategories().length; i++) {
            BookWikiContainer.Category category = bookWiki.getContainer().getCategories()[i];
            int x = width / 2 - 292 / 2 + 14 + 27 * i;
            int y = height / 2 - 180 / 2 - 24 + 7;
            mc.getTextureManager().bindTexture(BookWikiGui.TEXTURE);
            GlStateManager.disableLighting();
            ItemStack stack = category.getIcon();
            RenderHelper.enableGUIStandardItemLighting();
            mc.getRenderItem().zLevel = 100.0F;
            if (currentCategory.equals(category.getID())) {
                drawModalRectWithCustomSizedTexture(x - 3, y, 374, 77, 27, 24, 512.0F, 512.0F);
                startGlScissor(x + 3, y + 9, 16, 12);
                mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x + 3, y + 5);
                mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, x + 3, y + 5, null);
                endGlScissor();
            } else if (mouseX >= x && mouseY >= y && mouseX < x + 27 && mouseY < y + 24) {
                drawModalRectWithCustomSizedTexture(x, y, 319, 77, 27, 24, 512.0F, 512.0F);
                mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x + 5, y + 3);
                mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, x + 5, y + 3, null);
            } else {
                drawModalRectWithCustomSizedTexture(x, y, 292, 77, 27, 24, 512.0F, 512.0F);
                RenderHelper.enableGUIStandardItemLighting();
                mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x + 5, y + 5);
                mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, x + 5, y + 5, null);
            }
            mc.getRenderItem().zLevel = 0.0F;
            RenderHelper.disableStandardItemLighting();
            if (mouseX >= x && mouseY >= y && mouseX < x + 27 && mouseY < y + 24) {
                hover = category.getName();
            }
        }
        List<String> lines = Lists.newArrayList(fontRendererObj.listFormattedStringToWidth(getContent(bookWiki.getCategoryByID(currentCategory).getDefaultPage()), 116));
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int x = width / 2 - 292 / 2 + 16;
            int y = height / 2 - 180 / 2 + 14 + fontRendererObj.FONT_HEIGHT * i;
            if (i >= 17) {
                x = width / 2 - 292 / 2 + 16 + 140;
                y = height / 2 - 180 / 2 + 14 + fontRendererObj.FONT_HEIGHT * (i - 17);
            }
            Map<IComponent, String> componentMap = Maps.newHashMap();
            for (IComponent component : BookWikiAPI.getComponents()) {
                Matcher matcher = Pattern.compile("<" + component.getID() + ":[A-Za-z]*>").matcher(line);
                while (matcher.find()) {
                    String group = matcher.group();
                    String arg = group.substring(3, group.length() - 1);
                    componentMap.put(component, arg);
                    line = line.replace(group, "");
                }
            }
            GlStateManager.disableLighting();
            fontRendererObj.drawString(line, x, y, 0x000);
            for (Map.Entry<IComponent, String> entry : componentMap.entrySet()) {
                entry.getKey().render(mc, bookWiki, entry.getValue(), x, y, mouseX, mouseY);
            }
        }
        if (hover != null) {
            drawCreativeTabHoveringText(StatCollector.translateToLocal(hover), mouseX, mouseY);
        }
    }

    public String getContent(BookWikiContainer.Page page) {
        String result = page.getContent();
        for (IComponent component : BookWikiAPI.getComponents()) {
            Matcher matcher = Pattern.compile("<" + component.getID() + ":[A-Za-z]*>").matcher(result);
            while (matcher.find()) {
                String group = matcher.group();
                String arg = group.substring(3, group.length() - 1);
                result = component.init(result, arg, group);
            }
        }
        return result;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            for (int i = 0; i < bookWiki.getContainer().getCategories().length; i++) {
                BookWikiContainer.Category category = bookWiki.getContainer().getCategories()[i];
                if (currentCategory.equals(category.getID())) {
                    continue;
                }
                int x = width / 2 - 292 / 2 + 14 + 27 * i;
                int y = height / 2 - 180 / 2 - 24 + 7;
                if (mouseX >= x && mouseY >= y && mouseX < x + 27 && mouseY < y + 24) {
                    currentCategory = category.getID();
                    mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(bookWiki.getMod().modid() + ":page-flip"), 1.0F));
                }
            }
        }
    }

    public void startGlScissor(int x, int y, int width, int height) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        double scaleW = (double) mc.displayWidth / scaledResolution.getScaledWidth_double();
        double scaleH = (double) mc.displayHeight / scaledResolution.getScaledHeight_double();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) Math.floor((double) x * scaleW), (int) Math.floor((double) mc.displayHeight - ((double) (y + height) * scaleH)), (int) Math.floor((double) (x + width) * scaleW) - (int) Math.floor((double) x * scaleW), (int) Math.floor((double) mc.displayHeight - ((double) y * scaleH)) - (int) Math.floor((double) mc.displayHeight - ((double) (y + height) * scaleH)));
    }

    public void endGlScissor() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }
}
