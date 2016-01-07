package net.ilexiconn.bookwiki;

import com.google.common.collect.Lists;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SideOnly(Side.CLIENT)
public class BookWikiGui extends GuiScreen {
    private BookWiki bookWiki;

    public static final ResourceLocation TEXTURE = new ResourceLocation("jurassicraft", "bookwiki/gui.png");
    private String currentCategory = "general";
    private RecipeRenderer recipeRenderer;

    public Pattern recipePattern = Pattern.compile("<r:[A-Za-z0-9]+>");
    public Pattern colorPattern = Pattern.compile("<c:[A-Za-z]+>");
    public String[] rainbowChars = new String[]{"4", "6", "e", "a", "9", "5"};

    public BookWikiGui(BookWiki bookWiki) {
        this.bookWiki = bookWiki;
        this.recipeRenderer = new RecipeRenderer();
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
            if (currentCategory.equals(category.getID())) {
                drawModalRectWithCustomSizedTexture(x, y, 346, 77, 27, 24, 512.0F, 512.0F);
            } else if (mouseX >= x && mouseY >= y && mouseX < x + 27 && mouseY < y + 24) {
                drawModalRectWithCustomSizedTexture(x, y, 319, 77, 27, 24, 512.0F, 512.0F);
            } else {
                drawModalRectWithCustomSizedTexture(x, y, 292, 77, 27, 24, 512.0F, 512.0F);
            }
            if (mouseX >= x && mouseY >= y && mouseX < x + 27 && mouseY < y + 24) {
                hover = category.getName();
            }
            ItemStack stack = category.getIcon();
            if (stack != null) {
                RenderHelper.enableGUIStandardItemLighting();
                mc.getRenderItem().zLevel = 100.0F;
                mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x + 5, y + 5 + (hover != null && hover.equals(category.getName()) && !currentCategory.equals(category.getID()) ? -2 : 0));
                mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, x + 5, y + 5 + (hover != null && hover.equals(category.getName()) && !currentCategory.equals(category.getID()) ? -2 : 0), null);
                mc.getRenderItem().zLevel = 0.0F;
                RenderHelper.disableStandardItemLighting();
            }
        }
        List<String> lines = Lists.newArrayList(fontRendererObj.listFormattedStringToWidth(getContent(bookWiki.getCategoryByID(currentCategory).getDefaultPage()), 116));
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Matcher matcher = recipePattern.matcher(line);
            if (i < 17) {
                if (matcher.find()) {
                    String group = matcher.group();
                    line = line.replace(group, "");
                    String id = group.substring(3, group.length() - 1);
                    BookWikiContainer.Recipe recipe = bookWiki.getRecipeByID(id);
                    recipeRenderer.render(recipe, width / 2 - 292 / 2 + 16 + 16, height / 2 - 180 / 2 + 14 + fontRendererObj.FONT_HEIGHT * (i + 1), mouseX, mouseY);
                }
                fontRendererObj.drawString(line, width / 2 - 292 / 2 + 16, height / 2 - 180 / 2 + 14 + fontRendererObj.FONT_HEIGHT * i, 0x000);
            } else {
                if (matcher.find()) {
                    String group = matcher.group();
                    line = line.replace(group, "");
                    String id = group.substring(3, group.length() - 1);
                    BookWikiContainer.Recipe recipe = bookWiki.getRecipeByID(id);
                    recipeRenderer.render(recipe, width / 2 - 292 / 2 + 16 + 16 + 140, height / 2 - 180 / 2 + 14 + fontRendererObj.FONT_HEIGHT * (i - 16), mouseX, mouseY);
                }
                fontRendererObj.drawString(line, width / 2 - 292 / 2 + 16 + 140, height / 2 - 180 / 2 + 14 + fontRendererObj.FONT_HEIGHT * (i - 17), 0x000);
            }
        }
        if (hover != null) {
            drawCreativeTabHoveringText(StatCollector.translateToLocal(hover), mouseX, mouseY);
        }
    }

    public String getContent(BookWikiContainer.Page page) {
        String result = page.getContent();
        Matcher matcher = recipePattern.matcher(result);
        while (matcher.find()) {
            String group = matcher.group();
            result = result.replace(group, group + "\n\n\n\n\n\n\n");
        }
        matcher = colorPattern.matcher(result);
        while (matcher.find()) {
            String group = matcher.group();
            String id = group.substring(3, group.length() - 1).toUpperCase();
            EnumChatFormatting formatting = EnumChatFormatting.getValueByName(id);
            if (formatting != null) {
                result = result.replace(group, formatting + "");
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
}
