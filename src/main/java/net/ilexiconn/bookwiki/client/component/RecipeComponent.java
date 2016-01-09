package net.ilexiconn.bookwiki.client.component;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.BookWikiContainer;
import net.ilexiconn.bookwiki.api.IComponent;
import net.ilexiconn.bookwiki.client.gui.BookWikiGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * @author iLexiconn
 */
@SideOnly(Side.CLIENT)
public class RecipeComponent extends Gui implements IComponent {
    @Override
    public char getID() {
        return 'r';
    }

    @Override
    public String init(String string, String arg, String group) {
        return string.replace(group, group + "\n\n\n\n\n\n\n");
    }

    @Override
    public void render(Minecraft mc, BookWiki bookWiki, String arg, BookWikiGui gui, int x, int y, int mouseX, int mouseY) {
        BookWikiContainer.Recipe recipe = bookWiki.getRecipeByID(arg);
        mc.getTextureManager().bindTexture(gui.getTexture());
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        x += 14;
        y += mc.fontRendererObj.FONT_HEIGHT;
        Gui.drawModalRectWithCustomSizedTexture(x + 64, y + 14, 292, 101, 26, 26, 512.0F, 512.0F);
        mc.getRenderItem().zLevel = 100.0F;
        mc.getRenderItem().renderItemAndEffectIntoGUI(recipe.getResult(), x + 69, y + 19);
        mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, recipe.getResult(), x + 69, y + 19, null);
        mc.getRenderItem().zLevel = 0.0F;
        mc.getTextureManager().bindTexture(gui.getTexture());
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        Gui.drawModalRectWithCustomSizedTexture(x, y, 292, 23, 54, 54, 512.0F, 512.0F);
        RenderHelper.enableGUIStandardItemLighting();
        for (int i = 0; i < 9; i++) {
            ItemStack stack = recipe.getRecipe()[i];
            if (stack != null) {
                int newX = (x + (i % 3) * 18) + 1;
                int newY = (y + (i / 3) * 18) + 1;
                mc.getRenderItem().zLevel = 100.0F;
                mc.getRenderItem().renderItemAndEffectIntoGUI(stack, newX, newY);
                mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, newX, newY, null);
                mc.getRenderItem().zLevel = 0.0F;
            }
        }
        RenderHelper.disableStandardItemLighting();
        if (recipe.isShapeless()) {
            mc.getTextureManager().bindTexture(gui.getTexture());
            Gui.drawModalRectWithCustomSizedTexture(x + 54, y, 318, 101, 7, 7, 512.0F, 512.0F);
        }
    }

    @Override
    public void drawTooltip(Minecraft mc, BookWiki bookWiki, String arg, int x, int y, int mouseX, int mouseY) {
        BookWikiContainer.Recipe recipe = bookWiki.getRecipeByID(arg);
        x += 14;
        y += mc.fontRendererObj.FONT_HEIGHT;
        ItemStack currentItem = null;
        if (mouseX >= x + 64 && mouseY >= y + 14 && mouseX < x + 64 + 26 && mouseY < y + 14 + 26) {
            currentItem = recipe.getResult();
        }
        for (int i = 0; i < 9; i++) {
            ItemStack stack = recipe.getRecipe()[i];
            if (stack != null) {
                int newX = (x + (i % 3) * 18) + 1;
                int newY = (y + (i / 3) * 18) + 1;
                if (mouseX + 1 >= newX && mouseY + 1 >= newY && mouseX + 1 < newX + 18 && mouseY + 1 < newY + 18) {
                    currentItem = stack;
                }
            }
        }
        if (recipe.isShapeless()) {
            if (mouseX >= x + 54 && mouseY >= y && mouseX < x + 54 + 7 && mouseY < y + 7) {
                drawHoveringText(StatCollector.translateToLocal("component.recipe.shapeless"), mouseX, mouseY, mc.fontRendererObj);
            }
        }
        if (currentItem != null) {
            renderToolTip(mc, currentItem, mouseX, mouseY);
        }
    }

    public void renderToolTip(Minecraft mc, ItemStack stack, int x, int y) {
        List<String> list = stack.getTooltip(mc.thePlayer, mc.gameSettings.advancedItemTooltips);

        for (int i = 0; i < list.size(); ++i) {
            if (i == 0) {
                list.set(i, stack.getRarity().rarityColor + list.get(i));
            } else {
                list.set(i, EnumChatFormatting.GRAY + list.get(i));
            }
        }

        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        int i = 0;

        for (String s : list) {
            int j = mc.fontRendererObj.getStringWidth(s);
            if (j > i) {
                i = j;
            }
        }

        int l1 = x + 12;
        int i2 = y - 12;
        int k = 8;

        if (list.size() > 1) {
            k += 2 + (list.size() - 1) * 10;
        }

        zLevel = 300.0F;
        int l = -267386864;
        drawGradientRect(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, l, l);
        drawGradientRect(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, l, l);
        drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, l, l);
        drawGradientRect(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, l, l);
        drawGradientRect(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, l, l);
        int i1 = 1347420415;
        int j1 = (i1 & 16711422) >> 1 | i1 & -16777216;
        drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, i1, j1);
        drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, i1, j1);
        drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, i1, i1);
        drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, j1, j1);
        zLevel = 0.0F;

        for (int k1 = 0; k1 < list.size(); ++k1) {
            String s1 = list.get(k1);
            mc.fontRendererObj.drawStringWithShadow(s1, (float) l1, (float) i2, -1);
            if (k1 == 0) {
                i2 += 2;
            }
            i2 += 10;
        }

        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }

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
