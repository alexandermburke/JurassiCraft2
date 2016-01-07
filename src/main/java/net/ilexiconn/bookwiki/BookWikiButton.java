package net.ilexiconn.bookwiki;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookWikiButton extends GuiButton {
    private final boolean next;

    public BookWikiButton(int id, int x, int y, boolean next) {
        super(id, x, y, 23, 13, "");
        this.next = next;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(BookWikiGui.TEXTURE);
            int x = 0;
            int y = 192;

            if (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height) {
                x += 23;
            }

            if (!this.next) {
                y += 13;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, x, y, 23, 13);
        }
    }
}
