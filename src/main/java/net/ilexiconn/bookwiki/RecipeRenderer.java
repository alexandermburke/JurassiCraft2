package net.ilexiconn.bookwiki;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class RecipeRenderer extends Gui {
    private final Minecraft mc = Minecraft.getMinecraft();

    public void render(BookWikiContainer.Recipe recipe, int x, int y, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(BookWikiGui.TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        drawModalRectWithCustomSizedTexture(x + 64, y + 14, 292, 101, 26, 26, 512.0F, 512.0F);
        ItemStack currentItem = null;
        mc.getRenderItem().zLevel = 100.0F;
        mc.getRenderItem().renderItemAndEffectIntoGUI(recipe.getResult(), x + 69, y + 19);
        mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, recipe.getResult(), x + 69, y + 19, null);
        mc.getRenderItem().zLevel = 0.0F;
        if (mouseX >= x + 64 && mouseY >= y + 14 && mouseX < x + 64 + 26 && mouseY < y + 14 + 26) {
            currentItem = recipe.getResult();
        }
        mc.getTextureManager().bindTexture(BookWikiGui.TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        drawModalRectWithCustomSizedTexture(x, y, 292, 23, 54, 54, 512.0F, 512.0F);
        for (int i = 0; i < 9; i++) {
            ItemStack stack = recipe.getRecipe()[i];
            if (stack != null) {
                int newX = (x + (i % 3) * 18) + 1;
                int newY = (y + (i / 3) * 18) + 1;
                mc.getRenderItem().zLevel = 100.0F;
                mc.getRenderItem().renderItemAndEffectIntoGUI(stack, newX, newY);
                mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, newX, newY, null);
                mc.getRenderItem().zLevel = 0.0F;
                if (mouseX + 1 >= newX && mouseY + 1 >= newY && mouseX + 1 < newX + 18 && mouseY + 1 < newY + 18) {
                    currentItem = stack;
                }
            }
        }
        if (currentItem != null) {
            renderToolTip(currentItem, mouseX, mouseY);
        }
    }

    protected void renderToolTip(ItemStack stack, int x, int y) {
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

        for (int k1 = 0; k1 < list.size(); ++k1) {
            String s1 = list.get(k1);
            mc.fontRendererObj.drawStringWithShadow(s1, (float) l1, (float) i2, -1);
            if (k1 == 0) {
                i2 += 2;
            }
            i2 += 10;
        }

        zLevel = 0.0F;
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }
}
