package net.ilexiconn.bookwiki.client.component;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.api.IComponent;
import net.ilexiconn.bookwiki.client.gui.BookWikiGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author iLexiconn
 */
@SideOnly(Side.CLIENT)
public class StackComponent extends Gui implements IComponent {
    @Override
    public char getID() {
        return 's';
    }

    @Override
    public String init(String string, String arg, String group, BookWiki bookWiki) {
        return string.replace(group, group + "\n\n");
    }

    @Override
    public void render(Minecraft mc, BookWiki bookWiki, String arg, BookWikiGui gui, int x, int y, int mouseX, int mouseY) {
        int stackSize = 1;
        if (arg.contains("*")) {
            String[] s = arg.split("\\*");
            arg = s[0];
            stackSize = Integer.parseInt(s[1]);
        }
        ItemStack stack = new ItemStack(Item.getByNameOrId(arg), stackSize);
        mc.getTextureManager().bindTexture(gui.getTexture());
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawModalRectWithCustomSizedTexture(x + 50, y, 292, 127, 18, 18, 512.0F, 512.0F);
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().zLevel = 100.0F;
        mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x + 51, y + 1);
        mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, x + 51, y + 1, null);
        mc.getRenderItem().zLevel = 0.0F;
        RenderHelper.disableStandardItemLighting();
    }

    @Override
    public void renderTooltip(Minecraft mc, BookWiki bookWiki, String arg, BookWikiGui gui, int x, int y, int mouseX, int mouseY) {
        int stackSize = 1;
        if (arg.contains("*")) {
            String[] s = arg.split("\\*");
            arg = s[0];
            stackSize = Integer.parseInt(s[1]);
        }
        ItemStack stack = new ItemStack(Item.getByNameOrId(arg), stackSize);
        if (mouseX + 1 >= x + 51 && mouseY + 1 >= y + 1 && mouseX + 1 < x + 51 + 18 && mouseY + 1 < y + 1 + 18) {
            gui.renderToolTip(mc, stack, mouseX, mouseY);
        }
    }
}
