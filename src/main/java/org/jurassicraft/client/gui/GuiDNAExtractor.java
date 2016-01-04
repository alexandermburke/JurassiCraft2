package org.jurassicraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.container.ContainerDNAExtractor;

@SideOnly(Side.CLIENT)
public class GuiDNAExtractor extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation("jurassicraft:textures/gui/dna_extractor.png");
    /**
     * The player inventory bound to this GUI.
     */
    private final InventoryPlayer playerInventory;
    private final IInventory extractor;

    public GuiDNAExtractor(InventoryPlayer playerInv, IInventory dnaSequencer)
    {
        super(new ContainerDNAExtractor(playerInv, (TileEntity) dnaSequencer));
        this.playerInventory = playerInv;
        this.extractor = dnaSequencer;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items). Args : mouseX, mouseY
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.extractor.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Args : renderPartialTicks, mouseX, mouseY
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int progress = this.getProgress();
        this.drawTexturedModalRect(k + 77, l + 36, 176, 0, progress + 1, 16);
    }

    private int getProgress()
    {
        int j = this.extractor.getField(0);
        int k = this.extractor.getField(1);
        return k != 0 && j != 0 ? j * 24 / k : 0;
    }
}
